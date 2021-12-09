package InterfaceAdapter.Controller;

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

public class NormalCMovieTest {
    private static NormalCMovie ncm;
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @BeforeClass
    public static void setUp() {
        InstanceMain.setClearInstance();
        WriteUser writeUser = new WriteUser(str1 + "/src/test/resNormalControllers/NormalUser", str1 + "/src/test/resNormalControllers/AdminUser");
        WriteReview writeReview = new WriteReview(str1 + "/src/test/resNormalControllers/Review");
        WriteMovie writeMovie = new WriteMovie(str1 + "/src/test/resNormalControllers/Moviedata/", str1 + "/src/test/resNormalControllers/");
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);
        NormalCUser ncu = InstanceMain.getNormalCUser();
        ncm = InstanceMain.getNormalCMovie();

        assertTrue(ncu.login("NormalController", "123yu"));
        assertEquals("NormalController", ncu.currNormalName);
    }

//    @Test
//    public void ifMovieExist() {
//    }

    @Test
    public void movieProfile() {
        Object[] actual = ncm.movieProfile("Happy Life");
        Object[] expect = {"Happy Life", "bdudhjbsdakjushd-link", "Romantic", 3};
        for (int i = 0; i <= 3; i++) {
            assertEquals(expect[i], actual[i]);
        }
    }

    @Test
    public void movieReviews() {
        ArrayList<Object[]> actual = ncm.movieReviews("Apple");
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
    }

    @Test
    public void getReviewInfo() {
        Object[] expect = {"ReviewManager1", "Banana", "content3", 10, 3};
        Object[] actual = ncm.getReviewInfo(3);
        for (int i = 0; i <= 4; i++){
            if (i != 3){ //skip numCoin as it is modified
                assertEquals(expect[i], actual[i]);
            }
        }
    }

    @Test
    public void likeLikedMovie() {
        assertFalse(ncm.likeMovie("Team28"));
    }

    @Test
    public void likeMovie() {
        assertFalse(ncm.likeMovie("Team28"));
    }

    @Test
    public void emptyPlaylist() {
    }

    @Test
    public void undoLike() {
    }

    @Test
    public void rankMovie() {
    }

    @Test
    public void writeReview() {
    }
}