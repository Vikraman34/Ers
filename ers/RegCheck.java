import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class RegCheck extends HttpServlet
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

         // Execute SQL query
         Statement stmt = conn.createStatement();
         String sql="SELECT * FROM hallticket";
         ResultSet rs = stmt.executeQuery(sql);
		
		out.println("<!DOCTYPE html>"+
   				     "<html>"+
						"<head>"+
							"<meta charset='utf-8'>"+
							"<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
							"<link rel='stylesheet' href='menustyle.css'>"+
						"</head>");
		int flag=0;
		while(rs.next())
		{
			String n1=rs.getString("id"); 
			if(n1.equals(id))
				flag=1;
		}
		
		if(flag==0)
		{
			out.println("<body onload=view()>"+
						"<script>"+
							"function view()"+
								"{window.location.replace('register.html');}"+
						"</script></body></html>");
		}
		else
		{
			out.println("<body>"+
						"<center><br><h1>You have already registered for a course</h1><br><br>"+
						"<input type='button' value='back' onclick='view()'></center>"+
						"<script>"+
							"function view()"+
								"{window.location.replace('http://localhost:8080/ers/Menu')}"+
						"</script></body></html>");
		}
		 
		
         // Clean-up environment
         rs.close();
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