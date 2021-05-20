import javax.swing.*;
import java.awt.*;

public class SensorsMain extends JPanel {
    Main frame;
    Main.colorEnum colorScheme;

    public SensorsMain(Main frame, boolean modal){
        this.frame = frame;
        this.colorScheme = Main.getColorScheme();

        setLayout(new GridBagLayout());
        setBackground(colorScheme.getPrimaryColor());

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

        Header headPanel = new Header(frame, this);
        headPanel.setPreferredSize(new Dimension(1920,128));
        headPanel.setMaximumSize(new Dimension(1920,128));
        add(headPanel, gc);

        JLabel center = new JLabel();
        add(center, gcSecond);

        SensorsFooter sensorsFooterPanel = new SensorsFooter(frame);
        sensorsFooterPanel.setPreferredSize(new Dimension(1920,30));
        sensorsFooterPanel.setMaximumSize(new Dimension(1920,30));
        add(sensorsFooterPanel, gcThird);

    }

}
