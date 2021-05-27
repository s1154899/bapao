

import org.jfree.ui.RefineryUtilities;
import raspberry.RaspberryPi;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


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
        results.setBackground(Main.colorScheme.getSecondBackgroundColor());
        results.setPreferredSize(new Dimension(640, 50));
        results.setMaximumSize(new Dimension(640, 50));
        results.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(Main.mainFrame,true);

                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

                JScrollPane scrollFrame = new JScrollPane(mainPanel);
                mainPanel.setAutoscrolls(true);
                scrollFrame.setPreferredSize(new Dimension( getWidth() / 3,300));
                dialog.add(scrollFrame);

                for (RaspberryPi pi : RaspberryPi.connectedPis) {
                    String[] types = new String[]{"Temperature", "humidity", "pressure"};
                    for (String type : types) {
                        try {
                            int[] results = pi.databaseCon.GetResults(30,type);
                            String[] stamps = pi.databaseCon.GetTimeStamps(30,type);

                           mainPanel.add(new Graphs().lineGraph(results,stamps,type,"tijd","waardens"));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }


                    }
                }
                Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                dialog.setSize(r.width, r.height);

                dialog.setVisible(true);

                Timer timer = new Timer(1000 * 60, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        remove(mainPanel);
                        JPanel mainPanel = new JPanel();
                        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

                        JScrollPane scrollFrame = new JScrollPane(mainPanel);
                        mainPanel.setAutoscrolls(true);
                        scrollFrame.setPreferredSize(new Dimension( getWidth() / 3,300));
                        dialog.add(scrollFrame);

                        for (RaspberryPi pi : RaspberryPi.connectedPis) {
                            String[] types = new String[]{"Temperature", "humidity", "pressure"};
                            for (String type : types) {
                                try {
                                    int[] results = pi.databaseCon.GetResults(10,type);
                                    String[] stamps = pi.databaseCon.GetTimeStamps(10,type);

                                    mainPanel.add(new Graphs().lineGraph(results,stamps,type,"tijd","waardens"));
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }


                            }
                        }
                        repaint();
                        revalidate();

                    }

                });
            timer.start();
            }
        });
        boxes[0].add(results);

        JButton actions = new JButton();
//        actions.setFont(getUsedFont().deriveFont(20f));
        actions.setText("Actions");
        actions.setBackground(Main.colorScheme.getSecondBackgroundColor());
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
        sensors.setBackground(Main.colorScheme.getSecondBackgroundColor());
        sensors.setPreferredSize(new Dimension(640, 50));
        sensors.setMaximumSize(new Dimension(640, 50));
        sensors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(Main.mainFrame,true);

                Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                dialog.setSize(r.width,r.height);
                dialog.add(new SensorsWeergaveMain());

                dialog.setVisible(true);
            }
        });
        boxes[2].add(sensors);

        results.setForeground(Main.colorScheme.getDetailColor());
        results.setBorder(new LineBorder(Main.colorScheme.getBorderColor()));
        results.setBackground(Main.colorScheme.getSecondaryColor());

        actions.setForeground(Main.colorScheme.getDetailColor());
        actions.setBorder(new LineBorder(Main.colorScheme.getBorderColor()));
        actions.setBackground(Main.colorScheme.getSecondaryColor());

        sensors.setForeground(Main.colorScheme.getDetailColor());
        sensors.setBorder(new LineBorder(Main.colorScheme.getBorderColor()));
        sensors.setBackground(Main.colorScheme.getSecondaryColor());
    }
    public void removeThis(){

//        frame.remove(this);
    }
}
