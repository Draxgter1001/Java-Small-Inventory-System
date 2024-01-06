import java.util.Scanner;

public interface ShoppingManager {

    void runMenu(Scanner input);
    void addProduct(Product product);
    void deleteProduct(String productId);
    void displayProducts();
    void saveToFile();
    void readFromFile();
}
