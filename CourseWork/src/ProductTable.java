import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class ProductTable extends JTable {

    public ProductTable(ArrayList<Product> productsList){
        super(new ProductTableModel(productsList));
        setAutoCreateRowSorter(true);
        setupCellRenderer();
    }

    //Custom cell renderer used to colour the table row with the product that has a quantity of 3 or less : OpenAI. (2023). ChatGPT [Large language model]. https://chat.openai.com
    private void setupCellRenderer() {
        this.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ProductTableModel model = (ProductTableModel)table.getModel();
                Product product = model.getProductAt(row);

                if (product.getQuantity() <= 3) {
                    c.setBackground(Color.RED);
                } else {
                    c.setBackground(Color.WHITE); // default color
                }
                return c;
            }
        });
    }
}
