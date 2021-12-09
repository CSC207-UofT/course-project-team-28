package Framework.GUI;

import InterfaceAdapter.InstanceMain;
import InterfaceAdapter.Presenter.TextPresenter;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.HashMap;

public class ChooseAvatar extends View{
    private final JPanel jPanel;
    private JList<Object> jList;
    private final GUIUserRegister guiUserRegister = (GUIUserRegister) previous;
    private final TextPresenter textPresenter = guiUserRegister.getTextPresenter();
    private final HashMap<Object, Object> picInDiffLan;
    private final String[] avatarName = {textPresenter.printText("water"),textPresenter.printText("flower"),textPresenter.printText("galaxy")
            ,textPresenter.printText("color"),textPresenter.printText("moon"),textPresenter.printText("paint")};

    public ChooseAvatar(View p) {
        super(p);
        picInDiffLan = new HashMap<>();
        jPanel = new JPanel();
        PlaceThingsOnPanel(jPanel);
    }

    private void PlaceThingsOnPanel(JPanel p){
        p.setLayout(null);
        String[] avatarNameEng = {"water","flower","galaxy","color","moon","paint"};
        for (String str: avatarNameEng){
            picInDiffLan.put(avatarName[Arrays.asList(avatarNameEng).indexOf(str)], str);
        }
        jList = new JList<>(avatarName);
        p.setBounds(0,0,500,320);
        jList.setBounds(20,20,100,150);
        jList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                OnSaveAvatarClicked();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        });
        p.add(jList);
    }

    private void OnSaveAvatarClicked(){
          String selectedAvatar = "/src/main/res/GUIPic/" + picInDiffLan.get(jList.getSelectedValue()) + ".jpg";
          InstanceMain.getNormalCUser().editProfile(selectedAvatar, "picPath");
          this.getFrame().dispose();
    }

    @Override
    protected void UpdateText() {

    }
    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle(textPresenter.printText("Choose your Avatar"));
        frame.add(jPanel);
        frame.setSize(500,320);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }
}
