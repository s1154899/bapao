import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main extends JFrame {

    public JPanel menu;

    public AccountManager accountManager;
    private static colorEnum colorScheme;
    private Font usedFont;
    private Home home;
    private static Main main;

    public static void main(String[] args) {
        main = new Main();

    }

    //creates the main page for the domotica system
    public Main(){
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        colorScheme = colorEnum.lightMode;
        setTitle("Domotica: home screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width,r.height);


        //layout for the main panel
        BorderLayout border = new BorderLayout();
        setLayout(border);

        //layout for the menu
        GridLayout grid = new GridLayout(4,2,40,60);
        menu = new JPanel();
        menu.setLayout(grid);

        String[] buttons = {"Songs", "Results", "Playlists", "Actions", "Edit_Playlist", "Sensors", "Playing" };

        for(String button : buttons) {
            //creates button for creating the senors page
            JButton button1 = new JButton();
            button1.setText(button);
            button1.addActionListener(Reflect.actionListenerFromMethod(this, button+"Page"));

            //adds sensors button to the menu
            menu.add(button1);
        }


        //adds the menu to the main screen
        add(menu, BorderLayout.CENTER);

        //turns the Jframe Visible

        try {
            usedFont = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Assets/Comfort.ttf"));
        } catch (IOException |FontFormatException e) {
            e.printStackTrace();
        }

        loginPage();

    }

    //function called at the start for displaying a log-in screen
    public void loginPage(){
        setVisible(false);

        Login login = new Login(this, true);

        revalidate();
        repaint();
    }

    public void resetApp(){
        main.dispose();
        main = new Main();
    }

    public void homeScreen(){

        remove(menu);
        try {
            remove(home);
        }
        catch (Exception e){

        }
        home = new Home(this, true);

        this.add(home);

        revalidate();
        repaint();
    }

    public void showMusic(){
        remove(home);
        MusicMain music = new MusicMain(this, true);
        add(music);

        revalidate();
        repaint();
    }

    public void showStats(){
        remove(home);
        SensorsMain sensors = new SensorsMain(this, true);
        add(sensors);

        revalidate();
        repaint();
    }

    //function called when songs button is pressed
    public void SongsPage(){
        remove(menu);

        Songs songs = new Songs(this, true);
        add(songs);

        revalidate();
        repaint();
    }
    //function called when results button is pressed
    public void ResultsPage(){
        remove(menu);

        Graphs graphs = new Graphs();

        add(graphs.lineGraph());
        revalidate();
        repaint();
        System.out.println("x");
    }
    //function called when playlists button is pressed
    public void PlaylistsPage(){

        Playlists playlists = new Playlists(this, true);
        add(playlists);






    }
    //function called when actions is pressed
    public void ActionsPage(){
        remove(menu);

        revalidate();
        repaint();
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
    public void PlayingPage(MusicMain musicMain){
        remove(menu);

        Playing playing = new Playing(this, true, musicMain);
        add(playing);

        revalidate();
        repaint();
    }

    public enum colorEnum{
        lightMode(1),
        normalMode(2),
        darkMode(3);

        colorEnum(int number){
            switch (number) {
                case 1 -> {
                    this.primaryColor = new Color(249, 247, 247);
                    this.secondaryColor = new Color(219, 226, 239);
                    this.detailColor = new Color(63, 114, 175);
                    this.firstBackgroundColor = primaryColor;
                    this.secondBackgroundColor = secondaryColor;
                    this.borderColor = detailColor.brighter();
                    this.headerColor = null;
                }
                case 2 -> {
                    this.primaryColor = new Color(67, 136, 204);
                    this.secondaryColor = new Color(238, 238, 238);
                    this.detailColor = Color.BLACK;
                }
                case 3 -> {
                    this.primaryColor = new Color(34, 40, 49);
                    this.secondaryColor = new Color(57, 62, 70);
                    this.detailColor = new Color(238, 238, 238);
                    this.firstBackgroundColor = secondaryColor;
                    this.secondBackgroundColor = primaryColor;
                    this.borderColor = primaryColor;
                    this.headerColor = primaryColor;
                }
            }
        }

        public Color primaryColor = null;
        public Color secondaryColor = null;
        public Color detailColor = null;
        public Color firstBackgroundColor = null;
        public Color secondBackgroundColor = null;
        public Color borderColor = null;
        public Color headerColor = null;

        public Color getDetailColor() {
            return detailColor;
        }

        public Color getPrimaryColor() {
            return primaryColor;
        }

        public Color getSecondaryColor() {
            return secondaryColor;
        }

        public Color getFirstBackgroundColor() {
            return firstBackgroundColor;
        }

        public Color getSecondBackgroundColor() {
            return secondBackgroundColor;
        }

        public Color getBorderColor() {
            return borderColor;
        }

        public Color getHeaderColor() {
            return headerColor;
        }
    }

    public static colorEnum getColorScheme() {
        return colorScheme;
    }

    public Font getUsedFont() {
        return usedFont;
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


