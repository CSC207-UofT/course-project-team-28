package Framework.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIUserLogin extends SharedView {
    private static JPanel panel;
    private static JLabel usernameLabel;
    private static JLabel pswLabel;
    private static JLabel loginResult;
    private static JTextField usernameText;
    private static JTextField passwordText;
    private final JLabel adminCodeLabel = new JLabel("Administrator Code");
    private final JTextField adminCodeText = new JTextField(20);

    //gui
    public GUIUserLogin(View view, Boolean isAdmin){
        super(view, isAdmin);
        panel = new JPanel();
        placeComponents(panel);

    }
    // place components on GUI
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10,20,80,25);
        panel.add(usernameLabel);
        usernameText = new JTextField(20);
        usernameText.setBounds(130,20,165,25);
        panel.add(usernameText);
        pswLabel = new JLabel("Password");
        pswLabel.setBounds(10,50,80,25);
        panel.add(pswLabel);
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
            adminCodeLabel.setBounds(10,80,120,25);
            panel.add(adminCodeLabel);
            adminCodeText.setBounds(130,80,165,25);
            panel.add(adminCodeText);
        }
    }
    //actions
    public void OnLoginClick(ActionEvent e) {
        userName = usernameText.getText();
        String password = passwordText.getText();
        boolean login = false;
        if(isAdmin){
            String code = adminCodeText.getText();
            login = (IM.aucontroller.login(userName, password, code));
        } else {
            login = IM.ncu.login(userName, password);
        }
        if (login){
            loginResult.setText("Login successful.");
        }else {
            loginResult.setText("Username or password incorrect, please try again.");
        }
        nextView(new GUIProfile(this), true);
    }

    @Override
    protected void UpdateText() {

    }

    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle("Login");
        frame.setSize(350,200);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }


}

