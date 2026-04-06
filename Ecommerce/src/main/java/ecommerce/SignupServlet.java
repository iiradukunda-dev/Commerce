package ecommerce;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Fetching html contents
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		PrintWriter out=response.getWriter();
		Connection conn;
		
		//Connectivity and Db statement for signup operation
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //Driver loading
			 conn=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ecommerce","root","" //Connection setup
					);
			String sql="INSERT INTO users(email,password)VALUES(?,?)";
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);
			
			int rowsInserted=statement.executeUpdate();
			if(rowsInserted>0) {
				response.sendRedirect("SignIn.jsp?status=success");
			}else {
			response.sendRedirect("Signup.html?status=failed");
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("Signup.html?status=serverFailure");
		}
	}

}
