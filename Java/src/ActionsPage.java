import javax.swing.*;
import java.awt.*;

public class ActionsPage {

    public JPanel ActionPanel()
    {
        return new ActionPanel();
    }

}

class ActionPanel extends JPanel
{
    private JPanel jpAddAction;
    private JTabbedPane jtpAction;

    private JButton actionbutton;

    public ActionPanel()
    {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLayout(new BorderLayout());

        jpAddAction = new JPanel();
        jpAddAction.setBackground(new Color(255,255,0));
        jpAddAction.setPreferredSize(new Dimension((int)(size.width * 0.8), size.height));
        jpAddAction.setBorder(BorderFactory.createLineBorder(Color.black));
        jpAddAction.setLayout(new BoxLayout(jpAddAction, BoxLayout.Y_AXIS));

        jtpAction = new JTabbedPane();
        jtpAction.setTabPlacement(JTabbedPane.LEFT);
        add(jtpAction);
        jtpAction.add("<html><body><table width='150'>nieuwe actie +</table></body></html>", jpAddAction);





    }
}