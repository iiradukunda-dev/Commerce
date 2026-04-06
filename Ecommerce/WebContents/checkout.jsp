<%
    // Check if user is logged in
    if (session.getAttribute("email") == null) {
        response.sendRedirect("SignIn.jsp");
        return;
    }

    String cart = (String) session.getAttribute("cart");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
</head>
<body>

<h2>Checkout</h2>

<a href="cart.jsp">Back to Cart</a><br><br>

<%
    if (cart == null || cart.equals("")) {
%>
        <p>Your cart is empty.</p>
<%
    } else {
        String[] items = cart.split(",");
%>

        <h3>Items:</h3>
        <ul>
        <%
            for (int i = 0; i < items.length; i++) {
        %>
            <li><%= items[i] %></li>
       <%}%>
        </ul>

        <h3>Total Items: <%= items.length %></h3>

        <form action="CheckoutServlet" method="post">
            <input type="submit" value="Confirm Order">
        </form>

<%}%>

</body>
</html>