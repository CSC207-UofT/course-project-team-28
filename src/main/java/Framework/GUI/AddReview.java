package Framework.GUI;

import InterfaceAdapter.InstanceMain;
import InterfaceAdapter.Presenter.TextPresenter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * This class functions as a page for user to write a review after searching.
 */
public class AddReview extends View{
    private final JPanel p1;
    private String thisMovie;
    private JTextArea writeReview;
    private final MoviePage moviePage = (MoviePage) previous;
    private final TextPresenter textPresenter = moviePage.getTextPresenter();
    Font font2 = new Font("SansSerif", Font.PLAIN, 20);

    /**
     * The constructor for this class.
     * @param previous The View parameter from the super class.
     */
    public AddReview(View previous) {
        super(previous);
        p1 = new JPanel();
        PlaceThingsOnP1(p1);
    }

    /**
     * Add components to panel.
     * @param p1 The Panel to be modified.
     */
    private void PlaceThingsOnP1(JPanel p1){
        p1.setLayout(null);
        thisMovie = moviePage.getSearchedMovie();
        Border bb = BorderFactory.createLineBorder(Color.BLACK,1);
        Border b = BorderFactory.createTitledBorder(bb,textPresenter.printText("Add Review"), TitledBorder.LEADING, TitledBorder.TOP, font2);
        p1.setBounds(20,20, 840,720);
        p1.setBorder(b);
        writeReview = new JTextArea();
        JButton finishWrite = new JButton();
        JButton giveUPWrite = new JButton();
        writeReview.setBounds(20, 40, 800, 600);
        writeReview.setLineWrap(true);
        writeReview.setWrapStyleWord(true);
        writeReview.setText(textPresenter.printText("Enter your review here..."));
        finishWrite.setText(textPresenter.printText("Done with my review!"));
        giveUPWrite.setText(textPresenter.printText("Nah, maybe next time!"));
        finishWrite.setBounds(30, 650, 370, 50);
        giveUPWrite.setBounds(440, 650, 370, 50);
        finishWrite.addActionListener(e -> OnFinishButtonClick());
        giveUPWrite.addActionListener(e -> OnGiveUpButtonClick());
        p1.add(giveUPWrite);
        p1.add(finishWrite);
        p1.add(writeReview);
    }

    /**
     * This is the action when the user press the Finish button.
     */
    private void OnFinishButtonClick(){
        InstanceMain.getNormalCMovie().writeReview(thisMovie, writeReview.getText());
        nextView(new MoviePage(this), true);
    }

    /**
     *The getter function for getting the selected movie name.
     */
    public String getMovieSelected(){
        return moviePage.getSearchedMovie();
    }
    public TextPresenter getTextPresenter() { return this.textPresenter;}

    private void OnGiveUpButtonClick(){
        nextView(new MoviePage(this), true);
    }

    @Override
    protected void UpdateText() {

    }

    /**
     *The method in super class for generating a new frame.
     */
    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle(textPresenter.printText("Add Review"));
        frame.add(p1);
        frame.setSize(900,800);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }
}
