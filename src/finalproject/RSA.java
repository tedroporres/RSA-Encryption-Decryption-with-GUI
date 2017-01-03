/**
 * File Name:  RSA.java
 * Purpose:    The RSA class provides methods that can be use to apply the RSA
 *             crypto system. 
 * Programmer: Pedro Torres
 * Last Updated Date: 11/27/2016
 */
package finalproject;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import javax.swing.JOptionPane;

public class RSA {
   
    private static BigInteger[] publicKey = new BigInteger[2];
    private static BigInteger[] privateKey = new BigInteger[2];
        
    protected static byte[] encrypted;   // array of encrypted bytes.
    protected static byte[] decrypted;   // array od cevrypted bytes.
        
    private static File file;
    private static Scanner inputFile;  // to read from the files
    
    /**
     * The encrypt method manipulates text to encrypt it.
     * @param text A string to be encrypted.
     * @return encrypted bytes converted to a String.
     */
    public static String encrypt(String text) {
        getPublicKey();
        encrypted = encrypt(text.getBytes());
        
        return bytesToString(encrypted);
    }
    /**
     * The decrypt method decrypts an encrypted array of bytes.
     * @return String with decrypted bytes.
     */    
    public static String decrypt(String text) {
        getPrivateKey();
        
        if(!text.equals(bytesToString(encrypted)))
            return "Could not decode the previous message. Redo the proccess "
                    + "and try to decode the given encrypted message.";
        else {
            decrypted = decrypt(encrypted);
        
            return new String(decrypted);
        }
    }    
    
    /**
     * The bytesToString method is a helper that converts bytes to String.
     * @param encrypted A byte array
     * @return test A String with bytes converted to String type.
     */
    public static String bytesToString(byte[] encrypted) {
        String test = "";

        for (byte b : encrypted) {
            test += Byte.toString(b);
        }

        return test;
    }
    
    /**
     * The encrypt method applies the RSA algorithm to encrypt a message
     * @param message A byte array with the bytes of the message to encrypt.
     * @return an encrypted byte array.
     */
    private static byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(publicKey[0], publicKey[1]).toByteArray();
    }

    /**
     * The decrypt method applies the RSA algorithm to decrypt a message.
     * @param message A byte array with the bytes of the message to decrypt.
     * @return a decrypted byte array.
     */
    private static byte[] decrypt(byte[] message) {
        return (new BigInteger(message)).modPow(privateKey[0], privateKey[1]).toByteArray();
    }
    
    /**
     * The getPublicKey method initializes the array that holds the public key.
     */
    private static void getPublicKey() {
        try {
            file = new File("public_key.txt");
            inputFile = new Scanner(file);
            
            int index = 0;
            
            while (inputFile.hasNext()) {
                publicKey[index] = new BigInteger(inputFile.nextLine());
                index++;
            }
            inputFile.close();
        }
        catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Error while opening the file.");
        }
    }
    /**
     * The getPrivateKey method initializes the array that holds the private key.
     */    
    private static void getPrivateKey() {
        try {
            file = new File("private_key.txt");
            inputFile = new Scanner(file);
            
            int index = 0;
            
            while (inputFile.hasNext()) {
                privateKey[index] = new BigInteger(inputFile.nextLine());
                index++;
            }
            inputFile.close();
        }
        catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Error while opening the file.");
        }
    }
}
