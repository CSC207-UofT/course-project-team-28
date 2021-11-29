package Framework.GUI;

import InterfaceAdapter.InstanceMain;
import Framework.Presenter.PicPresenter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GUIProfile extends View {
    public JLabel contactInfoLabel;
    private JLabel coinLabel;
    /*private final JTabbedPane jTabbedPane;*/
    private final JPanel panel1;
    /*
    private final JPanel panel2;
    private final JPanel panel3;
    */
    Font font1 = new Font("SansSerif", Font.BOLD, 30);
    public GUIProfile(View previous){
        super(previous);
        panel1 = new JPanel();
        /*
        panel2 = new JPanel();
        panel3 = new JPanel();
        jTabbedPane = new JTabbedPane();
        jTabbedPane.setBounds(35,30,700,500);
        jTabbedPane.add("User Profile",panel1);
        jTabbedPane.add("Playlist",panel2);
        jTabbedPane.add("Category",panel3);
         */
        PlaceThingsOnP1(panel1);
        /*
        PlaceThingsOnP2(panel2);
         */
    }
    private void PlaceThingsOnP1(JPanel p1){
        p1.setLayout(null);
        Border b = BorderFactory.createLineBorder(Color.BLACK, 1);
        p1.setBorder(b);
        p1.setBounds(20,20, 380,820);
        JLabel usernameLabel = new JLabel((String)InstanceMain.getNormalCUser().profilePage(userName)[0]);
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

    /*
    private void PlaceThingsOnP1(JPanel p2){
        p2.setLayout(null);
        p2.setBounds(640,20, 600,450);
        JLabel usernameLabel = new JLabel("Username: " + InstanceMain.getNormalCUser().profilePage(userName)[0]);
        usernameLabel.setFont(font1);
        usernameLabel.setBounds(20,20,700,200);
        coinLabel = new JLabel("number of coins: " + InstanceMain.getNormalCUser().profilePage(userName)[5]);
        coinLabel.setBounds(20, 60, 300, 200);
        contactInfoLabel = new JLabel();
        contactInfoLabel.setText("Contact Info: " + InstanceMain.getNormalCUser().profilePage(userName)[2]);
        contactInfoLabel.setBounds(20, 90, 700, 200);
        JLabel description = new JLabel("User description: " + InstanceMain.getNormalCUser().profilePage(userName)[3]);
        description.setBounds(20, 120, 700, 200);
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
    }
    */

    private void PlaceThingsOnP2(JPanel p2){
        p2.setLayout(null);
        ArrayList<String> playList = (ArrayList<String>) InstanceMain.getNormalCUser().profilePage(userName)[6];
        JList PlayList = new JList(playList.toArray());
        PlayList.setBounds(10,10,500,500);
        p2.add(PlayList);
        PlayList.setFont(font1);
    }
    private void OnButtonClick(ActionEvent e) {
        nextView(new GUIEditContactInfo(this), false);

    }
//    private int aa = 0;
    @Override
    protected void UpdateText() {
        System.out.println(InstanceMain.getNormalCUser().profilePage(userName)[2]);
        contactInfoLabel.setText("Contact Info: " + InstanceMain.getNormalCUser().profilePage(userName)[2]);
//        contactInfoLabel.setText(String.valueOf(aa++));
    }

    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle("Your Page");
        /*
        frame.add(jTabbedPane);
        */
        frame.add(panel1);
        frame.setSize(1200,900);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }
}
