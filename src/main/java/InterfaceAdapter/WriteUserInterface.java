package InterfaceAdapter;

public interface WriteUserInterface {
    boolean createFile(String userName, String userPassword, String userType);
    void getObjectFromFile();

    boolean givelikeReadAndWrite(String movieName, String username);
    boolean undoLikeReadAndWrite(String movieName, String username);
    boolean editProfileReadAndWrite(String username, String newUpdate, String writeType);
}
