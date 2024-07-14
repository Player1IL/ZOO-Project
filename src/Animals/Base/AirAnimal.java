/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class AirAnimal
 */
package Animals.Base;
import Mobility.Point;
import Olympics.Medal;

import java.util.ArrayList;

/**
 * The AirAnimal class represents animals that are capable of flying.
 * It extends the Animal class and adds the wingspan attribute.
 */
public class AirAnimal extends Animal {
    private double wingspan;


    /**
     * Constructs a new AirAnimal instance.
     *
     * @param name     The name of the animal.
     * @param gender   The gender of the animal.
     * @param weight   The weight of the animal.
     * @param speed    The speed of the animal.
     * @param medals   A list of medals that the animal has won.
     * @param wingspan The wingspan of the animal.
     */
    public AirAnimal(String name, gender gender, double weight, double speed, ArrayList<Medal> medals, double wingspan) {
        super(name, gender, weight, speed, medals, new Point(0,100));
        this.wingspan = wingspan;
    }
    /**
     * Returns a string representation of the AirAnimal instance, including the wingspan.
     *
     * @return A string representation of the AirAnimal instance.
     */
    @Override
    public String toString() {
        return super.toString() + ", Wings span: " + wingspan;
    }
    /**
     * Compares this AirAnimal to another object for equality.
     *
     * @param airAnimal The object to compare this AirAnimal against.
     * @return true if the specified object is equal to this AirAnimal; false otherwise.
     */
    @Override
    public boolean equals(Object airAnimal) {
        if (this == airAnimal) return true;
        if (!(airAnimal instanceof AirAnimal newAirAnimal)) return false;
        if (!super.equals(airAnimal)) return false;
        return Double.compare(wingspan, newAirAnimal.wingspan) == 0;
    }
}
