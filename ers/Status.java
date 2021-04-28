import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class Status extends HttpServlet
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
		 
         Statement stmt = conn.createStatement();
         String sql="SELECT payment,approval FROM hallticket Where id='"+id+"'";
         ResultSet rs = stmt.executeQuery(sql);
		 rs.next();
		 
		String n1=rs.getString("payment");
		String n2=rs.getString("approval");
		
		 out.println("<!DOCTYPE html>"+
   				     "<html>"+
						"<head>"+
							"<meta charset='utf-8'>"+
							"<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
							"<link rel='stylesheet' href='menustyle.css'>"+
							"<title>Check Status</title>"+
						"</head>"+
						"<body><br><br>"+
						"<center><table>"+
							"<tr>"+
								"<th>Student Id</th>"+
								"<th>Payment Status</th>"+
								"<th>Approval Status</th>"+
							"</tr>"+
							"<tr>"+
								"<td>"+id+"</th>"+
								"<td>"+n1+"</th>"+
								"<td>"+n2+"</th>"+
							"</tr>"+
						"</table>"+
						"<br><input type='button' value='Back' onclick='view()'>"+
						"<script>"+
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