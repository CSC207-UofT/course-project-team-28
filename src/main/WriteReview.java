import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;


/**
 * Called for read and writer review's file
 * it should be only called by Review class and its subclass.
 */

public class WriteReview implements WriteFile{
    protected FileReader reviewreader;
    protected BufferedReader getreview;
    protected FileWriter writereview;


    /**
     * Called by Review constructor, which is designed for creating a new file to store the new review, including
     * the reviewer username, movie name and review content.
     */

    @Override
    public void create_file(Object review) throws IOException {
        Path path1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        writereview = new FileWriter(path1 + "\\src\\main\\Review\\" + ((Review) review).ID + ".txt");
        writereview.write(((Review) review).reviewer);
        writereview.write("\r\n");
        writereview.write(((Review) review).movie);
        writereview.write("\r\n");
        writereview.write(((Review) review).review_content);
        writereview.write("\r\n");
        writereview.write(Integer.toString(((Review) review).ID));
        writereview.close();
    }

    /**
     * Read the Review folder, create object for each review and return an ArrayList, which consists of Review Objects.
     */
    @Override
    public ArrayList<Review> get_object_from_file() throws IOException{
        // get the path of src
        Path path2 = FileSystems.getDefault().getPath("").toAbsolutePath();
        // get the path of Review folder
        File ReviewPath = new File(path2 + "\\src\\main\\Review\\");

        // get the file name in the Review folder
        String[] lstOfReview = ReviewPath.list();
        // the return list consisting of Reviews
        ArrayList<Review> Review_lst = new ArrayList<>();

        // when there is no file in the Review folder
        if(lstOfReview == null){
            return new ArrayList<>();
        }
        // when there are files in the Review folder
        else{
            for(String r: lstOfReview) {
                ArrayList<String> lst = read_file(path2, r, "Review");

                Review re = new Review(lst.get(0), lst.get(1), lst.get(2), Integer.parseInt(lst.get(3))); // create object foe this single review

                Review_lst.add(re);
            }
        }
        return Review_lst;
    }

    /**
     * read the Review file
     */
    public ArrayList<String> read_file(Path path2, String fileOfReview, String folder) throws IOException{
        reviewreader = new FileReader(path2.toString() + "\\src\\main\\" + folder + "\\" + fileOfReview);
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
