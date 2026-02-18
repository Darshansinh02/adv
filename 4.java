import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

// Product class
class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}

// CartItem class
class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getTotalPrice() { return product.getPrice() * quantity; }
}

// ShoppingCart class
class ShoppingCart {
    private List<CartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public void removeItem(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    public List<CartItem> getItems() { return items; }
    public double getTotalPrice() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}

// Session simulation using a map

class SessionManager {
    private static Map<String, ShoppingCart> sessions = new HashMap<>();

    public static ShoppingCart getCart(String sessionId) {
        return sessions.computeIfAbsent(sessionId, k -> new ShoppingCart());
    }

    public static void removeSession(String sessionId) {
        sessions.remove(sessionId);
    }
}

// Main class for demonstration
class OnlineShoppingCart {
    public static void main(String[] args) {
        // Simulate session
        String sessionId = "user123";

        ShoppingCart cart = SessionManager.getCart(sessionId);

        // Add products
        Product laptop = new Product(1, "Laptop", 1000.0);
        Product phone = new Product(2, "Phone", 500.0);

        cart.addItem(laptop, 1);
        cart.addItem(phone, 2);

        // Display cart
        System.out.println("Shopping Cart:");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getProduct().getName() + " - Quantity: " + item.getQuantity() + " - Total: $" + item.getTotalPrice());
        }
        System.out.println("Total Price: $" + cart.getTotalPrice());
    }
}