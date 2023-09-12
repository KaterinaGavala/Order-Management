/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement;

import java.awt.TextField;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class StatisticFrame {
    
    private JFrame statFrame = new JFrame("Order Statistics");
    private JLabel titlelbl, ordersNumberLbl,ordersNetPriceLbl, ordersMixedPriceLbl, codeExpensOrderLbl, codeCheapOrderLbl;
    private TextField ordersNumberTF, ordersNetPriceTF, ordersMixedPriceTF, codeExpensOrderTF, codeCheapOrderTF ;
    private int sum = 0;
    private float totalCost = 0;
    private float mixedCost = 0;
    private float maxPrice = 0;
    private String maxCode = null;
    private float minPrice = 100*100;
    private String minCode = null;

    public StatisticFrame(){
        
        //Creating and setting Frame 
        
        statFrame.setDefaultCloseOperation(statFrame.DISPOSE_ON_CLOSE);
        statFrame.setSize(500,300);
        statFrame.setVisible(true);
        statFrame.setLocationRelativeTo(null);
        
        
        titlelbl = new JLabel("Τα στατιστικά αυτής της παραγγελίας είναι: ");
        ordersNumberLbl = new JLabel("Συνολικός αριθμός παραγγελιών:\t");
        ordersNetPriceLbl = new JLabel("Συνολικό κόστος παραγγελιών(Καθαρό):\t");
        ordersMixedPriceLbl = new JLabel("Συνολικό κόστος παραγγελιών(Μικτό):\t");
        codeExpensOrderLbl = new JLabel("Κωδικός ακριβότερης παραγγελίας:\t");
        codeCheapOrderLbl  = new JLabel("Κωδικός φθηνότερης παραγγελίας:\t");

        ordersNumberTF = new TextField(10);
        ordersNetPriceTF = new TextField(10);
        ordersMixedPriceTF = new TextField(10);
        codeExpensOrderTF= new TextField(10);
        codeCheapOrderTF = new TextField(10);


    }
    
    public void prepareUI(ArrayList<Order> ordersList){
        
        statFrame.setLayout(null);
        statFrame.add(titlelbl);
        titlelbl.setBounds(10,20,300,20);
        statFrame.add(ordersNumberLbl);
        ordersNumberLbl.setBounds(10,50,200,20);
        statFrame.add(ordersNumberTF);
        ordersNumberTF.setBounds(250,50,100,25);
        statFrame.add(ordersNetPriceLbl);
        ordersNetPriceLbl.setBounds(10,90,240,20);
        statFrame.add(ordersNetPriceTF);
        ordersNetPriceTF.setBounds(250,90,100,25);
        statFrame.add(ordersMixedPriceLbl);
        ordersMixedPriceLbl.setBounds(10, 130, 220, 20);
        statFrame.add(ordersMixedPriceTF);
        ordersMixedPriceTF.setBounds(250, 130, 100, 25);
        statFrame.add(codeExpensOrderLbl);
        codeExpensOrderLbl.setBounds(10,170,200,20);
        statFrame.add(codeExpensOrderTF);
        codeExpensOrderTF.setBounds(250,170,100,25);
        statFrame.add(codeCheapOrderLbl);
        codeCheapOrderLbl.setBounds(10,210,200,20);
        statFrame.add(codeCheapOrderTF);
        codeCheapOrderTF.setBounds(250,210,100,25);
        
        if(ordersList!=null && !ordersList.isEmpty()){


                for(Order order : ordersList){
                   
                    sum = ordersList.size();
                    
                    //calculate the cost
                    totalCost += Float.parseFloat(order.getNumberOfProd()) * Float.parseFloat(order.getPrice());

                    //calculate the mixed cost
                    float mixed = order.calculateMixed(order.getPrice(),order.getTax());
                    mixedCost += Float.parseFloat(order.getNumberOfProd()) * mixed;

                    //calculate the max Price
                    if (mixed > maxPrice){
                        maxPrice = mixed;
                        maxCode = order.getOrderCode(); //print this
                    }

                    //calculate the min Price
                    if (mixed < minPrice){
                        minPrice = mixed;
                        minCode = order.getOrderCode(); //print this
                    }
                }
                ordersNumberTF.setText(""+sum);
                ordersNetPriceTF.setText(""+mixedCost+"$");
                codeExpensOrderTF.setText(""+maxCode);
                codeCheapOrderTF.setText(""+minCode);
                ordersMixedPriceTF.setText(""+totalCost+"$");

        }
    }
}
