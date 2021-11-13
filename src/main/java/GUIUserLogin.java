import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIUserLogin extends SharedView {
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
    private static JLabel loginResult;
    private static JTextField usernameText;
    private static JTextField passwordText;
    private final JLabel adminCodeLable = new JLabel("Administrator Code");
    private final JTextField adminCodeText = new JTextField(20);

    //gui
    public GUIUserLogin(Boolean isAdmin){
        super(isAdmin);
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
        usernameText.setBounds(130,20,165,25);
        panel.add(usernameText);
        pswLable = new JLabel("Password");
        pswLable.setBounds(10,50,80,25);
        panel.add(pswLable);
        passwordText = new JPasswordField(20);
        passwordText.setBounds(130,50,165,25);
        panel.add(passwordText);
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 110, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OnLoginClick(actionEvent);
            }
        });
        panel.add(loginButton);
        loginResult = new JLabel("");
        loginResult.setBounds(10, 130, 300, 25);
        panel.add(loginResult);
        if(isAdmin){
            adminCodeLable.setBounds(10,80,120,25);
            panel.add(adminCodeLable);
            adminCodeText.setBounds(130,80,165,25);
            panel.add(adminCodeText);
        }
    }
    //actions
    public void OnLoginClick(ActionEvent e) {
        String username = usernameText.getText();
        String password = passwordText.getText();
        boolean login = false;
        if(isAdmin){
            String code = adminCodeText.getText();
            login = (aucontroller.login(username, password, code));
        } else {
            login = nucontroller.login(username, password);
        }
        if (login){
            loginResult.setText("Login successful.");
        }else {
            loginResult.setText("Username or password incorrect, please try again.");
        }

    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    protected void nextView(View view) {

    }
}

