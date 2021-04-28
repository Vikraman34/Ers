import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class Payment extends HttpServlet
{
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
      String DB_URL="jdbc:mysql://localhost/ers";
	  
      String USER = "root";
      String PASS = "9500";

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
	  
	  HttpSession session = request.getSession();
	  String id=(String)session.getAttribute("sid");
      
      try {
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

		PreparedStatement stmt = conn.prepareStatement("UPDATE hallticket SET payment=? WHERE id='"+id+"'");
		stmt.setString(1,"paid");
		stmt.execute();
		 
		out.println("<!DOCTYPE html>"+
   				     "<html>"+
						"<head>"+
							"<meta charset='utf-8'>"+
							"<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
							"<link rel='stylesheet' href='menustyle.css'>"+
							"<title>Payment</title>"+
						"</head>"+
						"<body><br>"+
							"<center><h1>Payment Successful</h1><br><br>"+
							"<h1>Redirecting to home page</h1>"+
							 "<script>"+
								"setTimeout(view, 1500);"+
								"function view()"+
									"{window.location.replace('http://localhost:8080/ers/Menu');}"+
						     "</script>"+
						"</body>"+
					 "</html>");
						
         // Clean-up environment
         stmt.close();
         conn.close();
      } catch(SQLException se) {
         //Handle errors for JDBC
         se.printStackTrace();
      } catch(Exception e) {
         //Handle errors for Class.forName
         e.printStackTrace();
      } 
	}
}