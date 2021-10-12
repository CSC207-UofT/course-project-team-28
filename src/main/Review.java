public class Review {
    protected String reviewer;
    protected String movie;
    protected String review_content;

    /**
     * Construct a new review content for the specific movie by the reviewer.
      */
    public Review(String reviewer, String movie, String review_content){
        this.reviewer = reviewer;
        this.movie = movie;
        this.review_content = review_content;
    }
}
