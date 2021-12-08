
import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;

import InterfaceAdapter.InstanceMain;
import UseCase.MovieRanking;
import UseCase.MovieManager;
import UseCase.ReviewManager;
import Entity.Movie;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;


import static org.junit.Assert.assertEquals;

public class MovieRankingTest {
    private static MovieRanking mr;
    private static ReviewManager rm;
    private static MovieManager mm;


    @BeforeClass
    public static void setUp() {
        rm = new ReviewManager();
        mm = new MovieManager(rm);
        mm.addMovie("Apple", "shdjhadshjasfhkasf", "Anime", 1);
        mm.addMovie("Water", "hshauihasjhjk", "Action", 0);
        mr = new MovieRanking(mm.getMovies());
    }

    @Test
    public void getMovieRankTest() {
        ArrayList<Movie> listMov = mr.getMovieRank();
        ArrayList<Movie> newlistMov = new ArrayList<Movie>();
        newlistMov.add(new Movie("Apple", "shdjhadshjasfhkasf", 1, "Anime"));
        newlistMov.add(new Movie("Water", "hshauihasjhjk", 0, "Action"));
        for (int index = 0; index < 2; index ++) {
            assertEquals(listMov.get(index).toString(), newlistMov.get(index).toString());
        }
    }

}