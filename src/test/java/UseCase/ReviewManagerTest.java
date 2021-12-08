package UseCase;

import Entity.Review;
import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ReviewManagerTest {
    private static ReviewManager rm;
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @BeforeClass
    public static void setUp() {
        WriteUser writeUser = new WriteUser(str1 + "/src/test/res/NormalUser",
                str1 + "/src/test/res/AdminUser");
        WriteReview writeReview = new WriteReview(str1 + "/src/test/res/Review");
        WriteMovie writeMovie = new WriteMovie(str1 + "/src/test/res/Moviedata/",
                str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);
        rm = InstanceMain.getReviewManager();
    }

    private boolean twoReviewGetInfoArrayEqual(Object[] rev1, Object[] rev2){
        boolean result = true;
        for (int i = 0; i <= 4; i++){
            result = result && rev1[i].equals(rev2[i]);
        }
        return result;
    }

    @Test
    public void listRevsOfMovieWithNoReview() {
        assertTrue(rm.listRevsOfMovie("Water").isEmpty());
    }

    @Test
    public void listRevsOfMovieWithOneReview() {
        ArrayList<Object[]> actualr = rm.listRevsOfMovie("Banana");
        assertEquals(1, actualr.size());
        Object[] expect = {"ReviewManager1", "Banana", "content3", 10, 3};
        Object[] actual = actualr.get(0);
        for (int i = 0; i <= 4; i++){
            assertEquals(expect[i], actual[i]);
        }
    }

    @Test
    public void listRevsOfMovie() {
        ArrayList<Object[]> actual = rm.listRevsOfMovie("Apple");
        assertEquals(4, actual.size());
        Object[] rev0 = {"ReviewManager2", "Apple", "content7", 8, 7};
        Object[] rev1 = {"ReviewManager2", "Apple", "hahahahha", 7, 1};
        Object[] rev2 = {"ReviewManager3", "Apple", "content4", 3, 4};
        Object[] rev3 = {"ReviewManager2", "Apple", "content6", 3, 6};
        ArrayList<Object[]> expect = new ArrayList<>();
        expect.add(rev0);
        expect.add(rev1);
        expect.add(rev2);
        expect.add(rev3);
        for (int i = 0; i <= 3; i++){
            for (int j = 0; j <= 4; j++) {
                assertEquals(expect.get(i)[j], actual.get(i)[j]);
            }
        }
        assertEquals(6, rm.getReviewList().size());
    }

    @Test
    public void writeReview() {
        Review rev1 = rm.getReviewList().get(0);
        assertEquals(1, rev1.getID());
        assertEquals(7, rev1.getnumCoin());
        assertEquals("Apple", rev1.getMovie());
        assertEquals("ReviewManager2", rev1.getReviewer());
        assertEquals("hahahahha", rev1.getContent());
        Review rev6 = rm.getReviewList().get(4);
        assertEquals(6, rev6.getID());
        assertEquals(7, rm.getCurrMaxRevId());
    }


    @Test
//    TODO
    public void addCoin() {
        int numCoinExp = (int) rm.getRevInfoById(3)[3] + 1;
        assertTrue(rm.addCoin(3, "ReviewManager3"));
        int act = (int) rm.getRevInfoById(3)[3];
        assertEquals(numCoinExp, act);
    }

    @Test
    public void getRevInfoById() {
        assertNull(rm.getRevInfoById(908));
    }

    @Test
    public void updateMovieToRevsKey() {
        rm.updateMovieToRevsKey("testcsafho");
        assertTrue(rm.listRevsOfMovie("testcsafho").isEmpty());
    }
}