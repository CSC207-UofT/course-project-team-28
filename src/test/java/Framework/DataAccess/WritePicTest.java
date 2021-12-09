package Framework.DataAccess;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class WritePicTest {
    protected static Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();

    WritePic writePic = new WritePic("/src/test/res/GUIPic/");

    @Test
    public void testGetPic(){
        assertEquals(str1 + "/src/test/res/GUIPic/" + "1.jpg", writePic.getPic("1.jpg"));
    }

    @Test
    public void testGetUserPic(){
        assertEquals(str1 + "/src/test/res/GUIPic/moon.jpg",
                writePic.getPicUser("/src/test/res/GUIPic/moon.jpg"));
    }
}
