import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Home extends JPanel {

    Main frame;
    Main.colorEnum colorScheme;

    JButton musicButton;
    JButton sensorButton;

    ImageIcon musicIcon;
    ImageIcon sensorIcon;
    static boolean added;

    Header header;

    public Home(Main frame, boolean modal){
        added = true;
        this.frame = frame;
        this.colorScheme = Main.getColorScheme();
        //setSize(1920,1080);

        float screenWidthFactor = (float) (frame.getWidth() / GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth());
        float screenHeightFactor = (float) (frame.getHeight() / GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight());

        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setSize(1920,1080);


        setBackground(colorScheme.getPrimaryColor());
        setLayout(new GridBagLayout());

        musicButton = new JButton();
        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");
                musicButton.setBackground(null);
                frame.showMusic();
                removeThis();
                Home.added = false;
            }
        });

        sensorButton = new JButton();
        sensorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");
                sensorButton.setBackground(null);
                frame.showStats();
                removeThis();
                Home.added = false;
            }
        });

        try {
            InputStream imageSource = Login.class.getResourceAsStream("Assets/thermometer.png");
            imageSource = Login.class.getResourceAsStream("Assets/sensors.png");
            Image sensorImage = ImageIO.read(imageSource);
            sensorIcon = new ImageIcon(sensorImage);

            imageSource = Login.class.getResourceAsStream("Assets/playMusic.png");
            imageSource = Login.class.getResourceAsStream("Assets/music.png");
            Image musicImage = ImageIO.read(imageSource);
            musicIcon = new ImageIcon(musicImage);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        sensorButton.setIcon(sensorIcon);
        musicButton.setIcon(musicIcon);

        sensorButton.setPressedIcon(sensorIcon);
        musicButton.setPressedIcon(musicIcon);

        sensorButton.setBorder(new EmptyBorder(30,Math.round(200f*screenWidthFactor),0,0));
        musicButton.setBorder(new EmptyBorder(30,0,0,Math.round(200f*screenWidthFactor)));

        sensorButton.setBackground(Color.RED);
        musicButton.setBackground(Color.RED);

        sensorButton.setFocusable(false);
        musicButton.setFocusable(false);

        sensorButton.setContentAreaFilled(false);
        musicButton.setContentAreaFilled(false);

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
        tempPanel.setBorder(new EmptyBorder(0,0,0,0));


        //tempPanel.setLocation(500,490);
        tempPanel.setOpaque(false);

        JPanel musicPanel = new JPanel();
        musicPanel.setLayout(new BoxLayout(musicPanel,BoxLayout.Y_AXIS ));
        musicPanel.setBackground(colorScheme.getSecondaryColor());
        musicPanel.setOpaque(false);

        Box[] boxes = new Box[2];

        for (int i = 0; i < boxes.length; i++){
            boxes[i] = Box.createHorizontalBox();
            musicPanel.add(boxes[i]);
        }

        boxes[0].add(musicButton);
        JLabel musicLabel = new JLabel("Music");
        musicLabel.setForeground(colorScheme.getDetailColor());
        musicLabel.setBackground(Color.WHITE);
        musicLabel.setBorder(new EmptyBorder(10,0,0,Math.round(200f*screenWidthFactor)));
        musicLabel.setFont(frame.getUsedFont().deriveFont(20f));
        boxes[1].add(musicLabel);

        tempPanel.add(musicPanel);

        JPanel sensorPanel = new JPanel();
        sensorPanel.setOpaque(false);
        sensorPanel.setLayout(new BoxLayout(sensorPanel,BoxLayout.Y_AXIS ));
        sensorPanel.setBackground(colorScheme.getSecondaryColor());
        Box[] boxes1 = new Box[2];

        for (int i = 0; i < boxes.length; i++){
            boxes1[i] = Box.createHorizontalBox();
            sensorPanel.add(boxes1[i]);
        }

        boxes1[0].add(sensorButton);
        JLabel sensorLabel = new JLabel("Sensors");
        sensorLabel.setForeground(colorScheme.getDetailColor());
        sensorLabel.setFont(frame.getUsedFont().deriveFont(20f));
        sensorLabel.setBorder(new EmptyBorder(Math.round(20f*screenHeightFactor),Math.round(170f*screenWidthFactor),50,0));
        boxes1[1].add(sensorLabel);
        //tempPanel.add(new JLabel());
        //tempPanel.add(new JLabel());
        //tempPanel.add(new JLabel());
        tempPanel.add(sensorPanel);
        //tempPanel.setPreferredSize(new Dimension(1920, 824));
        tempPanel.setMaximumSize(new Dimension(1920, 824));

        GridBagConstraints gc = new GridBagConstraints();
        //gc.anchor = GridBagConstraints.CENTER;
        //gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 0.8f;
        gc.gridwidth = 1;
        gc.gridheight = 1;

        add(tempPanel, gc);


        header = new Header(frame, this);
        header.setPreferredSize(new Dimension(1920,Math.round(128f*screenHeightFactor)));
        header.setMaximumSize(new Dimension(1920,Math.round(128f*screenHeightFactor)));


        GridBagConstraints gcSecond = new GridBagConstraints();
        gcSecond.fill = GridBagConstraints.BOTH;
        gcSecond.gridx = 0;
        gcSecond.weightx = 1;
        gcSecond.weighty = 0.001f;
        gcSecond.gridy = 0;
        gcSecond.gridwidth = 3;
        gcSecond.gridheight = 1;


        add(header, gcSecond);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(null);
        //footerPanel.setBorder(BorderFactory.createMatteBorder(4,0,0,0, colorScheme.getBorderColor()));
        footerPanel.setMaximumSize(new Dimension(1920,Math.round(128f*screenHeightFactor)));
        footerPanel.setPreferredSize(new Dimension(1920,Math.round(128f*screenHeightFactor)));
        footerPanel.setOpaque(false);
        GridBagConstraints gcThird = new GridBagConstraints();
        gcThird.fill = GridBagConstraints.BOTH;
        //gcSecond.anchor = GridBagConstraints.FIRST_LINE_START;
        gcThird.gridx = 0;
        gcThird.weightx = 1;
        gcThird.weighty = 0.001f;
        gcThird.gridy = 2;
        gcThird.gridwidth = 3;
        gcThird.gridheight = 1;

        add(footerPanel, gcThird);
        paintComponent(frame.getGraphics());
    }

    public void removeThis(){
        frame.remove(this);
        frame.remove(header);
        header = null;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        try{
            InputStream imageSourceDisabled = Login.class.getResourceAsStream("Assets/button.png");
            Image button = ImageIO.read(imageSourceDisabled);

            //g.drawImage(button, 538,343, 300, 300, null);
            //g.drawImage(button, 1048,343, 300, 300, null);
            GradientPaint grad = new GradientPaint(0,0,colorScheme.getFirstBackgroundColor(), 0, (float) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight(),colorScheme.getSecondBackgroundColor());
            Graphics2D g2d1 = (Graphics2D) g;
            g2d1.setPaint(grad);
            g2d1.fill(new Rectangle2D.Double(0,0, frame.getWidth() , frame.getHeight()));

        }
        catch (Exception e){

        }
        float screenWidthFactor = (frame.getWidth() / 1920f);
        float screenHeightFactor = (frame.getHeight() / 1080f);
        Graphics2D g2d1 = (Graphics2D) g;
        g2d1.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d1.setColor(colorScheme.getSecondaryColor());
        g2d1.fillOval(Math.round(538f* screenWidthFactor),Math.round(350f* screenHeightFactor), 300, 300);
        g2d1.fillOval(Math.round(1048f* screenWidthFactor),Math.round(350f* screenHeightFactor), 300, 300);

        g2d1.setColor(colorScheme.getBorderColor());
        g2d1.drawOval(Math.round(538f* screenWidthFactor),Math.round(350f* screenHeightFactor), 300, 300);
        g2d1.drawOval(Math.round(539f* screenWidthFactor),Math.round(351f* screenHeightFactor), 298, 298);
        g2d1.drawOval(Math.round(1048f* screenWidthFactor),Math.round(350f* screenHeightFactor), 300, 300);
        g2d1.drawOval(Math.round(1049f* screenWidthFactor),Math.round(351f* screenHeightFactor), 298, 298);

        repaint();
    }
}


