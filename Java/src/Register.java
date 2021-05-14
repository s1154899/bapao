import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.util.Arrays;

public class Register extends JDialog {

    JTextField usernameInput;
    JPasswordField passwordInput;
    JPasswordField secondPasswordInput;

    JButton register;
    JButton goToLogin;

    String insertedName;
    String insertedPassword;
    String insertedSecondPassword;

    String currentAccountName;

    AccountManager accountManager;
    Login loginWindow;
    JLabel errorMessage;
    Image iconImage;

    private Main.colorEnum colorScheme;

    private char passwordChar;
    Icon enabled;
    Icon disabled;

    Font usedFont;

    public Register(JFrame frame, boolean modal, Login login){
        this.loginWindow = login;
        usedFont = login.getUsedFont();
        colorScheme = Main.getColorScheme();

        try {
            InputStream imageSource = Login.class.getResourceAsStream("Assets/icon.png");
            iconImage = ImageIO.read(imageSource);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(500,600);
        setLocation(710, 190);
        setIconImage(iconImage);

        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(colorScheme.getPrimaryColor());
        registerPanel.setPreferredSize(new Dimension(500, 700));
        registerPanel.setMaximumSize(new Dimension(500, 700));
        registerPanel.setLocation(710, 0);
        registerPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Register", SwingConstants.CENTER);
        title.setForeground(colorScheme.getDetailColor());
        title.setPreferredSize(new Dimension(400,100));
        title.setMaximumSize(new Dimension(400,100));
        title.setFont(usedFont.deriveFont(40f));
        title.setLocation(0,0);
        title.setBackground(Color.WHITE);

        registerPanel.add(title, BorderLayout.PAGE_START);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        Box[] boxes = new Box[7];

        for (int i = 0; i < boxes.length; i++){
            boxes[i] = Box.createHorizontalBox();
            centerPanel.add(boxes[i]);
        }



        centerPanel.setPreferredSize(new Dimension(460,100));
        centerPanel.setMaximumSize(new Dimension(460,100));
        centerPanel.setBackground(null);

        JLabel username = new JLabel("Username:");
        username.setForeground(colorScheme.getDetailColor());
        username.setPreferredSize(new Dimension(460,30));
        username.setMaximumSize(new Dimension(460,30));
        username.setFont(usedFont.deriveFont(20f));
        username.setBorder(new EmptyBorder(10,0,10,0));
        boxes[0].add(username);

        usernameInput = new JTextField();
        usernameInput.setFont(usedFont.deriveFont(25f));
        usernameInput.setMaximumSize(new Dimension(460, 60));
        usernameInput.setPreferredSize(new Dimension(460, 60));
        usernameInput.setBorder(new EmptyBorder(2,15,2,15));

        usernameInput.setBackground(colorScheme.getSecondaryColor());

        usernameInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameInput.getText().equals("Username") || usernameInput.getText().equals("")) {
                    usernameInput.setForeground(colorScheme.getDetailColor());
                    usernameInput.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameInput.getText().equals("")) {
                    usernameInput.setForeground(Color.GRAY);
                    usernameInput.setText("Username");
                }
            }
        });

        boxes[1].add(usernameInput);

        JLabel password = new JLabel("Password:");
        password.setForeground(colorScheme.getDetailColor());
        password.setPreferredSize(new Dimension(460,30));
        password.setMaximumSize(new Dimension(460,30));
        password.setFont(usedFont.deriveFont(20f));
        password.setBorder(new EmptyBorder(10,0,0,0));
        boxes[2].add(password);

        JPanel background = new JPanel();
        background.setBackground(colorScheme.getSecondaryColor());
        background.setLayout(new BorderLayout());

        JPanel passwordInputPanel = new JPanel();
        passwordInputPanel.setBackground(null);
        passwordInputPanel.setLayout(new BorderLayout());
        passwordInputPanel.setMaximumSize(new Dimension(480, 80));
        passwordInputPanel.setPreferredSize(new Dimension(480, 80));
        passwordInputPanel.setBorder(new EmptyBorder(10,10,10,10));

        passwordInput = new JPasswordField("Password");
        passwordChar = passwordInput.getEchoChar();
        passwordInput.setEchoChar((char)0);
        passwordInput.setForeground(Color.GRAY);
        passwordInput.setFont(usedFont.deriveFont(25f));
        passwordInput.setMaximumSize(new Dimension(460, 60));
        passwordInput.setPreferredSize(new Dimension(460, 60));
        passwordInput.setBackground(colorScheme.getSecondaryColor());
        passwordInput.setBorder(new EmptyBorder(2,15,2,15));

        JButton changeInput = new JButton();
        changeInput.setBackground(colorScheme.getSecondaryColor());
        changeInput.setBorder(null);

        try {
            InputStream imageSourceEnabled = Login.class.getResourceAsStream("Assets/visible.png");
            Image enabledImage = ImageIO.read(imageSourceEnabled);
            enabled = new ImageIcon(enabledImage);

            InputStream imageSourceDisabled = Login.class.getResourceAsStream("Assets/nonVisible.png");
            Image disabledImage = ImageIO.read(imageSourceDisabled);
            disabled = new ImageIcon(disabledImage);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        changeInput.setIcon(disabled);
        changeInput.setBorder(new EmptyBorder(20,20,20,20));
        changeInput.setContentAreaFilled(false);
        changeInput.setFocusable(false);
        changeInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (changeInput.getIcon() == enabled){
                    if (!passwordInput.getText().equals("Password")){
                        passwordInput.setEchoChar(passwordChar);
                    }
                    changeInput.setIcon(disabled);
                }
                else if (changeInput.getIcon() == disabled){
                    if (!passwordInput.getText().equals("Password")){
                        passwordInput.setEchoChar((char)0);
                    }
                    changeInput.setIcon(enabled);
                }

            }
        });

        passwordInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordInput.getText().equals("Password") || passwordInput.getText().equals("")) {
                    passwordInput.setForeground(colorScheme.getDetailColor());
                    passwordInput.setText("");
                    if (changeInput.getIcon() == disabled) {
                        passwordInput.setEchoChar(passwordChar);
                    }
                    else if (changeInput.getIcon() == enabled){
                        passwordInput.setEchoChar((char) 0);
                    }
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordInput.getText().equals("")) {
                    passwordInput.setForeground(Color.GRAY);
                    passwordInput.setText("Password");
                    passwordInput.setEchoChar((char) 0);
                }
            }
        });

        background.add(passwordInput, BorderLayout.CENTER);
        background.add(changeInput, BorderLayout.EAST);
        passwordInputPanel.add(background);

        boxes[3].add(passwordInputPanel);

        JLabel secondPassword = new JLabel("Validate password:");
        secondPassword.setForeground(colorScheme.getDetailColor());
        secondPassword.setFont(usedFont.deriveFont(20f));
        secondPassword.setPreferredSize(new Dimension(460,30));
        secondPassword.setMaximumSize(new Dimension(460,30));
        boxes[4].add(secondPassword);

        JPanel secondBackground = new JPanel();
        secondBackground.setBackground(colorScheme.getSecondaryColor());
        secondBackground.setLayout(new BorderLayout());

        JPanel secondPasswordInputPanel = new JPanel();
        secondPasswordInputPanel.setBackground(null);
        secondPasswordInputPanel.setLayout(new BorderLayout());
        secondPasswordInputPanel.setMaximumSize(new Dimension(480, 80));
        secondPasswordInputPanel.setPreferredSize(new Dimension(480, 80));
        secondPasswordInputPanel.setBorder(new EmptyBorder(10,10,10,10));




        secondPasswordInput = new JPasswordField("Password");
        secondPasswordInput.setEchoChar((char) 0);
        secondPasswordInput.setForeground(Color.GRAY);
        secondPasswordInput.setFont(usedFont.deriveFont(25f));
        secondPasswordInput.setMaximumSize(new Dimension(460, 60));
        secondPasswordInput.setPreferredSize(new Dimension(460, 60));
        secondPasswordInput.setBorder(new EmptyBorder(2,15,2,15));
        secondPasswordInput.setBackground(colorScheme.getSecondaryColor());


        JButton secondChangeInput = new JButton();
        secondChangeInput.setBackground(colorScheme.getSecondaryColor());
        secondChangeInput.setBorder(null);
        secondChangeInput.setIcon(disabled);
        secondChangeInput.setBorder(new EmptyBorder(20,20,20,20));
        secondChangeInput.setContentAreaFilled(false);
        secondChangeInput.setFocusable(false);
        secondChangeInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondChangeInput.getIcon() == enabled){
                    if (!secondPasswordInput.getText().equals("Password")){
                        secondPasswordInput.setEchoChar(passwordChar);
                    }
                    secondChangeInput.setIcon(disabled);
                }
                else if (secondChangeInput.getIcon() == disabled){
                    if (!secondPasswordInput.getText().equals("Password")){
                        secondPasswordInput.setEchoChar((char)0);
                    }
                    secondChangeInput.setIcon(enabled);
                }

            }
        });

        secondPasswordInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (secondPasswordInput.getText().equals("Password") || secondPasswordInput.getText().equals("")) {
                    secondPasswordInput.setForeground(colorScheme.getDetailColor());
                    secondPasswordInput.setText("");
                    if (secondChangeInput.getIcon() == disabled) {
                        secondPasswordInput.setEchoChar(passwordChar);
                    }
                    else if (secondChangeInput.getIcon() == enabled){
                        secondPasswordInput.setEchoChar((char) 0);
                    }
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (secondPasswordInput.getText().equals("")) {
                    secondPasswordInput.setForeground(Color.GRAY);
                    secondPasswordInput.setText("Password");
                    secondPasswordInput.setEchoChar((char) 0);
                }
            }
        });

        secondBackground.add(secondPasswordInput, BorderLayout.CENTER);
        secondBackground.add(secondChangeInput, BorderLayout.EAST);

        secondPasswordInputPanel.add(secondBackground);

        boxes[5].add(secondPasswordInputPanel);

        errorMessage = new JLabel();
        errorMessage.setText("");
        errorMessage.setForeground(Color.RED);
        errorMessage.setBorder(new EmptyBorder(10,10,10,10));

        boxes[6].add(errorMessage);

        registerPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        register = new JButton("Register");
        goToLogin = new JButton("Want to login?");

        register.setFont(usedFont.deriveFont(15f));
        goToLogin.setFont(usedFont.deriveFont(15f));

        register.setFocusable(false);
        goToLogin.setFocusable(false);

        register.setForeground(colorScheme.getDetailColor());
        goToLogin.setForeground(colorScheme.getDetailColor());

        register.setBackground(colorScheme.getPrimaryColor());
        goToLogin.setBackground(colorScheme.getPrimaryColor());

        register.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorScheme.getDetailColor()), new EmptyBorder(5,2,5,2) ));
        goToLogin.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorScheme.getDetailColor()), new EmptyBorder(5,2,5,2) ));

        register.setFocusable(false);
        goToLogin.setFocusable(false);

        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension tempSize = register.getSize();
                register.setBackground(colorScheme.getSecondaryColor());
                register.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorScheme.getDetailColor()), new EmptyBorder(5,2,5,2) ));
                register.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = register.getSize();
                register.setBackground(colorScheme.getPrimaryColor());
                register.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorScheme.getDetailColor()), new EmptyBorder(5,2,5,2) ));
                register.setSize(tempSize);
            }
        });

        goToLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension tempSize = goToLogin.getSize();
                goToLogin.setBackground(colorScheme.getSecondaryColor());
                goToLogin.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorScheme.getDetailColor()), new EmptyBorder(5,2,5,2) ));
                goToLogin.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = goToLogin.getSize();
                goToLogin.setBackground(colorScheme.getPrimaryColor());
                goToLogin.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorScheme.getDetailColor()), new EmptyBorder(5,2,5,2) ));
                goToLogin.setSize(tempSize);
            }
        });

        buttonPanel.add(goToLogin);
        buttonPanel.add(register);

        registerPanel.add(buttonPanel, BorderLayout.PAGE_END);

        accountManager = new AccountManager();


        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertedName = usernameInput.getText();

                insertedPassword = Arrays.toString(passwordInput.getPassword());
                insertedSecondPassword = Arrays.toString(secondPasswordInput.getPassword());

                if (insertedPassword.equals(insertedSecondPassword) && insertedName.length() > 3 && insertedPassword.length() > 3 ){
                    accountManager.makeAccount(insertedName, insertedPassword);
                    currentAccountName = insertedName;
                    removeThis();
                    frame.setVisible(true);
                }
                else{
                    errorMessage.setText("Incorrect input");
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
