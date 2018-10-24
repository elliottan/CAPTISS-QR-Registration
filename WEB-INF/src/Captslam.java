import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Extend HttpServlet class
public class Captslam extends HttpServlet {
    private String message = "";
    private HashMap<String, HashMap<String, String>> allLines;
    private ServletContext application;

    public void init() throws ServletException {
        application = getServletContext();  // Get context for logging purposes
    }

    // Method to handle POST method request.
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        int stationNumber = Integer.parseInt((String)request.getParameter("stationnumber"));
        int numberDone = Integer.parseInt((String)request.getParameter("numberdone"));
        List<List<String>> stationDescriptions = (List<List<String>>)application.getAttribute("stationdescriptions");
        List<String> temp = stationDescriptions.get(stationNumber-1);
        temp.set(3, ((Integer)(Integer.parseInt(temp.get(3)) - numberDone)).toString());

        // Redirect to qrcode request page with welcome message
        RequestDispatcher dispatcher = application.getRequestDispatcher("/captslam.jsp" + "?stationnumber=" + stationNumber);
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
