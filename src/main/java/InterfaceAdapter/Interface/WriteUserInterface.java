package InterfaceAdapter.Interface;

import java.util.ArrayList;

public interface WriteUserInterface {
    boolean createFile(String userName, String userPassword, String userType);
    @SuppressWarnings("unused")
    void getObjectFromFile();

    boolean givelikeReadAndWrite(String movieName, String username);
    boolean undoLikeReadAndWrite(String movieName, String username);
    boolean editProfileReadAndWrite(String username, String newUpdate, String writeType);
    void writeFile(String path, ArrayList<Object> lst);
    ArrayList<Object> readFile(String path);
}
