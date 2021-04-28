import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class ViewData extends HttpServlet
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
         String sql="SELECT photo_name,sign_name,aadhar_name FROM document Where id='"+id+"'";
         ResultSet rs = stmt.executeQuery(sql);
		
		out.println("<!DOCTYPE html>"+
   				     "<html>"+
						"<head>"+
							"<meta charset='utf-8'>"+
							"<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
							"<link rel='stylesheet' href='menustyle.css'>"+
							"<title>View Data</title>"+
						"</head>");
		
		rs.next();
		String n1=rs.getString("photo_name"); 
		String n2=rs.getString("sign_name"); 
		String n3=rs.getString("aadhar_name"); 
		
		 out.println("<center><h1> The Uploaded Documents </h1>"+
					"<div class='gallery'>"+
					"<a target='_blank' href='files/"+n1+"'>"+
					"<img src='files/"+n1+"'  width='400' height='400'></a>"+
					"<h3>Verify your Photograph</h3>"+
					"</div>"+
					"<div class='gallery'>"+
					"<a target='blank' href='files/"+n2+"'>"+
					"<img src='files/"+n2+"' width='400' height='400'></a>"+
					"<h3>Verify your Signature</h3>"+
					"</div>"+
					"<div class='gallery'>"+
					"<a target='blank' href='files/"+n3+"'>"+
					"<img src='files/"+n3+"' width='400' height='400'></a>"+
					"<h3>Verify your Aadhar</h3>"+
					"</div>"+
					"</center>"+
					"<form id='verify' action='http://localhost:8080/ers/payment.html'>"+
					"<input type='checkbox' id='check' name='check' value='' required>"+
					"<label for='check'> I hereby confirm that the above uploaded documents verify my identity</label><br>"+
					"<input type='submit' value='Continue to Pay Fee'>");
		 
		
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