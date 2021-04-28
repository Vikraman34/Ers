import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class ConfirmDetails extends HttpServlet
{
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
      String DB_URL="jdbc:mysql://localhost/ers";
	  
      String USER = "root";
      String PASS = "9500";

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
	  
	  HttpSession session = request.getSession();
	  String id=(String)session.getAttribute("sid");
	  
	  String nm=request.getParameter("name");
	  String col=request.getParameter("college");
	  String cou=request.getParameter("course");
	  String tempa=request.getParameter("city1");
	  String tempb=request.getParameter("city2");
	  String tempc=request.getParameter("city3");      
      try {
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

		 PreparedStatement stmt = conn.prepareStatement("INSERT INTO hallticket (id,name,college,course,city1,city2,city3) VALUES(?,?,?,?,?,?,?)");
 		 stmt.setString(1,id);
		 stmt.setString(2,nm);
		 stmt.setString(3,col);
		 stmt.setString(4,cou);
		 stmt.setString(5,tempa);
		 stmt.setString(6,tempb);
		 stmt.setString(7,tempc);
		 
		 // Execute SQL query
        stmt.executeUpdate();
								
		out.println("<!DOCTYPE html>"+
   				     "<html>"+
						"<head>"+
							"<meta charset='utf-8'>"+
							"<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
							"<link rel='stylesheet' href='menustyle.css'>"+
							"<title>Confirm Details</title>"+
						"</head>"+
						"<body style='background-color:lightyellow'><br>"+
							"<center><h1>Added Successfully</h1></center>"+
							 "<script>"+
							 "setTimeout(view, 1500);"+
							 "function view()"+
								"{window.location.replace('docs.html');}"+
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