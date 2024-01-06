import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class Product implements Comparable<Product>, Serializable {
    private String productId;
    private String productName;
    private int quantity;
    private double price;
    private String category;

    private double displayPrice; //Display prices in the GUI shopping cart


    //Product constructor
    public Product(String productId, String productName, double price, int quantity){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    //Method to update display price
    public void updateDisplayPrice(){
        this.displayPrice = this.getPrice() * this.getQuantity();
    }

    //Getter methods
    public String getProductId(){

        return productId;
    }

    public String getProductName(){

        return productName;
    }

    public int getQuantity(){

        return quantity;
    }

    public String getCategory(){

        return category;
    }
    public double getPrice(){

        return price;
    }

    public double getDisplayPrice(){
        return displayPrice;
    }

    //Setter methods
    public void setProductId(String productId){

        this.productId = productId;
    }

    public void setProductName(String productName){

        this.productName = productName;
    }

    public void setQuantity(int quantity){
        if(quantity > 0){
            this.quantity = quantity;
        }else{
            System.out.println("Product quantity can't be less than 0");
        }

    }

    public void setCategory(String category){

        this.category = category;
    }

    public void setPrice(double price){
        if(price > 0){
            this.price = price;
        }else{
            System.out.println("Product price can't be less than 0");
        }
    }

    @Override
    public String toString(){
        //Formatting the price correctly and displaying it in UK currency
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.UK);
        String myPrice = currency.format(getPrice());
        return "Product ID: " + getProductId() + " - Name: " + getProductName() + " - Category: " + getCategory() + " - Price: " + myPrice + " - Quantity: " + getQuantity() + " - ";
    }
}
