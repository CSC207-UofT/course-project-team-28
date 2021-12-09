package InterfaceAdapter.Controller;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NormalCCoinTest {
    private static NormalCCoin ncc;
    private static NormalCUser ncu;
    private static NormalCMovie ncm;
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @BeforeClass
    public static void setUp() {
        InstanceMain.setClearInstance();
        WriteUser writeUser = new WriteUser(str1 + "/src/test/resNormalControllers/NormalUser", str1 + "/src/test/resNormalControllers/AdminUser");
        WriteReview writeReview = new WriteReview(str1 + "/src/test/resNormalControllers/Review");
        WriteMovie writeMovie = new WriteMovie(str1 + "/src/test/resNormalControllers/Moviedata/", str1 + "/src/test/resNormalControllers/");
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);
        ncc = InstanceMain.getNormalCCoin();
        ncu = InstanceMain.getNormalCUser();
        ncm = InstanceMain.getNormalCMovie();
        // login
        NormalCUser ncu = InstanceMain.getNormalCUser();
        assertTrue(ncu.login("CoinManager", "12"));
        assertEquals("CoinManager", ncu.currNormalName);
    }

    @Test
    public void giveCoinToRev() {
        int preUser = (int) ncu.profilePage("CoinManager")[5];
        int preRev = (int) ncm.getReviewInfo(2)[3];
        ncc.giveCoinToRev(2);
        int aftUser = (int) ncu.profilePage("CoinManager")[5];
        int aftRev = (int) ncm.getReviewInfo(2)[3];
        assertEquals(1, aftRev-preRev);
        assertEquals(-1, aftUser-preUser);
    }

    @Test
    public void earnCoinAfterWriteRev() {
        int preUser = (int) ncu.profilePage("CoinManager")[5];
        ncc.earnCoinAfterWriteRev();
        int aftUser = (int) ncu.profilePage("CoinManager")[5];
        assertEquals(1, aftUser-preUser);
    }
}