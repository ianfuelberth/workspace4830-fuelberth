
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormInsert")
public class SimpleFormInsert extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SimpleFormInsert() {
      super();
   }

   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String booktitle = request.getParameter("title");
      String author = request.getParameter("author");
      String pub = request.getParameter("publisher");
      String[] boxValues = request.getParameterValues("available");
      String boxValue = boxValues[0];
      Boolean available = Boolean.parseBoolean(boxValue);
      

      Connection connection = null;
      String insertSql = " INSERT INTO libraryTable (id, title, author, publisher, available) values (default, ?, ?, ?, ?)";

      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, booktitle);
         preparedStmt.setString(2, author);
         preparedStmt.setString(3, pub);
         preparedStmt.setBoolean(4, available);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert New Book";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

      out.println(docType + 
  	    	"<html>\n" + //
  	  		"<head>\n"
  	  		+ "<style>\n"
  	  		+ "header {\n"
  	  		+ "    background-color:rgb(43, 123, 76);\n"
  	  		+ "    color:white;\n"
  	  		+ "    text-align:center;\n"
  	  		+ "    padding:5px;	 \n"
  	  		+ "}\n"
  	  		+ "nav {\n"
  	  		+ "    line-height:30px;\n"
  	  		+ "    background-color:#eeeeee;\n"
  	  		+ "    height:300px;\n"
  	  		+ "    width:100px;\n"
  	  		+ "    float:left;\n"
  	  		+ "    padding:5px;	      \n"
  	  		+ "}\n"
  	  		+ "section {\n"
  	  		+ "    width:350px;\n"
  	  		+ "    float:left;\n"
  	  		+ "    padding:10px;	 	 \n"
  	  		+ "}\n"
  	  		+ "footer {\n"
  	  		+ "    background-color:rgb(43, 123, 76);\n"
  	  		+ "    color:white;\n"
  	  		+ "    clear:both;\n"
  	  		+ "    text-align:center;\n"
  	  		+ "    padding:5px;	 	 \n"
  	  		+ "}\n"
  	  		+ "</style>\n"
  	  		+ "</head>\n");
  	    
  	    out.println("<body><header><h1> Search Results </h1></header></body>");
  	    out.println("<body><nav>\n"
  	    		+ "<a href=\"/webproject-techexercise-fuelberth/simpleFormSearch.html\">Search Books</a> <br>\n"
  	    		+ "<a href=\"/webproject-techexercise-fuelberth/simpleFormInsert.html\">Add New Book</a> <br>\n"
  	    		+ "<a href=\"/webproject-techexercise-fuelberth/View All\">View Allk</a> <br>\n"
  	    		+ "</nav></body>");
  	    out.println("<body><section>");
      
  	    out.println(
            "<ul>\n" + //

            "  <li><b>Title</b>: " + booktitle + "\n" + //
            "  <li><b>Author</b>: " + author + "\n" + //
            "  <li><b>Publisher</b>: " + pub + "\n" + //
            "  <li><b>Available</b>: " + String.valueOf(available) + "\n" + //

            "</ul>\n");
  	    out.println("</body></section>");
      
  	  out.println("<body><footer>\n"
	       		+ "	Copyright\n"
	       		+ "</body></footer>");
      out.println("</html>");
   }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
