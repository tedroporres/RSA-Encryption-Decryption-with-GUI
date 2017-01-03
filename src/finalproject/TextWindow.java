/**
 * File Name:  TextWindow.java
 * Purpose:    The TextWindow class provides the user with a graphical text area
 *             where they can enter text that can be encrypted/decrypted.
 * Programmer: Pedro Torres
 * Last Updated Date: 11/25/2016
 */
package finalproject;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;

public class TextWindow extends JFrame{
    
    private JLabel instruction;
    private JTextArea textArea;
    
    private JButton encryptButton;
    private JButton decryptButton;
    private JButton clearButton;
    
    private JScrollPane scroller;
    
    private JPanel labelPanel; 
    private JPanel textPanel;
    private JPanel buttonPanel;
    
    /**
     * Constructor.
     * @param command String with the actions to be performed(encrypt or decrypt) 
     */
    public TextWindow(String command) {
        
        setTitle("RSA Cryptosystem");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        buildGeneralComponents();
       
        if (command.equals("encrypt"))
            buttonPanel.add(encryptButton);
        else
            buttonPanel.add(decryptButton);
        
        
        buttonPanel.add(clearButton);
        
        styleComponents();
        
        add(labelPanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Properties of the frame
        setSize(520,380);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * The buildGeneralComponents method helps to create the components of the
     * TextWindow frame.
     */
    private void buildGeneralComponents() {
        instruction = new JLabel("<html><p style='margin: 10px 0px 20px text-align: center;'>"
                + "Enter the text to be encrypted / decrypted below</p></html>");
        
        textArea = new JTextArea(10, 35);
        
        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");
        clearButton = new JButton("Clear");
        
        //adding Listeners
        encryptButton.addActionListener(new EncryptButtonListener());
        decryptButton.addActionListener(new DecryptButtonListener());
        clearButton.addActionListener(new ClearButtonListener());
        
        scroller = new JScrollPane(textArea);
        
        
        labelPanel = new JPanel();
        textPanel = new JPanel();
        buttonPanel = new JPanel();
        
        labelPanel.add(instruction);
        textPanel.add(scroller);
    }
    
    /**
     * The styleComponents method enhances the visual look of the components.
     */
    private void styleComponents() {
        // labelPanel components
        labelPanel.setBackground(Color.white);
        instruction.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        
        // textLabel components
        textPanel.setBackground(Color.white);
        textArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        // buttonPanel components
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30,0,15,0));
        buttonPanel.setBackground(Color.white);
        encryptButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        encryptButton.setBackground(Color.white);
        decryptButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        decryptButton.setBackground(Color.white);
        clearButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        clearButton.setBackground(Color.white);
       
    }
    
    /**
     * The EncryptButtonListener is an event handler for the encrypt button.
     * It opens a display window with encrypted text.
     */
    private class EncryptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new DisplayResult("encrypt", RSA.encrypt(textArea.getText()));
        }
    }
    
    /**
     * The DecryptButtonListener is an event handler for the decrypt button.
     * It opens a display window with decrypted text.
     */
    private class DecryptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new DisplayResult("decrypt", RSA.decrypt(textArea.getText()));
        }
    }
    
    /**
     * The ClearButtonListener creates an event handler to clear the text in
     * the text area.
     */
    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.setText("");
        }
    }
}
