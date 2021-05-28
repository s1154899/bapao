import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;

public class Login extends JDialog {

    private JButton login;
    private JButton goToRegister;

    JTextField usernameInput;
    JPasswordField passwordInput;

    private AccountManager accountManager;
    private String insertedName;
    private String insertedPassword;

    private String currentAccountName;

    private JLabel errorMessage;

    private Image iconImage;


    private char passwordChar;


    Icon enabled;
    Icon disabled;

    public Login(){

//        try {
//            usedFont = Font.createFont(Font.TRUETYPE_FONT, Login.class.getResourceAsStream("Assets/Comfort.ttf"));
//        } catch (IOException |FontFormatException e) {
//            e.printStackTrace();
//            usedFont = new Font("Serif", Font.PLAIN, 11);
//        }



        try {
            InputStream imageSource = Login.class.getResourceAsStream("Assets/icon.png");
            iconImage = ImageIO.read(imageSource);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //super(frame, modal);
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(500,600);
        setLocation(710, 190);
        setIconImage(iconImage);

        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Main.colorScheme.getPrimaryColor());
        loginPanel.setPreferredSize(new Dimension(500, 700));
        loginPanel.setMaximumSize(new Dimension(500, 700));
        loginPanel.setLocation(710, 0);
        loginPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setForeground(Main.colorScheme.getDetailColor());

        title.setPreferredSize(new Dimension(400,100));
        title.setMaximumSize(new Dimension(400,100));

        //title.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
        title.setFont(Main.colorScheme.usedFont.deriveFont(40f));
        title.setLocation(0,0);
        title.setBackground(Color.WHITE);
        loginPanel.add(title, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        Box[] boxes = new Box[5];

        for (int i = 0; i < boxes.length; i++){
            boxes[i] = Box.createHorizontalBox();
            centerPanel.add(boxes[i]);
        }

        centerPanel.setPreferredSize(new Dimension(480,100));
        centerPanel.setMaximumSize(new Dimension(480,100));
        centerPanel.setBackground(null);

        JLabel username = new JLabel("Username:");
        username.setFont(Main.colorScheme.usedFont.deriveFont(20f));
        username.setForeground(Main.colorScheme.getDetailColor());
        username.setPreferredSize(new Dimension(460,30));
        username.setMaximumSize(new Dimension(460,30));
        username.setBorder(new EmptyBorder(10,0,10,0));
        boxes[0].add(username);

        usernameInput = new JTextField();
        usernameInput.setFocusable(false);
        usernameInput.setFocusable(true);
        usernameInput.setFont(Main.colorScheme.usedFont.deriveFont(25f));
        usernameInput.setMaximumSize(new Dimension(460, 60));
        usernameInput.setPreferredSize(new Dimension(460, 60));
        //usernameInput.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(BevelBorder.LOWERED), new EmptyBorder(2,15,2,15)));
        usernameInput.setBorder(new EmptyBorder(2,15,2,15));

        usernameInput.setBackground(Main.colorScheme.getSecondaryColor());

        usernameInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameInput.getText().equals("Username") || usernameInput.getText().equals("")) {
                    usernameInput.setForeground(Main.colorScheme.getDetailColor());
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
        password.setFont(Main.colorScheme.usedFont.deriveFont(20f));
        password.setForeground(Main.colorScheme.getDetailColor());
        password.setPreferredSize(new Dimension(460,30));
        password.setMaximumSize(new Dimension(460,30));
        password.setBorder(new EmptyBorder(10,0,0,0));
        boxes[2].add(password);

        JPanel background = new JPanel();
        background.setBackground(Main.colorScheme.getSecondaryColor());
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
        passwordInput.setFont(Main.colorScheme.usedFont.deriveFont(25f));
        passwordInput.setMaximumSize(new Dimension(460, 60));
        passwordInput.setPreferredSize(new Dimension(460, 60));
        passwordInput.setBorder(new EmptyBorder(2,15,2,15));
        passwordInput.setBackground(Main.colorScheme.getSecondaryColor());



        JButton changeInput = new JButton();
        changeInput.setBackground(Main.colorScheme.getSecondaryColor());
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
                    passwordInput.setForeground(Main.colorScheme.getDetailColor());
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

        errorMessage = new JLabel();
        errorMessage.setText("");
        errorMessage.setForeground(Color.RED);
        errorMessage.setBorder(new EmptyBorder(10,10,10,10));

        boxes[4].add(errorMessage);

        loginPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        login = new JButton("Login");
        goToRegister = new JButton("Want to register?");

        login.setFont(Main.colorScheme.usedFont.deriveFont(15f));
        goToRegister.setFont(Main.colorScheme.usedFont.deriveFont(15f));

        login.setForeground(Main.colorScheme.getDetailColor());
        goToRegister.setForeground(Main.colorScheme.getDetailColor());

        login.setBackground(Main.colorScheme.getPrimaryColor());
        goToRegister.setBackground(Main.colorScheme.getPrimaryColor());

        login.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Main.colorScheme.getDetailColor()), new EmptyBorder(5,2,5,2) ));
        goToRegister.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Main.colorScheme.getDetailColor()), new EmptyBorder(5,2,5,2) ));

        login.setFocusable(false);
        goToRegister.setFocusable(false);

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension tempSize = login.getSize();
                login.setBackground(Main.colorScheme.getSecondaryColor());
                login.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Main.colorScheme.getDetailColor()), new EmptyBorder(5,2,5,2) ));
                login.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = login.getSize();
                login.setBackground(Main.colorScheme.getPrimaryColor());
                login.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Main.colorScheme.getDetailColor()), new EmptyBorder(5,2,5,2) ));
                login.setSize(tempSize);
            }
        });

        goToRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension tempSize = goToRegister.getSize();
                goToRegister.setBackground(Main.colorScheme.getSecondaryColor());
                goToRegister.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Main.colorScheme.getDetailColor()), new EmptyBorder(5,2,5,2) ));
                goToRegister.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = goToRegister.getSize();
                goToRegister.setBackground(Main.colorScheme.getPrimaryColor());
                goToRegister.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Main.colorScheme.getDetailColor()), new EmptyBorder(5,2,5,2) ));
                goToRegister.setSize(tempSize);
            }
        });

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
                    Main.mainFrame.setVisible(true);

                    removeThis();
                }
                else{
                    System.out.println("incorrect");
                    errorMessage.setText("Username or password are incorrect");
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
//        this.setVisible(false);
        this.dispose();
    }

    public void showRegister(){
        Register register = new Register();
        register.setVisible(true);
    }

    public String getCurrentAccountName() {
        return currentAccountName;
    }

//    public Font getUsedFont() {
//        return usedFont;
//    }

    public void setCurrentAccountName(String currentAccountName) {
        this.currentAccountName = currentAccountName;
    }
}
