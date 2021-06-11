package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// This ExerciseLog class allows user to collect all exercise they enter so far. Each exercise enter by the user can be
// added to this collection that can be use for further process/ analysis

public class ExerciseLog {
    public List<Exercise> exerciseList;


    // EFFECT: create new ExerciseLog
    public ExerciseLog() {
        exerciseList = new ArrayList<Exercise>();
    }


    // MODIFIES: this
    // EFFECTS : add new exercise into log
    public void addExercise(Exercise newActicity) {
        exerciseList.add(newActicity);
    }

    // EFFECTS : return the list of exercise
    public List<Exercise> getLog() {
        return exerciseList;
    }



    // EFFECT: calculate total calories burned in total
    public double calculateCalorie() {
        double totalCalorie = 0.0;
        for (Exercise e : exerciseList) {
            totalCalorie += e.getCalorie();
        }
        return totalCalorie;
    }

    // EFFECT: write the entire exerciseList as a JSONArray
    public JSONArray recordLog() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : exerciseList) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;

    }







}
