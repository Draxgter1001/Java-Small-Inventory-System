
public class Clothing extends Product {

    private String size;
    private String colour;

    //Clothing constructor;
    public Clothing(String productId, String productName, double price, int quantity, String size, String colour) {
        super(productId, productName, price, quantity);
        this.size = size;
        this.colour = colour;
    }

    //Getter methods;
    public String getSize(){
        return size;
    }

    public String getColour(){
        return colour;
    }

    //Setter methods;
    public void setSize(String size){
        this.size = size;
    }

    public void setColour(String colour){
        this.colour = colour;
    }

    @Override
    public String toString(){

        return super.toString() + " Size: " + getSize() + " - Colour: " + getColour();
    }

    @Override
    public int compareTo(Product p) {

        return this.getProductId().compareTo(p.getProductId());
    }
}
