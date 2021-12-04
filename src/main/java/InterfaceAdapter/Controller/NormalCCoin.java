package InterfaceAdapter.Controller;

import InterfaceAdapter.InstanceMain;

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
    public boolean giveCoinToRev(int reviewID) {
        return InstanceMain.getCoinManager().GiveCoinToReview(this.currNormalName, reviewID);

    }


    /**
     * Update the number of coins that the user has after a review.
     * Note: number of coins of that the user +1.
     */
    public boolean EarnCoinAfterWriteRev(){
        return InstanceMain.getCoinManager().EarnCoinAfterReview(this.currNormalName);
    }


}
