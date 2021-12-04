package Framework.GUI;

import Framework.Presenter.PicPresenter;
import InterfaceAdapter.InstanceMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIAdminUser extends View{

    private JLabel movieName;
    private JLabel movieLink;
    private JTextField mNameText;
    private JTextField mLinkText;
    private JButton selectPics;
    private JButton uploadMovies;
    private JPanel panel1;
    Font font1 = new Font("SansSerif", Font.PLAIN, 20);

    public GUIAdminUser(View previous){
        super(previous);
        panel1 = new JPanel();
        PlaceThingsOnP1(panel1);

    }

    private void PlaceThingsOnP1(JPanel p1) {

        movieName = new JLabel();
        movieLink = new JLabel();
        mNameText = new JTextField();
        mLinkText = new JTextField();
        selectPics = new JButton("Select Pictures for the Movie");
        ImageIcon icon = new ImageIcon(PicPresenter.getPic("shake hand.jpg"));
        JLabel i = new JLabel("Movie Image", icon, JLabel.CENTER);
        uploadMovies = new JButton("Upload Movies");

        movieName.setText("Movie Name");
        movieLink.setText("Movie Link");

        p1.setLayout(null);

        p1.setBounds(0, 0, 600, 1000);
        movieName.setBounds(30, 20, 150, 20);
        movieLink.setBounds(30, 70, 150, 20);
        mNameText.setBounds(200, 10, 300, 40);
        mLinkText.setBounds(200, 60, 300, 40);
        selectPics.setBounds(200, 130, 200, 50);
        uploadMovies.setBounds(200, 800, 200, 50);
        i.setBounds(30,200,400,180);

        movieName.setFont(font1);
        movieLink.setFont(font1);

        uploadMovies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnUploadClick(e);

            }
        });

        p1.add(movieName);
        p1.add(movieLink);
        p1.add(mNameText);
        p1.add(mLinkText);
        p1.add(selectPics);
        p1.add(i);
        p1.add(uploadMovies);
    }

    public void OnUploadClick(ActionEvent e) {
        String mName = mNameText.getText();
        String link = mLinkText.getText();
        if (InstanceMain.getAdminInputProcessor().uploadMovie(mName, link)) {
            previous.UpdateText();

            JOptionPane.showMessageDialog(null, "Successfully uploaded", ":D",
                    JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Your movie already exists", ":(",
                    JOptionPane.PLAIN_MESSAGE);
        }

    }

    @Override
    protected void UpdateText () {

    }

    @Override
    public JFrame getFrame () {
        JFrame frame = super.getFrame();
        frame.setTitle("AdminUser Page");
        frame.add(panel1);
        frame.setSize(800, 1500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }
}
