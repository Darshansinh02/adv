import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ViewCartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Shopping Cart</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>Product</th><th>Quantity</th><th>Price</th><th>Total</th></tr>");
        for (CartItem item : cart.getItems()) {
            out.println("<tr>");
            out.println("<td>" + item.getProduct().getName() + "</td>");
            out.println("<td>" + item.getQuantity() + "</td>");
            out.println("<td>" + item.getProduct().getPrice() + "</td>");
            out.println("<td>" + item.getTotalPrice() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<p>Total: $" + cart.getTotalPrice() + "</p>");
        out.println("<a href='index.html'>Continue Shopping</a>");
        out.println("</body></html>");
    }
}