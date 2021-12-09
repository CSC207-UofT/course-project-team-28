package InterfaceAdapter;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;

public class AdminInputProcessorTest {
    private static AdminInputProcessor adminInputProcessor;
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
    }

    public void d() {
        // delete movie file after creation
        InstanceMain.getWriteMovie().deleteFile("movieName", "category");


        //delete adminuser file after creation
        try {
            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/AdminUser/" + "adminUsername" + ".txt")));
        }
        catch (IOException e){
            System.out.println("create file test for NormalCUser fails");
        }
    }

    @Test
    public void register() {
    }

    @Test
    public void login() {
    }

    @Test
    public void uploadMovie() {
    }
}