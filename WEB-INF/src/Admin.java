import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;

// Extend HttpServlet class
// Admin Servlet will be loaded on Web Server startup (specified in web.xml file)
public class Admin extends HttpServlet {
   private String message = "";
   private ServletContext application; // Get context for logging purposes
   private SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

   public void init() throws ServletException {
      // Do required initialization
      application = getServletContext();  // Get context for logging purposes
      application.setAttribute("registrationtime", new ConcurrentHashMap<String, Date>()); // To keep track of current registration records

      // Open registration file and parse the csv file of data (immediately when server starts up)
      // Also stores the data after parsing in a hashmap, stored as an application variable, for easier access
//      CSVUtils csvutils = new CSVUtils("C:\\apache-tomcat-9.0.10\\webapps\\captiss\\files\\masters_tea_060918\\masterstea0960918.csv",
      application.log(application.getRealPath("WEB-INF/files/masters_tea_060918/masterstea060918.csv"));
      CSVUtils csvutils = new CSVUtils(application.getRealPath("WEB-INF/files/masters_tea_060918/masterstea060918.csv"),
              new ArrayList<>() {{add("id"); add("name"); add("email");}}, 1);
      HashMap<String, HashMap<String, String>> allLines = csvutils.getLines();	// Get array of all the data
      application.setAttribute("registrationrecords", allLines); // Store in server application for all to access
      application.log("Successfully pulled registration records from file");

      // Pull existing attendance records
      csvutils = new CSVUtils(application.getRealPath("WEB-INF/files/masters_tea_060918/outputfiles/outputfile.csv"),
              new ArrayList<>() {{add("id"); add("timein"); }}, 1);
      allLines = csvutils.getLines();	// Get array of all the data
      if (!allLines.isEmpty()) { // Check if the file has contents
         ConcurrentHashMap<String, Date> registrationtime = new ConcurrentHashMap<>();
         for (String key : allLines.keySet()) {
            HashMap<String, String> record = allLines.getOrDefault(key, null);
            if (record != null) {
               Date timein = null;
               try {
                  timein = dateFormatter.parse(record.get("timein"));
               } catch (ParseException e) {
                  application.log("Error while parsing time-in from record " + record.get("id"));
               }
               if (timein != null)
                  registrationtime.put(record.get("id"), timein);
            }
         }
         application.setAttribute("registrationtime", registrationtime);
         application.log("Successfully pulled previous attendance records from file");
      }

      // Get web server's current IP address and store it as an application variable
      application.setAttribute("ipaddress", "<unknown>");
      try {
         InetAddress ip = InetAddress.getLocalHost();
         application.setAttribute("ipaddress", ip.getHostAddress());
         application.log("Current IP address : " + ip.getHostAddress());
      } catch (UnknownHostException e) {
         e.printStackTrace();
      }
   }

   // Check if user is logged in
   private void verifyLoggedIn(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      if (request.getSession(false) == null
              || request.getSession(true).getAttribute("username") == null ) {
         // Redirect back to login page
         RequestDispatcher dispatcher = application.getRequestDispatcher("/index.jsp");
         dispatcher.forward(request, response);
         return;
      }
   }

   // Method to handle POST method request.
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      verifyLoggedIn(request, response);

      FileOutputStream fout = null;

      // Back up the data to the file
      try {
//         File file = new File(application.getRealPath("WEB-INF\\files\\masters_tea_060918\\outputfile.csv"));
         File file = new File(application.getRealPath("WEB-INF/files/masters_tea_060918/outputfiles/outputfile.csv"));
//                 + fileNameFormatter.format(new Date()) + ".csv"));
         file.createNewFile();
         fout = new FileOutputStream(file);
         fout.write("ID,TimeIn\n".getBytes()); // Write header lines
         ConcurrentHashMap<String, Date> registrationtime =
                 (ConcurrentHashMap<String, Date>) application.getAttribute("registrationtime");
         for (String key : registrationtime.keySet()) {  // Write data
            Date time = registrationtime.getOrDefault(key, null);
            if (time != null)
               fout.write((key + "," + dateFormatter.format(time) + "\n").getBytes());
         }

         application.log("Successfully written to output file");
      } catch (FileNotFoundException e) {
         application.log("Error when trying to open file to write to (the file may be open), please try writing again");
      } catch (Exception e) {
         application.log("General exception thrown during file opening or writing, but not sure what it is");
      } finally {
         if (fout != null)
            fout.close();
      }

      // Update IP address
      try {
         InetAddress ip = InetAddress.getLocalHost();
         application.setAttribute("ipaddress", ip.getHostAddress());
         application.log("Current IP address : " + ip.getHostAddress());
      } catch (UnknownHostException e) {
         e.printStackTrace();
      }

      // Redirect back to admin page
      RequestDispatcher dispatcher = application.getRequestDispatcher("/admin.jsp");
      dispatcher.forward(request, response);
   }

   // Method to handle GET method request.
   public void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      verifyLoggedIn(request, response);

      // Redirect back to admin page
      RequestDispatcher dispatcher = application.getRequestDispatcher("/admin.jsp");
      dispatcher.forward(request, response);
   }

   public void destroy() {
      // do nothing.
   }
}