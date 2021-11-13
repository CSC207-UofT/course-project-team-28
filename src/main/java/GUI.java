import javax.swing.*;

public class GUI {

    public static void main(String[] args){
//        GUIMain guimain = new GUIMain();
//        GUIAdminUserReg guiaureg = new GUIAdminUserReg();
//        GUINormalUserReg guinureg = new GUINormalUserReg();
//        GUINormalUserLogin guinulogin = new GUINormalUserLogin();
//        GUIAdminUserLogin guiaulogin = new GUIAdminUserLogin();
        View gui = new GUIMain();
        gui.getFrame().setVisible(true);


    }
}
