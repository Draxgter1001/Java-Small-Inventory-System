import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class ShoppingCartWindow{

    JFrame frame = new JFrame();
    ArrayList<Product> cartList;
    ShoppingCartTable cartTable;

    public ShoppingCartWindow(ShoppingCart existingCart){

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Shopping Cart");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        cartList = existingCart.getCartProducts(); // Use the existing cart

        // Initializing the table with the cartList that contains products
        cartTable = new ShoppingCartTable(cartList);

        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        // Iterate over the column count of the table
        int columnCount = cartTable.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            cartTable.getColumnModel().getColumn(i).setCellRenderer(centerRender);
        }

        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setPreferredSize(new Dimension(700, 70));

        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Use Flowlayout for better control
        tablePanel.add(scrollPane); // Add scrollPane to the center

        //Initialing Top panel and adding its components
        JPanel topPanel = new JPanel(new GridLayout(3,1));

        JPanel whiteSpace = new JPanel();
        JPanel whiteSpace2 = new JPanel();

        topPanel.add(whiteSpace);
        topPanel.add(tablePanel);
        topPanel.add(whiteSpace2);

        //Creating labels for the Bottom Panel

        JLabel totalLabel = new JLabel("Total: ");
        totalLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel totalNumberLabel = new JLabel(String.valueOf(existingCart.getSubTotal()) + "£");

        JLabel discountTotalLabel = new JLabel("Three Items in same Category Discount (20%): ");
        discountTotalLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel discountNumberLabel = new JLabel(String.valueOf(existingCart.getDiscountPrice()) + "£");

        JLabel finalTotalLabel = new JLabel("Final Total: ");
        finalTotalLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel finalTotalNumber = new JLabel(String.valueOf(existingCart.getTotalCost()) + "£");

        //Initializing Bottom Panel and adding components
        JPanel botPanel = new JPanel(new GridLayout(3, 2));

        botPanel.add(totalLabel);
        botPanel.add(totalNumberLabel);
        botPanel.add(discountTotalLabel);
        botPanel.add(discountNumberLabel);
        botPanel.add(finalTotalLabel);
        botPanel.add(finalTotalNumber);

        //Initializing Main panel and adding the other panels
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        mainPanel.add(topPanel);
        mainPanel.add(botPanel);

        frame.add(mainPanel);
        frame.setVisible(true);

    }
}
