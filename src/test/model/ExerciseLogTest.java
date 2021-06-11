package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.ExerciseType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExerciseLogTest {

    ExerciseLog testLog;

    @BeforeEach
    public void setUp() {
        testLog = new ExerciseLog();
    }

    // Getter
    @Test
    public void testGetter() {
        assertEquals(testLog.exerciseList,testLog.getLog());
    }

    @Test
    public void addExerciseOne() {
        Exercise testRun = new Exercise(10,RUN);
        testLog.addExercise(testRun);

        assertEquals(1,testLog.exerciseList.size());
        assertTrue(testLog.exerciseList.contains(testRun));
    }

    @Test
    public void addExerciseMany() {
        Exercise testRun = new Exercise(10,RUN);
        Exercise testDance = new Exercise(10,DANCE);
        Exercise testWalk = new Exercise(10, WALK);
        testLog.addExercise(testRun);
        testLog.addExercise(testDance);
        testLog.addExercise(testWalk);

        assertEquals(3,testLog.exerciseList.size());
        assertTrue(testLog.exerciseList.contains(testRun));
        assertTrue(testLog.exerciseList.contains(testDance));
        assertTrue(testLog.exerciseList.contains(testWalk));

    }

    @Test
    public void testCalculateNone() {
        assertEquals(0,testLog.calculateCalorie());
    }

    @Test
    public void testCalculateOne() {
        Exercise testRun = new Exercise(10,RUN);
        testLog.addExercise(testRun);
        assertEquals(90,testLog.calculateCalorie());
    }

    @Test
    public void calculateMany() {
        Exercise testRun = new Exercise(10,RUN);
        Exercise testDance = new Exercise(10,DANCE);
        Exercise testWalk = new Exercise(10, WALK);
        testLog.addExercise(testRun);
        testLog.addExercise(testDance);
        testLog.addExercise(testWalk);
        assertEquals(195,testLog.calculateCalorie());
    }


}
