import java.io.IOException;

public interface WriteFile {

    boolean create_file(String a, String b, String c) throws IOException;
    void get_object_from_file() throws IOException;
}
