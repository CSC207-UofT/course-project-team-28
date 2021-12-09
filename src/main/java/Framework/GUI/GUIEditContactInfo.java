package Framework.GUI;

import InterfaceAdapter.InstanceMain;
import InterfaceAdapter.Presenter.TextPresenter;

import javax.swing.*;

public class GUIEditContactInfo extends View{
    private final JPanel jPanel;
    private JTextField jTextField;
    private JTextArea description;
    private final GUIProfile guiProfile = (GUIProfile) previous;
    private final TextPresenter textPresenter = guiProfile.getTextPresenter();

    /**
    Edit contact information.
     */
    public GUIEditContactInfo(View previous) {
        super(previous);
        jPanel = new JPanel();
        PlaceThingsOnPanel(jPanel);
    }

    /**
    Place related buttons and texts on panels.
     */
    private void PlaceThingsOnPanel(JPanel p){
        p.setLayout(null);
        jTextField = new JTextField();
        description = new JTextArea();
        JLabel jLabel = new JLabel(textPresenter.printText("Enter your new phone number: "));
        JLabel e = new JLabel(textPresenter.printText("Enter your new description: "));
        JButton saveButton = new JButton(textPresenter.printText("Save"));

        p.setBounds(0,0,500,500);
        jLabel.setBounds(5,40,300,30);
        e.setBounds(5,100,300,30);
        jTextField.setBounds(200,40,150,30);
        description.setBounds(200,100,200,120);
        saveButton.setBounds(200,230,80,30);
        jTextField.setText((String) InstanceMain.getNormalCUser().profilePage(userName)[2]);
        description.setText((String)InstanceMain.getNormalCUser().profilePage(userName)[3]);


        saveButton.addActionListener(actionEvent -> OnSaveClick());

        p.add(jLabel);
        p.add(jTextField);
        p.add(saveButton);
        p.add(e);
        p.add(description);
    }

    /**
    Save the updated information and dispose the edit window.
     */
    public void OnSaveClick(){
        String contactInfo = jTextField.getText();
        String des = description.getText();
        InstanceMain.getNormalCUser().editProfile(contactInfo, "contactInfo");
        InstanceMain.getNormalCUser().editProfile(des,"description");
        guiProfile.UpdateText();
        this.getFrame().dispose();
    }

    @Override
    protected void UpdateText() {

    }

    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle(textPresenter.printText("Edit Contact Info"));
        frame.add(jPanel);
        frame.setSize(500,320);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }
}
