/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Snake
 */
package Animals;

import Animals.Base.Animal;
import Animals.Base.IReptile;
import Animals.Base.TerrestrialAnimals;
import Olympics.Medal;

import java.util.ArrayList;
/**
 * The Snake class represents a specific type of terrestrial animal.
 * It extends the TerrestrialAnimals class and implements the IReptile interface,
 * adding attributes specific to a snake.
 */
public class Snake extends TerrestrialAnimals implements IReptile {
    /** Enum representing how venomous the snake is. */
    public enum poisonous {
        Low, Medium, High
    }
    private final poisonous poisonous;
    /**
     * Constructs a new Snake instance.
     *
     * @param name      The name of the snake.
     * @param gender    The gender of the snake.
     * @param weight    The weight of the snake.
     * @param medals    A list of medals that the snake has won.
     * @param poisonous Whether the snake is poisonous or not.
     */
    public Snake(String name, Animal.gender gender, double weight, ArrayList<Medal> medals, poisonous poisonous){
        super(name, gender, weight, 0, medals, 0);
        this.poisonous = poisonous;
    }
    /**
     * Produces the sound made by the snake.
     *
     * @return A string representing the sound made by the snake.
     */
    public String makeSound() {
        return super.makeSound() + "ssssssss";
    }
    /**
     * Returns a string representation of the Snake instance, including its poisonous status.
     *
     * @return A string representation of the Snake instance.
     */
    @Override
    public String toString() {
        return  super.toString() + ", Is poisonous: " + poisonous + "\n";
    }
    /**
     * Compares this Snake to another object for equality.
     *
     * @param snake The object to compare this Snake against.
     * @return true if the specified object is equal to this Snake; false otherwise.
     */
    public boolean equals(Object snake) {
        if (this == snake) return true;
        if (!(snake instanceof Snake newSnake)) return false;
        if (!super.equals(snake)) return false;
        return poisonous == newSnake.poisonous;
    }
    /**
     * Speeds up the movement speed of the snake.
     *
     * @param speed The amount by which to increase the speed.
     */
    @Override
    public void speedUp(int speed) {
        setSpeed(Math.min(MAX_SPEED, getSpeed() + speed));
    }
}
