import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Hallticket extends HttpServlet
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
		 String table="<table id='tab' style='text-align:left;width:80%'><tr>";

         Statement stmt = conn.createStatement();
         String sql="SELECT name,age,address,phone,gender FROM student Where id="+id;
         ResultSet rs = stmt.executeQuery(sql);
	
	     rs.next();
		 
		 String name= rs.getString("name");
		 String age= rs.getString("age");
		 String address= rs.getString("address");
		 String phone= rs.getString("phone");
		 String gender= rs.getString("gender");
		 	 
		 sql="SELECT college,course,payment,approval,city1 FROM hallticket Where id="+id;
         rs = stmt.executeQuery(sql);
		 rs.next();

		 String college= rs.getString("college");
		 String course= rs.getString("course");
	     String appro=rs.getString("approval");
		 String city=rs.getString("city1");
		if(appro.equals("yes"))
		{
			 sql="SELECT photo_name,sign_name,aadhar_name FROM document Where id="+id;
			 rs = stmt.executeQuery(sql);
			 
			 rs.next();
			 
			 String photo= rs.getString("photo_name");
			 String sign= rs.getString("sign_name");
			 String aadhar= rs.getString("aadhar_name");
			 
			 table+="<tr>";
			 table+="<th>Id</th>";
			 table+="<td>"+id+"</td>";
			 table+="<td rowspan='3'><img  width='100' height='100' src='files/"+photo+"'></td>";
			 table+="</tr>";
			 
			 table+="<tr>";
			 table+="<th>Name</th>";
			 table+="<td>"+name+"</td>";
			 table+="</tr>";
			 
			 table+="<tr>";
			 table+="<th>Age</th>";
			 table+="<td>"+age+"</td>";
			 table+="</tr>";
			 
			 table+="<tr>";
			 table+="<th>Address</th>";
			 table+="<td>"+address+"</td>";
			 table+="<td rowspan='3'><img  width='100' height='100' src='files/"+sign+"'></td>";
		     table+="</tr>";
			 
			 table+="<tr>";
			 table+="<th>Phone</th>";
			 table+="<td>"+phone+"</td>";
			 table+="</tr>";
			 
			 table+="<tr>";
			 table+="<th>Gender</th>";
			 table+="<td>"+gender+"</td>";
			 table+="</tr>";
			 
			 table+="<tr>";
			 table+="<th>College</th>";
			 table+="<td>"+college+"</td>";
		     table+="<td rowspan='3'><img  width='100' height='100' src='files/"+aadhar+"'></td>";
		     table+="</tr>";
			 
			 table+="<tr>";
			 table+="<th>Course</th>";
			 table+="<td>"+course+"</td>";
			 table+="</tr>";
			 
			 table+="<tr>";
			 table+="<th>City</th>";
			 table+="<td>"+city+"</td>";
			 table+="</tr>";
			 
			 table+="</table>";
			 
			 out.println("<!DOCTYPE html>"+
						 "<html>"+
							"<head>"+
								"<meta charset='utf-8'>"+
								"<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
								"<link rel='stylesheet' href='signupstyle.css'>"+
								"<title>Hallticket</title>"+
							"</head>"+
							"<body><center><br><h1>Hallticket</h1><br><br>"+table+
							"<input type='button' value='Print' onclick='printTable()'>"+
							"<input type='button' value='Back' onclick='view()'>"+
							"<script>"+
								"function printTable(){"+
									"var tab = document.getElementById('tab');"+
									"var win = window.open('', '', 'height=700,width=700');"+
									"win.document.write(tab.outerHTML);"+
									"win.document.close();"+
									"win.print();}"+
								"function view()"+
									"{window.location.replace('http://localhost:8080/ers/Menu');}"+					
							"</script>"+
							"</body>"+	
							"</html>");
		 }
		 else
		 {
			 out.println("<!DOCTYPE html>"+
						 "<html>"+
							"<head>"+
								"<meta charset='utf-8'>"+
								"<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
								"<link rel='stylesheet' href='menustyle.css'>"+
								"<title>Hallticket</title>"+
							"</head>"+
							"<body><center><br>"+
							"<h1>Hallticket not generated</h1><br>"+
							"<input type='button' value='Back' onclick='view()'>"+
							"</body>"+
							"<script>"+
								"function view()"+
									"{window.location.replace('http://localhost:8080/ers/Menu');}"+
							"</script>"+
							"</html>");
		}
			
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