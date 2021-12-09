package InterfaceAdapter;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.junit.Assert.assertNotNull;

public class InstanceMainTest {
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @BeforeClass
    public static void setUpBeforeClass() {
        InstanceMain.setClearInstance();
        WriteUser wu = new WriteUser(str1 + "/src/test/res/NormalUser", str1 + "/src/test/res/AdminUser");
        WriteReview wr = new WriteReview();
        WriteMovie wm = new WriteMovie(str1 + "/src/main/res/MovieData/", str1 + "/src/main/res/");
        InstanceMain.setWriteFileClass(wu, wm, wr);
    }

    @Test
    public void getAdminInputProcessor() {
        assertNotNull(InstanceMain.getAdminInputProcessor());
    }

    @Test
    public void getWriteUser() {
        assertNotNull(InstanceMain.getWriteUser());
    }

    @Test
    public void getWriteMovie() {
        assertNotNull(InstanceMain.getWriteMovie());
    }

    @Test
    public void getWriteReview() {
        assertNotNull(InstanceMain.getWriteReview());
    }

    @Test
    public void getNormalCUser() {
        assertNotNull(InstanceMain.getNormalCUser());
    }

    @Test
    public void getNormalCCoin() {
        assertNotNull(InstanceMain.getNormalCCoin());
    }

    @Test
    public void getNormalCMovie() {
        assertNotNull(InstanceMain.getNormalCMovie());
    }

    @Test
    public void getUserManager() {
        assertNotNull(InstanceMain.getUserManager());
    }

    @Test
    public void getMovieManager() {
        assertNotNull(InstanceMain.getMovieManager());
    }

    @Test
    public void getReviewManager() {
        assertNotNull(InstanceMain.getReviewManager());
    }

    @Test
    public void getCoinManager() {
        assertNotNull(InstanceMain.getCoinManager());
    }
}