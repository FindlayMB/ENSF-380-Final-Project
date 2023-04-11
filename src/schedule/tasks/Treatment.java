/**
 * 
 * @author Findlay Brown
 * @author Parsa Kargari
 * @author Aly Mohammed
 * @author Lujaina Eldelebshany
 * @version 1.9.3
 * @since 2023-04-10
 */

package schedule.tasks;

public class Treatment {
    private final int TREATMENTID;
    private final int ANIMALID;
    private final int TASKID;
    private int startHour;

    /**
     * Treatment Constructor
     * 
     * @param treatmentID
     * @param animalID
     * @param taskID
     * @param startHour
     */
    public Treatment(int treatmentID, int animalID, int taskID, int startHour) {
        this.TREATMENTID = treatmentID;
        this.ANIMALID = animalID;
        this.TASKID = taskID;
        if (startHour < 0 || startHour > 23) {
            throw new IllegalArgumentException();
        }
        this.startHour = startHour;
    }

    /**
     * TreatmentID Getter
     * 
     * @return TREATMENTID -> a constant integer
     */
    public int getTreatmentID() {
        return this.TREATMENTID;
    }

    /**
     * AnimalID Getter
     * 
     * @return ANIMALID -> a constant integer
     */
    public int getAnimalID() {
        return this.ANIMALID;
    }

    /**
     * TaskID Getter
     * 
     * @return TASKID -> a constant integer
     */
    public int getTaskID() {
        return this.TASKID;
    }

    /**
     * StartHour Getter
     * 
     * @return startHour -> an integer
     */
    public int getStartHour() {
        return this.startHour;
    }

    // *****************
    // OTHER METHODS
    // *****************

    /**
     * StartHour Setter
     * 
     * @param newStartHour -> an integer
     */
    public void setStartHour(int startHour) {
        if (startHour < 0 || startHour > 23) {
            throw new IllegalArgumentException();
        }
        this.startHour = startHour;
    }
}
