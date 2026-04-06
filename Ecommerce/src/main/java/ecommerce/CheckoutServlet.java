package ecommerce;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check if user is logged in
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("SignIn.jsp");
            return;
        }

        String cart = (String) session.getAttribute("cart");

        // Check if cart is empty
        if (cart == null || cart.equals("")) {
            response.sendRedirect("cart.jsp");
            return;
        }

        // Process order (for now just simulate)
        String[] items = cart.split(",");

        // OPTIONAL: you could save to database here

        // Clear cart after checkout
        session.removeAttribute("cart");

        // Show success page
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Order placed successfully!</h2>");
        response.getWriter().println("<p>Total items: " + items.length + "</p>");
        response.getWriter().println("<a href='product.jsp'>Continue Shopping</a>");
        response.getWriter().println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("cart.jsp");
    }
}