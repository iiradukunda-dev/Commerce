<%@ page import="javax.servlet.http.*" %>
<%
    if(session == null || session.getAttribute("email") == null) {
        response.sendRedirect("SignIn.jsp");
        return;
    }

    String cart = (String) session.getAttribute("cart");

    // SIMPLE REMOVE LOGIC
    String indexParam = request.getParameter("index");

    if(indexParam != null && cart != null) {
        String[] items = cart.split(",");

        int index = Integer.parseInt(indexParam);

        if(index >= 0 && index < items.length) {
            String newCart = "";

            for(int i = 0; i < items.length; i++) {
                if(i != index) {
                    if(newCart.equals("")) {
                        newCart = items[i];
                    } else {
                        newCart = newCart + "," + items[i];
                    }
                }
            }

            session.setAttribute("cart", newCart);
            cart = newCart;
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Cart</title>
</head>
<body>

<h2>Your Cart:</h2>

<a href="product.jsp">Back to Products</a><br><br>

<%
    if(cart == null || cart.equals("")) {
        out.println("Cart is empty");
    } else {
        String[] items = cart.split(",");

        for(int i = 0; i < items.length; i++) {
            out.println(items[i] + 
            " <a href='cart.jsp?index=" + i + "'>Remove</a><br>");
        }
    }
%>

<br>
<a href="Checkout.jsp">Checkout</a>

</body>
</html>