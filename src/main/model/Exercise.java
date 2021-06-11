package model;

// The exercise class allow user to enter a new exercise and the duration of this exercise; User can only choose from
// the given types of exercise; Calories will be calculated according to duration and type

import org.json.JSONObject;
import persistence.Writable;

public class Exercise implements Writable {

    private double calorie;
    private int time; // in minute
    private ExerciseType type;

    // REQUIRE: time >= 0;
    // MODIFIES: this
    // EFFECT : create new exercise with given duration time and type
    public Exercise(int time, ExerciseType type) {
        this.time = time;
        this.type = type;
        this.calorie = calculateCalorie();
    }

    // Getter
    // EFFECT: return time of this exervise
    public int getTime() {
        return this.time;
    }

    // EFFECT: return energy burned for the exercise
    public double getCalorie() {
        return this.calorie;
    }

    // EFFECT: return type of the exercise
    public ExerciseType getType() {
        return this.type;
    }

    // MODIFIES: this
    // EFFECT: calculate the total calorie burned for this exercise
    public double calculateCalorie() {
        double calorie = 0;
        if (getType().equals(ExerciseType.RUN)) {
            calorie =  (this.getTime() * 9);
        } else if (getType().equals(ExerciseType.DANCE)) {
            calorie = (this.getTime() * 6);
        } else if (getType().equals(ExerciseType.WALK)) {
            calorie = (this.getTime() * 4.5);
        } else if (getType().equals(ExerciseType.YOGA)) {
            calorie = (this.getTime() * 4);
        } else if (getType().equals(ExerciseType.CLEANING)) {
            calorie = (this.getTime() * 4.5);
        } else {
            calorie = (this.getTime() * 4.5);
        }
        return calorie;
    }

    // EFFECT: write each exercises as a JSONobject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Exercise type", type);
        json.put("Time",this.time);
        return json;
    }
}
