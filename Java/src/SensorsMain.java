

import org.jfree.ui.HorizontalAlignment;
import raspberry.RaspberryPi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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

        JPanel center = new JPanel();
        center.setBackground(null);
        center.setBorder(new EmptyBorder(0,0,0,0));
        center.setOpaque(false);
        //center.setPreferredSize(new Dimension(500,300));
        center.setMinimumSize(new Dimension(1100,255));
        center.setLayout(new GridLayout(1,4));
        RoundButton results = new RoundButton(250,250,Main.colorScheme.getSecondaryColor(), Main.colorScheme.getDetailColor(), "Assets/Icons/results.png");
        results.setBorder(new EmptyBorder(0,150,0,150));
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
                    String[] types = new String[]{"temperature", "humidity", "pressure","light"};
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
        center.add(results);
        RoundButton actions = new RoundButton(250,250,Main.colorScheme.getSecondaryColor(), Main.colorScheme.getDetailColor(), "Assets/Icons/actions.png");
        actions.setBorder(new EmptyBorder(0,150,0,150));
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
        center.add(actions);
        RoundButton sensors = new RoundButton(250,250,Main.colorScheme.getSecondaryColor(), Main.colorScheme.getDetailColor(), "Assets/Icons/sensors.png");
        sensors.setBorder(new EmptyBorder(0,150,0,150));
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
        center.add(sensors);
        add(center, gcSecond);

        JPanel sensorsFooterPanel = new JPanel();
        sensorsFooterPanel.setPreferredSize(new Dimension(1920,150));
        sensorsFooterPanel.setMaximumSize(new Dimension(1920,150));
        sensorsFooterPanel.setMinimumSize(new Dimension(0,150));
        sensorsFooterPanel.setOpaque(false);
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
            if (component instanceof JPanel){
                try {
                    for (Component component1 : ((JPanel) component).getComponents()) {
                        RoundButton roundButton = (RoundButton) component1;
                        roundButton.borderColor = Main.colorScheme.getDetailColor();
                        roundButton.color = Main.colorScheme.getSecondaryColor();
                    }
                }
                catch (Exception e){

                }
            }
        }
    }

}
