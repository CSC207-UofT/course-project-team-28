import java.io.IOException;

public interface WriteFile {

    boolean createFile(String a, String b, String c) throws IOException;
    void getObjectFromFile() throws IOException;
}
