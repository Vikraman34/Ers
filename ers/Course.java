import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Course extends HttpServlet{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL= "jdbc:mysql://localhost:3306/ers";

    String USER ="root";
    String PASS="9500";

    try{
      response.setContentType("text/html");
      PrintWriter pw = response.getWriter();
      Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

      String course_name = request.getParameter("course");

      String search = "Select course from courses where course like '"+course_name+"%' ;";
	  pw.println("Select * from courses");
      Statement stmt = conn.createStatement();

      ResultSet rs = stmt.executeQuery(search);

      while(rs.next()){
        String course = rs.getString("course");
        pw.println("<option name='"+course+"'>"+course+"</option>");
      }
    }catch(Exception E){
      E.printStackTrace();
    }
  }
}
