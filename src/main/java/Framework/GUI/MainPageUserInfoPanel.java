package Framework.GUI;

import Framework.Presenter.PicPresenter;
import InterfaceAdapter.InstanceMain;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPageUserInfoPanel extends View{
    /*
    Construct User Information Panel on Main Page
     */
    public JLabel contactInfoLabel;
    private JLabel coinLabel;
    private final JPanel panel1;
    Font font1 = new Font("SansSerif", Font.BOLD, 30);

    public MainPageUserInfoPanel(View p){
        super(p);
        panel1 = new JPanel();
        PlaceThingsOnP1(panel1);
    }

    /*
    Place all components on the User Info Panel.
     */
    private void PlaceThingsOnP1(JPanel p1){
        p1.setLayout(null);
        Border b = BorderFactory.createLineBorder(Color.BLACK, 1);
        p1.setBorder(b);
        p1.setBounds(20,20, 380,820);
        JLabel usernameLabel = new JLabel((String) InstanceMain.getNormalCUser().profilePage(userName)[0]);
        usernameLabel.setFont(font1);
        usernameLabel.setBounds(20,20,700,200);
        coinLabel = new JLabel("coins: " + InstanceMain.getNormalCUser().profilePage(userName)[5]);
        coinLabel.setBounds(20, 60, 300, 200);
        contactInfoLabel = new JLabel();
        contactInfoLabel.setText((String)InstanceMain.getNormalCUser().profilePage(userName)[2]);
        contactInfoLabel.setBounds(20, 90, 700, 200);
        ImageIcon icon = new ImageIcon(PicPresenter.getPic("5.jpg"));
        JLabel i = new JLabel("Image and Text", icon, JLabel.CENTER);
        i.setBounds(20, 200, 300, 200);
        JTextArea description = new JTextArea((String)InstanceMain.getNormalCUser().profilePage(userName)[3]);
        description.setEditable(false);
        description.setBounds(40, 480, 300, 200);
        JButton EditContactInfo = new JButton("Edit");
        EditContactInfo.setBounds(280,180,60,20);
        EditContactInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OnButtonClick(actionEvent);
            }
        });
        UpdateText();
        p1.add(usernameLabel);
        p1.add(coinLabel);
        p1.add(contactInfoLabel);
        p1.add(EditContactInfo);
        p1.add(description);
        p1.add(i);
    }
    private void OnButtonClick(ActionEvent e) {
        nextView(new GUIEditContactInfo(this), false);

    }

    @Override
    protected void UpdateText() {

    }
}
