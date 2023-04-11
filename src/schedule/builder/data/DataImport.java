/**
 * 
 * @author Findlay Brown
 * @author Parsa Kargari
 * @author Aly Mohammed
 * @author Lujaina Eldelebshany
 * @version 2.3.9
 */

package schedule.builder.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import schedule.tasks.*;
import schedule.animals.*;

/** */
public class DataImport {
    private Connection dbConnection;

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
     * Uses the dbConnection to get data from tables
     * 
     * @param tableName -> the table to get data from
     * @return ResultSet of the table
     * @throws SQLException -> throws if there is an error
     *                      getting data from selected table
     */
    public ResultSet getTable(String tableName) throws SQLException {
        String query = String.format("SELECT * FROM %s", tableName);
        Statement statement = dbConnection.createStatement();
        return statement.executeQuery(query);
    }

    /**
     * Creates a hashmap from a result set which contains the content
     * from table 'animals'
     * 
     * @return animalHashMap -> key: animalID which is an integer
     *         value: Animal object
     * @throws SQLException
     */
    public HashMap<Integer, Animal> getAnimalsTable() throws SQLException {
        HashMap<Integer, Animal> animalHashMap = new HashMap<Integer, Animal>();
        ResultSet table = getTable("animals");
        while (table.next()) {
            Animal newAnimal;
            String animalSpecies = table.getString("AnimalSpecies");
            if (animalSpecies.equals("beaver")) {
                newAnimal = new Beaver(table.getInt("AnimalID"),
                        table.getString("AnimalNickname"),
                        animalSpecies);
            } else if (animalSpecies.equals("coyote")) {
                newAnimal = new Coyote(table.getInt("AnimalID"),
                        table.getString("AnimalNickname"),
                        animalSpecies);
            } else if (animalSpecies.equals("fox")) {
                newAnimal = new Fox(table.getInt("AnimalID"),
                        table.getString("AnimalNickname"), animalSpecies);
            } else if (animalSpecies.equals("porcupine")) {
                newAnimal = new Porcupine(table.getInt("AnimalID"),
                        table.getString("AnimalNickname"),
                        animalSpecies);
            } else {
                newAnimal = new Raccoon(table.getInt("AnimalID"),
                        table.getString("AnimalNickname"),
                        animalSpecies);
            }
            animalHashMap.put(table.getInt("AnimalID"), newAnimal);
        }
        return animalHashMap;
    }

    /**
     * Creates an hashmap from a result set which contains the content
     * from table 'tasks'
     * 
     * @return taskHashMap -> key: taskID which is an integer
     *         value: Task object
     * @throws SQLException -> If getTable fails
     */
    public HashMap<Integer, Task> getTasksTable() throws SQLException {
        HashMap<Integer, Task> taskHashMap = new HashMap<Integer, Task>();
        ResultSet table = getTable("tasks");
        while (table.next()) {
            Task newTask = new Task(table.getInt("TaskID"),
                    table.getString("Description"),
                    table.getInt("Duration"),
                    table.getInt("MaxWindow"));
            taskHashMap.put(table.getInt("TaskID"), newTask);
        }
        return taskHashMap;
    }

    /**
     * Creates an arraylist from a result set which contains the content
     * from table 'treatments'
     * 
     * @return treatmentList
     * @throws SQLException
     */
    public ArrayList<Treatment> getTreatmentsTable() throws SQLException {
        ArrayList<Treatment> treatmentList = new ArrayList<Treatment>();
        ResultSet table = getTable("treatments");
        while (table.next()) {
            Treatment newTreatment = new Treatment(
                    table.getInt(1),
                    table.getInt(2),
                    table.getInt(3),
                    table.getInt(4));
            treatmentList.add(newTreatment);
        }
        return treatmentList;

    }

    /**
     * Sets the startHour of a treatment based on user input to fix the schedule
     * 
     * @param treatmentID  -> key in table to find location to update
     * @param newStartHour -> value to be put in table
     * @return boolean -> true or false depending if update is successful
     */
    public boolean setValue(int treatmentID, int newStartHour) {
        String query = String.format(
                "UPDATE TREATMENTS SET StartHour = %d WHERE TreatmentID = %d",
                newStartHour, treatmentID);
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
