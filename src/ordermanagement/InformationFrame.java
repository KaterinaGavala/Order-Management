/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class InformationFrame {

    private JFrame infoFrame = new JFrame("Information");
    private JLabel infoLbl,developerLbL, imageLbl;
   
    
    public InformationFrame() {
        
        //Creating and setting Frame 
        
        infoFrame.setDefaultCloseOperation(infoFrame.DISPOSE_ON_CLOSE);
        infoFrame.setSize(500,300);
        infoFrame.setVisible(true);
        infoFrame.setLocationRelativeTo(null);
        
        //Frame's elements

        infoLbl = new JLabel("Πληροφορίες σχετικά με τον κατασκευαστή και την εφαρμογή:\t");
        developerLbL = new JLabel("Κατερίνα Γαβαλά, ΑΜ 171021, Χρόνος δημιουργίας εφαρμογής: 1 εβδομάδα");
        ImageIcon icon = new ImageIcon("C:\\Users\\User\\desktop\\desktop.jpg");
        imageLbl = new JLabel(icon);
        
        
    }
    
    public void prepareUI(){
        
        //Adding elements
        
        infoFrame.setLayout(null);
        infoFrame.add(infoLbl);
        infoLbl.setBounds(10,20,400,30);
        infoFrame.add(developerLbL);
        developerLbL.setBounds(10,40,450,30);
        infoFrame.add(imageLbl);
        imageLbl.setBounds(10, 70, 300, 300);
        
    }
    
}
