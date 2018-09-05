import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

// Extend HttpServlet class
public class FormalDinner extends HttpServlet {
   private String message = "";
   private HashMap<String, HashMap<String, String>> allLines;
   private ServletContext application;

   public void init() throws ServletException {
      application = getServletContext();  // Get context for logging purposes

      // Retrieve records already held on the server
      allLines = (HashMap<String, HashMap<String, String>>)application.getAttribute("registrationrecords");
   }

   // Method to handle GET method request.
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      // Set response content type
      response.setContentType("text/html");
      message = request.getParameter("qrcode");

      // Get corresponding record based on qr code (id)
      HashMap<String, String> record = allLines.get(message);
      message = "Error: no registration record found for QR code provided."; // set default message
      if (record != null) {   // Found record
         ConcurrentHashMap<String, Date> registrationTime = (ConcurrentHashMap<String, Date>)application.getAttribute("registrationtime");

         if (!registrationTime.containsKey(record.get("id"))) {   // If haven't been registered previously
            registrationTime.putIfAbsent(record.get("id"), new Date()); // Add registration record
//            message = "Welcome, " + record.get("name") + ", you have been successfully registered.";
            message = "Welcome, <b>" + record.get("name") + "</b> from <b>" + record.get("house") + "</b>!"
                    + " Please confirm that you are having <b>"
                    +  record.get("dietary").substring(0, record.get("dietary").indexOf(':')) + ", " + record.get("halal") + ".</b>";
         } else { // Already registered, do nothing
//            message = "Welcome back, " + record.get("name") + ", you have already been registered previously.";
            message = "Welcome back, <b>" + record.get("name") + "</b> from <b>" + record.get("house") + "</b>!"
                    + " Please confirm that you are having <b>"
                    +  record.get("dietary").substring(0, record.get("dietary").indexOf(':')) + ", " + record.get("halal") + ".</b>";
         }
      } else {
         // No record found
      }

      // Redirect to qrcode request page with welcome message
      request.setAttribute("responsemessage", message);
      RequestDispatcher dispatcher = application.getRequestDispatcher("/formaldinner.jsp");
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