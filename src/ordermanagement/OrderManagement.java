/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement;

import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class OrderManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here 
        MainFrame frame = new MainFrame();
        frame.setSize(600, 650);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.prepareUI();
        frame.setTitle("Order Management Window");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
