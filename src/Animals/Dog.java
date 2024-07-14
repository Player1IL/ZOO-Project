/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Dog
 */
package Animals;
import Animals.Base.TerrestrialAnimals;
import Olympics.Medal;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The Dog class represents a specific type of terrestrial animal.
 * It extends the TerrestrialAnimals class and adds attributes specific to a dog.
 */
public class Dog extends TerrestrialAnimals {
    private final String breed;
    /**
     * Constructs a new Dog instance.
     *
     * @param name     The name of the dog.
     * @param gender   The gender of the dog.
     * @param weight   The weight of the dog.
     * @param medals   A list of medals that the dog has won.
     * @param breed    The breed type of the dog.
     */
    public Dog(String name, gender gender, double weight, ArrayList<Medal> medals, String breed){
        super(name, gender, weight, 0, medals, 4);
        this.breed = breed;
    }
    /**
     * Produces the sound made by the dog.
     *
     * @return A string representing the sound made by the dog.
     */
    public String makeSound() {
        return super.makeSound() + "Woof Woof";
    }
    /**
     * Returns a string representation of the Dog instance, including its breed type.
     *
     * @return A string representation of the Dog instance.
     */
    @Override
    public String toString() {
        return  super.toString() + ", Breed type: " + breed + "\n";
    }
    /**
     * Compares this Dog to another object for equality.
     *
     * @param dog The object to compare this Dog against.
     * @return true if the specified object is equal to this Dog; false otherwise.
     */
    public boolean equals(Object dog) {
        if (this == dog) return true;
        if (!(dog instanceof Dog newDog)) return false;
        if (!super.equals(dog)) return false;
        return Objects.equals(breed, newDog.breed);
    }
}
