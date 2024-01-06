import java.io.*;
import java.util.*;
public class WestminsterShoppingManager implements ShoppingManager {

    //ArrayList to store products
    private ArrayList<Product> listProducts;

    //Maximum number of products
    private final int maxNumProducts;

    private static boolean exit;

    public WestminsterShoppingManager(int maxQuantity){
        this.listProducts = new ArrayList<>();
        this.maxNumProducts = maxQuantity;
        exit = true;

    }
    @Override
    public void runMenu(Scanner input){
        System.out.println("Shopping Manager");

        String electronicId;
        String clothId;

        int option;
        int option2;

        while(exit){
            try{
                System.out.println("Enter 1 to add a product.");
                System.out.println("Enter 2 to delete a product.");
                System.out.println("Enter 3 to display all the products.");
                System.out.println("Enter 4 to open the Westminster Shopping Center app.");
                System.out.println("Enter 5 to exit.");
                System.out.print("Please enter your option: ");
                try{
                    option = input.nextInt();
                }catch (InputMismatchException e){
                    input.next();
                    System.out.println("Option entered is invalid.");
                    System.out.println();
                    continue;
                }

                input.nextLine();

                switch (option){

                    case 1 :

                        System.out.println("Enter 1 if you want to add an electronic.");
                        System.out.println("Enter 2 if you want to add a piece of clothing.");
                        System.out.print("Please enter your option: ");

                        try {
                            option2 = input.nextInt();
                        }catch (InputMismatchException e){
                            input.next();
                            System.out.println("Option entered is invalid.");
                            System.out.println();
                            continue;
                        }
                        if(option2 < 1 || option2 > 2){
                            System.out.println("Option entered is invalid.");
                            continue;
                        }
                        input.nextLine();

                        switch (option2){
                            case 1:

                                System.out.print("Enter the product ID: ");
                                electronicId = input.nextLine();
                                System.out.print("Enter the electronic name: ");
                                String electronicName = input.nextLine();
                                System.out.print("Enter the electronic price: ");
                                double electronicPrice = input.nextDouble();
                                input.nextLine();
                                System.out.print("Enter the electronic brand: ");
                                String electronicBrand = input.nextLine();
                                System.out.print("Enter the electronic warranty period: ");
                                String electronicWarrantyPeriod = input.nextLine();
                                System.out.print("Enter the quantity: ");
                                int electronicQuantity = input.nextInt();

                                Electronics electronic = new Electronics(electronicId, electronicName, electronicPrice, electronicQuantity, electronicBrand, electronicWarrantyPeriod);
                                electronic.setProductId(electronicId);
                                electronic.setProductName(electronicName);
                                electronic.setPrice(electronicPrice);
                                electronic.setQuantity(electronicQuantity);
                                electronic.setCategory("Electronics");
                                electronic.setBrand(electronicBrand);
                                electronic.setWarrantyPeriod(electronicWarrantyPeriod);

                                if(electronic.getPrice() < 0 || electronic.getQuantity() < 0){
                                    System.out.println("Product has not been added, please try again.");
                                    break;
                                }else{
                                    addProduct(electronic);
                                }

                                break;
                            case 2:
                                System.out.print("Enter the product ID: ");
                                clothId = input.nextLine();
                                System.out.print("Enter the cloth name: ");
                                String clothName = input.nextLine();
                                System.out.print("Enter the cloth price: ");
                                double clothPrice = input.nextDouble();
                                System.out.print("Enter the cloth size: ");
                                String clothSize = input.next();
                                input.nextLine();
                                System.out.print("Enter the cloth colour: ");
                                String clothColour = input.nextLine();
                                System.out.print("Enter the quantity: ");
                                int clothQuantity = input.nextInt();

                                Clothing cloth = new Clothing(clothId, clothName, clothPrice, clothQuantity , clothSize, clothColour);
                                cloth.setProductId(clothId);
                                cloth.setProductName(clothName);
                                cloth.setPrice(clothPrice);
                                cloth.setQuantity(clothQuantity);
                                cloth.setCategory("Clothing");
                                cloth.setSize(clothSize);
                                cloth.setColour(clothColour);

                                if(cloth.getPrice() < 0 || cloth.getQuantity() < 0){
                                    System.out.println("Product has not been added, please try again.");
                                    break;
                                }else{
                                    addProduct(cloth);
                                }

                                break;
                        }

                        System.out.println();

                        break;
                    case 2:
                        System.out.print("Please enter the product ID: ");
                        String productId= input.nextLine();

                        deleteProduct(productId);
                        System.out.println();

                        break;
                    case 3:
                        displayProducts();
                        System.out.println();

                        break;
                    case 4:
                        runGui();
                        break;
                    case 5:
                        exit = false;
                        saveToFile();

                        break;
                    default:
                        System.out.println("Option entered is invalid.");
                        System.out.println();

                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input.");
                System.out.println();
                input.next();
            }
        }
    }
    @Override
    public void addProduct(Product product) {
        //Checking if there are any space left to add a product
        if(listProducts.size() < maxNumProducts){
            //Checking if the product ID is present in the list
            if(!searchProductId(product.getProductId())){
                listProducts.add(product);
                System.out.println("The following product has been added.");
            }else{
                System.out.println("ID already exist. Please use a different ID.");
            }

        }else{
            System.out.println("No more space left, please remove an already existing product to add a new one.");
        }

    }

    @Override
    public void deleteProduct(String productId) {
        boolean found = false;
        //Iterating through the list to find the product ID
        for(int i = 0; i < listProducts.size(); i++){
            if(listProducts.get(i).getProductId().equals(productId)){
                listProducts.remove(i);
                found = true;
            }
        }

        //Messages based on product's deletion status
        if(found){
            System.out.println("The selected product has been deleted.");
        }else {
            System.out.println("Product with ID " + productId + " does not exists.");
        }
        System.out.println("Number of products listed: " + listProducts.size());
    }

    @Override
    public void displayProducts() {
        //Checking if the list is empty
        if(listProducts.size() == 0){
            System.out.println("There are no product saved.");
        }else{
            //Sorting the product in ascending and alphabetical order and displaying them
            Collections.sort(listProducts);
            System.out.println("Here are the products: ");
            for(int i = 0; i < listProducts.size(); i++){
                System.out.println(" - " + listProducts.get(i));
            }
        }
    }

    //Method to check if the product ID already exist
    public boolean searchProductId(String productId){
        for(int i = 0; i < listProducts.size(); i++){
            if(listProducts.get(i).getProductId().equals(productId)){
                return true;
            }
        }
        return false;
    }
    @Override
    public void saveToFile() {
        try {
            FileOutputStream writeData = new FileOutputStream("products.csv");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            //Writing each product to the file
            for (Product product : listProducts) {
                writeStream.writeObject(product);
            }
            writeStream.close();

        } catch (IOException e) {
            System.err.println("Error saving products to the file: " + e.getMessage());
        }
    }

    @Override
    public void readFromFile() {
        try {
            FileInputStream readData = new FileInputStream("products.csv");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            //Reading the saved products from the file
            while (true) {
                try {
                    Product product = (Product) readStream.readObject();
                    listProducts.add(product);
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }
            readStream.close();

        } catch (IOException e) {
            System.err.println("Error reading products from the file: " + e.getMessage());
        }
    }

    //Method to call the main GUI
    public void runGui(){

        new ShoppingApp(listProducts);
    }


}
