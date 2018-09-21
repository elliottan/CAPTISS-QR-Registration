import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Handle walk-in (on the spot) registration
 */
public class WalkInResearchForum extends HttpServlet {
    private String message;
    private HashMap<String, HashMap<String, String>> allLines;
    private ServletContext application;
    private ConcurrentHashMap<String, Date> registrationTime;

    public void init() throws ServletException {
        message = "";
        application = getServletContext();  // Get context for logging purposes

        // Retrieve records already held on the server
        allLines = (HashMap<String, HashMap<String, String>>) application.getAttribute("registrationrecords");
        registrationTime = (ConcurrentHashMap<String, Date>) application.getAttribute("registrationtime");
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

        // Get parameters sent from walkin_researchforum.jsp page
        String name = request.getParameter("name");
        String org = request.getParameter("org");
        String title = request.getParameter("title");
        switch (title) {
            case "prof":
                title = "Prof";
                break;
            case "assocprof":
                title = "Assoc Prof";
                break;
            case "dr":
                title = "Dr";
                break;
            case "mr":
                title = "Mr";
                break;
            case "mrs":
                title = "Mrs";
                break;
            case "ms":
                title = "Ms";
                break;
            default:
                title = "";
                break;
        }

        HashMap<String, String> newRecord = new HashMap<>();
        String newUuid = UUID.randomUUID().toString(); // Generate walk-in QR code/ID
        newRecord.put("id", newUuid);
        newRecord.put("name", name.trim());
        newRecord.put("title", title);
        newRecord.put("org", org.trim());

        // Store in registration records, as well as registration time (attendance)
        allLines.put(newUuid, newRecord);
        if (!registrationTime.containsKey(newUuid)) {   // If haven't been registered previously
            registrationTime.putIfAbsent(newUuid, new Date()); // Add registration record
            message = "Welcome, <h3>" + newRecord.get("name") + "</h3>! You have been successfully registered.";
        } else { // Already registered, do nothing
            message = "Welcome back, <h3>" + newRecord.get("name") + "</h3>, you have already been registered previously.";
        }

        // Write to original input file
        FileOutputStream fout = null;
        try {
            File file = new File(application.getRealPath("WEB-INF/files/" + Admin.fileFolder + "/" + Admin.fileName));
            fout = new FileOutputStream(file, true);
            fout.write((newUuid + "," + newRecord.get("name") + "," + newRecord.get("title") + "," + newRecord.get("org") + "\n").getBytes()); // append to end of file
            application.log("Successfully updated to input .csv file");
        } catch (FileNotFoundException e) {
            application.log("Error when trying to open file to write to (the file may be open), please try writing again");
        } catch (Exception e) {
            application.log("General exception thrown during file opening or writing, but not sure what it is");
        } finally {
            if (fout != null)
                fout.close();
        }

        application.log("New walk-in registration: " + newUuid + ", " + newRecord.get("name"));

        // Redirect back to check-in page
        request.setAttribute("responsemessage", message);
        RequestDispatcher dispatcher = application.getRequestDispatcher("/checkin_secondary.jsp");
        dispatcher.forward(request, response);
    }

    // Method to handle POST method request.
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // If user is not logged in, redirect to login page
        if (!Admin.isLoggedIn(request, response)) {
            // Redirect back to login page
            RequestDispatcher dispatcher = application.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Redirect to walk-in page
        RequestDispatcher dispatcher = application.getRequestDispatcher("/checkin_secondary.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}