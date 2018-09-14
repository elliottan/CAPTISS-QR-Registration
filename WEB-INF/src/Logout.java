import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// Extend HttpServlet class
public class Logout extends HttpServlet {
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
      // Remove session variable
      HttpSession session = request.getSession();
      session.removeAttribute("username");
      session.invalidate();

      // Logged out, edirect to login page
      request.setAttribute("responsemessage", "You have successfully logged out.");
      RequestDispatcher dispatcher = application.getRequestDispatcher("/index.jsp");
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