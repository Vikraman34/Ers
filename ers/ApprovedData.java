import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ApprovedData extends HttpServlet
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
		 String table="<center><table style='width:80%'><tr>";

         Statement stmt = conn.createStatement();
         String sql="SELECT name,age,address,phone,gender FROM student Where id="+id;
         ResultSet rs = stmt.executeQuery(sql);
	
	     rs.next();
		 
		 String name= rs.getString("name");
		 String age= rs.getString("age");
		 String address= rs.getString("address");
		 String phone= rs.getString("phone");
		 String gender= rs.getString("gender");
		 
		 
		 sql="SELECT college,course,payment FROM hallticket Where id="+id;
         rs = stmt.executeQuery(sql);
		 
		 rs.next();
		 String college= rs.getString("college");
		 String course= rs.getString("course");
		 String payment= rs.getString("payment");
		 
		 sql="SELECT photo_name,sign_name,aadhar_name FROM document Where id="+id;
         rs = stmt.executeQuery(sql);
		 
		 rs.next();
		 
		 String photo= rs.getString("photo_name");
		 String sign= rs.getString("sign_name");
		 String aadhar= rs.getString("aadhar_name");
		 
		 table+="<tr>";
		 table+="<th>Id</th>";
		 table+="<td colspan='3'>"+id+"</td>";
		 table+="</tr>";
		 
		 table+="<tr>";
		 table+="<th>Name</th>";
		 table+="<td colspan='3'>"+name+"</td>";
		 table+="</tr>";
		 
		 table+="<tr>";
		 table+="<th>Age</th>";
		 table+="<td colspan='3'>"+age+"</td>";
		 table+="</tr>";
		 
		 table+="<tr>";
		 table+="<th>Address</th>";
		 table+="<td colspan='3'>"+address+"</td>";
		 table+="</tr>";
		 
		 table+="<tr>";
		 table+="<th>Phone</th>";
		 table+="<td colspan='3'>"+phone+"</td>";
		 table+="</tr>";
		 
		 table+="<tr>";
		 table+="<th>Gender</th>";
		 table+="<td colspan='3'>"+gender+"</td>";
		 table+="</tr>";
		 
		 table+="<tr>";
		 table+="<th>College</th>";
		 table+="<td colspan='3'>"+college+"</td>";
		 table+="</tr>";
		 
		 table+="<tr>";
		 table+="<th>Course</th>";
		 table+="<td colspan='3'>"+course+"</td>";
		 table+="</tr>";
		 
		 table+="<tr>";
		 table+="<th>Payment</th>";
		 table+="<td colspan='3'>"+payment+"</td>";
		 table+="</tr>";
		 
		 table+="<tr>";
		 table+="<th>Documents</th>";
		 table+="<td><img  width='100' height='100' src='files/"+photo+"'></td>";
		 table+="<td><img  width='100' height='100' src='files/"+sign+"'></td>";
		 table+="<td><img  width='100' height='100' src='files/"+aadhar+"'></td>";
		 table+="</tr>";
		 
		 table+="</table>";
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