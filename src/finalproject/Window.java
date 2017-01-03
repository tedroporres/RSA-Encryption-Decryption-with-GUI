/**
 * File Name:  Window.java
 * Purpose:    The Window class displays the main menu of the RSA Algorithm project.
 *             It gives the user options to select for encrypting and decrypting text.
 * Programmer: Pedro Torres
 * Last Updated Date: 11/25/2016
 */
package finalproject;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;

public class Window extends JFrame {
    
    private JPanel panelNorth;         // where label is located
    private JPanel panelSouth;         // where buttons are located
    private JPanel panelEast;          // text / file option radio buttons
    private JPanel panelWest;          // encrypt / decrypt option radio buttons
    
    private JLabel label1;             // main text of the window
    
    private JRadioButton encryptRadio;
    private JRadioButton decryptRadio;
    private JRadioButton textFileRadio;
    private JRadioButton textRadio;
    
    private JButton continueButton;
    
    /**
     * Constructor.
     */
    public Window() {
        setTitle("RSA Cryptosystem");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        buildPanels();                      // build and style components
        styleComponents();
        setLayout(new FlowLayout());
        
        add(panelNorth);                    // adding panels to the content pane
        add(panelEast);
        add(panelWest);
        add(panelSouth);
        
        setSize(520,310);
        setResizable(false);
        setLocationRelativeTo(null);        // centers the frame
        setVisible(true);
        getContentPane().setBackground(Color.white);
        
        new GenerateKeys();                 // option to generate keys
    }
    
    /**
     * The buildPanels method creates the components of the Window frame.
     */
    private void buildPanels() {
        String message = "<html><p style='margin: 10px 0px 20px 0px; text-align: center;'>"
                + "This program can encrypt and decrypt a text file<br>"
                + "Select an option and then press continue</p></html>";
        
        label1 = new JLabel(message);
        
        encryptRadio = new JRadioButton("Encrypt", true);
        decryptRadio = new JRadioButton("Decrypt");
        encryptRadio.addActionListener(new FileRadioListener());
        decryptRadio.addActionListener(new FileRadioListener());
        
        ButtonGroup group = new ButtonGroup();
        group.add(encryptRadio);
        group.add(decryptRadio);
        
        textRadio = new JRadioButton("Text / Paragraph", true);
        textFileRadio = new JRadioButton("Text file (.txt)");
        
        ButtonGroup group2 = new ButtonGroup();
        group2.add(textRadio);
        group2.add(textFileRadio);
        
        continueButton = new JButton("Continue");
        continueButton.addActionListener(ContinueButtonListener);
        
        panelNorth = new JPanel();
        panelEast = new JPanel();
        panelWest = new JPanel();
        panelSouth = new JPanel();
        
        panelNorth.add(label1);
        panelEast.add(encryptRadio);
        panelEast.add(decryptRadio);
        panelWest.add(textRadio);
        panelWest.add(textFileRadio);
        panelSouth.add(continueButton);
        
    }
    
    /**
     * The styleComponents method enhances the visual look of the components.
     */
    private void styleComponents() {
        label1.setFont(new Font("Helvetica Neue", Font.PLAIN, 22));
        Dimension dimension = new Dimension(200,80);
        
        // Panel North
        panelNorth.setBackground(Color.white);
        
        // Components in panelWest
        panelWest.setPreferredSize(dimension);
        panelWest.setLayout(new GridLayout(2,1));
        panelWest.setBorder(BorderFactory.createTitledBorder(null, "Format", TitledBorder.LEFT, TitledBorder.TOP,new Font("Helvetica Neue", Font.PLAIN, 20) ));
        textRadio.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
        textFileRadio.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
        panelWest.setBackground(Color.white);
        textRadio.setBackground(Color.white);
        textFileRadio.setBackground(Color.white);
        
        // Components in panelEast
        panelEast.setPreferredSize(dimension);
        panelEast.setLayout(new GridLayout(2,1));
        panelEast.setBorder(BorderFactory.createTitledBorder(null, "Option", TitledBorder.LEFT, TitledBorder.TOP,new Font("Helvetica Neue", Font.PLAIN, 20) ));
        encryptRadio.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
        decryptRadio.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
        panelEast.setBackground(Color.white);
        encryptRadio.setBackground(Color.white);
        decryptRadio.setBackground(Color.white);
        
        // Panel south
        continueButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        continueButton.setBackground(Color.white);
        panelSouth.setBackground(Color.white);
    }
    
    /**
     * The ContinueButtonListener class is an event handler for the continue 
     * button. It leads to different results depending on the selected radio buttons.
     */
    ActionListener ContinueButtonListener = event -> { 
            if (encryptRadio.isSelected()) {       // encrypt   . option
                if (textRadio.isSelected())        // encrypt text
                    new TextWindow("encrypt");
                else                               // encryot text in file
                    new FileChooserWindow();       
            }
            else {                                 // decrypt option
                if (textRadio.isSelected())        // decrypt text
                    new TextWindow("decrypt"); 
                else                               // decrypt text in file
                    new FileChooserWindow();
            }
        
    };
    /**
     * The FileRadioListener class is an event handler for the encrypt/decrypt
     * radio buttons. It hides the file option for decryption.
     */
    private class FileRadioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(decryptRadio.isSelected())
                textFileRadio.setVisible(false);
            else
                textFileRadio.setVisible(true);
        }
    }
    
    public static void main(String [] args) {
        new Window();
    }
}
