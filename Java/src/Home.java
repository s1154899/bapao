import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;

public class Home extends JPanel {

    Main frame;
    Main.colorEnum colorScheme;

    JButton musicButton;
    JButton sensorButton;


    ImageIcon musicIcon;
    ImageIcon sensorIcon;

    public Home(Main frame, boolean modal){
        this.frame = frame;
        this.colorScheme = Main.getColorScheme();

        setBackground(colorScheme.getPrimaryColor());
        setLayout(new BorderLayout());

        musicButton = new JButton();
        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musicButton.setBackground(null);
                frame.showMusic();
                removeThis();
            }
        });

        sensorButton = new JButton();
        sensorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sensorButton.setBackground(null);
                frame.showStats();
                removeThis();
            }
        });

        try {
            InputStream imageSource = Login.class.getResourceAsStream("Assets/sensors.png");
            Image sensorImage = ImageIO.read(imageSource);
            sensorIcon = new ImageIcon(sensorImage);

            imageSource = Login.class.getResourceAsStream("Assets/music.png");
            Image musicImage = ImageIO.read(imageSource);
            musicIcon = new ImageIcon(musicImage);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        sensorButton.setIcon(sensorIcon);
        musicButton.setIcon(musicIcon);

        sensorButton.setBorder(new EmptyBorder(200,200,200,200));
        musicButton.setBorder(new EmptyBorder(200,200,200,200));

        sensorButton.setBackground(null);
        musicButton.setBackground(null);

        sensorButton.setFocusable(false);
        musicButton.setFocusable(false);

        sensorButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                sensorButton.setBackground(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                sensorButton.setBackground(null);
            }
        });

        musicButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                musicButton.setBackground(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                musicButton.setBackground(null);
            }
        });

        JPanel tempPanel = new JPanel();
        tempPanel.setBackground(null);
        tempPanel.setLayout(new GridLayout(1,2));
        tempPanel.setBorder(new EmptyBorder(200,200,200,200));
        tempPanel.add(musicButton);
        tempPanel.add(sensorButton);
        add(tempPanel, BorderLayout.CENTER);


    }

    public void removeThis(){
        frame.remove(this);
    }
}
