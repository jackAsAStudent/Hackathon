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
public class Page3 implements Handler {

    public static final String URL = "/Page3.html";

    @Override
    public void handle(Context context) throws Exception {
        JDBCConnection jdbc = new JDBCConnection();

        String html = "";

        html += "<html>";

        html +=     "<head>";
        html +=         "<meta charset=\"UTF-8\">";
        html +=         "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">";
        html +=         "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">";
        html +=         "<title>Homelessness</title>";
        html +=         "<link rel='stylesheet' type='text/css' href='common.css' />";
        html +=     "</head>";

        html += "<div class=\"imgBox\">";
        html += "<img class=\"navBarBG\" src=/cloud.jpg>";

        html += "<div class=\"navbar\">";
        html += "<a href=\"/\" class=\"nav\">Home</a></li>";
        html += "<a href=\"Page1.html\" class=\"nav\">Overview</a></li>";
        html += "<a href=\"Page2.html\" class=\"nav\">Other Factors</a></li>";
        html += "<a class=\"active\" href=\"Page3.html\" class=\"nav\">Over Time</a></li>";
        html += "</div></div>";

        html += "<div class=\"imgBox\">";
        html += "<img class=\"page3BG\" src=/kid.jpg>";
        html += "<div class=\"page3\">";

        html += "<body>";
        html +=         "<form action = /Page3.html method = 'post' class=form>";
        html +=                 "<h1>Growth in Homelessness</h1>";
        html +=                 "<h3>Browse how homelessness in Australia has changed from 2016 to 2018</h3>";
        
        html +=                 "<label for='changeDrop'> What would you like to see change data for?* </label>";
        html +=                 "<select id='changeDrop' name='changeDrop'>";
        html +=                     "<option value=\"none\" selected disabled hidden> Select an Option </option>";
        html +=                     "<option value=\"p\"> Trends by Population (no filters applicable) </option>";
        html +=                     "<option value=\"p2\"> Trends by Homelessness (no filters applicable) </option>";
        html +=                     "<option value=\"h\"> Homeless </option>";
        html +=                     "<option value=\"a\"> At Risk of Homeless </option>";
        html +=                     "<option value=\"r\"> Ratio Homeless:At Risk of Homeless </option>";
        html +=                 "</select>";

        html +=             "<p><b> Show only: </b>";
        html +=                 "<input type='radio' id='INC' name='IDB' value=i>";
        html +=                     "<label for='INC'> Positive Change </label>&nbsp;&nbsp;&nbsp;&nbsp;";
        html +=                 "<input type='radio' id='DEC' name='IDB' value=d>";
        html +=                     "<label for='DEC'> Negative Change </label>&nbsp;&nbsp;&nbsp;&nbsp;";
        html +=                 "<input type='radio' id='BOTH' name='IDB' value=b checked>";
        html +=                     "<label for='BOTH'> All </label>&nbsp;&nbsp;&nbsp;&nbsp<i></i>";
        html +=                 "<img src=\"question.png\" class='help' width=\"18\" height=\"18\">";
        html +=                 "<i class='hide'>Show only positive change to see increase in homelessness. Show only negative change to see decrease in homelessness.</i>";
        html +=             "</p>";

        //html +=             "<br><b> Demographic Filters (excluding population): </b>";

        html +=             "<table class='formTable'><tr><th>Include the following genders:&nbsp;&nbsp;</th>";
        html +=             "<th>Include the following age groups:&nbsp;&nbsp;</th><th>Include the following states:&nbsp;&nbsp;</th></tr><tr>";

        html +=                 "<td><input type='checkbox' id='MALE' name='MALE' checked>";
        html +=                     "<label for='MALE'> Male </label><br>";
        html +=                 "<input type='checkbox' id='FEMALE' name='FEMALE' checked>";
        html +=                     "<label for='FEMALE'> Female </label><br></td>";

        html +=                 "<td><input type='checkbox' id='09' name='09' checked>";
        html +=                     "<label for='09'> Under 10 </label><br>";
        html +=                 "<input type='checkbox' id='19' name='19' checked>";
        html +=                     "<label for='19'> 10 to 19 </label><br>";
        html +=                 "<input type='checkbox' id='29' name='29' checked>";
        html +=                     "<label for='29'> 20 to 29 </label><br>";
        html +=                 "<input type='checkbox' id='39' name='39' checked>";
        html +=                     "<label for='39'> 30 to 39 </label><br>";
        html +=                 "<input type='checkbox' id='49' name='49' checked>";
        html +=                     "<label for='49'> 40 to 49 </label><br>";
        html +=                 "<input type='checkbox' id='59' name='59' checked>";
        html +=                     "<label for='59'> 50 to 59 </label><br>";
        html +=                 "<input type='checkbox' id='60' name='60' checked>";
        html +=                     "<label for='60'> 60 and older </label><br>";
        html +=                 "<input type='checkbox' id='U' name='U'>";
        html +=                     "<label for='U'> Age unknown</label><br></td>";

        html +=                 "<td><input type='checkbox' id='VIC' name='VIC' checked>";
        html +=                     "<label for='VIC'> VIC </label><br>";
        html +=                 "<input type='checkbox' id='SA' name='SA' checked>";
        html +=                     "<label for='SA'> SA </label><br>";
        html +=                 "<input type='checkbox' id='NT' name='NT' checked>";
        html +=                     "<label for='NT'> NT </label><br>";
        html +=                 "<input type='checkbox' id='WA' name='WA' checked>";
        html +=                     "<label for='WA'> WA </label><br>";
        html +=                 "<input type='checkbox' id='NSW' name='NSW' checked>";
        html +=                     "<label for='NSW'> NSW </label><br>";
        html +=                 "<input type='checkbox' id='QLD' name='QLD' checked>";
        html +=                     "<label for='QLD'> QLD </label><br>";
        html +=                 "<input type='checkbox' id='TAS' name='TAS' checked>";
        html +=                     "<label for='TAS'> TAS </label><br>";
        html +=                 "<input type='checkbox' id='ACT' name='ACT' checked>";
        html +=                     "<label for='ACT'> ACT </label><br>";
        html +=                 "<input type='checkbox' id='OTHER' name='OTHER' checked>";
        html +=                     "<label for='OTHER'> Other </label><br></td></table>";

        

        

        html +=             "<p> <b>Sort selected data in: &nbsp;&nbsp;&nbsp;&nbsp;</b>";
        html +=                 "<input type='radio' id='DESC' name='Order' value='desc' checked>";
        html +=                     "<label for='DESC'> Descending order </label>&nbsp;&nbsp;&nbsp;&nbsp;";
        html +=                 "<input type='radio' id='ASC' name='Order' value='asc'>";
        html +=                     "<label for='ASC'> Ascending order </label>&nbsp;&nbsp;&nbsp;&nbsp;";
        html +=                 "<input type='radio' id='ALP' name='Order' value='alp'>";
        html +=                     "<label for='ALP'> Alphabetical order </label>&nbsp;&nbsp;&nbsp;&nbsp;";
        html +=                 "<img src=\"question.png\" class='help' width=\"18\" height=\"18\">";
        html +=                 "<i class='hide'>Sort by descending to see positive change first. Sort by ascending to see negative change first.</i>";

        html +=             "</p>";

        html +=             "<button type='submit' class='btn btn-primary'>Show me the data!</button>";
        html +=         "</form>";
        html += "</div>";
        html += "</div><br>";

        ArrayList<String> select = new ArrayList<String>();
        select.add(context.formParam("MALE"));
        select.add(context.formParam("FEMALE"));

        String changeDrop = context.formParam("changeDrop");
        if (changeDrop == null) {

        } else if (changeDrop.equals("p") || changeDrop.equals("p2")) {
            select.add("on");
            select.add(null);
        } else if (changeDrop.equals("h")) {
            select.add("on");
            select.add(null);
        } else if (changeDrop.equals("a")) {
            select.add(null);
            select.add("on");
        } else if (changeDrop.equals("r")) {
            select.add("on");
            select.add(null);
        } else if (changeDrop.equals("t")) {
            select.add("on");
            select.add(null);
        }

        select.add(context.formParam("09"));
        select.add(context.formParam("19"));
        select.add(context.formParam("29"));
        select.add(context.formParam("39"));
        select.add(context.formParam("49"));
        select.add(context.formParam("59"));
        select.add(context.formParam("60"));
        select.add(context.formParam("U")); // ????

        ArrayList<String> state = new ArrayList<String>();
        state.add(context.formParam("VIC"));
        state.add(context.formParam("SA"));
        state.add(context.formParam("NT"));
        state.add(context.formParam("WA"));
        state.add(context.formParam("NSW"));
        state.add(context.formParam("QLD"));
        state.add(context.formParam("TAS"));
        state.add(context.formParam("ACT"));
        state.add(context.formParam("OTHER"));

        String idb = context.formParam("IDB");
        String order = context.formParam("Order");

        if (changeDrop == null) {
            System.out.println();

        } else if (changeDrop.equals("p") || changeDrop.equals("p2")) {
            String arithmetic = "percentage";
            String query = jdbc.columnMakerChange(select, arithmetic);
            String popTable = jdbc.totalPop(state, query, idb, order, changeDrop);
            String results = jdbc.showingResults(select, state, changeDrop, idb, order);
            html += "<div class=page>" + results; 
            html += "<div align=right><img src=\"question.png\" class='help' width=\"18\" height=\"18\">";
            html += "<i class='hide'>The last column refers to the proportion of those who became homeless/at risk for every 1% increase in population.<br>";
            html += "This table conveys the association between population growth and homelessness growth. <br>";
            html += "Author's Suggestion: LGA's with a lower population growth may be associated with higher levels of those at risk and homeless. <br>";
            html += "Further investigation is required. </i></div>";
            html += "</div>" + popTable;

        } else if (changeDrop.equals("h")) {
            String arithmetic = "difference";
            String query = jdbc.columnMakerChange(select, arithmetic);
            String selectComplete = jdbc.changeSelectComplete(query, state, idb, order);
            String totalName = "Total Change";
            String htmlTable = jdbc.changeTableMaker(selectComplete, totalName);
            
            String results = jdbc.showingResults(select, state, changeDrop, idb, order);
            html += "<div class=page>" + results;
            html += "<div align=right><img src=\"question.png\" class='help' width=\"18\" height=\"18\">";
            html += "<i class='hide'>Total change refers to the absolute increase of people who has become homeless.<br>";
            html += "Author's Suggestion: A high total points to LGAs with the most in need of support.</i></div>";
            html += "</div>" + htmlTable;

        } else if (changeDrop.equals("a")) {
            String arithmetic = "difference";
            String query = jdbc.columnMakerChange(select, arithmetic);
            String selectComplete = jdbc.changeSelectComplete(query, state, idb, order);
            String totalName = "Total Change";
            String htmlTable = jdbc.changeTableMaker(selectComplete, totalName);
            String results = jdbc.showingResults(select, state, changeDrop, idb, order);
            html += "<div class=page>" + results;
            html += "<div align=right><img src=\"question.png\" class='help' width=\"18\" height=\"18\">";
            html += "<i class='hide'>Total change refers to the absolute increase of people in becoming at risk of homelessness.<br>";
            html += "Author's Suggestion: A high total may signal an higher proportion of issues affecting the community.</i></div>";
            html += "</div>" + htmlTable;

        } else if (changeDrop.equals("r")) {
            String queryHomeless = jdbc.ratioMaker(select);
            select.set(2, null);
            select.set(3, "on");
            String queryAtRisk = jdbc.ratioMaker(select);
            String query = "CAST((((((" + queryHomeless + "))*1.0)/(((" + queryAtRisk + "))*1.0))-1)*100 AS INT)";
            String ratioQuery = jdbc.changeSelectComplete(query, state, idb, order);
            String totalName = "Homeless:At Risk";
            String htmlTable = jdbc.changeTableMaker(ratioQuery, totalName);
            String results = jdbc.showingResults(select, state, changeDrop, idb, order);
            html += "<div class=page>" + results;
            html += "<div align=right><img src=\"question.png\" class='help' width=\"18\" height=\"18\">";
            html += "<i class='hide'>A higher ratio refers a higher proportion of people who became homeless for every 1% of people who became at risk of becoming homeless.<br>";
            html += "Author's Suggestion: A high ratio may indicate that the LGA has comparatively less social support and needs more attention.</i></div>";
            html += "</div>" + htmlTable;
            System.out.println(ratioQuery);


            //Support

            

        } /* else if (changeDrop.equals("t")) {

            //jdbc.columnMakerChange(select, state);


            
            String queryHomeless = jdbc.ratioMaker(select);
            select.set(2, null);
            select.set(3, "on");
            String queryAtRisk = jdbc.ratioMaker(select);
            String query = "CAST((((((" + queryHomeless + "))*1.0)/(((" + queryAtRisk + "))*1.0))-1)*100 AS INT)";
            String ratioQuery = jdbc.changeSelectComplete(query, state, idb, order);
            String totalName = "Homeless:At Risk";
            String htmlTable = jdbc.changeTableMaker(ratioQuery, totalName);
            String results = jdbc.showingResults(select, state, changeDrop, idb, order);
            html += "<div class=page>" + results + "</div>" + htmlTable;
        }*/

        html +=     "</body>";
        html += "</html>";
        context.html(html);
    }
}
