// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;             // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;             // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/bigbobaaddons")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class BigbobaAddonsServlet extends HttpServlet {

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
      out.println("<head><title>Choose your ADD-ONs</title></head>");
      out.println("<style>");
      out.println("body {  background-image: url('https://media.istockphoto.com/vectors/vector-seamless-pattern-with-bubble-tea-in-plastic-cups-and-doodles-vector-id1273254729?k=20&m=1273254729&s=612x612&w=0&h=H8kLcd9Eup3rztMsu4eMhDlZOcu-uv7dI2u2k3L86eM=');  background-repeat: no-repeat;  background-attachment: fixed;  background-size: cover;}");
      out.println(".content {  max-width: 5000px;  margin: auto;  background: white;  padding: 10px;  opacity: 0.85;}");
      out.println("input[type=checkbox] {transform: scale(1.5);}");
      out.println(".button {  display: inline-block;  padding: 15px 25px; font-weight: bold; font-size: 25px  cursor: pointer;  text-align: center;  text-decoration: none;  outline: none;  color: #361212;  background-color: #eff4c3;  border: none;  border-radius: 15px;  box-shadow: 0 9px #999;}");
      out.println(".button:hover {background-color: #f2d5c8}");
      out.println(".button:active {  background-color: #f2d5c8;  box-shadow: 0 5px #666;  transform: translateY(4px);}");
      out.println("p.round {  border: 6px solid #c17e2c;  border-radius: 12px;  padding: 5px;}");
      out.println("</style>");
      out.println("</head>");
      out.println("<body>");
      out.println("<center>");
      out.println("<div class=\"content\">");
      out.println("<img src=\"https://cdn-icons-png.flaticon.com/512/3428/3428877.png\" alt=\"Bubble Tea\" style=\"width:350px;height:350px;\">");
      out.println(" <h1><p style=\"font-family:Trebuchet MS; font-size: 50px; color:#c17e2c\"><b>CONFIRM YOUR ORDER</b></p></h1>");
      out.println("<form method='get' action='bigbobaorder'>");


      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/bigboba?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
      //Get orders
      String[] ids = request.getParameterValues("id");  // Returns an array of Strings
      //selecting
      String sqlStr = "SELECT * FROM tea WHERE id IN (";
         for (int i = 0; i < ids.length; ++i) {
            if (i < ids.length - 1) {
               sqlStr += "'" + ids[i] + "', ";  // need a commas
            } else {
               sqlStr += "'" + ids[i] + "'";    // no commas
            }
         }sqlStr += ")";

         //out.println("<p>Your SQL statement is: " + sqlStr + "</p>"); // Echo for debugging
         ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server

         // Step 4: Process the query result set
         
         //order table
         while(rset.next()) {
            if (rset.getInt("qty")>0) {
               // Print a paragraph <p>...</p> for each record
               out.println("<p class=\"round\" style=\"font-family:Trebuchet MS; font-size: 20px; color:#c17e2c\">"
                  + rset.getString("name") + "&nbsp;&nbsp;$ " + rset.getDouble("price")
                     +"          <input type='hidden' name='teaid' value='"+rset.getString("id")+"'/></p>");
               out.println("<p style=\"font-family:Trebuchet MS; font-size: 15px; color:#c17e2c\">");
               out.println("<u><b>ADD-ONs</b></u>");
               out.println("<input type='checkbox' name='"+rset.getString("id")+"[]' value='1'/> Pearl $1.50 "
                  + "<input type='checkbox' name='"+rset.getString("id")+"[]' value='2'/> Aloe Vera $1.50 "
                  + "<input type='checkbox' name='"+rset.getString("id")+"[]' value='3'/> Coconut Jelly $1.50 "
                  + "<input type='checkbox' name='"+rset.getString("id")+"[]' value='4'/> Sakura Ai Yu Jelly $1.50 "
                  + "<input type='checkbox' name='"+rset.getString("id")+"[]' value='5'/> Brown Sugar Pearl $2.00 "
                  + "<input type='checkbox' name='"+rset.getString("id")+"[]' value='6'/> White Pearl $2.00 "
                  + "<input type='checkbox' name='"+rset.getString("id")+"[]' value='7'/> CheezHO $4.00 ");
               out.println("</p>");
               out.println("<br></br>");
            } else {
               out.println("<p class=\"round\" style=\"font-family:Trebuchet MS; font-size: 20px; color:#c17e2c\">"
               + rset.getString("name") + " is out of stock'/></p>");
            }
         }
            sqlStr = "UPDATE tea SET qty = qty - 1 WHERE id IN (";
            for (int i = 0; i < ids.length; ++i) {
               if (i < ids.length - 1) {
                  sqlStr += "'" + ids[i] + "', ";  // need a commas
               } else {
                  sqlStr += "'" + ids[i] + "'";    // no commas
               }
            }sqlStr += ")";
               //out.println("<p>" + sqlStr + "</p>");  // for debugging
               stmt.executeUpdate(sqlStr);
            
      
      
      out.println("<input type=\"submit\" class=\"button\" value=\"CONFIRM\" >");
      out.println("</form>");
      out.println("<br></br>");
      out.println("<form>");
      out.println(" <input type=\"button\" class=\"button\" value=\"BACK\" onclick=\"history.back()\">");
      out.println("</form>");

      } catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
 
      out.println("</div></center></body></html>");
      out.close();
   }

   @Override
   public void doPost (HttpServletRequest request, HttpServletResponse response)
                   throws ServletException, IOException {
      doGet(request, response);  // Re-direct POST request to doGet()
   }
}