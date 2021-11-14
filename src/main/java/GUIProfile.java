import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GUIProfile extends View {
    private final JTabbedPane jTabbedPane;
    private final JPanel panel1;
    private final JPanel panel2;
    private final JPanel panel3;
    Font font1 = new Font("SansSerif", Font.BOLD, 30);
    public GUIProfile(){
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        jTabbedPane = new JTabbedPane();
        jTabbedPane.setBounds(35,30,700,500);
        jTabbedPane.add("User Profile",panel1);
        jTabbedPane.add("Playlist",panel2);
        jTabbedPane.add("Category",panel3);
        PlaceThingsOnP1(panel1);
        PlaceThingsOnP2(panel2);
    }
    private void PlaceThingsOnP1(JPanel p1){
        p1.setLayout(null);
        JLabel usernameLabel = new JLabel("Username: " + IM.ncu.profilePage(userName)[0]);
        usernameLabel.setFont(font1);
        usernameLabel.setBounds(20,20,700,200);
        JLabel coinLabel = new JLabel("number of coins: " + IM.ncu.profilePage(userName)[5]);
        coinLabel.setBounds(20, 60, 300, 200);
        JLabel contactInfoLabel = new JLabel("Contact info: " + IM.ncu.profilePage(userName)[2]);
        contactInfoLabel.setBounds(20, 90, 700, 200);
        JLabel description = new JLabel("User description: " + IM.ncu.profilePage(userName)[3]);
        description.setBounds(20, 120, 700, 200);
        JButton EditContactInfo = new JButton("Edit");
        EditContactInfo.setBounds(180,180,60,20);
        EditContactInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nextView(new GUIEditContactInfo(), false);
            }
        });
        p1.add(usernameLabel);
        p1.add(coinLabel);
        p1.add(contactInfoLabel);
        p1.add(EditContactInfo);
        p1.add(description);
    }
    private void PlaceThingsOnP2(JPanel p2){
        p2.setLayout(null);
        ArrayList<String> playList = (ArrayList<String>) IM.ncu.profilePage(userName)[6];
        JList PlayList = new JList(playList.toArray());
        PlayList.setBounds(10,10,500,500);
        p2.add(PlayList);
        PlayList.setFont(font1);
    }

    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle("Your Page");
        frame.add(jTabbedPane);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }
}
