package Entity;

/**
 * Represent Movies in Entity.
 */
public class Movie {
    protected String movieName;
    protected String movieLink;
    public String category;
    public int Likes;


    /**
     * Constructor for movie Class
     * @param movieName name of the movie
     * @param movieLink link of the movie
     * @param i number of likes of the movie
     * @param category category of the movie
     */
    public Movie(String movieName, String movieLink, int i, String category) {
        this.movieName = movieName;
        this.movieLink = movieLink;
        this.Likes = i;
        this.category = category;

    }

    /**
     * Getter Methods for Movie Class.
     */
    public String toString() {
        return "Movie name: " + this.movieName + ", \n" + "Link: " + this.movieLink + ", \n" + "Category: " + this.category
                + ", \n" + "# of Likes: " + this.Likes;
    }


    /**
     * Getter method for the name of the movie.
     * @return returns the name of the movie.
     */
    public String getMovieName(){
        return this.movieName;
    }

    /**
     * Getter method for the link of the movie.
     * @return returns the link of the movie.
     */
    public String getLink(){
        return this.movieLink;
    }

    /**
     * Getter method for the category of the movie.
     * @return returns the category of the movie.
     */
    public String getCategory() {return this.category; }

    /**
     * Getter method for the number of likes of the movie.
     * @return returns the number of likes of the movie.
     */
    public int getLikes() {return this.Likes; }

    /**
     * Add a like to a movie.
     */
    public void AddLike(){
        this.Likes += 1;
    }

    /**
     * Retrieve a like from a movie.
     */
    public void UndoLike(){
        this.Likes -= 1;
    }

}
