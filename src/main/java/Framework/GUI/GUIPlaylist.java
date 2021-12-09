package Framework.GUI;

import InterfaceAdapter.InstanceMain;
import InterfaceAdapter.Presenter.TextPresenter;

import javax.swing.*;

import java.util.ArrayList;

/**
 * This class functions as a page for demonstrating user's playlist.
 */
public class GUIPlaylist extends View{
    private final JPanel jPanel;
    private final GUIProfile guiProfile = (GUIProfile) previous;
    private final TextPresenter textPresenter = guiProfile.getTextPresenter();

    /**
     * The constructor for this class.
     * @param previous The View parameter from the super class.
     */
    public GUIPlaylist(View previous) {
        super(previous);
        jPanel = new JPanel();
        PlaceThingsOnPanel(jPanel);
    }

    /**
     * Add components to panel.
     * @param p The Jpanel to be modified.
     */
    @SuppressWarnings("unchecked")
    private void PlaceThingsOnPanel(JPanel p){
        p.setLayout(null);

        ArrayList<String> pl1 = (ArrayList<String>) InstanceMain.getNormalCUser().profilePage(userName)[6];
        Object[] pl2 = pl1.toArray();

        JList<Object> jList = new JList<>(pl2);
        JButton returnButton = new JButton(textPresenter.printText("Back"));

        p.setBounds(0,0,500,500);
        jList.setBounds(10,10,400,400);
        returnButton.setBounds(180,450,100,50);

        returnButton.addActionListener(actionEvent -> OnReturnClick());

        p.add(jList);
        p.add(returnButton);
    }

    /**
     * This is the action when the user press the Return button.
     */
    public void OnReturnClick(){
        this.getFrame().dispose();
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
        frame.setTitle(textPresenter.printText("Playlist"));
        frame.add(jPanel);
        frame.setSize(440,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }
}


