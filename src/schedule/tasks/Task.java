package schedule.tasks;

public class Task {
    /*
     * Task class used to create tasks
     * this class is used by Animal and Treatments to create a list of
     * tasks for each Animal.
     */
    private final int TASKID;
    private String description;
    // private int startHour;
    private int maxWindow;
    private int duration;

    /**
     * 
     * @param taskID
     * @param description
     * @param startHour
     * @param maxWindow
     * @param duration
     */
    public Task(int taskID, String description, // int startHour,
            int duration, int maxWindow) {
        this.TASKID = taskID;
        this.description = description;
        // this.startHour = startHour;
        this.maxWindow = maxWindow;
        this.duration = duration;
    }

    /*
     * Basic getters
     */

    /**
     * TaskID getter
     * 
     * @return TASKID
     */
    public int getTaskID() {
        return this.TASKID;
    }

    /**
     * Description getter
     * 
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * MaxWindow getter
     * 
     * @return maxWindow
     */
    public int getMaxWindow() {
        return this.maxWindow;
    }

    /**
     * Duration getter
     * 
     * @return duration
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * 
     * @return
     */
    // public int getStartHour() {
    // return this.startHour;
    // }
}
