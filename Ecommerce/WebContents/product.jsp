<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
		HttpSession sess=request.getSession();
		if(sess==null||session.getAttribute("email")==null){
			response.sendRedirect("SignIn.jsp?status=loggedOut");
		}
		
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product</title>
</head>
<body>
	<h2>Welcome dear <%= session.getAttribute("email") %> !</h2>
	<form action="AddToCartServlet" method="post">
		<input type="hidden" name="product" value="100">
		Laptop 100$ <button type="submit">Add to Cart</button>
	</form>
	<form action="AddToCartServlet" method="post">
		<input type="hidden" name="product" value="200">
		Phone 90$ <button type="submit">Add to Cart</button>
	</form>
	<a href="cart.jsp">Go to servlet</a>
	<a href="LogoutServlet">Logout</a>
</body>
</html>