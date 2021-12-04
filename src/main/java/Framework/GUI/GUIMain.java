package Framework.GUI;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import Framework.Presenter.PicPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIMain extends View {
    private JButton registerButton;
    private JPanel panel1;
    private JButton loginButton;
    private JButton adminRegisterButton;
    private JButton adminLoginButton;

    /*
    GuiMain designs the main interface of our program, with four buttons, AdminRegister, register, AdminLogin and login.
    Users can choose one button they want.
     */
    public GUIMain(View previous) {
        super(previous);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nextView(new GUIUserRegister(previous,false),false);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nextView(new GUIUserLogin(previous,false), true);
            }
        });
        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nextView(new GUIUserLogin(previous,true), true);
            }
        });
        adminRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nextView(new GUIUserRegister(previous,true),false);
            }
        });
    }

    @Override
    protected void UpdateText() {

    }

    /*
    Add four buttons on the Panel.
     */
    protected void addComponentToP1() {
        ImageIcon bg = new ImageIcon(PicPresenter.getPic("Bg.gif"));
        bg.setImage(bg.getImage().getScaledInstance(810, 1440, Image.SCALE_DEFAULT));
        JLabel jbg = new JLabel(bg);
        jbg.add(registerButton);
        registerButton.setBounds(100, 600,250,100);
        jbg.add(loginButton);
        loginButton.setBounds(460, 600,250,100);
        jbg.add(adminRegisterButton);
        adminRegisterButton.setBounds(100, 725, 250, 100);
        jbg.add(adminLoginButton);
        adminLoginButton.setBounds(460, 725, 250, 100);
        panel1.add(jbg);
    }


    @Override
    protected JFrame getFrame() {
        JFrame jFrame = super.getFrame();
        jFrame.setTitle("Our App");
        jFrame.setSize(810, 900);
        jFrame.setVisible(true);
        addComponentToP1();
        jFrame.add(panel1);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return jFrame;
    }

    public static void main(String[] args){
        WriteUser writeUser = new WriteUser();
        WriteReview writeReview = new WriteReview();
        WriteMovie writeMovie = new WriteMovie();
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);

        View gui = new GUIMain(null);
        gui.nextView(gui,false);
    }
}
