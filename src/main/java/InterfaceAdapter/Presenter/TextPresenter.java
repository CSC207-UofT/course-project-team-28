package InterfaceAdapter.Presenter;

import Framework.DataAccess.ReadGUI;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Called for changing language for text in GUI.
 * Should be called by GUI.
 */
public class TextPresenter extends SecurityManager {
    public ArrayList<String> lstText;
    public ReadGUI readGUI;
    public ArrayList<String> lstIni;

    /**
     * Get all text ready for GUI with required language.
     * @param language language needed for GUI.
     */
    public TextPresenter(String language) throws IOException {
        readGUI = new ReadGUI();
        this.lstText = readGUI.getAllText(language + ".txt");
        this.lstIni = readGUI.getAllText("ENGLISH" + ".txt");
    }

    /**
     * Take in the text needed and returns the text in required language.
     * @param text the text needed
     * @return the text needed in required language
     */
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
