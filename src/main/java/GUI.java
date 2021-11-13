import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    private static final NormalInputProcessor nucontroller = new NormalInputProcessor();
    private static final AdminInputProcessor aucontroller = new AdminInputProcessor();
    private static final WriteUser wu = new WriteUser(nucontroller, aucontroller);
    private static final WriteMovie wm = new WriteMovie(nucontroller, aucontroller);
    private static final WriteReview wr = new WriteReview(nucontroller);
    private static String userName = "";
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel usernameLable;
    private static JLabel pswLable;
    private static JLabel success;
    private static JLabel fail;
    private static JTextField usernameText;
    private static JTextField passwordText;
    //main
    public static void main(String[] args){
        new GUI();
    }
    //gui
    public GUI(){
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(350,200);
        frame.add(panel);
        placeComponents(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    // place components on GUI
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        usernameLable = new JLabel("Username");
        usernameLable.setBounds(10,20,80,25);
        panel.add(usernameLable);
        usernameText = new JTextField(20);
        usernameText.setBounds(100,20,165,25);
        panel.add(usernameText);
        pswLable = new JLabel("Password");
        pswLable.setBounds(10,50,80,25);
        panel.add(pswLable);
        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(this);
        panel.add(loginButton);
        success = new JLabel("");
        success.setBounds(10, 100, 300, 25);
        panel.add(success);
        fail = new JLabel("");
        fail.setBounds(10, 100, 300, 25);
        panel.add(fail);
    }
    //actions
    @Override
    public void actionPerformed(ActionEvent login) {
        String username = usernameText.getText();
        String password = passwordText.getText();
        while (!(nucontroller.login(username, password))){
            fail.setText("Username or password incorrect, please try again.");
        }

    }
}
