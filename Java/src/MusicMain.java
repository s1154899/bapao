

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicMain extends JPanel {

    Header header;

    public MusicMain(){

        setLayout(new GridBagLayout());
        setBackground(Main.colorScheme.getPrimaryColor());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.05f;
        gc.gridy = 0;
        gc.gridwidth = 3;
        gc.gridheight = 1;

        GridBagConstraints gcSecond = new GridBagConstraints();
        gcSecond.anchor = GridBagConstraints.CENTER;
        gcSecond.gridx = 1;
        gcSecond.gridy = 1;
        gcSecond.weightx = 1;
        gcSecond.weighty = 0.8f;
        gcSecond.gridwidth = 1;
        gcSecond.gridheight = 1;

        GridBagConstraints gcThird = new GridBagConstraints();
        gcThird.fill = GridBagConstraints.BOTH;
        //gcSecond.anchor = GridBagConstraints.FIRST_LINE_START;
        gcThird.gridx = 0;
        gcThird.weightx = 1;
        gcThird.weighty = 0.01f;
        gcThird.gridy = 2;
        gcThird.gridwidth = 3;
        gcThird.gridheight = 1;

        header = new Header();
        header.setPreferredSize(new Dimension(1920,128));
        header.setMaximumSize(new Dimension(1920,128));

        add(header, gc);

        header.homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeThis();
            }
        });



        JLabel center = new JLabel("test");
        add(center, gcSecond);

        MusicFooter musicFooter = new MusicFooter();
        musicFooter.setPreferredSize(new Dimension(1920,30));
        musicFooter.setMaximumSize(new Dimension(1920,30));
        add(musicFooter, gcThird);
    }

    public void removeThis(){
        Main.mainFrame.remove(this);
        Main.mainFrame.returnHome();
        Main.mainFrame.revalidate();
        Main.mainFrame.repaint();
    }

    public void changeColor(){

    }


}
