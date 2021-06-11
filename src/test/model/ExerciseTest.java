package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.ExerciseType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {

    Exercise testRun;
    Exercise testDance;
    Exercise testWalk;
    Exercise testYoga;
    Exercise testCleaning;
    Exercise testRopejumping;

    @BeforeEach
    public void setUp() {
        testRun = new Exercise(10,RUN);
        testDance = new Exercise(10,DANCE);
        testWalk = new Exercise(10, WALK);
        testYoga = new Exercise(10,YOGA);
        testCleaning = new Exercise(10,CLEANING);
        testRopejumping = new Exercise(10,ExerciseType.ROPEJUMPING);

    }

    @Test
    public void testGetter() {
        assertEquals(10,testRun.getTime());
        assertEquals(RUN,testRun.getType());
        assertEquals(90,testRun.getCalorie());
    }

    @Test
    public void testCalculateCal() {
        assertEquals(90,testRun.calculateCalorie());
        assertEquals(60,testDance.calculateCalorie());
        assertEquals(45,testWalk.calculateCalorie());
        assertEquals(40,testYoga.calculateCalorie());
        assertEquals(45,testCleaning.calculateCalorie());
        assertEquals(45,testRopejumping.calculateCalorie());

    }
    @Test
    public void testRopeJumping() {
        Exercise jump = new Exercise(10, ExerciseType.ROPEJUMPING);
        assertEquals(45,jump.calculateCalorie());
    }

    @Test
    public void testDacne() {
        assertEquals(60,testDance.calculateCalorie());
    }

    @Test
    public void testWalk() {
        assertEquals(45,testWalk.calculateCalorie());
    }

    @Test
    public void testYoga() {
        assertEquals(40,testYoga.calculateCalorie());
    }

    @Test
    public void testCleaning() {
        assertEquals(45,testCleaning.calculateCalorie());
    }

    @Test
    public void testRun() {
        assertEquals(90,testRun.calculateCalorie());
    }
}
