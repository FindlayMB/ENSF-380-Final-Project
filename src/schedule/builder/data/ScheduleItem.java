/**
 * 
 * @author Findlay Brown
 * @author Parsa Kargari
 * @author Aly Mohammed
 * @author Lujaina Eldelebshany
 * @version 1.7.3
 * @since 2023-04-10
 */

package schedule.builder.data;

public class ScheduleItem {

    private int animalID;
    private int taskID;
    private int startHour;
    private int maxWindow;
    private int duration;
    private int treatmentID;

    /**
     * ScheduleItem Constructor
     * 
     * @param animalID
     * @param taskID
     * @param startHour
     * @param maxWindow
     * @param duration
     */
    public ScheduleItem(int animalID, int taskID, int startHour,
            int maxWindow, int duration) {
        if (duration < 0) {
            throw new IllegalArgumentException();
        }
        if (maxWindow <= 0 || maxWindow > 24) {
            throw new IllegalArgumentException();
        }
        if (startHour < 0 || startHour > 23) {
            throw new IllegalArgumentException();
        }
        this.animalID = animalID;
        this.taskID = taskID;
        this.startHour = startHour;
        this.maxWindow = maxWindow;
        this.duration = duration;
        this.treatmentID = 0;
    }

    public ScheduleItem(int animalID, int taskID, int startHour,
            int maxWindow, int duration, int treatmentID) {
        if (duration < 0) {
            throw new IllegalArgumentException();
        }
        if (maxWindow <= 0 || maxWindow > 24) {
            throw new IllegalArgumentException();
        }
        if (startHour < 0 || startHour > 23) {
            throw new IllegalArgumentException();
        }
        this.animalID = animalID;
        this.taskID = taskID;
        this.startHour = startHour;
        this.maxWindow = maxWindow;
        this.duration = duration;
        this.treatmentID = treatmentID;
    }

    // *****************
    // GETTERS
    // *****************

    /**
     * AnimalID Getter
     * 
     * @return animalID -> an int that stores the animal id
     */
    public int getAnimalID() {
        return this.animalID;
    }

    /**
     * TaskID Getter
     * 
     * @return taskID -> an integer
     */
    public int getTaskID() {
        return this.taskID;
    }

    /**
     * StartHour Getter
     * 
     * @return startHour -> an integer
     */
    public int getStartHour() {
        return this.startHour;
    }

    /**
     * MaxWindow Getter
     * 
     * @return maxWindow -> an integer
     */
    public int getMaxWindow() {
        return this.maxWindow;
    }

    /**
     * Duration Getter
     * 
     * @return duration -> an integer
     */
    public int getDuration() {
        return this.duration;
    }

    public int getTreatmentID() {
        return this.treatmentID;
    }
    // *****************
    // OTHER METHODS
    // *****************

    /**
     * StartHour Setter
     * 
     * @param newStartHour -> an integer
     */
    public void setStartHour(int newStartHour) {
        if (startHour < 0 || startHour > 23) {
            throw new IllegalArgumentException();
        }
        this.startHour = newStartHour;
    }
}
