/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Pigeon
 */
package Animals;

import Animals.Base.AirAnimal;
import Animals.Base.Animal;
import Olympics.Medal;

import java.util.ArrayList;
import java.util.Objects;
/**
 * The Pigeon class represents a specific type of air animal.
 * It extends the AirAnimal class and adds attributes specific to a pigeon.
 */
public class Pigeon extends AirAnimal {
    private String family;
    /**
     * Constructs a new Pigeon instance.
     *
     * @param name     The name of the pigeon.
     * @param gender   The gender of the pigeon.
     * @param weight   The weight of the pigeon.
     * @param medals   A list of medals that the pigeon has won.
     * @param wingspan The wingspan of the pigeon.
     * @param family   The family type of the pigeon.
     */
    public Pigeon(String name, Animal.gender gender, double weight, ArrayList<Medal> medals, double wingspan, String family) {
        super(name, gender, weight, 0, medals, wingspan);
        this.family = family;
    }
    /**
     * Produces the sound made by the pigeon.
     *
     * @return A string representing the sound made by the pigeon.
     */
    public String makeSound() {
        return super.makeSound() + "Arr-rar-rar-rar-raah";
    }
    /**
     * Returns a string representation of the Pigeon instance, including its family type.
     *
     * @return A string representation of the Pigeon instance.
     */
    @Override
    public String toString() {
        return  super.toString() + ", Family: " + family + "\n";
    }
    /**
     * Compares this Pigeon to another object for equality.
     *
     * @param pigeon The object to compare this Pigeon against.
     * @return true if the specified object is equal to this Pigeon; false otherwise.
     */
    public boolean equals(Object pigeon) {
        if (this == pigeon) return true;
        if (!(pigeon instanceof Pigeon newPigeon)) return false;
        if (!super.equals(pigeon)) return false;
        return Objects.equals(family, newPigeon.family);
    }
}
