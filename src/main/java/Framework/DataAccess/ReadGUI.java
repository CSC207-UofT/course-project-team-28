package Framework.DataAccess;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Read the files for text shown in GUI.
 * Should be called by Presenter.
 */
public class ReadGUI {
    protected Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
    protected File TextFile = new File(str1 + "/src/main/res/GUIText"); //get full path for GUIText folder
    protected FileReader textReader;
    protected BufferedReader getText;

    /**
     * Constructor of ReadGUI
     */
    public ReadGUI(){}

    /**
     * Constructor of ReadGUI for test only
     * @param textFile the file path of the GUI text folder for test only
     */
    public ReadGUI(String textFile){
        this.TextFile = new File(textFile);
    }

    /**
     * Read the file named with language and put all text inside into an Arraylist.
     * @param language the language needed for GUI
     * @return an ArrayList containing all the text needed for GUI in required language
     */
    @SuppressWarnings("Duplicates")
    public ArrayList<String> getAllText(String language) throws IOException {
        String[] lst1 = TextFile.list();
        assert lst1 != null;
        for (String str: lst1){
            if (str.equals(language)){
                textReader = new FileReader(str1 + "/src/main/res/GUIText/" + language);
            }
        }
        getText = new BufferedReader(textReader);

        ArrayList<String> lst = new ArrayList<>();
        String line = getText.readLine();
        while (line != null) {
            lst.add(line);
            line = getText.readLine();
        }
        getText.close();

        return lst;
    }
}
