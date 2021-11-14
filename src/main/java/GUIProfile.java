import javax.swing.*;
import java.awt.*;

public class GUIProfile extends View {
    JTabbedPane jTabbedPane;
    JLabel usernameLabel;
    JLabel coinLabel;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    Font font1 = new Font("SansSerif", Font.BOLD, 20);
    public GUIProfile(){
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel2 = new JPanel();
        panel3 = new JPanel();
        jTabbedPane = new JTabbedPane();
        jTabbedPane.setBounds(35,30,700,500);
        jTabbedPane.add("User Profile",panel1);
        jTabbedPane.add("Playlist",panel2);
        jTabbedPane.add("Category",panel3);
        usernameLabel = new JLabel("Username: " + IM.nucontroller.profile_page(userName).get(0));
        usernameLabel.setFont(font1);
        usernameLabel.setBounds(20,20,300,200);
        coinLabel = new JLabel("number of coins: " + "300");
        coinLabel.setBounds(200, 20, 300, 200);
        panel1.add(usernameLabel);
        panel1.add(coinLabel);
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

//    public static void main(String[] args) {
//        new GUIProfile(false);
//    }
}
