import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class Header extends JPanel {

    Main frame;
    private Main.colorEnum colorScheme;

    ImageIcon homeIcon;
    JButton homeButton;


    public Header(Main frame){
        this.frame = frame;
        colorScheme = Main.getColorScheme();

        setBackground(colorScheme.getHeaderColor());
        setBorder(BorderFactory.createMatteBorder(0,0,5,0, (colorScheme.getSecondaryColor() == Main.colorEnum.darkMode.getSecondaryColor())? null : colorScheme.getSecondaryColor()));
        setLocation(0,0);
        setLayout(new GridLayout(1,5));
        setPreferredSize(new Dimension(1920,180));
        setMaximumSize(new Dimension(1920,180));

        try {

                InputStream imageSource = Login.class.getResourceAsStream("Assets/thermometer.png");
                imageSource = Login.class.getResourceAsStream("Assets/homeLight128.png");
                Image sensorImage = ImageIO.read(imageSource);
                homeIcon = new ImageIcon(sensorImage);

        }
        catch (Exception e){
            e.printStackTrace();
        }


        homeButton = new JButton();
        homeButton.setIcon(homeIcon);
        homeButton.setPreferredSize(new Dimension(384,180));
        homeButton.setMaximumSize(new Dimension(384,180));

        add(homeButton);
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());



    }
}
