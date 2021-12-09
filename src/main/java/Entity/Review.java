package Entity;

import java.util.Objects;

public class Review {
    protected String reviewer;
    protected String movie;
    protected String reviewContent;
    protected int numCoin;
    protected int ID;

    /**
     * Construct a new review content for the specific movie by the reviewer.
      */
    public Review(String reviewer, String movie, String reviewContent, int numCoin, int ID){
        this.reviewer = reviewer;
        this.movie = movie;
        this.reviewContent = reviewContent;
        this.numCoin = numCoin;
        this.ID = ID;
    }

    /**
     * Return a String of review, containing all of its information.
     * The string will be in the format of:
     * "ID: {ID}, for movie {movie} - {reviewer}  with {numCoin} coins: {review content}."
     */
    public String toString() {
        String idstr = String.valueOf(this.ID);
        return "ID: " + idstr + ", for movie " + this.movie + " - " + this.reviewer + " with " + this.numCoin
                + " coins: " + this.reviewContent;
    }

    /**
     * @return the review writer's name.
     */
    public String getReviewer(){
        return this.reviewer;
    }

    /**
     *
     * @return which movie the review belongs to.
     */
    public String getMovie(){
        return this.movie;
    }

    /**
     *
     * @return the review content.
     */
    public String getContent(){
        return this.reviewContent;
    }

    /**
     *
     * @return the number of coins received by this review.
     */
    public int getNumCoin(){
        return this.numCoin;
    }

    /**
     *
     * @param i set the number of coins as a new number i.
     */
    public void setNumCoin(int i){
        this.numCoin = i;
    }

    /**
     *
     * @return the ID of the review.
     */
    public int getID(){
        return this.ID;
    }

    /**
     *
     * @return the array [reviewer, movie, reviewContent, numCoin, ID]
     */
    public Object[] getReviewInfo(){
        Object[] result = new Object[5];
        result[0] = getReviewer();
        result[1] = getMovie();
        result[2] = getContent();
        result[3] = getNumCoin();
        result[4] = getID();
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()){
            return false;
        }
        Review other = (Review) obj;
        if (this.ID != other.ID){
            return false;
        }
        else if (this.numCoin != other.numCoin){
            return false;
        }
        else if (!Objects.equals(this.reviewer, other.reviewer)){
            return false;
        }
        else if (!Objects.equals(this.movie, other.movie)){
            return false;
        }
        else if (!Objects.equals(this.reviewContent, other.reviewContent)){
            return false;
        }
        return true;
    }

}

