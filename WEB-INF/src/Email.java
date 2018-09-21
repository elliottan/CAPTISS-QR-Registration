//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.activation.*;
//
//// Extend HttpServlet class
//// Admin Servlet will be loaded on Web Server startup (specified in web.xml file)
//public class Email extends HttpServlet {
//   private String message = "";
//   private HashMap<String, HashMap<String, String>> allLines;
//   private ServletContext application; // Get context for logging purposes
//
//   public void init() throws ServletException {
//      // Do required initialization
//      application = getServletContext();  // Get context for logging purposes
//
//      // Retrieve records already held on the server
//      allLines = (HashMap<String, HashMap<String, String>>)application.getAttribute("registrationrecords");
//   }
//
//   // Method to handle GET method request.
//   public void doGet(HttpServletRequest request, HttpServletResponse response)
//      throws ServletException, IOException {
//         application.log("Email servlet called");
//      // Recipient's email ID needs to be mentioned.
//      String to = "abcd@gmail.com";
//
//      // Sender's email ID needs to be mentioned
//      String from = "web@gmail.com";
//
//      // Assuming you are sending email from localhost
//      String host = "localhost";
//
//      // Get system properties
//      Properties properties = System.getProperties();
//
//      // Setup mail server
//      properties.setProperty("mail.smtp.host", host);
//
//      // Get the default Session object.
//      Session session = Session.getDefaultInstance(properties);
//
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//
//      try {
//         // Create a default MimeMessage object.
//         MimeMessage message = new MimeMessage(session);
//
//         // Set From: header field of the header.
//         message.setFrom(new InternetAddress(from));
//
//         // Set To: header field of the header.
//         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//         // Set Subject: header field
//         message.setSubject("This is the Subject Line!");
//
//         // Create the message part
//         BodyPart messageBodyPart = new MimeBodyPart();
//
//         // Fill the message
//         messageBodyPart.setText("This is message body");
//
//         // Create a multipar message
//         Multipart multipart = new MimeMultipart();
//
//         // Set text message part
//         multipart.addBodyPart(messageBodyPart);
//
//         // Part two is attachment
//         messageBodyPart = new MimeBodyPart();
//         String filename = "file.txt";
//         DataSource source = new FileDataSource(filename);
//         messageBodyPart.setDataHandler(new DataHandler(source));
//         messageBodyPart.setFileName(filename);
//         multipart.addBodyPart(messageBodyPart);
//
//         // Send the complete message parts
//         message.setContent(multipart );
//
//         // Send message
//         Transport.send(message);
//         String title = "Send Email";
//         String res = "Sent message successfully....";
//         String docType =
//                 "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
//
//         out.println(docType +
//                 "<html>\n" +
//                 "<head><title>" + title + "</title></head>\n" +
//                 "<body bgcolor = \"#f0f0f0\">\n" +
//                 "<h1 align = \"center\">" + title + "</h1>\n" +
//                 "<p align = \"center\">" + res + "</p>\n" +
//                 "</body>
//                 </html>"
//         );
//      } catch (MessagingException mex) {
//         mex.printStackTrace();
//      }
//
//      // Redirect back to admin page
//      RequestDispatcher dispatcher = application.getRequestDispatcher("/admin_secondary.jsp");
//      dispatcher.forward(request, response);
//   }
//
//   // Method to handle POST method request.
//   public void doPost(HttpServletRequest request, HttpServletResponse response)
//           throws ServletException, IOException {
//      doGet(request, response);
//   }
//
//   public void destroy() {
//      // do nothing.
//   }
//}