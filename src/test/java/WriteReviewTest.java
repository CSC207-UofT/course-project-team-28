import Entity.Review;
import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import UseCase.ReviewManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WriteReviewTest {

    private static Object[] infoList;
    private static ArrayList<Object> fileData;
    private static ReviewManager rm;

    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @BeforeClass
    public static void setUp() {
        infoList = new Object[5];
        infoList[0] = "TestReviewer";
        infoList[1] = "TestMovie";
        infoList[2] = "TestReviewContent";
        infoList[3] = 0;
        infoList[4] = 4;

        InstanceMain.setClearInstance();
        WriteUser writeUser = new WriteUser(str1 + "/src/test/res/NormalUser",
                str1 + "/src/test/res/AdminUser");
        WriteReview writeReview = new WriteReview(str1 + "/src/test/res/ReviewWriteReview");
        WriteMovie writeMovie = new WriteMovie(str1 + "/src/test/res/Moviedata/",
                str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);
        rm = InstanceMain.getReviewManager();

        fileData = InstanceMain.getWriteReview().readFile("/src/test/res/ReviewWriteReview/" + "WriteReview.txt");

        ArrayList<Review> rvList = InstanceMain.getReviewManager().getReviewList();
        assertEquals(3, rvList.size());
    }

    @Test
    public void testCreateFile() {
        try {
            assertTrue(InstanceMain.getWriteReview().createFile("TestReviewer", "TestMovie",
                    "TestReviewContent",4));

            ArrayList<Object> result = InstanceMain.getWriteReview().readFile(str1 +
                    "/src/test/res/ReviewWriteReview/" + "4.txt");
            assertTrue(fileContentTest(infoList, result));

            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/ReviewWriteReview/" +
                    "4.txt")));
        } catch (IOException e) {
            System.out.println("createFile test for Review fails");
        }
    }


    @Test
    public void testAddCoinsToReview(){
        ArrayList<Object> lst1 = InstanceMain.getWriteReview().readFile(str1 + "/src/test/res/ReviewWriteReview/"
                + "1.txt");

        assertTrue(InstanceMain.getWriteReview().addCoinsToReview(1, 1));

        assertTrue(InstanceMain.getWriteReview().addCoinsToReview(1, -1));

        assertEquals(9, Integer.parseInt((String) lst1.get(3)));
    }


    @Test
    public void writeNewReview() {
        assertTrue(rm.writeNewReview("ReviewManager2", "lslfj", "content to be deleted",
                0));
        Object[] expect = {"ReviewManager2", "lslfj", "content to be deleted", 0, 8};
        Object[] actual = rm.getRevInfoById(rm.getCurrMaxRevId());
        for (int i = 0; i <= 3; i++){ //skip reviewId
            assertEquals(expect[i], actual[i]);
        }
//        assertEquals(7, rm.getReviewList().size());
//        ReviewSort rsort = new ReviewSort();
//        rsort.sortReviews(rm.getReviewList());
//        assertEquals(7, rm.getReviewList().size());
        assertTrue(InstanceMain.getWriteReview().deleteReviewFile(rm.getCurrMaxRevId()));

    }


    private boolean fileContentTest(Object[] rl, ArrayList<Object> infoList) {
        for (int i = 0; i < rl.length; i++){
            if (!infoList.get(i).toString().equals(rl[i].toString()))
                return false;
        }
        return true;
    }
}
