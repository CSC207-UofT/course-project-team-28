package Framework.DataAccess;

import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Give the path of Pictures used by GUI.
 * Should be called by GUI.
 */
public class WritePic {
    protected static Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath();
    protected static String filepath = "/src/main/res/GUIPic/";

    /**
     * Constructor of WritePic for test only.
     * @param filepath the file path of GUIPic for test only.
     */
    public WritePic(String filepath){
        WritePic.filepath = filepath;
    }

    /**
     * Take in the picture name and return the picture path
     * @param str the picture file name
     * @return the full path of the picture
     */
    public static String getPic(String str){
        return str1 + filepath + str;
    }

    /**
     * Take in the half path of a user picture and returns the full path.
     * @param str half path for picture for user use only
     * @return the full path of the picture for user use only
     */
    public static String getPicUser(String str){
        return str1 + str;
    }

}
