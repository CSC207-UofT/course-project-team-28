package InterfaceAdapter.Presenter;

import Framework.DataAccess.ReadGUI;

import java.io.IOException;
import java.util.ArrayList;

public class TextPresenter extends SecurityManager {
    public ArrayList<String> lstText;
    public ReadGUI readGUI;
    public ArrayList<String> lstIni;

    public TextPresenter(String language) throws IOException {
        readGUI = new ReadGUI();
        this.lstText = readGUI.getAllText(language + ".txt");
        this.lstIni = readGUI.getAllText("ENGLISH" + ".txt");
    }

    public String printText(String text) {
        if (lstText != null & lstIni != null) {
            for (String str : lstIni) {
                if (str.equals(text)) {
                    return lstText.get(lstIni.indexOf(str));
                }
            }
        }
        return text;
    }
}
