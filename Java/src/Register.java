import javax.swing.*;
import java.awt.*;

public class Register extends JDialog {

    public Register(JFrame frame, boolean modal){
        super(frame, modal);
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width, r.height);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new GridLayout(6, 1));

    }
}
