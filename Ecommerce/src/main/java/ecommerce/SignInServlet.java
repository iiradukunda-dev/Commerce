package ecommerce;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		PreparedStatement statement=null;
		ResultSet result=null;
		Connection conn=null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","");
			statement=conn.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
			statement.setString(1, email);
			statement.setString(2, password);
			
			result=statement.executeQuery();
			if(result.next()) {
				HttpSession session=request.getSession();
				session.setAttribute("email", email);
				response.sendRedirect("product.jsp");
				
				statement.close();
				conn.close();
				result.close();
			}else {
//				out.println("Failed to login");
				response.sendRedirect("SignIn.jsp?status=InvalidCredentials");
			}
			
		}catch(Exception e){
			e.getStackTrace();
		}
		
	}}