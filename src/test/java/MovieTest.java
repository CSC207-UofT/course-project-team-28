import java.io.IOException;
import java.util.*;


import Core.Movie;
import Core.Review;
import UseCase.MovieManager;
import org.junit.Assert;
import org.junit.Test;

public class MovieTest {
    private final MovieManager mm = new MovieManager();

    public MovieTest() throws IOException {
    }

    @Test
    public void TestMovieExist() throws IOException {
        HashMap<Object, Object> map = new HashMap<>();
        Boolean b = this.mm.add_movie("Killer", "abc123", map, 3);
        Assert.assertTrue(b);
    }

    @Test
    public void TestMovieAddReview() throws IOException {
        HashMap<Object, Object> map = new HashMap<>();
        Movie m = new Movie("Killer", "1235", map, 2);
        Review r = new Review("Ella", "Killer", "123", 2, 1);
        m.AddReview(r);
        HashMap<Object, Object> ma = new HashMap<>();
        ma.put("123", "Ella");
        Assert.assertEquals(m.moviereviews, ma);
    }

    @Test
    public void TestMovieAddLike() throws IOException {
        HashMap<Object, Object> map = new HashMap<>();
        Movie m = new Movie("Killer", "1235", map, 2);
        m.AddLike();
        m.AddLike();
        Assert.assertEquals(m.Likes, 4);
    }

    @Test
    public void TestMovieUndoLike() throws IOException {
        HashMap<Object, Object> map = new HashMap<>();
        Movie m = new Movie("Killer", "1235", map, 2);
        m.UndoLike();
        m.UndoLike();
        Assert.assertEquals(m.Likes, 0);
    }
}
