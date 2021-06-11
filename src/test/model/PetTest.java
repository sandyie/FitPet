package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test for Pet

public class PetTest {

    Pet testPet;
    int INITIAL_ENERGY = 12;

    @BeforeEach
    public void setUp() {
        testPet = new Pet("sandy");
    }

    @Test
    public void testGetter() {
        assertEquals("sandy",testPet.getName());
        assertEquals(INITIAL_ENERGY,testPet.getEnergy());
        assertEquals(0,testPet.getIntimacy());
        testPet.setName("a");
        assertEquals("a",testPet.getName());
    }

    @Test
    public void feedNoneTest() {
        testPet.feed(0);
        assertEquals(INITIAL_ENERGY,testPet.getEnergy());
    }

    @Test
    public void feedManyTest() {
        testPet.feed(100);
        assertEquals(100+INITIAL_ENERGY,testPet.getEnergy());
    }

    @Test
    public void playNone() {
        testPet.play(0);
        assertEquals(0,testPet.getIntimacy());
    }

    @Test
    public void playMany() {
        testPet.play(100);
        assertEquals(20,testPet.getIntimacy());
    }

    @Test
    public void playLessThanOneIntimacy() {
        testPet.play(2);
        assertEquals(0,testPet.getIntimacy());
    }

    @Test
    public void decreaseEnergyPointNone() {
        testPet.decreaseEnergy(0);
        assertEquals(INITIAL_ENERGY,testPet.getEnergy());
    }

    @Test
    public void decreaseEnergyPointOne() {
        testPet.decreaseEnergy(10);
        assertEquals(INITIAL_ENERGY - 10,testPet.getEnergy());
    }

    @Test
    public void decreaseEnergyPointAll() {
        testPet.decreaseEnergy(INITIAL_ENERGY);
        assertEquals(INITIAL_ENERGY - INITIAL_ENERGY,testPet.getEnergy());
    }

    @Test
    public void testDie() {
        testPet.decreaseEnergy(INITIAL_ENERGY);
        assertTrue(testPet.petDie());

    }

    @Test
    public void testNotDie() {
        testPet.decreaseEnergy(1);
        assertFalse(testPet.petDie());

    }


}
