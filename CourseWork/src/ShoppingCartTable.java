import javax.swing.*;
import java.util.ArrayList;

public class ShoppingCartTable extends JTable{
    public ShoppingCartTable(ArrayList<Product> cartList){
        super(new ShoppingCartTableModel(cartList));
        setAutoCreateRowSorter(true);
    }
}
