import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.FileSystems;

// Extend HttpServlet class
public class Login extends HttpServlet {
    private String message;
    ServletContext application;

    public void init() throws ServletException {
        // Do required initialization
        message = "";
        application = getServletContext();  // Get context for logging purposes
    }

    // Method to handle GET method request.
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters sent from index.jsp page
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Ensure valid credentials
        if (username.equalsIgnoreCase("admin")
                && password.equals("admin")) {
            // Valid login, redirect to admin page
            RequestDispatcher dispatcher = application.getRequestDispatcher("/admin.jsp");
            dispatcher.forward(request, response);

            // Set session variable so that user name access all other pages
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
        } else {
            // Invalid credentials, redirect to login page
            request.setAttribute("responsemessage", "Invalid username or password");
            RequestDispatcher dispatcher = application.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
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