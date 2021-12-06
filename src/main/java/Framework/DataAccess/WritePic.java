package Framework.DataAccess;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class WritePic {
    protected static Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
    protected static String filepath = "/src/main/res/GUIPic/";


    public static String getPic(String str){
        return str1 + filepath + str;
    }
}
