package Framework.GUI;

import InterfaceAdapter.InstanceMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class GUIPlaylist extends View{
    private final JPanel jPanel;
    private JList jList;

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
        JButton returnButton = new JButton("Back");

        p.setBounds(0,0,500,500);
        jList.setBounds(10,10,400,400);
        returnButton.setBounds(10,450,100,50);

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
        frame.setTitle("Playlist");
        frame.add(jPanel);
        frame.setSize(700,700);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }
}


