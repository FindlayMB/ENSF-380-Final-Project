package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import schedule.builder.data.ScheduleItem;
import schedule.animals.Animal;
import schedule.animals.Beaver;
import schedule.animals.Coyote;
import schedule.animals.Fox;
import schedule.animals.Porcupine;
import schedule.animals.Raccoon;

public class animalTest {

    // This test checks that the getID method of the Animal class returns the correct ID
    @Test
    public void testGetID() {
        Animal animal = new Animal(1, "Fluffy", "Cat");
        assertEquals(1, animal.getID());
    }

    // This test checks that the getName method of the Animal class returns the correct name
    @Test
    public void testGetName() {
        Animal animal = new Animal(1, "Fluffy", "Cat");
        assertEquals("Fluffy", animal.getName());
    }

    // This test checks that the getSpecies method of the Animal class returns the correct species
    @Test
    public void testGetSpecies() {
        Animal animal = new Animal(1, "Fluffy", "Cat");
        assertEquals("Cat", animal.getSpecies());
    }

    // This test checks that the getKitStatus method of the Animal class returns false when the kitStatus variable is false
    @Test
    public void testGetKitStatus() {
        Animal animal = new Animal(1, "Fluffy", "Cat");
        assertFalse(animal.getKitStatus());
    }
    
    // This test checks that the setKitStatus method of the Animal class sets the kitStatus variable to true
    @Test
    public void testSetKitStatus() {
        Animal animal = new Animal(1, "Fluffy", "Cat");
        animal.setKitStatus();
        assertTrue(animal.getKitStatus());
    }

    // This test checks that the feeding method of the Animal class returns the correct ScheduleItem object
    @Test
    public void testFeeding() {
        Animal animal = new Animal(1, "Fluffy", "Cat");
        ScheduleItem expected = new ScheduleItem(1, 0, 0, 3, 5);
        assertEquals(expected.getAnimalID(), animal.feeding().getAnimalID());
        assertEquals(expected.getTaskID(), animal.feeding().getTaskID());
        assertEquals(expected.getStartHour(), animal.feeding().getStartHour());
        assertEquals(expected.getMaxWindow(), animal.feeding().getMaxWindow());
        assertEquals(expected.getDuration(), animal.feeding().getDuration());
    }

    // This test checks that the feeding method of the Beaver class returns the correct ScheduleItem object
    @Test
    public void testFeedingB() {
        Beaver beaver = new Beaver(1, "Bob", "Castor canadensis");
        ScheduleItem feedingItem = beaver.feeding();
        assertEquals(1, feedingItem.getAnimalID());
        assertEquals(0, feedingItem.getTaskID());
        assertEquals(8, feedingItem.getStartHour());
        assertEquals(3, feedingItem.getMaxWindow());
        assertEquals(5, feedingItem.getDuration());
    }

    // This test checks that the feeding method of the Coyote class returns the correct ScheduleItem object
    @Test
    public void testFeedingC() {
        Coyote coyote = new Coyote(1, "Wiley", "Coyote");
        ScheduleItem feedingScheduleItem = coyote.feeding();
        assertEquals(1, feedingScheduleItem.getAnimalID());
        assertEquals(0, feedingScheduleItem.getTaskID());
        assertEquals(19, feedingScheduleItem.getStartHour());
        assertEquals(3, feedingScheduleItem.getMaxWindow());
        assertEquals(5, feedingScheduleItem.getDuration());
    }

    // This test checks that the feeding method of the Fox class returns the correct ScheduleItem object
    @Test
    public void testFeedingF() {
        Fox fox = new Fox(1, "Wiley", "Coyote");
        ScheduleItem feedingScheduleItem = fox.feeding();
        assertEquals(1, feedingScheduleItem.getAnimalID());
        assertEquals(0, feedingScheduleItem.getTaskID());
        assertEquals(0, feedingScheduleItem.getStartHour());
        assertEquals(3, feedingScheduleItem.getMaxWindow());
        assertEquals(5, feedingScheduleItem.getDuration());
    }

    // This test checks that the feeding method of the Porcupine class returns the correct ScheduleItem object
    @Test
    public void testFeedingP() {
        Porcupine porcupine = new Porcupine(1, "Porky", "Porcupine");
        ScheduleItem feedingScheduleItem = porcupine.feeding();
        assertEquals(1, feedingScheduleItem.getAnimalID());
        assertEquals(0, feedingScheduleItem.getTaskID());
        assertEquals(19, feedingScheduleItem.getStartHour());
        assertEquals(3, feedingScheduleItem.getMaxWindow());
        assertEquals(5, feedingScheduleItem.getDuration());
    }

    // This test checks that the feeding method of the Raccoon class returns the correct ScheduleItem object
    @Test
    public void testFeedingR() {
        Raccoon raccoon = new Raccoon(1, "Porky", "Raccoon");
        ScheduleItem feedingScheduleItem = raccoon.feeding();
        assertEquals(1, feedingScheduleItem.getAnimalID());
        assertEquals(0, feedingScheduleItem.getTaskID());
        assertEquals(0, feedingScheduleItem.getStartHour());
        assertEquals(3, feedingScheduleItem.getMaxWindow());
        assertEquals(5, feedingScheduleItem.getDuration());
    }

    // This test checks that an IllegalArgumentException is thrown when an Animal object is created with a negative ID
    @Test(expected = IllegalArgumentException.class)
    public void testAnimalConstructorWithNegativeID() {
        Animal animal = new Animal(-1, "Fluffy", "Cat");
    }

    // This test checks that an IllegalArgumentException is thrown when an Animal object is created with a null or empty name
    @Test(expected = IllegalArgumentException.class)
    public void testAnimalConstructorWithEmptyName() {
        Animal animal1 = new Animal(1, null, "Cat"); 
        Animal animal2 = new Animal(2, "", "Dog"); 
    }

    // This test checks that an IllegalArgumentException is thrown when an Animal object is created with a null or empty species
    @Test(expected = IllegalArgumentException.class)
    public void testAnimalConstructorWithEmptySpecies() {
        Animal animal1 = new Animal(1, "Fluffy", null);
        Animal animal2 = new Animal(2, "Fido", "");
    }
}
