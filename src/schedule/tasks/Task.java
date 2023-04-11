/**
 * 
 * @author Findlay Brown
 * @author Parsa Kargari
 * @author Aly Mohammed
 * @author Lujaina Eldelebshany
 * @version 1.9.2
 * @since 2023-04-10
 */

package schedule.tasks;

public class Task {
    /*
     * Task class used to create tasks
     * this class is used by Animal and Treatments to create a list of
     * tasks for each Animal.
     */
    private final int TASKID;
    private final String DESCRIPTION;
    private final int MAXWINDOW;
    private final int DURATION;

    /**
     * Task Constructor
     * 
     * @param taskID
     * @param description
     * @param maxWindow
     * @param duration
     */
    public Task(int taskID, String description,
            int duration, int maxWindow) {
        if (duration < 0) {
            throw new IllegalArgumentException();
        }
        if (maxWindow < 0 || maxWindow > 24) {
            throw new IllegalArgumentException();
        }

        this.TASKID = taskID;
        this.DESCRIPTION = description;
        this.MAXWINDOW = maxWindow;
        this.DURATION = duration;
    }

    // *****************
    // GETTERS
    // *****************

    /**
     * TaskID Getter
     * 
     * @return TASKID -> a constant integer
     */
    public int getTaskID() {
        return this.TASKID;
    }

    /**
     * Description Getter
     * 
     * @return DESCRIPTION -> a constant String
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * MaxWindow Getter
     * 
     * @return maxWindow
     */
    public int getMaxWindow() {
        return this.MAXWINDOW;
    }

    /**
     * Duration Getter
     * 
     * @return duration
     */
    public int getDuration() {
        return this.DURATION;
    }
}
