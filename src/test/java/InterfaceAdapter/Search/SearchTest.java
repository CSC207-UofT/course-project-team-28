package InterfaceAdapter.Search;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


public class SearchTest {
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();


    @BeforeClass
    public static void setUp() {
        //InstanceMain setUp
        InstanceMain.setClearInstance();
        WriteUser wu = new WriteUser(str1 + "/src/test/res/NormalUser", str1 +
                "/src/test/res/AdminUser");
        WriteReview wr = new WriteReview();
        WriteMovie wm = new WriteMovie(str1 + "/src/test/res/Moviedata/", str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(wu, wm, wr);
    }

    @Test
    public void TestSuggestionSearch() {
        List<String> words = Arrays.asList("Apple", "Akira" );

        Assert.assertEquals(words, Search.suggestionSearch("a"));
    }
}
