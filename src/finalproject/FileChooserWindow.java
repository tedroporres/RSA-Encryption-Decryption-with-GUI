/**
 * File Name:  FileChooserWindow.java
 * Purpose:    The FileChooserWindow opens a file explorer that lets the user select
 *             a file in order to obtain its content.
 * Programmer: Pedro Torres
 * Last Updated Date: 11/25/2016
 */
package finalproject;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class FileChooserWindow {
    
    private JFileChooser fileChooser;
    private File selectedFile;
    private Scanner inputFile;
    private String text = "";
    
    /**
     * Constructor
     */
    public FileChooserWindow() {
        
        fileChooser = new JFileChooser("C:/Users");
        
        int status = fileChooser.showSaveDialog(null);
        
        if(status == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            String filename = selectedFile.getPath();
            JOptionPane.showMessageDialog(null, "You selected " + filename);
        }
        else
            System.exit(0);
        
        getText();
        
        new DisplayResult("encrypt", RSA.encrypt(text));
    }
    
    /**
     * The getText method reads the lines in the selected file and saves them 
     * into the text variable.
     */
    private void getText() {
        try {
            inputFile = new Scanner(selectedFile);
            
            while(inputFile.hasNext())
                text += inputFile.nextLine();
            
            inputFile.close();
        }
        catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Error handling the dile: + ex");
        }
    }
}
