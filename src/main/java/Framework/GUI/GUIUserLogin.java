package Framework.GUI;

import InterfaceAdapter.InstanceMain;
import InterfaceAdapter.Presenter.TextPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * This class contributes to the user log in page.
 */
public class GUIUserLogin extends SharedView {
    private static JPanel panel;
    private static JLabel loginResult;
    private static JTextField usernameText;
    private static JTextField passwordText;
    private final JTextField adminCodeText = new JTextField(20);
    private final GUIChooseLanguage guiMain = (GUIChooseLanguage) previous;
    private final TextPresenter textPresenter = guiMain.getTextPresenter();
    private final JLabel adminCodeLabel = new JLabel(textPresenter.printText("Administrator Code"));

    /**
    Constructor of this class.
     */
    public GUIUserLogin(View view, Boolean isAdmin) throws IOException {
        super(view, isAdmin);
        panel = new JPanel();
        placeComponents(panel);
    }

    /**
    place components on the page.
     */
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        JLabel usernameLabel = new JLabel(textPresenter.printText("Username"));
        usernameLabel.setBounds(10,20,80,25);
        panel.add(usernameLabel);
        usernameText = new JTextField(20);
        usernameText.setBounds(130,20,165,25);
        panel.add(usernameText);
        JLabel pswLabel = new JLabel(textPresenter.printText("Password"));
        pswLabel.setBounds(10,50,80,25);
        panel.add(pswLabel);
        passwordText = new JPasswordField(20);
        passwordText.setBounds(130,50,165,25);
        panel.add(passwordText);
        JButton loginButton = new JButton(textPresenter.printText("login"));
        loginButton.setBounds(10, 110, 80, 25);
        loginButton.addActionListener(this::OnLoginClick);
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

    /**
    Check whether the user is AdminUser or NormalUser, and Login.
     */
    public void OnLoginClick(ActionEvent e) {
        userName = usernameText.getText();
        String password = passwordText.getText();
        boolean login;
        if(isAdmin){
            String code = adminCodeText.getText();
            login = (InstanceMain.getAdminInputProcessor().login(userName, password, code));
            if (login){
                nextView(new GUIAdminUser(this), true);
            }
            else {
                loginResult.setText(textPresenter.printText("Username or password incorrect, please try again."));
            }
        } else {
            login = InstanceMain.getNormalCUser().login(userName, password);
            if (login){
                loginResult.setText(textPresenter.printText("Login successful."));
                nextView(new GUIProfile(this), true);
            }
            else {
                loginResult.setText(textPresenter.printText("Username or password incorrect, please try again."));
            }
        }
    }

    /**
     * Inherited method, to update text.
     */
    @Override
    protected void UpdateText() {

    }

    /**
     * Inherited method, to get text, which contributes to change language.
     * @return text according to the chosen language.
     */
    public TextPresenter getTextPresenter(){
        return this.textPresenter;
    }

    /**
     * Inherited method, to get the frame of this page
     * @return the frame of the page
     */
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle(textPresenter.printText("Login"));
        frame.setSize(350,200);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }


}

