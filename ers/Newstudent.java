import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class Newstudent extends HttpServlet
{
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
      String DB_URL="jdbc:mysql://localhost/ers";
	  
      String USER = "root";
      String PASS = "9500";

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
	  
	  String pwd=request.getParameter("spassword");
	  String name=request.getParameter("name");
	  String age=request.getParameter("age");
	  String address=request.getParameter("address");
	  String num=request.getParameter("num");
	  String gender=request.getParameter("gender");
      
      try {
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

		 PreparedStatement stmt = conn.prepareStatement("INSERT INTO student (password,name,age,address,phone,gender) VALUES(?,?,?,?,?,?)");
		 stmt.setString(1,pwd);
		 stmt.setString(2,name);
		 stmt.setString(3,age);
		 stmt.setString(4,address);
		 stmt.setString(5,num);
		 stmt.setString(6,gender);
		 
		 // Execute SQL query
        stmt.executeUpdate();
							
		Statement stmt1 = conn.createStatement();
        String sql="SELECT id FROM student WHERE name='"+name+"' AND password='"+pwd+"'";
        ResultSet rs = stmt1.executeQuery(sql);
		rs.next();
		String aid= rs.getString("id");
		out.println("<!DOCTYPE html>"+
   				     "<html>"+
						"<head>"+
							"<meta charset='utf-8'>"+
							"<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
							"<link rel='stylesheet' href='menustyle.css'>"+
							"<title>View Added Record</title>"+
						"</head>"+
						"<body style='background-color:lightyellow'><br>"+
							"<center><h1>Added Successfully</h1><br><br>"+
							"<h1>Your Student Id is :"+aid+"</h1><br><br>"+
							"<input type='button' value='back' onclick='view()'></center>"+
							 "<script>"+
							 "function view()"+
								"{window.location.replace('index.html');}"+
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