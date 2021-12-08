
import UseCase.CoinManager;
import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import UseCase.ReviewManager;
import UseCase.UserManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class CoinManagerTest {
    private static CoinManager cm;
    private static UserManager um;
    private static ReviewManager rm;
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @BeforeClass
    public static void setUp() {
        WriteUser writeUser = new WriteUser(str1 + "/src/test/res/NormalUser", str1 + "/src/test/res/AdminUser");
        WriteReview writeReview = new WriteReview(str1 + "/src/test/res/ReviewCoinManager");
        WriteMovie writeMovie = new WriteMovie(str1 + "/src/test/res/Moviedata/", str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);
        cm = InstanceMain.getCoinManager();
        um = InstanceMain.getUserManager();
        rm = InstanceMain.getReviewManager();
    }

    @Test
    public void EarnCoinAfterReviewTest() {
        Object[] CMinfoList1 = um.getUserInfoList("CoinManager3", "NormalUser");
        cm.EarnCoinAfterReview("CoinManager3");
        Object[] CMinfoList2 = um.getUserInfoList("CoinManager3", "NormalUser");
        int diff = (int)CMinfoList2[5] - (int)CMinfoList1[5];
        assertEquals(diff, 1);
    }

    @Test
    public void GiveCoinToReviewTest() {
        Object[] CMinfoList1 = um.getUserInfoList("CoinManager2", "NormalUser");
        Object[] CMtestrevInfo1 = rm.getRevInfoById(1);
        cm.GiveCoinToReview("CoinManager2", 1);
        Object[] CMinfoList2 = um.getUserInfoList("CoinManager2", "NormalUser");
        assertEquals((int)CMinfoList2[5] - (int)CMinfoList1[5], -1);
        Object[] CMtestrevInfo2 = rm.getRevInfoById(1);
        assertEquals((int)CMtestrevInfo2[3] - (int)CMtestrevInfo1[3], 1);
    }
}