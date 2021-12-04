package InterfaceAdapter.Interface;

public interface WriteReviewInterface {
    boolean createFile(String currUserName, String movieName, String revContent, int ID);
    void getObjectFromFile();
    boolean addCoinsToReview(int id, int numCoin);
}
