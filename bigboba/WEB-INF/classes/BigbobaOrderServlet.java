// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;             // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;             // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/bigbobaorder")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class BigbobaOrderServlet extends HttpServlet {

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
      out.println("<head><title>Confirm Your Order</title></head>");
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
      out.println("h2{font-family:Trebuchet MS; text-align: right;font-weight: bold; font-size: 200%}");
      out.println("</style>");
      out.println("</head>");
      out.println("<body>");
      out.println("<center>");
      out.println("<div class=\"content\">");
      out.println("<img src=\"https://cdn-icons-png.flaticon.com/512/3428/3428877.png\" alt=\"Bubble Tea\" style=\"width:350px;height:350px;\">");
      out.println(" <h1><p style=\"font-family:Trebuchet MS; font-size: 50px; color:#c17e2c\"><b>Confirm Your Order</b></p></h1>");



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
      String[] tea = request.getParameterValues("teaid");  // Returns an array of Strings
      String sqlStr = "SELECT * FROM tea WHERE id IN (";
         for (int i = 0; i < tea.length; ++i) {
            if (i < tea.length - 1) {
               sqlStr += "'" + tea[i] + "', ";  // need a commas
            } else {
               sqlStr += "'" + tea[i] + "'";    // no commas
            }
         }sqlStr += ")";

         //out.println("<p>Your SQL statement is: " + sqlStr + "</p>"); // Echo for debugging
         ResultSet rsettea = stmt.executeQuery(sqlStr);  // Send the query to the server
         String[] teaname=new String [tea.length];
         double[] teaprice=new double [tea.length];
         String[] teaid=new String[tea.length];
         int count=0;
         while(rsettea.next()) {
            teaname[count] = rsettea.getString("name");
            teaprice[count] = rsettea.getDouble("price");
            teaid[count] = rsettea.getString("id");
            count++;
            
         }
         //order table
         out.println("<table>");
         double total=0;
         for(int j=0;j<tea.length;j++) {
            out.println("<tr>");
            out.println("<td><p><b>" + teaname[j] + "</b></p>");
            
            //selecting addons
            String[] ids = request.getParameterValues(teaid[j]+"[]");  // Returns an array of Strings
               if(ids!=null) {
                  String sqlStradd = "SELECT * FROM addons WHERE id IN (";
                  for (int i = 0; i < ids.length; ++i) {
                     if (i < ids.length - 1) {
                       sqlStradd += "'" + ids[i] + "', ";  // need a commas
                     } else {
                        sqlStradd += "'" + ids[i] + "'";    // no commas
                     }
                  }sqlStradd += ")";
               

               //out.println("<p>Your SQL statement is: " + sqlStradd + "</p>"); // Echo for debugging
               ResultSet rsetadd = stmt.executeQuery(sqlStradd);  // Send the query to the server
               while(rsetadd.next()) {
                  out.println("<p>+"+ rsetadd.getString("name")+"</p>");
                  teaprice[j]+=rsetadd.getDouble("price");
               }
               }
               out.println("</td>");
               out.println( "<td> $" + teaprice[j] + "</td>");
            out.println("</tr>");
            total+=teaprice[j];
            }
         out.println("</table>");
         out.println("<br> </br>");
         out.printf("<h2>Total: $  %.2f  &nbsp;&nbsp;</h2>\n",total);

      out.println("<form  method=\"get\" action=\"bigbobadone\">");
      out.println("<input type=\"submit\" class=\"button\" value=\"PAY\" >");
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