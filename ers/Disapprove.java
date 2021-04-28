import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Disapprove extends HttpServlet
{
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
      String DB_URL="jdbc:mysql://localhost/ers";
	  
      String USER = "root";
      String PASS = "9500";

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
	  String id=request.getParameter("id");
      try {
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

		 // Open a connection
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		 PreparedStatement stmt = conn.prepareStatement("UPDATE hallticket SET approval=? WHERE id='"+id+"'");
		 stmt.setString(1,"no");
		 stmt.execute();
		 
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