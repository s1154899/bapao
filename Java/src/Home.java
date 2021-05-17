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

    public Home(Main frame, boolean modal){
        added = true;
        this.frame = frame;
        this.colorScheme = Main.getColorScheme();
        setSize(1920,1080);



        setBackground(colorScheme.getPrimaryColor());
        setLayout(new GridBagLayout());
        setLocation(0,0);
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

        sensorButton.setBorder(new EmptyBorder(30,Math.round(10f*(frame.getWidth()/1920f)),0,0));
        musicButton.setBorder(new EmptyBorder(30,0,0,Math.round(35f*(frame.getWidth()/1920f))));

        sensorButton.setBackground(null);
        musicButton.setBackground(null);

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
        tempPanel.setLayout(new GridLayout(1,4));
        //tempPanel.setBorder(new EmptyBorder(200,200,200,200));


        tempPanel.setLocation(500,490);
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
        musicLabel.setBorder(new EmptyBorder(10,0,0,40));
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
        sensorLabel.setBorder(new EmptyBorder(Math.round(20f*(frame.getHeight()/1080f)),Math.round(10f*(frame.getWidth()/1920f)),0,0));
        boxes1[1].add(sensorLabel);
        tempPanel.add(new JLabel());
        tempPanel.add(new JLabel());
        //tempPanel.add(new JLabel());
        tempPanel.add(sensorPanel);

        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.CENTER;
        //gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 0.6f;
        gc.gridwidth = 1;
        gc.gridheight = 1;

        add(tempPanel, gc);


        Header headPanel = new Header(frame, this);
        headPanel.setPreferredSize(new Dimension(1920,10));
        headPanel.setMaximumSize(new Dimension(1920,10));


        GridBagConstraints gcSecond = new GridBagConstraints();
        gcSecond.fill = GridBagConstraints.BOTH;
        gcSecond.gridx = 0;
        gcSecond.weightx = 1;
        gcSecond.weighty = 0.001f;
        gcSecond.gridy = 0;
        gcSecond.gridwidth = 3;
        gcSecond.gridheight = 1;


        add(headPanel, gcSecond);

        JLabel footerPanel = new JLabel();
        footerPanel.setBackground(null);
        //footerPanel.setBorder(BorderFactory.createMatteBorder(4,0,0,0, colorScheme.getBorderColor()));
        footerPanel.setSize(1920,180);

        GridBagConstraints gcThird = new GridBagConstraints();
        gcThird.fill = GridBagConstraints.BOTH;
        //gcSecond.anchor = GridBagConstraints.FIRST_LINE_START;
        gcThird.gridx = 0;
        gcThird.weightx = 1;
        gcThird.weighty = 0.2f;
        gcThird.gridy = 2;
        gcThird.gridwidth = 3;
        gcThird.gridheight = 1;

        add(footerPanel, gcThird);




        //paintPanel paintPanel = new paintPanel(frame);
        //add(paintPanel);

        paintComponent(frame.getGraphics());
    }

    public void removeThis(){
        frame.remove(this);
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
            GradientPaint grad = new GradientPaint(0,0,colorScheme.getFirstBackgroundColor(), 0,1080,colorScheme.getSecondBackgroundColor());
            Graphics2D g2d1 = (Graphics2D) g;
            g2d1.setPaint(grad);
            g2d1.fill(new Rectangle2D.Double(0,0, 1920 , 1080));

        }
        catch (Exception e){

        }

        Graphics2D g2d1 = (Graphics2D) g;
        g2d1.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d1.setColor(colorScheme.getSecondaryColor());
        g2d1.fillOval(Math.round(538f* (frame.getWidth() / 1920f)),Math.round(350f* (frame.getHeight() / 1080f)), 300, 300);
        g2d1.fillOval(Math.round(1048f* (frame.getWidth() / 1920f)),Math.round(350f* (frame.getHeight() / 1080f)), 300, 300);

        g2d1.setColor(colorScheme.getBorderColor());
        g2d1.drawOval(Math.round(538f* (frame.getWidth() / 1920f)),Math.round(350f* (frame.getHeight() / 1080f)), 300, 300);
        g2d1.drawOval(Math.round(539f* (frame.getWidth() / 1920f)),Math.round(351f* (frame.getHeight() / 1080f)), 298, 298);
        g2d1.drawOval(Math.round(1048f* (frame.getWidth() / 1920f)),Math.round(350f* (frame.getHeight() / 1080f)), 300, 300);
        g2d1.drawOval(Math.round(1049f* (frame.getWidth() / 1920f)),Math.round(351f* (frame.getHeight() / 1080f)), 298, 298);

        repaint();
    }
}


