
public class Electronics extends Product {

    private String brand;
    private String warrantyPeriod;

    //Electronics constructor;
    public Electronics(String productId, String productName, double price, int quantity, String brand, String warrantyPeriod) {
        super(productId, productName, price, quantity);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    //Getter methods;
    public String getBrand(){
        return brand;
    }

    public String getWarrantyPeriod(){

        return warrantyPeriod;
    }

    //Setter methods;
    public void setBrand(String brand){

        this.brand = brand;
    }

    public void setWarrantyPeriod(String warrantyPeriod){

        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString(){
        return super.toString() + " Brand: " + getBrand() + " - Warranty Period: " + getWarrantyPeriod();
    }

    @Override
    public int compareTo(Product p) {

        return this.getProductId().compareTo(p.getProductId());
    }
}
