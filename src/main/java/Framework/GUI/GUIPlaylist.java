package Framework.GUI;

import InterfaceAdapter.InstanceMain;
import InterfaceAdapter.Presenter.TextPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class GUIPlaylist extends View{
    private final JPanel jPanel;
    private JList jList;
    private final GUIProfile guiProfile = (GUIProfile) previous;
    private final TextPresenter textPresenter = guiProfile.getTextPresenter();

    public GUIPlaylist(View previous) {
        super(previous);
        jPanel = new JPanel();
        PlaceThingsOnPanel(jPanel);
    }

    @SuppressWarnings("unchecked")
    private void PlaceThingsOnPanel(JPanel p){
        p.setLayout(null);

        ArrayList<String> pl1 = (ArrayList<String>) InstanceMain.getNormalCUser().profilePage(userName)[6];
        Object[] pl2 = pl1.toArray();

        jList = new JList<>(pl2);
        JButton returnButton = new JButton(textPresenter.printText("Back"));

        p.setBounds(0,0,500,500);
        jList.setBounds(10,10,400,400);
        returnButton.setBounds(180,450,100,50);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OnReturnClick(actionEvent);
            }
        });

        p.add(jList);
        p.add(returnButton);
    }

    public void OnReturnClick(ActionEvent e){
        this.getFrame().dispose();
    }

    @Override
    protected void UpdateText() {

    }

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


