import Entity.Review;
import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import UseCase.ReviewManager;
import org.junit.BeforeClass;
import org.junit.Test;
import UseCase.ReviewSort;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class WriteReviewTest {
    private static ReviewManager rm;
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @BeforeClass
    public static void setUp() {
        WriteUser writeUser = new WriteUser(str1 + "/src/test/res/NormalUser", str1 + "/src/test/res/AdminUser");
        WriteReview writeReview = new WriteReview(str1 + "/src/test/res/Review");
        WriteMovie writeMovie = new WriteMovie(str1 + "/src/test/res/Moviedata/", str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);
        rm = InstanceMain.getReviewManager();
    }

    @Test
    public void writeNewReview() {
        assertTrue(rm.writeNewReview("ReviewManager2", "lslfj", "content to be deleted", 0));
        assertEquals(8, rm.getCurrMaxRevId());
        Object[] expect = {"ReviewManager2", "lslfj", "content to be deleted", 0, 8};
        Object[] actual = rm.getRevInfoById(8);
        for (int i = 0; i <= 4; i++){
            assertEquals(expect[i], actual[i]);
        }
        assertEquals(7, rm.getReviewList().size());
        ReviewSort rsort = new ReviewSort();
        rsort.sortReviews(rm.getReviewList());
        assertEquals(7, rm.getReviewList().size());
        InstanceMain.getWriteReview().deleteReviewFile(8);

    }
}
