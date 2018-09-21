import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

// Extend HttpServlet class
public class Print extends HttpServlet {
    private String message = "";
    private ServletContext application; // Get context for logging purposes

    public void init() throws ServletException {
        // Do required initialization
        application = getServletContext();  // Get context for logging purposes
    }

    // Method to handle POST method request.
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Remove current queue elements and send them to printqueue.jsp as a string that can be split (delimited by ,)
        ConcurrentLinkedQueue<String> printQueue = (ConcurrentLinkedQueue<String>)application.getAttribute("printqueue");
        HashMap<String, HashMap<String, String>> allLines = (HashMap<String, HashMap<String, String>>)application.getAttribute("registrationrecords");
        StringBuilder printString = new StringBuilder();
        while (!printQueue.isEmpty()) {
            String id = printQueue.poll();
            HashMap<String, String> record = allLines.get(id);

            // Cat 1 or 2, print panel
            if (record.get("cat").equals("1") || record.get("cat").equals("1.5") || record.get("cat").equals("2")) {
                printString.append(id + "|" + record.get("name") + "|"
                        + (record.get("p1").isEmpty() ? "-" : record.get("p1").charAt(0)) + "|"
                        + (record.get("p2").isEmpty() ? "-" : record.get("p2").charAt(0)) + "|"
                        + (record.get("p3").isEmpty() ? "-" : record.get("p3").charAt(0)) + "|"
                        + (record.get("p4").isEmpty() ? "-" : record.get("p4").charAt(0)) + ";");
            } else {
                printString.append(id + "|" + record.get("name") + "|"
                        + record.get("cat") + ";");
            }
        }

        // Send print string to print page
        application.setAttribute("printstring", printString.toString());
        RequestDispatcher dispatcher = application.getRequestDispatcher("/printqueue.jsp");
        dispatcher.forward(request, response);
    }

    // Method to handle GET method request.
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}