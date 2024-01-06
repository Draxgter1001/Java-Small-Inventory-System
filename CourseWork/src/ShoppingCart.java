import java.util.*;
public class ShoppingCart {

    private ArrayList<Product> cartProducts;
    private double subTotal;
    private double discountPrice;
    protected int electronicCount = 0;
    protected int clothingCount = 0;
    private double totalCost;
    public ShoppingCart(){

        cartProducts = new ArrayList<>();
    }

    public void addProduct(Product productAdd){
        boolean productExist = false;
        for(Product cartProduct : cartProducts){
            if(cartProduct.getProductId().equals(productAdd.getProductId())){
                int newQuantity = cartProduct.getQuantity() + 1;
                cartProduct.setQuantity(newQuantity);
                cartProduct.updateDisplayPrice();
                productExist = true;
                break;
            }
        }

        if(!productExist){  //Create a clone of the product in the shopping cart, so it can be used independently.
            Product productCopy = cloneProduct(productAdd);
            productCopy.updateDisplayPrice();
            cartProducts.add(productCopy);
        }

        totalCostCalculation();
    }

    public void totalCostCalculation(){
        double subTotal= 0;

        //Calculate subtotal and count the products in each category
        for(Product product : cartProducts){
            subTotal += product.getPrice() * product.getQuantity();
            this.subTotal = subTotal;
            if(product instanceof Electronics){
                electronicCount++;
            } else if (product instanceof Clothing) {
                clothingCount++;
            }
        }

        totalCost = this.subTotal - this.discountPrice;
    }

    //Getter methods
    public double getSubTotal(){

        return subTotal;
    }

    public ArrayList<Product> getCartProducts() {

        return cartProducts;
    }

    public double getDiscountPrice(){

        //Apply discount when applicable
        if(this.electronicCount >= 3){
            discountPrice = getSubTotal() * 0.2;
        }
        if(this.clothingCount >= 3){
            discountPrice = getSubTotal() * 0.2;
        }
        return discountPrice;
    }

    public double getTotalCost(){

        return this.totalCost - getDiscountPrice();
    }

    // Method to clone a Product while preserving its specific type and attributes
    private Product cloneProduct(Product product) { //Method to clone the product
        if (product instanceof Electronics) {
            Electronics electronics = (Electronics) product;
            return new Electronics(electronics.getProductId(), electronics.getProductName(), electronics.getPrice(), 1, electronics.getBrand(), electronics.getWarrantyPeriod());
        } else if (product instanceof Clothing) {
            Clothing clothing = (Clothing) product;
            return new Clothing(clothing.getProductId(), clothing.getProductName(), clothing.getPrice(), 1, clothing.getSize(), clothing.getColour());
        }
        return product;
    }

}
