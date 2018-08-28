import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.FileSystems;

// Extend HttpServlet class
public class HelloWorld extends HttpServlet {
   private String message;

   public void init() throws ServletException {
      // Do required initialization
      message = "Hello World";

      ServletContext application = getServletContext();  // Get context for logging purposes
//      sContext.log(System.getProperty("user.dir"));
//      Path path = FileSystems.getDefault().getPath(".").toAbsolutePath();
//      sContext.log(path.toString());

      // Open and parse the csv file of data
      CSVUtils csvutils = new CSVUtils("C:\\apache-tomcat-9.0.10\\webapps\\captiss\\farewellattendance.csv",
              new ArrayList<>() {{add("p1no"); add("nric"); add("name"); add("diet"); add("salutatino");
              add("halal"); add("gender"); add("qrno"); add("picpath"); add("email");}}, 1);
      HashMap<String, HashMap<String, String>> allLines = csvutils.getLines();	// Get array of all the data
//      sContext.log(allLines.toString());
      application.setAttribute("farewellrecords", allLines);
      for (HashMap<String, String> line : allLines.values()) {	// Print each line of data
         message += line + "<br />";
      }
   }

   // Method to handle GET method request.
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      // Set response content type
      response.setContentType("text/html");
//      message = request.getParameter("qrcode");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.println("<h1>" + message + "</h1>");
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