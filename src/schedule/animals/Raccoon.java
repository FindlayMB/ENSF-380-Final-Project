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

public class Raccoon extends Animal {

    // Raccoon Constructor
    public Raccoon(int ID, String nickName, String species) {
        super(ID, nickName, species);
    }

    @Override
    public ScheduleItem feeding() {
        // starthour = 00:00
        return new ScheduleItem(getID(), 0, 0, 3, 5);
    }
}
