package persistence;

import model.Exercise;
import model.ExerciseLog;
import model.ExerciseType;
import model.Pet;
import org.junit.jupiter.api.Test;
import ui.PetGame;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderUnableFind() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PetGame pg = reader.read();
            fail("Should not be able to read as such file don't exist");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testReaderEmptyGame() {
        JsonReader reader = new JsonReader("./data/testEmptyGame.json");
        try {
            PetGame pg = reader.read();
            Pet p = pg.getYourPet();
            ExerciseLog log = pg.getLog();
            int reward = pg.getRewardPoint();
            double cal = pg.getCal();
            assertEquals("test1",p.getName());
            assertEquals(0,p.getIntimacy());
            assertEquals(p.INITIAL_ENERGY, p.getEnergy());

            assertEquals(0,log.getLog().size());
            assertEquals(0,reward);
            assertEquals(0,cal);
        } catch (IOException e) {
            fail("There is a file and Exception shuold not be thrown");
        }
    }

    @Test
    public void testGeneralGame() {
        JsonReader reader = new JsonReader("./data/testGeneralGame.json");
        try {
            PetGame test = reader.read();
            Pet p = test.getYourPet();
            ExerciseLog log = test.getLog();
            int reward = test.getRewardPoint();
            double cal = test.getCal();

            assertEquals("test2",p.getName());
            assertEquals(12,p.getIntimacy());
            assertEquals(20, p.getEnergy());

            assertEquals(3,log.getLog().size());
            Exercise e1 = log.getLog().get(0);
            checkExercise(ExerciseType.RUN,135,e1);
            Exercise e2 = log.getLog().get(1);
            checkExercise(ExerciseType.DANCE,10,e2);
            Exercise e3 = log.getLog().get(2);
            checkExercise(ExerciseType.YOGA,7,e3);

            assertEquals(666,reward);
            assertEquals(777,cal);

        } catch (IOException e) {
            fail("There is a file with this name, Exception should not be throw");
        }
    }
}
