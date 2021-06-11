package persistence;

import model.Exercise;
import model.ExerciseLog;
import model.ExerciseType;
import model.Pet;
import org.junit.jupiter.api.Test;
import ui.PetGame;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    public void testWriterInvalidFile() {
        try {
            Pet testPET = new Pet("test1");
            ExerciseLog testLog = new ExerciseLog();
            PetGame pg = new PetGame(testPET,testLog,1,0);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testWriterEmptyGameFile() {
        try {
            Pet testPET = new Pet("test1");
            ExerciseLog testLog = new ExerciseLog();
            PetGame pg = new PetGame(testPET,testLog,0,0);
            JsonWriter writer = new JsonWriter("./data/emptyWriter.json");
            writer.open();
            writer.write(pg);
            writer.close();

            JsonReader reader = new JsonReader("./data/emptyWriter.json");
            pg = reader.read();
            testPET = pg.getYourPet();
            testLog = pg.getLog();
            assertEquals("test1", testPET.getName());
            assertEquals(0, testLog.getLog().size());
        } catch (FileNotFoundException e) {
            fail("Exception should not be thrown");
        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testWriterGeneralFile() {
        try {
            Pet testPET = new Pet("test2",10,7);
            ExerciseLog testLog = new ExerciseLog();
            Exercise e1 = new Exercise(10, ExerciseType.WALK);
            Exercise e2 = new Exercise(11,ExerciseType.RUN);
            testLog.addExercise(e1);
            testLog.addExercise(e2);
            double cal = testLog.calculateCalorie();
            PetGame pg = new PetGame(testPET,testLog,777,cal);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFile.json");
            writer.open();
            writer.write(pg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFile.json");
            pg = reader.read();
            testPET = pg.getYourPet();
            testLog = pg.getLog();
            checkPet("test2",10,7,testPET);
            assertEquals(2, testLog.getLog().size());
            Exercise f1 = testLog.getLog().get(0);
            checkExercise(ExerciseType.WALK,10,f1);
            Exercise f2 = testLog.getLog().get(1);
            checkExercise(ExerciseType.RUN,11,f2);
            assertEquals(777,pg.getRewardPoint());
            assertEquals(cal,pg.getCal());
        } catch (FileNotFoundException e) {
            fail("Exception should not be thrown");
        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }


}
