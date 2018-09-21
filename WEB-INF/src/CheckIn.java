import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

// Extend HttpServlet class
public class CheckIn extends HttpServlet {
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
        message = request.getParameter("print");
        boolean toPrint = message != null && message.equalsIgnoreCase("print") ? true : false;
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
            if (!registrationTime.containsKey(record.get("id"))) {   // If haven't been registered previously
                registrationTime.putIfAbsent(record.get("id"), new Date()); // Add registration record
                message = "Welcome, <h3>" + record.get("name") + "</h3>! You have been successfully registered.";
                toPrint = true;
            } else { // Already registered, do nothing
                message = "Welcome back, <h3>" + record.get("name") + "</h3>, you have already been registered previously.";
            }

            // Add this record into the print queue
            if (toPrint) {
                ConcurrentLinkedQueue<String> printQueue = (ConcurrentLinkedQueue<String>) application.getAttribute("printqueue");
                printQueue.offer(record.get("id"));
            }
        }

        // Redirect to qrcode request page with welcome message
        request.setAttribute("responsemessage", message);
        // Update registration conter, and back up files if necessary
        application.log("" + Admin.updateRegistrationCount());
        if (Admin.isTimeToBackup()) {
            application.log("time to backup");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Admin");
            dispatcher.forward(request, response);
        } else {
            // Redirect to qrcode request page with welcome message
            RequestDispatcher dispatcher = application.getRequestDispatcher("/captiss.jsp");
//            RequestDispatcher dispatcher = application.getRequestDispatcher("/checkin.jsp");
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

        RequestDispatcher dispatcher = application.getRequestDispatcher("/checkin.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}
