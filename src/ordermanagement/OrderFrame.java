/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement;

import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class OrderFrame {
    private JFrame orderFrame = new JFrame("New Order");
    private JLabel appIdLbl,orderCodeLbl, dateLbL, companyNameLbl
            , prodLbl, numberProdLbl, priceLbl, taxLbl ;
    private JButton saveBtn,clearBtn;
    private TextField appIdField, orderCodeField, dateField, companyNameField,
            prodField, numberProdField, priceField, taxField;
    
    public OrderFrame(){
        
        //Creating and setting Frame 
        
        super();
        orderFrame.setDefaultCloseOperation(orderFrame.DISPOSE_ON_CLOSE);
        orderFrame.setSize(700,200);
        orderFrame.setVisible(true);
        orderFrame.setLocationRelativeTo(null);

        //Frame's elements
        
        appIdLbl = new JLabel("App ID:\t");
        orderCodeLbl = new JLabel("Order ID:\t");
        dateLbL = new JLabel("Order Date:\t");
        companyNameLbl = new JLabel("Client Name:\t");
        prodLbl = new JLabel("Product Name:\t");
        numberProdLbl = new JLabel("Units Count:\t");
        priceLbl = new JLabel("Net Item Price:\t");
        taxLbl = new JLabel("Tax Percentage:\t");

        appIdField = new TextField(20);
        appIdField.setText("171021");
        orderCodeField = new TextField(20);
        dateField = new TextField(20);
        companyNameField = new TextField(20);
        prodField = new TextField(20);
        numberProdField = new TextField(10);
        priceField = new TextField(10);
        taxField = new TextField(10);

        saveBtn = new JButton("Save Order");
        clearBtn = new JButton("Clear");

    }
    
    public void prepareUI(ArrayList<Order> ordersList, TextArea area){
           
        //Adding elements
        
        orderFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
        orderFrame.add(appIdLbl);
        orderFrame.add(appIdField);
        orderFrame.add(orderCodeLbl);
        orderFrame.add(orderCodeField);
        orderFrame.add(dateLbL);
        orderFrame.add(dateField);
        orderFrame.add(companyNameLbl);
        orderFrame.add(companyNameField);
        orderFrame.add(prodLbl);
        orderFrame.add(prodField);
        orderFrame.add(numberProdLbl);
        orderFrame.add(numberProdField);
        orderFrame.add(priceLbl);
        orderFrame.add(priceField);
        orderFrame.add(taxLbl);
        orderFrame.add(taxField);
        orderFrame.add(saveBtn);
        orderFrame.add(clearBtn);
        
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String appID = appIdField.getText();
                String orderCode = orderCodeField.getText();
                String date = dateField.getText();
                String client = companyNameField.getText();
                String item = prodField.getText();
                String units = numberProdField.getText();
                String price = priceField.getText();
                String tax = taxField.getText();

                if((orderCode!=null && !orderCode.isEmpty())&&(date!=null && !date.isEmpty())
                        &&(client!=null && !client.isEmpty())&&(item!=null && !item.isEmpty())&&
                        (units!=null && !units.isEmpty())&&(price!=null && !price.isEmpty()) &&
                        (tax!=null && !tax.isEmpty())) {
                    Order order = new Order();
                    order.setAppID(appID);
                    order.setOrderCode(orderCode);
                    order.setDate(date);
                    order.setClientName(client);
                    order.setItemName(item);
                    order.setNumberOfProd(units);
                    order.setPrice(""+Float.parseFloat(price));
                    order.setTax(tax);

                    ordersList.add(order);
                    area.append(order.toString());
                    area.append("\n");


                }
                else{
                    JOptionPane.showMessageDialog(saveBtn,
                            "One or more fields are empty. Try again!",
                            "File access error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }
        });
        
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                orderCodeField.setText("");
                dateField.setText("");
                companyNameField.setText("");
                prodField.setText("");
                numberProdField.setText("");
                priceField.setText("");
                taxField.setText("");
            }
        });
        
    }
}
