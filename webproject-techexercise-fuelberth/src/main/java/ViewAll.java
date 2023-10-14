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

       
          String selectSQL = "SELECT * FROM libraryTable";
          preparedStatement = connection.prepareStatement(selectSQL);
       
       ResultSet rs = preparedStatement.executeQuery();

       while (rs.next()) {
          int id = rs.getInt("id");
          String name = rs.getString("title").trim();
          String author = rs.getString("author").trim();
          String publisher = rs.getString("publisher").trim();
          String available = String.valueOf(rs.getBoolean("available"));

          
             out.println("ID: " + id + ", ");
             out.println("Title: " + name + ", ");
             out.println("Author: " + author + ", ");
             out.println("Publisher: " + publisher + ", ");
             out.println("Available: " + available + "<br>");
          
       }
       out.println("<a href=/webproject-techexercise-fuelberth/simpleFormSearch.html>Search Data</a> <br>");
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
   
   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}