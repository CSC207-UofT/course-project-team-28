package Framework.GUI;

import InterfaceAdapter.Presenter.TextPresenter;
import InterfaceAdapter.Search.Search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
  Construct the page for search result.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class SearchResult extends View{
    private final JPanel panel1;
    private final GUIProfile guiProfile = (GUIProfile) previous;
    private JList searchList;
    private final TextPresenter textPresenter = guiProfile.getTextPresenter();
    Font font1 = new Font("SansSerif", Font.BOLD, 50);

    /**
     * the constructor
     * @param previous the previous page.
     */
    public SearchResult(View previous) {
        super(previous);
        panel1 = new JPanel();
        addComponentOnPanel(panel1);
    }

    /**
     * add everything to panel
     * @param p the panel
     */
    private void addComponentOnPanel(JPanel p){
        p.setLayout(null);
        p.setBounds(0, 0, 700, 700);
        String searchText = guiProfile.getSearchInput();
        List lst = Search.suggestionSearch(searchText);
        searchList = new JList(lst.toArray());
        searchList.setBounds(150, 120, 400, 400);
        searchList.setFont(font1);
        searchList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OnMovieNameClick();
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

    /**
     * @return the selected movie.
     */
    public String getMovieSelected(){
        return (String) searchList.getSelectedValue();
    }

    /**
     * The inherited method
     * @return the text, depend on the language chosen by user.
     */
    public TextPresenter getTextPresenter() { return this.textPresenter;}

    /**
     * click the movie name to switch to the movie page.
     */
    private void OnMovieNameClick(){
        nextView(new MoviePage(this), true);
    }

    /**
     * Inherited method, to update text.
     */
    @Override
    protected void UpdateText() {
    }

    /**
     * Inherited method, to get the frame
     * @return the frame of Search Results.
     */
    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle(textPresenter.printText("Search Result"));
        frame.setSize(700,700);
        frame.add(panel1);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }
}
