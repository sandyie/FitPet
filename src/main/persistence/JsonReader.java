package persistence;

import model.Exercise;
import model.ExerciseLog;
import model.ExerciseType;
import model.Pet;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.PetGame;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// The JsonReader class include methods that help read a saved file of PetGame (in JsonObject) and parse the file
// to PetGame

public class JsonReader {
    private String dataSite;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.dataSite = source;
    }

    // EFFECTS: reads PetGame from file and returns it as a PetGame;
    // throws IOException if an error occurs reading data from file
    public PetGame read() throws IOException {
        String jsonData = readFile(dataSite);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePetGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it as string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses PetGame field from JSON object and returns it as a new PetGame()
    private PetGame parsePetGame(JSONObject jsonObject) {
        Pet savedPet = parsedPet(jsonObject);
        ExerciseLog savedLog = parseExercises(jsonObject);
        double savedCalorie = parseTotalCal(jsonObject);
        int savedReward = parseTotalReward(jsonObject);
        PetGame readGame = new PetGame(savedPet,savedLog,savedReward,savedCalorie);

        return readGame;
    }

    // EFFECT: parse the pet
    private Pet parsedPet(JSONObject jsonObject) {
        JSONObject getPetObject = jsonObject.getJSONObject("Pet");
        String petName = getPetObject.getString("name");
        int petEnergy = getPetObject.getInt("EnergyLevel");
        int intimate = getPetObject.getInt("Intimacy");
        Pet savedPet = new Pet(petName,petEnergy,intimate);
        return savedPet;
    }

    // EFFECT: parse the exercise log saved in the data file
    private ExerciseLog parseExercises(JSONObject jsonObject) {
        JSONArray savedExercise = jsonObject.getJSONArray("Exercises");
        ExerciseLog savedLog = new ExerciseLog();

        for (Object json : savedExercise) {
            JSONObject nextThingy = (JSONObject) json;
            addExercise(savedLog, nextThingy);
        }
        return savedLog;
    }

    // EFFECT: parse each exercise in the saved logs into a exercise log (an array list)
    private void addExercise(ExerciseLog log,JSONObject next) {
        int time = next.getInt("Time");
        ExerciseType type = ExerciseType.valueOf(next.getString("Exercise type"));
        Exercise exercise = new Exercise(time, type);
        log.addExercise(exercise);
    }

    // EFFECT: parse the specific int of calorie of the game status
    private double parseTotalCal(JSONObject jsonObject) {
        double totalCal = jsonObject.getInt("Total calorie");
        return totalCal;
    }


    // EFFECT: parse the specific int of saved reward point
    private int parseTotalReward(JSONObject jsonObject) {
        int rewardPoint = jsonObject.getInt("Reward points");
        return rewardPoint;
    }



}


