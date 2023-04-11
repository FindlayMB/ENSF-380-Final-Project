package schedule.builder.data;

import java.util.Comparator;

public class SortByMaxWindow implements Comparator<ScheduleItem> {

    // Sort Schedule items by maxWindow in ascending order
    public int compare(ScheduleItem o1, ScheduleItem o2) {
        return o1.getMaxWindow() - o2.getMaxWindow();
    }
    /*
     * How to use
     * Collections.sort(scheduleItems, new SortByMaxWindow());
     */

}