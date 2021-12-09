package InterfaceAdapter.Interface;

import java.util.ArrayList;

public interface WriteReviewInterface {
    boolean createFile(String currUserName, String movieName, String revContent, int ID);
    void getObjectFromFile();
    boolean addCoinsToReview(int id, int numCoin);
    ArrayList<Object> readFile(String path);
}
