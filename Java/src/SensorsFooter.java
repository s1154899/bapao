

import org.jfree.ui.RefineryUtilities;
import raspberry.RaspberryPi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


public class SensorsFooter extends JPanel{

    private SensorsWeergaveMain sensorsWeergaveMain;
    private JPanel sensors;
    private JDialog sensorsDialog;

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
                sensorsDialog = new JDialog(Main.mainFrame,true);

                sensors = new JPanel();
                sensors.setLayout(new BoxLayout(sensors,BoxLayout.Y_AXIS));

                JScrollPane scrollFrame = new JScrollPane(sensors);
                sensors.setAutoscrolls(true);
                scrollFrame.setPreferredSize(new Dimension( getWidth() / 3,300));
                sensorsDialog.add(scrollFrame);

                for (RaspberryPi pi : RaspberryPi.connectedPis) {
                    String[] types = new String[]{"Temperature", "humidity", "pressure","licht"};
                    for (String type : types) {
                        try {
                            int[] results = pi.databaseCon.GetResults(10,type);
                            String[] stamps = pi.databaseCon.GetTimeStamps(10,type);

                           sensors.add(new Graphs().lineGraph(results,stamps,type,"tijd","waardens"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }


                    }
                }
                Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                sensorsDialog.setSize(r.width, r.height);


                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("test");
                        sensors.removeAll();

                        for (RaspberryPi pi : RaspberryPi.connectedPis) {
                            String[] types = new String[]{"Temperature", "humidity", "pressure","licht"};
                            for (String type : types) {
                                try {
                                    int[] results = pi.databaseCon.GetResults(10,type);
                                    String[] stamps = pi.databaseCon.GetTimeStamps(10,type);

                                    sensors.add(new Graphs().lineGraph(results,stamps,type,"tijd","waardens"));
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }


                            }
                        }
                        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                        sensorsDialog.setSize(r.width, r.height);



                        sensors.repaint();
                        sensors.revalidate();
                    }

                });

                timer.setRepeats(true);
                timer.start();
                sensorsDialog.setVisible(true);
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

                sensorsWeergaveMain = new SensorsWeergaveMain();
                dialog.add(sensorsWeergaveMain);




                Timer timer = new Timer(100 * 60, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    dialog.remove(sensorsWeergaveMain);
                    sensorsWeergaveMain = new SensorsWeergaveMain();
                    dialog.add(sensorsWeergaveMain);
                    dialog.repaint();
                    dialog.revalidate();
                    }
                });
                timer.setRepeats(true);
                timer.start();


                dialog.setVisible(true);
            }
        });
        boxes[2].add(sensors);
    }
    public void removeThis(){

//        frame.remove(this);
    }
}
