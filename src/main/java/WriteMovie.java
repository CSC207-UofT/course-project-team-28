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

public class WriteMovie implements WriteFile{
    private MovieManager mm;

    protected FileReader moviereader;
    protected BufferedReader getmovie;
    protected FileWriter writemovie;
    protected NormalCUser nip;
    protected AdminInputProcessor aip;

    /**
     * Creates files for movies and saved in Moviedata,
     * also files for movie reviews separately saved in Moviereviews.
     * @return boolean
     */

    public WriteMovie(NormalCUser nip, AdminInputProcessor aip, MovieManager mm){
        this.mm = mm;
        getObjectFromFile();

        this.nip = nip;
        this.aip = aip;
        this.nip.setMovMana(mm);
        this.aip.setMov_mana(mm);
    }

    @Override
    public boolean createFile(String movieName, String movieLink, String z) {

        try{
            Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath();
            writemovie = new FileWriter(p1 + "/src/main/res/Moviedata/" + movieName + ".txt");
            writemovie.write(movieName);
            writemovie.write("\r\n");
            writemovie.write(movieLink);
            writemovie.write("\r\n");
            writemovie.write("0");
            writemovie.close();
            Properties properties = new Properties();
            properties.putAll(new HashMap<>());
            FileOutputStream fos;
            fos = new FileOutputStream(p1 + "/src/main/res/Moviereview/" + movieName + " reviews.properties");
            IgnoreFirstLineBufferedWriter ilfw = new IgnoreFirstLineBufferedWriter(new OutputStreamWriter(fos), 0);
            properties.store(ilfw, null);
            String string = fos.toString();
            String sep = System.getProperty("line.separator");
            String content = string.substring(string.indexOf(sep) + sep.length());
            out.write(content.getBytes(StandardCharsets.UTF_8));
            fos.close();
            File moviefile = new File(p1 + "/src/main/res/Moviedata/" + movieName + ".txt");
            File moviereview = new File(p1 + "/src/main/res/Moviereview/" + movieName + " reviews.properties");

            this.mm.add_movie(movieName, movieLink, new HashMap<>(), 0);

            return moviefile.exists() && moviereview.exists() && this.mm.add_movie(movieName, movieLink, new HashMap<>(), 0);
        }
        catch (IOException e){
            System.out.println("Cannot create the file");
            return false;
        }
    }


    public void addReviewToFile(String userName, String movieName, String reviewContent) {
        try {
            Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath();
            FileOutputStream fos;
            Properties properties = new Properties();
            properties.put(reviewContent, userName);
            File file = new File(p1 + "/src/main/res/Moviereview/" + movieName + " reviews.properties");
            fos = new FileOutputStream(file, true);
            IgnoreFirstLineBufferedWriter ilfw = new IgnoreFirstLineBufferedWriter(new OutputStreamWriter(fos), 0);
            properties.store(ilfw, null);
            String string = fos.toString();
            String sep = System.getProperty("line.separator");
            String content = string.substring(string.indexOf(sep) + sep.length());
            out.write(content.getBytes(StandardCharsets.UTF_8));
            fos.close();
        }
        catch (IOException e){
            System.out.println("Cannot add review to file");
        }

    }

    public void addLikeToFile(String movieName, String state) {
        try {
            Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath();
            ArrayList<String> lst = new ArrayList<>(readFile(p1, movieName + ".txt", "Moviedata"));
            writemovie = new FileWriter(p1 + "/src/main/res/Moviedata/" + movieName + ".txt");
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
        }
        catch (IOException e){
            System.out.println("Cannot add like to file");
        }
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
    public void getObjectFromFile() {
        try {
            Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
            File MoviePath = new File(p1 + "/src/main/res/Moviedata"); //get full path for Moviedata folder
            File MoviereviewPath = new File(p1 + "/src/main/res/Moviereview"); //get full path for Moviereview folder

            String[] lstOfMovie = MoviePath.list();// get all the file name in Moviedata folder

            if (lstOfMovie == null) {

            } else {
                for (String m : lstOfMovie) {
                    ArrayList<String> lst = readFile(p1, m, "Moviedata");
                    HashMap<Object, Object> moviereview = new HashMap<>(); // initialise a HashMap
                    String a = m.replace(".txt", "");
                    FileInputStream movier = new FileInputStream(MoviereviewPath + "/" + a + " reviews.properties");
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

                     // create object for a single movie
                    this.mm.add_movie(lst.get(0), lst.get(1), moviereview, Integer.parseInt(lst.get(2)));
                }
            }
        }
        catch (IOException e){
            System.out.println("Unable to get the file from the Movie Folder");
        }
    }

    /**
     * Delete a movie file with corresponding movie review file.
     * @return Boolean
     */

    public Boolean deleteFile(Object movie) {
        Path p1 = FileSystems.getDefault().getPath("").toAbsolutePath();
        File moviefile = new File(p1 + "/src/main/res/Moviedata/" + ((Movie) movie).moviename + ".txt");
        File moviereviewfile = new File(p1 + "/src/main/res/Moviereview/" + ((Movie) movie).moviename + " reviews.properties");
        Boolean a = moviefile.delete();
        Boolean b = moviereviewfile.delete();
        return a & b;
    }

    /**
     * Helper method
     */
    public ArrayList<String> readFile(Path str1, String fn, String folder) throws IOException {
        moviereader = new FileReader(str1.toString() + "/src/main/res/" + folder + "/" + fn);
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


