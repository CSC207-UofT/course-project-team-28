import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIEditContactInfo extends View{
    private final JPanel jPanel;
    private JLabel jLabel;
    private JTextField jTextField;
    private JButton SaveButton;
    public GUIEditContactInfo() {
        jPanel = new JPanel();
       PlaceThingsOnPanel(jPanel);
    }
    private void PlaceThingsOnPanel(JPanel p){
        p.setLayout(null);
        p.setBounds(0,0,500,300);
        jLabel = new JLabel("Enter your new phone number: ");
        jLabel.setBounds(5,40,300,30);
        jTextField = new JTextField();
        jTextField.setText((String)IM.ncu.profilePage(userName)[2]);
        jTextField.setBounds(200,40,150,30);
        SaveButton = new JButton("Save");
        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OnSaveClick(actionEvent);
                nextView(new GUIProfile(), true);
            }
        });
        SaveButton.setBounds(200,80,80,30);
        p.add(jLabel);
        p.add(jTextField);
        p.add(SaveButton);
    }
    public void OnSaveClick(ActionEvent e){
        String contactInfo = jTextField.getText();
        IM.ncu.editProfile(contactInfo, "contactinfo", IM.wu);
//        profile.SetContactInfoText("Contact Info: " + jTextField.getText());
        this.getFrame().dispose();
    }

    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle("Edit Contact Info");
        frame.add(jPanel);
        frame.setSize(500,150);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }
}
