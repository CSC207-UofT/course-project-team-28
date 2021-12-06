package Framework.GUI;

import Framework.DataAccess.WritePic;
import InterfaceAdapter.InstanceMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIAdminUser extends View{

    private JLabel movieName;
    private JLabel movieLink;
    private JLabel category;
    private JTextField mNameText;
    private JTextField mLinkText;
    private JList categoryChoice;
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
        category = new JLabel();
        mNameText = new JTextField();
        mLinkText = new JTextField();

        String[] cate = {"Action", "Anime", "Comedy", "Horror", "Romantic"};

        selectPics = new JButton("Select Pictures for the Movie");
        ImageIcon icon = new ImageIcon(WritePic.getPic("shake hand.jpg"));
        JLabel i = new JLabel("Movie Image", icon, JLabel.CENTER);
        uploadMovies = new JButton("Upload Movies");

        movieName.setText("Movie Name");
        movieLink.setText("Movie Link");
        category.setText("Category");
        categoryChoice = new JList<>(cate);

        p1.setLayout(null);

        p1.setBounds(0, 0, 600, 1000);
        movieName.setBounds(30, 20, 150, 30);
        movieLink.setBounds(30, 70, 150, 30);
        category.setBounds(30,120,150,30);
        mNameText.setBounds(200, 10, 300, 40);
        mLinkText.setBounds(200, 60, 300, 40);
        categoryChoice.setBounds(200,110,300,100);
        selectPics.setBounds(200, 230, 200, 50);
        uploadMovies.setBounds(200, 800, 200, 50);
        i.setBounds(30,280,400,180);

        movieName.setFont(font1);
        movieLink.setFont(font1);
        category.setFont(font1);

        uploadMovies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnUploadClick(e);

            }
        });

        p1.add(movieName);
        p1.add(movieLink);
        p1.add(category);
        p1.add(mNameText);
        p1.add(mLinkText);
        p1.add(categoryChoice);
        p1.add(selectPics);
        p1.add(i);
        p1.add(uploadMovies);
    }

    public void OnUploadClick(ActionEvent e) {
        String mName = mNameText.getText();
        String link = mLinkText.getText();
        String category = "Action"; //!!!!!!
        if (InstanceMain.getAdminInputProcessor().uploadMovie(mName, link, category)) {
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
