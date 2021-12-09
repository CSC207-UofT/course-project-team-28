package UseCase;

import Entity.Movie;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MovieRankingTest {
    private static MovieRanking mr;


    @BeforeClass
    public static void setUp() {
        ReviewManager rm = new ReviewManager();
        MovieManager mm = new MovieManager(rm);
        assertTrue(mm.addMovie("Apple", "shdjhadshjasfhkasf", "Anime", 1));
        mm.addMovie("Water", "hshauihasjhjk", "Action", 0);
        mm.addMovie("Bananaa", "hshauihasjhjkww", "Action", 2);
        mm.addMovie("Bananab", "hshauihasjhjkw", "Action", 3);
        mr = new MovieRanking(mm.getMovies());
    }

    @Test
    public void getMovieRankTest() {
        ArrayList<Movie> listMov = mr.getMovieRank();
        ArrayList<Movie> newlistMov = new ArrayList<>();
        assertTrue(newlistMov.add(new Movie("Bananab", "hshauihasjhjkw", 3, "Action")));
        newlistMov.add(new Movie("Bananaa", "hshauihasjhjkww", 2, "Action"));
        newlistMov.add(new Movie("Apple", "shdjhadshjasfhkasf", 1, "Anime"));
        newlistMov.add(new Movie("Water", "hshauihasjhjk", 0, "Action"));
        for (int index = 0; index < 4; index ++) {
            assertEquals(listMov.get(index).toString(), newlistMov.get(index).toString());
        }
    }

}