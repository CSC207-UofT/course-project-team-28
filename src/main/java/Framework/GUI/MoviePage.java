package Framework.GUI;

import Framework.DataAccess.WritePic;
import InterfaceAdapter.InstanceMain;
import InterfaceAdapter.Presenter.TextPresenter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;


public class MoviePage extends View {

    private final JPanel panel1;
    private final JPanel panel2;
    private String searchedMovie;
    private JLabel numberOfLikes;
    private JList reviewList;
    private TextPresenter textPresenter;
    Font font1 = new Font("SansSerif", Font.BOLD, 30);
    Font font2 = new Font("SansSerif", Font.PLAIN, 20);
    Font font3 = new Font("SansSerif", Font.PLAIN, 17);

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
        try {
            SearchResult searchResult = (SearchResult) previous;
            searchedMovie = searchResult.getMovieSelected();
            textPresenter = searchResult.getTextPresenter();
        }
        catch (ClassCastException e){
            AddReview searchResult = (AddReview) previous;
            searchedMovie = searchResult.getMovieSelected();
            textPresenter = searchResult.getTextPresenter();
        }



        numberOfLikes = new JLabel();
        JLabel movieName = new JLabel();
        JLabel movieLink = new JLabel();
        JButton giveLikeToMovie = new JButton(textPresenter.printText("Like the movie & add to your playlist"));
        JLabel movieCategory = new JLabel();
        ImageIcon icon = new ImageIcon(WritePic.getPic("4.jpg"));
        JLabel i = new JLabel(icon, JLabel.CENTER);
        Border bb = BorderFactory.createLineBorder(Color.BLACK,1);
        Border b = BorderFactory.createTitledBorder(bb,textPresenter.printText("Movie Info"), TitledBorder.LEADING, TitledBorder.TOP, font2);
        movieName.setText((String) InstanceMain.getNormalCMovie().movieProfile(searchedMovie)[0]);
        movieLink.setText((String) InstanceMain.getNormalCMovie().movieProfile(searchedMovie)[1]);
        numberOfLikes.setText(textPresenter.printText("number of likes: ") + InstanceMain.getNormalCMovie().movieProfile(searchedMovie)[3]);
        movieCategory.setText(textPresenter.printText("Category: ") + InstanceMain.getNormalCMovie().movieProfile(searchedMovie)[2]);
        JButton addReview = new JButton(textPresenter.printText("Add review to this movie"));

        p1.setLayout(null);
        p1.setBorder(b);


        // Set bounds to the components
        p1.setBounds(20,20, 380,820);
        numberOfLikes.setBounds(140, 300, 300, 200);
        movieName.setBounds(140,300,700,200);
        movieLink.setBounds(110, 360, 700, 200);
        numberOfLikes.setBounds(110, 410, 700, 200);
        i.setBounds(40, 70, 300, 300);
        movieCategory.setBounds(110, 550, 300, 200);
        giveLikeToMovie.setBounds(40,560,300,40);
        addReview.setBounds(60, 700, 260, 40);

        movieName.setFont(font1);
        numberOfLikes.setFont(font2);
        movieCategory.setFont(font2);
        movieLink.setFont(font2);

        giveLikeToMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OnEditButtonClick(actionEvent);
            }
        });

        addReview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnAddButtonClick(e);
            }
        });


        p1.add(movieName);
        p1.add(numberOfLikes);
        p1.add(numberOfLikes);
        p1.add(giveLikeToMovie);
        p1.add(movieCategory);
        p1.add(i);
        p1.add(movieLink);
        p1.add(addReview);
    }

    /**
     Panel 2 is the movie recommendation panel, this method palaces the relevant components on panel 2.
     */
    private void PlaceThingsOnP2(JPanel p2){
        p2.setLayout(null);
        List lst = new ArrayList();
        if (!InstanceMain.getNormalCMovie().movieReviews(searchedMovie).isEmpty()) {
            ArrayList<Object[]> list = InstanceMain.getNormalCMovie().movieReviews(searchedMovie);
            for (Object a : list) {
                lst.add(((Object[]) a)[4].toString() + ": "+ ((Object[]) a)[2]);
            }
        }
        reviewList = new JList(lst.toArray());
        reviewList.setFont(font3);
        RendererHelper cellRenderer = new RendererHelper(500);
        reviewList.setCellRenderer(cellRenderer);
        reviewList.setFixedCellHeight(350);
        JScrollPane jScrollPane = new JScrollPane(reviewList);
        p2.setBounds(420,20,760,820);
        Border bb = BorderFactory.createLineBorder(Color.BLACK,1);
        Border b = BorderFactory.createTitledBorder(bb,textPresenter.printText("Review Ranking"), TitledBorder.LEADING, TitledBorder.TOP, font2);
        p2.setBorder(b);
        jScrollPane.setBounds(20,40,720,760);

        reviewList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OnReviewClick(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        p2.add(jScrollPane);


    }

    private void OnReviewClick(MouseEvent e){
        nextView(new ReviewPage(this),true);
    }

    public int getSelectedReivew(){
        String result = (String) reviewList.getSelectedValue();
        String[] lst = result.split(": ");
        return Integer.parseInt(lst[0]);
    }

    /**
     This action adds to the Edit button, which directs to the edit page.
     */
    private void OnEditButtonClick(ActionEvent e) {
        InstanceMain.getNormalCMovie().likeMovie(searchedMovie);
    }

    private void OnAddButtonClick(ActionEvent e) {
        nextView(new AddReview(this), true);
    }

    public String getSearchedMovie(){return this.searchedMovie;}
    public TextPresenter getTextPresenter() { return this.textPresenter;}

    /**
     This method enables automatic updates on this page after user enters new information on the edit page.
     */
    @Override
    protected void UpdateText() {
        numberOfLikes.setText(textPresenter.printText("number of likes: ")+ InstanceMain.getNormalCMovie().movieProfile(searchedMovie)[3]);
    }


    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle(textPresenter.printText("Movie Info"));
        frame.add(panel1);
        frame.add(panel2);
        frame.setSize(1200,900);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }

}
