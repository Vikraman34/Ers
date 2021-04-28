import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class City extends HttpServlet{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL= "jdbc:mysql://localhost:3306/ers";

    String USER ="root";
    String PASS="9500";

    try{
      response.setContentType("text/html");
      PrintWriter pw = response.getWriter();
      Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

      String city_name = request.getParameter("city");

      String search = "Select city from cities where city like '"+city_name+"%' ;";
	  pw.println("Select * from cities");
      Statement stmt = conn.createStatement();

      ResultSet rs = stmt.executeQuery(search);

      while(rs.next()){
        String city = rs.getString("city");
        pw.println("<option name='"+city+"'>"+city+"</option>");
      }
    }catch(Exception E){
      E.printStackTrace();
    }
  }
}
