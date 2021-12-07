package InterfaceAdapter.Controller;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class NormalCUserTest {
    private NormalCUser ncu;
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @Before
    public void setUp(){
        WriteUser writeUser = new WriteUser(str1 + "/src/test/res/NormalUser", str1 + "/src/test/res/AdminUser");
        WriteReview writeReview = new WriteReview(str1 + "/src/test/res/Review");
        WriteMovie writeMovie = new WriteMovie(str1 + "/src/test/res/Moviedata/", str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);
        ncu = InstanceMain.getNormalCUser();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void register() {
    }

    @Test
    public void login() {
    }

    @Test
    public void profilePage() {
    }

    @Test
    public void editProfile() {
    }
}