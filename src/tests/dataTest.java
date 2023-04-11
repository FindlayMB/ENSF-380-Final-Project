package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import schedule.builder.data.ScheduleItem;
import schedule.builder.data.SortByMaxWindow;

import org.junit.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class dataTest {

    @Test
    public void testConstructor() {
        // Test that the constructor sets all values correctly
        ScheduleItem item = new ScheduleItem(1, 2, 3, 4, 5);
        assertEquals(1, item.getAnimalID());
        assertEquals(2, item.getTaskID());
        assertEquals(3, item.getStartHour());
        assertEquals(4, item.getMaxWindow());
        assertEquals(5, item.getDuration());
    }
    
    @Test
    public void testGetAnimalID() {
        // Test that getAnimalID() returns the correct value
        ScheduleItem item = new ScheduleItem(1, 2, 3, 4, 5);
        assertEquals(1, item.getAnimalID());
    }
    
    @Test
    public void testGetTaskID() {
        // Test that getTaskID() returns the correct value
        ScheduleItem item = new ScheduleItem(1, 2, 3, 4, 5);
        assertEquals(2, item.getTaskID());
    }
    
    @Test
    public void testGetStartHour() {
        // Test that getStartHour() returns the correct value
        ScheduleItem item = new ScheduleItem(1, 2, 3, 4, 5);
        assertEquals(3, item.getStartHour());
    }
    
    @Test
    public void testGetMaxWindow() {
        // Test that getMaxWindow() returns the correct value
        ScheduleItem item = new ScheduleItem(1, 2, 3, 4, 5);
        assertEquals(4, item.getMaxWindow());
    }
    
    @Test
    public void testGetDuration() {
        // Test that getDuration() returns the correct value
        ScheduleItem item = new ScheduleItem(1, 2, 3, 4, 5);
        assertEquals(5, item.getDuration());
    }
    
    @Test
    public void testSetStartHour() {
        // Test that setStartHour() sets the start hour correctly
        ScheduleItem item = new ScheduleItem(1, 2, 3, 4, 5);
        item.setStartHour(6);
        assertEquals(6, item.getStartHour());
    }
    
    @Test
    public void testCompare() {
        // Test that the SortByMaxWindow comparator sorts the items correctly
        ScheduleItem item1 = new ScheduleItem(1, 2, 3, 4, 5);
        ScheduleItem item2 = new ScheduleItem(6, 7, 8, 9, 10);
        ScheduleItem item3 = new ScheduleItem(11, 12, 13, 14, 15);
        List<ScheduleItem> scheduleItems = new ArrayList<>();
        scheduleItems.add(item1);
        scheduleItems.add(item2);
        scheduleItems.add(item3);
        Collections.sort(scheduleItems, new SortByMaxWindow());
        Assert.assertEquals(4, scheduleItems.get(0).getMaxWindow());
        Assert.assertEquals(9, scheduleItems.get(1).getMaxWindow());
        Assert.assertEquals(14, scheduleItems.get(2).getMaxWindow());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStartHour() {
        // Test that an IllegalArgumentException is thrown when the start hour is invalid
        ScheduleItem item = new ScheduleItem(1, 2, 24, 4, 5);
        ScheduleItem item2 = new ScheduleItem(1, 2, -1, 4, 5);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMaxWindow() {
        // Test that an IllegalArgumentException is thrown when the max window is invalid
        ScheduleItem item = new ScheduleItem(1, 2, 3, 0, 5);
        ScheduleItem item2 = new ScheduleItem(1, 2, 3, -4, 5);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDuration() {
        // This should throw an exception since duration is negative
        ScheduleItem item = new ScheduleItem(1, 2, 3, 4, -1);
    }
}