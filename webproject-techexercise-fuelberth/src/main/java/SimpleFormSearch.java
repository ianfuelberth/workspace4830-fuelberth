import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormSearch")
public class SimpleFormSearch extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SimpleFormSearch() {
      super();
   }

   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword");
      display(keyword, response);
   }

   void search(String keyword, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;

         if (keyword.isEmpty()) {
            String selectSQL = "SELECT * FROM libraryTable";
            preparedStatement = connection.prepareStatement(selectSQL);
         } else {
            String selectSQL = "SELECT * FROM libraryTable WHERE title LIKE ?";
            String booktitle = keyword + "%";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, booktitle);
         }
         ResultSet rs = preparedStatement.executeQuery();

         while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("title").trim();
            String author = rs.getString("author").trim();
            String publisher = rs.getString("publisher").trim();
            String available = String.valueOf(rs.getBoolean("available"));

            if (keyword.isEmpty() || name.contains(keyword)) {
               out.println("ID: " + id + ", ");
               out.println("Title: " + name + ", ");
               out.println("Author: " + author + ", ");
               out.println("Publisher: " + publisher + ", ");
               out.println("Available: " + available + "<br>");
            }
         }
         out.println("<a href=/webproject-techexercise-fuelberth/simpleFormSearch.html>Search Books</a> <br>");
         out.println("</body></html>");
         rs.close();
         preparedStatement.close();
         connection.close();
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (preparedStatement != null)
               preparedStatement.close();
         } catch (SQLException se2) {
         }
         try {
            if (connection != null)
               connection.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
   }
   void display(String keyword, HttpServletResponse response) throws IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String title = "All Books";
	    String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
	          "transitional//en\">\n"; //
//	    out.println(docType + //
//	          "<html>\n" + //
//	          "<section><title>" + title + "</title></head>\n" + //
//	          "<body bgcolor=\"#f0f0f0\">\n" + //
//	          "<h1 align=\"center\">" + title + "</h1>\n");
	    
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
	    		+ "<a href=\"/webproject-techexercise-fuelberth/ViewAll\">View All</a> <br>\n"
	    		+ "<a href=\"/webproject-techexercise-fuelberth/simpleFormInsert.html\">Add New Book</a> <br>\n"
	    		+ "</nav></body>");
	    out.println("<body><section>");
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    try {
	       DBConnection.getDBConnection();
	       connection = DBConnection.connection;

	       
	       if (keyword.isEmpty()) {
	            String selectSQL = "SELECT * FROM libraryTable";
	            preparedStatement = connection.prepareStatement(selectSQL);
	         } else {
	            String selectSQL = "SELECT * FROM libraryTable WHERE title LIKE ?";
	            String booktitle = keyword + "%";
	            preparedStatement = connection.prepareStatement(selectSQL);
	            preparedStatement.setString(1, booktitle);
	         }
	         ResultSet rs = preparedStatement.executeQuery();

	         while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("title").trim();
	            String author = rs.getString("author").trim();
	            String publisher = rs.getString("publisher").trim();
	            String available = String.valueOf(rs.getBoolean("available"));

	            if (keyword.isEmpty() || name.contains(keyword)) {
	               out.println("ID: " + id + ", ");
	               out.println("Title: " + name + ", ");
	               out.println("Author: " + author + ", ");
	               out.println("Publisher: " + publisher + ", ");
	               out.println("Available: " + available + "<br>");
	            }
	         }
	          
	       
	       out.println("</section></body>");
	       
	       out.println("<body><footer>\n"
	       		+ "	Copyright\n"
	       		+ "</body></footer>");
	       out.println("</html>");
	       rs.close();
	       preparedStatement.close();
	       connection.close();
	    } catch (SQLException se) {
	       se.printStackTrace();
	    } catch (Exception e) {
	       e.printStackTrace();
	    } finally {
	       try {
	          if (preparedStatement != null)
	             preparedStatement.close();
	       } catch (SQLException se2) {
	       }
	       try {
	          if (connection != null)
	             connection.close();
	       } catch (SQLException se) {
	          se.printStackTrace();
	       }
	    }
	}

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
