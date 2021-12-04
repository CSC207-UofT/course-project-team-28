package UseCase;

import InterfaceAdapter.Gateway;
import InterfaceAdapter.Controller.NormalController;

/**
 * Represents the entire system of coins
 */
public class CoinManager extends NormalController {
    private final UserManager userManager;
    private final ReviewManager reviewManager;
    private final Gateway gateway = new Gateway();

    /**
     * Creates a UseCase.CoinManager.
     */

    public CoinManager(UserManager um, ReviewManager rm) {
        this.userManager = um;
        this.reviewManager = rm;
    }

    /**
     * Return true if the number of coins that the user has is greater or equal to 1.
     * Given the username and reviewid, update the number of coins that the user has,
     * and update the number of coins that the review earns.
     */
    public boolean GiveCoinToReview(String userName, int reviewid) {
        if (this.userManager.checkCoinBiggerThanOne(userName)) {

            return this.userManager.updateCoin(userName, -1) && this.reviewManager.addCoin(reviewid) && this.gateway.editCoin(userName, reviewid);
        }
        return false;
    }

    /**
     * Update the number of coins that the user has after a review.
     * Note: each new-made review increases the number of coins that the user has by 1.
     */
    public boolean EarnCoinAfterReview(String username) {
        return this.userManager.updateCoin(username, 1);
    }
}
