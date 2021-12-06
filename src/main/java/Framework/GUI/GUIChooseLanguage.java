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

public class GUIChooseLanguage extends View{
    private String Language;
    private JButton english;
    private JButton mandarin;
    private JPanel p1;

    public GUIChooseLanguage(View previous){
        super(previous);
        p1 = new JPanel();
        english = new JButton();
        mandarin = new JButton();
        PlaceThingsOnP1(p1);
    }

    private void PlaceThingsOnP1(JPanel p1){
        p1.setLayout(null);
        p1.setBounds(20, 20, 160, 360);
        english.setBounds(130, 50, 120, 60);
        mandarin.setBounds(130, 120, 120, 60);
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

    private void OnClickButtonEng(ActionEvent e) throws IOException {
        Language = "ENGLISH";
        GUIMain g = new GUIMain(this);
        nextView(g, false);
        this.getFrame().dispose();
    }

    private void OnClickButtonMan(ActionEvent e) throws IOException {
        Language = "MANDARIN";
        GUIMain g = new GUIMain(this);
        nextView(g, false);
        this.getFrame().dispose();
    }

    public String getLanguage(){
        return this.Language;
    }

    public TextPresenter getTextPresenter() throws IOException {
        TextPresenter textPresenter = new TextPresenter(this.Language);
        return textPresenter;
    }


    @Override
    protected void UpdateText() {

    }

    @Override
    protected JFrame getFrame() {
        JFrame jFrame = super.getFrame();
        jFrame.setTitle("Our App");
        jFrame.setSize(400, 300);
        jFrame.setVisible(true);
        jFrame.add(p1);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return jFrame;
    }

    public static void main(String[] args) throws IOException {
        WriteUser writeUser = new WriteUser();
        WriteReview writeReview = new WriteReview();
        WriteMovie writeMovie = new WriteMovie();
        InstanceMain.setWriteFileClass(writeUser, writeMovie, writeReview);

        View gui = new GUIChooseLanguage(null);
        gui.nextView(gui,false);
    }
}
