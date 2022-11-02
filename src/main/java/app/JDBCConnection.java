package app;

import java.util.*;

import javax.lang.model.util.ElementScanner14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database. Allows SQL
 * queries to be used with the SQLLite Databse in Java.
 * 
 * This is an example JDBC Connection that has a single query for the Movies
 * Database This is similar to the project workshop JDBC examples.
 *
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    private static final String DATABASE = "jdbc:sqlite:database/projectBase.db";

    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /*
     * Input is what gender, datatype (homeless / atRisk) and age groups the user
     * wants to be calculated in the total. Input in form of a ArrayList of length k
     * 12. In order the indexes of the arraylist correspond to: M, F, Homeless,
     * AtRisk, age groups in ascending order (Uknown are at the end). Returns a
     * three length String array where the indexes are the following: 0: All of the
     * columns that the user has selected, with a + between each one 1: The male
     * columns of these columns ^, in the same format 2: A string that will be
     * placed above the table showing the user what they have entered.
     */
    public String[] columnMaker(ArrayList<String> inputs) {
        String[] results = new String[3];
        // The string results[2] will be shown at the top of the table after the user
        // submits
 
        results[2] = "<div class=page>Showing results for ";
        ArrayList<String> columns = new ArrayList<String>();
        // populate columns by iterating through query that returns column names.
        Connection connection = null;
        String columnsQuery = "PRAGMA table_info(HomelessAtRisk)"; // This is the query.
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet columnNames = statement.executeQuery(columnsQuery);

            while (columnNames.next()) {
                String name = columnNames.getString("name");
                if (name.equals("Code") || name.equals("Year")) {
                    continue;
                } else {
                    columns.add(name);
                }
            }
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        if (inputs.get(0) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("M")) {
                    i.remove();
                }
            }
        } else {
            results[2] += "<b>Male</b>";
        }
        if (inputs.get(1) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("F")) {
                    i.remove();
                }
            }
        } else {
            // Only females
            if (inputs.get(0) == null) {
                results[2] += "<b>Female</b> ";
                // Both genders
            } else {
                results[2] += ", <b>Female</b>";
            }
        }
        if (inputs.get(2) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("H")) {
                    i.remove();
                }
            }
        } else {
            results[2] += ", <b>Homeless People</b>";
        }
        if (inputs.get(3) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("A")) {
                    i.remove();
                }
            }
        } else {
            results[2] += ", <b>At Risk of Being Homeless</b>";
        }
        results[2] += " between the ages of: ";
        if (inputs.get(4) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("09")) {
                    i.remove();
                }
            }
        } else {
            results[2] += "<b>0 and 9</b>";
        }
        if (inputs.get(5) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("19")) {
                    i.remove();
                }
            }
        } else {
            if (results[2].charAt(results[2].length() - 1) != ',') {
                results[2] += ", ";
            }
            results[2] += "<b>10 and 19</b>";
        }
        if (inputs.get(6) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("29")) {
                    i.remove();
                }
            }
        } else {
            if (results[2].charAt(results[2].length() - 1) != ',') {
                results[2] += ", ";
            }
            results[2] += "<b>20 and 29</b>";
        }
        if (inputs.get(7) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("39")) {
                    i.remove();
                }
            }
        } else {
            if (results[2].charAt(results[2].length() - 1) != ',') {
                results[2] += ", ";
            }
            results[2] += "<b>30 and 39</b>";
        }
        if (inputs.get(8) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("49")) {
                    i.remove();
                }
            }
        } else {
            if (results[2].charAt(results[2].length() - 1) != ',') {
                results[2] += ", ";
            }
            results[2] += "<b>40 and 49</b>";
        }
        if (inputs.get(9) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("59")) {
                    i.remove();
                }
            }
        } else {
            if (results[2].charAt(results[2].length() - 1) != ',') {
                results[2] += ", ";
            }
            results[2] += "<b>50 and 59</b>";
        }
        if (inputs.get(10) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("60")) {
                    i.remove();
                }
            }
        } else {
            if (results[2].charAt(results[2].length() - 2) != ',') {
                results[2] += ", ";
            }
            results[2] += "<b>60+ </b>";
        }
        if (inputs.get(11) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("U")) {
                    i.remove();
                }
            }
        } else {
            results[2] += "<b>Unknown ages </b>";
        }
        // build column total from columns
        for (String column : columns) {
            results[0] += column + "+";
        }
        // make the male columns
        for (String column : columns) {
            if (column.contains("M")) {
                results[1] += column + "+";
            }
        }
        // remove last +
        for (int i = 0; i < 2; ++i) {
            if (results[i] != null) {
                if (results[i].length() > 0) {
                    results[i] = results[i].substring(4, results[i].length() - 1);
                }
            }
        }

        return results;
    }

    /*
     * Input is a string of columns made by columnMaker, an LGA name (if the user
     * did not input anything will be null) and whether the user selected
     * 'ascending'. If they did not select ascending, this will be null. Returns the
     * html of a table in a String.
     */

    public String lgaTableMaker(String[] columns, String lgaName, String ascdesc) {
        int ranking = 1;
        if (ascdesc == null) {
            return "";
        } else {
            // Make the query string with columnInfo, lgaName and asc
            String query = "SELECT CASE WHEN ha.code LIKE '1%' THEN 'NSW' WHEN ha.code LIKE '2%' THEN 'VIC' WHEN ha.code LIKE '3%' THEN 'QLD'";
            query += "WHEN ha.code LIKE '4%' THEN 'SA' WHEN ha.code LIKE '5%' THEN 'WA' WHEN ha.code LIKE '6%' THEN 'TAS' WHEN ha.code LIKE '7%' THEN 'NT'";
            query += "WHEN ha.code LIKE '8%' THEN 'ACT' WHEN ha.code LIKE '9%' THEN 'OTHER' END state, name, " + columns[0] + " as Total FROM HomelessAtRisk ha JOIN LGA l on l.code = ha.code WHERE year = '2018'";
            // If they have provided a name
            if (!lgaName.equals("")) {
                query += " AND l.name LIKE '%" + lgaName + "%'";
            }
            query += " ORDER BY Total ";
            if (ascdesc.equals("d")) {
                query += "DESC";
            } else {
                query += "ASC";
            }
            String table = "<div class = 'tableFixHead'><table class = 'results' id = 'lgaTable'><thead> <tr> <th>Ranking</th> <th>State</th> <th>LGA name</th> <th>Total</th> </tr> </thead>";
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(DATABASE);
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                if (ascdesc.equals("d")) {
                    String rankingQuery = "SELECT count(*) as count FROM HomelessAtRisk ha JOIN LGA l on l.code = ha.code WHERE year = '2018'";
                    if (!lgaName.equals("")) {
                        rankingQuery += "AND l.name LIKE '%" + lgaName + "%'";
                    }
                    ResultSet rankingResult = statement.executeQuery(rankingQuery);
                    String rank = rankingResult.getString("count");
                    ranking = Integer.valueOf(rank);
                }
                ResultSet results = statement.executeQuery(query);

                while (results.next()) {
                    String result = results.getString("Total");
                    if (result == null) {
                        result = "0";
                    }
                    table += "<tr> <td>" + ranking + "</td><td>" + results.getString("state") +  "</td><td>" + results.getString("name") + "</td><td>" + result
                            + "</td></tr>";
                    if (ascdesc.equals("d")) {
                        --ranking;
                    } else {
                        ++ranking;
                    }
                }
                table += "</table></div>";

            } catch (SQLException e) {
                // If there is an error, lets just pring the error
                System.err.println(e.getMessage());
            } finally {
                // Safety code to cleanup
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e.getMessage());
                }
            }
            return table;
        }
    }

    public String stateTableMaker(String[] columns, String ascdesc) {
        if (ascdesc == null) {
            return "";
        } else {
            int ranking = 1;
            String stateCode = null;
            String name = "";
            String total = "";
            String html = "<br><br><div class = 'tableFixHead'><table class = 'results'><thead><tr> <th>Ranking</th> <th><State Name></th> <th>Total</th> </tr></thead>";

            Connection connection = null;
            // This is the query.
            String query = stateQuery(columns, ascdesc);
            try {
                connection = DriverManager.getConnection(DATABASE);
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                ResultSet results = statement.executeQuery(query);
                if (ascdesc.equals("d")) {
                    ranking = 9;
                }
                while (results.next()) {
                    stateCode = results.getString("code");
                    name = stateName(stateCode);
                    total = results.getString("total");
                    html += "<tr> <td>" + ranking + "</td><td>" + name + "</td><td>" + total + "</td> </tr>";
                    if (ascdesc.equals("d")) {
                        --ranking;
                    } else {
                        ++ranking;
                    }
                }
                html += "</table>";
                return html;

            } catch (SQLException e) {
                // If there is an error, lets just pring the error
                System.err.println(e.getMessage());
            } finally {
                // Safety code to cleanup
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e.getMessage());
                }
            }
            return html;
        }
    }

    public String stateQuery(String[] columns, String ascdesc) {
        String query = "";
        Connection connection = null;
        // This is the query.
        query += "SELECT " + "1 as code" + ", SUM(" + columns[0] + ") AS total FROM HomelessAtRisk WHERE code LIKE '"
                + "1" + "%'";
        for (int i = 2; i < 10; ++i) {
            query += " UNION ";
            query += "SELECT " + i + " as code, SUM(" + columns[0] + ") AS total FROM HomelessAtRisk WHERE code LIKE '"
                    + i + "%'";
        }
        query += " ORDER BY total";
        if (ascdesc.equals("d")) {
            query += " DESC";
        }
        query.replace('\n', ' ');
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);

        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return query;
    }

    public String stateName(String stateID) {
        String name;
        switch (stateID) {
            case "1":
                name = "New South Wales";
                break;
            case "2":
                name = "Victoria";
                break;
            case "3":
                name = "Queensland";
                break;
            case "4":
                name = "South Australia";
                break;
            case "5":
                name = "Western Australia";
                break;
            case "6":
                name = "Tasmania";
                break;
            case "7":
                name = "Northern Territory";
                break;
            case "8":
                name = "Australian Capital Territory";
                break;
            case "9":
                name = "All others";
                break;
            default:
                name = "error";
                break;
        }
        return name;
    }

    public String tableMakerAttributes(String[] queryAndCaption) {
        String table = "<p class = 'inputField' id = 'showingResultsFor'>" + queryAndCaption[1] + "</p>"
                + "<div class = 'tableFixHead'><table id = 'tableAttributes'> <thead> <th>Ranking</th> <th>State</th> <th>LGA name</th> <th>Homeless people per 100,000 population</th> <th>% Males</th> <th>Median Age</th> <th>Median Income ($AUD)</th> <th>Median Mortgage ($AUD)</th> <th>Median Rent ($AUD)</th> </thead>";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(queryAndCaption[0]);

            int i = 1;
            while (results.next()) {
                String lgaName = results.getString("Name");
                String state = results.getString("state");
                String totalRatio = results.getString("Homeless people per 100,000 population");
                String males = results.getString("%Males");
                if (males == null) {
                    males = "NA";
                }
                String age = results.getString("Median age");
                String income = results.getString("Median income");
                String mortgage = results.getString("Median mortgage repayments");
                String rent = results.getString("Median rent");
                table += "<tr> <td>" + i + "</td> <td>" + state + "</td> <td>"+ lgaName + "</td><td>" + totalRatio + "</td> <td>" + males
                        + "</td> <td>" + age + "</td> <td>" + income + "</td> <td>" + mortgage + "</td> <td>" + rent
                        + "</td> <tr>";
                ++i;
            }
            table += "</table> </div>";
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return table;
    }

    public String[] queryMaker1(String lgaName, String year, String columns[], ArrayList<String> lgaFilters, String ascdesc,
            String sorted, String showing) {
        String[] queryAndCaption = new String[2];
        queryAndCaption[1] = showing;
        String query = "SELECT CASE ";
        String stateQuery = "";
        String states = "";
        // iterate through the users state selections and add where clauses
        int start = 8;
        if (lgaFilters.get(start) == null) {
            stateQuery += " AND ha.code NOT LIKE '1%'";
            states += "NSW, ";
        } else {
            query += "WHEN ha.code LIKE '1%' THEN 'NSW' ";
        }
        if (lgaFilters.get(start + 1) == null) {
            stateQuery += " AND ha.code NOT LIKE '2%'";
            states += "VIC, ";
        } else {
            query += "WHEN ha.code LIKE '2%' THEN 'VIC' ";
        }
        if (lgaFilters.get(start + 2) == null) {
            stateQuery += " AND ha.code NOT LIKE '3%'";
            states += "QLD, ";
        } else {
            query += "WHEN ha.code LIKE '3%' THEN 'QLD' ";
        }
        if (lgaFilters.get(start + 3) == null) {
            stateQuery += " AND ha.code NOT LIKE '4%'";
            states += "SA, ";
        } else {
            query += "WHEN ha.code LIKE '4%' THEN 'SA' ";
        }
        if (lgaFilters.get(start + 4) == null) {
            stateQuery += " AND ha.code NOT LIKE '5%'";
            states += "WA, ";
        } else {
            query += "WHEN ha.code LIKE '5%' THEN 'WA' ";
        }
        if (lgaFilters.get(start + 5) == null) {
            stateQuery += " AND ha.code NOT LIKE '6%'";
            states += "TAS, ";
        } else {
            query += "WHEN ha.code LIKE '6%' THEN 'TAS' ";
        }
        if (lgaFilters.get(start + 6) == null) {
            stateQuery += " AND ha.code NOT LIKE '7%'";
            states += "NT, ";
        } else {
            query += "WHEN ha.code LIKE '7%' THEN 'NT' ";
        }
        if (lgaFilters.get(start + 7) == null) {
            stateQuery += " AND ha.code NOT LIKE '8%'";
            states += "ACT, ";
        } else {
            query += "WHEN ha.code LIKE '8%' THEN 'ACT' ";
        }
        if (lgaFilters.get(start + 8) == null) {
            stateQuery += " AND ha.code NOT LIKE '9%'";
            states += "All others ";
        } else {
            query += "WHEN ha.code LIKE '9%' THEN 'Others' ";
        }
        if (!states.equals("")) {
            queryAndCaption[1] += "In all states apart from:" + states;
        } else {
            queryAndCaption[1] += "in all states";
        }

        query += "END state, l.Name, (((" + columns[0]
                + ")* 100000) / p.value) AS 'Homeless people per 100,000 population', ";

        if (columns[1] == null) {
            query += "0";
        }
        else {
            query += "((" + columns[1] + ")*100 / (" + columns[0] + "))";
        }
                
        query += " AS '%Males'"
                + ", MedianIncome AS 'Median Income', Age AS 'Median age', Mortgage AS 'Median mortgage repayments', Rent AS 'Median rent' FROM HomelessAtRisk ha JOIN Income i on ha.code = i.code JOIN Population p on ha.code = p.code JOIN LGA l on l.code = ha.code ";
        // make where clauses from user inputs into LGA attribute fields
        String whereClauses = " p.year = " + year + " AND ha.year = '" + year + "' AND (Age BETWEEN " + lgaFilters.get(0) + " and "
                + lgaFilters.get(1) + ") AND (MedianIncome BETWEEN $" + lgaFilters.get(2) + " and $" + lgaFilters.get(3)
                + ") AND (Mortgage BETWEEN $" + lgaFilters.get(4) + " and $" + lgaFilters.get(5) + ") AND (Rent BETWEEN $"
                + lgaFilters.get(6) + " and $" + lgaFilters.get(7) + ")";

        if (!lgaName.equals("")) {
            query += "WHERE l.name LIKE '%" + lgaName + "%' AND year = '2018'";
            queryAndCaption[1] += " in the LGA's with this in their name: '" + lgaName + "', ";
        } else {
            queryAndCaption[1] += " where each LGA falls in the following ranges: " + whereClauses + ", ";
            queryAndCaption[1] = queryAndCaption[1].replace("AND", "and");
            queryAndCaption[1] = queryAndCaption[1].replace("(", "");
            queryAndCaption[1] = queryAndCaption[1].replace(")", "");
            queryAndCaption[1] = queryAndCaption[1].replace("BETWEEN", "between");
            queryAndCaption[1] = queryAndCaption[1].replace("p.", "");
            queryAndCaption[1] = queryAndCaption[1].replace("and ha.year = '2018' ", "");
            whereClauses = whereClauses.replace("$", "");
            query += "WHERE " + whereClauses;
        }
        query += stateQuery;
        // ordered by ?
        query += " ORDER BY \"" + sorted + "\"";
        // Ascending descending?
        if (ascdesc != null) {
            if (ascdesc.equals("d")) {
            query += " DESC";
            } else {
                query += " ASC";
            }  
        }    

        queryAndCaption[0] = query;
        return queryAndCaption;
    }

    public String totalPop(ArrayList<String> state, String input, String radio, String order, String changeDrop) {
        Connection connection = null;
        
        String html = "<div class = 'tableFixHead'><table class = ><thead><tr><th></th><th> State </th><th> LGA </th><th> Population (POP) <br>Growth </th><th> Homelessness (HL) <br>Growth </th><th> At Risk (AR) <br>Growth </th><th> HL & AR Growth per <br>1% POP Growth </th></tr></thead>";

        String query = "SELECT CASE "; 
        
        if (state.get(0) != null) {
            query += "WHEN h8.code LIKE '2%' THEN 'VIC' ";
        }
        if (state.get(1) != null) {
            query += "WHEN h8.code LIKE '4%' THEN 'SA' ";
        }
        if (state.get(2) != null) {
            query += "WHEN h8.code LIKE '7%' THEN 'NT' ";
        }
        if (state.get(3) != null) {
            query += "WHEN h8.code LIKE '5%' THEN 'WA' ";
        }
        if (state.get(4) != null) {
            query += "WHEN h8.code LIKE '1%' THEN 'NSW' ";
        }
        if (state.get(5) != null) {
            query += "WHEN h8.code LIKE '3%' THEN 'QLD' ";
        }
        if (state.get(6) != null) {
            query += "WHEN h8.code LIKE '6%' THEN 'TAS'";
        }
        if (state.get(7) != null) {
            query += "WHEN h8.code LIKE '8%' THEN 'ACT' ";
        }
        if (state.get(8) != null) {
            query += "WHEN h8.code LIKE '9%' THEN 'OTHER' ";
        }
    

        query += "END state, l.name, p2.value-p1.value AS total, round((((((" + input
                + ")*1.0))-1)*100), 2) AS homeless, ";
        query += "round((((((h8.AF09+h8.AM09+h8.AF19+h8.AM19+h8.AF29+h8.AM29+h8.AF39+h8.AM39+h8.AF49+h8.AM49+h8.AF59+h8.AM59+h8.AF60+h8.AM60)*1.0)/";
        query += "((h6.AF09+h6.AM09+h6.AF19+h6.AM19+h6.AF29+h6.AM29+h6.AF39+h6.AM39+h6.AF49+h6.AM49+h6.AF59+h6.AM59+h6.AF60+h6.AM60)*1.0))-1)*100), 2) AS atrisk, ";
            
        query += "round((round(((";
        query += "(((h8.AF09+h8.AM09+h8.AF19+h8.AM19+h8.AF29+h8.AM29+h8.AF39+h8.AM39+h8.AF49+h8.AM49+h8.AF59+h8.AM59+h8.AF60+h8.AM60+";
        query += "h8.HF09+h8.HM09+h8.HF19+h8.HM19+h8.HF29+h8.HM29+h8.HF39+h8.HM39+h8.HF49+h8.HM49+h8.HF59+h8.HM59+h8.HF60+h8.HM60)*1.0) /";
        query += "((h6.AF09+h6.AM09+h6.AF19+h6.AM19+h6.AF29+h6.AM29+h6.AF39+h6.AM39+h6.AF49+h6.AM49+h6.AF59+h6.AM59+h6.AF60+h6.AM60+";
        query += "h6.HF09+h6.HM09+h6.HF19+h6.HM19+h6.HF29+h6.HM29+h6.HF39+h6.HM39+h6.HF49+h6.HM49+h6.HF59+h6.HM59+h6.HF60+h6.HM60)*1.0))";
        query += "-1)*100), 2)";
        query += "/";
        query += "(round(((";
        query += "((p2.value * 1.0)/(p1.value * 1.0))";
        query += "-1)*100), 2))), 2) AS ha ";

        query += "FROM population p1 JOIN population p2 ON p1.code = p2.code JOIN homelessatrisk h8 ON p1.code = h8.code JOIN lga l ON l.code = p1.code ";
        query += "JOIN homelessatrisk h6 ON h6.code = h8.code WHERE h6.year = '2016' AND h8.year = '2018' AND homeless IS NOT NULL AND atrisk IS NOT NULL AND ha IS NOT NULL AND p1.year = 2016 AND p2.year = 2018 ";
        String add = "";
        if (radio.equals("i")) {
            add += " AND homeless > 0";
        } else if (radio.equals("d")) {
            add += " AND homeless < 0";
        }

        if (changeDrop.equals("p")) {
            if (order.equals("alp")) {
                add += " ORDER BY l.name ASC";
            } else if (order.equals("desc")) {
                add += " ORDER BY total DESC";
            } else if (order.equals("asc")) {
                add += " ORDER BY total ASC";
            }
        } else if (changeDrop.equals("p2")) {
            if (order.equals("alp")) {
                add += " ORDER BY l.name ASC";
            } else if (order.equals("desc")) {
                add += " ORDER BY ha DESC";
            } else if (order.equals("asc")) {
                add += " ORDER BY ha ASC";
            }
        }

        query += add;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);

            int ranking = 1;
            while (results.next()) {
                html += "<tr><td> " + ranking + "</td><td> " + results.getString("state") + "</td><td>" + results.getString("name") + "</td><td>";
                html += results.getString("total") + "</td><td>" + results.getString("homeless") + "%</td><td>" + results.getString("atrisk") + "%</td><td>" + results.getString("ha") + ":1</td></tr>";
                ++ranking;
            }
            html += "</table></div>";

        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return html;
    }

    public ArrayList<String> columnMakerEdited(ArrayList<String> inputs) {
        ArrayList<String> columns = new ArrayList<String>();
        // populate columns by iterating through query that returns column names.
        Connection connection = null;
        String columnsQuery = "PRAGMA table_info(HomelessAtRisk)"; // This is the query.
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet columnNames = statement.executeQuery(columnsQuery);

            while (columnNames.next()) {
                String name = columnNames.getString("name");
                if (name.equals("Code") || name.equals("Year")) {
                    continue;
                } else {
                    columns.add(name);
                }
            }
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        if (inputs.get(0) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("M")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(1) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("F")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(2) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("H")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(3) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("A")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(4) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("09")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(5) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("19")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(6) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("29")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(7) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("39")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(8) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("49")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(9) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("59")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(10) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("60")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(11) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("U")) {
                    i.remove();
                }
            }
        }

        return columns;
    }

    public String columnMakerChange(ArrayList<String> select, String arithmetic) {
        ArrayList<String> columnMaker = columnMakerEdited(select);
        String queryString = "";

        String columnString2016 = "";

        for (String column : columnMaker) {
            if (column.length() > 2) {
                if (column.charAt(0) == 'U') {
                    if (columnString2016.charAt(3) == 'H') {
                        break;
                    }
                }
            }
            columnString2016 += "h6." + column + "+";
        }

        if (columnString2016.length() > 0) {
            columnString2016 = columnString2016.substring(0, columnString2016.length() - 1);
        }

        String columnString2018 = "";
        for (String column : columnMaker) {
            if (column.charAt(0) == 'U') {
                if (columnString2016.charAt(3) == 'H') {
                    break;
                }
            }
            columnString2018 += "h8." + column + "+";
        }

        if (columnString2018.length() > 0) {
            columnString2018 = columnString2018.substring(0, columnString2018.length() - 1);
        }

        if (arithmetic.equals("percentage")) {
            queryString = columnString2018 + ")*1.0)/((" + columnString2016;
        }

        if (arithmetic.equals("difference")) {
            queryString = columnString2018 + ")-(" + columnString2016;
        }
        return queryString;
    }

    public String changeTableMaker(String query, String totalName) {
        Connection connection = null;
        String html = "<div class = 'tableFixHead'><table class='table1'><thead> <tr> <th> Ranking </th> <th> State </th> <th> LGA </th> <th>" + totalName + "</th></tr>";

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet results = statement.executeQuery(query);

            int rank = 1;
            while (results.next()) {
                html += "<tr> <td>" + rank + "</td> <td>" + results.getString("state") + "</td><td>" + results.getString("name") + "</td><td>";
                if (totalName.equals("Homeless:At Risk")) {
                    html += results.getString("total") + ":1 </td></tr>";
                }

                else {
                    html += results.getString("total") + "</td> </tr>";
                }
                ++rank;
            }

            html += "</table></div>";

            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return html;
    }

    public String changeSelectComplete(String query, ArrayList<String> state, String radio, String order) {

        String selectQuery = "SELECT CASE ";
        
        if (state.get(0) != null) {
            selectQuery += "WHEN h8.code LIKE '2%' THEN 'VIC' ";
        }
        if (state.get(1) != null) {
            selectQuery += "WHEN h8.code LIKE '4%' THEN 'SA' ";
        }
        if (state.get(2) != null) {
            selectQuery += "WHEN h8.code LIKE '7%' THEN 'NT' ";
        }
        if (state.get(3) != null) {
            selectQuery += "WHEN h8.code LIKE '5%' THEN 'WA' ";
        }
        if (state.get(4) != null) {
            selectQuery += "WHEN h8.code LIKE '1%' THEN 'NSW' ";
        }
        if (state.get(5) != null) {
            selectQuery += "WHEN h8.code LIKE '3%' THEN 'QLD' ";
        }
        if (state.get(6) != null) {
            selectQuery += "WHEN h8.code LIKE '6%' THEN 'TAS'";
        }
        if (state.get(7) != null) {
            selectQuery += "WHEN h8.code LIKE '8%' THEN 'ACT' ";
        }
        if (state.get(8) != null) {
            selectQuery += "WHEN h8.code LIKE '9%' THEN 'OTHER' ";
        }
            
        selectQuery += "END state, l.name as name, (" + query + ") AS total ";
        selectQuery += "FROM homelessatrisk h6 JOIN homelessatrisk h8 ON h6.code = h8.code JOIN lga l ON l.code = h8.code WHERE h6.year = '2016' AND h8.year = '2018' AND total IS NOT NULL";

        selectQuery += " AND (";
        if (state.get(0) != null) {
            selectQuery += "h8.code LIKE '2%' OR ";
        }
        if (state.get(1) != null) {
            selectQuery += "h8.code LIKE '4%' OR ";
        }
        if (state.get(2) != null) {
            selectQuery += "h8.code LIKE '7%' OR ";
        }
        if (state.get(3) != null) {
            selectQuery += "h8.code LIKE '5%' OR ";
        }
        if (state.get(4) != null) {
            selectQuery += "h8.code LIKE '1%' OR ";
        }
        if (state.get(5) != null) {
            selectQuery += "h8.code LIKE '3%' OR ";
        }
        if (state.get(6) != null) {
            selectQuery += "h8.code LIKE '6%' OR ";
        }
        if (state.get(7) != null) {
            selectQuery += "h8.code LIKE '8%' OR ";
        }
        if (state.get(8) != null) {
            selectQuery += "h8.code LIKE '9%' OR ";
        }

        if (selectQuery.length() > 0) {
            selectQuery = selectQuery.substring(0, selectQuery.length() - 4) + ")";
        }

        if (radio.equals("i")) {
            selectQuery += " AND total > 0";
        } else if (radio.equals("d")) {
            selectQuery += " AND total < 0";
        }

        if (order.equals("alp")) {
            selectQuery += " ORDER BY name ASC";
        } else if (order.equals("desc")) {
            selectQuery += " ORDER BY total DESC";
        } else if (order.equals("asc")) {
            selectQuery += " ORDER BY total ASC";
        }

        return selectQuery;
    }

    public String stateFilter(ArrayList<String> inputs) {
        String selectedColumns = "";
        ArrayList<String> columns = new ArrayList<String>();
        // populate columns by iterating through query that returns column names.
        Connection connection = null;
        String columnsQuery = "PRAGMA table_info(HomelessAtRisk)"; // This is the query.
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet columnNames = statement.executeQuery(columnsQuery);

            while (columnNames.next()) {
                String name = columnNames.getString("name");
                if (name.equals("Code") || name.equals("Year")) {
                    continue;
                } else {
                    columns.add(name);
                }
            }
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        if (inputs.get(0) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("M")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(1) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("F")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(2) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("H")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(3) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("A")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(4) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("09")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(5) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("19")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(6) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("29")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(7) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("39")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(8) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("49")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(9) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("59")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(10) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("60")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(11) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("U")) {
                    i.remove();
                }
            }
        }
        // build column total from columns
        for (String column : columns) {
            selectedColumns += column + "+";
        }
        // remove last +
        if (selectedColumns.length() > 0) {
            selectedColumns = selectedColumns.substring(0, selectedColumns.length() - 1);
        }

        return selectedColumns;
    }

    public String ratioMaker(ArrayList<String> select) {

        ArrayList<String> columns = columnMakerEdited(select);

        String columnString2016 = "";
        for (String column : columns) {
            if (column.charAt(0) == 'U') {
                if (columnString2016.charAt(3) != 'H')
                    break;
            }
            columnString2016 += "h6." + column + "+";
        }
        if (columnString2016.length() > 0) {
            columnString2016 = columnString2016.substring(0, columnString2016.length() - 1);
        }

        String columnString2018 = "";
        for (String column : columns) {
            if (column.charAt(0) == 'U') {
                if (columnString2016.charAt(3) != 'H')
                    break;
            }
            columnString2018 += "h8." + column + "+";
        }
        if (columnString2018.length() > 0) {
            columnString2018 = columnString2018.substring(0, columnString2018.length() - 1);
        }

        String queryString = columnString2018 + ")-(" + columnString2016;

        return queryString;
    }

    public String showingResults(ArrayList<String> demographic, ArrayList<String> state, String changeDrop, String idb,
            String order) {

        String html = "Showing results for <b>";

        if (changeDrop.equals("p")) {
            html += "Total Population</b>";
        } else if (changeDrop.equals("h")) {
            html += "Homeless Population</b>";
        } else if (changeDrop.equals("a")) {
            html += "At Risk of Homeless Population</b>";
        } else if (changeDrop.equals("r")) {
            html += "Ratio of Change of Homeless to Change At Risk of Homeless</b>";
        } /*
           * else if (changeDrop.equals("ah")) { // path tbc html += "Population"
           */

        html += " in <b>";

        if (order.equals("alp")) {
            html += " Alphabetical Order</b>";
        } else if (order.equals("desc")) {
            html += " Descending Order</b>";
        } else if (order.equals("asc")) {
            html += " Ascending Order</b>";
        }

        html += " showing <b>";

        if (idb.equals("i")) {
            html += " Increasing Change only</b>";
        } else if (idb.equals("d")) {
            html += " Decreasing Change only</b>";
        } else {
            html += " both Increasing and Decreasing change</b>";
        }

        html += " in ";
        int i = 0;
        int j = 0;

        for (String count : state) {
            if (count != null) {
                ++j;
            }
        }

        if (j > 8) {
            html += "<b>All States</b> ";
        }

        else {
            if (state.get(0) != null) {
                html += "<b>VIC</b>, ";
                ++i;
                if (i == j - 1) {
                    html = html.substring(0, html.length() - 2);
                    html += " and ";
                }

            }
            if (state.get(1) != null) {
                html += "<b>SA</b>, ";
                ++i;
                if (i == j - 1) {
                    html = html.substring(0, html.length() - 2);
                    html += " and ";
                }
            }
            if (state.get(2) != null) {
                html += "<b>NT </b>, ";
                ++i;
                if (i == j - 1) {
                    html = html.substring(0, html.length() - 2);
                    html += " and ";
                }
            }
            if (state.get(3) != null) {
                html += "<b>WA</b>, ";
                ++i;
                if (i == j - 1) {
                    html = html.substring(0, html.length() - 2);
                    html += " and ";
                }
            }
            if (state.get(4) != null) {
                html += "<b>NSW</b>, ";
                ++i;
                if (i == j - 1) {
                    html = html.substring(0, html.length() - 2);
                    html += " and ";
                }
            }
            if (state.get(5) != null) {
                html += "<b>QLD</b>, ";
                ++i;
                if (i == j - 1) {
                    html = html.substring(0, html.length() - 2);
                    html += " and ";
                }
            }
            if (state.get(6) != null) {
                html += "<b>TAS</b>, ";
                ++i;
                if (i == j - 1) {
                    html = html.substring(0, html.length() - 2);
                    html += " and ";
                }
            }
            if (state.get(7) != null) {
                html += "<b>ACT</b>, ";
                ++i;
                if (i == j - 1) {
                    html = html.substring(0, html.length() - 2);
                    html += " and ";
                }
            }
            if (state.get(8) != null) {
                html += "<b>Other</b>, ";
                ++i;
                if (i == j - 1) {
                    html = html.substring(0, html.length() - 2);
                    html += " and ";
                }
            }

            if (html.length() > 0) {
                html = html.substring(0, html.length() - 2);
            }
        }

        html += " for ";

        if (demographic.get(0) != null && demographic.get(1) != null) {
            html += "<b>Males</b> and <b>Females</b> ";
        } else if (demographic.get(0) != null && demographic.get(1) == null) {
            html += "<b>Males</b> ";
        } else {
            html += "<b>Females</b>";
        }

        j = 0;
        for (i = 4; i < demographic.size() - 1; ++i) {
            if (demographic.get(i) != null)
                ++j;
        }

        html += " for ages ";

        if (j == 7) {
            html += "<b>All Ages</b> ";
        }

        else if (j == 6 && demographic.get(4) == null) {
            html += "<b>10 & Above</b> ";
        } else if (j == 6 && demographic.get(10) == null) {
            html += "<b>59 & Below</b> ";
        }

        else if (j == 5 && demographic.get(4) == null && demographic.get(5) == null) {
            html += "<b>20 & Above</b> ";
        }

        else if (j == 5 && demographic.get(9) == null && demographic.get(10) == null) {
            html += "<b>49 & Below</b> ";
        }

        else if (j == 4 && demographic.get(4) == null && demographic.get(5) == null && demographic.get(6) == null) {
            html += "<b>30 & Above</b> ";
        }

        else if (j == 4 && demographic.get(8) == null && demographic.get(9) == null && demographic.get(10) == null) {
            html += "<b>39 & Below</b> ";
        }

        else if (j == 3 && demographic.get(4) == null && demographic.get(5) == null && demographic.get(6) == null
                && demographic.get(7) == null) {
            html += "<b>40 & Above</b> ";
        }

        else if (j == 3 && demographic.get(7) == null && demographic.get(8) == null && demographic.get(9) == null
                && demographic.get(10) == null) {
            html += "<b>29 & Below</b> ";
        }

        else if (j == 2 && demographic.get(4) == null && demographic.get(5) == null && demographic.get(6) == null
                && demographic.get(7) == null && demographic.get(8) == null) {
            html += "<b>50 & Above</b> ";
        }

        else if (j == 2 && demographic.get(6) == null && demographic.get(7) == null && demographic.get(8) == null
                && demographic.get(9) == null && demographic.get(10) == null) {
            html += "<b>19 & Below</b> ";
        }

        else {
            if (demographic.get(4) != null) {
                html += "<b>0 to 9</b>, ";
            }

            if (demographic.get(5) != null) {
                html += "<b>10 to 19</b>, ";
            }

            if (demographic.get(6) != null) {
                html += "<b>20 to 29</b>, ";
            }

            if (demographic.get(7) != null) {
                html += "<b>30 to 39</b>, ";
            }

            if (demographic.get(8) != null) {
                html += "<b>40 to 49</b>, ";
            }

            if (demographic.get(9) != null) {
                html += "<b>50 to 59</b>, ";
            }

            if (demographic.get(10) != null) {
                html += "<b>60+</b>, ";
            }
        }

        return html;
    }

}
