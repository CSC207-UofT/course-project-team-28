package Framework.GUI;

import Framework.DataAccess.WritePic;
import InterfaceAdapter.Presenter.TextPresenter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Construct the GUIMain, the page consists of four buttons, to register and log in for both AdminUser and NormalUser.
 */
public class GUIMain extends View {
    private JButton registerButton;
    private JPanel panel1;
    private JButton loginButton;
    private JButton adminRegisterButton;
    private JButton adminLoginButton;
    public TextPresenter textPresenter;


    /**
    GuiMain designs the main interface of our program, with four buttons, AdminRegister, register, AdminLogin and login.
    Users can choose one button they want.
     */
    public GUIMain(View previous) throws IOException {
        super(previous);
        GUIChooseLanguage chooseLanguage = (GUIChooseLanguage) previous;
        textPresenter = new TextPresenter(chooseLanguage.getLanguage());
        registerButton.addActionListener(actionEvent -> {
            try {
                nextView(new GUIUserRegister(previous, false), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        loginButton.addActionListener(actionEvent -> {
            try {
                nextView(new GUIUserLogin(previous, false), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        adminLoginButton.addActionListener(actionEvent -> {
            try {
                nextView(new GUIUserLogin(previous, true), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        adminRegisterButton.addActionListener(actionEvent -> {
            try {
                nextView(new GUIUserRegister(previous, true), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Inherited method, to update text.
     */
    @Override
    protected void UpdateText() {

    }


    /**
    Add four buttons on the Panel.
     */
    protected void addComponentToP1() {
        JButton chooseLanguage = new JButton();
        chooseLanguage.addActionListener(actionEvent -> nextView(new GUIChooseLanguage(previous), true));
        ImageIcon bg = new ImageIcon(WritePic.getPic("Bg.gif"));
        bg.setImage(bg.getImage().getScaledInstance(810, 1440, Image.SCALE_DEFAULT));
        JLabel jbg = new JLabel(bg);
        jbg.add(registerButton);
        registerButton.setBounds(100, 600, 250, 100);
        registerButton.setText(textPresenter.printText("Register"));
        jbg.add(loginButton);
        loginButton.setBounds(460, 600, 250, 100);
        loginButton.setText(textPresenter.printText("login"));
        jbg.add(adminRegisterButton);
        adminRegisterButton.setBounds(100, 725, 250, 100);
        adminRegisterButton.setText(textPresenter.printText("Admin Register"));
        jbg.add(adminLoginButton);
        adminLoginButton.setBounds(460, 725, 250, 100);
        adminLoginButton.setText(textPresenter.printText("Admin Login"));
        jbg.add(chooseLanguage);
        chooseLanguage.setBounds(710, 10, 90, 50);
        chooseLanguage.setText(textPresenter.printText("language"));
        panel1.add(jbg);
    }

    /**
     * Inherited method, to get the frame of GUIMain page.
     * @return the frame of GUIMain page.
     */
    @Override
    protected JFrame getFrame() {
        JFrame jFrame = super.getFrame();
        jFrame.setTitle(textPresenter.printText("Our App"));
        jFrame.setSize(810, 900);
        jFrame.setVisible(true);
        addComponentToP1();
        jFrame.add(panel1);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return jFrame;
    }

}

