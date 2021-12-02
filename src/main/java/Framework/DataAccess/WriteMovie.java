package Framework.DataAccess;

import InterfaceAdapter.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import static java.lang.System.out;

/**
 * Called for read and write movie's file.
 */

public class WriteMovie implements WriteMovieInterface{
    protected Gateway gateway = new Gateway();

    protected FileReader moviereader;
    protected BufferedReader getmovie;
    protected FileWriter writemovie;
    protected Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
    protected File MovieFolderPath = new File(str1 + "/src/main/res/Moviedata"); //get full path for Moviedata folder
    protected String ReadPath = str1 + "/src/main/res/"; //get path for readfile mthod
    protected String MoviePath = str1 + "/src/main/res/Moviedata/"; //get path to moviedata folder


    /*
     * Creates files for movies and saved in Moviedata,
     * also files for movie reviews separately saved in Moviereviews.
     * @return boolean
     */

    /**
     * call getObjectFromFile method first, then store all the object into all Manager classes, finally, store those
     * Manager classes into controller.
     */
    public WriteMovie(){
        getObjectFromFile();

    }

    /**
     * Constructor for test use only
     * @param moviePath Core.Movie test folder path
     */
    public WriteMovie(String moviePath, String readPath){
        this.MovieFolderPath = new File(moviePath);
        this.MoviePath = moviePath +"/";
        this.ReadPath = readPath + "/";

        getObjectFromFile();

    }
    @Override
    public boolean createFile(String movieName, String movieLink) {

        try{
            writemovie = new FileWriter(MoviePath + movieName + ".txt");
            writemovie.write(movieName);
            writemovie.write("\r\n");
            writemovie.write(movieLink);
            writemovie.write("\r\n");
            writemovie.write("0");
            writemovie.close();
            File moviefile = new File(MoviePath + movieName + ".txt");


            return moviefile.exists();
        }
        catch (IOException e){
            System.out.println("Cannot create the file");
            return false;
        }
    }


    @Override
    public boolean addLikeToFile(String movieName, String state) {
        try {
            ArrayList<String> lst = new ArrayList<>(readFile(movieName + ".txt", "Moviedata"));
            writemovie = new FileWriter(MoviePath + movieName + ".txt");
            if (state.equals("Increase")){

                lst.set(2,Integer.toString(Integer.parseInt(lst.get(2)) + 1));
            }
            else{
                lst.set(2, Integer.toString(Integer.parseInt(lst.get(2)) - 1));
            }

            for (String str : lst) {
                writemovie.write(str);
                writemovie.write("\r\n");
            }
            writemovie.close();
            return true;
        }
        catch (IOException e){
            System.out.println("Cannot add like to file");
            return false;
        }
    }

    /* Replace method for add_review
    public void add_review(Core.Review review) throws IOException {
        Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        File file = new File(p1.toString() + "\\main\\Moviereview\\" + review.movie + " reviews.properties");
        FileOutputStream fos;
        FileInputStream fls;
        fls = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fls);
        properties.setProperty(review.review_content, review.reviewer);
        fos = new FileOutputStream(file);
        properties.store(fos, null);
        fos.close();
    }
    */

    /**
     * Read Moviedata and Moviereviews two folders, create obejct for each movie and return a two-dimensional array.
     * the movie reviews are taken from the Moviereviews file while other parameters taken from Moviedata
     */

    @Override
    public void getObjectFromFile() {
        try {
            File MoviePathFile = new File(MoviePath); //get full path for Moviedata folder

            String[] lstOfMovie = MoviePathFile.list();// get all the file name in Moviedata folder

            if (lstOfMovie != null) {
                for (String m : lstOfMovie) {
                    ArrayList<String> lst = readFile(m, "Moviedata");

                    // create object for a single movie
                    this.gateway.createFileMovie(lst.get(0), lst.get(1), Integer.parseInt(lst.get(2)));
                }
            }
        }
        catch (IOException e){
            System.out.println("Unable to get the file from the Core.Movie Folder");
        }
    }

    /**
     * Delete a movie file with corresponding movie review file.
     * @return Boolean
     */

    public Boolean deleteFile(String movie) {
        File moviefile = new File(str1 + "/src/test/res/Moviedata/" + movie + ".txt");
        File moviereviewfile = new File(str1 + "/src/test/res/Moviereview/" + movie + " reviews.properties");
        Boolean a = moviefile.delete();
        Boolean b = moviereviewfile.delete();
        return a & b;
    }

    /**
     * Helper method
     */
    public ArrayList<String> readFile(String fn, String folder) throws IOException {
        moviereader = new FileReader(ReadPath + folder + "/" + fn);
        getmovie = new BufferedReader(moviereader);

        ArrayList<String> lst = new ArrayList<>();
        String line = getmovie.readLine();
        while (line != null) {
            lst.add(line);
            line = getmovie.readLine();
        }
        getmovie.close();

        return lst;
    }
}


