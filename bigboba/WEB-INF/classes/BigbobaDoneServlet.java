// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;             // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;             // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/bigbobadone")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class BigbobaDoneServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();

      // Print an HTML page as the output of the query
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head><title>Preparing order</title></head>");
      out.println("<style>");
      out.println("body {  background-image: url('https://media.istockphoto.com/vectors/vector-seamless-pattern-with-bubble-tea-in-plastic-cups-and-doodles-vector-id1273254729?k=20&m=1273254729&s=612x612&w=0&h=H8kLcd9Eup3rztMsu4eMhDlZOcu-uv7dI2u2k3L86eM=');  background-repeat: no-repeat;  background-attachment: fixed;  background-size: cover;}");
      out.println(".content {  max-width: 5000px;  margin: auto;  background: white;  padding: 10px;  opacity: 0.85;}");
      out.println("input[type=checkbox] {transform: scale(1.5);}");
      out.println(".button {  display: inline-block;  padding: 15px 25px; font-weight: bold; font-size: 25px  cursor: pointer;  text-align: center;  text-decoration: none;  outline: none;  color: #361212;  background-color: #eff4c3;  border: none;  border-radius: 15px;  box-shadow: 0 9px #999;}");
      out.println(".button:hover {background-color: #f2d5c8}");
      out.println(".button:active {  background-color: #f2d5c8;  box-shadow: 0 5px #666;  transform: translateY(4px);}");
      out.println("</style>");
      out.println("</head>");
      out.println("<body>");
      out.println("<center>");
      out.println("<div class=\"content\">");
      out.println(" <h1><p style=\"font-family:Trebuchet MS; font-size: 50px; color:#c17e2c\"><b>Your Order Number is:</b></p></h1>");



      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/bigboba?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
      String sqlStr = "UPDATE queue SET no = no +1 where name='number';";
      stmt.executeUpdate(sqlStr);
      String sqlStr1 = "SELECT * from queue where name ='number'";
      ResultSet rset = stmt.executeQuery(sqlStr1);
      if (rset.next()) {
      String qno= String.format("%04d",rset.getInt("no"));
      out.println("<p style=\"font-family:Trebuchet MS; font-size: 60px; color:#ceb195\">" + qno + " </p>");
      }
      out.println("<img src=\"https://i.imgur.com/tLiR7XB.gif\" alt=\"Bubble Tea\" >");
      out.println(" <h1><p style=\"font-family:Trebuchet MS; font-size: 35px; color:#c17e2c\"><b>Your order is being prepared right now...</b></p></h1>");

      } catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
      out.println(" <br> </br>");
      out.println("<form  method=\"get\" action=\"bigbobaquery\">");
      out.println("<input type=\"submit\" class=\"button\" value=\"Order Again\" >");
      out.println("</form>");
      out.println("</div></center></body></html>");
      out.close();
   }

   @Override
   public void doPost (HttpServletRequest request, HttpServletResponse response)
                   throws ServletException, IOException {
      doGet(request, response);  // Re-direct POST request to doGet()
   }
}