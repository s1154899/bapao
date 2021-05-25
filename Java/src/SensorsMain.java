

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SensorsMain extends JPanel {


    Header header;

    public SensorsMain(){


        setLayout(new GridBagLayout());
        setBackground(Main.colorScheme.getPrimaryColor());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.001f;
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
        gcThird.weighty = 0.001f;
        gcThird.gridy = 2;
        gcThird.gridwidth = 3;
        gcThird.gridheight = 1;

        header = new Header();
        header.setPreferredSize(new Dimension(1920,128));
        header.setMaximumSize(new Dimension(1920,128));
        header.setMinimumSize(new Dimension(0,128));
        add(header, gc);

        header.homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeThis();
            }
        });

        JLabel center = new JLabel();
        add(center, gcSecond);

        SensorsFooter sensorsFooterPanel = new SensorsFooter();
        sensorsFooterPanel.setPreferredSize(new Dimension(1920,30));
        sensorsFooterPanel.setMaximumSize(new Dimension(1920,30));
        sensorsFooterPanel.setMinimumSize(new Dimension(0,30));
        add(sensorsFooterPanel, gcThird);

        center.setForeground(Main.colorScheme.getDetailColor());
    }
    public void removeThis(){
        Main.mainFrame.remove(this);
        Main.mainFrame.returnHome();
        Main.mainFrame.revalidate();
        Main.mainFrame.repaint();
    }

    public void changeColor(){
        setBackground(Main.colorScheme.getPrimaryColor());
        setForeground(Main.colorScheme.getDetailColor());

        for (Component component : getComponents()){
            component.setForeground(Main.colorScheme.getDetailColor());
            if (component instanceof SensorsFooter){
                for (Component component1 : ((SensorsFooter) component).getComponents()){
                    Box box = (Box) component1;
                    JButton button = (JButton) box.getComponent(0);
                    button.setForeground(Main.colorScheme.getDetailColor());
                    button.setBorder(new LineBorder(Main.colorScheme.getBorderColor()));
                    button.setBackground(Main.colorScheme.getSecondaryColor());
                }
            }
        }
    }

}
