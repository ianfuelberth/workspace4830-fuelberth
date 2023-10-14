import java.io.IOException;
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

@WebServlet("/MyServletTEFuelberth")
public class MyServletTEFuelberth extends HttpServlet {
   private static final long serialVersionUID = 1L;
   static String url = "jdbc:mysql://ec2-3-142-201-14.us-east-2.compute.amazonaws.com:3306/libraryDB";
   static String user = "ifuel_remote";
   static String password = "csci4830";
   static Connection connection = null;

   public MyServletTEFuelberth() {
      super();
   }

   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      response.getWriter().println("-------- MySQL JDBC Connection Testing ------------<br>");
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");//("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) {
         System.out.println("Where is your MySQL JDBC Driver?");
         e.printStackTrace();
         return;
      }
      response.getWriter().println("MySQL JDBC Driver Registered!<br>");
      connection = null;
      try {
         connection = DriverManager.getConnection(url, user, password);
      } catch (SQLException e) {
         System.out.println("Connection Failed! Check output console");
         e.printStackTrace();
         return;
      }
      if (connection != null) {
         response.getWriter().println("You made it, take control your database now!<br>");
      } else {
         System.out.println("Failed to make connection!");
      }
      try {
         String selectSQL = "SELECT * FROM libraryTable";

         response.getWriter().println(selectSQL + "<br>");
         response.getWriter().println("------------------------------------------<br>");
         PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

         ResultSet rs = preparedStatement.executeQuery();
         while (rs.next()) {
            String id = rs.getString("id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String publisher = rs.getString("publisher");
            Boolean available = rs.getBoolean("available");
            response.getWriter().append("BOOK ID: " + id + "<br>");
            response.getWriter().append("TITLE: " + title + "<br>");
            response.getWriter().append("AUTHOR: " + author + "<br>");
            response.getWriter().append("PUBLISHER: " + publisher + "<br>");
            response.getWriter().append("AVAILABLE: " + String.valueOf(available) + "<br>");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}