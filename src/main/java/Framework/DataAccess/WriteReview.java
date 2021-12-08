package Framework.DataAccess;

import InterfaceAdapter.Gateway;
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

    protected FileReader reviewReader;
    protected BufferedReader getReview;
    protected FileWriter writeReview;
    protected Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
    protected File ReviewFolderPath = new File(str1 + "/src/main/res/Review"); //get full path for Review folder
    protected String halfRvPath = str1 + "/src/main/res/Review/"; //get half path for Review file
    protected Gateway gateway = new Gateway();




    /**
     * Called by Core.Review constructor, which is designed for creating a new file to store the new review, including
     * the reviewer username, movie name and review content. Once it is created successfully, it returns true.
     * Otherwise, return false.
     */


    public WriteReview(){
        getObjectFromFile();
    }

    /**
     * Constructor for test use only
     * @param reviewPath Core.User.NormalUser test folder path
     */
    public WriteReview(String reviewPath){
        this.ReviewFolderPath = new File(reviewPath);
        this.halfRvPath = reviewPath +"/";

        getObjectFromFile();

    }

    @Override
    public boolean createFile(String currUserName, String movieName, String revContent, int ID){
        File file_if_exist;
        ArrayList<Object> infoList = new ArrayList<>();
        infoList.add(currUserName);
        infoList.add(movieName);
        infoList.add(revContent);
        infoList.add(0);
        infoList.add(ID);

        writeFile(halfRvPath + ID + ".txt", infoList);
        file_if_exist = new File(halfRvPath + ID + ".txt");

        return file_if_exist.exists();
//        try {
//
//            File file_if_exist;
//            Path path1 = FileSystems.getDefault().getPath("").toAbsolutePath();
//            writeReview = new FileWriter(path1 + "/src/main/res/Review/" + ID + ".txt");
//            writeReview.write(currUserName);
//            writeReview.write("\r\n");
//            writeReview.write(movieName);
//            writeReview.write("\r\n");
//            writeReview.write(revContent);
//            writeReview.write("\r\n");
//            writeReview.write("0");
//            writeReview.write("\r\n");
//            writeReview.write(Integer.toString(ID));
//            writeReview.close();
//
//            file_if_exist = new File(path1 + "/src/main/res/Review/" + ID + ".txt");
//
//
//
//            return file_if_exist.exists();
//        }
//        catch (IOException e){
//            System.out.println("Cannot create the file");
//            return false;
//        }
    }


    /** add coins to the review.
     */
    @Override
    public boolean addCoinsToReview(int id, int numCoin) {
        try {
            ArrayList<Object> revLst = new ArrayList<>(readFile(str1 + Integer.toString(id) + ".txt"));
            revLst.set(3, Integer.parseInt((String) revLst.get(3)) + numCoin);
            String path1 = str1 + "/src/main/res/Review/" + id + ".txt";
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
    public void getObjectFromFile() {
//        String[] lstOfReview = ReviewFolderPath.list();
//
//        if (lstOfReview != null) {
//            for (String rv : lstOfReview) {
//                ArrayList<String> lst = readFile(halfRvPath, rv);
//
//                this.gateway.createFileReview(lst.get(0),lst.get(1),lst.get(2),Integer.parseInt(lst.get(3)),
//                        Integer.parseInt(lst.get(4)));
//
//                }
//            }
//        }


        try {
            String[] lstOfReview = ReviewFolderPath.list();

            if (lstOfReview != null) {
                for (String rv : lstOfReview) {
                    ArrayList<String> lst = readFile(halfRvPath + rv);

                    this.gateway.createFileReview(lst.get(0), lst.get(1), lst.get(2), Integer.parseInt(lst.get(3)),
                            Integer.parseInt(lst.get(4)));

                }
            }
        } catch (IOException e) {
            System.out.println("Unable to get the file from the Movie Folder");
        }
    }





//        try {
//            // get the path of src
//            Path path2 = FileSystems.getDefault().getPath("").toAbsolutePath();
//            // get the path of Core.Review folder
//            File ReviewPath = new File(path2 + "/src/main/res/Review/");
//
//            // get the file name in the Review folder
//            String[] lstOfReview = ReviewPath.list();
//
//            // when there is no file in the Review folder
//            if (lstOfReview != null) {
//                for (String r : lstOfReview) {
//                    ArrayList<String> lst = readFile(path2, r);
//
//                    // create object for this single review
//                    this.gateway.createFileReview(lst.get(0), lst.get(1), lst.get(2),
//                            Integer.parseInt(lst.get(3)), Integer.parseInt(lst.get(4)));
//
//                }
//            }
//        }
//        catch (IOException e){
//            System.out.println("Unable to get the file from the Movie Folder");
//        }
//    }



    /**
     * read the Core.Review file
     */
    private ArrayList<String> readFile(String path) throws FileNotFoundException {
        ArrayList<String> lst = new ArrayList<>();
        try {
            getReview = new BufferedReader(new FileReader(path));

            String line = getReview.readLine();
            while (line != null) {
                lst.add(line);
                line = getReview.readLine();
            }
            getReview.close();

            return lst;
        }
        catch (IOException e) {
            System.out.println("Unable to read Review file");
        }
        return lst;
    }
//        reviewReader = new FileReader(path2 + "/src/main/res/" + "Review/" + fileOfReview);
//        getReview = new BufferedReader(reviewReader);
//
//        ArrayList<String> result = new ArrayList<>();
//        String line = getReview.readLine();
//        while(line != null){
//            result.add(line);
//            line = getReview.readLine();
//        }
//        getReview.close();
//
//        return result;

    /**
     * Helper method, write file
     */
    private void writeFile(String path, ArrayList<Object> lst) {
        try{
            writeReview = new FileWriter(path);
            for(Object str: lst){
                writeReview.write(str.toString());
                writeReview.write("\r\n");
            }
            writeReview.close();
        }
        catch (IOException e){
            System.out.println("Unable to write file");
        }

    }

    /**
     * Only for test, to delete the file.
     */
    public boolean deleteReviewFile(int reviewID){
        Path path1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        File obj = new File(path1 +  "/src/test/res/Review/" + reviewID + ".txt");
        return obj.delete();
    }
}
