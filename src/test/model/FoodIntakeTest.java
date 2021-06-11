package model;

import model.FoodIntake;
import model.FoodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.FormattedDateConversion;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodIntakeTest {
    FoodIntake carb;
    FoodIntake protein;
    FoodIntake fats;

    @BeforeEach
    public void setUp() {
        carb = new FoodIntake(10, FoodType.CARB);
        protein = new FoodIntake(10,FoodType.PROTEIN);
        fats = new FoodIntake(10,FoodType.FATS);
    }

    @Test
    public void testGetter() {
        assertEquals(10,carb.getGram());
        assertEquals(FoodType.PROTEIN,protein.getType());
        assertEquals(40,carb.getCalorie());
    }

    @Test
    public void testCalculateCal() {
        assertEquals(40,carb.calculateCal());
        assertEquals(40,protein.calculateCal());
        assertEquals(90,fats.calculateCal());
    }

    @Test
    public void testCalculateProtein() {
        FoodIntake newProtein = new FoodIntake(20,FoodType.PROTEIN);
        assertEquals(80,newProtein.calculateCal());

    }

    @Test
    public void testCarb() {
        assertEquals(40,carb.calculateCal());
    }

    @Test
    public void testFats() {
        assertEquals(90,fats.calculateCal());
    }
}
