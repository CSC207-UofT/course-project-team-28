package InterfaceAdapter;

public abstract class DataAccessInterface {
    protected abstract boolean createFile(String a, String b, String c, int d);
    protected abstract void getObjectFromFile();

    //WriteUser
    protected boolean givelikeReadAndWrite(String movieName, String username){
        return false;
    }
    protected boolean undoLikeReadAndWrite(String movieName, String username) {
        return false;
    }
    protected boolean editProfileReadAndWrite(String username, String newUpdate, String writeType) {
        return false;
    }


    //WriteMovie
    protected void addReviewToFile(String userName, String movieName, String reviewContent) {}
    protected boolean addLikeToFile(String movieName, String state) {
        return false;
    }

    //WriteReview
    protected boolean addCoinsToReview(int id, int numCoin) {
        return false;
    }
}
