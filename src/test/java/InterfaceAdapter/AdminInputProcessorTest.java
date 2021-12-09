package InterfaceAdapter;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import UseCase.MovieManager;
import UseCase.UserManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;

public class AdminInputProcessorTest {
    private static AdminInputProcessor adminInputProcessor;
    private static UserManager um;
    private static MovieManager mm;
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @BeforeClass
    public static void setUp() {
        InstanceMain.setClearInstance();
        WriteUser writeUser = new WriteUser(str1 + "/src/test/res/NormalUser",
                str1 + "/src/test/res/AdminUser");
        WriteReview writeReview = new WriteReview(str1 + "/src/test/res/Review");
        WriteMovie writeMovie = new WriteMovie(str1 + "/src/test/res/Moviedata/",
                str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);
        adminInputProcessor = InstanceMain.getAdminInputProcessor();
        um = InstanceMain.getUserManager();
        mm = InstanceMain.getMovieManager();
    }

    @Test
    public void registerTest() {
        assertTrue(adminInputProcessor.register("AdminTest1", "123", "123456"));
        assertTrue(um.userIfExist("AdminTest1", "123", "AdminUser"));
        try {
            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/AdminUser/" + "AdminTest1" + ".txt")));
        }
        catch (IOException e){
            System.out.println("create file test for NormalCUser fails");
        }
    }

    @Test
    public void loginTest() {
        adminInputProcessor.register("AdminTest2", "123", "123456");
        assertTrue(adminInputProcessor.login("AdminTest2", "123", "123456"));
        try {
            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/AdminUser/" + "AdminTest2" + ".txt")));
        }
        catch (IOException e){
            System.out.println("create file test for NormalCUser fails");
        }
    }

    @Test
    public void uploadMovieTest() {
        adminInputProcessor.uploadMovie("MovieTest1", "abcdfsa", "Comedy");
        assertTrue(mm.IfMovieExist("MovieTest1", "abcdfsa"));
        InstanceMain.getWriteMovie().deleteFile("MovieTest1", "Comedy");
    }
}