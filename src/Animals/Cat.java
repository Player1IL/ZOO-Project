/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Cat
 */
package Animals;

import Animals.Base.TerrestrialAnimals;
import Graphics.CompetitionFrame;
import Olympics.Medal;
import java.util.ArrayList;

/**
 * The Cat class represents a specific type of terrestrial animal.
 * It extends the TerrestrialAnimals class and adds attributes specific to a cat.
 */
public class Cat extends TerrestrialAnimals {
    private boolean castrated;
    /**
     * Constructs a new Cat instance.
     *
     * @param name       The name of the cat.
     * @param gender     The gender of the cat.
     * @param weight     The weight of the cat.
     * @param medals     A list of medals that the cat has won.
     * @param castrated  Whether the cat is castrated or not.
     */
    public Cat(String name, Gender gender, Competition competition, double weight, int maxEnergy, int energyPerMeter,
               ArrayList<Medal> medals, CompetitionFrame myFrame, boolean castrated){
        super(name, gender, competition, weight, 0, maxEnergy, energyPerMeter, medals, 4, myFrame);
        this.castrated = castrated;
        loadImages("cat");
    }
    /**
     * Produces the sound made by the cat.
     *
     * @return A string representing the sound made by the cat.
     */
    public String makeSound() {
        return super.makeSound() + "Meow";
    }
    /**
     * Returns a string representation of the Cat instance, including whether it is castrated.
     *
     * @return A string representation of the Cat instance.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "- " + super.toString() + ", Is castrated: " + castrated + "\n";
    }
    /**
     * Compares this Cat to another object for equality.
     *
     * @param cat The object to compare this Cat against.
     * @return true if the specified object is equal to this Cat; false otherwise.
     */
    public boolean equals(Object cat) {
        if (this == cat) return true;
        if (!(cat instanceof Cat newCat)) return false;
        if (!super.equals(cat)) return false;
        return castrated == newCat.castrated;
    }
}
