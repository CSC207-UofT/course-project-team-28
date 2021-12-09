package Framework.GUI;

import InterfaceAdapter.InstanceMain;

import javax.swing.*;

/**
 * Construct the Review Page.
 */
public class ReviewPage extends View{

    private final JPanel panel1;
    private JLabel numCoin;
    private final MoviePage moviePage = (MoviePage) previous;

    /**
     * Constructor of this class
     */
    public ReviewPage(View previous){
        super(previous);
        panel1 = new JPanel();
        PlaceThingsOnP1(panel1);
    }

    /**
     * Place everything needed on p1
     * @param p1 the panel to be put on the page
     */
    private void PlaceThingsOnP1(JPanel p1){
        Object[] selectedReview = InstanceMain.getNormalCMovie().getReviewInfo(moviePage.getSelectedReview());
        JLabel reviewer = new JLabel();
        numCoin = new JLabel();
        JButton addCoins = new JButton();

        reviewer.setText("Reviewer: " + selectedReview[0]);
        numCoin.setText("Number of Coins: " + selectedReview[3]);
        addCoins.setText("Give Coins for this Review");

        p1.setLayout(null);
        p1.setBounds(0,0,300,300);
        reviewer.setBounds(10,10,200,30);
        numCoin.setBounds(10,50,200,30);
        addCoins.setBounds(10,100,200,50);

        addCoins.addActionListener(actionEvent -> OnAddButtonClick());

        p1.add(reviewer);
        p1.add(numCoin);
        p1.add(addCoins);
    }

    /**
     * the button to give coin to review
     */
    private void OnAddButtonClick() {
        InstanceMain.getNormalCCoin().giveCoinToRev(moviePage.getSelectedReview());
        UpdateText();
    }

    /**
     * Inherited method, to update the number of coins dynamically.
     */
    @Override
    protected void UpdateText() {
        int result = (Integer) InstanceMain.getNormalCMovie().getReviewInfo(moviePage.getSelectedReview())[3];
        numCoin.setText("Number of Coins: " + result);
    }

    /**
     * Inherited class.
     * @return the frame of review page.
     */
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
