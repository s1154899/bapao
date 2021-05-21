
import raspberry.RaspberryPi;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main extends JFrame {

    public JPanel menu;

    public AccountManager accountManager;

    private Home home;
    public static Main mainFrame;

    public static void main(String[] args) {
        mainFrame = new Main();

    }

    //creates the main page for the domotica system
    public Main(){
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Domotica: home screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width,r.height);


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

        new RaspberryPi("192.168.2.22");


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

    public void homeScreen(){
        home = new Home();
        this.add(home);

        revalidate();
        repaint();
    }

    public void showMusic(){
        remove(home);
        Home.added = false;
        MusicMain music = new MusicMain();
        add(music);

        revalidate();
        repaint();
    }

    public void showStats(){
        remove(home);
        Home.added = false;
        SensorsMain sensors = new SensorsMain();
        add(sensors);

        revalidate();
        repaint();
    }

    //function called when songs button is pressed
    public void SongsPage(){
        remove(menu);

        Songs songs = new Songs(mainFrame, true);
        add(songs);

        revalidate();
        repaint();
    }
    //function called when results button is pressed
    public void ResultsPage() {
        remove(menu);

        RaspberryPi pi = RaspberryPi.connectedPis.get(0);
        Graphs graphs = new Graphs();

        try {

            add(graphs.lineGraph(pi.databaseCon.getTemp()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        revalidate();
        repaint();
        System.out.println("x");
    }
    //function called when playlists button is pressed
    public void PlaylistsPage(){

        Playlists playlists = new Playlists();
        add(playlists);






    }
    //function called when actions is pressed
    public void ActionsPage(){

    }
    //function called when edit playlists is pressed
    public void Edit_PlaylistPage(){
        remove(menu);

        revalidate();
        repaint();
    }
    //function called when sensors is pressed
    public void SensorsPage(){
        remove(menu);

        revalidate();
        repaint();
    }
    //function called when playing is pressed
    public void PlayingPage(){
        remove(menu);

        MusicMain musicMain = new MusicMain();
        Playing playing = new Playing(this, true, musicMain);
        add(playing);

        revalidate();
        repaint();
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


