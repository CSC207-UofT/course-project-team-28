package Framework.GUI;

import Framework.Presenter.PicPresenter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainPageMoviePanel extends View {
    private final JPanel panel1;

    public MainPageMoviePanel(View v) {
        super(v);
        panel1 = new JPanel();
        placeComponentOnPanel(panel1);
    }

    public void placeComponentOnPanel(JPanel p){
        p.setLayout(null);
        Border b = BorderFactory.createLineBorder(Color.BLACK, 1);
        p.setBorder(b);
        p.setBounds(40,40,300,300);
        ImageIcon icon1 = new ImageIcon(PicPresenter.getPic("5.jpg"));
        ImageIcon icon2 = new ImageIcon(PicPresenter.getPic("5.jpg"));
        ImageIcon icon3 = new ImageIcon(PicPresenter.getPic("5.jpg"));
        JLabel i1 = new JLabel("Pic to be added", icon1, JLabel.CENTER);
        JLabel i2 = new JLabel("Pic to be added", icon2, JLabel.CENTER);
        JLabel i3 = new JLabel("Pic to be added", icon3, JLabel.CENTER);
        i1.setBounds(20, 200, 300, 200);
        i2.setBounds(40, 200, 300, 200);
        i3.setBounds(60, 200, 300, 200);
        p.add(i1);
        p.add(i2);
        p.add(i3);
    }

    @Override
    protected void UpdateText() {

    }
}
