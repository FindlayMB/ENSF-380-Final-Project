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

public class Coyote extends Animal {

    // Coyote Constructor
    public Coyote(int ID, String nickName, String species) {
        super(ID, nickName, species);
    }

    @Override
    public ScheduleItem feeding() {
        // starthour = 19:00
        return new ScheduleItem(getID(), 0, 19, 3, 5);
    }

}
