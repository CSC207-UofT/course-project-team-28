public class Review {
    protected String reviewer;
    protected String movie;
    protected String review_content;
    protected int ID;

    /**
     * Construct a new review content for the specific movie by the reviewer.
      */
    public Review(String reviewer, String movie, String review_content, int ID){
        this.reviewer = reviewer;
        this.movie = movie;
        this.review_content = review_content;
        this.ID = ID;
    }
}

