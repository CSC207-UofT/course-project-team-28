package Framework.GUI;

import InterfaceAdapter.Search.Search;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SearchResult extends View{
    private final JPanel panel1;
    private final GUIProfile guiProfile;
    private JList searchList;
    private String searchText;

    public SearchResult(View previous) {
        super(previous);
        guiProfile = new GUIProfile(this);
        panel1 = new JPanel();
        searchList = new JList();
//        searchText = "";
        addComponentOnPanel(panel1);
    }

    private void addComponentOnPanel(JPanel p){
        BoxLayout boxLayout = new BoxLayout(p, BoxLayout.Y_AXIS);
        p.setLayout(boxLayout);
        searchText = guiProfile.getSearchInput();

//        if (searchText.equals("")){
//            JOptionPane.showMessageDialog(null,"Please enter a movie name","!",JOptionPane.PLAIN_MESSAGE);
//            this.getFrame().dispose();
//        } else {
        List<String> pl1 = Search.suggestionSearch(searchText);
        Object[] pl2 = pl1.toArray();
        searchList = new JList<>(pl2);
        p.add(searchList);
//        }

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
