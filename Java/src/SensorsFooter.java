

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.ui.RefineryUtilities;

public class SensorsFooter extends JPanel{


    public SensorsFooter(){


        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);
        setBackground(null);

        Box[] boxes = new Box[3];
        for (int i = 0; i < boxes.length; i++){
            boxes[i] = Box.createHorizontalBox();
            add(boxes[i]);
        }

        JButton results = new JButton();
//        results.setFont(getUsedFont().deriveFont(20f));
        results.setText("Results");
        results.setBackground(ColorScheme.getSecondBackgroundColor());
        results.setPreferredSize(new Dimension(640, 50));
        results.setMaximumSize(new Dimension(640, 50));
        results.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemperatureChart chart = new TemperatureChart(
                        "Temperature",
                        "Temperature");

                chart.pack();
                RefineryUtilities.centerFrameOnScreen(chart);
                RefineryUtilities.positionFrameOnScreen(chart, 0, 1);
                chart.setVisible(true);

                PressureChart chart2 = new PressureChart(
                        "Air Pressure",
                        "Air Pressure");
                chart2.pack();
                RefineryUtilities.centerFrameOnScreen(chart2);
                RefineryUtilities.positionFrameOnScreen(chart2, 0.6, 0);
                chart2.setVisible(true);

                HumidityChart chart3 = new HumidityChart(
                        "Humidity",
                        "Humidity");
                chart3.pack();
                RefineryUtilities.centerFrameOnScreen(chart3);
                RefineryUtilities.positionFrameOnScreen(chart3, 0, 0);
                chart3.setVisible(true);
            }
        });
        boxes[0].add(results);

        JButton actions = new JButton();
//        actions.setFont(getUsedFont().deriveFont(20f));
        actions.setText("Actions");
        actions.setBackground(ColorScheme.getSecondBackgroundColor());
        actions.setPreferredSize(new Dimension(640, 50));
        actions.setMaximumSize(new Dimension(640, 50));
        actions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(Main.mainFrame,true);
                dialog.add(new ActionsMain());
                Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                dialog.setSize(r.width,r.height);
                dialog.setVisible(true);






            }
        });
        boxes[1].add(actions);

        JButton sensors = new JButton();
//        sensors.setFont(getUsedFont().deriveFont(20f));
        sensors.setText("Sensors");
        sensors.setBackground(ColorScheme.getSecondBackgroundColor());
        sensors.setPreferredSize(new Dimension(640, 50));
        sensors.setMaximumSize(new Dimension(640, 50));
        sensors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(Main.mainFrame,true);

                Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                dialog.setSize(r.width,r.height);

                dialog.setVisible(true);
            }
        });
        boxes[2].add(sensors);
    }
    public void removeThis(){

//        frame.remove(this);
    }
}
