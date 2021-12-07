package Framework.DataAccess;

import InterfaceAdapter.*;
import InterfaceAdapter.Interface.WriteReviewInterface;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;


/**
 * Called for read and writer review's file
 * it should be only called by Core.Review class and its subclass.
 */

public class WriteReview implements WriteReviewInterface {

    protected FileReader reviewreader;
    protected BufferedReader getreview;
    protected FileWriter writereview;
    protected Gateway gateway = new Gateway();




    /**
     * Called by Core.Review constructor, which is designed for creating a new file to store the new review, including
     * the reviewer username, movie name and review content. Once it is created successfully, it returns true.
     * Otherwise, return false.
     */


    public WriteReview(){
        getObjectFromFile();
    }

//    /**
//     * Constructor for test use only
//     * @param normalPath Core.User.NormalUser test folder path
//     * @param adminPath Core.User.AdminUser test folder path
//     */
//    public WriteReview(String normalPath, String adminPath){
//        this.AdminUserFolderPath = new File(adminPath);
//        this.NormalUserFolderPath = new File(normalPath);
//        this.halfAuPath = adminPath +"/";
//        this.halfNuPath = normalPath + "/";
//
//        getObjectFromFile();
//
//    }

    @Override
    public boolean createFile(String currUserName, String movieName, String revContent, int ID){
        try {

            File file_if_exist;
            Path path1 = FileSystems.getDefault().getPath("").toAbsolutePath();
            writereview = new FileWriter(path1 + "/src/main/res/Review/" + ID + ".txt");
            writereview.write(currUserName);
            writereview.write("\r\n");
            writereview.write(movieName);
            writereview.write("\r\n");
            writereview.write(revContent);
            writereview.write("\r\n");
            writereview.write("0");
            writereview.write("\r\n");
            writereview.write(Integer.toString(ID));
            writereview.close();

            file_if_exist = new File(path1 + "/src/main/res/Review/" + ID + ".txt");



            return file_if_exist.exists();
        }
        catch (IOException e){
            System.out.println("Cannot create the file");
            return false;
        }
    }


    /** add coins to the review.
     */
    @Override
    public boolean addCoinsToReview(int id, int numCoin) {
        try {
            Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath();
            ArrayList<Object> revLst = new ArrayList<>(readFile(p1, id+".txt", "Review"));
            revLst.set(3, Integer.parseInt((String) revLst.get(3)) + numCoin);
            String path1 = p1 + "/src/main/res/Review/" + id + ".txt";
            writeFile(path1, revLst);
            return true;
        }
        catch (Exception e){
            System.out.println("Cannot Add coin");
            return false;
        }

    }


    /**
     * Read the Core.Review folder, create object for each review and return an ArrayList, which consists of Core.Review Objects.
     */
    @Override
    public void getObjectFromFile(){
        try {
            // get the path of src
            Path path2 = FileSystems.getDefault().getPath("").toAbsolutePath();
            // get the path of Core.Review folder
            File ReviewPath = new File(path2 + "/src/main/res/Review/");

            // get the file name in the Review folder
            String[] lstOfReview = ReviewPath.list();

            // when there is no file in the Review folder
            if (lstOfReview != null) {
                for (String r : lstOfReview) {
                    ArrayList<String> lst = readFile(path2, r, "Review");

                    // create object for this single review
                    this.gateway.createFileReview(lst.get(0), lst.get(1), lst.get(2),
                            Integer.parseInt(lst.get(3)), Integer.parseInt(lst.get(4)));

                }
            }
        }
        catch (IOException e){
            System.out.println("Unable to get the file from the Movie Folder");
        }
    }



    /**
     * read the Core.Review file
     */
    private ArrayList<String> readFile(Path path2, String fileOfReview, String folder) throws IOException{
        reviewreader = new FileReader(path2.toString() + "/src/main/res/" + folder + "/" + fileOfReview);
        getreview = new BufferedReader(reviewreader);

        ArrayList<String> result = new ArrayList<>();
        String line = getreview.readLine();
        while(line != null){
            result.add(line);
            line = getreview.readLine();
        }
        getreview.close();

        return result;
    }

    /**
     * Helper method, write file
     */
    private void writeFile(String path, ArrayList<Object> lst) {
        try{
            writereview = new FileWriter(path);
            for(Object str: lst){
                writereview.write(str.toString());
                writereview.write("\r\n");
            }
            writereview.close();
        }
        catch (IOException e){
            System.out.println("Unable to write file");
        }

    }
}
