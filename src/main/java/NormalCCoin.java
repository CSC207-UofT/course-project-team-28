import java.io.IOException;

public class NormalCCoin extends NormalController{

    public NormalCCoin() {
        super();
    }


    /**
     * !!!Should only be called with a valid reviewId
     * Return true if the number of coins that the user has is greater or equal to 1.
     * Given the username and reviewid, update the number of coins that the user has,
     * and update the number of coins that the review earns.
     */
    public boolean giveCoinToRev(int reviewId, WriteReview wr, WriteUser wu) throws IOException {
        return coinMana.GiveCoinToReview(currNuname, reviewId) &&
                wu.editProfileReadAndWrite("-1", currNuname, "coin") &&
                wr.addCoinsToReview(reviewId, 1);
    }


    /**
     * Update the number of coins that the user has after a review.
     * Note: number of coins of that the user +1.
     */
    public void EarnCoinAfterWriteRev(WriteUser wu){
        coinMana.EarnCoinAfterReview(currNuname);
        wu.editProfileReadAndWrite("1", currNuname, "coin");
    }


}
