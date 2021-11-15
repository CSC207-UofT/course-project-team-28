import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIMain extends View {
    private JButton registerButton;
    private JPanel panel1;
    private JButton loginButton;
    private JButton adminRegisterButton;
    private JButton adminLoginButton;
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

    @Override
    protected JFrame getFrame() {
        JFrame jFrame = super.getFrame();
        jFrame.setTitle("Our App");
        jFrame.setSize(300, 120);
        jFrame.setContentPane(panel1);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return jFrame;
    }

    public static void main(String[] args){
        View gui = new GUIMain(null);
        gui.nextView(gui,false);
    }
}
