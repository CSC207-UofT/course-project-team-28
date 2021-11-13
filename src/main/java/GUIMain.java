import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIMain extends View {
    private JButton registerButton;
    private JPanel panel1;
    private JButton loginButton;
    private JButton adminRegisterButton;
    private JButton adminLoginButton;
    private JFrame jFrame;
    public GUIMain() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nextView(new GUIUserRegister(false));
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nextView(new GUIUserLogin(false));
            }
        });
        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nextView(new GUIUserLogin(true));
            }
        });
        adminRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nextView(new GUIUserRegister(true));
            }
        });
    }

    @Override
    protected JFrame getFrame() {
        jFrame = new JFrame("App");
        jFrame.setSize(300, 120);
        jFrame.setContentPane(panel1);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return jFrame;
    }

    @Override
    protected void nextView(View view) {
        JFrame nextFrame = view.getFrame();
        nextFrame.setLocationRelativeTo(null);
        nextFrame.setVisible(true);
        jFrame.dispose();
    }
}
