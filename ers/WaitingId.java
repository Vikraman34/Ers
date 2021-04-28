import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class WaitingId extends HttpServlet
{
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
      String DB_URL="jdbc:mysql://localhost/ers";
	  
      String USER = "root";
      String PASS = "9500";

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      try {
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

		 // Open a connection
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		 
         Statement stmt = conn.createStatement();
         String sql="SELECT id,payment,approval FROM hallticket";
         ResultSet rs = stmt.executeQuery(sql);
		
		String table="<center><table style='width:80%'><tr>";
		while(rs.next())
		 {
			String sid=rs.getString("id");
			String n1=rs.getString("payment");
			String n2=rs.getString("approval");	
			
			if(n1.equals("paid") && n2.equals("no"))
			{
				table+="<td onclick=display_list2("+sid+")>"+sid+"</td>";
			}
		 }
		
		table+="</tr></table>";
		out.println(table);
		
						
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