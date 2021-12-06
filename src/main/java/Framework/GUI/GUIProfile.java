package Framework.GUI;

import Framework.DataAccess.WritePic;
import InterfaceAdapter.InstanceMain;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GUIProfile extends View {

    public JLabel contactInfoLabel;
    private JTextArea description;
    private JLabel coinLabel;
    private final JPanel panel1;
    private final JPanel panel2;
    private JPanel panel3;
    Font font1 = new Font("SansSerif", Font.BOLD, 30);
    Font font2 = new Font("SansSerif", Font.PLAIN, 20);

    public GUIProfile(View previous){
        super(previous);
        panel1 = new JPanel();
        panel2 = new JPanel();
        PlaceThingsOnP1(panel1);
        PlaceThingsOnP2(panel2);
    }

    private void PlaceThingsOnP1(JPanel p1){

        JLabel usernameLabel = new JLabel();
        JButton EditContactInfo = new JButton("Edit your profile");
        description = new JTextArea();
        coinLabel = new JLabel();
        contactInfoLabel = new JLabel();
        ImageIcon icon = new ImageIcon(WritePic.getPic("winnie.jpg"));
        JLabel i = new JLabel(icon, JLabel.CENTER);
        Border bb = BorderFactory.createLineBorder(Color.BLACK,1);
        Border b = BorderFactory.createTitledBorder(bb,"Your Profile", TitledBorder.LEADING, TitledBorder.TOP, font2);

        usernameLabel.setText((String)InstanceMain.getNormalCUser().profilePage(userName)[0]);
        contactInfoLabel.setText((String)InstanceMain.getNormalCUser().profilePage(userName)[2]);
        coinLabel.setText("coins: " + InstanceMain.getNormalCUser().profilePage(userName)[5]);
        description.setText((String)InstanceMain.getNormalCUser().profilePage(userName)[3]);

        p1.setLayout(null);
        p1.setBorder(b);


        // Set bounds to the components
        p1.setBounds(20,20, 380,820);
        coinLabel.setBounds(140, 250, 300, 200);
        usernameLabel.setBounds(150,200,700,200);
        contactInfoLabel.setBounds(100, 300, 700, 200);
        i.setBounds(20, 70, 300, 200);
        description.setBounds(40, 480, 300, 200);
        EditContactInfo.setBounds(110,700,160,40);

        usernameLabel.setFont(font1);
        coinLabel.setFont(font2);
        contactInfoLabel.setFont(font2);
        description.setFont(font2);
        description.setEditable(false);

        EditContactInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OnButtonClick(actionEvent);
            }
        });

        UpdateText();
        p1.add(usernameLabel);
        p1.add(coinLabel);
        p1.add(contactInfoLabel);
        p1.add(EditContactInfo);
        p1.add(description);
        p1.add(i);
    }

    private void PlaceThingsOnP2(JPanel p2){

        Border bb = BorderFactory.createLineBorder(Color.BLACK,1);
        Border b = BorderFactory.createTitledBorder(bb,"Recommendation", TitledBorder.LEADING,TitledBorder.TOP, font2);
        p2.setBorder(b);
        p2.setBounds(420,290,740,550);

        ImageIcon icon1 = new ImageIcon(WritePic.getPic("5.jpg"));
        ImageIcon icon2 = new ImageIcon(WritePic.getPic("4.jpg"));
        ImageIcon icon3 = new ImageIcon(WritePic.getPic("3.jpg"));
        JLabel Jicon1 = new JLabel("Movie 1", SwingConstants.CENTER);
        JLabel Jicon2 = new JLabel("Movie 2", SwingConstants.CENTER);
        JLabel Jicon3 = new JLabel("Movie 3",SwingConstants.CENTER);

        JLabel i1 = new JLabel(icon1);
        JLabel i2 = new JLabel(icon2);
        JLabel i3 = new JLabel(icon3);

        p2.setLayout(null);
        i1.setBounds(5, 200, 200, 200);
        i2.setBounds(200, 200, 300, 200);
        i3.setBounds(450, 200, 300, 200);
        Jicon1.setBounds(5,400,200,20);
        Jicon2.setBounds(200,400,300,20);
        Jicon3.setBounds(450,400,300,20);

        p2.add(i1);
        p2.add(i2);
        p2.add(i3);
        p2.add(Jicon1);
        p2.add(Jicon2);
        p2.add(Jicon3);

    }

    private void OnButtonClick(ActionEvent e) {
        nextView(new GUIEditContactInfo(this), false);
    }

    @Override
    protected void UpdateText() {
//        System.out.println(InstanceMain.getNormalCUser().profilePage(userName)[2]);
//        System.out.println(InstanceMain.getNormalCUser().profilePage(userName)[3]);
        contactInfoLabel.setText("Contact Info: " + InstanceMain.getNormalCUser().profilePage(userName)[2]);
        description.setText((String)InstanceMain.getNormalCUser().profilePage(userName)[3]);
    }

    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle("TEReview");
        frame.add(panel1);
        frame.add(panel2);
        frame.setSize(1200,900);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

}
