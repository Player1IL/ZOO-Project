/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Alligator
 */
package Animals;
import Animals.Base.IReptile;
import Animals.Base.TerrestrialAnimals;
import Animals.Base.WaterAnimal;
import Olympics.Medal;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The Alligator class represents a specific type of water animal, implementing characteristics of a reptile.
 * It extends the WaterAnimal class and implements the IReptile interface.
 */
public class Alligator extends WaterAnimal implements IReptile {
    private TerrestrialAnimals terrestrialSide;
    private String areaOfLiving;

    /**
     * Constructs a new Alligator instance.
     *
     * @param name         The name of the alligator.
     * @param gender       The gender of the alligator.
     * @param weight       The weight of the alligator.
     * @param medals       A list of medals that the alligator has won.
     * @param areaOfLiving The area where the alligator lives.
     */
    public Alligator(String name, gender gender, double weight, ArrayList<Medal> medals, String areaOfLiving) {
        super(name, gender, weight, medals, 0);
        this.terrestrialSide = new TerrestrialAnimals(name, gender, weight, 0, medals, 4);
        this.areaOfLiving = areaOfLiving;
    }
    /**
     * Produces the sound made by the alligator.
     *
     * @return A string representing the sound made by the alligator.
     */
    public String makeSound() {
        return super.makeSound() + "Roar";
    }
    /**
     * Returns a string representation of the Alligator instance, including its area of living.
     *
     * @return A string representation of the Alligator instance.
     */
    @Override
    public String toString() {
        return  super.toString() + ", Area of Living: " + areaOfLiving + "\n";
    }
    /**
     * Compares this Alligator to another object for equality.
     *
     * @param alligator The object to compare this Alligator against.
     * @return true if the specified object is equal to this Alligator; false otherwise.
     */
    public boolean equals(Object alligator) {
        if (this == alligator) return true;
        if (!(alligator instanceof Alligator newAlligator)) return false;
        if (!super.equals(alligator)) return false;
        return this.terrestrialSide.equals(newAlligator.terrestrialSide) &&
                Objects.equals(areaOfLiving, newAlligator.areaOfLiving);
    }
    /**
     * Increases the speed of the alligator by a specified amount.
     *
     * @param speed The amount by which to increase the speed.
     */
    @Override
    public void speedUp(int speed) {
        setSpeed(Math.min(MAX_SPEED, getSpeed() + speed));
    }
}
