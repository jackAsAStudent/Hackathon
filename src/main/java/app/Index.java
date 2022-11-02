package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin by writing the raw HTML into a Java
 * String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Index implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        final String questionThree = "How many people per 10,000 were homeless in the NT?";
        final String questionTwo = "How many homeless women aged 55+ were there?";
        final String questionOne = "How many homeless people were there in total?";
        final String answerOne = "116,000";
        final String answerTwo = "13,500";
        final String answerThree = "600";
        final String answerPlaceholder = "<p>Answer the questions to see the results!</p>";

        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + "<title>Homepage</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        // Add the body
        /*
         * Navigation Bar html += "<ul>" + "<li><a href=Index.html>Home</a></li>" +
         * "</ul>";
         */

        // NAVIGATION BAR - make sure to update all.
        html += "<div class=\"imgBox\">";
        html += "<img class=\"navBarBG\" src=/cloud.jpg>";

        html += "<div class=\"navbar\">";
        html += "<a class=\"active\" href=\"/\" class=\"nav\">Home</a></li>";
        html += "<a href=\"Page1.html\" class=\"nav\">Overview</a></li>";
        html += "<a href=\"Page2.html\" class=\"nav\">Other Factors</a></li>";
        html += "<a href=\"Page3.html\" class=\"nav\">Over Time</a></li>";
        html += "</div></div>";



        

        // Box 1: Knowledge Test
        html += "<div class=\"imgBox\">";
        html += "<img class=\"knowledgeTestBG\" src=/pexels-pixabay-220365.jpg>";

        html += "<div class=\"knowledgeTestContent\">";
        html += "   <h1>Homelessness</h1>";
        html += "   <h3 id = 'firstQ'>If you would like to test your knowledge before you 'dive in', do so here:</h3>";
        html += "   <h4>(2016 data)</h4>";

        html += "       <form action = / method = 'post'>";
        html += "           <div class = \"form-group\">";
        html += "               <p><label for = 'firstQ'>" + questionOne + "</label>";
        html += "                   <input id = 'firstQ' name = 'firstQ'>";

        String firstAnswer = context.formParam("firstQ");
        if (firstAnswer == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html += "";
        } else {
            html += "<p class='answer'> You answered: " + firstAnswer + "</p>" + "<p class='answer'>Answer is: "
                    + answerOne + "</p>";
        }

        html += "               <p><label for = 'secondQ'>" + questionTwo + "</label>";
        html += "                   <input id = 'secondQ' name = 'secondQ'>";

        String secondAnswer = context.formParam("secondQ");
        if (secondAnswer == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html += "";
        } else {
            html += "<p class='answer'> You answered: " + secondAnswer + "</p>" + "<p class='answer'>Answer is: "
                    + answerTwo + "</p>";
        }

        html += "               <p><label for = 'thirdQ'>" + questionThree + "</label>";
        html += "                   <input id = 'thirdQ' name = 'thirdQ'>";

        String thirdAnswer = context.formParam("thirdQ");
        if (thirdAnswer == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html += "";
        } else {
            html += "<p class='answer'> You answered: " + thirdAnswer + "</p>" + "<p class='answer'>Answer is: "
                    + answerThree + "</p>";
        }

        html += "           </div>";

        html += "   <button type='submit' class='btn btn-primary'>Submit</button>";
        html += "</form>";

        html += "</div>";
        html += "</div>";

        // Box 2: LGA

        html += "<div class=\"imgBox\">";
        html += "<img class=\"seeYourLGABG\" src=/help.jpg>";
        html += "<div class=\"seeYourLGAContent\">";
        html += "<h1>Overview of Homelessness </h1>";
        html += "<p>There are plenty of people who do not have a place to call home, or at risk of losing their homes.<br>";
        html += "Here, you will be able to see how many people in your <b>Local Government Area</b> or <b>State</b> are impacted.</p>";
        html += "<p> You will be able to filter by:";
        html += "<ul> <li>Gender</li>";
        html += "<li>Age</li>";
        html += "<li>Local Government Area or State</li></ul></p>";
        html += "<a href = Page1.html> <img src=\"go.png\" width=\"30\" height=\"30\" style=\"float:right;\"></a>";
        html += "<a href = Page1.html> <img src=\"go.png\" width=\"30\" height=\"30\" style=\"float:right;\"></a>";
        html += "<a href = Page1.html> <img src=\"go.png\" width=\"30\" height=\"30\" style=\"float:right;\"></a>"; 
        

        html += "</div></div>";

        

        // Box 3: Indepth Data
        html += "<div class=\"imgBox\">";
        html += "<img class=\"deeperDiveBG\" src=/seeking-kindness.jpg>";
        html += "<div class=\"deeperDiveContent\">";
        html += "<h1>A Further Look</h1>";

        html += "<h2>Homelessness and Other Factors</h2>";
        html += "<p>Homelessness is an issue impacted by other factors.<br>";
        html += "Here, you will be able compare the rate of homelessness against these other factors.</p>";
        html += "<p>These factors include median LGA household:";
        html += "<ul><li>Cost of Weekly Rent</li>";
        html += "<li>Mortgage Repayments</li>";
        html += "<li>Weekly Income</li>";
        html += "<li>Age</li></ul></p>";
        html += "<a href = Page2.html> <img src=\"go.png\" width=\"30\" height=\"30\" style=\"float:right;\"></a>";
        html += "<a href = Page2.html> <img src=\"go.png\" width=\"30\" height=\"30\" style=\"float:right;\"></a>";
        html += "<a href = Page2.html> <img src=\"go.png\" width=\"30\" height=\"30\" style=\"float:right;\"></a></a><br><br>"; ;

        html += "<h2>Homelessness Over Time</h2>";
        html += "<p>We also provide data on the change of homelessness and those at risk have changed from 2016 to 2018.<br>";
        html += "Here, you will be able to see:</p>";
        html += "<p><ul><li>Total population change against percentage change in homelessness</li>";
        html += "<li>Total change in the Homeless Population</li>";
        html += "<li>Total change in the At Risk of Homeless Population</li>";
        html += "<li>Ratio of <b>Change of Homeless</b> to <b>Change of At Risk</b></li></ul></p>";
        html += "<a href = Page3.html> <img src=\"go.png\" width=\"30\" height=\"30\" style=\"float:right;\"></a>";
        html += "<a href = Page3.html> <img src=\"go.png\" width=\"30\" height=\"30\" style=\"float:right;\"></a>";
        html += "<a href = Page3.html> <img src=\"go.png\" width=\"30\" height=\"30\" style=\"float:right;\"></a></a><br><br>"; ;
        

        html += "</div></div>";

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
