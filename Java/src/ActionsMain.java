import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;

class ActionsMain extends JPanel implements ActionListener {
    private Font usedFont;
    private JPanel jpAddAction;
    private JTabbedPane jtpAction;
    Main frame;
    Main.colorEnum colorScheme;


    private JTextField jtActieNaam;
    private JTextField jtTijdInterval;

    private JComboBox<String> jcbTijd;

    private JButton jbUploadFile;
    private JButton jbSaveAction;

    private boolean isComboBoxDropped = false;


    public ActionsMain(Main frame, boolean modal) {
        this.colorScheme = Main.getColorScheme();
        this.frame = frame;
        this.setBackground(colorScheme.getSecondaryColor());
        this.setLayout(new BorderLayout());
        try {
            usedFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Login.class.getResourceAsStream("Assets/Comfort.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        jpAddAction = new JPanel();
        jpAddAction.setBackground(colorScheme.getPrimaryColor());
        jpAddAction.setBorder(BorderFactory.createLineBorder(Color.black));
        jpAddAction.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        jtpAction = new JTabbedPane(JTabbedPane.LEFT);
        jtpAction.setForeground(colorScheme.getDetailColor());
        jtpAction.setFocusable(false);
        add(jtpAction);
        jtpAction.setFont(usedFont.deriveFont(20f));
        jtpAction.add("<html><body><table width='150'>Nieuwe actie +</table></body></html>", jpAddAction);

        JLabel jlNaam = new JLabel("Naam actie:");
        jlNaam.setFont(usedFont.deriveFont(20f));
        jlNaam.setForeground(colorScheme.getDetailColor());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 40;
        c.ipady = 20;
        jpAddAction.add(jlNaam, c);

        jtActieNaam = new JTextField();
        jtActieNaam.setFont(usedFont.deriveFont(15f));
        jtActieNaam.setBackground(colorScheme.getSecondaryColor());
        jtActieNaam.setForeground(colorScheme.getDetailColor());
        jtActieNaam.setBorder(new EmptyBorder(2,15,2,15));
        c.gridx = 1;
        jpAddAction.add(jtActieNaam, c);

        JLabel jlTijd = new JLabel("Tijd interval:");
        jlTijd.setForeground(colorScheme.getDetailColor());
        jlTijd.setFont(usedFont.deriveFont(20f));
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        jpAddAction.add(jlTijd, c);

        jtTijdInterval = new JTextField();
        jtTijdInterval.setFont(usedFont.deriveFont(15f));
        jtTijdInterval.setBackground(colorScheme.getSecondaryColor());
        jtTijdInterval.setForeground(colorScheme.getDetailColor());
        jtTijdInterval.setBorder(new EmptyBorder(2,15,2,15));
        c.gridx = 1;
        c.gridy = 1;
        jpAddAction.add(jtTijdInterval, c);

        String[] tijdInterval = {"Seconden", "Minuten", "Uren", "Dagen"};
        jcbTijd = new JComboBox<>(tijdInterval);
        jcbTijd.setFocusable(false);
        jcbTijd.setSelectedIndex(0);
        jcbTijd.setFont(usedFont.deriveFont(15f));
        c.gridx = 2;
        jpAddAction.add(jcbTijd, c);

        jbUploadFile = new JButton("Upload js file");
        jbUploadFile.setFont(usedFont.deriveFont(20f));
        jbUploadFile.setFocusable(false);
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(25, 0, 0, 0);
        jpAddAction.add(jbUploadFile, c);

        jbSaveAction = new JButton("Maak actie aan");
        jbSaveAction.setFont(usedFont.deriveFont(30f));
        jbSaveAction.setFocusable(false);
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(50, 0, 0, 0);
        jpAddAction.add(jbSaveAction, c);

        jbSaveAction.setForeground(colorScheme.getDetailColor());
        jbUploadFile.setForeground(colorScheme.getDetailColor());
        jcbTijd.setForeground(colorScheme.getDetailColor());

        jbSaveAction.setBackground(colorScheme.getPrimaryColor());
        jbUploadFile.setBackground(colorScheme.getPrimaryColor());
        jcbTijd.setBackground(colorScheme.getPrimaryColor());

        jbSaveAction.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
        jbUploadFile.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));


        jbSaveAction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension tempSize = jbSaveAction.getSize();
                jbSaveAction.setBackground(colorScheme.getSecondaryColor());
                jbSaveAction.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbSaveAction.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = jbSaveAction.getSize();
                jbSaveAction.setBackground(colorScheme.getPrimaryColor());
                jbSaveAction.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbSaveAction.setSize(tempSize);
            }
        });

        jbUploadFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension tempSize = jbUploadFile.getSize();
                jbUploadFile.setBackground(colorScheme.getSecondaryColor());
                jbUploadFile.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbUploadFile.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = jbUploadFile.getSize();
                jbUploadFile.setBackground(colorScheme.getPrimaryColor());
                jbUploadFile.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(colorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbUploadFile.setSize(tempSize);
            }
        });

        jcbTijd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isComboBoxDropped) {
                    Dimension tempSize = jcbTijd.getSize();
                    jcbTijd.setBackground(colorScheme.getSecondaryColor());
                    jcbTijd.setSize(tempSize);
                } else {
                    Dimension tempSize = jcbTijd.getSize();
                    jcbTijd.setBackground(colorScheme.getPrimaryColor());
                    jcbTijd.setSize(tempSize);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Dimension tempSize = jcbTijd.getSize();
                jcbTijd.setBackground(colorScheme.getPrimaryColor());
                jcbTijd.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = jcbTijd.getSize();
                jcbTijd.setBackground(colorScheme.getPrimaryColor());
                jcbTijd.setSize(tempSize);
            }
        });

        jcbTijd.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                // This method is called before the popup menu becomes visible.
                isComboBoxDropped = true;
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // This method is called before the popup menu becomes invisible
                isComboBoxDropped = false;
            }

            public void popupMenuCanceled(PopupMenuEvent e) {
                // This method is called when the popup menu is canceled
                isComboBoxDropped = false;
            }
        });

        jtActieNaam.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtActieNaam.getText().equals("Actienaam") || jtActieNaam.getText().equals("")) {
                    jtActieNaam.setForeground(colorScheme.getDetailColor());
                    jtActieNaam.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtActieNaam.getText().equals("")) {
                    jtActieNaam.setForeground(Color.GRAY);
                    jtActieNaam.setText("Actienaam");
                }
            }
        });

        jtTijdInterval.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtTijdInterval.getText().equals("Intervaltijd") || jtTijdInterval.getText().equals("")) {
                    jtTijdInterval.setForeground(colorScheme.getDetailColor());
                    jtTijdInterval.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtTijdInterval.getText().equals("")) {
                    jtTijdInterval.setForeground(Color.GRAY);
                    jtTijdInterval.setText("Intervaltijd");
                }
            }
        });


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


}


