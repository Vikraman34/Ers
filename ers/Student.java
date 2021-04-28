import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Student extends HttpServlet
{
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
      String DB_URL="jdbc:mysql://localhost/ers";
	  
      String USER = "root";
      String PASS = "9500";

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
	  String sid=request.getParameter("sname");
	  String pwd=request.getParameter("spassword");
	   
      try {
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

         // Execute SQL query
         Statement stmt = conn.createStatement();
         String sql="SELECT id,password,name FROM student";
         ResultSet rs = stmt.executeQuery(sql);
		
		 out.println("<!DOCTYPE html>"+
   				     "<html>"+
						"<head>"+
							"<meta charset='utf-8'>"+
							"<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
							"<link rel='stylesheet' href='menustyle.css'>"+
							"<title>Student Login</title>"+
						"</head>"+
						"<body><center><br>");
								
         // Extract data from result set
		 int flag=0;
         while(rs.next())
		 {
            //Retrieve by column name
			String id= rs.getString("id");
			String password= rs.getString("password");
			String name=rs.getString("name");
			
			if(sid.equals(id) && pwd.equals(password))
			{
				out.println("<script>"+
								 "setTimeout(view,1000);"+
								 "function view()"+
								 "{window.location.replace('http://localhost:8080/ers/Menu');}"+
						    "</script>"+
							"</center></body>"+
							"</html>");
							
				HttpSession session = request.getSession();
				session.setAttribute("sid",sid);
				session.setAttribute("password",pwd);
				session.setAttribute("name",name);
				flag=1;
				break;
			}
         }
		 
		 if(flag==0)
		 {
			out.println("<h1>Invalid Login<br><br>Try Again"+
						"</center>"+
						"<script>"+
							 "setTimeout(view, 1500);"+
							 "function view()"+
							 "{window.location.replace('http://localhost:8080/ers');}"+
						 "</script>"+
						"</body>"+
						"</html>");
		 }
		
         // Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
		 out.close();
      } catch(SQLException se) {
         //Handle errors for JDBC
         se.printStackTrace();
      } catch(Exception e) {
         //Handle errors for Class.forName
         e.printStackTrace();
      } 
	}
}