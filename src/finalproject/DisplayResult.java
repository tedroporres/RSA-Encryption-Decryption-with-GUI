/**
 * File Name:  DisplayResult.java
 * Purpose:    The DisplayResult class displays a windows with a text field where
 *             the result of the RSA algorithm will be displayed.
 * Programmer: Pedro Torres
 * Last Updated Date: 11/25/2016
 */
package finalproject;
import java.awt.*;
import javax.swing.*;


public class DisplayResult extends JFrame {
    private JPanel panelNorth;
    private JPanel panelCenter;
    private JScrollPane scroller;
    
    private JLabel label;
    private JTextArea resultArea;
    
    /**
     * Constructor.
     * @param command Controls the text to be displayed. 
     * @param result  Encrypted / decrypted text.
     */
    public DisplayResult(String command, String result) {
        setTitle("RSA Cryptosystem");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        if (command.equals("encrypt"))
            label = new JLabel("<html><p style='margin: 10px 0px 20px text-align: center;'>"
                    + "This is the encrypted text</p></html");
        else
            label = new JLabel("<html><p style='margin: 10px 0px 20px text-align: center;'>"
                    + "This is the decrypted text</p></html");
        
        resultArea = new JTextArea(10, 35);
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        scroller = new JScrollPane(resultArea);
        
        // style for components
        panelNorth.setBackground(Color.WHITE);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        
        panelCenter.setBackground(Color.WHITE);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setText(result);
        
        panelNorth.add(label);
        panelCenter.add(scroller);
        
        add(panelNorth, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        
        // properties of frame
        setSize(520,380);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
