package schedule.builder;

import java.util.*;
import java.awt.EventQueue;

import schedule.animals.*;
import schedule.tasks.*;

public class ScheduleBuilder {
    /*
     * For each animal in TABLE ANIMALS
     * create Animal object
     * 
     * 
     */

    private static DataImport dataImport = new DataImport();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new GuiScheduler(dataImport).setVisible(true);
        });
    }
}
