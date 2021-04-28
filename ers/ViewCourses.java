import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.util.*;
import java.sql.*;  


public class ViewCourses extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
  {  
     PrintWriter out = response.getWriter();  
     response.setContentType("text/html");  
     
     out.println("<html><head><meta charset='utf-8'><meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
     out.println("<title>Course List and details</title>");
     out.println("<link rel='stylesheet' href='signupstyle.css'>");

     String JDBC_DRIVER = "com.mysql.jdbc.Driver";
     String DB_URL = "jdbc:mysql://localhost:3306/ers";


      String USER = "root";
      String PASS = "9500";


     Connection conn = null;
     Statement stmt = null;

     try 
     {  
        //Open Connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL,USER,PASS); 

        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Courses");

        out.println("<br><br><table border=1 width=90% height=90%>");  
        out.println("<tr><th>Course-ID</th><th>Course Name</th><th>Course Syllabus</th><th>Instructor</th><th>Duration in Weeks</th><th>Exam Date</th><th>Mode of Examination</th><tr>");

        while (rs.next()) 
        {  
            String cid = rs.getString("id");  
            String nm = rs.getString("course");
			String syl= rs.getString("course_syllabus");
			String fac = rs.getString("instructor");
            int numweeks = rs.getInt("duration_weeks"); 
            String exmode = rs.getString("exam_mode"); 
            out.println("<tr><td>" + cid + "</td><td>" + nm + "</td><td>" + syl + "</td><td>" + fac+ "</td><td>" + numweeks + "</td><td>" + rs.getDate("exam_date") + "</td><td>" + exmode + "</td></tr>");   
        }  
        out.println("</table><br><center><input type='button' value='Back' onclick='view()'>"); 
        out.println("<script>"+
							 "function view()"+
								"{window.location.replace('http://localhost:8080/ers/Menu');}"+
						     "</script>"+
							 "</body></html>");  
        conn.close();      
     }

     catch(Exception E)
     {
      E.printStackTrace();
     }

     out.close();
  }
}