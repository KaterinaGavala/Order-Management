 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement;

/**
 *
 * @author User
 */
public class Order {
    private String appID,orderCode,date,clientName,
            itemName,numberOfProd,price,tax;
    private float mixedPrice;
    
    public Order(){
        
    }
    
    public Order(String orderCode,String date, String clientName,String itemName
            , String numberOfProd, String price, String tax){
        
        this.orderCode = orderCode;
        this.date = date;
        this.clientName = clientName;
        this.itemName = itemName;
        this.numberOfProd = numberOfProd;
        this.price = price;
        this.tax = tax;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getNumberOfProd() {
        return numberOfProd;
    }

    public void setNumberOfProd(String numberOfProd) {
        this.numberOfProd = numberOfProd;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {                                              //overriding toSting for showing the File in the frame
        return orderCode+"   "+date+"   "+clientName+"   "+itemName+
                "   "+numberOfProd+"   "+price+"$"+"   "+tax+"%";
    }
    
    public String toStringFile() {                                          //overriding toSting for saving the File
        return  appID+";"+orderCode+";"+date+";"+clientName+";"+itemName+
                ";"+numberOfProd+";"+price+";"+tax;
    }
    
    public float calculateMixed(String price, String tax) {                   //calculating mixed price
        float pr = Float.parseFloat(price);                                   // convert String price to float 
        float t = Float.parseFloat(tax);                                      // convert String tax to float
        mixedPrice = pr + pr * t / 100 ;
        return mixedPrice;
    }
}
