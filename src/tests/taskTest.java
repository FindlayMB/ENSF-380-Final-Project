package tests;

import org.junit.*;

import schedule.tasks.Task;
import schedule.tasks.Treatment;

import static org.junit.Assert.*;

// This class contains JUnit tests for the Task and Treatment classes.
public class taskTest {

    // This test verifies that the getTaskID() method returns the correct value.
    @Test
    public void testGetTaskID() {
        Task task = new Task(1, "Task 1", 10, 20);
        assertEquals(1, task.getTaskID());
    }

    // This test verifies that the getDescription() method returns the correct value.
    @Test
    public void testGetDescription() {
        Task task = new Task(1, "Task 1", 10, 20);
        assertEquals("Task 1", task.getDescription());
    }

    // This test verifies that the getMaxWindow() method returns the correct value.
    @Test
    public void testGetMaxWindow() {
        Task task = new Task(1, "Task 1", 10, 20);
        assertEquals(20, task.getMaxWindow());
    }

    // This test verifies that the getDuration() method returns the correct value.
    @Test
    public void testGetDuration() {
        Task task = new Task(1, "Task 1", 10, 20);
        assertEquals(10, task.getDuration());
    }

    // This test verifies that the Task constructor creates a non-null object.
    @Test
    public void testConstructor() {
        Task task = new Task(1, "Task 1", 10, 20);
        assertNotNull(task);
    }

    // This test verifies that the Task constructor throws an IllegalArgumentException if the duration is negative.
    @Test
    public void testConstructorWithNegativeDuration() {
        try {
            Task task = new Task(1, "Task 1", -10, 20);
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            // do nothing
        }
    }
    
    // This test verifies that the Task constructor throws an IllegalArgumentException if the max window is negative.
    @Test
    public void testConstructorWithNegativeMaxWindow() {
        try {
            Task task = new Task(1, "Task 1", 10, -20);
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            // do nothing
        }
    }

    // This test verifies that the getters for the Treatment class return the correct values.
    @Test
    public void testGetters() {
        Treatment t = new Treatment(1, 2, 3, 4);
        assertEquals(1, t.getTreatmentID());
        assertEquals(2, t.getAnimalID());
        assertEquals(3, t.getTaskID());
        assertEquals(4, t.getStartHour());
    }

    // This test verifies that the setStartHour() method in the Treatment class correctly sets the start hour.
    @Test
    public void testSetStartHour() {
        Treatment t = new Treatment(1, 2, 3, 4);
        t.setStartHour(5);
        assertEquals(5, t.getStartHour());
    }

    // This test verifies that an IllegalArgumentException is thrown if a negative start hour is used in the Treatment constructor.
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeStartHour() {
        Treatment t = new Treatment(1, 2, 3, -4);
    }

    // This test verifies that the setStartHour() method in the Treatment class works correctly for boundary cases.
    @Test
    public void testSetAndGetStartHourBoundary() {
        Treatment t = new Treatment(1, 2, 3, 4);
        t.setStartHour(0);
        assertEquals(0, t.getStartHour());
        t.setStartHour(23);
        assertEquals(23, t.getStartHour());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetStartHourTooLow() {
        Treatment t = new Treatment(1, 2, 3, 4);
        t.setStartHour(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetStartHourTooHigh() {
        Treatment t = new Treatment(1, 2, 3, 4);
        t.setStartHour(24);
    }

    
}
