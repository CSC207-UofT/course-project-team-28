public class NormalCCoin extends NormalController{

    public NormalCCoin() {
        super();
    }


    /**
     * Return true if the number of coins that the user has is greater or equal to 1.
     * Given the username and reviewid, update the number of coins that the user has,
     * and update the number of coins that the review earns.
     */
    public boolean giveCoinToRev(int reviewId){
        return coinMana.GiveCoinToReview(currNuname, reviewId);
    }


    /**
     * Update the number of coins that the user has after a review.
     * Note: number of coins of that the user +1.
     */
    public void EarnCoinAfterWriteRev(){
        coinMana.EarnCoinAfterReview(currNuname);
    }


}
