import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ShoppingCartTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Product", "Quantity", "Price"};
    private ArrayList<Product> cartList;

    public ShoppingCartTableModel(ArrayList<Product> cartList){

        this.cartList = cartList;
    }

    @Override
    public int getRowCount() {

        return cartList.size();
    }

    @Override
    public int getColumnCount() {

        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;

        if(columnIndex == 0){
            if(cartList.get(rowIndex) instanceof Electronics){
                temp = ((Electronics) cartList.get(rowIndex)).getProductId() + " " + ((Electronics) cartList.get(rowIndex)).getProductName() + " "
                        + ((Electronics) cartList.get(rowIndex)).getBrand() + " " + ((Electronics) cartList.get(rowIndex)).getWarrantyPeriod();
            }else if(cartList.get(rowIndex) instanceof Clothing){
                temp = ((Clothing) cartList.get(rowIndex)).getProductId() + " " + ((Clothing) cartList.get(rowIndex)).getProductName() + " "
                + ((Clothing) cartList.get(rowIndex)).getSize() + ", " + ((Clothing) cartList.get(rowIndex)).getColour();
            }
        }else if(columnIndex == 1){
            temp = cartList.get(rowIndex).getQuantity();
        }else if(columnIndex == 2){
            temp = cartList.get(rowIndex).getDisplayPrice() + "£";
        }

        return temp;
    }

    @Override
    public String getColumnName(int col){

        return columnNames[col];
    }
}
