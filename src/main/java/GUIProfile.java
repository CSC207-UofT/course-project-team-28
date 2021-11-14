import javax.swing.*;

public class GUIProfile extends View {
    JLabel userNameUpLabel;
    JTextArea jTextArea;
    JTabbedPane jTabbedPane;
    JLabel usernameLabel;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    public GUIProfile(){
        userNameUpLabel = new JLabel();
        jTextArea = new JTextArea(200,200);
        panel1 = new JPanel();
        panel1.add(jTextArea);
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel1.add(userNameUpLabel);
        jTabbedPane = new JTabbedPane();
        jTabbedPane.setBounds(35,30,700,500);
        jTabbedPane.add("User Profile",panel1);
        jTabbedPane.add("Playlist",panel2);
        jTabbedPane.add("Category",panel3);
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
