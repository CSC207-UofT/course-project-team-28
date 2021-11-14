import javax.swing.*;

public class GUIProfile extends View {
    JLabel username;
    JTextArea jTextArea;
    JTabbedPane jTabbedPane;
    public GUIProfile(boolean b){
        username = new JLabel();
        jTextArea = new JTextArea(200,200);
        JPanel panel1 = new JPanel();
        panel1.add(jTextArea);
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        panel1.add(username);
        jTabbedPane = new JTabbedPane();
        jTabbedPane.setBounds(50,50,300,300);
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
