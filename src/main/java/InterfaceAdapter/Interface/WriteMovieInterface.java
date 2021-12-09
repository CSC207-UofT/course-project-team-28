package InterfaceAdapter.Interface;

import java.io.IOException;
import java.util.ArrayList;

public interface WriteMovieInterface {
    boolean createFile(String movieName, String movieLink, String category);
    void getObjectFromFile();

    boolean addLikeToFile(String movieName, String state, String category);
    ArrayList<String> readFile(String fn, String folder) throws IOException;
    boolean deleteFile(String movie, String category);
}
