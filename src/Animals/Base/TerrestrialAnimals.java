/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Terrestrial lAnimals
 */
package Animals.Base;
import Graphics.CompetitionFrame;
import Mobility.Point;
import Olympics.Medal;

import java.util.ArrayList;

/**
 * The TerrestrialAnimals class represents animals that live primarily on land.
 * It extends the Animal class and adds the number of legs attribute.
 */
public class TerrestrialAnimals extends Animal {
    private final int noLegs;
    /**
     * Constructs a new TerrestrialAnimals instance.
     *
     * @param name     The name of the animal.
     * @param gender   The gender of the animal.
     * @param weight   The weight of the animal.
     * @param speed    The speed of the animal.
     * @param medals   A list of medals that the animal has won.
     * @param noLegs   The number of legs the animal has.
     */
    public TerrestrialAnimals(String name, Gender gender, Competition competition, double weight, int speed, int maxEnergy, int energyPerMeter,
                              ArrayList<Medal> medals, int noLegs, CompetitionFrame myFrame) {
        super(name, gender, competition, weight, speed, maxEnergy, energyPerMeter, medals, myFrame);
        this.noLegs = noLegs;
    }
    /**
     * Returns a string representation of the TerrestrialAnimals instance, including the number of legs.
     *
     * @return A string representation of the TerrestrialAnimals instance.
     */
    @Override
    public String toString() {
        return super.toString() + ", Number of legs: " + noLegs;
    }
        /**
     * Compares this TerrestrialAnimals to another object for equality.
     *
     * @param terrestrialAnimals The object to compare this TerrestrialAnimals against.
     * @return true if the specified object is equal to this TerrestrialAnimals; false otherwise.
     */
    @Override
    public boolean equals(Object terrestrialAnimals) {
        if (this == terrestrialAnimals) return true;
        if (!(terrestrialAnimals instanceof TerrestrialAnimals newTerrestrialAnimals)) return false;
        if (!super.equals(terrestrialAnimals)) return false;
        return noLegs == newTerrestrialAnimals.noLegs;
    }
}
