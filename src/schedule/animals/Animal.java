package schedule.animals;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

import schedule.tasks.Task;

public interface Animal {
    // private final int ID;
    // private final String NAME;
    // private String species;
    // private HashMap<Integer, Task> standardTasks;
    // Hashmap -> keys: animal ID, values: standard tasks
    // Standard tasks
    // Feeding and cleaning cage
    // Feeding can be overwritten if animal has
    // the medical task: id = 1, description = kit feeding
    // this can be done by making feeding task id = 1

    // private HashMap<Integer, Task> medicalTasks;
    // Hashmap -> keys: animal ID, values: medical tasks

    /**
     * Animal Constructor
     * 
     * @param ID      - the Animal's ID
     * @param name    - the Animal's name/nickname
     * @param species - the species of the Animal
     */
    // public Animal(int ID, String name, String species);
    // public Animal(int ID, String name, String species) {
    // this.ID = ID;
    // this.NAME = name;
    // this.species = species;
    // this.standardTasks = new HashMap<Integer, Task>();
    // this.medicalTasks = new HashMap<Integer, Task>();
    // }

    /** Getters **/

    /**
     * Standard getter
     * 
     * @return the Animal name
     */
    public String getName();
    // public String getName() {
    // return this.NAME;
    // }

    /**
     * Standard getter
     * 
     * @return the standard tasks for the Animal
     */
    public HashMap<Integer, Task> getStandardTasks();
    // public HashMap<Integer, Task> getStandardTasks() {
    // return this.standardTasks;
    // }

    /**
     * Standard getter
     * 
     * @return the medical tasks for the Animal
     */
    public HashMap<Integer, Task> getMedicalTasks();
    // public HashMap<Integer, Task> getMedicalTasks() {
    // return this.medicalTasks;
    // }

}
