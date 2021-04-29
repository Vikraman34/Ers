import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Menu extends HttpServlet
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
							"<title>Menu</title>"+
						"</head>"+
						"<body><center><br>"+
							"<h1>Welcome "+name+
							"<br><br><h1>Select from below</h1>"+
							"<div class='topnav'>"+
							"<nav style='font-size:20px'>"+
							"<a href='http://localhost:8080/ers/ViewCourses'>View Exam Details</a>"+
							"<a href='http://localhost:8080/ers/RegCheck'>Register For Exam</a>"+
							"<a href='http://localhost:8080/ers/OptOut'>Optout of a course</a>"+
							"<a href='http://localhost:8080/ers/Status'>Check Status</a>"+
							"<a href='http://localhost:8080/ers/Hallticket'>View Hallticket</a>"+
							"<a href='http://localhost:8080/ers/Signout'>Sign Out</a>"+
							"</nav></center>"+
							"<div class='container'>"+"<figure><figcaption>Hover the image for Today's Quote!</figcaption></figure>"+
							"<img src='picday.png' alt='Avatar' class='image'>"+
							"<div class='overlay'>"+
							"<br/>"+
							"<br/>"+
							"<div class='text'><strong>Knowledge is power. Information is liberating. Education is the premise of progress, in every society</strong></div>"+
							"</div>"+
							"</div>"+
							"<img src='exampic.png' alt='Animation' class='ai'>"+
						"</body>"+
					"</html>");
   }
}
