import java.io.IOException;

public interface WriteFile {
    boolean create_file(Object s) throws IOException;
    Object get_object_from_file() throws IOException;
}
