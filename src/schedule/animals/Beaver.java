/**
 * 
 * @author Findlay Brown
 * @author Parsa Kargari
 * @author Aly Mohammed
 * @author Lujaina Eldelebshany
 * @version 1.9.0
 * @since 2023-04-10
 */
package schedule.animals;

import schedule.builder.data.ScheduleItem;

public class Beaver extends Animal {

    // Beaver Constructor
    public Beaver(int ID, String nickName, String species) {
        super(ID, nickName, species);
    }

    @Override
    public ScheduleItem feeding() {
        // starthour = 08:00
        return new ScheduleItem(getID(), 0, 8, 3, 5);
    }
}
