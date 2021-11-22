package UseCase;

/**
 * Represents the entire system of coins
 */
public class CoinManager {
    private final UserManager um;
    private final ReviewManager rm;

    /**
     * Creates a UseCase.CoinManager.
     */

    public CoinManager(UserManager um, ReviewManager rm) {
        this.um = um;
        this.rm = rm;
    }

    /**
     * Return true if the number of coins that the user has is greater or equal to 1.
     * Given the username and reviewid, update the number of coins that the user has,
     * and update the number of coins that the review earns.
     */
    public boolean GiveCoinToReview(String username, int reviewid) {
        if (um.checkCoinBiggerThanOne(username)) {
            um.updateCoin(username, -1);
            rm.addCoin(reviewid);
            return true;
        }
        return false;
    }

    /**
     * Update the number of coins that the user has after a review.
     * Note: each new-made review increases the number of coins that the user has by 1.
     */
    public void EarnCoinAfterReview(String username) {
        um.updateCoin(username, 1);
    }
}
