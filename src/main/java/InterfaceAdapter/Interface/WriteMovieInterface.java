package InterfaceAdapter.Interface;

import java.util.ArrayList;

public interface WriteMovieInterface {
    boolean createFile(String movieName, String movieLink, String category);
    @SuppressWarnings("unused")
    void getObjectFromFile();

    boolean addLikeToFile(String movieName, String state, String category);
    ArrayList<String> readFile(String fn, String folder);
    boolean deleteFile(String movie, String category);
}
