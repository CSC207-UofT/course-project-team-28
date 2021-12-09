package InterfaceAdapter.Interface;

public interface WriteMovieInterface {
    boolean createFile(String movieName, String movieLink, String category);
    void getObjectFromFile();

    boolean addLikeToFile(String movieName, String state, String category);
}
