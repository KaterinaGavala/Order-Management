/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author User
 */
public class MainFrame extends JFrame {
    
    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem openItem, saveItem, saveAsItem,
            exitItem, aboutItem, statisticsItem;
    private TextArea area, pathArea;
    private JButton newOrderBtn,clearOrderBtn;
    private JPanel panel1,panel2, panel3;
    private JLabel pathLbl,newOrderLbl,elementLbl;
    private ArrayList<Order> ordersList;
    private File savedFile;
    
    public MainFrame(){
        
        //Menu Creation

        JMenu fileMenu = new JMenu("Αρχείο");
        JMenu staticsMenu = new JMenu("Στατιστικά");
        JMenu aboutMenu = new JMenu("Σχετικά");

        fileMenu.setMnemonic('A');
        staticsMenu.setMnemonic('B');
        aboutMenu.setMnemonic('C');
        
        menuBar.add(fileMenu);
        menuBar.add(staticsMenu);
        menuBar.add(aboutMenu);
        
        openItem = fileMenu.add("Άνοιγμα αρχείου");
        openItem.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));

        fileMenu.addSeparator();
        
        saveItem = fileMenu.add("Αποθήκευση");
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
        saveAsItem = fileMenu.add("Αποθήκευση Ως...");

        fileMenu.addSeparator();

        exitItem = fileMenu.add("Έξοδος");
        exitItem.setAccelerator(KeyStroke.getKeyStroke('Q', Event.CTRL_MASK));

        statisticsItem = staticsMenu.add("Στατιστικά Παραγγελιών");
        statisticsItem.setAccelerator(KeyStroke.getKeyStroke('P', Event.CTRL_MASK));

        aboutItem = aboutMenu.add("Πληροφορίες");
        aboutItem.setAccelerator(KeyStroke.getKeyStroke('I', Event.CTRL_MASK));

        //Frame's elements
        
        area = new TextArea(25,40);
        pathArea = new TextArea(1,40);

        newOrderBtn = new JButton("Νέα Παραγγελία ");
        clearOrderBtn = new JButton("Καθάρισμα Παραγγελιών");  // clearing order

        pathLbl = new JLabel("Path Αρχείου:\t");
        newOrderLbl = new JLabel("Δημιουργία νεας παραγγελίας:\t");
        elementLbl = new JLabel("ΣΤΟΙΧΕΙΑ ΠΑΡΑΓΓΕΛΙΩΝ\n");


        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        ordersList = new ArrayList();
        
    }
    
    public void prepareUI(){
        //Adding element to frame

        setJMenuBar(menuBar);
        this.add(panel1, BorderLayout.NORTH);
        panel1.add(newOrderLbl);
        panel1.add(newOrderBtn);
        this.add(panel2, BorderLayout.CENTER);
        panel2.setLayout(null);
        panel2.add(elementLbl);
        elementLbl.setBounds(220, 10, 150, 20);
        panel2.add(area);
        area.setBounds(90, 30, 400, 400);
        panel2.add(clearOrderBtn);
        clearOrderBtn.setBounds(180,450,200,30);
        this.add(panel3, BorderLayout.SOUTH);
        panel3.add(pathLbl);
        panel3.add(pathArea);
        
        //Listeners
        
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (savedFile!=null || (savedFile==null && area.getText().isEmpty())) {  //if there is nothing to save or order is saved ask to exit
                    int i = JOptionPane.showConfirmDialog(exitItem,
                            "Do you want to exit the app?");

                    if (i == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                } else if(savedFile==null && !area.getText().isEmpty()) {  // if the orders aren't saved ask to save and exit 
                    int i = JOptionPane.showConfirmDialog(exitItem,
                            "You haven't save the orders. Do you want to save it and then exit?");
                    if (i == JOptionPane.YES_OPTION) {
                        final JFileChooser fcSaveAs = new JFileChooser();
                        int returnVal = fcSaveAs.showSaveDialog(saveItem);

                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            String fileNameSaveAs = fcSaveAs.getSelectedFile().getPath();
                            savedFile = new File(fileNameSaveAs);

                            if (fileNameSaveAs != null && !fileNameSaveAs.isEmpty()) {
                                saveOrdersList(fileNameSaveAs);
                            }
                            System.exit(0);
                        }
                    }
                    else{                                               // if user don't want to save orders ask to exit
                        int x = JOptionPane.showConfirmDialog(exitItem,
                                "Do you want to exit the app?");

                        if (x == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        }
                    }
                }
                
            }
        });
        
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fcOpen = new JFileChooser();
                int returnVal = fcOpen.showOpenDialog(openItem);
                fcOpen.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv")); //filter for choosing CSV files

                if(returnVal==JFileChooser.APPROVE_OPTION){
                    String fileNameOpen = fcOpen.getSelectedFile().getPath();

                    if(fileNameOpen!=null && !fileNameOpen.isEmpty()){
                        openFromFile(fileNameOpen);
                    }
                }
            }
        });
        
        saveAsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ordersList.isEmpty()) {
                    JOptionPane.showMessageDialog(saveAsItem,
                            "Nothing to save",
                            "File access error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                final JFileChooser fcSaveAs = new JFileChooser();
                int returnVal = fcSaveAs.showSaveDialog(saveItem);

                if(returnVal==JFileChooser.APPROVE_OPTION){
                    String fileNameSaveAs = fcSaveAs.getSelectedFile().getPath();
                    savedFile = new File(fileNameSaveAs);

                    if(fileNameSaveAs!=null && !fileNameSaveAs.isEmpty()){
                        saveOrdersList(fileNameSaveAs);     // saveOrdersList method for saving order for the first time in a file or saving order in another file
                    }
                }
            }
        });
        
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (!ordersList.isEmpty()) {   

                    if (savedFile == null) {   //First time saving the file
                        if (ordersList.isEmpty()) {
                            JOptionPane.showMessageDialog(saveAsItem,
                                    "Nothing to save",
                                    "File access error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        final JFileChooser fcSaveAs = new JFileChooser();
                        int returnVal = fcSaveAs.showSaveDialog(saveItem);

                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            String fileNameSaveAs = fcSaveAs.getSelectedFile().getPath();
                            savedFile = new File(fileNameSaveAs);

                            if (fileNameSaveAs != null && !fileNameSaveAs.isEmpty()) {
                                saveOrdersList(fileNameSaveAs);  //method for saving order for the first time in a file
                            }
                        }
                    } else {                                    //Existing saving file
                        saveInSameFile(savedFile);              //saveInSameFile method for saving orders in the same file
                    }

                }
                else {

                    if (ordersList.isEmpty()) {
                        JOptionPane.showMessageDialog(saveAsItem,
                                "Nothing to save",
                                "File access error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                }
            }
        });
        
        newOrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderFrame orderFrame= new OrderFrame();    //Create a new class for new order frame
                orderFrame.prepareUI(ordersList,area);
            }
        });
        
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                InformationFrame infoFrame = new InformationFrame();  //Create a new class for infrormation frame
                infoFrame.prepareUI();
            }
        });
        
        statisticsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               StatisticFrame statFrame = new StatisticFrame();   //Create a new class for statistics frame
               statFrame.prepareUI(ordersList);
            }
        });
        
        clearOrderBtn.addActionListener(new ActionListener() {     //Clearing all the orders existing in the frame 
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setText(null);
                ordersList.clear();
                savedFile = null;
                pathArea.setText(null);
            }
        });
        
        
    }
    
    private void openFromFile(String fileNameOpen){
        
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(fileNameOpen));
            String line = "";
            String[] token;
            
            while(reader.ready()){
                line = reader.readLine();
                token = line.split(",");

                if (token.length==7){
                    Order order = new Order(token[0], token[1], token[2], token[3],
                            token[4], token[5], token[6]);
                    ordersList.add(order);
                    
                }
            }
          
            pathArea.setText(fileNameOpen);
            
            reader.close();
            showList();
                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void showList(){
        for(Order order: ordersList){
            area.append(order.toString());
            area.append("\n");
        }
    }
    
    private void saveOrdersList(String fileNameSaveAs){
        
        try {
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameSaveAs,false));
            
            for(Order order: ordersList){
                writer.write(order.toStringFile());
                writer.newLine();
            }
            writer.close();
            JOptionPane.showMessageDialog(saveAsItem,ordersList.size()
                            +" orders saved to "+fileNameSaveAs," Save Completed",
                    JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void saveInSameFile(File savedFile){
        
        try {
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(savedFile,false));
            
            for(Order order: ordersList){
                writer.write(order.toStringFile());
                writer.newLine();
            }
            writer.close();
            JOptionPane.showMessageDialog(saveAsItem,ordersList.size()
                            +" orders saved to "+savedFile," Save Completed",
                    JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
