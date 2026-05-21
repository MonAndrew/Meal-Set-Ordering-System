import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
 
public class OrderGUI extends JFrame {
 
    DecimalFormat df = new DecimalFormat("#,###.00");
 
    CardLayout cardLayout = new CardLayout();
    JPanel     mainPanel  = new JPanel(cardLayout);
 
    Meals     meals     = new Meals();
    Beverages beverages = new Beverages();
    Sides     side      = new Sides();
    Extras    extras    = new Extras();
 
    
    static final String SCREEN_HOME     = "HOME";
    static final String SCREEN_CATEGORY = "CATEGORY";
    static final String SCREEN_CART     = "CART";
 
    
    JPanel itemGridPanel = new JPanel();
    JLabel categoryTitle = new JLabel("", SwingConstants.CENTER);
 
    DefaultListModel<String> cartModel   = new DefaultListModel<>();
    JList<String>            cartList    = new JList<>(cartModel);
    JLabel                   totalLabel  = new JLabel("Total: Php 0.00");
    StoreOrder               storeOrder  = new StoreOrder();
    List<StoreOrder>         savedOrders = new ArrayList<>();
 
    
    public OrderGUI() {
        setTitle("Jollibot Ordering System");
        setSize(1100, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        add(mainPanel);
 
        mainPanel.add(buildHomeScreen(),     SCREEN_HOME);
        mainPanel.add(buildCategoryScreen(), SCREEN_CATEGORY);
        mainPanel.add(buildCartScreen(),     SCREEN_CART);
 
        cardLayout.show(mainPanel, SCREEN_HOME);
 
        setVisible(true);
    }
 

    // HOME SCREEN, first To shouw up
    private JPanel buildHomeScreen() {
        JPanel panel = new JPanel(new BorderLayout());
 
        JLabel title = new JLabel("Welcome! Choose a Category", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(title, BorderLayout.NORTH);
 
        JPanel categoryPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        categoryPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
 
        // the main 4 panels that are added on the main frame
        categoryPanel.add(buildCategoryButton("MEALS",  "images/meals.jpg",  "MEALS"));
        categoryPanel.add(buildCategoryButton("DRINKS", "images/drinks.jpg", "DRINKS"));
        categoryPanel.add(buildCategoryButton("SIDES",  "images/sides.jpeg",  "SIDES"));
        categoryPanel.add(buildCategoryButton("EXTRAS", "images/extras.png", "EXTRAS"));
 
        panel.add(categoryPanel, BorderLayout.CENTER);
 
        JPanel bottomPanel = new JPanel();
        
        //three buttons on the homescreen
        JButton viewCartBtn   = new JButton("View Cart");
        JButton checkoutBtn   = new JButton("Checkout");
        JButton savedOrderBtn = new JButton("View Saved Orders");
        
        
        viewCartBtn  .addActionListener(e -> cardLayout.show(mainPanel, SCREEN_CART));
        checkoutBtn  .addActionListener(e -> checkout());
        savedOrderBtn.addActionListener(e -> viewSavedOrders());
 
        bottomPanel.add(viewCartBtn);
        bottomPanel.add(checkoutBtn);
        bottomPanel.add(savedOrderBtn);
 
        panel.add(bottomPanel, BorderLayout.SOUTH);
 
        return panel;
    }
 
    
    // CATEGORY BUTTON (used in home screen), once click mo agto ka sa category screen
    private JButton buildCategoryButton(String name, String imagePath, String category) {
        JButton btn = new JButton();
        btn.setLayout(new BorderLayout());
        btn.setPreferredSize(new Dimension(200, 180));
 
        ImageIcon icon = loadImage(imagePath, 120, 120);
        if (icon != null) {
            JLabel img = new JLabel(icon, SwingConstants.CENTER);
            btn.add(img, BorderLayout.CENTER);
        }
 
        JLabel lbl = new JLabel(name, SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.add(lbl, BorderLayout.SOUTH);
 
        btn.addActionListener(e -> {
            loadCategory(category);
            cardLayout.show(mainPanel, SCREEN_CATEGORY);
        });
 
        return btn;
    }
 

    // CATEGORY SCREEN
    private JPanel buildCategoryScreen() {
        JPanel panel = new JPanel(new BorderLayout());
 
        categoryTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        categoryTitle.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panel.add(categoryTitle, BorderLayout.NORTH);
 
        itemGridPanel.setLayout(new GridLayout(0, 3, 15, 15));
        JScrollPane scroll = new JScrollPane(itemGridPanel);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.add(scroll, BorderLayout.CENTER);
 
        JPanel bottom  = new JPanel();
        JButton backBtn = new JButton("Back to Menu");
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, SCREEN_HOME));
        bottom.add(backBtn);
        panel.add(bottom, BorderLayout.SOUTH);
 
        return panel;
    }
 

    // LOAD CATEGORY ITEMS INTO GRID, Mao ni ang 4 ka buttons
    private void loadCategory(String category) {
        itemGridPanel.removeAll();
        categoryTitle.setText(category);
 
        switch (category) {
            case "MEALS" -> {
                for (int i = 0; i < meals.getNumberLimit(); i++) {
                    String name  = meals.getFood(i).getName();
                    double price = meals.getFood(i).getPrice();
                    String img   = "images/" + name.toLowerCase().replace(" ", "_") + ".jpg";
                    itemGridPanel.add(buildItemCard(name, price, img));
                }
            }
            case "DRINKS" -> {
                for (int i = 0; i < beverages.getNumberLimit(); i++) {
                    String name  = beverages.getDrinks(i).getName();
                    double price = beverages.getDrinks(i).getPrice();
                    String img   = "images/" + name.toLowerCase().replace(" ", "_") + ".jpg";
                    itemGridPanel.add(buildItemCard(name, price, img));
                }
            }
            case "SIDES" -> {
                for (int i = 0; i < side.getNumberLimit(); i++) {
                    String name  = side.getSides(i).getName();
                    double price = side.getSides(i).getPrice();
                    String img   = "images/" + name.toLowerCase().replace(" ", "_") + ".jpg";
                    itemGridPanel.add(buildItemCard(name, price, img));
                }
            }
            case "EXTRAS" -> {
                for (int i = 0; i < extras.getNumberLimit(); i++) {
                    String name  = extras.getExtra(i).getName();
                    double price = extras.getExtra(i).getPrice();
                    String img   = "images/" + name.toLowerCase().replace(" ", "_") + ".jpg";
                    itemGridPanel.add(buildItemCard(name, price, img));
                }
            }
        }
 
        itemGridPanel.revalidate();
        itemGridPanel.repaint();
    }
 

    // ITEM CARD, once you clicked which category, either meals, beverages, sides, or extras, ang mga items niya kay mo show up
    private JPanel buildItemCard(String name, double price, String imagePath) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
 
        ImageIcon icon   = loadImage(imagePath, 100, 100);
        JLabel imgLabel  = new JLabel(icon == null ? "[no image]" : "", SwingConstants.CENTER);
        if (icon != null) imgLabel.setIcon(icon);
        card.add(imgLabel, BorderLayout.CENTER);
 
        JPanel info     = new JPanel(new GridLayout(2, 1));
        JLabel nameLbl  = new JLabel(name, SwingConstants.CENTER);
        JLabel priceLbl = new JLabel(price == 0 ? "Free" : "Php " + df.format(price), SwingConstants.CENTER);
        nameLbl .setFont(new Font("SansSerif", Font.BOLD,  11));
        priceLbl.setFont(new Font("SansSerif", Font.PLAIN, 11));
        info.add(nameLbl);
        info.add(priceLbl);
        card.add(info, BorderLayout.SOUTH);
 
        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(e -> {
            addItem(name, price);
            JOptionPane.showMessageDialog(this, name + " added to cart!");
        });
        card.add(addBtn, BorderLayout.NORTH);
 
        return card;
    }
 

    // CART SCREEN, to show all the items added to the cart
    private JPanel buildCartScreen() {
        JPanel panel = new JPanel(new BorderLayout());
 
        JLabel title = new JLabel("YOUR CART", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panel.add(title, BorderLayout.NORTH);
 
        panel.add(new JScrollPane(cartList), BorderLayout.CENTER);
 
        JPanel  bottom      = new JPanel();
        JButton backBtn     = new JButton("Back to Menu");
        JButton clearBtn    = new JButton("Clear Cart");
        JButton checkoutBtn = new JButton("Checkout");
 
        backBtn    .addActionListener(e -> cardLayout.show(mainPanel, SCREEN_HOME));
        clearBtn   .addActionListener(e -> clearCart());
        checkoutBtn.addActionListener(e -> checkout());
 
        bottom.add(totalLabel);
        bottom.add(backBtn);
        bottom.add(clearBtn);
        bottom.add(checkoutBtn);
        panel.add(bottom, BorderLayout.SOUTH);
 
        return panel;
    }
 

    // tweak the image
    private ImageIcon loadImage(String path, int width, int height) {
        try {
            ImageIcon raw    = new ImageIcon(path);
            Image     scaled = raw.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (Exception e) {
            return null;
        }
    }
 
 
    // functions add,update,clear,checkout,saveOrder and view
    private void addItem(String name, double price) {
        storeOrder.addItem(new StoreItem(name, price, 1));
        cartModel.addElement(name + " - Php " + df.format(price));
        updateTotal();
    }
 
    private void updateTotal() {
        totalLabel.setText("Total: Php " + df.format(storeOrder.getTotal()));
    }
 
    private void clearCart() {
        storeOrder.clear();
        cartModel.clear();
        updateTotal();
    }
 
    private void checkout() {
        if (storeOrder.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cart is empty!");
            return;
        }
 
        StringBuilder sb = new StringBuilder();
        sb.append("==== RECEIPT ====\n");
        for (StoreItem item : storeOrder.getItems()) {
            sb.append(item.getName())
              .append(" x").append(item.getQuantity())
              .append(" = Php ").append(df.format(item.getTotal()))
              .append("\n");
        }
        sb.append("\nTOTAL: Php ").append(df.format(storeOrder.getTotal()));
 
        JOptionPane.showMessageDialog(this, sb.toString(), "Receipt", JOptionPane.PLAIN_MESSAGE);
 
        saveOrder();
        clearCart();
        cardLayout.show(mainPanel, SCREEN_HOME);
    }
 
    private void saveOrder() {
        StoreOrder copy = new StoreOrder();
        for (StoreItem item : storeOrder.getItems()) {
            copy.addItem(new StoreItem(item.getName(), item.getPrice(), item.getQuantity()));
        }
        savedOrders.add(copy);
    }
 
    private void viewSavedOrders() {
        if (savedOrders.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No saved orders yet.");
            return;
        }
 
        StringBuilder sb    = new StringBuilder();
        int           count = 1;
        for (StoreOrder order : savedOrders) {
            sb.append("==== ORDER #").append(count).append(" ====\n");
            for (StoreItem item : order.getItems()) {
                sb.append(item.getName())
                  .append(" x").append(item.getQuantity())
                  .append(" = Php ").append(df.format(item.getTotal()))
                  .append("\n");
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
}
