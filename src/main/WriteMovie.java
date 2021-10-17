import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

import static java.lang.System.out;

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
     * @return boolean
     */

    @Override
    public boolean create_file(Object movie) throws IOException {
        Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        writemovie = new FileWriter(p1.toString() + "\\src\\main\\Moviedata\\" + ((Movie) movie).moviename + ".txt");
        writemovie.write(((Movie) movie).moviename);
        writemovie.write("\r\n");
        writemovie.write(((Movie) movie).movielink);
        writemovie.write("\r\n");
        writemovie.write(String.valueOf(((Movie) movie).Likes));
        writemovie.close();
        Properties properties = new Properties();
        properties.putAll(((Movie) movie).moviereviews);
        FileOutputStream fos;
        fos = new FileOutputStream(p1.toString() + "\\src\\main\\Moviereview\\" + ((Movie) movie).moviename + " reviews.properties");
        IgnoreFirstLineBufferedWriter ilfw = new IgnoreFirstLineBufferedWriter(new OutputStreamWriter(fos), 0);
        properties.store(ilfw, null);
        String string = fos.toString();
        String sep = System.getProperty("line.separator");
        String content = string.substring(string.indexOf(sep) + sep.length());
        out.write(content.getBytes(StandardCharsets.UTF_8));
        fos.close();
        File moviefile = new File(p1.toString() + "\\src\\main\\Moviedata\\" + ((Movie) movie).moviename + ".txt");
        File moviereview = new File(p1.toString() + "\\src\\main\\Moviereview\\" + ((Movie) movie).moviename + " reviews.properties");
        return moviefile.exists() & moviereview.exists();
    }


    public void add_review_to_file(Review review) throws IOException {
        Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        FileOutputStream fos;
        Properties properties = new Properties();
        properties.put(review.review_content, review.reviewer);
        File file = new File(p1.toString() + "\\src\\main\\Moviereview\\" + review.movie + " reviews.properties");
        fos = new FileOutputStream(file, true);
        IgnoreFirstLineBufferedWriter ilfw = new IgnoreFirstLineBufferedWriter(new OutputStreamWriter(fos), 0);
        properties.store(ilfw, null);
        String string = fos.toString();
        String sep = System.getProperty("line.separator");
        String content = string.substring(string.indexOf(sep) + sep.length());
        out.write(content.getBytes(StandardCharsets.UTF_8));
        fos.close();
    }

    public String add_like_to_file(Movie movie) throws IOException {
        Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        ArrayList<String> lst = new ArrayList<>(read_file(p1, movie.moviename + ".txt", "Moviedata"));
        writemovie = new FileWriter(p1.toString() + "\\src\\main\\Moviedata\\" + movie.moviename + ".txt");
        lst.set(2, String.valueOf(movie.getLikes()));
        for(String str: lst){
            writemovie.write(str);
            writemovie.write("\r\n");
        }
        writemovie.close();
        return lst.get(2);
    }

    /* Replace method for add_review
    public void add_review(Review review) throws IOException {
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
     * @return ArrayList</Movie>
     */

    @Override
    public ArrayList<Movie> get_object_from_file() throws IOException {
        Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
        File MoviePath = new File(p1.toString() + "\\src\\main\\Moviedata"); //get full path for Moviedata folder
        File MoviereviewPath = new File(p1.toString() + "\\src\\main\\Moviereview"); //get full path for Moviereview folder

        String[] lstOfMovie = MoviePath.list();// get all the file name in Moviedata folder
        String[] lstOfMovieReview = MoviereviewPath.list();// get all the file name in Moviereview folder

        ArrayList<Movie> Movie_lst = new ArrayList<>(); // the list that will be returned, containing Movies.

        if(lstOfMovie == null){
            return new ArrayList<>();
        }
        else{
            for(String m: lstOfMovie) {
                ArrayList<String> lst = read_file(p1, m, "Moviedata");
                HashMap<Object, Object> moviereview = new HashMap<Object, Object>(); // initialise a HashMap
                String a = m.replace(".txt", "");
                FileInputStream movier = new FileInputStream(MoviereviewPath.toString() + "\\" + a + " reviews.properties");
                PushbackInputStream p = new PushbackInputStream(movier);
                int b;
                b = p.read();
                if (b != -1) {
                    Properties properties = new Properties();
                    properties.load(movier);
                    for (String key : properties.stringPropertyNames()) {
                        String value = properties.getProperty(key);
                        moviereview.put(key, value);
                    }
                    } // Find the corresponding review file for movie and change the HashMap created to the stored one

                Movie movie = new Movie(lst.get(0), lst.get(1)); // create object for a single movie
                movie.GetReviewandLike(moviereview, Integer.parseInt(lst.get(2))); // put in all parameters
                Movie_lst.add(movie);
            }
        }
        return Movie_lst;
    }

    /**
     * Delete a movie file with corresponding movie review file.
     * @return Boolean
     */

    public Boolean delete_file(Object movie) throws IOException {
        Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        File moviefile = new File(p1.toString() + "\\src\\main\\Moviedata\\" + ((Movie) movie).moviename + ".txt");
        File moviereviewfile = new File(p1.toString() + "\\src\\main\\Moviereview\\" + ((Movie) movie).moviename + " reviews.properties");
        Boolean a = moviefile.delete();
        Boolean b = moviereviewfile.delete();
        return a & b;
    }

    /**
     * Helper method
     */
    public ArrayList<String> read_file(Path str1, String fn, String folder) throws IOException {
        moviereader = new FileReader(str1.toString() + "\\src\\main\\" + folder + "\\" + fn);
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


