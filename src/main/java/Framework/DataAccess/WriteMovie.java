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
 * Should be called by Movie related class.
 */

@SuppressWarnings("SingleStatementInBlock")
public class WriteMovie implements WriteMovieInterface {
    protected Gateway gateway = new Gateway();

    protected FileReader movieReader;
    protected BufferedReader getMovie;
    protected FileWriter writeMovie;
    protected Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
    protected File MovieFolderPath = new File(str1 + "/src/main/res/Moviedata"); //get full path for MovieData folder
    protected String ReadPath = str1 + "/src/main/res/"; //get path for readFile method
    protected String MoviePath = str1 + "/src/main/res/Moviedata/"; //get path to moviedata folder


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
     * @param readPath test res folder Path
     */
    public WriteMovie(String moviePath, String readPath){
        this.MovieFolderPath = new File(moviePath);
        this.MoviePath = moviePath;
        this.ReadPath = readPath;

        getObjectFromFile();

    }

    /**
     * Create a file for a single movie in corresponding category folder
     * @param movieName name of the movie
     * @param movieLink link of the movie
     * @param category category of the movie
     * @return true if the file successfully created, false if not.
     */
    @Override
    public boolean createFile(String movieName, String movieLink, String category) {

        try{
            writeMovie = new FileWriter(MoviePath + category + "/" + movieName + ".txt");
            writeMovie.write(movieName);
            writeMovie.write("\r\n");
            writeMovie.write(movieLink);
            writeMovie.write("\r\n");
            writeMovie.write("0");
            writeMovie.write("\r\n");
            writeMovie.write(category);
            writeMovie.close();
            File movieFile = new File(MoviePath + category + "/" + movieName + ".txt");


            return movieFile.exists();
        }
        catch (IOException e){
            System.out.println("Cannot create the file");
            return false;
        }
    }


    /**
     * Add or remove one like of a movie updated into movie file
     * @param movieName the name of the movie
     * @param state if "Increase", add one like to file, others, decrease one like from file.
     * @param category category of the movie
     * @return true if the change of like in movie file succeeded, false if not
     */
    @Override
    public boolean addLikeToFile(String movieName, String state, String category) {
        try {
            ArrayList<String> lst = new ArrayList<>(readFile(movieName + ".txt", "Moviedata/"));
            writeMovie = new FileWriter(MoviePath + category + "/" + movieName + ".txt");
            if (state.equals("Increase")){

                lst.set(2,Integer.toString(Integer.parseInt(lst.get(2)) + 1));
            }
            else{
                lst.set(2, Integer.toString(Integer.parseInt(lst.get(2)) - 1));
            }

            for (String str : lst) {
                writeMovie.write(str);
                writeMovie.write("\r\n");
            }
            writeMovie.close();
            return true;
        }
        catch (IOException e){
            System.out.println("Cannot add like to file");
            return false;
        }
    }

    /**
     * At the beginning state of program, this method will read all the file in the MovieData folder and create object for
     * each movie, then store all the movie object into MovieManager.
     */
    @Override
    public void getObjectFromFile() {
        try {
            File MovieCPathFile = new File(MoviePath); //get full path for MovieData folder

            String[] lstOfMovieC = MovieCPathFile.list();// get all the file name in MovieData folder
            ArrayList<String> lstOfMovie = new ArrayList<>();
            assert lstOfMovieC != null;
            for (String str: lstOfMovieC){
                File MoviePathFile = new File(MoviePath + str);
                String[] lstOfMovies = MoviePathFile.list();
                assert lstOfMovies != null;
                lstOfMovie.addAll(Arrays.asList(lstOfMovies));
            }
            for (String m : lstOfMovie) {
                ArrayList<String> lst = readFile(m, "Moviedata");

                // create object for a single movie
                this.gateway.createFileMovie(lst.get(0), lst.get(1), lst.get(3), Integer.parseInt(lst.get(2)));
            }
        }
        catch (IOException e){
            System.out.println("Unable to get the file from the Core.Movie Folder");
        }
    }


    /**
     * Delete a movie with corresponding name and category.
     * @param movie the name of the movie
     * @param category the category of the movie
     * @return true if the movie file had been successfully deleted, false if not
     */
    public boolean deleteFile(String movie, String category) {
        File movieFile = new File(MoviePath + category + "/" + movie + ".txt");
        return movieFile.delete();
    }

    /**
     * Helper method for WriteMovie class, returns a list of data read from a movie data file.
     * @param fn the name of the movie
     * @param folder the name of the folder containing the movie data, mostly MovieData
     * @return An arraylist contains all the data of a single movie
     */
    @SuppressWarnings({"Duplicates", "rawtypes", "unchecked"})
    public ArrayList<String> readFile(String fn, String folder) throws IOException {
        try{
            File f = new File(ReadPath + folder + "/") ;
            String[] lst1 =  f.list();
            HashMap<String[], String> map = new HashMap();
            assert lst1 != null;
            for (String s: lst1){
                File f1 = new File(ReadPath + folder + "/" + s + "/");
                String[] lst2 = f1.list();
                map.put(lst2, s);
            }
            for (String[] s1: map.keySet()){
                if (Arrays.asList(s1).contains(fn)){
                    movieReader = new FileReader(ReadPath + folder + "/" + map.get(s1) + "/" +fn);
                }
            }
            getMovie = new BufferedReader(movieReader);

            ArrayList<String> lst = new ArrayList<>();
            String line = getMovie.readLine();
            while (line != null) {
                lst.add(line);
                line = getMovie.readLine();
            }
            getMovie.close();

            return lst;
        }
        catch (Exception e){
            System.out.println("Unable to read movie file");
            return null;
        }
    }
}


