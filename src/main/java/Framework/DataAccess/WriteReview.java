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

    protected BufferedReader getReview;
    protected FileWriter writeReview;
    protected final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
    protected File ReviewFolderPath = new File(str1 + "/src/main/res/Review"); //get full path for Review folder
    protected String halfRvPath = str1 + "/src/main/res/Review/"; //get half path for Review file
    protected final Gateway gateway = new Gateway();




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

    /**
     *
     * @param currUserName the name of review writer
     * @param movieName the name of movie
     * @param revContent the content of review
     * @param ID the ID of review
     * @return true if create review file successfully
     */
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
    }


    /** add coins to the review.
     */
    @Override
    public boolean addCoinsToReview(int id, int numCoin) {
        ArrayList<Object> revLst = new ArrayList<>(readFile(halfRvPath + id + ".txt"));
        revLst.set(3, Integer.parseInt((String) revLst.get(3)) + numCoin);
        String path1 = halfRvPath + id + ".txt";
        writeFile(path1, revLst);
        return true;
    }


    /**
     * Read the Core.Review folder, create object for each review and return an ArrayList, which consists of Core.Review Objects.
     */
    @Override
    public void getObjectFromFile() {
        String[] lstOfReview = ReviewFolderPath.list();

        if (lstOfReview != null) {
            for (String rv : lstOfReview) {
                ArrayList<Object> lst = readFile(halfRvPath + rv);

                this.gateway.createFileReview((String) lst.get(0),(String) lst.get(1),(String)lst.get(2),
                        Integer.parseInt((String) lst.get(3)), Integer.parseInt((String) lst.get(4)));

            }
        }
    }


    /**
     * read the Core.Review file
     */
    public ArrayList<Object> readFile(String path){
        ArrayList<Object> lst = new ArrayList<>();
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


}
