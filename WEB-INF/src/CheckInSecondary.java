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
public class CheckInSecondary extends HttpServlet {
    private String message = "";
    private HashMap<String, HashMap<String, String>> allLines;
    private ServletContext application;

    public void init() throws ServletException {
        application = getServletContext();  // Get context for logging purposes
    }

    // Method to handle POST method request.
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // If user is not logged in, redirect to login page
        if (!Admin.isLoggedIn(request, response)) {
            // Redirect back to login page
            RequestDispatcher dispatcher = application.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Set response content type
        response.setContentType("text/html");

        // Get qrcode input and find corresponding record
        message = request.getParameter("qrcode");

        HashMap<String, String> record = null;
        if (message != null && !message.trim().isEmpty()) {
            allLines = (HashMap<String, HashMap<String, String>>) application.getAttribute("registrationrecords");

            // Get corresponding record based on qr code (id)
            record = allLines.get(message.trim());
        }

        message = "Error: no registration record found for QR code provided."; // set default message
        if (record != null) {   // Found record
            // Retrieve records already held on the server
            ConcurrentHashMap<String, Date> registrationTime = (ConcurrentHashMap<String, Date>) application.getAttribute("registrationtime");
            ConcurrentHashMap<String, Date> registrationTimeSecondary = (ConcurrentHashMap<String, Date>) application.getAttribute("registrationtime_secondary");
            if (!registrationTime.containsKey(record.get("id"))) {   // If haven't been registered previously
                message = "Error: not yet registered at the main counter.";
            } else if (!registrationTimeSecondary.containsKey(record.get("id"))) {
                registrationTimeSecondary.putIfAbsent(record.get("id"), new Date()); // Add registration record
                message = "Hello, <h3>" + record.get("name") + "</h3>! Welcome to " + Admin.secondaryVenueName + "!";
            } else {
                message = "Welcome back, <h3>" + record.get("name")
                        + "</h3>, you have already been registered at " + Admin.secondaryVenueName + " previously.";
            }
        }
        
        request.setAttribute("responsemessage", message);

        // Update registration conter, and back up files if necessary
        application.log("" + Admin.updateRegistrationCount());
        if (Admin.isTimeToBackup()) {
            application.log("time to backup");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Admin");
            dispatcher.forward(request, response);
        } else {
            // Redirect to qrcode request page with welcome message
            RequestDispatcher dispatcher = application.getRequestDispatcher("/checkin_secondary.jsp");
            dispatcher.forward(request, response);
        }
    }

    // Method to handle GET method request.
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // If user is not logged in, redirect to login page
        if (!Admin.isLoggedIn(request, response)) {
            // Redirect back to login page
            RequestDispatcher dispatcher = application.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            return;
        }

        RequestDispatcher dispatcher = application.getRequestDispatcher("/checkin_secondary.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}
