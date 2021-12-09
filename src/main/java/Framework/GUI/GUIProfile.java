package Framework.GUI;

import Framework.DataAccess.WritePic;
import InterfaceAdapter.InstanceMain;
import InterfaceAdapter.Presenter.TextPresenter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * This is the class responsible for generating the main page.
 */
public class GUIProfile extends View {

    public JLabel contactInfoLabel;
    public String searchInput;
    private JTextArea description;
    public JTextField searchBar;
    private final JPanel panel1;
    private final JPanel panel2;
    private final JPanel panel3;
    private final GUIUserLogin guiUserLogin = (GUIUserLogin) previous;
    private final TextPresenter textPresenter = guiUserLogin.getTextPresenter();
    Font font1 = new Font("SansSerif", Font.BOLD, 30);
    Font font2 = new Font("SansSerif", Font.PLAIN, 20);
    Font font3 = new Font("SansSerif", Font.PLAIN, 15);

    /**
    constructor of this class
     */
    public GUIProfile(View previous){
        super(previous);
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        PlaceThingsOnP1(panel1);
        PlaceThingsOnP2(panel2);
        PlaceThingsOnP3(panel3);
    }

    /**
    Panel 1 is the user profile panel, this method palaces the relevant components on panel 1.
     */
    private void PlaceThingsOnP1(JPanel p1){

        JLabel usernameLabel = new JLabel();
        JButton EditContactInfo = new JButton(textPresenter.printText("Edit your profile"));
        JButton goToPlaylist = new JButton(textPresenter.printText("Go to your playlist"));
        description = new JTextArea();
        JLabel coinLabel = new JLabel();
        contactInfoLabel = new JLabel();
        ImageIcon icon = new ImageIcon(WritePic.getPicUser((String)InstanceMain.getNormalCUser().profilePage(userName)[7]));
        JLabel i = new JLabel(icon, JLabel.CENTER);
        Border bb = BorderFactory.createLineBorder(Color.BLACK,1);
        Border b = BorderFactory.createTitledBorder(bb,textPresenter.printText("Your Profile"), TitledBorder.LEADING, TitledBorder.TOP, font2);

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
        contactInfoLabel.setBounds(60, 300, 700, 200);
        i.setBounds(20, 70, 300, 200);
        description.setBounds(40, 440, 300, 200);
        EditContactInfo.setBounds(110,680,160,40);
        goToPlaylist.setBounds(110,740,160,40);

        usernameLabel.setFont(font1);
        coinLabel.setFont(font2);
        contactInfoLabel.setFont(font2);
        description.setFont(font2);
        description.setEditable(false);

        EditContactInfo.addActionListener(actionEvent -> OnEditButtonClick());

        goToPlaylist.addActionListener(e -> OnButtonClick2());

        p1.add(usernameLabel);
        p1.add(coinLabel);
        p1.add(contactInfoLabel);
        p1.add(EditContactInfo);
        p1.add(description);
        p1.add(i);
        p1.add(goToPlaylist);
        UpdateText();
    }

    /**
    Panel 2 is the movie recommendation panel, this method palaces the relevant components on panel 2.
     */
    private void PlaceThingsOnP2(JPanel p2){

        Border bb = BorderFactory.createLineBorder(Color.BLACK,1);
        Border b = BorderFactory.createTitledBorder(bb,textPresenter.printText("Recommendation"), TitledBorder.LEADING,TitledBorder.TOP, font2);
        p2.setBorder(b);
        p2.setBounds(420,230,740,610);

        ImageIcon icon1 = new ImageIcon(WritePic.getPic("1.jpg"));
        ImageIcon icon2 = new ImageIcon(WritePic.getPic("2.jpg"));
        ImageIcon icon3 = new ImageIcon(WritePic.getPic("3.jpg"));
        ImageIcon icon4 = new ImageIcon(WritePic.getPic("4.jpg"));
        JLabel Jicon1 = new JLabel(textPresenter.printText("Happy Life"), SwingConstants.CENTER);
        JLabel Jicon2 = new JLabel(textPresenter.printText("Team 28"), SwingConstants.CENTER);
        JLabel Jicon3 = new JLabel(textPresenter.printText("Candy"),SwingConstants.CENTER);
        JLabel Jicon4 = new JLabel(textPresenter.printText("Apple"),SwingConstants.CENTER);
        Jicon1.setFont(font3);
        Jicon2.setFont(font3);
        Jicon3.setFont(font3);
        Jicon4.setFont(font3);

        JLabel i1 = new JLabel(icon1);
        JLabel i2 = new JLabel(icon2);
        JLabel i3 = new JLabel(icon3);
        JLabel i4 = new JLabel(icon4);

        p2.setLayout(null);
        i1.setBounds(10, 25, 350, 225);
        i2.setBounds(380, 25, 350, 225);
        i3.setBounds(10, 320, 350, 225);
        i4.setBounds(380, 320, 350, 225);
        Jicon1.setBounds(80,270,200,20);
        Jicon2.setBounds(400,270,300,20);
        Jicon3.setBounds(35,570,300,20);
        Jicon4.setBounds(405,570,300,20);

        p2.add(i1);
        p2.add(i2);
        p2.add(i3);
        p2.add(i4);
        p2.add(Jicon1);
        p2.add(Jicon2);
        p2.add(Jicon3);
        p2.add(Jicon4);


    }

    /**
    Panel 3 is the search panel, this method palaces the relevant components on panel 3.
     */
    private void PlaceThingsOnP3(JPanel p3){
        p3.setLayout(null);
        Border bb = BorderFactory.createLineBorder(Color.BLACK,1);
        Border b = BorderFactory.createTitledBorder(bb,textPresenter.printText("Search movie by name"), TitledBorder.LEADING,TitledBorder.TOP, font2);
        p3.setBorder(b);
        p3.setBounds(420,20,740,200);

        searchBar = new JTextField();
        JButton searchButton = new JButton();
        searchButton.setText(textPresenter.printText("Search"));
        searchBar.setBounds(100,80,500,40);
        searchBar.setFont(font2);
        searchButton.setBounds(300,140,100,40);
//        listSearch.setBounds(100,120,500,200);

        searchButton.addActionListener(actionEvent -> OnSearchButtonClick());

        p3.add(searchBar);
        p3.add(searchButton);
    }

    /**
    This action adds to the Edit button, which directs to the edit page.
     */
    private void OnEditButtonClick() {
        nextView(new GUIEditContactInfo(this), false);
        UpdateText();
    }

    private void OnButtonClick2() {
        nextView(new GUIPlaylist(this), false);
    }

    private void OnSearchButtonClick(){
        this.searchInput = searchBar.getText();
        if (!(searchInput.equals(""))){
            nextView(new SearchResult(this), false);

        } else {
            JOptionPane.showMessageDialog(null,textPresenter.printText("Please enter a movie name"),"!",JOptionPane.PLAIN_MESSAGE);
        }
    }

    public String getSearchInput(){
        return this.searchInput;
    }

    /**
    This method enables automatic updates on this page after user enters new information on the edit page.
     */
    @Override
    protected void UpdateText() {
        contactInfoLabel.setText("Contact Info: " + InstanceMain.getNormalCUser().profilePage(userName)[2]);
        description.setText((String)InstanceMain.getNormalCUser().profilePage(userName)[3]);
    }

    public TextPresenter getTextPresenter(){
        return this.textPresenter;
    }

    /**
     *The method in super class for generating a new frame.
     */
    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle(textPresenter.printText("TEReview"));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.setSize(1200,900);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

}
