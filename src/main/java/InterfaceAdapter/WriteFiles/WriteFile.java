package InterfaceAdapter.WriteFiles;

import java.io.IOException;



/**
 * Interface of all kinds of writeFile classes
 */
public interface WriteFile {

    boolean createFile(String a, String b, String c) throws IOException;
    void getObjectFromFile() throws IOException;
}
