package persistence;

import model.Exercise;
import model.ExerciseType;
import model.Pet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkPet(String name, int energy, int intimacy, Pet pet) {
        assertEquals(name,pet.getName());
        assertEquals(energy,pet.getEnergy());
        assertEquals(intimacy,pet.getIntimacy());
    }

    protected void checkExercise(ExerciseType type, int time, Exercise exercise) {
        assertEquals(type, exercise.getType());
        assertEquals(time,exercise.getTime());
    }


}
