package Entity;

import java.util.ArrayList;

public class Review {
    protected String reviewer;
    protected String movie;
    protected String review_content;
    protected int numCoin;
    protected int ID;

    /**
     * Construct a new review content for the specific movie by the reviewer.
      */
    public Review(String reviewer, String movie, String review_content, int numCoin, int ID){
        this.reviewer = reviewer;
        this.movie = movie;
        this.review_content = review_content;
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
                + " coins: " + this.review_content;
    }

    public String getReviewer(){
        return this.reviewer;
    }

    public String getMovie(){
        return this.movie;
    }

    public String getContent(){
        return this.review_content;
    }

    public int getnumCoin(){
        return this.numCoin;
    }

    public void setNumCoin(int i){
        this.numCoin = i;
    }

    public int getID(){
        return this.ID;
    }


    public ArrayList getRevieInfo(){
        ArrayList result = new ArrayList();
        result.add(getReviewer());
        result.add(getMovie());
        result.add(getContent());
        result.add(getnumCoin());
        result.add(getID());
        return result;
    }

}

