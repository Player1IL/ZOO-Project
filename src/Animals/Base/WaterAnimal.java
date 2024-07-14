/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Water Animals
 */
package Animals.Base;
import Mobility.Point;
import Olympics.Medal;

import java.util.ArrayList;

/**
 * The WaterAnimal class represents animals that live primarily in water.
 * It extends the Animal class and adds attributes related to diving.
 */
public class WaterAnimal extends Animal {
    private static final double MAX_DIVE = -800;
    private double diveDepth = 0;

    /**
     * Constructs a new WaterAnimal instance.
     *
     * @param name     The name of the animal.
     * @param gender   The gender of the animal.
     * @param weight   The weight of the animal.
     * @param medals   A list of medals that the animal has won.
     * @param speed    The speed of the animal.
     */
    public WaterAnimal(String name, gender gender, double weight, ArrayList<Medal> medals, double speed) {
        super(name, gender, weight, speed, medals, new Point(50,0));
    }
    /**
     * Causes the animal to dive to a specified depth. The depth is limited by a maximum dive depth.
     *
     * @param depth The depth to which the animal dives.
     */
    public void Dive(double depth) {
        diveDepth = Math.min(MAX_DIVE, diveDepth - depth);
    }
    /**
     * Returns a string representation of the WaterAnimal instance, including the dive depth.
     *
     * @return A string representation of the WaterAnimal instance.
     */
    @Override
    public String toString() {
        return super.toString() + ", Dive depth: " + diveDepth;
    }
    /**
     * Compares this WaterAnimal to another object for equality.
     *
     * @param waterAnimal The object to compare this WaterAnimal against.
     * @return true if the specified object is equal to this WaterAnimal; false otherwise.
     */
    @Override
    public boolean equals(Object waterAnimal) {
        if (this == waterAnimal) return true;
        if (!(waterAnimal instanceof WaterAnimal newWaterAnimal)) return false;
        if (!super.equals(waterAnimal)) return false;
        return Double.compare(diveDepth, newWaterAnimal.diveDepth) == 0;
    }
}
