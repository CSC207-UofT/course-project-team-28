import Entity.Review;
import UseCase.MovieManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;


public class MovieManagerTest {
    private final MovieManager mm = new MovieManager();

    @Test
    public void TestAddMovie(){
        Assert.assertTrue(mm.addMovie("go", "www.go.com",null , 0));
    }

    /*@Test
    public void TestAddNewMovie(){
        Assert.assertTrue(mm.addNewMovie("go", "www.go.com"));
    }*/

    @Test
    public void TestGetMovie(){
        mm.addMovie("go", "www.go.com",null , 0);
        Assert.assertEquals("go", mm.getMovie("go").getMoviename());
    }

    @Test
    public void TestIfMovieExist(){
        mm.addMovie("Killer", "www.go.com",null , 0);
        Assert.assertTrue(mm.IfMovieExist("Killer", "www.go.com"));
    }

    @Test
    public void TestAddReviewToMovie(){
        Review review = new Review("me", "Killer", "haha", 0, 1);
        HashMap<Object, Object> map = new HashMap<>();
        mm.addMovie("Killer", "www.go.com",map , 0);
        mm.addReviewToMovie("Killer", review);
        Assert.assertTrue(mm.getMovie("Killer").moviereviews.containsKey("haha"));
    }

    @Test
    public void TestGetMovieProfile() {
        mm.addMovie("go", "www.go.com",null , 0);
        String a = "Core.Movie name: " + "go" + ", \n" + "Link: " + "www.go.com" + ", \n" +
                "# of Likes: " + "0";
        Assert.assertEquals(a, mm.getMovieProfile("go"));
    }

    /*@Test
    public void TestLike_movie() {
        mm.addMovie("go", "www.go.com",null , 0);
        mm.likeMovie("go");
        Assert.assertEquals(mm.getMovie("go").Likes, 1);
    }

    @Test
    public void TestUndolike_movie() {
        mm.addMovie("go", "www.go.com",null , 0);
        mm.likeMovie("go");
        mm.undolikeMovie("go");
        Assert.assertEquals(mm.getMovie("go").Likes, 0);
    }

    @Test
    public void TestToString() {
        mm.addMovie("go", "www.go.com",null , 0);
        Assert.assertEquals(mm.toString(), "go, ");
    }*/



}
