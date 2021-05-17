import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Header extends JPanel {

    Main frame;
    private Main.colorEnum colorScheme;
    private boolean timeEnabled = false;

    ImageIcon lightHomeIcon;
    ImageIcon homeIcon;
    JButton homeButton;
    JLabel time;
    TimeControl bc;
    JPanel parent;


    public Header(Main frame, JPanel parent){
        this.frame = frame;
        colorScheme = Main.getColorScheme();
        this.parent = parent;

        setBackground(colorScheme.getHeaderColor());
        setBorder(BorderFactory.createMatteBorder(0,0,5,0, (colorScheme.getSecondaryColor() == Main.colorEnum.darkMode.getSecondaryColor())? null : colorScheme.getSecondaryColor()));
        setLocation(0,0);
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
        Box[] boxes = new Box[4];

        for (int i = 0; i < boxes.length; i++){
            boxes[i] = Box.createHorizontalBox();
            add(boxes[i]);
        }

        boxes[0].setBorder(new EmptyBorder(0,32,0,32));

        setPreferredSize(new Dimension(frame.getWidth(),128));
        setMaximumSize(new Dimension(frame.getWidth(),128));

        try {

                InputStream imageSource = Login.class.getResourceAsStream("Assets/thermometer.png");
                imageSource = Login.class.getResourceAsStream("Assets/homeLight.png");
                Image sensorImage = ImageIO.read(imageSource);
                lightHomeIcon = new ImageIcon(sensorImage);

                imageSource = Login.class.getResourceAsStream("Assets/home.png");
                Image homeImage = ImageIO.read(imageSource);
                homeIcon = new ImageIcon(homeImage);

        }
        catch (Exception e){
            e.printStackTrace();
        }



        homeButton = new JButton();
        homeButton.setIcon(lightHomeIcon);
        homeButton.setPreferredSize(new Dimension(64,64));
        homeButton.setMaximumSize(new Dimension(64,64));
        homeButton.setBackground(null);
        homeButton.setBorder(new EmptyBorder(32,0,32,0));

        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                homeButton.setIcon(homeIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                homeButton.setIcon(lightHomeIcon);
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Home.added) {
                    frame.homeScreen();
                }
            }
        });



        boxes[0].add(homeButton);

        JLabel date = new JLabel();
        date.setForeground(colorScheme.getDetailColor());
        date.setFont(frame.getUsedFont().deriveFont(20f));
        date.setBorder(new EmptyBorder(3,0,0,0));
        date.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("cccc dd.MM.uuuu", Locale.ENGLISH)));
        boxes[1].add(date);

        time = new JLabel();
        time.setForeground(colorScheme.getDetailColor());
        time.setFont(frame.getUsedFont().deriveFont(20f));
        time.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        time.setBorder(new EmptyBorder(0,20,0,20));
        time.setPreferredSize(new Dimension(300,64));
        time.setMaximumSize(new Dimension(300,64));
        boxes[2].add(time);

        JButton logout = new JButton();
        logout.setFont(frame.getUsedFont().deriveFont(20f));
        logout.setBackground(null);
        logout.setText("Log Out");
        logout.setBorder(new CompoundBorder(new LineBorder(colorScheme.getBorderColor(), 2), new EmptyBorder(5,5,5,5)));
        logout.setForeground(colorScheme.getDetailColor());
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.removeAll();
                frame.resetApp();
                bc.halt();

            }
        });
        boxes[3].setBorder(new EmptyBorder(0,1070*(frame.getWidth()/1920),0,0));
        boxes[3].add(logout);

        if (!timeEnabled) {
            bc = new TimeControl();
            bc.setTimeEverySecond();
            timeEnabled = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            InputStream imageSource = Login.class.getResourceAsStream("Assets/icon.png");
            Image icon = ImageIO.read(imageSource);
            g2d.drawImage(icon, Math.round(1814f * (frame.getWidth() / 1920f)),32,64,64, null);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeThis(){
        frame.remove(this);
        frame.remove(parent);
        bc = null;

    }

    class TimeControl {

        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool ( 1 );
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "HH:mm:ss");

        public void setTimeEverySecond () {
            final Runnable setTime = new Runnable () {
                public void run () {
                    try {

                        time.setText(LocalDateTime.now().format(formatter));
                    } catch ( Exception e ) {
                        e.printStackTrace();
                    }
                }
            };
            final ScheduledFuture<?> timeHandle = scheduler.scheduleAtFixedRate ( setTime , 0 , 1 , TimeUnit.MILLISECONDS ); // (Runnable command, long initialDelay, long period, TimeUnit unit)
        }

        public void halt () {
            System.out.println ( "HALTED" );
            scheduler.shutdown ();
            scheduler.shutdownNow();
        }
    }
}


