package Framework.GUI;

import InterfaceAdapter.InstanceMain;
import Framework.Presenter.PicPresenter;

import javax.swing.*;
import javax.swing.border.Border;
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
    Font font1 = new Font("SansSerif", Font.BOLD, 30);
    Font font2 = new Font("SansSerif", Font.PLAIN, 20);

    public GUIProfile(View previous){
        super(previous);
        panel1 = new JPanel();
        PlaceThingsOnP1(panel1);
    }

    private void PlaceThingsOnP1(JPanel p1){

        JLabel usernameLabel = new JLabel();
        JButton EditContactInfo = new JButton("Edit your profile");
        description = new JTextArea();
        coinLabel = new JLabel();
        contactInfoLabel = new JLabel();
        ImageIcon icon = new ImageIcon(PicPresenter.getPic("5.jpg"));
        JLabel i = new JLabel(icon, JLabel.CENTER);
        Border b = BorderFactory.createLineBorder(Color.BLACK, 1);

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
        p2.setLayout(null);
        ArrayList<String> playList = (ArrayList<String>) InstanceMain.getNormalCUser().profilePage(userName)[6];
        JList PlayList = new JList(playList.toArray());
        PlayList.setBounds(10,10,500,500);
        p2.add(PlayList);
        PlayList.setFont(font1);
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
        frame.setTitle("Your Page");
        /*
        frame.add(jTabbedPane);
        */
        frame.add(panel1);
        frame.setSize(1200,900);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

}
