import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog {

    private JButton login;
    private JButton goToRegister;

    JTextField usernameInput;
    JTextField passwordInput;

    private AccountManager accountManager;
    private String insertedName;
    private String insertedPassword;

    private String currentAccountName;

    private JFrame frame;

    public Register register;

    public Login(JFrame frame, boolean modal){

        this.frame = frame;

        register = new Register(frame, modal, this);

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

        title.setPreferredSize(new Dimension(400,100));
        title.setMaximumSize(new Dimension(400,100));
        title.setFont(new Font(title.getFont().getFontName(), Font.PLAIN, 30));
        title.setLocation(0,0);
        title.setBackground(Color.WHITE);

        loginPanel.add(title, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        Box[] boxes = new Box[4];

        for (int i = 0; i < boxes.length; i++){
            boxes[i] = Box.createHorizontalBox();
            centerPanel.add(boxes[i]);
        }



        centerPanel.setPreferredSize(new Dimension(480,100));
        centerPanel.setMaximumSize(new Dimension(480,100));
        centerPanel.setBackground(null);

        JLabel username = new JLabel("Username:");
        username.setPreferredSize(new Dimension(460,30));
        username.setMaximumSize(new Dimension(460,30));
        boxes[0].add(username);

        usernameInput = new JTextField();
        usernameInput.setFont(usernameInput.getFont().deriveFont(20f));
        usernameInput.setMaximumSize(new Dimension(460, 60));
        usernameInput.setPreferredSize(new Dimension(460, 60));

        boxes[1].add(usernameInput);

        JLabel password = new JLabel("Password:");
        password.setPreferredSize(new Dimension(460,30));
        password.setMaximumSize(new Dimension(460,30));
        boxes[2].add(password);

        passwordInput = new JTextField();
        passwordInput.setFont(passwordInput.getFont().deriveFont(20f));
        passwordInput.setMaximumSize(new Dimension(460, 60));
        passwordInput.setPreferredSize(new Dimension(460, 60));

        boxes[3].add(passwordInput);

        loginPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        login = new JButton("Login");
        goToRegister = new JButton("Want to register?");
        buttonPanel.add(goToRegister);
        buttonPanel.add(login);

        loginPanel.add(buttonPanel, BorderLayout.PAGE_END);

        accountManager = new AccountManager();


        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertedName = usernameInput.getText();
                insertedPassword = passwordInput.getText();

                if (accountManager.validateAccount(insertedName, insertedPassword)){
                    currentAccountName = insertedName;

                    removeThis();
                }
            }
        });

        goToRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeThis();
                showRegister();
            }
        });

        add(loginPanel);
        setVisible(true);
    }

    public void removeThis(){
        this.setVisible(false );
    }

    public void showRegister(){
        register.setVisible(true);
    }

    public String getCurrentAccountName() {
        return currentAccountName;
    }
}
