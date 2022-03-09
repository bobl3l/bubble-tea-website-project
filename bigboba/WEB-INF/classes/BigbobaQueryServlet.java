// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;             // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;             // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/bigbobaquery")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class BigbobaQueryServlet extends HttpServlet {

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
      out.println("<head><title>Make Your Order</title></head>");
      out.println("<script>");
      out.println("function validateForm() {");
      out.println("var form_data = new FormData(document.querySelector(\"form\"));");
      out.println("if(!form_data.has(\"id\")) {");
      out.println("alert(\"You have not chosen any drink\");");
      out.println("return false; }}");
      out.println("</script>");
      out.println("<style>");
      out.println("table {  border-collapse: collapse;  width: 100%;}");
      out.println("th {  padding: 8px;  text-align: center;  font-size: 20px;  font-family:Trebuchet MS;  border-bottom: 1px solid #ddd;}");
      out.println("td {  padding: 8px;  text-align: center;  font-size: 15px;  font-family:MV Boli;  border-bottom: 1px solid #ddd;}");
      out.println("tr:hover {background-color: #eff4c3;}");
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
      out.println("<img src=\"https://cdn-icons-png.flaticon.com/512/3428/3428877.png\" alt=\"Bubble Tea\" style=\"width:350px;height:350px;\">");
      out.println(" <h1><p style=\"font-family:Trebuchet MS; font-size: 50px; color:#c17e2c\"><b>MENU</b></p></h1>");
      out.println("<form  method='get' action='bigbobaaddons' onsubmit='return validateForm()'required>");


      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/bigboba?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
      // cheezho
      String sqlStr1 = "select * from cheezho;";
      ResultSet rset1 = stmt.executeQuery(sqlStr1);
         out.println("<h2><p style=\"font-family:Trebuchet MS; font-size: 35px; color:#c17e2c\">CheezHO Tea</p></h2>");
         out.println("<table>");
         out.println("<tr>");
         out.println("<th>NAME</th>");
         out.println("<th>PRICE</th>");
         out.println("<th>   </th>");
         out.println("</tr>");
         out.println("<tr>");
         while(rset1.next()) {
         out.println("<tr>");
            out.println(
                  "<td>" + rset1.getString("name") + "</td>"
                  + "<td> $" + rset1.getDouble("price") + "</td>"
                  + "<td><p><input type='checkbox' name='id' value="
                  + "'" + rset1.getString("id") + "' /></p></td>");
            out.println("</tr>");
         }
         out.println("</tr>");
         out.println("</table>");

      // brew
      String sqlStr2 = "select * from brew;";
      ResultSet rset2 = stmt.executeQuery(sqlStr2);
         out.println("<h2><p style=\"font-family:Trebuchet MS; font-size: 35px; color:#c17e2c\">Brew Tea</p></h2>");
         out.println("<table>");
         out.println("<tr>");
         out.println("<th>NAME</th>");
         out.println("<th>PRICE</th>");
         out.println("<th>   </th>");
         out.println("</tr>");
         out.println("<tr>");
         while(rset2.next()) {
         out.println("<tr>");
            out.println(
                  "<td>" + rset2.getString("name") + "</td>"
                  + "<td> $" + rset2.getDouble("price") + "</td>"
                  + "<td><p><input type='checkbox' name='id' value="
                  + "'" + rset2.getString("id") + "' /></p></td>");
            out.println("</tr>");
         }
         out.println("</tr>");
         out.println("</table>");

      // milk
      String sqlStr3 = "select * from milk;";
      ResultSet rset3 = stmt.executeQuery(sqlStr3);
         out.println("<h2><p style=\"font-family:Trebuchet MS; font-size: 35px; color:#c17e2c\">Milk Tea</p></h2>");
         out.println("<table>");
         out.println("<tr>");
         out.println("<th>NAME</th>");
         out.println("<th>PRICE</th>");
         out.println("<th>   </th>");
         out.println("</tr>");
         out.println("<tr>");
         while(rset3.next()) {
         out.println("<tr>");
            out.println(
                  "<td>" + rset3.getString("name") + "</td>"
                  + "<td> $" + rset3.getDouble("price") + "</td>"
                  + "<td><p><input type='checkbox' name='id' value="
                  + "'" + rset3.getString("id") + "' /></p></td>");
            out.println("</tr>");
         }
         out.println("</tr>");
         out.println("</table>");

      // fruit
      String sqlStr4 = "select * from fruit;";
      ResultSet rset4 = stmt.executeQuery(sqlStr4);
         out.println("<h2><p style=\"font-family:Trebuchet MS; font-size: 35px; color:#c17e2c\">Fruit Tea</p></h2>");
         out.println("<table>");
         out.println("<tr>");
         out.println("<th>NAME</th>");
         out.println("<th>PRICE</th>");
         out.println("<th>   </th>");
         out.println("</tr>");
         out.println("<tr>");
         while(rset4.next()) {
         out.println("<tr>");
            out.println(
                  "<td>" + rset4.getString("name") + "</td>"
                  + "<td> $" + rset4.getDouble("price") + "</td>"
                  + "<td><p><input type='checkbox' name='id' value="
                  + "'" + rset4.getString("id") + "' /></p></td>");
            out.println("</tr>");
         }
         out.println("</tr>");
         out.println("</table>");
      out.println("<input type=\"submit\" class=\"button\" value=\"NEXT\" >");
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