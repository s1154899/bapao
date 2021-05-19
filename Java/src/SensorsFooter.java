import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SensorsFooter extends JPanel{
    Main frame;
    Main.colorEnum colorScheme;

    public SensorsFooter(Main frame){
        this.frame = frame;
        this.colorScheme = Main.getColorScheme();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);
        setBackground(null);

        Box[] boxes = new Box[3];
        for (int i = 0; i < boxes.length; i++){
            boxes[i] = Box.createHorizontalBox();
            add(boxes[i]);
        }

        JButton results = new JButton();
        results.setFont(frame.getUsedFont().deriveFont(20f));
        results.setText("Results");
        results.setBackground(colorScheme.getSecondBackgroundColor());
        results.setPreferredSize(new Dimension(640, 50));
        results.setMaximumSize(new Dimension(640, 50));
        boxes[0].add(results);

        JButton actions = new JButton();
        actions.setFont(frame.getUsedFont().deriveFont(20f));
        actions.setText("Actions");
        actions.setBackground(colorScheme.getSecondBackgroundColor());
        actions.setPreferredSize(new Dimension(640, 50));
        actions.setMaximumSize(new Dimension(640, 50));
        actions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("wot");
                JFrame f = new JFrame();
                f.setSize(500, 500);

                add(new ActionsMain());

                f.setVisible(true);
            }
        });
        boxes[1].add(actions);

        JButton sensors = new JButton();
        sensors.setFont(frame.getUsedFont().deriveFont(20f));
        sensors.setText("Sensors");
        sensors.setBackground(colorScheme.getSecondBackgroundColor());
        sensors.setPreferredSize(new Dimension(640, 50));
        sensors.setMaximumSize(new Dimension(640, 50));
        boxes[2].add(sensors);
    }
}
