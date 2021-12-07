package Framework.GUI;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WritePic;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import InterfaceAdapter.Presenter.TextPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUIMain extends View {
    private JButton registerButton;
    private JPanel panel1;
    private JButton loginButton;
    private JButton adminRegisterButton;
    private JButton adminLoginButton;
    public TextPresenter textPresenter;
    private GUIChooseLanguage chooseLanguage;

    /*
    GuiMain designs the main interface of our program, with four buttons, AdminRegister, register, AdminLogin and login.
    Users can choose one button they want.
     */
    public GUIMain(View previous) throws IOException {
        super(previous);
        chooseLanguage = (GUIChooseLanguage) previous;
        textPresenter = new TextPresenter(chooseLanguage.getLanguage());
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    nextView(new GUIUserRegister(previous, false), false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    nextView(new GUIUserLogin(previous, false), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    nextView(new GUIUserLogin(previous, true), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        adminRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    nextView(new GUIUserRegister(previous, true), false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void UpdateText() {

    }

    public TextPresenter getTextPresenter(){
        return this.textPresenter;
    }

    /*
    Add four buttons on the Panel.
     */
    protected void addComponentToP1() {
        JButton chooseLanguage = new JButton();
        chooseLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nextView(new GUIChooseLanguage(previous), true);
            }
        });
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


    @Override
    protected JFrame getFrame() {
        JFrame jFrame = super.getFrame();
        jFrame.setTitle(textPresenter.printText("Our App"));
        jFrame.setSize(810, 900);
        jFrame.setVisible(true);
        addComponentToP1();
        jFrame.add(panel1);
        WriteUser writeUser = new WriteUser();
        WriteReview writeReview = new WriteReview();
        WriteMovie writeMovie = new WriteMovie();
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return jFrame;
    }
}

