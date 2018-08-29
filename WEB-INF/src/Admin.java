import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
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
      CSVUtils csvutils = new CSVUtils("C:\\apache-tomcat-9.0.10\\webapps\\captiss\\files\\masterstea30aug2018.csv",
              new ArrayList<>() {{add("id"); add("name"); add("email");}}, 2);
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
      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

      // Back up the data to the file
      FileOutputStream fout = null;
      try {
         File file = new File("C:\\apache-tomcat-9.0.10\\webapps\\captiss\\files\\outputfile.csv");
         file.createNewFile();
         fout = new FileOutputStream(file);
         fout.write("ID,TimeIn\n".getBytes()); // Write header lines
         ConcurrentHashMap<String, Date> registrationtime =
                 (ConcurrentHashMap<String, Date>) application.getAttribute("registrationtime");
         for (String key : registrationtime.keySet()) {  // Write data
            Date time = registrationtime.getOrDefault(key, null);
            if (time != null)
               fout.write((key + "," + formatter.format(time) + "\n").getBytes());
         }

         application.log("Successfully written to output file");
      } catch (FileNotFoundException e) {
         application.log("Error when trying to open file to write to (the file may be open), please try writing again");
      } finally {
         if (fout != null)
            fout.close();
      }

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