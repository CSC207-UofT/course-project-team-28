package Framework.DataAccess;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class ReadGUI {
    protected Path str1 = FileSystems.getDefault().getPath("").toAbsolutePath(); //get absolute path for src folder
    protected File TextFile = new File(str1 + "/src/main/res/GUIText"); //get full path for GUIText folder
    protected FileReader textReader;
    protected BufferedReader getText;

    public ArrayList<String> getAllText(String language) throws IOException {
        String[] lst1 = TextFile.list();
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
