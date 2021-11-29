package Framework.GUI;

import InterfaceAdapter.InstanceMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIEditContactInfo extends View{
    private final JPanel jPanel;
    private JLabel jLabel;
    private JTextField jTextField;
    private JButton SaveButton;

    /*
    Edit contact information.
     */
    public GUIEditContactInfo(View previous) {
        super(previous);
        jPanel = new JPanel();
        PlaceThingsOnPanel(jPanel);
    }

    /*
    Place related buttons and texts on panels.
     */
    private void PlaceThingsOnPanel(JPanel p){
        p.setLayout(null);
        p.setBounds(0,0,500,300);
        jLabel = new JLabel("Enter your new phone number: ");
        jLabel.setBounds(5,40,300,30);
        jTextField = new JTextField();
        jTextField.setText((String) InstanceMain.getNormalCUser().profilePage(userName)[2]);
        jTextField.setBounds(200,40,150,30);
        SaveButton = new JButton("Save");
        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OnSaveClick(actionEvent);
            }
        });
        SaveButton.setBounds(200,80,80,30);
        p.add(jLabel);
        p.add(jTextField);
        p.add(SaveButton);
    }

    /*
    Save the updated information and dispose the edit window.
     */
    public void OnSaveClick(ActionEvent e){
        String contactInfo = jTextField.getText();
        InstanceMain.getNormalCUser().editProfile(contactInfo, "contactInfo");
        previous.UpdateText();
        this.getFrame().dispose();
    }


    @Override
    protected void UpdateText() {

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
