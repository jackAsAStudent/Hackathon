package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Temporary HTML as an example page.
 * 
 * Based on the Project Workshop code examples. This page currently: - Provides
 * a link back to the index page - Displays the list of movies from the Movies
 * Database using the JDBCConnection
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Page1 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/Page1.html";

    @Override

    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html += "<head>";
        html += "    <meta charset=\"UTF-8\">";
        html += "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">";
        html += "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">";
        html += "    <title>Homelessness</title>";
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html += "</head>";

        html += "<div class=\"imgBox\">";
        html += "<img class=\"navBarBG\" src=/cloud.jpg>";

        html += "<div class=\"navbar\">";
        html += "<a href=\"/\" class=\"nav\">Home</a></li>";
        html += "<a class=\"active\" href=\"Page1.html\" class=\"nav\">Overview</a></li>";
        html += "<a href=\"Page2.html\" class=\"nav\">Other Factors</a></li>";
        html += "<a href=\"Page3.html\" class=\"nav\">Over Time</a></li>";
        html += "</div></div>";

        html += "<div class=\"imgBox\">";
        html += "<img class=\"page1BG\" src=/coin.jpg>";
        html += "<div class=\"page1\">";

        html += "<body>";
        html += "   <form action = '/Page1.html' method = 'post' class=form>";
        html += "<h1>Total Homelessness</h1>";
        html +=                 "<h3>Browse for total figures of homelessness in Australia</h3>";


        html += "          <p><b>Include the following:</b><br><br>";
        html += "             <input type = 'checkbox' name = 'homeless' id = 'homeless' checked>";
        html += "             <label for 'homeless'>Total people who are <b>homeless</b></label><br>";
        html += "             <input type = 'checkbox' name = 'atRisk' id = 'atRisk'>";
        html += "             <label for 'atRisk'>Total people who are <b>at risk of being homeless</b><label></p>";

        html += "          <p><b>By</b>&nbsp;&nbsp;&nbsp;&nbsp; <input type = 'radio' name = 'stateOrLga' id = 'lga' value = 'lga' checked>";
        html += "          <label for 'lga'>Local Government Area (LGA)</label>&nbsp;&nbsp;&nbsp;&nbsp;";
        html += "          <input type = 'radio' name = 'stateOrLga' id = 'state' value = 'state'>";
        html += "          <label for 'state'>State</lga></p>";

        html +=             "<table class='formTable'><tr><th>Include the following genders:&nbsp;&nbsp;</th>";
        html +=             "<th>Include the following age groups:&nbsp;&nbsp;</th></tr>";

        html += "             <tr><td><input type = 'checkbox' name = 'female' id = 'female' checked>";
        html += "             <label for = 'female'>Female</label><br>";
        html += "             <input type = 'checkbox' name = 'male' id = 'male' checked>";
        html += "             <label for = 'male'>Male</label></p></td>";

        html += "             <td><input type = 'checkbox' name = '09' id = '09' checked>";
        html += "             <label for = '10'>Under 10</label><br>";
        html += "             <input type = 'checkbox' name = '19' id = '19' checked>";
        html += "             <label for = '20'>10 to 19</label><br>";
        html += "             <input type = 'checkbox' name = '29' id = '29' checked>";
        html += "             <label for = '30'>20 to 29</label><br>";
        html += "             <input type = 'checkbox' name = '39' id = '39' checked>";
        html += "             <label for = '40'>30 to 39</label><br>";
        html += "             <input type = 'checkbox' name = '49' id = '49' checked>";
        html += "             <label for = '50'>40 to 49</label><br>";
        html += "             <input type = 'checkbox' name = '59' id = '59' checked>";
        html += "             <label for = '60'>50 to 59</label><br>";
        html += "             <input type = 'checkbox' name = '60' id = '60' checked>";
        html += "             <label for = 'plus'> 60 and older</label><br>";
        html += "             <input type = 'checkbox' name = 'U' id = 'U'>";
        html += "             <label for = 'plus'> Age unknown</label></td></tr></table></p>";

        html +=             "<p><b> Sort selected data in: &nbsp;&nbsp;&nbsp;&nbsp;</b>";
        html += "             <input type = 'radio' name = 'sort' id = 'desc' value = 'd' checked>";
        html += "             <label for = 'desc'>Descending order</label>&nbsp;&nbsp;&nbsp;&nbsp;";
        html += "             <input type = 'radio' name = 'sort' id = 'asc' value = 'a'>";
        html += "             <label for = 'asc'>Ascending order</label>&nbsp;&nbsp;&nbsp;&nbsp;";
        html +=                 "<img src=\"question.png\" class='help' width=\"18\" height=\"18\">";
        html +=                 "<i class='hide'>Sort by descending to see positive change first. Sort by ascending to see negative change first.</i></p>";

        html += "          <p><i> <label for = 'lgaName'>Or looking for a specific LGA? Enter manually (optional):</i></label>";
        html += "          <input type = 'text' name = 'lgaName' id = 'lgaName' placeholder = 'Greater Geelong'></p>";

        html += "          <button type='submit' class='btn btn-primary'>Show me the data!</button><br>";
        html += "   </form>";
        html += "</div>";
        html += "</div>";
        html += "</div>";

        // Assign all of the user inputs variables.
        String lgaOrState = context.formParam("stateOrLga");
        String lgaName = context.formParam("lgaName");
        String ascdesc = context.formParam("sort");
        ArrayList<String> columnInfo = new ArrayList<String>();
        // All of the user input needed for columnMaker
        columnInfo.add(context.formParam("male"));
        columnInfo.add(context.formParam("female"));
        columnInfo.add(context.formParam("homeless"));
        columnInfo.add(context.formParam("atRisk"));
        columnInfo.add(context.formParam("09"));
        columnInfo.add(context.formParam("19"));
        columnInfo.add(context.formParam("29"));
        columnInfo.add(context.formParam("39"));
        columnInfo.add(context.formParam("49"));
        columnInfo.add(context.formParam("59"));
        columnInfo.add(context.formParam("60"));
        columnInfo.add(context.formParam("U"));

        // to see columnMaker working in action
        JDBCConnection jdbc = new JDBCConnection();
        String[] columns = jdbc.columnMaker(columnInfo);

        String table;
        // If the user wants to see LGA's
        
        html += "<div class=page><br>Showing results by";
        
        if (lgaOrState != null) {
            if (lgaOrState.equals("lga")) {
            html += " <b>LGA</b> ";
            }
            else {
                html += " <b>State</b> ";
            }
        }

        if (columnInfo.get(2) != null && columnInfo.get(3) != null) {
            html += "for both <b>Homeless & At Risk of Homeless</b> ";
        }
        
        else if (columnInfo.get(2) == null && columnInfo.get(3) != null) {
            html += "for <b>At Risk of Homeless</b> ";
        }

        else if (columnInfo.get(2) != null && columnInfo.get(3) == null) {
            html += "for <b>Homeless</b> ";
        }

        else {
            html += "for <b>n/a</b> ";
        }

        if (ascdesc != null) {
            if (ascdesc.equals("d")) {
                html += "in <b>Descending Order</b> ";
            }
            
            else {
                html += "in <b>Ascending Order</b> ";
            }
        }

        html += "for ";

        if (columnInfo.get(0) != null && columnInfo.get(0) != null) {
            html += "<b>Males & Females</b> ";
        }
        else if (columnInfo.get(0) != null) {
            html += "<b>Males</b>";
        }

        else if (columnInfo.get(1) != null) {
            html += "<b>Females</b>";
        }
        
        else {
            html += "n/a (please choose a gender)";
        }



        html += " for ";

        if (columnInfo.get(4) != null && columnInfo.get(5) != null && columnInfo.get(6) != null && columnInfo.get(7) != null && columnInfo.get(8) != null && columnInfo.get(9) != null && columnInfo.get(10) != null) {
            html += "<b>All Ages</b> ";
        }
        else {
            if (columnInfo.get(4) != null) {
                html += "<b>Under 10</b>, ";
            }
            if (columnInfo.get(5) != null) {
                html += "<b>10 to 19</b>, ";
            }
            if (columnInfo.get(6) != null) {
                html += "<b>20 to 29</b>, ";
            }
            if (columnInfo.get(7) != null) {
                html += "<b>30 to 39</b>, ";
            }
            if (columnInfo.get(8) != null) {
                html += "<b>40 to 49</b>, ";
            }
            if (columnInfo.get(9) != null) {
                html += "<b>50 to 59</b>, ";
            }
            if (columnInfo.get(10) != null) {
                html += "<b>60 and over</b>, ";
            }
            if (columnInfo.get(11) != null) {
                html += "<b>other</b>,";
            }

            html = html.substring(0, html.length() - 2);
            html += ".</div>";

        }

        if (lgaOrState != null) {
            if (lgaOrState.equals("lga")) {
                // Make the table with columns, lgaName and asc
                table = jdbc.lgaTableMaker(columns, lgaName, ascdesc);
            } else {
                // make the table with columns.
                table = jdbc.stateTableMaker(columns, ascdesc);
            }
            html += "</div>" + table;
        }


        html += "</body></html>";

        context.html(html);

    }
}