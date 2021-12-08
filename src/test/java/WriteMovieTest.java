//import Entity.Movie;
//import InterfaceAdapter.AdminInputProcessor;
//import InterfaceAdapter.Controller.NormalCCoin;
//import InterfaceAdapter.Controller.NormalCMovie;
//import InterfaceAdapter.Controller.NormalCUser;
//import Framework.DataAccess.WriteMovie;
//import UseCase.MovieManager;
//import org.junit.*;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.file.FileSystems;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import static org.junit.Assert.*;
//
//public class WriteMovieTest {
//
//    //use case
//
//
//    //gateway
//    public static WriteMovie wm;
//    private static MovieManager mm;
//
//    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
//
//
//    @BeforeClass
//    public static void setUpBeforeClass() {
//    }
//
//    @Before
//    public void setUp() {
//        //controller
//        NormalCUser ncu = new NormalCUser();
//        NormalCCoin ncc = new NormalCCoin();
//        NormalCMovie ncm = new NormalCMovie();
//        AdminInputProcessor aucontroller = new AdminInputProcessor();
//        mm = new MovieManager();
//
//        wm = new WriteMovie(str1 + "/src/test/res/Moviedata", str1 + "/src/test/res/Moviereview",
//                str1 + "/src/test/res", ncu, ncc, ncm, aucontroller, mm);
//    }
//
//    @Test
//    public void testcordFileandlike() {
//        try{
//            HashMap<Object, Object> map = new HashMap<>();
//            map.put("hahaha", "Ella");
//
//            assertTrue(wm.createFile("LL", "123456abc", "a"));
//            assertTrue(wm.deleteFile("LL"));
//
//            wm.addLikeToFile("LL","Increase");
//            wm.addLikeToFile("LL","Increase");
//
//            assertTrue(fileContentTest(wm.readFile("LL", "Moviedata"), this.readFile("LL","Moviedata")));
////            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/Moviedata/" + "LL" + ".txt")));
////            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/Moviereview/" + "LL" + " reviews.properties")));
//
//
//        }
//        catch (IOException e){
//            System.out.println("create file test for Core.Movie fails");
//        }
//
//    }
//
//    @Test
//    public void testgetObjectFromFile() {
//        HashMap<Object, Object> map = new HashMap<>();
//
//        ArrayList<Movie> l = mm.getMovies();
//
//        assertEquals(6, l.size());
//
//        Object[] moviefile = {"Apple", "Banana", "Candy", "Happy Life", "Team28", "Water"};
//
//        for (int i = 0; i < 3; i++){
//            assertEquals(l.get(i).getMoviename(), moviefile[i]);
//        }
//    }
//
//
//    public boolean fileContentTest(ArrayList<String> ul, ArrayList<String> infoList) {
//        for (int i = 0; i < ul.size(); i++){
//            if (!infoList.get(i).equals(ul.get(i)))
//                return false;
//        }
//        return true;
//    }
//
//    public ArrayList<String> readFile(String fn, String folder) throws IOException {
//        FileReader moviereader = new FileReader(str1 + "/src/main/res/" + folder + "/" + fn);
//        BufferedReader getmovie = new BufferedReader(moviereader);
//
//        ArrayList<String> lst = new ArrayList<>();
//        String line = getmovie.readLine();
//        while (line != null) {
//            lst.add(line);
//            line = getmovie.readLine();
//        }
//        getmovie.close();
//
//        return lst;
//    }
//
//
//}
