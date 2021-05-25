import raspberry.DatabaseCon;
import raspberry.RaspberryPi;
import raspberry.UploadedScripts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TimerTask;

public class SensorsWeergaveMain extends JPanel implements ActionListener {
    private Font usedFont;


    public SensorsWeergaveMain() {

        this.setBackground(Main.colorScheme.getSecondaryColor());
        this.setLayout(new BorderLayout());
        try {
            usedFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Login.class.getResourceAsStream("Assets/Comfort.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }



        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JScrollPane scrollFrame = new JScrollPane(mainPanel);
        mainPanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( getWidth() / 3,300));
        add(scrollFrame);

        for(RaspberryPi pi : RaspberryPi.connectedPis) {

            ArrayList<String[]> results= null;
            try {
                results = pi.databaseCon.GetNieuwResults();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            JPanel RaspPanel = new JPanel();

            RaspPanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            setBackground(Main.colorScheme.getPrimaryColor());

            JLabel jlRaspberryIp = new JLabel("Ip van raspberry pi is: "+ pi.getHost());
            jlRaspberryIp.setFont(usedFont.deriveFont(20f));
            jlRaspberryIp.setForeground(Main.colorScheme.getDetailColor());
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            RaspPanel.add(jlRaspberryIp, c);

            for (int i = 0; i < results.size() ; i++) {

                JLabel jlLastTemperature = new JLabel("De laatste "+results.get(i)[0]+" van de sensor is: "+results.get(i)[1]);
                jlLastTemperature.setFont(usedFont.deriveFont(20f));
                jlLastTemperature.setForeground(Main.colorScheme.getDetailColor());
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = i + 1;
                RaspPanel.add(jlLastTemperature, c);
            }

            mainPanel.add(RaspPanel);
        }


        Timer timer = new Timer(100 * 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(scrollFrame);
                refresh();
                repaint();
                revalidate();
            }
        });
        timer.setRepeats(true);
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    public void refresh(){


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JScrollPane scrollFrame = new JScrollPane(mainPanel);
        mainPanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( getWidth() / 3,300));
        add(scrollFrame);

        for(RaspberryPi pi : RaspberryPi.connectedPis) {

            ArrayList<String[]> results= null;
            try {
                results = pi.databaseCon.GetNieuwResults();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            JPanel RaspPanel = new JPanel();

            RaspPanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            setBackground(Main.colorScheme.getPrimaryColor());

            JLabel jlRaspberryIp = new JLabel("Ip van raspberry pi is: "+ pi.getHost());
            jlRaspberryIp.setFont(usedFont.deriveFont(20f));
            jlRaspberryIp.setForeground(Main.colorScheme.getDetailColor());
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            RaspPanel.add(jlRaspberryIp, c);

            for (int i = 0; i < results.size() ; i++) {

                JLabel jlLastTemperature = new JLabel("De laatste "+results.get(i)[0]+" van de sensor is: "+results.get(i)[1]);
                jlLastTemperature.setFont(usedFont.deriveFont(20f));
                jlLastTemperature.setForeground(Main.colorScheme.getDetailColor());
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = i + 1;
                RaspPanel.add(jlLastTemperature, c);
            }

            mainPanel.add(RaspPanel);
        }
    }
}
