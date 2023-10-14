import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewAll")
public class ViewAll extends HttpServlet {
   private static final long serialVersionUID = 1L;
   static String url = "jdbc:mysql://ec2-3-142-201-14.us-east-2.compute.amazonaws.com:3306/libraryDB";
   static String user = "ifuel_remote";
   static String password = "csci4830";
   static Connection connection = null;

   public ViewAll() {
      super();
   }

   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      display(response);
   }

void display(HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "All Books";
    String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
          "transitional//en\">\n"; //
//    out.println(docType + //
//          "<html>\n" + //
//          "<section><title>" + title + "</title></head>\n" + //
//          "<body bgcolor=\"#f0f0f0\">\n" + //
//          "<h1 align=\"center\">" + title + "</h1>\n");
    
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
    
    out.println("<body><header><h1> All Books </h1></header></body>");
    out.println("<body><nav>\n"
    		+ "<a href=\"/webproject-techexercise-fuelberth/simpleFormSearch.html\">Search Books</a> <br>\n"
    		+ "<a href=\"/webproject-techexercise-fuelberth/simpleFormInsert.html\">Add New Book</a> <br>\n"
    		+ "</nav></body>");
    out.println("<body><section>");
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
       DBConnection.getDBConnection();
       connection = DBConnection.connection;

       
          String selectSQL = "SELECT * FROM libraryTable";
          preparedStatement = connection.prepareStatement(selectSQL);
       
       ResultSet rs = preparedStatement.executeQuery();

       while (rs.next()) {
          int id = rs.getInt("id");
          String name = rs.getString("title").trim();
          String author = rs.getString("author").trim();
          String publisher = rs.getString("publisher").trim();
          String available = String.valueOf(rs.getBoolean("available"));

          
             out.println("ID: " + id + "\n");
             out.println("Title: " + name + "\n");
             out.println("Author: " + author + "\n");
             out.println("Publisher: " + publisher + "\n");
             out.println("Available: " + available + "<br>\n\n");
          
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