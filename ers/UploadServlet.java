// Import required java libraries
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

public class UploadServlet extends HttpServlet {
   
   private boolean isMultipart;
   private String filePath;
   private int maxFileSize = 50000 * 1024;
   private int maxMemSize = 40000 * 1024;
   private File file ;

   public void init( ){
      // Get the file location where it would be stored.
      filePath = getServletContext().getInitParameter("file-upload"); 
   }
   
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, java.io.IOException {
	  java.io.PrintWriter out = response.getWriter( );

      // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
  
      DiskFileItemFactory factory = new DiskFileItemFactory();
   
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
   
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("c:\\temp"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
   
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try { 
         // Parse the request to get file items.
         List fileItems = upload.parseRequest(request);
	
         // Process the uploaded file items
         Iterator i = fileItems.iterator();
   
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("sid");
		
		int flag=0;
         while ( i.hasNext () ) {
            FileItem fi = (FileItem)i.next();
            if ( !fi.isFormField () ) {
               // Get the uploaded file parameters
               String fieldName = fi.getFieldName();
               String fileName = fi.getName();
               String contentType = fi.getContentType();
               boolean isInMemory = fi.isInMemory();
               long sizeInBytes = fi.getSize();
            
               // Write the file
               if( fileName.lastIndexOf("\\") >= 0 ) {
                  file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
               } else {
                  file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
               }
               fi.write( file ) ;
			   
				out.println("</body>");
			    out.println("</html>");
				String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
				String DB_URL="jdbc:mysql://localhost/ers";
				String USER = "root";
				String PASS = "9500";
				Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
				if(flag==0)
				{
					PreparedStatement pstmt = con.prepareStatement("INSERT INTO document(id,photo,photo_name) VALUES(?,?,?)");
					InputStream in = new FileInputStream(file);
					pstmt.setString(1,id);
					pstmt.setBlob(2, in);
					pstmt.setString(3,"upload"+fileName);
					pstmt.execute();
					flag++;
				}
				else if(flag==1)
				{
					PreparedStatement pstmt = con.prepareStatement("UPDATE document SET sign=? , sign_name=? WHERE id='"+id+"'");
					InputStream in = new FileInputStream(file);
					pstmt.setBlob(1, in);
					pstmt.setString(2,"upload"+fileName);
					pstmt.execute();
					flag++;
				}
				else if(flag==2)
				{
					PreparedStatement pstmt = con.prepareStatement("UPDATE document SET aadhar=? , aadhar_name=? WHERE id='"+id+"'");
					InputStream in = new FileInputStream(file);
					pstmt.setBlob(1, in);
					pstmt.setString(2,"upload"+fileName);
					pstmt.execute();
					flag++;
				}
            }
         }
		 
		 out.println("<html>");
         out.println("<head>");
		 out.println("<link rel='stylesheet' href='menustyle.css'>");
         out.println("<title>Servlet upload</title>");  
         out.println("</head><body>");
		 out.println("<center><h1>Upload Successfull</h1><br>");
		 out.println("<script>"+
					 "setTimeout(view, 1500);"+
					 "function view()"+
					 "{window.location.replace('http://localhost:8080/ers/ViewData');}"+
					 "</script>");
				
         } catch(Exception ex) {
            System.out.println(ex);
         }
		
      }
   }
