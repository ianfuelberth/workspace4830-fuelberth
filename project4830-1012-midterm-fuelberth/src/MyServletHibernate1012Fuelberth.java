

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.MyEmployeeFuelberth;
import util.UtilDB;
import util.UtilFile;

/**
 * Servlet implementation class MyServletHibernate1012Fuelberth
 */
@WebServlet("/MyServletHibernate1012Fuelberth")
public class MyServletHibernate1012Fuelberth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServletHibernate1012Fuelberth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		String filename = "/WEB-INF/input.csv";
		List<String> contents = UtilFile.readFile(getServletContext(), filename);
		
		for (String entry : contents) 
		{
			String[] values = entry.split(",");
			
			UtilDB.createEmployees(values[0], values[1], values[2], values[3]);
			System.out.println(entry);
		}
		
 
        retrieveDisplayData(response.getWriter());
     } 

     void retrieveDisplayData(PrintWriter out) {
        String title = "Database Result";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
              "transitional//en\">\n"; //
        out.println(docType + //
              "<html>\n" + //
              "<head><title>" + title + "</title></head>\n" + //
              "<body bgcolor=\"#f0f0f0\">\n" + //
              "<h1 align=\"center\">" + title + "</h1>\n");
        out.println("<ul>");
        List<MyEmployeeFuelberth> listEmployees = UtilDB.listEmployees();
        for (MyEmployeeFuelberth myEmployeeFuelberth : listEmployees) {
           System.out.println("[DBG] " + myEmployeeFuelberth.getId() + ", " //
                 + myEmployeeFuelberth.getFirstName() + ", " //
                 + myEmployeeFuelberth.getLastName() + ", " //
                 + myEmployeeFuelberth.getPhone() + ", " //
                 + myEmployeeFuelberth.getEmail());

           out.println("<li>" + myEmployeeFuelberth.getId() + ", " //
                 + myEmployeeFuelberth.getFirstName() + ", " //
                 + myEmployeeFuelberth.getLastName() + ", " //
                 + myEmployeeFuelberth.getPhone() + ", " //
                 + myEmployeeFuelberth.getEmail() + "</li>");
        }
        out.println("</ul>");
        out.println("</body></html>");
     }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
