import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShoppingApp extends JFrame {

    JComboBox productFilter;
    ArrayList<Product> productsList;
    private ShoppingCart shoppingCart;
    JButton shoppingCartButton;
    JButton addToShoppingCart;
    ProductTable productTable;
    JLabel productInfoLabel;
    JTextArea productInfoArea;

    public ShoppingApp(ArrayList<Product> productsList){

        this.productsList = productsList;
        shoppingCart = new ShoppingCart();
        ShoppingAppHandler listener = new ShoppingAppHandler();
        TableHandler tableListener = new TableHandler();

        //Initializing the JFrame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Westminster Shopping Centre");
        setSize(1100, 600);
        setLayout(new BorderLayout());

        //Creating Shopping Cart Button and its panel
        shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.setFocusable(false);
        shoppingCartButton.addActionListener(listener);

        JPanel shoppingCartButtonPanel = new JPanel();
        shoppingCartButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        shoppingCartButtonPanel.add(shoppingCartButton);

        //Creating Select Product Category label and its panel
        JLabel topLabel = new JLabel("Select Product Category");
        JPanel topLabelPanel = new JPanel();
        topLabelPanel.add(topLabel);

        //Creating combo box and putting the text in the middle
        String[] category = {"All", "Electronics", "Clothing"};
        productFilter = new JComboBox<>(category);
        productFilter.setPreferredSize(new Dimension(150, 30));
        productFilter.addActionListener(listener);

        JPanel productFilterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        productFilterPanel.add(productFilter);

        DefaultListCellRenderer comboBoxRenderer = new DefaultListCellRenderer();
        comboBoxRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        productFilter.setRenderer(comboBoxRenderer);

        //Initializing a panel that holds the label and the combo box
        JPanel topLabelProductFilterPanel = new JPanel(new GridLayout(1, 3));

        topLabelProductFilterPanel.add(topLabelPanel);
        topLabelProductFilterPanel.add(productFilterPanel);

        JPanel emptyPanel = new JPanel();
        topLabelProductFilterPanel.add(emptyPanel);

        //Initializing the table and its panel
        productTable = new ProductTable(productsList);

        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        for(int i = 0; i < productsList.size(); i++){
            productTable.getColumnModel().getColumn(i).setCellRenderer(centerRender);
        }
        productTable.getColumnModel().getColumn(4).setCellRenderer(centerRender);

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setPreferredSize(new Dimension(1000, 70));

        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tablePanel.add(scrollPane);

        productTable.getSelectionModel().addListSelectionListener(tableListener);

        //Initializing Top panel and adding its components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));
        topPanel.add(topLabelProductFilterPanel);
        topPanel.add(tablePanel);

        //Creating Add To Shopping Cart Button and its panel
        addToShoppingCart = new JButton("Add to Shopping Cart");
        addToShoppingCart.setFocusable(false);

        JPanel addToShoppingCartButtonPanel = new JPanel();
        addToShoppingCartButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addToShoppingCartButtonPanel.add(addToShoppingCart);
        addToShoppingCart.addActionListener(listener);

        //Creating Selected Product Details label and its panel
        productInfoLabel = new JLabel("Selected Product - Details");
        productInfoLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel productInfoLabelPanel = new JPanel(new GridLayout(1, 3));
        JPanel whiteSpace = new JPanel();
        JPanel whiteSpace2 = new JPanel();

        productInfoLabelPanel.add(productInfoLabel);
        productInfoLabelPanel.add(whiteSpace);
        productInfoLabelPanel.add(whiteSpace2);

        //Creating Product Info panel and adding its components
        JPanel productInfoPanel = new JPanel(new GridLayout(1, 3));
        productInfoArea = new JTextArea(5, 20);
        productInfoArea.setEditable(false);
        productInfoArea.setMargin(new Insets(10, 110, 10, 10));
        Font boldFont = new Font(productInfoArea.getFont().getFontName(), Font.BOLD, productInfoArea.getFont().getSize());

        productInfoArea.setFont(boldFont);
        productInfoPanel.add(productInfoArea);

        //Initializing Bottom panel and adding its components
        JPanel botPanel = new JPanel(new GridLayout(2, 1));
        botPanel.add(productInfoLabelPanel);
        botPanel.add(productInfoPanel);

        //Initializing Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(topPanel);
        mainPanel.add(botPanel);

        //Adding components to the JFrame
        add(shoppingCartButtonPanel, BorderLayout.NORTH);
        add(addToShoppingCartButtonPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }

    //Class that handles the events of the components
    private class ShoppingAppHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == productFilter){
                updateTable();
            }

            if(e.getSource() == shoppingCartButton){
                new ShoppingCartWindow(shoppingCart); //Open shopping cart frame
            }

            if(e.getSource() ==  addToShoppingCart){
                int selectedRow = productTable.getSelectedRow();
                if(selectedRow != -1){
                    Product selectedProduct = productsList.get(selectedRow);
                    if(selectedProduct.getQuantity() > 0){ //Add product to the shopping cart only if quantity > 0
                        shoppingCart.addProduct(selectedProduct);
                        selectedProduct.setQuantity(selectedProduct.getQuantity() - 1);
                    }
                }
            }
        }
    }

    //Class that handles the selection of the product in the table
    private class TableHandler implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(!e.getValueIsAdjusting()){
                int selectedRow = productTable.getSelectedRow();
                if(selectedRow != -1){
                    displayProductInfo(selectedRow);
                }
            }
        }
    }

    //Method used to update the table when filtering it throught the combo box
    private void updateTable(){
        String productCategory = (String) productFilter.getSelectedItem();
        ArrayList<Product> filteredProducts = new ArrayList<>();

        if("All".equals(productCategory)){
            filteredProducts = productsList;
        }else{
            for(Product product : productsList){
                if(productCategory.equals(product.getCategory())){
                    filteredProducts.add(product);
                }
            }
        }

        //Update the table with the filtered products
        ProductTableModel updateProductTableModel = new ProductTableModel(filteredProducts);
        productTable.setModel(updateProductTableModel);
        productTable.repaint();
    }

    private void displayProductInfo(int row){
        Product selectedProduct = productsList.get(row);

        String productInfo = ""; // Simple string concatenation

        if (selectedProduct instanceof Electronics) {
            Electronics electronics = (Electronics) selectedProduct;
            productInfo += "Product Id: " + electronics.getProductId() + "\n"
                    + "Name: " + electronics.getProductName() + "\n"
                    + "Category: " + electronics.getCategory() + "\n"
                    + "Brand: " + electronics.getBrand() + "\n"
                    + "Warranty Period: " + electronics.getWarrantyPeriod() + "\n"
                    + "Items available: " + electronics.getQuantity() + "\n";
        } else if (selectedProduct instanceof Clothing) {
            Clothing clothing = (Clothing) selectedProduct;
            productInfo += "Product Id: " + clothing.getProductId() + "\n"
                    + "Name: " + clothing.getProductName() + "\n"
                    + "Category: " + clothing.getCategory() + "\n"
                    + "Size: " + clothing.getSize() + "\n"
                    + "Colour: " + clothing.getColour() + "\n"
                    + "Items available: " + clothing.getQuantity() + "\n";
        }

        this.productInfoArea.setText(productInfo);
    }

}
