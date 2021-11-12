import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel username;
    private static JLabel psw;
    public static void main(String[] args){
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(350,200);
        frame.add(panel);
        placeComponents(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);
        username = new JLabel("Username");
        username.setBounds(10,20,80,25);
        panel.add(username);
        JTextField usernameText = new JTextField(20);
        usernameText.setBounds(100,20,165,25);
        panel.add(usernameText);
        psw = new JLabel("Password");
        psw.setBounds(10,50,80,25);
        panel.add(psw);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
