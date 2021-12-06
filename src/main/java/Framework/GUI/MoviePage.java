package Framework.GUI;

import Framework.DataAccess.WritePic;
import InterfaceAdapter.InstanceMain;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MoviePage extends View {

    public JLabel contactInfoLabel;
    public String searchInput;
    private JTextArea description;
    public JTextField searchBar;
    private final JPanel panel1;
    private final JPanel panel2;
    Font font1 = new Font("SansSerif", Font.BOLD, 30);
    Font font2 = new Font("SansSerif", Font.PLAIN, 20);

    /**
     constructor of this class
     */
    public MoviePage(View previous){
        super(previous);
        panel1 = new JPanel();
        panel2 = new JPanel();
        PlaceThingsOnP1(panel1);
        PlaceThingsOnP2(panel2);
    }

    /**
     Panel 1 is the user profile panel, this method palaces the relevant components on panel 1.
     */
    private void PlaceThingsOnP1(JPanel p1){

        JLabel movieName = new JLabel();
        JButton giveLikeToMovie = new JButton("Like the Movie");
        JButton addToPlaylist = new JButton("Add to your playlist");
        description = new JTextArea();
        JLabel coinLabel = new JLabel();
        contactInfoLabel = new JLabel();
        ImageIcon icon = new ImageIcon(WritePic.getPic("5.jpg"));
        JLabel i = new JLabel(icon, JLabel.CENTER);
        Border bb = BorderFactory.createLineBorder(Color.BLACK,1);
        Border b = BorderFactory.createTitledBorder(bb,"Your Profile", TitledBorder.LEADING, TitledBorder.TOP, font2);

        movieName.setText((String)InstanceMain.getNormalCUser().profilePage(userName)[0]);
        contactInfoLabel.setText((String)InstanceMain.getNormalCUser().profilePage(userName)[2]);
        coinLabel.setText("coins: " + InstanceMain.getNormalCUser().profilePage(userName)[5]);
        description.setText((String)InstanceMain.getNormalCUser().profilePage(userName)[3]);

        p1.setLayout(null);
        p1.setBorder(b);


        // Set bounds to the components
        p1.setBounds(20,20, 380,820);
        coinLabel.setBounds(140, 250, 300, 200);
        movieName.setBounds(150,200,700,200);
        contactInfoLabel.setBounds(100, 300, 700, 200);
        i.setBounds(20, 70, 300, 200);
        description.setBounds(40, 440, 300, 200);
        giveLikeToMovie.setBounds(110,680,160,40);
        addToPlaylist.setBounds(110,740,160,40);

        movieName.setFont(font1);
        coinLabel.setFont(font2);
        contactInfoLabel.setFont(font2);
        description.setFont(font2);
        description.setEditable(false);

        giveLikeToMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OnEditButtonClick(actionEvent);
            }
        });

        addToPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnButtonClick2(e);
            }
        });

        UpdateText();
        p1.add(movieName);
        p1.add(coinLabel);
        p1.add(contactInfoLabel);
        p1.add(giveLikeToMovie);
        p1.add(description);
        p1.add(i);
        p1.add(addToPlaylist);
    }

    /**
     Panel 2 is the movie recommendation panel, this method palaces the relevant components on panel 2.
     */
    private void PlaceThingsOnP2(JPanel p2){

        Border bb = BorderFactory.createLineBorder(Color.BLACK,1);
        Border b = BorderFactory.createTitledBorder(bb,"Recommendation", TitledBorder.LEADING,TitledBorder.TOP, font2);
        p2.setBorder(b);
        p2.setBounds(420,20,740,820);

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

    /**
     This action adds to the Edit button, which directs to the edit page.
     */
    private void OnEditButtonClick(ActionEvent e) {
        nextView(new GUIEditContactInfo(this), false);
    }

    private void OnButtonClick2(ActionEvent e) {
        nextView(new GUIPlaylist(this), false);
    }

    private void OnSearchButtonClick(ActionEvent e){
        this.searchInput = searchBar.getText();
        if (!(searchInput.equals(""))){
            nextView(new SearchResult(this), false);

        } else {
            JOptionPane.showMessageDialog(null,"Please enter a movie name","!",JOptionPane.PLAIN_MESSAGE);
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
