import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MenuAdmin extends HttpServlet
{
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
	  HttpSession session = request.getSession();
	  String name=(String)session.getAttribute("name");
	  String pwd=(String)session.getAttribute("password");
	  
	  out.println("<!DOCTYPE html>"+
   				     "<html>"+
						"<head>"+
							"<meta charset='utf-8'>"+
							"<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
							"<link rel='stylesheet' href='menustyle.css'>"+
							"<title>Profile</title>"+
						"</head>"+
						"<body><center><br>"+
							"<h1>Welcome "+name+
							"<br><br><h1>Select from below</h1>"+
							"<div class='topnav'>"+
							"<nav style='font-size:20px'>"+
							"<br><a href='http://localhost:8080/ers/waiting.html'>View Waiting List</a>"+
							"<br><a href='http://localhost:8080/ers/approved.html'>View Approved List</a>"+
							"<br><a href='http://localhost:8080/ers/Signout'>Sign Out</a>"+
							"</nav></center>"+
							"</div>"+
						"</body>"+
					"</html>");
   }
}
