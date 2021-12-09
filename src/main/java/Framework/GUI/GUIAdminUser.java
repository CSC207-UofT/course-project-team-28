package Framework.GUI;

import Framework.DataAccess.WritePic;
import InterfaceAdapter.InstanceMain;
import InterfaceAdapter.Presenter.TextPresenter;

import javax.swing.*;
import java.awt.*;

/**
 * This class functions as a page for admin user to upload movie after admin registration.
 */
public class GUIAdminUser extends View{

    private JTextField mNameText;
    private JTextField mLinkText;
    private JList<Object> categoryChoice;
    private final JPanel panel1;
    private final GUIUserLogin guiUserLogin = (GUIUserLogin) previous;
    private final TextPresenter textPresenter = guiUserLogin.getTextPresenter();
    private final String[] cate = {"Action", "Anime", "Comedy", "Horror", "Romantic"};
    Font font1 = new Font("SansSerif", Font.PLAIN, 20);

    /**
     * The constructor for this class.
     * @param previous The View parameter from the super class.
     */
    public GUIAdminUser(View previous){
        super(previous);
        panel1 = new JPanel();
        PlaceThingsOnP1(panel1);
    }

    /**
     * Add components to panel.
     * @param p1 The Jpanel to be modified.
     */
    private void PlaceThingsOnP1(JPanel p1) {

        JLabel movieName = new JLabel(); // The first letter of the movie must be capitalized.
        JLabel movieLink = new JLabel();
        JLabel category = new JLabel();
        mNameText = new JTextField();
        mLinkText = new JTextField();

        JButton selectPics = new JButton(textPresenter.printText("Select Pictures for the Movie"));
        ImageIcon icon = new ImageIcon(WritePic.getPic("shake hand.jpg"));
        JLabel i = new JLabel(textPresenter.printText("Movie Image"), icon, JLabel.CENTER);
        JButton uploadMovies = new JButton(textPresenter.printText("Upload Movies"));

        movieName.setText(textPresenter.printText("Movie Name"));
        movieLink.setText(textPresenter.printText("Movie Link"));
        category.setText(textPresenter.printText("Category"));
        categoryChoice = new JList<>(cate);

        p1.setLayout(null);

        p1.setBounds(0, 0, 600, 1000);
        movieName.setBounds(30, 20, 150, 30);
        movieLink.setBounds(30, 70, 150, 30);
        category.setBounds(30,120,150,30);
        mNameText.setBounds(200, 10, 300, 40);
        mLinkText.setBounds(200, 60, 300, 40);
        categoryChoice.setBounds(200,110,300,100);
        categoryChoice.setSelectedIndex(0);
        selectPics.setBounds(200, 230, 200, 50);
        uploadMovies.setBounds(230, 250, 200, 50);
        i.setBounds(30,280,400,180);

        movieName.setFont(font1);
        movieLink.setFont(font1);
        category.setFont(font1);

        uploadMovies.addActionListener(e -> OnUploadClick());

        p1.add(movieName);
        p1.add(movieLink);
        p1.add(category);
        p1.add(mNameText);
        p1.add(mLinkText);
        p1.add(categoryChoice);
        p1.add(uploadMovies);
    }

    /**
     * This is the action when the admin user press the Upload button.
     */
    public void OnUploadClick() {
        String mName = mNameText.getText();
        String link = mLinkText.getText();
        String category = (String) categoryChoice.getSelectedValue();
        if (InstanceMain.getAdminInputProcessor().uploadMovie(mName, link, category)) {
            previous.UpdateText();

            JOptionPane.showMessageDialog(null, textPresenter.printText("Successfully uploaded"), textPresenter.printText(":D"),
                    JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, textPresenter.printText("Your movie already exists"), ":(",
                    JOptionPane.PLAIN_MESSAGE);
        }

    }

    @Override
    protected void UpdateText () {

    }

    /**
     *The method in super class for generating a new frame.
     */
    @Override
    public JFrame getFrame () {
        JFrame frame = super.getFrame();
        frame.setTitle(textPresenter.printText("AdminUser Page"));
        frame.add(panel1);
        frame.setSize(800, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }
}
