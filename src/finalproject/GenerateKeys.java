/**
 * File Name:  GenerateKeys.java
 * Purpose:    The GenerateKeys class displays a windows that asks the user if
 *             he/she wants to generate new public and private keys.
 * Programmer: Pedro Torres
 * Last Updated Date: 11/25/2016
 */
package finalproject;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.io.*;
import java.math.BigInteger;

public class GenerateKeys extends JFrame {
    private JPanel promptPanel;
    private JPanel keyButtonPanel;
    
    private JLabel prompt;
    private JButton newKeyButton;
    private JButton existingKeyButton;
    
    private BigInteger p;     // a random prime number
    private BigInteger q;     // a random prime number
    private BigInteger N;     // product of p and q
    private BigInteger phi;   // phi of N
    private BigInteger e;     // e = encryption. Used for public key
    private BigInteger d;     // d = decryption. Used fot private key
    private int bitlength = 1024; // length of bit, used in BigInteger prime generator 
    private static Random rand = new Random();
    
    /**
     * Constructor.
     */
    public GenerateKeys() {
        setTitle("RSA Cryptosystem");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout(new GridLayout(2,1));
         
        buildComponents();
        styleComponents();
        
        add(promptPanel);
        add(keyButtonPanel);
        
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * The buildCompenents method is a helper to create components of the frame.
     */
    public void buildComponents() {
        prompt = new JLabel("<html><p style='margin: 5px 5px 15px 5px; font-size: 16px;'>"
                + "Do you want to use existing keys or generate new keys ?</p></html>");
        
        // buttons
        newKeyButton = new JButton("New Keys");
        newKeyButton.addActionListener(new KeyButtonListener());
        existingKeyButton = new JButton("Existing Keys");
        existingKeyButton.addActionListener(new KeyButtonListener());
        
        
        //panels
        promptPanel = new JPanel();
        keyButtonPanel = new JPanel();
        
        // adding components to panels
        promptPanel.add(prompt);
        keyButtonPanel.add(newKeyButton);
        keyButtonPanel.add(existingKeyButton);
        
    }
    
    /**
     * The styleComponents method is a helper that styles the components in the 
     * frame.
     */
    public void styleComponents() {
        // prompt panel components
        promptPanel.setBackground(Color.white);
        prompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        
        // key buttons panel
        keyButtonPanel.setBackground(Color.white);
        newKeyButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        newKeyButton.setBackground(Color.white);
        existingKeyButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        existingKeyButton.setBackground(Color.white);
    }
    
    /**
     * The generateKeys method initializes the variables and performs the
     * necessary calculations for the generation of the public and private keys.
     * It uses the methods in the BigInteger class.
     */
    private void generateKeys() {  
        rand = new Random();
        p = BigInteger.probablePrime(bitlength, rand); 
        q = BigInteger.probablePrime(bitlength, rand);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, rand);
        
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }
        
        d = e.modInverse(phi);
    }
    
    /**
     * The KeyButtonListener is and event that handles two buttons. One to generate
     * keys, and the other keep the keys as they are.
     */
    private class KeyButtonListener implements ActionListener { 
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                if (event.getSource() == newKeyButton) {
                    generateKeys();
                    PrintWriter publicKeyWriter = new PrintWriter("public_key.txt");
                    PrintWriter privateKeyWriter = new PrintWriter("private_key.txt");
                    
                    publicKeyWriter.println(e);
                    publicKeyWriter.println(N);
                    
                    privateKeyWriter.println(d);
                    privateKeyWriter.println(N);
                    
                    publicKeyWriter.close();
                    privateKeyWriter.close();
                    JOptionPane.showMessageDialog(null, "New public and private keys were generated.");
                } 
                
                setVisible(false);
            }
            catch(IOException ex) {
                JOptionPane.showMessageDialog(null, "Error while opening the file.");
            }
        }
        
    }
    
}
