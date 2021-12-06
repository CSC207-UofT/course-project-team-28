package InterfaceAdapter.Presenter;

import Framework.DataAccess.ReadGUI;

import java.io.IOException;
import java.util.ArrayList;

public class TextPresenter {
    public ArrayList<String> lstText;
    public ReadGUI readGUI;
    public ArrayList<String> lstIni;

    public TextPresenter(String language) throws IOException {
        this.lstText = readGUI.getAllText(language);
        this.lstIni = readGUI.getAllText("ENGLISH");
    }

    public String printText(String text){
        for (String str: lstIni){
            if (str.equals(text)){
                return lstText.get(lstIni.indexOf(str));
            }
        }
        return null;
    }
}
