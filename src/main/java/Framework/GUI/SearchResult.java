package Framework.GUI;

import InterfaceAdapter.Search.Search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class SearchResult extends View{
    private final JPanel panel1;
    private final GUIProfile guiProfile;
    private JList searchList;
    private String searchText;
    Font font1 = new Font("SansSerif", Font.BOLD, 50);

    public SearchResult(View previous) {
        super(previous);
        guiProfile = (GUIProfile) previous;
        panel1 = new JPanel();
        addComponentOnPanel(panel1);
    }

    private void addComponentOnPanel(JPanel p){
        p.setLayout(null);
        p.setBounds(0, 0, 700, 700);
        searchText = guiProfile.getSearchInput();
        List lst = Search.suggestionSearch(searchText);
        searchList = new JList(lst.toArray());
        searchList.setBounds(150, 120, 400, 400);
        searchList.setFont(font1);
        searchList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OnMovieNameClick(e);
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
        p.add(searchList);
    }

    private void OnMovieNameClick(MouseEvent e){
        nextView(new GUIPlaylist(this), false); //!!!!!!!
    }

    @Override
    protected void UpdateText() {

    }
    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle("Search Result");
        frame.setSize(700,700);
        frame.add(panel1);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }
}
