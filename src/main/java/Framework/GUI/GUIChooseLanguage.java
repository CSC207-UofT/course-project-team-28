package Framework.GUI;

import Framework.DataAccess.WriteMovie;
import Framework.DataAccess.WriteReview;
import Framework.DataAccess.WriteUser;
import InterfaceAdapter.InstanceMain;
import InterfaceAdapter.Presenter.TextPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This is the first page of our program, which allows the user to select language (English or Chinese).
 */
public class GUIChooseLanguage extends View{
    private String Language;
    private final JButton english;
    private final JButton mandarin;
    private final JPanel p1;

    /**
     * The constructor of this class.
     * @param previous The parameter from the super class View. It is used to switch pages.
     */

    public GUIChooseLanguage(View previous){
        super(previous);
        p1 = new JPanel();
        english = new JButton();
        mandarin = new JButton();
        PlaceThingsOnP1(p1);
    }

    /**
     * Add components to panel.
     * @param p1 The Jpanel to be modified.
     */
    private void PlaceThingsOnP1(JPanel p1){
        p1.setLayout(null);
        p1.setBounds(20, 20, 160, 360);
        english.setBounds(30, 70, 120, 60);
        mandarin.setBounds(30, 200, 120, 60);
        english.setText("English");
        mandarin.setText("中文");
        english.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OnClickButtonEng(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        mandarin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OnClickButtonMan(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        p1.add(english);
        p1.add(mandarin);
    }

    /**
     * This is the action when the user press the 'English' button.
     * @param e An action for the click on the button.
     */
    private void OnClickButtonEng(ActionEvent e) throws IOException {
        Language = "ENGLISH";
        GUIMain g = new GUIMain(this);
        nextView(g, false);
        this.getFrame().dispose();
    }
    /**
     * This is the action when the user press the '中文' button.
     * @param e An action for the click on the button.
     */
    private void OnClickButtonMan(ActionEvent e) throws IOException {
        Language = "MANDARIN";
        GUIMain g = new GUIMain(this);
        nextView(g, false);
        this.getFrame().dispose();
    }

    /**
     * The getter method for getting the selected language.
     */
    public String getLanguage(){
        return this.Language;
    }

    /**
     * The getter method for getting the text from the presenter layer.
     */
    public TextPresenter getTextPresenter() throws IOException {
        TextPresenter textPresenter = new TextPresenter(this.Language);
        return textPresenter;
    }

    /**
     * The method from supper class.
     */
    @Override
    protected void UpdateText() {

    }

    /**
     *The method in super class for generating a new frame.
     */
    @Override
    protected JFrame getFrame() {
        JFrame jFrame = super.getFrame();
        jFrame.setTitle("Our App");
        jFrame.setSize(200, 400);
        jFrame.setVisible(true);
        jFrame.add(p1);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return jFrame;
    }

    /**
     *This is the main method.
     */
    public static void main(String[] args) throws IOException {
        InstanceMain.setClearInstance();
        WriteUser writeUser = new WriteUser();
        WriteReview writeReview = new WriteReview();
        WriteMovie writeMovie = new WriteMovie();
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);

        View gui = new GUIChooseLanguage(null);
        gui.nextView(gui,false);
    }
}
