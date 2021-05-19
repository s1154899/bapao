import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Flow;

public class RoundButton extends JButton {

    int width, height;
    Color color;

    public RoundButton(){
        this(100,100,new Color(1f,0f,0f,.5f ));

    }

    public RoundButton(int width, int height, Color color){
        this.width = width;
        this.height = height;
        this.color = color;


        setBorder(new EmptyBorder(30, 0, 0, Math.round(20f)));
        setFocusable(false);


        setSize(width, height);
        setPreferredSize(new Dimension(width, height));

        setContentAreaFilled(false);

        setBackground(null);
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackground(null);
            }
        });

        JLabel musicLabel = new JLabel("Music");

//            setBorder(new EmptyBorder(10,0,0,Math.round(200f)));


//            musicLabel.setFont(frame.getUsedFont().deriveFont(20f));

    }


    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fillOval(0,0, width, height);

        super.paintComponent(g);

    }


    public RoundButton(int width, int height, Color color, String imgLink){
        this(width,height,color);
        try {
        InputStream imageSource = Login.class.getResourceAsStream(imgLink);
        Image musicImage = null;
        musicImage = ImageIO.read(imageSource);
        ImageIcon musicIcon = new ImageIcon(musicImage);

        setIcon(musicIcon);
        setPressedIcon(musicIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(500,500);

        RoundButton rb = new RoundButton(150,150,new Color(1f,0f,0f,.5f ),"Assets/music.png");
        RoundButton rb2 = new RoundButton(150,150,new Color(1f,0f,0f,.5f ));
        rb2.setText("dasd");
        frame.setLayout(new FlowLayout());
        frame.add(rb);
        frame.add(rb2);



        frame.setVisible(true);
    }
}
