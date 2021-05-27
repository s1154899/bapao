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


    private boolean timeEnabled = false;
    JButton logout;
    ImageIcon lightHomeIcon;
    ImageIcon homeIcon;
    ImageIcon lightMode;
    ImageIcon darkMode;
    JButton homeButton;
    JLabel time;
    TimeControl bc;
    JPanel parent;


    public Header() {


        setBackground(Main.colorScheme.getHeaderColor());
        setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Main.colorScheme.getSecondaryColor()));
        setLocation(0, 0);
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
        Box[] boxes = new Box[5];

        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = Box.createHorizontalBox();

            add(boxes[i]);
        }


        boxes[0].setBorder(new EmptyBorder(0, 32, 0, 32));

        setPreferredSize(new Dimension(getWidth(), 128));
        setMaximumSize(new Dimension(getWidth(), 128));

        try {

            InputStream imageSource = Login.class.getResourceAsStream("Assets/thermometer.png");
            imageSource = Login.class.getResourceAsStream("Assets/homeLight.png");
            Image sensorImage = ImageIO.read(imageSource);
            lightHomeIcon = new ImageIcon(sensorImage);

            imageSource = Login.class.getResourceAsStream("Assets/home.png");
            Image homeImage = ImageIO.read(imageSource);
            homeIcon = new ImageIcon(homeImage);

            imageSource = Login.class.getResourceAsStream("Assets/lightMode.png");
            Image lightImage = ImageIO.read(imageSource);
            lightMode = new ImageIcon(lightImage);

            imageSource = Login.class.getResourceAsStream("Assets/darkMode.png");
            Image darkImage = ImageIO.read(imageSource);
            darkMode = new ImageIcon(darkImage);

        } catch (Exception e) {
            e.printStackTrace();
        }


        homeButton = new JButton();
        homeButton.setIcon(lightHomeIcon);
        homeButton.setPreferredSize(new Dimension(64, 64));
        homeButton.setMaximumSize(new Dimension(64, 64));
        homeButton.setBackground(Color.WHITE);
        homeButton.setBorder(new EmptyBorder(Math.round(32f * (getHeight() / 1080f)), 0, Math.round(32f * (getHeight() / 1080f)), 0));
        homeButton.setOpaque(false);
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

//        homeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //removeThis();
////                Main.mainFrame.homeScreen();
//            }
//        });


        boxes[0].add(homeButton);

        logout = new JButton();
        logout.setFont(Main.usedFont.deriveFont(20f));
        logout.setBackground(null);
        logout.setText("Log Out");
        logout.setBorder(new CompoundBorder(new LineBorder(Main.colorScheme.getBorderColor(), 2), new EmptyBorder(5, 5, 5, 5)));
        logout.setForeground(Main.colorScheme.getDetailColor());
        logout.setAlignmentX(Component.RIGHT_ALIGNMENT);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.mainFrame.removeAll();
                Main.mainFrame.resetApp();
                bc.halt();

            }
        });
        //boxes[3].setBorder(new EmptyBorder(0,Math.round(1200f*(frame.getWidth()/1920f)),0,0));
        boxes[1].add(logout);

        JButton changeStyle = new JButton();
        changeStyle.setForeground(Main.colorScheme.getDetailColor());
        changeStyle.setFont(Main.usedFont.deriveFont(20f));
        changeStyle.setBackground(Main.colorScheme.primaryColor);
        if (Main.colorScheme == ColorScheme.colorEnum.lightMode) {
            changeStyle.setIcon(darkMode);
        }
        else if (Main.colorScheme == ColorScheme.colorEnum.darkMode){
            changeStyle.setIcon(lightMode);
        }
        changeStyle.setBorder(null);
        changeStyle.setFocusable(false);
        changeStyle.setOpaque(true);
        changeStyle.setContentAreaFilled(false);
        changeStyle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.colorScheme == ColorScheme.colorEnum.darkMode) {
                    Main.colorScheme = ColorScheme.colorEnum.lightMode;
                    changeStyle.setIcon(darkMode);
                } else if (Main.colorScheme == ColorScheme.colorEnum.lightMode) {
                    Main.colorScheme = ColorScheme.colorEnum.darkMode;
                    changeStyle.setIcon(lightMode);
                }
                changeColor();
                repaint();
                revalidate();
            }
        });

        boxes[2].setBorder(new EmptyBorder(0,20,0,0));
        boxes[2].add(changeStyle);

        JLabel date = new JLabel();
        date.setForeground(Main.colorScheme.getDetailColor());
        date.setFont(Main.usedFont.deriveFont(20f));
        date.setBorder(new EmptyBorder(3, 50, 0, 0));
        date.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("cccc dd.MM.uuuu", Locale.ENGLISH)));
        boxes[3].add(date);

        time = new JLabel();
        time.setForeground(Main.colorScheme.getDetailColor());
        time.setFont(Main.usedFont.deriveFont(20f));
        time.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        time.setBorder(new EmptyBorder(3, Math.round(40f * (getWidth() / 1080f)), 0, Math.round(40f * (getWidth() / 1080f))));
        time.setPreferredSize(new Dimension(Math.round(200f * (getWidth() / 1920f)), Math.round(64f * (getHeight() / 1080f))));
        boxes[4].add(time);
        boxes[4].setBorder(new EmptyBorder(0, 20, 0, 0));


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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            InputStream imageSource = Login.class.getResourceAsStream("Assets/icon.png");
            Image icon = ImageIO.read(imageSource);
            g2d.drawImage(icon, Math.round(1814f * (getWidth() / 1920f)), 32, 64, 64, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeThis() {
        remove(this);
//        remove(parent);

        revalidate();
        repaint();

        bc = null;

    }

    public void changeColor() {
        setBackground(Main.colorScheme.getHeaderColor());
        setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Main.colorScheme.getSecondaryColor()));
        logout.setBorder(new CompoundBorder(new LineBorder(Main.colorScheme.getBorderColor(), 2), new EmptyBorder(5, 5, 5, 5)));
        for (Component component : this.getComponents()) {
            if (component instanceof Box) {
                Box box = (Box) component;
                for (Component componentOfBox : box.getComponents()) {
                    if (componentOfBox instanceof JTextArea) {
                        //componentOfBox.setBackground();
                    } else if (componentOfBox instanceof JButton) {
                        componentOfBox.setForeground(Main.colorScheme.getDetailColor());
                        componentOfBox.setBackground(Main.colorScheme.getPrimaryColor());
                    } else if (componentOfBox instanceof JLabel) {
                        componentOfBox.setForeground(Main.colorScheme.getDetailColor());
                    } else if (componentOfBox instanceof JPanel) {

                    }
                }
            }
        }
        for (Component parentComponents : getParent().getComponents()) {
            if (parentComponents.getParent() instanceof Home) {
                Home home = (Home) parentComponents.getParent();
                home.changeColor();
            } else if (parentComponents.getParent() instanceof MusicMain) {
                MusicMain musicMain = (MusicMain) parentComponents.getParent();
                musicMain.changeColor();
            } else if (parentComponents.getParent() instanceof SensorsMain) {
                SensorsMain sensorsMain = (SensorsMain) parentComponents.getParent();
                sensorsMain.changeColor();
            }
            break;
        }
    }

    class TimeControl {

        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        public void setTimeEverySecond() {
            final Runnable setTime = new Runnable() {
                public void run() {
                    try {

                        time.setText(LocalDateTime.now().format(formatter));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            final ScheduledFuture<?> timeHandle = scheduler.scheduleAtFixedRate(setTime, 0, 1, TimeUnit.MILLISECONDS); // (Runnable command, long initialDelay, long period, TimeUnit unit)
        }

        public void halt() {
            System.out.println("HALTED");
            scheduler.shutdown();
            scheduler.shutdownNow();
        }
    }
}


