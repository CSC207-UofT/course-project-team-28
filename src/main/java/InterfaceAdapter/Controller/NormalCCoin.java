package InterfaceAdapter.Controller;

import InterfaceAdapter.InstanceMain;

/**
 * NormalCCoin (InterfaceAdapter)
 * Controller responsible for coin-related operations.
 */

public class NormalCCoin extends NormalController{

    public NormalCCoin() {
        super();
    }


    /**
     * !!!Should only be called with a valid reviewId
     * Given the username and reviewId, update the number of coins that the user has,
     * and update the number of coins that the review earns.
     * @param reviewID the id of the review that receives the coin.
     * @return true if the number of coins that the user has is greater or equal to 1.
     */
    public boolean giveCoinToRev(int reviewID) {
        return InstanceMain.getCoinManager().GiveCoinToReview(this.currNormalName, reviewID);

    }

    /**
     * Update the number of coins that the user has after a review.
     * Note: number of coins of that the user +1.
     * @return ture iff thr change has been successfully made.
     */
    public boolean EarnCoinAfterWriteRev(){
        return InstanceMain.getCoinManager().EarnCoinAfterReview(this.currNormalName);
    }

}
