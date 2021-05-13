import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog {

    private JButton login;

    JTextArea usernameInput;
    JTextArea passwordInput;

    private AccountManager accountManager;
    private String insertedName;
    private String insertedPassword;

    private String currentAccountName;

    private JFrame frame;

    public Login(JFrame frame, boolean modal){
        //super(frame, modal);
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(500,700);
        setLocation(710, 190);

        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(67, 136, 204));
        loginPanel.setPreferredSize(new Dimension(500, 700));
        loginPanel.setMaximumSize(new Dimension(500, 700));
        loginPanel.setLocation(710, 0);
        loginPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        title.setPreferredSize(new Dimension(400,100));
        title.setMaximumSize(new Dimension(400,100));
        title.setFont(new Font(title.getFont().getFontName(), Font.PLAIN, 30));
        title.setLocation(0,0);
        title.setBackground(Color.WHITE);

        loginPanel.add(title, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1));
        JLabel username = new JLabel("Username:");
        username.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        username.setPreferredSize(new Dimension(400,30));
        username.setMaximumSize(new Dimension(400,30));

        centerPanel.add(username);

        usernameInput = new JTextArea();

        centerPanel.add(usernameInput);

        loginPanel.add(centerPanel, BorderLayout.LINE_START);

        login = new JButton("Login");


        passwordInput = new JTextArea();

        accountManager = new AccountManager();
        this.frame = frame;

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (accountManager.validateAccount(insertedName, insertedPassword)){
                    currentAccountName = insertedName;

                    removeThis();
                }
            }
        });

        add(loginPanel);
        setVisible(true);
    }

    public void removeThis(){
        frame.remove(this);
    }

    public String getCurrentAccountName() {
        return currentAccountName;
    }
}
