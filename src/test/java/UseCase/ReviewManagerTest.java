package UseCase;

import Entity.Review;
import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class ReviewManagerTest {
    private ReviewManager rm;
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @Before
    public void setUp() {
        WriteUser writeUser = new WriteUser(str1 + "/src/test/res/NormalUser", str1 + "/src/test/res/AdminUser");
        WriteReview writeReview = new WriteReview(str1 + "/src/test/res/Review");
        WriteMovie writeMovie = new WriteMovie(str1 + "/src/test/res/Moviedata/", str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);
        rm = InstanceMain.getReviewManager();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listRevsOfMovie() {
    }

    @Test
    public void writeReview() {
        Review rev1 = rm.getReviewList().get(1);
        assertEquals(1, rev1.getID());
        assertEquals(0, rev1.getnumCoin());
        assertEquals("Apple", rev1.getMovie());
        assertEquals("ReviewManager2", rev1.getReviewer());
        assertEquals("hahahahha", rev1.getContent());
        Review rev1temp = new Review("ReviewManager2", "Apple", "hahahahha", 10,
                1);
    }

    @Test
    public void test(){
        rm.writeReview("ReviewManager2", "Apple", "test", 10,
                5);
    }

    @Test
    public void writeNewReview() {
        rm.writeNewReview("ReviewManager1", "Banana", "content content", 0);

    }

    @Test
    public void getReviewID() {
    }

    @Test
    public void addCoin() {
    }

    @Test
    public void getRevInfoById() {
    }
}