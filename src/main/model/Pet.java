package model;

// The pet class represent the interactive pet used in the game. Each pet will need a name, energy level and intimacy
// level. User can feed/ play with the pet to increase energy level / intimacy with the pet.

import org.json.JSONObject;
import persistence.Writable;
import ui.PetGame;

public class Pet implements Writable {
    // Pet will die if energy level = 0
    public static int INITIAL_ENERGY = 12;
    private static int MINUTES_FOR_ONE_INTIMACY = 5;

    private String name;
    public int energyLevel;
    public int intimacy;

    // effect: create new pet
    public Pet(String name) {
        this.name = name;
        this.energyLevel = INITIAL_ENERGY;
        this.intimacy = 0;
    }

    // effect: create new pet
    public Pet(String name, int energyLevel, int intamacy) {
        this.name = name;
        this.energyLevel = energyLevel;
        this.intimacy = intamacy;
    }

    // Getter

    //EFFECT: return the pet's name
    public String getName() {
        return this.name;
    }

    //EFFECT: reset pet's name
    public void setName(String name) {
        this.name = name;
    }


    //EFFECT: return the pet's current energy level
    public int getEnergy() {
        return this.energyLevel;
    }


    //EFFECT: return pet's intimacy level;
    public int getIntimacy() {
        return this.intimacy;
    }


    //interaction

    // REQUIRE: energy <= 0
    //MODIFIES: this
    // EFFECT: feed pet and increase pet's energy of given energy unit
    public void feed(int energy) {
        this.energyLevel += energy;
    }

    //REQUIRE: minute <= 0
    //MODIFIES: this
    // EFFECT: play with your pet to increase intimacy; intimacy only increase per given minutes of playing;
    public void play(int minute) {
        int amountIncrease = minute / MINUTES_FOR_ONE_INTIMACY;
        this.intimacy += amountIncrease;
    }

    // EFFECT: return true if the pet die (energy level <= 0)
    public boolean petDie() {
        if (this.getEnergy() <= 0) {
            System.out.println("Your pet is too hungry, Game over");
            return true;
        } else {
            return false;
        }
    }

    // Require: decreasePoint >= 0
    // MODIFIES: this
    // EFFECT: decrease pet's energy level
    public void decreaseEnergy(int decreasePoint) {
        this.energyLevel -= decreasePoint;
    }

    @Override
    // EFFECT: write the Pet status in a JSONObject
    public JSONObject toJson() {
        JSONObject petJson = new JSONObject();
        petJson.put("name", this.getName());
        petJson.put("EnergyLevel",this.getEnergy());
        petJson.put("Intimacy", this.getIntimacy());
        return petJson;
    }




}
