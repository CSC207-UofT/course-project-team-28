package Framework.DataAccess;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class ReadGUITest {
    protected final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
    final ReadGUI readGUI = new ReadGUI(str1 + "/src/test/res/GUIText");

    @Test
    public void testGetAllText(){
        try {
            assertEquals("我们的程序", readGUI.getAllText("MANDARIN.txt").get(0));
        } catch (IOException e) {
            System.out.println("testGetAllText failed");
        }
    }
}
