import java.util.*;
import java.io.*;
public class Main {
    private static Scanner input = new Scanner(System.in);
    private static WestminsterShoppingManager manager = new WestminsterShoppingManager(51);

    public static void main(String[] args) {
        String fileName = "products.csv";

        if(new File(fileName).exists()){  //If condition that checks if the file exists, if so read from it.
            manager.readFromFile();
        }
        manager.runMenu(input);
    }

}