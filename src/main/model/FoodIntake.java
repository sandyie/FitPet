package model;

// The FoodIntake class allow user to record what they have eat. This entry requires gram of food intake and given
// food type. The class will calculate calorie of recorded intake.

public class FoodIntake {
    private int gram;
    private FoodType type;
    private int calorie;

    // create new food intake with given gram and type of food
    public FoodIntake(int gram, FoodType type) {
        this.gram = gram;
        this.type = type;
        this.calorie = calculateCal();
    }

    // Getter
    // EFFECT: return gram of food intake
    public int getGram() {
        return this.gram;
    }

    // EFFECT: return tyoe of this food intake
    public FoodType getType() {
        return this.type;
    }

    // EFFECT: return tyoe of this food intake
    public int getCalorie() {
        return this.calorie;
    }


    // REQUIRE:
    // MODIFIES: this
    // EFFECT: calculate calorie intake of this meal
    public int calculateCal() {
        int calorie = 0;
        if (this.getType().equals(FoodType.CARB)) {
            calorie = this.getGram() * 4;
        } else if (this.getType().equals(FoodType.FATS)) {
            calorie = this.getGram() * 9;
        } else  {
            calorie = this.getGram() * 4;
        }
        return calorie;
    }
}
