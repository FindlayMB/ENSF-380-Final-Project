/**
 * 
 * @author Findlay Brown
 * @author Parsa Kargari
 * @author Aly Mohammed
 * @author Lujaina Eldelebshany
 * @version 3.5.1
 * @since 2023-04-10
 */

package schedule.builder;

import java.util.*;
import java.io.*;
import javax.swing.*;

import java.awt.Dimension;

import schedule.animals.*;
import schedule.builder.data.*;

import schedule.tasks.*;

public class ScheduleBuilder extends JFrame {

    private DataImport dataImport = new DataImport();

    private HashMap<Integer, ArrayList<ScheduleItem>> schedule = new HashMap<Integer, ArrayList<ScheduleItem>>();
    // key: hour, value: ArrayList of tasks during key hour

    private HashMap<Integer, HashMap<String, Boolean>> foodPrepFeeding = new HashMap<Integer, HashMap<String, Boolean>>();

    private HashMap<Integer, Integer> timeAvailability = new HashMap<Integer, Integer>(24);
    private HashMap<Integer, Integer> maxTimeAvailability = new HashMap<Integer, Integer>(24);
    // private HashMap<Integer, ScheduleItem> schedule = new HashMap<Integer,
    // ScheduleItem>();
    // key: hour

    private HashMap<Integer, Animal> animalHashMap = new HashMap<Integer, Animal>();
    private HashMap<Integer, Task> taskHashMap = new HashMap<Integer, Task>();
    private ArrayList<Treatment> treatmentList = new ArrayList<Treatment>();

    public ScheduleBuilder(String username, String password) throws IllegalArgumentException {
        super("Schedule Builder");
        dataImport.createConnection(username, password);

        // checkGui();
        setSize(500, 300);
        setLocation(730, 390);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);

        try {
            animalHashMap = dataImport.getAnimalsTable();
            taskHashMap = dataImport.getTasksTable();
            treatmentList = dataImport.getTreatmentsTable();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        // Cage Cleaning task for coyote, fox, raccoon, and beaver
        taskHashMap.put(-2, new Task(-2, "Cage Cleaning", 5, 24));

        // Cage Cleaning task for porcupine
        taskHashMap.put(-1, new Task(-1, "Porcupine Cage Cleaning", 10, 24));

        for (int i = 0; i < 24; i++) {
            timeAvailability.put(i, 60);
            maxTimeAvailability.put(i, 60);
            schedule.put(i, null);
        }
        this.setVisible(false);
        generateSchedule();
    }

    /**
     * Tries to add ScheduleItem to schedule
     * 
     * @param startHour
     * @param maxWindow
     * @param duration
     * @return boolean -> returns true if database connection was succesful
     *         otherwise returns false
     */
    public boolean addToSchedule(ScheduleItem scheduleItem) {
        int duration = scheduleItem.getDuration();
        int priority = scheduleItem.getMaxWindow();

        for (int i = scheduleItem.getStartHour(); i < scheduleItem.getStartHour() + scheduleItem.getMaxWindow(); i++) {
            if (scheduleItem.getTaskID() == 0) {
                if (animalHashMap.get(scheduleItem.getAnimalID()) instanceof Fox) {
                    HashMap<String, Boolean> foodPrep = new HashMap<String, Boolean>();
                    if (foodPrepFeeding.get(i) != null) {
                        foodPrep = foodPrepFeeding.get(i);
                        if (!foodPrepFeeding.get(i).get("fox")) {
                            foodPrep.put("fox", true);
                            foodPrepFeeding.put(i, foodPrep);
                            duration = duration + 5;
                        }
                    } else {
                        foodPrep.put("fox", true);
                        foodPrepFeeding.put(i, foodPrep);
                        duration = duration + 5;
                    }
                } else if (animalHashMap.get(scheduleItem.getAnimalID()) instanceof Coyote) {
                    HashMap<String, Boolean> foodPrep = new HashMap<String, Boolean>();
                    if (foodPrepFeeding.get(i) != null) {
                        foodPrep = foodPrepFeeding.get(i);
                        if (!foodPrepFeeding.get(i).get("coyote")) {
                            foodPrep.put("coyote", true);
                            foodPrepFeeding.put(i, foodPrep);
                            duration = duration + 10;
                        }
                    } else {
                        foodPrep.put("coyote", true);
                        foodPrepFeeding.put(i, foodPrep);
                        duration = duration + 10;
                    }
                }
            }
            int newTimeAvailability = timeAvailability.get(i) - duration;

            if (newTimeAvailability >= 0) {
                ArrayList<ScheduleItem> temp = new ArrayList<ScheduleItem>();
                if (schedule.get(i) == null) {
                    temp.add(scheduleItem);
                } else {
                    temp = schedule.get(i);
                    temp.add(scheduleItem);
                }
                schedule.put(i, temp);
                timeAvailability.put(i, newTimeAvailability);

                return true;
            } else {
                priority--;
                if (priority <= 0) {
                    return false;
                }
            }
        }
        System.out.println("Went beyond for loop");
        System.out.print(scheduleItem.getMaxWindow() + "\t");
        System.out.print(scheduleItem.getAnimalID() + "\t");
        System.out.print(scheduleItem.getTaskID() + "\t");
        System.out.print(scheduleItem.getStartHour() + "\t");
        System.out.println(scheduleItem.getDuration() + "\n");
        // if (!addToSchedule(startHour, task.getMaxWindow(), task.getMaxWindow(),
        // animalID)) {
        // System.out.println("Failed to add to schedule!");
        // }
        return false;
    }

    public void generateSchedule() {
        String[] options = { "NO", "YES" };
        int[] possibleTimes = new int[timeAvailability.size()];
        ArrayList<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();
        scheduleItems.addAll(addMedicalTasks());
        scheduleItems.addAll(addCageCleaning());
        scheduleItems.addAll(addFeeding());

        // Sort scheduleItems by maxWindow in ascending order
        Collections.sort(scheduleItems, new SortByMaxWindow());
        System.out.println("\t\tMW\tAI\tTI\tSH\tDU");
        for (ScheduleItem scheduleItem : scheduleItems) {

            if (addToSchedule(scheduleItem)) {
                System.out.print("Added:\t\t");
            } else {
                int hourForVol = -1;
                for (int i = scheduleItem.getStartHour(); i < scheduleItem.getStartHour()
                        + scheduleItem.getMaxWindow(); i++) {
                    if (maxTimeAvailability.get(i) == 60) {
                        hourForVol = i;
                        break;
                    }
                }

                int selectedValue = JOptionPane.showOptionDialog(rootPane,
                        "A backup volunteer is required for" + hourForVol, "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, options, options[0]);
                if (selectedValue == 1) {
                    maxTimeAvailability.put(hourForVol, 120);
                    timeAvailability.put(hourForVol, timeAvailability.get(hourForVol) + 60);
                    addToSchedule(scheduleItem);

                } else {
                    String timeshown = "";
                    int userinput = 0;
                    String stringinput = "";
                    boolean validinput = false;

                    for (int i : timeAvailability.keySet()) {
                        if (timeAvailability.get(i) == 60) {
                            possibleTimes[i] = i;
                            timeshown += i + " ";
                        }
                    }

                    // do{
                    stringinput = JOptionPane.showInputDialog(rootPane,
                            "Please reschedule the task to a different time" + timeshown, userinput);
                    userinput = Integer.parseInt(stringinput);

                    for (int i : possibleTimes) {

                        if (i == userinput) {
                            System.out.println("it got here");
                            validinput = true;
                            break;
                        }
                    }

                    if (validinput == true) {
                        System.out.println("this is the new table");
                        scheduleItem.setStartHour(userinput);
                        addToSchedule(scheduleItem);
                        dataImport.setValue(scheduleItem.getTreatmentID(), userinput);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Invalid input");

                    }
                    // }while(validinput == false);

                }
                // checkGui(scheduleItem);
                // this.setVisible(false);
                System.out.print("Not added:\t");
            }
            System.out.print(scheduleItem.getMaxWindow() + "\t");
            System.out.print(scheduleItem.getAnimalID() + "\t");
            System.out.print(scheduleItem.getTaskID() + "\t");
            System.out.print(scheduleItem.getStartHour() + "\t");
            System.out.println(scheduleItem.getDuration() + "\n");
        }

        System.out.println("\nTime Availability");
        for (int i : timeAvailability.keySet()) {
            // System.out.println(i);
            // if (schedule.get(i) != null) {
            // for (ScheduleItem item : schedule.get(i)) {
            // System.out.println(item.getTaskID());
            // }
            // }
            System.out.println(i + "\t" + timeAvailability.get(i) + "\t" +
                    maxTimeAvailability.get(i));
        }
        generateTextFile();
        generateGUIschedule();
    }

    public ArrayList<ScheduleItem> addMedicalTasks() {
        ArrayList<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();
        for (Treatment treatment : treatmentList) {
            // Add all medical tasks to a list
            int animalID = treatment.getAnimalID();
            int taskID = treatment.getTaskID();
            if (taskID == 1) {
                // Update kitStatus if the task is kit feeding
                animalHashMap.get(animalID).setKitStatus();
            }
            int startHour = treatment.getStartHour();
            int maxWindow = taskHashMap.get(taskID).getMaxWindow();
            int duration = taskHashMap.get(taskID).getDuration();
            ScheduleItem scheduleItem = new ScheduleItem(animalID, taskID,
                    startHour, maxWindow, duration, treatment.getTreatmentID());
            scheduleItems.add(scheduleItem);
        }
        return scheduleItems;
    }

    // Add cage cleaning task with a
    // maxWindow = 24, startHour = 0
    // normal cleaning -> taskID = -1, duration = 5
    // porcupine cleaning -> taskID = -2, duration = 10
    public ArrayList<ScheduleItem> addCageCleaning() {
        ArrayList<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();
        for (int animalID : animalHashMap.keySet()) {
            ScheduleItem cleaning;
            if (animalHashMap.get(animalID) instanceof Porcupine) {
                cleaning = new ScheduleItem(animalID, -1, 0, 24, 10);
            } else {
                cleaning = new ScheduleItem(animalID, -2, 0, 24, 5);
            }
            scheduleItems.add(cleaning);
        }
        return scheduleItems;
    }

    // Add feeding tasks for animals that don't have a food prep time
    // maxWindow and startHour from certain animals, taskID = 0
    public ArrayList<ScheduleItem> addFeeding() {
        ArrayList<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();
        for (int animalID : animalHashMap.keySet()) {
            if (animalHashMap.get(animalID).getKitStatus()) {
                continue;
            }
            ScheduleItem scheduleItem = animalHashMap.get(animalID).feeding();
            scheduleItems.add(scheduleItem);
        }

        return scheduleItems;
    }

    public String formatScheduleItem(ScheduleItem scheduleItem) {
        String name = animalHashMap.get(scheduleItem.getAnimalID()).getName();
        String taskDescription;
        if (scheduleItem.getTaskID() == 0) {
            taskDescription = "Feeding";
        } else {
            taskDescription = taskHashMap.get(scheduleItem.getTaskID()).getDescription();
        }
        int duration = scheduleItem.getDuration();
        return "--" + taskDescription + " (" + name + ")    " + duration + " mins";
    }

    public void generateTextFile() {
        String fileName = "Schedule.txt";
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int i = 0; i < 24; i++) {
                if (i < 10) {
                    writer.write("The Time is : 0" + i + ":00\n");
                } else {
                    writer.write("The Time is : " + i + ":00\n");
                }
                ArrayList<String> itemsInHour = new ArrayList<String>();
                if (schedule.get(i) != null) {
                    for (ScheduleItem item : schedule.get(i)) {
                        itemsInHour.add(formatScheduleItem(item));
                    }
                    for (String item : itemsInHour) {
                        writer.write(item + "\n");
                    }
                }
                writer.write("----------------------------------------------------------" + "\n");
            }
            writer.close();
            // System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            // System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void generateGUIschedule() {
        String guiOutput = "";
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                guiOutput += "The Time is : 0" + i + ":00\n";
            } else {
                guiOutput += "The Time is : " + i + ":00\n";
            }
            ArrayList<String> itemsInHour = new ArrayList<String>();
            if (schedule.get(i) != null) {
                for (ScheduleItem item : schedule.get(i)) {
                    itemsInHour.add(formatScheduleItem(item));
                }
                for (String item : itemsInHour) {
                    guiOutput += item + "\n";
                }
            }
            guiOutput += "----------------------------------------------------------" + "\n";
        }

        JTextArea textArea = new JTextArea(guiOutput);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        JOptionPane.showMessageDialog(rootPane, scrollPane, "Schedule", JOptionPane.DEFAULT_OPTION);
    }

}
