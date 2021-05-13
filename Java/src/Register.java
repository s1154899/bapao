import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JDialog {

    JTextField usernameInput;
    JTextField passwordInput;
    JTextField secondPasswordInput;

    JButton register;
    JButton goToLogin;

    String insertedName;
    String insertedPassword;
    String insertedSecondPassword;

    String currentAccountName;

    AccountManager accountManager;
    Login loginWindow;

    public Register(JFrame frame, boolean modal, Login login){
        this.loginWindow = login;
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(500,700);
        setLocation(710, 190);

        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(new Color(67, 136, 204));
        registerPanel.setPreferredSize(new Dimension(500, 700));
        registerPanel.setMaximumSize(new Dimension(500, 700));
        registerPanel.setLocation(710, 0);
        registerPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Register", SwingConstants.CENTER);

        title.setPreferredSize(new Dimension(400,100));
        title.setMaximumSize(new Dimension(400,100));
        title.setFont(new Font(title.getFont().getFontName(), Font.PLAIN, 30));
        title.setLocation(0,0);
        title.setBackground(Color.WHITE);

        registerPanel.add(title, BorderLayout.PAGE_START);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        Box[] boxes = new Box[6];

        for (int i = 0; i < boxes.length; i++){
            boxes[i] = Box.createHorizontalBox();
            centerPanel.add(boxes[i]);
        }



        centerPanel.setPreferredSize(new Dimension(460,100));
        centerPanel.setMaximumSize(new Dimension(460,100));
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

        JLabel secondPassword = new JLabel("Validate password:");
        secondPassword.setPreferredSize(new Dimension(460,30));
        secondPassword.setMaximumSize(new Dimension(460,30));
        boxes[4].add(secondPassword);

        secondPasswordInput = new JTextField();
        secondPasswordInput.setFont(secondPasswordInput.getFont().deriveFont(20f));
        secondPasswordInput.setMaximumSize(new Dimension(460, 60));
        secondPasswordInput.setPreferredSize(new Dimension(460, 60));

        boxes[5].add(secondPasswordInput);

        registerPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        register = new JButton("Register");
        goToLogin = new JButton("Want to login?");
        buttonPanel.add(goToLogin);
        buttonPanel.add(register);

        registerPanel.add(buttonPanel, BorderLayout.PAGE_END);

        accountManager = new AccountManager();


        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertedName = usernameInput.getText();
                insertedPassword = passwordInput.getText();
                insertedSecondPassword = secondPasswordInput.getText();

                if (insertedPassword.equals(insertedSecondPassword)){
                    accountManager.makeAccount(insertedName, insertedPassword);
                    currentAccountName = insertedName;
                    removeThis();
                    frame.setVisible(true);
                }
            }
        });

        goToLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeThis();
                showLogin();
            }
        });

        add(registerPanel);
    }

    public void removeThis(){
        this.setVisible(false);
    }

    public void showLogin(){
        loginWindow.setVisible(true);
    }
}
