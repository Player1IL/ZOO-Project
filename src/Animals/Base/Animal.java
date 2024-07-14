/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Animal
 */
package Animals.Base;
import Graphics.IAnimal;
import Graphics.IClonable;
import Olympics.Medal;
import Mobility.Point;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The Animal class represents a generic animal with common attributes such as name, gender, weight, speed, medals, and position.
 * This class is abstract and should be extended by specific types of animals.
 */
abstract public class Animal implements IAnimal, IClonable {
    /**
     * Enum representing the gender of the animal.
     */
    public enum gender {
        Male, Female, Hermaphrodite
    }

    private String name;
    private final gender gender;
    private double weight, speed;
    private ArrayList<Medal> medals;
    private Point position;

    /**
     * Constructs a new Animal instance.
     *
     * @param name     The name of the animal.
     * @param gender   The gender of the animal.
     * @param weight   The weight of the animal.
     * @param speed    The speed of the animal.
     * @param medals   A list of medals that the animal has won.
     * @param position The position of the animal.
     */
    public Animal(String name, gender gender, double weight, double speed, ArrayList<Medal> medals, Point position) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.speed = speed;
        this.medals = medals;
        this.position = position;
    }
    /**
     * Returns the sound made by the animal.
     *
     * @return The sound made by the animal.
     */
    public String makeSound() {
        return this.name + " said ";
    }
    /**
     * Returns the speed of the animal.
     *
     * @return The speed of the animal.
     */
    public double getSpeed() {
        return this.speed;
    }
    /**
     * Sets the speed of the animal.
     *
     * @param speed The new speed of the animal.
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    /**
     * Returns a string representation of the Animal instance, including its name, gender, weight, speed, medals, and position.
     *
     * @return A string representation of the Animal instance.
     */
    @Override
    public String toString() {
        StringBuilder medalsString = new StringBuilder();
        for (Medal medal : medals) {
            medalsString.append(medal.toString()).append(", ");
        }
        return "Name: " + name +
                ", Gender: " + gender +
                ", Weight: " + weight +
                ", Speed: " + speed +
                ", Medals: [" + medalsString + "]" +
                ", Position: " + position;
    }
    /**
     * Compares this Animal to another object for equality.
     *
     * @param animal The object to compare this Animal against.
     * @return true if the specified object is equal to this Animal; false otherwise.
     */
    @Override
    public boolean equals(Object animal) {
        if (this == animal) return true;
        if (!(animal instanceof Animal newAnimal)) return false;
        return Objects.equals(name, newAnimal.name) &&
                gender == newAnimal.gender &&
                Double.compare(newAnimal.weight, weight) == 0 &&
                Double.compare(newAnimal.speed, speed) == 0 &&
                Objects.equals(position, newAnimal.position) &&
                medals.equals(newAnimal.medals);
    }
}
