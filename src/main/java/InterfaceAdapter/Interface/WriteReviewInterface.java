package InterfaceAdapter.Interface;

import java.util.ArrayList;

@SuppressWarnings("unused")
public interface WriteReviewInterface {
    boolean createFile(String currUserName, String movieName, String revContent, int ID);
    void getObjectFromFile();
    @SuppressWarnings("SameReturnValue")
    boolean addCoinsToReview(int id, int numCoin);
    ArrayList<Object> readFile(String path);
}
