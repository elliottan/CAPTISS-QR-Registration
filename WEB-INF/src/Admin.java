import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        application.setAttribute("registrationtime_masterstea", new ConcurrentHashMap<String, Date>()); // To keep track of current registration records

        pullRecordsFromFile("WEB-INF/files/research_forum_150918/researchforum150918.csv",
                new ArrayList<>() {{
                    add("id");
                    add("name");
                    add("title");
                    add("org");
                    add("email");
                    add("lunch");
                    add("tea");
                    add("imgpath");
                    add("imgurl");
                }}, "registrationrecords");

        pullExistingAttendanceRecordsFromFile("WEB-INF/files/research_forum_150918/outputfiles/outputfile.csv",
                new ArrayList<>() {{
                    add("id");
                    add("timein");
                }}, "registrationtime");

        pullRecordsFromFile("WEB-INF/files/masters_tea_130918/masterstea130918.csv",
                new ArrayList<>() {{
                    add("id");
                    add("name");
                    add("email");
                    add("imgpath");
                    add("imgurl");
                }}, "registrationrecords_masterstea");

        pullExistingAttendanceRecordsFromFile("WEB-INF/files/masters_tea_130918/outputfiles/outputfile.csv",
                new ArrayList<>() {{
                    add("id");
                    add("timein");
                }}, "registrationtime_masterstea");

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

    private void pullRecordsFromFile(String filePath, ArrayList<String> headers, String appAttributeName) {
        // Open registration file and parse the csv file of data (immediately when server starts up)
        // Also stores the data after parsing in a hashmap, stored as an application variable, for easier access
        application.log(application.getRealPath(filePath));
        CSVUtils csvutils = new CSVUtils(application.getRealPath(filePath), headers, 1);
        HashMap<String, HashMap<String, String>> allLines = csvutils.getLines();    // Get array of all the data
        application.setAttribute(appAttributeName, allLines); // Store in server application for all to access
        application.log("Successfully pulled registration records from file");
    }

    private void pullExistingAttendanceRecordsFromFile(String filePath, ArrayList<String> headers, String appAttributeName) {
        // Pull existing attendance records
        CSVUtils csvutils = new CSVUtils(application.getRealPath(filePath), headers, 1);
        HashMap<String, HashMap<String, String>> allLines = csvutils.getLines();    // Get array of all the data
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
            application.setAttribute(appAttributeName, registrationtime);
            application.log("Successfully pulled previous attendance records from file");
        }
    }

    // Check if user is logged in
    public static boolean isLoggedIn(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession(false) == null
                || request.getSession(true).getAttribute("username") == null) {
            return false;
        }
        return true;
    }

    private void backupToFile(String filePath, String appAttributeName) throws IOException {
        FileOutputStream fout = null;

        // Back up the data to the file
        try {
            File file = new File(application.getRealPath(filePath));
            file.createNewFile();
            fout = new FileOutputStream(file);
            fout.write("ID,TimeIn\n".getBytes()); // Write header lines
            ConcurrentHashMap<String, Date> registrationtime =
                    (ConcurrentHashMap<String, Date>) application.getAttribute(appAttributeName);
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

        backupToFile("WEB-INF/files/research_forum_150918/outputfiles/outputfile.csv", "registrationtime");
        backupToFile("WEB-INF/files/masters_tea_130918/outputfiles/outputfile.csv", "registrationtime_masterstea");

        // Update IP address
        try {
            InetAddress ip = InetAddress.getLocalHost();
            application.setAttribute("ipaddress", ip.getHostAddress());
            application.log("Current IP address : " + ip.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // Redirect back to admin page
        RequestDispatcher dispatcher = application.getRequestDispatcher("/admin_researchforum.jsp");
        dispatcher.forward(request, response);
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

        // Redirect back to admin page
        RequestDispatcher dispatcher = application.getRequestDispatcher("/admin_researchforum.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}