package Framework.GUI;

import InterfaceAdapter.InstanceMain;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddReview extends View{
    private JPanel p1;
    private String thisMovie;
    private JTextArea writeReview;
    private MoviePage moviePage;
    Font font2 = new Font("SansSerif", Font.PLAIN, 20);

    public AddReview(View previous) {
        super(previous);
        p1 = new JPanel();
        PlaceThingsOnP1(p1);

    }

    private void PlaceThingsOnP1(JPanel p1){
        p1.setLayout(null);
        Border bb = BorderFactory.createLineBorder(Color.BLACK,1);
        Border b = BorderFactory.createTitledBorder(bb,"Add Review", TitledBorder.LEADING, TitledBorder.TOP, font2);
        p1.setBounds(20,20, 840,720);
        p1.setBorder(b);
        moviePage = (MoviePage) previous;
        thisMovie = moviePage.getSearchedMovie();
        writeReview = new JTextArea();
        JButton finishWrite = new JButton();
        JButton giveUPWrite = new JButton();
        writeReview.setBounds(20, 40, 800, 600);
        writeReview.setLineWrap(true);
        writeReview.setWrapStyleWord(true);
        writeReview.setText("Enter your review here...");
        finishWrite.setText("Done with my review!");
        giveUPWrite.setText("Nah, maybe next time!");
        finishWrite.setBounds(30, 650, 370, 50);
        giveUPWrite.setBounds(440, 650, 370, 50);
        finishWrite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnFinishButtonClick(e);
            }
        });
        giveUPWrite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnGiveUpButtonClick(e);
            }
        });
        p1.add(giveUPWrite);
        p1.add(finishWrite);
        p1.add(writeReview);
    }

    private void OnFinishButtonClick(ActionEvent e){
        InstanceMain.getNormalCMovie().writeReview(thisMovie, writeReview.getText());
        nextView(new MoviePage(this), true);
    }

    public String getMovieSelected(){
        return moviePage.getSearchedMovie();
    }

    private void OnGiveUpButtonClick(ActionEvent e){
        nextView(new MoviePage(this), true);
    }

    @Override
    protected void UpdateText() {

    }

    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle("TEReview");
        frame.add(p1);
        frame.setSize(900,800);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }
}
