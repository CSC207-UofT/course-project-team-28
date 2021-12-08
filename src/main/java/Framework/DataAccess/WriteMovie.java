package Framework.DataAccess;

import InterfaceAdapter.Gateway;
import InterfaceAdapter.Interface.WriteMovieInterface;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Called for read and write movie's file.
 */

public class WriteMovie implements WriteMovieInterface {
    protected Gateway gateway = new Gateway();

    protected FileReader moviereader;
    protected BufferedReader getmovie;
    protected FileWriter writemovie;
    protected Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
    protected File MovieFolderPath = new File(str1 + "/src/main/res/Moviedata"); //get full path for Moviedata folder
    protected String ReadPath = str1 + "/src/main/res/"; //get path for readfile mthod
    protected String MoviePath = str1 + "/src/main/res/Moviedata/"; //get path to moviedata folder


    /*
     * Creates files for movies and saved in Moviedata + corresponding category
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
     * @param moviePath Movie test folder path
     */
    public WriteMovie(String moviePath, String readPath){
        this.MovieFolderPath = new File(moviePath);
        this.MoviePath = moviePath;
        this.ReadPath = readPath;

        getObjectFromFile();

    }
    @Override
    public boolean createFile(String movieName, String movieLink, String category) {

        try{
            writemovie = new FileWriter(MoviePath + category + "/" + movieName + ".txt");
            writemovie.write(movieName);
            writemovie.write("\r\n");
            writemovie.write(movieLink);
            writemovie.write("\r\n");
            writemovie.write("0");
            writemovie.write("\r\n");
            writemovie.write(category);
            writemovie.close();
            File moviefile = new File(MoviePath + category + "/" + movieName + ".txt");


            return moviefile.exists();
        }
        catch (IOException e){
            System.out.println("Cannot create the file");
            return false;
        }
    }


    @Override
    public boolean addLikeToFile(String movieName, String state, String category) {
        try {
            ArrayList<String> lst = new ArrayList<>(readFile(movieName + ".txt", "Moviedata/"));
            writemovie = new FileWriter(MoviePath + category + "/" + movieName + ".txt");
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
     * Read Moviedata folders, create obejct for each movie.
     */

    @Override
    public void getObjectFromFile() {
        try {
            File MoviecPathFile = new File(MoviePath); //get full path for Moviedata folder

            String[] lstOfMoviec = MoviecPathFile.list();// get all the file name in Moviedata folder
            ArrayList<String> lstOfMovie = new ArrayList<>();
            for (String str: lstOfMoviec){
                File MoviePathFile = new File(MoviePath + str);
                String[] lstOfMovies = MoviePathFile.list();
                lstOfMovie.addAll(Arrays.asList(lstOfMovies));
            }
            if (lstOfMovie != null) {
                for (String m : lstOfMovie) {
                    ArrayList<String> lst = readFile(m, "Moviedata");

                    // create object for a single movie
                    this.gateway.createFileMovie(lst.get(0), lst.get(1), lst.get(3), Integer.parseInt(lst.get(2)));
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

    public boolean deleteFile(String movie, String category) {
        File moviefile = new File(str1 + "/src/test/res/Moviedata/" + category + "/" + movie + ".txt");
        return moviefile.delete();
    }

    /**
     * Helper method
     */
    public ArrayList<String> readFile(String fn, String folder) throws IOException {
        try{
            File f = new File(ReadPath + folder + "/") ;
            String[] lst1 =  f.list();
            HashMap<String[], String> map = new HashMap();
            for (String s: lst1){
                File f1 = new File(ReadPath + folder + "/" + s + "/");
                String[] lst2 = f1.list();
                map.put(lst2, s);
            }
            for (String[] s1: map.keySet()){
                if (Arrays.stream(s1).anyMatch(fn::equals)){
                    moviereader = new FileReader(ReadPath + folder + "/" + map.get(s1) + "/" +fn);
                }
            }
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
        catch (Exception e){
            System.out.println("Unable to read movie file");
            return null;
        }
    }
}


