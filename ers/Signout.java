import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Signout extends HttpServlet
{
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      HttpSession session = request.getSession();
	  session.invalidate();
	  out.println("<!DOCTYPE html>"+
			      "<html>"+
					"<head>"+
						"<meta charset='utf-8'>"+
						"<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
						"<link rel='stylesheet' href='menustyle.css'>"+
						"<title>Logout</title>"+
					"</head>"+
					"<body><center><br>"+
					"<h1>Successfully Logged out</h1>"+
					"<script>"+
						 "setTimeout(view, 1500);"+
						 "function view()"+
						 "{window.location.replace('http://localhost:8080/ers/index.html');}"+
						 "</script>"+
					"</body>"+
					"</html>");
	}
}