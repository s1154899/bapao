
import raspberry.RaspberryPi;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main extends JFrame {

    public JPanel menu;
    public static ColorScheme.colorEnum colorScheme;
    public static Font usedFont;

    public AccountManager accountManager;

    private Home home;
    public static Main mainFrame;

    public static void main(String[] args) {
        mainFrame = new Main();

    }

    //creates the main page for the domotica system
    public Main(){
        super();
        colorScheme = ColorScheme.colorEnum.lightMode;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Domotica: home screen");
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width,r.height);
        try {
            usedFont = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Assets/Comfort.ttf"));
        } catch (IOException |FontFormatException e) {
            //Handle exception
        }


//        //layout for the main panel
//        BorderLayout border = new BorderLayout();
//        setLayout(border);
//
//        //layout for the menu
//        GridLayout grid = new GridLayout(4,2,40,60);
//        menu = new JPanel();
//        menu.setLayout(grid);
//
//        String[] buttons = {"Songs", "Results", "Playlists", "Actions", "Edit_Playlist", "Sensors", "Playing" };
//
//        for(String button : buttons) {
//            //creates button for creating the senors page
//            JButton button1 = new JButton();
//            button1.setText(button);
//            button1.addActionListener(Reflect.actionListenerFromMethod(this, button+"Page"));
//
//            //adds sensors button to the menu
//            menu.add(button1);
//        }

//
//        //adds the menu to the main screen
//        add(menu, BorderLayout.CENTER);

        //SubnetCheck subnetCheck = new SubnetCheck(this,true);

        new RaspberryPi("192.168.2.18");


        home = new Home();
        Home.added = true;
        add(home);


        loginPage();

    }

    //function called at the start for displaying a log-in screen
    public void loginPage(){
        setVisible(false);

        Login login = new Login();

        revalidate();
        repaint();
    }

    public void resetApp(){
        this.dispose();
        mainFrame = new Main();
    }


    public void returnHome(){
        add(new Home());
    }


}

class Reflect{

    public static ActionListener actionListenerFromMethod(Object obj, String Method){


        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.lang.reflect.Method method;
                    method = obj.getClass().getMethod(Method);
                    method.invoke(obj);
                } catch (SecurityException | NoSuchMethodException | InvocationTargetException exception) {exception.printStackTrace(); } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        };
    }

}


