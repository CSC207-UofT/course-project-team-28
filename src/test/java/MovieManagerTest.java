import java.util.*;


import Core.Review;
import UseCase.MovieManager;
import org.junit.Assert;
import org.junit.Test;


public class MovieManagerTest {
    private final MovieManager mm = new MovieManager();

    @Test
    public void TestAddMovie(){
        Assert.assertTrue(mm.add_movie("go", "www.go.com",null , 0));
    }

    @Test
    public void TestGetMovie(){
        mm.add_movie("go", "www.go.com",null , 0);
        Assert.assertEquals("go", mm.get_movie("go").getMoviename());
    }

    @Test
    public void TestIfMovieExist(){
        mm.add_movie("Killer", "www.go.com",null , 0);

        Assert.assertTrue(mm.IfMovieExist("Killer", "www.go.com"));
    }

    @Test
    public void TestAdd_review_to_movie(){
        Review review = new Review("me", "Killer", "haha", 0, 1);
        HashMap<Object, Object> map = new HashMap<>();
        mm.add_movie("Killer", "www.go.com",map , 0);
        mm.add_review_to_movie("Killer", review);
        Assert.assertTrue(mm.get_movie("Killer").moviereviews.containsKey("haha"));
    }

    @Test
    public void TestGet_movieprofile() {
        mm.add_movie("go", "www.go.com",null , 0);
        String a = "Core.Movie name: " + "go" + ", \n" + "Link: " + "www.go.com" + ", \n" +
                "# of Likes: " + "0";
        Assert.assertEquals(a, mm.get_movieprofile("go"));
    }

    @Test
    public void TestLike_movie() {
        mm.add_movie("go", "www.go.com",null , 0);
        mm.like_movie("go");
        Assert.assertEquals(mm.get_movie("go").Likes, 1);
    }

    @Test
    public void TestUndolike_movie() {
        mm.add_movie("go", "www.go.com",null , 0);
        mm.like_movie("go");
        mm.undolike_movie("go");
        Assert.assertEquals(mm.get_movie("go").Likes, 0);
    }

    @Test
    public void TestToString() {
        mm.add_movie("go", "www.go.com",null , 0);
        Assert.assertEquals(mm.toString(), "go, ");
    }



}
