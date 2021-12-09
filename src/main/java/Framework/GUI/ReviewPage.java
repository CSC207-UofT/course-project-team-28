package Framework.GUI;

import InterfaceAdapter.InstanceMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviewPage extends View{

    private final JPanel panel1;
    private JLabel reviewer;
    private Object[] selectedReview;
    private JLabel numCoin;
    private JButton addCoins;
    private MoviePage moviePage = (MoviePage) previous;
    Font font1 = new Font("SansSerif", Font.PLAIN, 20);

    /**
     * Constructor of this class
     */
    public ReviewPage(View previous){
        super(previous);
        panel1 = new JPanel();
        PlaceThingsOnP1(panel1);
    }

    private void PlaceThingsOnP1(JPanel p1){
        selectedReview = InstanceMain.getNormalCMovie().getReviewInfo(moviePage.getSelectedReview());
        reviewer = new JLabel();
        numCoin = new JLabel();
        addCoins = new JButton();

        reviewer.setText("Reviewer: " + selectedReview[0]);
        numCoin.setText("Number of Coins: " + selectedReview[3]);
        addCoins.setText("Give Coins for this Review");

        p1.setLayout(null);
        p1.setBounds(0,0,300,300);
        reviewer.setBounds(10,10,200,30);
        numCoin.setBounds(10,50,200,30);
        addCoins.setBounds(10,100,200,50);

        addCoins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OnAddButtonClick(actionEvent);
            }
        });

        p1.add(reviewer);
        p1.add(numCoin);
        p1.add(addCoins);
    }

    private void OnAddButtonClick(ActionEvent e) {
        InstanceMain.getNormalCCoin().giveCoinToRev(moviePage.getSelectedReview());
        UpdateText();
    }

    @Override
    protected void UpdateText() {
        int result = (Integer) InstanceMain.getNormalCMovie().getReviewInfo(moviePage.getSelectedReview())[3];
        numCoin.setText("Number of Coins: " + result);
    }


    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle("Give Coins to the Selected Review");
        frame.add(panel1);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }


}
