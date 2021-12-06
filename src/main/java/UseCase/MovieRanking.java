package UseCase;

import Entity.Movie;
import InterfaceAdapter.InstanceMain;
//import UseCase.MovieManager;

import java.util.ArrayList;

public class MovieRanking {
    private ArrayList<Movie> MovieRank;
    private ArrayList<Movie> Movies;
//    private MovieManager movieManager;

    public MovieRanking() {
        this.MovieRank = new ArrayList<>();
        this.Movies = InstanceMain.getMovieManager().getMovies();
    }

    /**
     * Return an ArrayList of ArrayList consisting movies
     * and the number of likes they receive.
     * (Helper method)
     */
    private ArrayList<ArrayList<Object>> LMovieLikes() {
        ArrayList<ArrayList<Object>> listMovieLikes = new ArrayList<>();
        for (Movie m: Movies) {
            ArrayList<Object> currentMovLik = new ArrayList<>();
            currentMovLik.add(m);
            currentMovLik.add(m.getLikes());
            listMovieLikes.add(currentMovLik);
        }
        return listMovieLikes;
    }

    /**
     * Return an ArrayList of ArrayList consisting movies and likes
     * with the most popular movie updated
     * when given an ArrayList of ArrayList consisting movies
     * and number of likes they receive.
     * (Helper method)
     */
    private ArrayList<ArrayList<Object>> updateLMovLikOnce(ArrayList<ArrayList<Object>> LMovLik) {
        ArrayList<ArrayList<Object>> newLMovLik = new ArrayList<>();
        int currMaxLike = 0;
        for (ArrayList<Object> each: LMovLik) {
            if (each[1] > currMaxLike) {
                currMaxLike = each[1];
            }
        }
        for (ArrayList<Object> object: LMovLik) {
            if (object[1] == currMaxLike) {
                newLMovLik.add(object);
            }
        }
        return newLMovLik;
    }

    /**
     * Update the MovieRank.
     * (Helper method)
     */
    private void orderMovieWLikes() {
        ArrayList<ArrayList<Object>> listMovLik = LMovieLikes();
        ArrayList<ArrayList<Object>> listMovLikCopy = (ArrayList<ArrayList<Object>>) listMovLik.clone();
        while (! listMovLik.equals(new ArrayList<>())) {
            ArrayList<ArrayList<Object>> newLMovLik = updateLMovLikOnce(listMovLik);
            listMovLik.remove(newLMovLik.get(-1));
        }
        for (ArrayList<Object> each: listMovLikCopy) {
            MovieRank.add((Movie) each.get(0));
        }
    }

    /**
     * Return an ArrayList of Movie, the first one is the most popular movie.
     */
    public ArrayList<Movie> getMovieRank() {
        this.orderMovieWLikes();
        return MovieRank;
    }
}
