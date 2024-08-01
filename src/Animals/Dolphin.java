/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Dolphin
 */
package Animals;

import Animals.Base.AquaticAnimal;
import Graphics.CompetitionFrame;
import Olympics.Medal;

import java.util.ArrayList;

/**
 * The Dolphin class represents a specific type of water animal.
 * It extends the WaterAnimal class and adds attributes specific to a dolphin.
 */
public class Dolphin extends AquaticAnimal {
    /**
     * Enum representing the types of water in which dolphins can live.
     */
    public enum WaterType {
        Sea, Sweet
    }
    private WaterType waterType;
    /**
     * Constructs a new Dolphin instance.
     *
     * @param name           The name of the dolphin.
     * @param gender         The gender of the dolphin.
     * @param weight         The weight of the dolphin.
     * @param medals         A list of medals that the dolphin has won.
     * @param waterType      The type of water in which the dolphin lives (Sea or Sweet).
     */
    public Dolphin(String name, Gender gender, Competition competition, double weight, int maxEnergy, int energyPerMeter,
                   ArrayList<Medal> medals, CompetitionFrame myFrame, WaterType waterType) {
        super(name, gender, competition, weight, 0, maxEnergy, energyPerMeter, medals, myFrame);
        this.waterType = waterType;
        loadImages("dolphin");
    }
    /**
     * Produces the sound made by the dolphin.
     *
     * @return A string representing the sound made by the dolphin.
     */
    public String makeSound() {
        return super.makeSound() + "Click-click";
    }
    /**
     * Returns a string representation of the Dolphin instance, including its living water type.
     *
     * @return A string representation of the Dolphin instance.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "- " + super.toString() + ", Living water type: " + waterType + "\n";
    }
    /**
     * Compares this Dolphin to another object for equality.
     *
     * @param dolphin The object to compare this Dolphin against.
     * @return true if the specified object is equal to this Dolphin; false otherwise.
     */
    public boolean equals(Object dolphin) {
        if (this == dolphin) return true;
        if (!(dolphin instanceof Dolphin newDolphin)) return false;
        if (!super.equals(dolphin)) return false;
        return waterType == newDolphin.waterType;
    }
}

