import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIUserRegister extends SharedView {
    private static String userName = "";
    private static JPanel panel;
    private static JLabel usernameLabel;
    private static JLabel pswLabel;
    private static JLabel RegResult;
    private static JLabel RegCondition;
    private static JTextField usernameText;
    private static JTextField passwordText;
    private final JLabel adminCodeLabel = new JLabel("Administrator Code");
    private final JTextField adminCodeText = new JTextField(20);
    public GUIUserRegister(Boolean isAdmin){
        super(isAdmin);
        panel = new JPanel();
        placeComponents(panel);
    }
    //Place components on GUI
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10,20,80,25);
        panel.add(usernameLabel);
        usernameText = new JTextField(20);
        usernameText.setBounds(130,20,165,25);
        panel.add(usernameText);
        RegCondition = new JLabel("(numbers and letters only)");
        RegCondition.setBounds(10,39,300,25);
        panel.add(RegCondition);
        pswLabel = new JLabel("Password");
        pswLabel.setBounds(10,60,80,25);
        panel.add(pswLabel);
        passwordText = new JPasswordField(20);
        passwordText.setBounds(130,60,165,25);
        panel.add(passwordText);
        JButton RegButton = new JButton("Register");
        RegButton.setBounds(10, 110, 90, 25);
        RegButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OnRegClick(actionEvent);
            }
        });
        panel.add(RegButton);
        RegResult = new JLabel("");
        RegResult.setBounds(10, 130, 300, 25);
        panel.add(RegResult);
        if(isAdmin){
            adminCodeLabel.setBounds(10,80,120,25);
            panel.add(adminCodeLabel);
            adminCodeText.setBounds(130,80,165,25);
            panel.add(adminCodeText);
        }

    }
    public void OnRegClick(ActionEvent e) {
        userName = usernameText.getText();
        String password = passwordText.getText();
        boolean register = false;
        if(isAdmin){
            String code = adminCodeText.getText();
            register = (IM.aucontroller.register(userName, password, code, IM.wu));
        } else {
            register = (IM.nucontroller.register(userName, password, IM.wu));
        }
        if(register){
            JOptionPane.showMessageDialog(null, "Successfully registered, you can login now.", ":D", JOptionPane.PLAIN_MESSAGE );
            this.getFrame().dispose();
        } else {
            RegResult.setText("Something is wrong with your username or password.");
        }

    }

    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle("Register");
        frame.setSize(350,200);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }
}
