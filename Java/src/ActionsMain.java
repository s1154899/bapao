import raspberry.RaspberryPi;
import raspberry.UploadedScripts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

class ActionsMain extends JPanel implements ActionListener {
    private Font usedFont;
    private JPanel jpAddAction;
    JTabbedPane jtpAction;
//    Main frame;


    private JTextField jtActionName;
    private JTextField jtTimeInterval;

    private JComboBox<String> jcbTime;
    private boolean isComboBoxDropped = false;

    private JButton jbUploadFile;
    private JButton jbSaveAction;

    ArrayList<Action> alActions = new ArrayList<>();

    private String[] tijdInterval = {"Seconden", "Minuten", "Uren", "Dagen"};


    private File f;

    public ActionsMain() {


        this.setBackground(ColorScheme.getSecondaryColor());
        this.setLayout(new BorderLayout());
        try {
            usedFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Login.class.getResourceAsStream("Assets/Comfort.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        //Change colors of selected and unselected pane
        UIManager.put("TabbedPane.selected", ColorScheme.getPrimaryColor());
        UIManager.put("TabbedPane.unselectedTabBackground", ColorScheme.getPrimaryColor());
        //Remove borders
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));

        jpAddAction = new JPanel();
        jpAddAction.setBackground(ColorScheme.getPrimaryColor());
        jpAddAction.setBorder(BorderFactory.createLineBorder(Color.black));
        jpAddAction.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        jtpAction = new JTabbedPane(JTabbedPane.LEFT);
        jtpAction.setForeground(ColorScheme.getDetailColor());
        jtpAction.setBackground(ColorScheme.getPrimaryColor());
        jtpAction.setOpaque(false);
        jtpAction.setFocusable(false);
        add(jtpAction);
        jtpAction.setFont(usedFont.deriveFont(20f));
        jtpAction.add("<html><body><table width='150'>Nieuwe actie +</table></body></html>", jpAddAction);

        JLabel jlNaam = new JLabel("Naam actie:");
        jlNaam.setFont(usedFont.deriveFont(20f));
        jlNaam.setForeground(ColorScheme.getDetailColor());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 40;
        c.ipady = 20;
        jpAddAction.add(jlNaam, c);

        jtActionName = new JTextField();
        jtActionName.setFont(usedFont.deriveFont(15f));
        jtActionName.setBackground(ColorScheme.getSecondaryColor());
        jtActionName.setText("Actienaam");
        jtActionName.setForeground(Color.gray);
        jtActionName.setBorder(new EmptyBorder(2, 15, 2, 15));
        jtActionName.setCaretColor(ColorScheme.getDetailColor()); //TODO setCaretColor op elke JTextfield
        c.gridx = 1;
        jpAddAction.add(jtActionName, c);

        JLabel jlTijd = new JLabel("Tijd interval:");
        jlTijd.setForeground(ColorScheme.getDetailColor());
        jlTijd.setFont(usedFont.deriveFont(20f));
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        jpAddAction.add(jlTijd, c);

        jtTimeInterval = new JTextField();
        jtTimeInterval.setFont(usedFont.deriveFont(15f));
        jtTimeInterval.setBackground(ColorScheme.getSecondaryColor());
        jtTimeInterval.setText("Intervaltijd");
        jtTimeInterval.setForeground(Color.gray);
        jtTimeInterval.setBorder(new EmptyBorder(2, 15, 2, 15));
        jtTimeInterval.setCaretColor(ColorScheme.getDetailColor());
        c.gridx = 1;
        c.gridy = 1;
        jpAddAction.add(jtTimeInterval, c);

        JLabel jlIntegerWarning = new JLabel();
        jlIntegerWarning.setForeground(Color.red);
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 0);
        jpAddAction.add(jlIntegerWarning, c);

        jtTimeInterval.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent k) {
                String value = jtTimeInterval.getText();
                int l = value.length();

                if (k.getKeyChar() >= '0' && k.getKeyChar() <= '9') {
                    if (l < 2) {
                        jtTimeInterval.setEditable(true);
                        jlIntegerWarning.setText("");
                    } else {
                        jtTimeInterval.setEditable(false);
                        jlIntegerWarning.setText("* Maximaal 2 cijfers");
                    }
                }
                else
                {
                    if(k.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || k.getExtendedKeyCode() == KeyEvent.VK_DELETE)
                    {
                        jtTimeInterval.setEditable(true);
                    }
                    else
                    {
                        jtTimeInterval.setEditable(false);
                        jlIntegerWarning.setText("* Vul alleen hele nummers in (integers)[1-9]");
                    }
                }

            }
        });


        String[] tijdInterval = {"Seconden", "Minuten", "Uren", "Dagen"};
        jcbTime = new JComboBox<>(tijdInterval);
        jcbTime.setFocusable(false);
        jcbTime.setSelectedIndex(0);
        jcbTime.setFont(usedFont.deriveFont(15f));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        jpAddAction.add(jcbTime, c);

        jbUploadFile = new JButton("Upload python file");
        jbUploadFile.setFont(usedFont.deriveFont(20f));
        jbUploadFile.setFocusable(false);
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(25, 0, 0, 0);
        jbUploadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();


                try {

                    int returnVal = file.showOpenDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        System.out.println("You chose to open this file: " +
                                file.getSelectedFile().getName());
                        f = file.getSelectedFile();
                        jbUploadFile.setText(f.getName());

                        jbUploadFile.repaint();
                        jbUploadFile.revalidate();
                    }


                } catch (NullPointerException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        jpAddAction.add(jbUploadFile, c);

        jbSaveAction = new JButton("Maak actie aan");
        jbSaveAction.setFont(usedFont.deriveFont(30f));
        jbSaveAction.setFocusable(false);
        jbSaveAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RaspberryPi.copyFileUsingStream(f);

                    if (e.getSource() == jbSaveAction) {
                        Action action = new Action(jtActionName.getText(), Integer.parseInt(jtTimeInterval.getText()), (String) jcbTime.getSelectedItem());

                        System.out.println("jbsaveactionbutton pressed");
                        ActionView newAction = new ActionView(action, alActions, jtpAction);
                        jtpAction.addTab(action.getActionName(), newAction);
                        alActions.add(action);
                        jtpAction.setSelectedIndex(alActions.indexOf(action) + 1);
                        System.out.println(alActions.indexOf(action));

                        UploadedScripts.addNewScript(new UploadedScripts(action.getActionName(), "./scripts/" + f.getName(), action.getTimeUnit(), action.getTimeInterval()));

                    }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(50, 0, 0, 0);
        jpAddAction.add(jbSaveAction, c);

        jbSaveAction.setForeground(ColorScheme.getDetailColor());
        jbUploadFile.setForeground(ColorScheme.getDetailColor());
        jcbTime.setForeground(ColorScheme.getDetailColor());

        jbSaveAction.setBackground(ColorScheme.getPrimaryColor());
        jbUploadFile.setBackground(ColorScheme.getPrimaryColor());
        jcbTime.setBackground(ColorScheme.getPrimaryColor());

        jbSaveAction.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ColorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
        jbUploadFile.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ColorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));


        jbSaveAction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension tempSize = jbSaveAction.getSize();
                jbSaveAction.setBackground(ColorScheme.getSecondaryColor());
                jbSaveAction.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ColorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbSaveAction.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = jbSaveAction.getSize();
                jbSaveAction.setBackground(ColorScheme.getPrimaryColor());
                jbSaveAction.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ColorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbSaveAction.setSize(tempSize);
            }
        });

        jbUploadFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension tempSize = jbUploadFile.getSize();
                jbUploadFile.setBackground(ColorScheme.getSecondaryColor());
                jbUploadFile.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ColorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbUploadFile.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = jbUploadFile.getSize();
                jbUploadFile.setBackground(ColorScheme.getPrimaryColor());
                jbUploadFile.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ColorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbUploadFile.setSize(tempSize);
            }
        });

        jcbTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isComboBoxDropped) {
                    Dimension tempSize = jcbTime.getSize();
                    jcbTime.setBackground(ColorScheme.getSecondaryColor());
                    jcbTime.setSize(tempSize);
                } else {
                    Dimension tempSize = jcbTime.getSize();
                    jcbTime.setBackground(ColorScheme.getPrimaryColor());
                    jcbTime.setSize(tempSize);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Dimension tempSize = jcbTime.getSize();
                jcbTime.setBackground(ColorScheme.getPrimaryColor());
                jcbTime.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = jcbTime.getSize();
                jcbTime.setBackground(ColorScheme.getPrimaryColor());
                jcbTime.setSize(tempSize);
            }
        });

        jcbTime.addPopupMenuListener(new PopupMenuListener() {
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

        jtActionName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtActionName.getText().equals("Actienaam") || jtActionName.getText().equals("")) {
                    jtActionName.setForeground(ColorScheme.getDetailColor());
                    jtActionName.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtActionName.getText().equals("")) {
                    jtActionName.setForeground(Color.GRAY);
                    jtActionName.setText("Actienaam");
                }
            }
        });

        jtTimeInterval.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jtTimeInterval.getText().equals("Intervaltijd") || jtTimeInterval.getText().equals("")) {
                    jtTimeInterval.setForeground(ColorScheme.getDetailColor());
                    jtTimeInterval.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jtTimeInterval.getText().equals("")) {
                    jtTimeInterval.setForeground(Color.GRAY);
                    jtTimeInterval.setText("Intervaltijd");
                }
            }
        });


//        for (UploadedScripts scripts : UploadedScripts.ReadScripts()){ //todo geen idee wat dit doet dus kan het niet aanpassen :(
//            alActions.add(indexActions, scripts.name);
//            alTimeInterval.add(indexTime, scripts.intervalTime);
//
//            System.out.println("jbsaveactionbutton pressed");
//            ActionView newAction = new ActionView(alTimeInterval.get(indexTime), alActions.get(indexActions), alTijdAanduiding.get(indexTijdAanduiding));
//            jtpAction.addTab(alActions.get(indexActions), newAction);
//        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

}


class ActionView extends JPanel {
    Font usedFont;


    public ActionView(Action action, ArrayList alActions, JTabbedPane jtpAction) {
        JButton jbRemoveAction;

        try {
            usedFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Login.class.getResourceAsStream("Assets/Comfort.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        setBackground(ColorScheme.getPrimaryColor());
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel jlTimeInterval = new JLabel("Deze actie runt elke " + action.getTimeInterval() + " " + action.getTimeUnit());
        jlTimeInterval.setFont(usedFont.deriveFont(20f));
        jlTimeInterval.setForeground(ColorScheme.getDetailColor());
        c.gridy = 0;
        add(jlTimeInterval, c);

        JLabel jlIndexes = new JLabel("");
        jlIndexes.setFont(usedFont.deriveFont(20f));
        jlIndexes.setForeground(ColorScheme.getDetailColor());
        c.gridy = 1;
        add(jlIndexes, c);

        jbRemoveAction = new JButton("Remove action");
        jbRemoveAction.setFont(usedFont.deriveFont(30f));
        jbRemoveAction.setFocusable(false);
        jbRemoveAction.setForeground(ColorScheme.getDetailColor());
        jbRemoveAction.setBackground(ColorScheme.getPrimaryColor());
        jbRemoveAction.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ColorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
        jbRemoveAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtpAction.removeTabAt(alActions.indexOf(action) + 1); //+1 to avoid removal of the 'addtab' tab, which has index 0
                alActions.remove(action);

            }
        });
        add(jbRemoveAction);

        jbRemoveAction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension tempSize = jbRemoveAction.getSize();
                jbRemoveAction.setBackground(ColorScheme.getSecondaryColor());
                jbRemoveAction.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ColorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbRemoveAction.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = jbRemoveAction.getSize();
                jbRemoveAction.setBackground(ColorScheme.getPrimaryColor());
                jbRemoveAction.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ColorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbRemoveAction.setSize(tempSize);
            }
        });
    }
}

class Action {
    private String actionName;
    private int timeInterval;
    private String timeUnit;

    public Action(String actionName, int timeInterval, String timeUnit) {
        this.actionName = actionName;
        this.timeInterval = timeInterval;
        this.timeUnit = timeUnit;
    }

    public String getActionName() {
        return actionName;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public String getTimeUnit() {
        return timeUnit;
    }
}



