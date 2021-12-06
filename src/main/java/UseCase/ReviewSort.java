package UseCase;

import Entity.Review;

import java.util.*;


/**
 * ReviewSort (UserCase)
 * Sort reviews using the number of coins of the reviews.
 */
public class ReviewSort {

    /**
     * Sort reviews base on number of coins.
     * Reviews with higher number of coins are put at the front of the arraylist
     * @param reviews given lis of reviews
     * @return sorted reviews
     */
    public ArrayList<Review> sortReviews(List<Review> reviews){
        ArrayList<Review> result = new ArrayList<>();
        HashMap<Integer, ArrayList<Review>> hmap = this.putRevsToMap(reviews);
        Set<Integer> temp = hmap.keySet();
        // This is correct
        Integer[] keys = temp.toArray(new Integer[0]);
        Arrays.sort(keys);
        for (int i = keys.length - 1; i >= 0; i--){
            ArrayList<Review> revs = hmap.get(keys[i]);
            result.addAll(revs);
        }
        return result;
    }


    /**
     * stores the arraylist of Reviews into map based on number of coins.
     * @param reviews and arraylist of reviews
     * @return map that stores reviews according to number of coins, the key is number of coins,
     *         and the value is an arraylist of all Review that has the number of coins
     */
    private HashMap<Integer, ArrayList<Review>> putRevsToMap(List<Review> reviews){
        HashMap<Integer, ArrayList<Review>> result = new HashMap<>();
        if (result.isEmpty()){
            return result;
        }
        for (Review rev: reviews) {
            Integer numCoin = rev.getnumCoin();
            if (result.containsKey(numCoin)) {
                result.get(numCoin).add(rev);
            } else {
                ArrayList<Review> lst = new ArrayList<>();
                lst.add(rev);
                result.put(numCoin, lst);
            }
        }
        return result;
    }

}
