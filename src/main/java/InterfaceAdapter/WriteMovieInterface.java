package InterfaceAdapter;

public interface WriteMovieInterface {
    boolean createFile(String movieName, String movieLink);
    void getObjectFromFile();

    boolean addLikeToFile(String movieName, String state);
}
