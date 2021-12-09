package UseCase;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;




public class MovieManagerTest {

    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
    private static MovieManager mm;

    @BeforeClass
    public static void setUp() {
        //InstanceMain setUp
        InstanceMain.setClearInstance();
        WriteUser wu = new WriteUser(str1 + "/src/test/res/NormalUser", str1 +
                "/src/test/res/AdminUser");
        WriteReview wr = new WriteReview();
        WriteMovie wm = new WriteMovie(str1 + "/src/test/res/Moviedata/", str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(wu, wm, wr);
        mm = InstanceMain.getMovieManager();
    }


    @Test
    public void TestAddMovie() throws IOException {

        Assert.assertTrue(mm.addMovie("Go", "www.go.com", "Action", 0));
        Files.deleteIfExists(Path.of(str1 + "/src/test/res/Moviedata/" + "Action" + "Go.txt"));
    }

    @Test
    public void TestAddNewMovie() throws IOException {

        Assert.assertTrue(mm.addNewMovie("Akira", "www.Akira.com", "Anime"));
        Files.deleteIfExists(Path.of(str1 + "/src/test/res/Moviedata/" + "Anime" + "Akira.txt"));
    }

    @Test
    public void TestGetMovie(){
        mm.addMovie("Go", "www.go.com", "Action", 0);
        Assert.assertEquals("Go", mm.getMovie("Go").getMovieName());
    }

    @Test
    public void TestIfMovieExist(){

        Assert.assertTrue(mm.IfMovieExist("Akira", "www.Akira.com"));
    }

    @Test
    public void TestGetMovieProfile() {

        Assert.assertTrue(mm.getMovieProfile("Akira").toString().startsWith("[Ljava.lang.Object;"));
    }

    @Test
    public void TestGetMovieCategory() {

        Assert.assertEquals("Anime", mm.getMovieCategory("Akira"));
    }

    @Test
    public void TestLikeMovie() {
        mm.likeMovie("Akira");
        Assert.assertEquals(mm.getMovie("Akira").Likes, 2);
    }


    @Test
    public void TestRankedMoviesProfile() {

        Assert.assertTrue(mm.rankedMoviesProfile().toString().startsWith("[[Ljava.lang.Object;"));
    }



}
