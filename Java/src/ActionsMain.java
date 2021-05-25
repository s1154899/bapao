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
    private JTabbedPane jtpAction;
//    Main frame;



    private JTextField jtActionName;
    private JTextField jtTimeInterval;

    private JComboBox<String> jcbTime;
    private boolean isComboBoxDropped = false;

    private JButton jbUploadFile;
    private JButton jbSaveAction;

    private ArrayList<String> alActions = new ArrayList<>();
    private int indexActions = 0;
    private ArrayList<Integer> alTimeInterval = new ArrayList<>();
    private int indexTime = 0;

    private String[] tijdInterval = {"Seconden", "Minuten", "Uren", "Dagen"};


    private File f;


    public ActionsMain() {


        this.setBackground(Main.colorScheme.getSecondaryColor());
        this.setLayout(new BorderLayout());
        try {
            usedFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Login.class.getResourceAsStream("Assets/Comfort.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }




        //Change colors of selected and unselected pane
        UIManager.put("TabbedPane.selected", Main.colorScheme.getPrimaryColor());
        UIManager.put("TabbedPane.unselectedTabBackground", Main.colorScheme.getPrimaryColor());
        //Remove borders
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));

        jpAddAction = new JPanel();
        jpAddAction.setBackground(Main.colorScheme.getPrimaryColor());
        jpAddAction.setBorder(BorderFactory.createLineBorder(Color.black));
        jpAddAction.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        jtpAction = new JTabbedPane(JTabbedPane.LEFT);
        jtpAction.setForeground(Main.colorScheme.getDetailColor());
        jtpAction.setBackground(Main.colorScheme.getPrimaryColor());
        jtpAction.setOpaque(false);
        jtpAction.setFocusable(false);
        add(jtpAction);
        jtpAction.setFont(usedFont.deriveFont(20f));
        jtpAction.add("<html><body><table width='150'>Nieuwe actie +</table></body></html>", jpAddAction);

        JLabel jlNaam = new JLabel("Naam actie:");
        jlNaam.setFont(usedFont.deriveFont(20f));
        jlNaam.setForeground(Main.colorScheme.getDetailColor());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 40;
        c.ipady = 20;
        jpAddAction.add(jlNaam, c);

        jtActionName = new JTextField();
        jtActionName.setFont(usedFont.deriveFont(15f));
        jtActionName.setBackground(Main.colorScheme.getSecondaryColor());
        jtActionName.setText("Actienaam");
        jtActionName.setForeground(Color.gray);
        jtActionName.setBorder(new EmptyBorder(2, 15, 2, 15));
        jtActionName.setCaretColor(Main.colorScheme.getDetailColor()); //TODO setCaretColor op elke JTextfield
        c.gridx = 1;
        jpAddAction.add(jtActionName, c);

        JLabel jlTijd = new JLabel("Tijd interval:");
        jlTijd.setForeground(Main.colorScheme.getDetailColor());
        jlTijd.setFont(usedFont.deriveFont(20f));
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 0);
        jpAddAction.add(jlTijd, c);

        jtTimeInterval = new JTextField();//TODO try catch
        jtTimeInterval.setFont(usedFont.deriveFont(15f));
        jtTimeInterval.setBackground(Main.colorScheme.getSecondaryColor());
        jtTimeInterval.setText("Intervaltijd");
        jtTimeInterval.setForeground(Color.gray);
        jtTimeInterval.setBorder(new EmptyBorder(2, 15, 2, 15));
        jtTimeInterval.setCaretColor(Main.colorScheme.getDetailColor());
        c.gridx = 1;
        c.gridy = 1;
        jpAddAction.add(jtTimeInterval, c);

        JLabel jlIntegerWarning = new JLabel();
        jlIntegerWarning.setForeground(Color.red);
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 0);
        jpAddAction.add(jlIntegerWarning, c);

        jtTimeInterval.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = jtTimeInterval.getText();
                int l = value.length();

                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    jtTimeInterval.setEditable(true);
                    jlIntegerWarning.setText("");
                } else {
                    jtTimeInterval.setEditable(false);
                    jlIntegerWarning.setText("* Vul alleen hele nummers in (integers)[1-9]");
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
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            file.getSelectedFile().getName());
                    f = file.getSelectedFile();
                    jbUploadFile.setText(f.getName());

                    jbUploadFile.repaint();
                    jbUploadFile.revalidate();
                }




                } catch (NullPointerException ioException ) {
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
                        alActions.add(indexActions, jtActionName.getText());
                        alTimeInterval.add(indexTime, Integer.parseInt(jtTimeInterval.getText()));

                        System.out.println("jbsaveactionbutton pressed");
                        ActionView newAction = new ActionView(alTimeInterval.get(indexTime));
                        jtpAction.addTab(alActions.get(indexActions), newAction);


                        UploadedScripts.addNewScript(new UploadedScripts(jtActionName.getText(),"./scripts/"+f.getName(),tijdInterval[jcbTime.getSelectedIndex()],alTimeInterval.get(indexTime)));

                        for(RaspberryPi pi : RaspberryPi.connectedPis){
                            pi.databaseCon.uploadScript(jtActionName.getText(),f.getName(),tijdInterval[jcbTime.getSelectedIndex()],alTimeInterval.get(indexTime));
                        }

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

        jbSaveAction.setForeground(Main.colorScheme.getDetailColor());
        jbUploadFile.setForeground(Main.colorScheme.getDetailColor());
        jcbTime.setForeground(Main.colorScheme.getDetailColor());

        jbSaveAction.setBackground(Main.colorScheme.getPrimaryColor());
        jbUploadFile.setBackground(Main.colorScheme.getPrimaryColor());
        jcbTime.setBackground(Main.colorScheme.getPrimaryColor());

        jbSaveAction.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Main.colorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
        jbUploadFile.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Main.colorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));


        jbSaveAction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension tempSize = jbSaveAction.getSize();
                jbSaveAction.setBackground(Main.colorScheme.getSecondaryColor());
                jbSaveAction.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Main.colorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbSaveAction.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = jbSaveAction.getSize();
                jbSaveAction.setBackground(Main.colorScheme.getPrimaryColor());
                jbSaveAction.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Main.colorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbSaveAction.setSize(tempSize);
            }
        });

        jbUploadFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Dimension tempSize = jbUploadFile.getSize();
                jbUploadFile.setBackground(Main.colorScheme.getSecondaryColor());
                jbUploadFile.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Main.colorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbUploadFile.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = jbUploadFile.getSize();
                jbUploadFile.setBackground(Main.colorScheme.getPrimaryColor());
                jbUploadFile.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Main.colorScheme.getDetailColor()), new EmptyBorder(5, 2, 5, 2)));
                jbUploadFile.setSize(tempSize);
            }
        });

        jcbTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isComboBoxDropped) {
                    Dimension tempSize = jcbTime.getSize();
                    jcbTime.setBackground(Main.colorScheme.getSecondaryColor());
                    jcbTime.setSize(tempSize);
                } else {
                    Dimension tempSize = jcbTime.getSize();
                    jcbTime.setBackground(Main.colorScheme.getPrimaryColor());
                    jcbTime.setSize(tempSize);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Dimension tempSize = jcbTime.getSize();
                jcbTime.setBackground(Main.colorScheme.getPrimaryColor());
                jcbTime.setSize(tempSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Dimension tempSize = jcbTime.getSize();
                jcbTime.setBackground(Main.colorScheme.getPrimaryColor());
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
                    jtActionName.setForeground(Main.colorScheme.getDetailColor());
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
                    jtTimeInterval.setForeground(Main.colorScheme.getDetailColor());
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


        for (UploadedScripts scripts : UploadedScripts.ReadScripts()){
            alActions.add(indexActions, scripts.name);
            alTimeInterval.add(indexTime, scripts.intervalTime);

            System.out.println("jbsaveactionbutton pressed");
            ActionView newAction = new ActionView(alTimeInterval.get(indexTime));
            jtpAction.addTab(alActions.get(indexActions), newAction);
        }



    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}


class ActionView extends JPanel {


    Font usedFont;


    public ActionView(int timeInterval) {



        try {
            usedFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Login.class.getResourceAsStream("Assets/Comfort.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        setBackground(Main.colorScheme.getPrimaryColor());
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel jlTimeInterval = new JLabel("Deze actie runt elke " + timeInterval + " seconden");
        jlTimeInterval.setFont(usedFont.deriveFont(20f));
        jlTimeInterval.setForeground(Main.colorScheme.getDetailColor());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        add(jlTimeInterval);


    }


}



