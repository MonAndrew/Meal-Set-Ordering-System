package graphical_user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author Junwel
 */


public class OrderGUI extends JFrame   {
    DecimalFormat df = new DecimalFormat("#,###.00");
    
    
    //instantiating these classes so that we can use their functions
    Meals meals = new Meals();
    Beverages beverages = new Beverages();
    Sides side = new Sides();
    Extras extras = new Extras();
    
    
    //use for storing and accessing the Meal class, Beverage Class, side class, extras class
    List<JCheckBox> mealBox = new ArrayList<>();
    List<JCheckBox> beveragesBox = new ArrayList<>();
    List<JCheckBox> sideBox = new ArrayList<>();
    List<JCheckBox> extrasBox = new ArrayList<>();
    
    
    //to see string in the cart side or right side of the panel
    DefaultListModel<String> cartModel = new DefaultListModel<>();
    JList<String> cartList = new JList<>(cartModel);
    
    
    JTextArea receiptArea = new JTextArea();
    JLabel totalLabel = new JLabel("Total: Php 0.00");
    
    //instantiation to store the orders being ordered and show it to the cart
    StoreOrder storeOrder = new StoreOrder();
    
    
    //save the orders that are done being checkout
    List<StoreOrder> savedOrders = new ArrayList<>();
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///constractor
    public OrderGUI(){
        setTitle("Meal Ordering System");
        setSize(1100,650);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //to separate the part of the main frame into other panels to be edited soon or to edit it easier
        setLayout(new BorderLayout());
        
        //1 frame (which is the main frame), inside of this are 4 main panels
        add(buildMenuPanel(), BorderLayout.WEST);
        add(new JScrollPane(receiptArea), BorderLayout.CENTER);
        add(buildCartPanel(), BorderLayout.EAST);
        add(buildBottomPanel(), BorderLayout.SOUTH);
        
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        receiptArea.setEditable(false);
        
        setVisible(true);
    }
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///function
    
    
    private JPanel buildMenuPanel(){
        JPanel panel = new JPanel(new GridLayout(0,1));
        
        panel.add(new JLabel("--- MEALS ---"));
        for(int i = 0; i < meals.getNumberLimit(); i++){
            JCheckBox cb = new JCheckBox(
                    meals.getFood(i).getName() + " - Php " + meals.getFood(i).getPrice()
            );
            
            //putting all the list box to the mealBox in order
            mealBox.add(cb);
            
            //putting all those list boxes to the main panel
            panel.add(cb);
        }
        
        panel.add(new JLabel("-- DRINKS -- "));
        for(int i = 0; i < beverages.getNumberLimit(); i++){
            JCheckBox cb = new JCheckBox(
                    beverages.getDrinks(i).getName() + " - Php " + beverages.getDrinks(i).getPrice()
            );
            
            //putting all the list box to the beveragesBox in order
            beveragesBox.add(cb);
            
            //putting all those list boxes to the main panel
            panel.add(cb);
        }
        
        panel.add(new JLabel("-- SIDES --"));
        for(int i = 0; i < side.getNumberLimit();i++){
            JCheckBox cb = new JCheckBox(
                    side.getSides(i).getName() + " - Php " + side.getSides(i).getPrice()
            );
            
            //putting all the list box to the sideBox in order
            sideBox.add(cb);
            
            //putting all those list boxes to the main panel
            panel.add(cb);
        }
        
        panel.add(new JLabel("-- EXTRAS -- "));
        for(int i = 0; i < extras.getNumberLimit();i++){
            JCheckBox cb = new JCheckBox(
                    extras.getExtra(i).getName() + " - Pph " + extras.getExtra(i).getPrice()
            );
            
            //putting all the list box to the extrasBox in order
            extrasBox.add(cb);
            
            //putting all those list boxes to the main panel
            panel.add(cb);
        }
        
        JButton addBtn = new JButton("Add to CART");
        addBtn.addActionListener(e->addToCart());
        
        panel.add(addBtn);
        
        
        return panel;
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///function
    
    private JPanel buildCartPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(350,0));
        
        JLabel title = new JLabel("CART", SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);
        
        panel.add(new JScrollPane(cartList), BorderLayout.CENTER);
        
        JButton clearBtn = new JButton("Clear Cart");
        clearBtn.addActionListener(e -> clearCart());
        
        panel.add(clearBtn, BorderLayout.SOUTH);
        
        return panel;
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///function
    
    //buttons here to click
    private JPanel buildBottomPanel(){
       JPanel panel = new JPanel();
       
       //creating buttons
       JButton checkOutBtn = new JButton("Checkout");
       JButton viewOrderBtn = new JButton("View Saved Orders");
       
       checkOutBtn.addActionListener(e -> checkout());
       viewOrderBtn.addActionListener(e -> viewSavedOrders());
       
       panel.add(totalLabel);
       panel.add(checkOutBtn);
       panel.add(viewOrderBtn);

       return panel;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///function
    
    private void checkout(){
        if(storeOrder.getItems().isEmpty()){
            JOptionPane.showMessageDialog(this, "Cart is Empty!");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("==== RECEIPT ====\n");
        
        
        //think of these as SYSTEM.OUT.PRINT, but it uses a library StringBuilder
        for(StoreItem item : storeOrder.getItems()){
            sb.append(item.getName()).append(" x").append(item.getQuantity()).append(" = Php").append(df.format(item.getTotal())).append("\n");
        }
        
        sb.append("\nTOTAL: Php ").append(df.format(storeOrder.getTotal()));
        
        JOptionPane.showMessageDialog(this, sb.toString());
        
        saveOrder();
        clearCart();
        receiptArea.setText("");
        
        
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///function
    
    private void saveOrder() {
        StoreOrder copy = new StoreOrder();
        
        for(StoreItem item : storeOrder.getItems()){
            copy.addItem(new StoreItem(item.getName(), item.getPrice(), item.getQuantity()));
        }
        
        savedOrders.add(copy);
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///function
    //everytime mo click sa add to cart mo balik siya to unSelect ang mga items nga imohang ge select, para ready napud ka mo select balik
    private void clearCart(){
        storeOrder.clear();
        cartModel.clear();
        receiptArea.setText("");
        
        for(JCheckBox cb: mealBox){
            cb.setSelected(false);
        }
        for(JCheckBox cb: beveragesBox){
            cb.setSelected(false);
        }
        for(JCheckBox cb: sideBox){
            cb.setSelected(false);
        }
        for(JCheckBox cb: extrasBox){
            cb.setSelected(false);
        }
        
        updateTotal();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///function
    
    private void viewSavedOrders(){
        if(savedOrders.isEmpty()){
            JOptionPane.showMessageDialog(this, "No saved orders yet.");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        int count = 1;
        
        for(StoreOrder order: savedOrders){
            sb.append("==== ORDER #").append(count).append(" ====\n");
            
            for(StoreItem item : order.getItems()){
                sb.append(item.getName()).append(" x").append(item.getQuantity()).append(" = Php").append(df.format(item.getTotal())).append("\n");
            }
            
            sb.append("TOTAL: Php ").append(df.format(order.getTotal())).append("\n\n");
            count++;
        }
        
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scroll, "Saved Orders", JOptionPane.PLAIN_MESSAGE);
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///function
    
    //print and show it to the right side or the cart side
    private void addToCart() {
        boolean ifCheck = false;
        
        for(int i =0; i < meals.getNumberLimit();i++){
            if(mealBox.get(i).isSelected()){
                addItem(meals.getFood(i).getName(), meals.getFood(i).getPrice());
                ifCheck = true;
            }
        }
        
        for(int i = 0; i < beverages.getNumberLimit();i++){
            if(beveragesBox.get(i).isSelected()){
                addItem(beverages.getDrinks(i).getName(), beverages.getDrinks(i).getPrice());
                ifCheck = true;
            }
        }
        
        for(int i =0; i < side.getNumberLimit();i++){
            if(sideBox.get(i).isSelected()){
                addItem(side.getSides(i).getName(), side.getSides(i).getPrice());
                ifCheck = true;
            }
        }
        
        
        for(int i = 0; i < extras.getNumberLimit();i++){
            if(extrasBox.get(i).isSelected()){
                addItem(extras.getExtra(i).getName(), extras.getExtra(i).getPrice());
                ifCheck = true;
            }
        }
        
        
        if(!ifCheck){
            JOptionPane.showMessageDialog(this, "None was selected");
            return;
        }
        
        for(JCheckBox cb: mealBox){
            cb.setSelected(false);
        }
        for(JCheckBox cb: beveragesBox){
            cb.setSelected(false);
        }
        for(JCheckBox cb: sideBox){
            cb.setSelected(false);
        }
        for(JCheckBox cb: extrasBox){
            cb.setSelected(false);
        }
        
        updateTotal();
    }
    
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///function
    
    //store ang mga data sa storeOrder nga class and sa cartModel nga list
    private void addItem(String name, double price){
        storeOrder.addItem(new StoreItem(name, price, 1));
        cartModel.addElement(name + " - Php " + df.format(price));
        receiptArea.append(name + " :Php " + df.format(price) + "\n");
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///function
    
    //tig update sa total
    private void updateTotal(){
        totalLabel.setText("Total: Php " + df.format(storeOrder.getTotal()));
    }    

    
    
}
