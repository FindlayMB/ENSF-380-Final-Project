package schedule.builder;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.DbDoc;

import schedule.tasks.*;
import schedule.animals.*;

/**
 * This class handles importing data from a MySQL database to the scheduling application.
 */
public class DataImport {
    
    // Instance variables
    private Connection dbConnection;                                    // Database connection object
    ArrayList<Animal> animalList = new ArrayList<Animal>();             // List of animals
    ArrayList<Task> taskList = new ArrayList<Task>();                   // List of tasks
    ArrayList<Treatment> treatmentList = new ArrayList<Treatment>();    // List of treatments

    /**
     * Tries to create a connection to EWR database on localhost
     * saves the connection to dbConnection if successful
     * 
     * @param username -> username for database
     * @param password -> password for database
     * 
     * @return boolean -> returns true if database connection was succesful
     *         otherwise returns false
     */
    public boolean createConnection(String username, String password) {
        try {
            this.dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/EWR", username, password); // Establishes a database connection using the specified username and password
        } catch (Exception e) {
            System.out.println(e);
            return false; // Returns false if the connection failed
        }
        return true; // Returns true if the connection was successful
    }

    /**
     * Returns a ResultSet containing all the rows from the specified table
     * 
     * @param tableName -> name of the table to retrieve
     * @return ResultSet -> ResultSet containing all the rows from the specified table
     * @throws SQLException -> if an error occurs while executing the SQL query
     */
    public ResultSet getTable(String tableName) throws SQLException {
        String query = String.format("SELECT * FROM %s", tableName); // Constructs an SQL query to select all rows from the specified table
        Statement statement = dbConnection.createStatement();               // Creates a new Statement object to execute the query
        return statement.executeQuery(query);                               // Executes the query and returns the ResultSet
    }

    /**
     * Retrieves all rows from the 'animals' table and prints their IDs, nicknames, and species
     * 
     * @throws Exception -> if an error occurs while retrieving data from the table
     */
    public void getAnimalsTable() throws Exception {
        ResultSet table = getTable("animals");                                // Retrieves all rows from the 'animals' table
        while (table.next()) {                                                          // Loops through each row in the ResultSet
            System.out.print(table.getInt("AnimalID"));                     // Prints the value of the 'AnimalID' column for the current row
            System.out.print(("\t" + table.getString("AnimalNickname")));   // Prints the value of the 'AnimalNickname' column for the current row
            System.out.println("\t" + table.getString("AnimalSpecies"));    // Prints the value of the 'AnimalSpecies' column for the current row
        }
    }

    /**
 * Retrieves all rows from the 'tasks' table and adds them to the taskList
 * 
 * @throws Exception -> if an error occurs while retrieving data from the table
 */
public void getTasksTable() throws Exception {
    ResultSet table = getTable("tasks"); // Retrieves all rows from the 'tasks' table
    while (table.next()) { // Loops through each row in the ResultSet
        // Creates a new Task object using the values from the current row
        Task newTask = new Task(table.getInt("TaskID"), 
                                table.getString("Description"), 
                                table.getInt("Duration"),
                                table.getInt("MaxWindow"));
        
        // Adds the new Task object to the taskList
        this.taskList.add(newTask);
    }
    System.out.println(taskList); // Prints the taskList to the console
}

/**
 * Retrieves all rows from the 'treatments' table and prints them to the console
 * 
 * @throws Exception -> if an error occurs while retrieving data from the table
 */
public void getTreatmentsTable() throws Exception {
    ResultSet table = getTable("treatments"); // Retrieves all rows from the 'treatments' table
    while (table.next()) { // Loops through each row in the ResultSet
        // Prints the values from the current row to the console
        System.out.print(table.getInt("TreatmentID"));
        System.out.print("\t" + table.getString("AnimalID"));
        System.out.print("\t" + table.getInt("TaskID"));
        System.out.println("\t" + table.getInt("StartHour"));
    }
}

}
