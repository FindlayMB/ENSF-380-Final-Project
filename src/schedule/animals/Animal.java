/**
 * 
 * @author Findlay Brown
 * @author Parsa Kargari
 * @author Aly Mohammed
 * @author Lujaina Eldelebshany
 * @version 1.9.3
 * @since 2023-04-10
 */

package schedule.animals;

import schedule.builder.data.ScheduleItem;

public class Animal {
    private final int ID;
    private String nickName;
    private String species;
    private boolean kitStatus = false;

    /**
     * Animal Constructor
     * 
     * @param ID      - the Animal's ID
     * @param name    - the Animal's name/nickname
     * @param species - the species of the Animal
     */
    public Animal(int ID, String name, String species) {
        if (ID < 0) {
            throw new IllegalArgumentException();
        }
        if (name == null || name == "") {
            throw new IllegalArgumentException();
        }
        if (species == null || species == "") {
            throw new IllegalArgumentException();
        }

        this.ID = ID;
        this.nickName = name;
        this.species = species;
    }

    // *****************
    // GETTERS
    // *****************

    /**
     * ID Getter
     * 
     * @return ID
     */
    public int getID() {
        return this.ID;
    }

    /**
     * NickName Getter
     * 
     * @return nickName
     */
    public String getName() {
        return this.nickName;
    }

    /**
     * Species Getter
     * 
     * @return species
     */
    public String getSpecies() {
        return this.species;
    }

    /**
     * KitStatus Getter
     * 
     * @return kitStatus
     */
    public boolean getKitStatus() {
        return this.kitStatus;
    }

    // *****************
    // OTHER METHODS
    // *****************

    /**
     * KitStatus Setter
     */
    public void setKitStatus() {
        this.kitStatus = true;
    }

    /**
     * Returns a ScheduleItem object that is the feeding task for an animal
     * This is overwritten for each animal
     * 
     * @return ScheduleItem
     */
    public ScheduleItem feeding() {
        return new ScheduleItem(this.ID, 0, 0, 3, 5);
    }

}
