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
public class Page2 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/Page2.html";

    @Override
    public void handle(Context context) throws Exception {
        JDBCConnection jdbc = new JDBCConnection();
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
        html += "<a href=\"Page1.html\" class=\"nav\">Overview</a></li>";
        html += "<a class=\"active\" href=\"Page2.html\" class=\"nav\">Other Factors</a></li>";
        html += "<a href=\"Page3.html\" class=\"nav\">Over Time</a></li>";
        html += "</div></div>";

        html += "<div class=\"imgBox\">";
        html += "<img class=\"page2BG\" src=/give.jpg>";
        html += "<div class=\"page2\">";

        html += "<body>";
        //html += "   <div class='container'>";
        //html += "       <div class = 'page' id ='item-a'>";
        html += "           <form action = /Page2.html method = 'post' class=form>";
        html +=                 "<h1>Factors of Homelessness</h1>";
        html +=                 "<h3>Browse how homelessness in Australia measures against other factors</h3>";


        
        //html += "      </div><div class = 'page inputField' id = 'item-c'>";
        html += "      <label for='sortBy'>All factors are provided. Select which factor you would like to sort by: </label>";
        html += "      <select id='sortBy' name='sortBy'>";
        html += "          <option selected>Homeless people per 100,000 population</option> <option>% Males</option> <option>Median age</option> <option>Median income</option> <option>Median mortgage repayments</option> <option>Median rent</option>";
        html += "      </select><br><br>";

        html += "          <b>Include the following:</b><br><br>";
        html += "             <input type = 'checkbox' name = 'homeless' id = 'homeless' checked>";
        html += "             <label for 'homeless'>People who are <b>homeless</b></label><br>";
        html += "             <input type = 'checkbox' name = 'atRisk' id = 'atRisk'>";
        html += "             <label for 'atRisk'>People who are <b>at risk of being homeless</b><label><br>";
        

   

        /*html += "           <div class = 'inputField' id = 'states'><p>Select the states you are interested in</p>";
        </div>
        
        </div><div class = 'page' id = 'item-b'>";*/

        html +=             "<br><table class='formTable'><tr><th>Include the following genders:&nbsp;&nbsp;</th>";
        html +=             "<th>Include the following age groups:&nbsp;&nbsp;</th><th>Include the following states:&nbsp;&nbsp;</th></tr><tr>";

        html += "           <td><input type = 'checkbox' name = 'male' id = 'male' checked>";
        html += "             <label for = 'male'>Male</label></br>";
        html += "             <input type = 'checkbox' name = 'female' id = 'female' checked>";
        html += "             <label for = 'female'>Female</label></td>";      

        html += "             <td><input type = 'checkbox' name = '09' id = '09' checked>";
        html += "             <label for = '10'>Under 10</label></br>";
        html += "             <input type = 'checkbox' name = '19' id = '19' checked>";
        html += "             <label for = '20'>10 to 19</label></br>";
        html += "             <input type = 'checkbox' name = '29' id = '29' checked>";
        html += "             <label for = '30'>20 to 29</label></br>";
        html += "             <input type = 'checkbox' name = '39' id = '39' checked>";
        html += "             <label for = '40'>30 to 39</label></br>";
        html += "             <input type = 'checkbox' name = '49' id = '49' checked>";
        html += "             <label for = '50'>40 to 49</label></br>";
        html += "             <input type = 'checkbox' name = '59' id = '59' checked>";
        html += "             <label for = '60'>50 to 59</label></br>";
        html += "             <input type = 'checkbox' name = '60' id = '60' checked>";
        html += "             <label for = 'plus'>60 and older</label></br>";
        html += "             <input type = 'checkbox' name = 'U' id = 'U'>";
        html += "             <label for = 'plus'>Age unknown</label></td>";

        html += "           <td><input type = 'checkbox' id = 'Vic' name = 'Vic' checked>";
        html += "           <label for = 'Vic'>VIC</label></br>";
        html += "           <input type = 'checkbox' id = 'SA' name = 'SA' checked>";
        html += "           <label for = 'SA'>SA</label></br>";
        html += "           <input type = 'checkbox' id = 'NT' name = 'NT' checked>";
        html += "           <label for = 'NT'>NT</label></br>";
        html += "           <input type = 'checkbox' id = 'WA' name = 'WA' checked>";
        html += "           <label for = 'WA'>WA</label></br>";
        html += "           <input type = 'checkbox' id = 'NSW' name = 'NSW' checked>";
        html += "           <label for = 'NSW'>NSW</label></br>";
        html += "           <input type = 'checkbox' id = 'QLD' name = 'QLD' checked>";
        html += "           <label for = 'QLD'>QLD</label></br>";
        html += "           <input type = 'checkbox' id = 'Tas' name = 'Tas' checked>";
        html += "           <label for = 'Tas'>TAS</label></br>";
        html += "           <input type = 'checkbox' id = 'ACT' name = 'ACT' checked>";
        html += "           <label for = 'ACT'>ACT</label></br>";
        html += "           <input type = 'checkbox' id = 'Other' name = 'Other' checked>";
        html += "           <label for = 'Other'>Other</label></td></tr></table><br><br>";


        //html += "           <p><strong>Or</strong>, change values of attributes to filter LGA's (Defaults are min and max)</p>";
        html += "           <b>Looking for more specific information? Specify the range of <u>median</u> data.</b><br>";
        html += "           <i>(optional, max range set as default).</i><br><br>";

        html += "           <b>Median age:</b> <i>(between 21 and 60 years old)</i><br><input type = 'number' id = 'minAge' name = 'minAge' min = '21' max = '60' value = '21'>";
        html += "            to <input type = 'number' id = 'maxAge' name = 'maxAge' min = '21' max = '60' value = '60'><br><br>";

        html += "           <b>Median income:</b> <i>(between $700 and $3481 AUD)</i><br><Input type = 'number' id = 'minIncome' name = 'minIncome' min = '700' max = '3481' value = '700'>";
        html += "           to <Input type = 'number' id = 'maxIncome' name = 'maxIncome' min = '700' max = '3481' value = '3250'><br><br>";

        html += "           <b>Median mortgage repayments:</b> <i>(between $0 and $3250 AUD)</i><br><Input type = 'number' id = 'minMortgage' name = 'minMortgage' min = '0' max = '3250' value = '0'>";
        html += "            to <Input type = 'number' id = 'maxMortgage' name = 'maxMortgage' min = '0' max = '3250' value = '3250'><br><br>";

        html += "           <b>Median weekly rental price:</b> <i>(between $0 and $650 AUD)</i><br><Input type = 'number' id = 'minRent' name = 'minRent' min = '0' max = '650' value = '0'>";
        html += "            to <Input type = 'number' id = 'maxRent' name = 'maxRent' min = '0' max = '650' value = '650'><br><br>";





        html +=             "<b>Sort selected data in: &nbsp;&nbsp;&nbsp;&nbsp;</b>";
        html += "      <input type = 'radio' name = 'sorted' id = 'desc' value = 'd' checked>";
        html += "      <label for = 'desc'>Descending order</label>&nbsp;&nbsp;&nbsp;&nbsp;";
        html += "      <input type = 'radio' name = 'sorted' id = 'asc' value = 'a'>";
        html += "      <label for = 'asc'>Ascending order</label>&nbsp;&nbsp;&nbsp;&nbsp;";
        html +=                 "<img src=\"question.png\" class='help' width=\"18\" height=\"18\">";
        html +=                 "<i class='hide'>Sort by descending to see highest first. Sort by ascending to see lowest first.</i>";

        html += "          <p><i> <label for = 'lgaName'>Or looking for a specific LGA? Enter manually (optional): </i></label>";
        html += "          <input name = 'userLGA' id = 'userLGA' placeholder = 'Geelong, Melbourne...'></p>";

        html += "        <input class = 'button' type = \"submit\" value = \"Show me the data!\" >";
        html += "</div>";
        html += "</div>";
        html += "        </form>";

        html += "      </body>";


        String lgaName = context.formParam("userLGA");
        if (lgaName == null) {
            lgaName = "";
        }
        ArrayList<String> columnInfo = new ArrayList<String>();
        String sortBy = context.formParam("sortBy");
        String ascdesc = context.formParam("sorted");
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

        ArrayList<String> lgaFilters = new ArrayList<String>();
        lgaFilters.add(context.formParam("minAge"));
        lgaFilters.add(context.formParam("maxAge"));
        lgaFilters.add(context.formParam("minIncome"));
        lgaFilters.add(context.formParam("maxIncome"));
        lgaFilters.add(context.formParam("minMortgage"));
        lgaFilters.add(context.formParam("maxMortgage"));
        lgaFilters.add(context.formParam("minRent"));
        lgaFilters.add(context.formParam("maxRent"));
        lgaFilters.add(context.formParam("NSW"));
        lgaFilters.add(context.formParam("Vic"));
        lgaFilters.add(context.formParam("QLD"));
        lgaFilters.add(context.formParam("SA"));
        lgaFilters.add(context.formParam("WA"));
        lgaFilters.add(context.formParam("Tas"));
        lgaFilters.add(context.formParam("NT"));
        lgaFilters.add(context.formParam("ACT"));
        lgaFilters.add(context.formParam("Other"));

        String columns[] = jdbc.columnMaker(columnInfo);

        String query[] = jdbc.queryMaker1(lgaName, "2018", columns, lgaFilters, ascdesc, sortBy, columns[2]);
        String table = jdbc.tableMakerAttributes(query);
        System.out.println(table);
        // html += query[0];
        //if (columnInfo.get(0) != null) {
            html += table;
        //}
        html += "</body> </html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}