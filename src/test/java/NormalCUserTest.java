import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.Controller.NormalCUser;
import InterfaceAdapter.InstanceMain;
import junit.framework.TestCase;
import org.junit.Before;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class NormalCUserTest extends TestCase {
    private NormalCUser ncu;
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();


    @Before
    public void setUp() {
        WriteUser writeUser = new WriteUser(str1 + "/src/test/res/NormalUser", str1 + "/src/test/res/AdminUser");
        //TODO
        WriteReview writeReview = new WriteReview();
        WriteMovie writeMovie = new WriteMovie(str1 + "/src/main/res/Moviedata/", str1 + "/src/main/res/");
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);
        ncu = InstanceMain.getNormalCUser();
    }

    public void testRegister() {
    }

    public void testLogin() {
    }

    public void testProfilePage() {
    }

    public void testEditProfile() {
    }
}