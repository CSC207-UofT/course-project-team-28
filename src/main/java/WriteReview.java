import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;


/**
 * Called for read and writer review's file
 * it should be only called by Review class and its subclass.
 */

public class WriteReview implements WriteFile{
    private final ReviewManager rm ;

    protected FileReader reviewreader;
    protected BufferedReader getreview;
    protected FileWriter writereview;
    protected NormalInputProcessor nip;




    /**
     * Called by Review constructor, which is designed for creating a new file to store the new review, including
     * the reviewer username, movie name and review content. Once it is created successfully, it returns true.
     * Otherwise, return false.
     */


    public WriteReview(NormalInputProcessor nip, ReviewManager rm){
        this.rm = rm;

        getObjectFromFile();


        this.nip = nip;
        this.nip.setRev_mana(this.rm);
    }


    @Override
    public boolean createFile(String currUserName, String movieName, String revContent){
        try {

            boolean reviewExist = this.rm.write_review(currUserName, movieName, revContent, 0,-1);

            File file_if_exist;
            Path path1 = FileSystems.getDefault().getPath("").toAbsolutePath();
            writereview = new FileWriter(path1 + "/src/main/res/Review/" + this.rm.getReviewID() + ".txt");
            writereview.write(currUserName);
            writereview.write("\r\n");
            writereview.write(movieName);
            writereview.write("\r\n");
            writereview.write(revContent);
            writereview.write("\r\n");
            writereview.write("0");
            writereview.write("\r\n");
            writereview.write(Integer.toString(this.rm.getReviewID()));
            writereview.close();

            file_if_exist = new File(path1 + "/src/main/res/Review/" + this.rm.getReviewID() + ".txt");



            return file_if_exist.exists() && reviewExist;
        }
        catch (IOException e){
            System.out.println("Cannot create the file");
            return false;
        }
    }







    /**
     * Read the Review folder, create object for each review and return an ArrayList, which consists of Review Objects.
     */
    @Override
    public void getObjectFromFile(){
        try {
            // get the path of src
            Path path2 = FileSystems.getDefault().getPath("").toAbsolutePath();
            // get the path of Review folder
            File ReviewPath = new File(path2 + "/src/main/res/Review/");

            // get the file name in the Review folder
            String[] lstOfReview = ReviewPath.list();

            // when there is no file in the Review folder
            if (lstOfReview == null) {

            }
            // when there are files in the Review folder
            else {
                for (String r : lstOfReview) {
                    ArrayList<String> lst = readFile(path2, r, "Review");

                    // create object for this single review
                    this.rm.write_review(lst.get(0), lst.get(1), lst.get(2), Integer.parseInt(lst.get(3)), Integer.parseInt(lst.get(4)));

                }
            }
        }
        catch (IOException e){
            System.out.println("Unable to get the file from the Movie Folder");
        }
    }



    /**
     * read the Review file
     */
    public ArrayList<String> readFile(Path path2, String fileOfReview, String folder) throws IOException{
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
}
