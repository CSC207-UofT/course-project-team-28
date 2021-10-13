import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 * Called for read and write movie's file.
 */

public class WriteMovie implements WriteFile{
        public FileReader moviereader;
        public BufferedReader getmovie;
        public FileWriter writemovie;

    /**
     * Creates files for movies and saved in Moviedata,
     * also files for movie reviews separately saved in Moviereviews.
     */

    @Override
    public void create_file(Object movie) throws IOException {
        Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        writemovie = new FileWriter(p1.toString() + "\\main\\Moviedata\\" + ((Movie) movie).moviename + ".txt");
        writemovie.write(((Movie) movie).moviename);
        writemovie.write("\r\n");
        writemovie.write(((Movie) movie).movielink);
        writemovie.write("\r\n");
        writemovie.write(((Movie) movie).Likes);
        writemovie.close();
        Properties properties = new Properties();
        properties.putAll(((Movie) movie).moviereviews);
        properties.store(new FileOutputStream(p1.toString() + "\\main\\Moviereview\\" + ((Movie) movie).moviename + " reviews.txt"), null);
    }

    /**
     * Read Moviedata and Moviereviews two folders, create obejct for each movie and return a two-dimensional array.
     * the movie reviews are taken from the Moviereviews file while other parameters taken from Moviedata.
     */

    @Override
    public ArrayList<Object> get_object_from_file() throws IOException {
        Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
        File MoviePath = new File(p1.toString() + "\\main\\Moviedata"); //get full path for Moviedata folder
        File MoviereviewPath = new File(p1.toString() + "\\main\\Moviereview"); //get full path for Moviereview folder

        String[] lstOfMovie = MoviePath.list();// get all the file name in Moviedata folder
        String[] lstOfMovieReview = MoviereviewPath.list();// get all the file name in Moviereview folder

        ArrayList<Object> Movie_lst = new ArrayList<Object>(); // the list that will be returned, containing Movies.

        if(Movie_lst == null){
            return new ArrayList<Object>();
        }
        else{
            for(String m: lstOfMovie) {
                ArrayList<String> lst = read_file(p1, m, "Moviedata");
                HashMap moviereview = null; // initialise a HashMap
                for (String mr : lstOfMovieReview) {
                    if (mr == m + " reviews") {
                        Properties properties = new Properties();
                        FileInputStream movier = new FileInputStream(MoviereviewPath.toString() + mr + ".txt");
                        properties.load(movier);
                        moviereview = new HashMap<Object, Object>(properties);
                    } // find the corresponding review file for movie and change the HashMap created to the stored one
                }
                Movie movie = new Movie(lst.get(0), lst.get(1)); // create object for a single movie
                movie.GetReviewandLike(moviereview, Integer.parseInt(lst.get(2))); // put in all parameters
                Movie_lst.add(movie);
            }
        }
        return Movie_lst;
    }


    public ArrayList<String> read_file(Path str1, String fn, String folder) throws IOException {
        moviereader = new FileReader(str1.toString() + "\\main\\" + folder + "\\" + fn);
        getmovie = new BufferedReader(moviereader);

        ArrayList<String> lst = new ArrayList<String>();
        String line = getmovie.readLine();
        while (line != null) {
            lst.add(line);
            line = getmovie.readLine();
        }
        getmovie.close();

        return lst;
    }
}


