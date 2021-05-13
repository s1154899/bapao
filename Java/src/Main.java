import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class Main extends JFrame {


    public JPanel menu;
    public AccountManager accountManager;


    public static void main(String[] args) {
        new Main();

    }


    //creates the main page for the domotica system
    public Main(){
        super();
        setTitle("Domotica: home screen");
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
        setVisible(true);



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
        remove(menu);

        revalidate();
        repaint();
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
    public void PlayingPage(){
        remove(menu);

        Playing playing = new Playing();
        add(playing);

        revalidate();
        repaint();
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
