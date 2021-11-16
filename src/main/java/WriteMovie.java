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
    protected NormalCUser ncu;
    protected NormalCCoin ncc;
    protected NormalCMovie ncm;
    protected AdminInputProcessor aip;
    protected Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
    protected File MovieFolderPath = new File(str1 + "/src/main/res/Moviedata"); //get full path for Moviedata folder
    protected File MovieReFolderPath = new File(str1 + "/src/main/res/Moviereview"); //get full path for Moviereview folder
    protected String ReadPath = str1 + "/src/main/res/"; //get path for readfile mthod
    protected String MoviePath = str1 + "/src/main/res/Moviedata/"; //get path to moviedata folder
    protected String MovierePath = str1 + "/src/main/res/Moviereview/"; //get path to moviereview folder


    /**
     * Creates files for movies and saved in Moviedata,
     * also files for movie reviews separately saved in Moviereviews.
     * @return boolean
     */

    /**
     * call getObjectFromFile method first, then store all the object into all Manager classes, finally, store those
     * Manager classes into controller.
     * @param ncu NormalCUser(Controller) instance
     * @param ncc NormalCCoin(Controller) instance
     * @param ncm NormalCMovie(Controller) instance
     * @param aip AdminInputProcessor(Controller) instance
     * @param mm MovieManager(Use Case) instance
     */
    public WriteMovie(NormalCUser ncu, NormalCCoin ncc, NormalCMovie ncm, AdminInputProcessor aip, MovieManager mm){
        this.mm = mm;
        getObjectFromFile();

        this.ncu = ncu;
        this.ncc = ncc;
        this.ncm = ncm;
        this.aip = aip;
        this.ncu.setMovMana(this.mm);
        this.ncc.setMovMana(this.mm);
        this.ncm.setMovMana(this.mm);
        this.aip.setMov_mana(this.mm);
    }

    /**
     * Constructor for test use only
     * @param moviePath Movie test folder path
     * @param ncu NormalCUser(Controller) instance
     * @param ncc NormalCCoin(Controller) instance
     * @param ncm NormalCMovie(Controller) instance
     * @param aip AdminInputProcessor(Controller) instance
     * @param mm MovieManager(Use Case) instance
     */
    public WriteMovie(String moviePath, String movierePath, String readPath, NormalCUser ncu, NormalCCoin ncc,
                      NormalCMovie ncm, AdminInputProcessor aip, MovieManager mm){
        this.MovieFolderPath = new File(moviePath);
        this.MovieReFolderPath = new File(movierePath);
        this.MoviePath = moviePath +"/";
        this.MovierePath = movierePath + "/";
        this.ReadPath = readPath + "/";

        this.mm = mm;
        getObjectFromFile();

        this.ncu = ncu;
        this.ncc = ncc;
        this.ncm = ncm;
        this.aip = aip;
        this.ncu.setMovMana(this.mm);
        this.ncc.setMovMana(this.mm);
        this.ncm.setMovMana(this.mm);
        this.aip.setMov_mana(this.mm);
    }

    @Override
    public boolean createFile(String movieName, String movieLink, String z) {

        try{
            writemovie = new FileWriter(MoviePath + movieName + ".txt");
            writemovie.write(movieName);
            writemovie.write("\r\n");
            writemovie.write(movieLink);
            writemovie.write("\r\n");
            writemovie.write("0");
            writemovie.close();
            Properties properties = new Properties();
            properties.putAll(new HashMap<>());
            FileOutputStream fos;
            fos = new FileOutputStream(MovierePath + movieName + " reviews.properties");
            IgnoreFirstLineBufferedWriter ilfw = new IgnoreFirstLineBufferedWriter(new OutputStreamWriter(fos), 0);
            properties.store(ilfw, null);
            String string = fos.toString();
            String sep = System.getProperty("line.separator");
            String content = string.substring(string.indexOf(sep) + sep.length());
            out.write(content.getBytes(StandardCharsets.UTF_8));
            fos.close();
            File moviefile = new File(MoviePath + movieName + ".txt");
            File moviereview = new File(MovierePath + movieName + " reviews.properties");

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
            FileOutputStream fos;
            Properties properties = new Properties();
            properties.put(reviewContent, userName);
            File file = new File(MovierePath + movieName + " reviews.properties");
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
     */

    @Override
    public void getObjectFromFile() {
        try {
            File MoviePathFile = new File(MoviePath); //get full path for Moviedata folder

            String[] lstOfMovie = MoviePathFile.list();// get all the file name in Moviedata folder

            if (lstOfMovie == null) {

            } else {
                for (String m : lstOfMovie) {
                    ArrayList<String> lst = readFile(m, "Moviedata");
                    HashMap<Object, Object> moviereview = new HashMap<>(); // initialise a HashMap
                    String a = m.replace(".txt", "");
                    FileInputStream movier = new FileInputStream(MovierePath + "/" + a + " reviews.properties");
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


