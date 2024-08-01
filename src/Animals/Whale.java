/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Whale
 */
package Animals;

import Animals.Base.AquaticAnimal;
import Graphics.CompetitionFrame;
import Olympics.Medal;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The Whale class represents a specific type of water animal.
 * It extends the WaterAnimal class, adding attributes specific to a whale.
 */
public class Whale extends AquaticAnimal {
    /** The type of food that the whale consumes */
    private String foodType;
    /**
     * Constructs a new Whale instance.
     *
     * @param name      The name of the whale.
     * @param gender    The gender of the whale.
     * @param weight    The weight of the whale.
     * @param medals    A list of medals that the whale has won.
     * @param foodType  The type of food that the whale consumes.
     */
    public Whale(String name, Gender gender, Competition competition, double weight, int maxEnergy, int energyPerMeter,
                 ArrayList<Medal> medals, CompetitionFrame myFrame, String foodType) {
        super(name, gender, competition, weight, 0, maxEnergy, energyPerMeter, medals, myFrame);
        this.foodType = foodType;
        loadImages("whale");
    }
    /**
     * Produces the sound made by the whale.
     *
     * @return A string representing the sound made by the whale.
     */
    public String makeSound() {
        return super.makeSound() + "Splash";
    }
    /**
     * Returns a string representation of the Whale instance, including its food type.
     *
     * @return A string representation of the Whale instance.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "- " + super.toString() + ", Food type: " + foodType + "\n";
    }
    /**
     * Compares this Whale to another object for equality.
     *
     * @param whale The object to compare this Whale against.
     * @return true if the specified object is equal to this Whale; false otherwise.
     */
    public boolean equals(Object whale) {
        if (this == whale) return true;
        if (!(whale instanceof Whale newWhale)) return false;
        if (!super.equals(whale)) return false;
        return Objects.equals(foodType, newWhale.foodType);
    }
}
