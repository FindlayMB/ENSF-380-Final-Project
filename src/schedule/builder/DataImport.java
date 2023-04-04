package schedule.builder;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.DbDoc;

import schedule.tasks.*;
import schedule.animals.*;

/** */
public class DataImport {
    private Connection dbConnection;
    ArrayList<Animal> animalList = new ArrayList<Animal>();
    ArrayList<Task> taskList = new ArrayList<Task>();
    ArrayList<Treatment> treatmentList = new ArrayList<Treatment>();

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
            this.dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/EWR", username, password);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    /**
     * 
     * @param tableName
     * @return
     * @throws SQLException
     */
    public ResultSet getTable(String tableName) throws SQLException {
        String query = String.format("SELECT * FROM %s", tableName);
        Statement statement = dbConnection.createStatement();
        return statement.executeQuery(query);
    }

    public void getAnimalsTable() throws Exception {
        ResultSet table = getTable("animals");
        // table.getArray("AnimalID");
        while (table.next()) {

            System.out.print(table.getInt("AnimalID"));
            System.out.print(("\t" + table.getString("AnimalNickname")));
            System.out.println("\t" + table.getString("AnimalSpecies"));
        }
    }

    public void getTasksTable() throws Exception {
        // ArrayList<Task> taskList = new ArrayList<Task>();
        ResultSet table = getTable("tasks");
        while (table.next()) {
            Task newTask = new Task(table.getInt("TaskID"),
                    table.getString("Description"),
                    table.getInt("Duration"),
                    table.getInt("MaxWindow"));
            this.taskList.add(newTask);
        }
        System.out.println(taskList);
    }

    public void getTreatmentsTable() throws Exception {

        ResultSet table = getTable("treatments");
        while (table.next()) {
            System.out.print(table.getInt("TreatmentID"));
            System.out.print(("\t" + table.getString("AnimalID")));
            System.out.print("\t" + table.getInt("TaskID"));
            System.out.println("\t" + table.getInt("StartHour"));
        }

    }
}
