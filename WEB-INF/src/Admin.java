import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;

// Extend HttpServlet class
// Admin Servlet will be loaded on Web Server startup (specified in web.xml file)
public class Admin extends HttpServlet {
   private String message;
   private ServletContext application; // Get context for logging purposes

   public void init() throws ServletException {
      // Do required initialization
      message = "";

      application = getServletContext();  // Get context for logging purposes
      application.setAttribute("registrationtime", new ConcurrentHashMap<String, Date>()); // To keep track of current registration records

      // Open and parse the csv file of data (immediately when server starts up)
      CSVUtils csvutils = new CSVUtils("C:\\apache-tomcat-9.0.10\\webapps\\captiss\\masterstea30aug2018.csv",
              new ArrayList<>() {{add("id"); add("name"); add("email");}}, 1);
      HashMap<String, HashMap<String, String>> allLines = csvutils.getLines();	// Get array of all the data
      application.setAttribute("masterstearecords", allLines); // Store in server application for all to access
      application.log("Successfully pulled masters tea records from file");

      InetAddress ip;
      try {

         ip = InetAddress.getLocalHost();
         application.setAttribute("ipaddress", ip.getHostAddress());
         application.log("Current IP address : " + ip.getHostAddress());
      } catch (UnknownHostException e) {
         e.printStackTrace();
      }
   }

   // Method to handle GET method request.
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      // Back up the data to the file
      application.log("Writing data to file...");

      // Redirect back to admin page
      RequestDispatcher dispatcher = application.getRequestDispatcher("/admin.jsp");
      dispatcher.forward(request, response);
   }

   // Method to handle POST method request.
   public void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      doGet(request, response);
   }

   public void destroy() {
      // do nothing.
   }
}