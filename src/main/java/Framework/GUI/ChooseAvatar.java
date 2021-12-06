package Framework.GUI;

import javax.swing.*;

public class ChooseAvatar extends View{
    private JPanel jPanel;
    private JList jList;
    String[] avatarName = {"water","flower","galaxy", "color","moon","paint"};

    public ChooseAvatar(View p){
        super(p);
        jPanel = new JPanel();
        PlaceThingsOnPanel(jPanel);
    }

    private void PlaceThingsOnPanel(JPanel p){
        p.setLayout(null);
        jList = new JList(avatarName);
        p.setBounds(0,0,500,320);
        jList.setBounds(20,20,100,150);
        p.add(jList);
    }

    @Override
    protected void UpdateText() {

    }
    @Override
    public JFrame getFrame() {
        JFrame frame = super.getFrame();
        frame.setTitle("Choose your Avatar");
        frame.add(jPanel);
        frame.setSize(500,320);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return frame;
    }
}
