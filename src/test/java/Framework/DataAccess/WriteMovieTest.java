package Framework.DataAccess;

import Entity.Movie;
import InterfaceAdapter.InstanceMain;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WriteMovieTest {
    private static Object[] infoList;
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    @Before
    public void setUpBeforeTest(){
        infoList = new Object[4];
        infoList[0] = "Peach";
        infoList[1] = "asduip";
        infoList[2] = "0";
        infoList[3] = "Romantic";

        InstanceMain.setClearInstance();
        WriteUser wu = new WriteUser(str1 + "/src/test/res/NormalUser", str1 +
                "/src/test/res/AdminUser");
        WriteReview wr = new WriteReview();
        WriteMovie wm = new WriteMovie(str1 + "/src/test/res/Moviedata/", str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(wu, wm, wr);


    }

    @Test
    public void testCreateFile() {
        try {
            assertTrue(InstanceMain.getWriteMovie().createFile("Peach", "asduip", "Romantic"));
            assertTrue(fileContentTest(infoList, InstanceMain.getWriteMovie().readFile("Peach.txt", "Moviedata")));
            assertTrue(Files.deleteIfExists(Path.of(str1 + "/src/test/res/Moviedata/Romantic/" + "Peach" + ".txt")));
        } catch (IOException e) {
            System.out.println("testCreateFile for write movie class failed");
        }
    }

    @Test
    public void testAddLikeToFile(){
        try {
            assertTrue(InstanceMain.getWriteMovie().addLikeToFile("Apple", "Increase", "Anime"));
            assertEquals("2", InstanceMain.getWriteMovie().readFile("Apple.txt", "Moviedata").get(2));
            assertTrue(InstanceMain.getWriteMovie().addLikeToFile("Apple", "Decrease", "Anime"));
            assertEquals("1", InstanceMain.getWriteMovie().readFile("Apple.txt", "Moviedata").get(2));
        } catch (IOException e) {
            System.out.println("testAddLikeToFile for write movie class failed");
        }
    }

    @Test
    public void testGetObjectFromFile(){
        ArrayList<Movie> ListOfMovie = InstanceMain.getMovieManager().getMovies();
        assertEquals(7, ListOfMovie.size());
        Object[] MovieFile = {"gateway", "Water", "Apple", "Banana", "Team28", "Candy", "Happy Life"};
        for (int i = 0; i < 7; i++){
            assertEquals(MovieFile[i], ListOfMovie.get(i).getMovieName());
        }
    }

    @Test
    public void testDeleteFile(){
        assertTrue(InstanceMain.getWriteMovie().createFile("Peach", "asduip", "Romantic"));
        assertTrue(InstanceMain.getWriteMovie().deleteFile("Peach", "Romantic"));
    }



    private boolean fileContentTest(Object[] ml, ArrayList<String> infoList) {
        for (int i = 0; i < ml.length; i++){
            if (!infoList.get(i).equals(ml[i].toString()))
                return false;
        }
        return true;
    }
}
