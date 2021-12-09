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

@SuppressWarnings("ALL")
public class TrieTest {
    private static final Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();


    @BeforeClass
    public static void setUp() {
        InstanceMain.setClearInstance();
        WriteUser writeUser = new WriteUser(str1 + "/src/test/res/NormalUser", str1 + "/src/test/res/AdminUser");
        WriteReview writeReview = new WriteReview(str1 + "/src/test/res/ReviewCoinManager");
        WriteMovie writeMovie = new WriteMovie(str1 + "/src/test/res/Moviedata/", str1 + "/src/test/res/");
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);



    }
    @Test
    public void TestTrieNode() {
        Trie.TrieNode a = new Trie.TrieNode('a');
        Assert.assertEquals('a', a.c);

    }
    @Test
    public void TestNode() {
        Trie.TrieNode a = new Trie.TrieNode();
        a.c = 'a';
        Assert.assertEquals('a', a.c);


    }
    @Test
    public void TestInsert() {
        List<String> words = InstanceMain.getMovieManager().getMovieNames();
        Trie trie = new Trie(words);
        List<String> a = Arrays.asList("Apple", "Akira" );
        Assert.assertEquals(a, trie.suggest("a"));



    }

    @Test
    public void TestTrie() {
        List<String> words = InstanceMain.getMovieManager().getMovieNames();
        Trie trie = new Trie(words);
        List<String> a = Arrays.asList("Apple", "Akira" );
        Assert.assertEquals(a, trie.suggest("a"));

    }

    @Test
    public void TestSuggestHelper() {
        List<String> words = Arrays.asList("Apple", "Akira" );
        Trie trie = new Trie(words);

        Assert.assertEquals(words, trie.suggest("a"));
    }


    @Test
    public void TestSuggestionSearch() {
        List<String> words = Arrays.asList("Apple", "Akira" );
        Trie trie = new Trie(words);

        Assert.assertEquals(words, trie.suggest("a"));
    }
}
