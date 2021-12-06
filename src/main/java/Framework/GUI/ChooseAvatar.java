package Framework.GUI;

import InterfaceAdapter.InstanceMain;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChooseAvatar extends View{
    private JPanel jPanel;
    private JList jList;
    String[] avatarName = {"water","flower","galaxy", "color","moon","paint"};

    public ChooseAvatar(View p){
        super(p);
        jPanel = new JPanel();
        PlaceThingsOnPanel(jPanel);
    }

    private void PlaceThingsOnPanel(JPanel p){
        p.setLayout(null);
        jList = new JList(avatarName);
        p.setBounds(0,0,500,320);
        jList.setBounds(20,20,100,150);
        jList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                OnSaveAvatarClicked(mouseEvent);
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

    private void OnSaveAvatarClicked(MouseEvent e){
//        String selectedAvatar = "/src/main/res/GUIPic/" + jList.getSelectedValue() + ".jpg";
//        InstanceMain.getNormalCUser().editProfile(selectedAvatar, "picPath");
//        this.getFrame().dispose();
        nextView(new GUIPlaylist(this),false);
    }

    @Override
    protected void UpdateText() {

    }
    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle("Choose your Avatar");
        frame.add(jPanel);
        frame.setSize(500,320);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }
}
