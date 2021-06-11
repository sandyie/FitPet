package ui;

//import com.sun.xml.internal.ws.policy.spi.PolicyAssertionValidator;
import model.*;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static model.ExerciseType.*;
import static model.FoodType.*;

// PetGame class include method and field for operating the game (ui) as well as setting up the game

public class PetGame implements Writable {
    private static final String GREETING = "Nice to meet you! I am ";
    private static final String FEED = "Feed";
    private static final String PLAY = "Play";
    private static final String STATUS = "Status";
    private static final String ENTER = "Exercise";
    private static final String EAT = "Meal";
    private static final String PRINT = "Record";
    private static final String QUIT = "Quit";
    private static final String SAVE = "Save";
    private static final String RELOAD = "Reload";
    private static int CANDY = 50;
    private static int CAKE = 100;
    private static int REWARD_PER_MINUTE_PLAY = 30;
    private static int POINT_PER_CALORIE = 1;
    private static int PET_ENERGY_DECREASE = 200;

    private static final String JSON_STORE = "./data/savedgame.json";

    private Scanner input;
    private boolean runPet;
    private Pet yourPet;
    private ExerciseLog fitnessLog;
    private int rewardPoint;
    private double calorie;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // MODIFIES: this
    // EFFECT: initialize and set up game document
    public PetGame() {
        input = new Scanner(System.in);
        runPet = true;
        System.out.println("What's the name of your pet?; please keep it to one word length");
        String name = input.next();
        yourPet = new Pet(name);
        System.out.println(GREETING + yourPet.getName());
        fitnessLog = new ExerciseLog();
        rewardPoint = 0;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        init();
    }

    // EFFECT: constructor for PetGame, mainly used for reload only
    public PetGame(Pet savedPet,ExerciseLog savedLog, int savedReward, double savedCal) {
        this.yourPet = savedPet;
        this.fitnessLog = savedLog;
        this.rewardPoint = savedReward;
        this.calorie = savedCal;
    }

    // EFFECT: get reward point
    public int getRewardPoint() {
        return this.rewardPoint;
    }

    // EFFECT: get yourPet
    public Pet getYourPet() {
        return this.yourPet;
    }

    // EFFECT: get fitnessLog
    public ExerciseLog getLog() {
        return this.fitnessLog;
    }

    // EFFECT: get totalCalorie
    public double getCal() {
        return this.calorie;
    }



    // EFFECT: start the game and keep running before user quit
    public void init() {
        System.out.println("Welcome to FitPet");
        runPet = true;
        while (runPet) {
            getInstruction();
        }
    }


    // EFFECT: print option for instruction
    private void printOption() {
        System.out.println("Please choose from above:");
        System.out.println("enter " + FEED + " to feed pet");
        System.out.println("enter " + PLAY + " to play with pet");
        System.out.println("enter " + ENTER + " to enter new record");
        System.out.println("enter " + STATUS + " to view pet's status");
        System.out.println("enter " + EAT + " to enter new record");
        System.out.println("enter " + PRINT + " to print fitness log");
        System.out.println("enter " + SAVE + " to save game");
        System.out.println("enter " + RELOAD + " to reload game");
        System.out.println("enter " + QUIT + " to stop game");
    }


    // EFFECT: determine and excecute what to do next
    public void getInstruction() {
        printOption();
        String instruction = input.next();
        if (instruction.equals(FEED)) {
            feedPet();
        } else if (instruction.equals(PLAY)) {
            playWithPet();
        } else if (instruction.equals(ENTER)) {
            enterNewExercise();
        } else if (instruction.equals(STATUS)) {
            getStatus();
        } else if (instruction.equals(EAT)) {
            recordFood();
        } else if (instruction.equals(PRINT)) {
            printLog();
        } else if (instruction.equals(QUIT)) {
            stopGame();
        } else if (instruction.equals(SAVE)) {
            saveGameStatus();
        } else if (instruction.equals(RELOAD)) {
            loadGameStatus();
        } else {
            printError();
        }
    }

    // MODIFIES: this
    // EFFECTS: feed the pet using reward point;
    public void feedPet() {
        System.out.println("What do you want to feed?");
        System.out.println("Select from cake or candy");
        String food = input.next();
        deductPoint(food);

        System.out.println("");

        checkAlive();
    }

    // MODIFIES: this
    // EFFECTS: deduct reward point to feed pet, print error message if reward point > point require to feed
    private void deductPoint(String food) {
        if (food.equals("cake")) {
            if (this.getRewardPoint() >= CAKE) {
                this.rewardPoint -= CAKE;
                yourPet.feed(2);
                System.out.println("Thank you for your cake!");
            } else {
                needMoreReward();
            }
        } else if (food.equals("candy")) {
            if (this.getRewardPoint() >= CANDY) {
                this.rewardPoint -= CANDY;
                yourPet.feed(1);
                System.out.println("Thank you for your candy!");
            } else {
                needMoreReward();
            }
        } else {
            feedPet();
        }
    }

    // EFFECTS: print error message when don't have enought reward point for feed/ play
    private void needMoreReward() {
        System.out.println("Please exercise more!");
    }

    // MODIFIES: this (this.pet)
    // EFFECTS: play with pet using reward point and deduct reward point of user
    public void playWithPet() {
        System.out.println("How long do you want to play with me?; please enter int in minute");
        int time = input.nextInt();
        int rewardRequire = time * REWARD_PER_MINUTE_PLAY;
        if (getRewardPoint() >= rewardRequire) {
            yourPet.play(time);
            rewardPoint -= rewardRequire;
            System.out.println("I love playing with you!");
        } else {
            needMoreReward();
        }
        System.out.println("");

        checkAlive();
    }

    // MODIFIES: this
    // EFFECTS: enter new exercise to the system; exercise also get added to exerciselog of the system
    public void enterNewExercise() {
        System.out.println("Please select your type of exercise:");
        System.out.println("RUN, DANCE, WALK, YOGA, CLEANING, ROPEJUMPING");
        String typestr = input.next();
        ExerciseType newType = getType(typestr);
        System.out.println("Please enter your exercise time?");
        int minute = input.nextInt();

        Exercise newRecord = new Exercise(minute, newType);
        fitnessLog.addExercise(newRecord);
        rewardPoint += newRecord.getCalorie() * POINT_PER_CALORIE;
        System.out.println("Exercise has been added! This exercise burned " + newRecord.getCalorie() + " calorie.");
        System.out.println("");

        checkAlive();
    }

    // REQUIRES: the givenType must be one of the six provided exercise type
    // EFFECTS: obtain type enter by user to crease new exercise entry
    private ExerciseType getType(String givenType) {
        givenType = givenType.toLowerCase();
        ExerciseType type = null;
        switch (givenType) {
            case "run":
                type = RUN;
                break;
            case "dance":
                type = DANCE;
                break;
            case "walk":
                type = WALK;
                break;
            case "yoga":
                type = YOGA;
                break;
            case "cleaning":
                type = CLEANING;
                break;
            case "ropejumping":
                type = ROPEJUMPING;
                break;
        }
        return type;
    }

    // EFFECTS: print the pet's status
    public void getStatus() {
        System.out.println("My name is " + yourPet.getName());
        System.out.println("My energy point is " + yourPet.getEnergy() + " and our relationship is  "
                + yourPet.getIntimacy() + " points");
        System.out.println("You currently have " + getRewardPoint() + " reward point.");
        System.out.println("");

        checkAlive();
    }

    // MODIFIES: this (this.pet)
    // EFFECTS: record food calories intake and reduce pet's energy level according to calories intake
    public void recordFood() {
        System.out.println("What is your food type?");
        FoodType newType = getFoodType();
        System.out.println("How many gram did you eat?");
        int gram = input.nextInt();
        FoodIntake food = new FoodIntake(gram, newType);
        int decreaseEnergyPoint = food.getCalorie() / PET_ENERGY_DECREASE;
        yourPet.decreaseEnergy(decreaseEnergyPoint);
        System.out.println("");

        checkAlive();
    }

    // EFFECTS: parse user input and convert the input to FoodType that will be use to create new FoodIntake entry
    // typestr must be one of carb/ protein/ fats
    private FoodType getFoodType() {
        System.out.println("please choose from \"CARB\", \"PROTEIN\", OR \"FATS\"");
        String typestr = input.next();
        typestr = typestr.toLowerCase();
        FoodType type = null;

        switch (typestr) {
            case "carb":
                type = CARB;
                break;
            case "protein":
                type = PROTEIN;
                break;
            case "fats":
                type = FATS;
                break;
        }
        return type;
    }


    // EFFECT: print fitness log
    public void printLog() {
        for (Exercise e: fitnessLog.getLog()) {
            System.out.println("This exercise burned " + e.getCalorie() + " calorie and lasts for " + e.getTime()
                    + " minutes");
        }
        System.out.println("Your total calorie burned is");
        System.out.println(calculateLogCalorie());

        checkAlive();
    }


    // MODIFIES :this
    // EFFECT: calculate the total calories burned by all exercises in the log
    private double calculateLogCalorie() {
        double calorie = fitnessLog.calculateCalorie();
        this.calorie = calorie;
        return calorie;
    }


    // MODIFIES: this
    // EFFECT: terminate game
    public void stopGame() {
        saveGameStatus();
        System.out.println("Goodbye!");
        input.close();
        runPet = false;
    }

    // EFFECT: Print error message when input unavailable request
    public void printError() {
        System.out.println("Sorry I don't understand..");
        System.out.println("");

        checkAlive();
    }


    // EFFECT: call to end game if Pet's energy level <= 0
    public void checkAlive() {
        if (yourPet.petDie()) {
            endNoSave();
        } else {
            getInstruction();
        }
    }

    // EFFECT: terminate game without save
    private void endNoSave() {
        input.close();
        runPet = false;
    }

    // MODIFIES: this
    // EFFECT: save the current game state to a json file in .data (detail data path name given as constant above)
    public void saveGameStatus() {
        try {
            jsonWriter.open();
            jsonWriter.write(this);
            jsonWriter.close();
            System.out.println("Your file is added to " + JSON_STORE);
            System.out.println("Come back to your game soon~");
        } catch (FileNotFoundException e) {
            System.out.println("unable to find given file name");
        }
    }

    // EFFECT: Store current game state field into a Json file
    @Override
    public JSONObject toJson() {
        JSONObject recordJson = new JSONObject();
        recordJson.put("Pet",this.yourPet.toJson());
        recordJson.put("Exercises",this.fitnessLog.recordLog());
        recordJson.put("Reward points", this.getRewardPoint());
        recordJson.put("Total calorie", this.calculateLogCalorie());

        return recordJson;
    }

    // MODIFIES: this
    // EFFECTS: loads game status from record
    public void loadGameStatus() {
        try {
            PetGame savedGame = jsonReader.read();
            this.yourPet = savedGame.getYourPet();
            this.fitnessLog = savedGame.getLog();
            this.calorie = savedGame.getCal();
            this.rewardPoint = savedGame.getRewardPoint();
            System.out.println("Your game stored at" + JSON_STORE + " had been loaded!");
        } catch (IOException e) {
            System.out.println("Unable to read file");
        }
    }

}
