import raspberry.RaspberryPi;
import raspberry.UploadedScripts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;

class SensorsWeergaveMain extends JPanel implements ActionListener {
    private Font usedFont;
    Main.colorEnum colorScheme;

    public SensorsWeergaveMain() {
        this.colorScheme = Main.getColorScheme();

        this.setBackground(colorScheme.getSecondaryColor());
        this.setLayout(new BorderLayout());
        try {
            usedFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Login.class.getResourceAsStream("Assets/Comfort.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setBackground(colorScheme.getPrimaryColor());

        JLabel jlRaspberryIp = new JLabel("Ip van raspberry pi is: ");
        jlRaspberryIp.setFont(usedFont.deriveFont(20f));
        jlRaspberryIp.setForeground(colorScheme.getDetailColor());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(jlRaspberryIp, c);

        JLabel jlLastTemperature = new JLabel("De laatste temperatuur van de sensor is: ");
        jlLastTemperature.setFont(usedFont.deriveFont(20f));
        jlLastTemperature.setForeground(colorScheme.getDetailColor());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        add(jlLastTemperature, c);

        JLabel jlLastTemperatureDate = new JLabel("De laatste temperatuur is opgehaald op: ");
        jlLastTemperatureDate.setFont(usedFont.deriveFont(20f));
        jlLastTemperatureDate.setForeground(colorScheme.getDetailColor());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        add(jlLastTemperatureDate, c);

        JLabel jlArduinoAddonId = new JLabel("Arduino Addon ID: ");
        jlArduinoAddonId.setFont(usedFont.deriveFont(20f));
        jlArduinoAddonId.setForeground(colorScheme.getDetailColor());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        add(jlArduinoAddonId, c);

        JLabel jlLastLight = new JLabel("Het laatste lichtsensor resultaat is: ");
        jlLastLight.setFont(usedFont.deriveFont(20f));
        jlLastLight.setForeground(colorScheme.getDetailColor());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        add(jlLastLight, c);

        JLabel jlLastLightDate = new JLabel("Het laatste lichtsensor resultaat is opgehaald op: ");
        jlLastLightDate.setFont(usedFont.deriveFont(20f));
        jlLastLightDate.setForeground(colorScheme.getDetailColor());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        add(jlLastLightDate, c);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
