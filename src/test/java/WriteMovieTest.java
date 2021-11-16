import org.junit.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class WriteMovieTest {

    //use case


    //gateway
    public static WriteMovie wm;
    private static MovieManager mm;

    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();


    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before
    public void setUp() {
        //controller
        NormalCUser ncu = new NormalCUser();
        NormalCCoin ncc = new NormalCCoin();
        NormalCMovie ncm = new NormalCMovie();
        AdminInputProcessor aucontroller = new AdminInputProcessor();
        mm = new MovieManager();

        wm = new WriteMovie(str1 + "/src/test/res/Moviedata", str1 + "/src/test/res/Moviereview",
                str1 + "/src/test/res", ncu, ncc, ncm, aucontroller, mm);
    }

    @Test
    public void testcordFileandlike() {
        try{
            HashMap<Object, Object> map = new HashMap<>();
            map.put("hahaha", "Ella");

            assertTrue(wm.createFile("LOL", "123456abc", "a"));

            wm.addLikeToFile("LOL","Increase");
            wm.addLikeToFile("LOL","Increase");

            assertTrue(fileContentTest(wm.readFile("LOL", "Moviedata"), this.readFile("LOL","Moviedata")));

            assertTrue(wm.deleteFile("LOL"));

        }
        catch (IOException e){
            System.out.println("create file test for Movie fails");
        }

    }

    @Test
    public void testgetObjectFromFile() {
        HashMap<Object, Object> map = new HashMap<>();
        mm.add_movie("Apple", "shdjhadshjasfhkasf", map, 0);
        mm.add_movie("Banana", "hjkdhfjshakjs", map, 0);
        mm.add_movie("Candy", "hudiadakjk", map, 0);

        ArrayList<Movie> l = mm.getMovies();

        assertEquals(3, l.size());

        Object[] moviefile = {"Apple", "Banana", "Candy"};

        for (int i = 0; i < 3; i++){
            assertEquals(l.get(i).getMoviename(), moviefile[i]);
        }
    }


    public boolean fileContentTest(ArrayList<String> ul, ArrayList<String> infoList) {
        for (int i = 0; i < ul.size(); i++){
            if (!infoList.get(i).equals(ul.get(i).toString()))
                return false;
        }
        return true;
    }

    public ArrayList<String> readFile(String fn, String folder) throws IOException {
        FileReader moviereader = new FileReader(str1 + "/src/main/res/" + folder + "/" + fn);
        BufferedReader getmovie = new BufferedReader(moviereader);

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
