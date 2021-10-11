public class Review {
    protected NormalUser reviewer;
    protected Movie movie;
    protected String reviewcontent;

    // Construct a new review content for the specific movie by the reviewer.
    public Review(NormalUser reviewer, Movie movie, String reviewcontent){
        this.reviewer = reviewer;
        this.movie = movie;
        this.reviewcontent = reviewcontent;
    }
}
