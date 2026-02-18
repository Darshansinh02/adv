import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Assume products are predefined or from database
        Product product = getProductById(productId); // Implement this method

        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        cart.addItem(product, quantity);

        response.sendRedirect("viewCart");
    }

    private Product getProductById(int id) {
        // Dummy implementation
        if (id == 1) return new Product(1, "Laptop", 1000.0);
        if (id == 2) return new Product(2, "Phone", 500.0);
        return null;
    }
}