/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Eagle
 */
package Animals;

import Animals.Base.AirAnimal;
import Graphics.CompetitionFrame;
import Olympics.Medal;

import java.util.ArrayList;
/**
 * The Eagle class represents a specific type of air animal.
 * It extends the AirAnimal class and adds attributes specific to an eagle.
 */
public class Eagle extends AirAnimal {
    static final double MAX_ALTITUDE = 1000;
    private double altitudeOfFlight;
    /**
     * Constructs a new Eagle instance.
     *
     * @param name            The name of the eagle.
     * @param gender          The gender of the eagle.
     * @param weight          The weight of the eagle.
     * @param medals          A list of medals that the eagle has won.
     * @param wingspan        The wingspan of the eagle.
     * @param altitudeOfFlight The altitude at which the eagle flies.
     */
    public Eagle(String name, Gender gender, Competition competition, double weight, int maxEnergy, int energyPerMeter,
                 ArrayList<Medal> medals, double wingspan, CompetitionFrame myFrame, double altitudeOfFlight) {
        super(name, gender, competition, weight, 0, maxEnergy, energyPerMeter, medals, myFrame, wingspan);
        this.altitudeOfFlight = limitAltitude(altitudeOfFlight);
        loadImages("eagle");
    }
    /**
     * Limits the altitude of flight to the maximum allowed altitude.
     *
     * @param altitude The altitude to limit.
     * @return The limited altitude, constrained by MAX_ALTITUDE.
     */
    private double limitAltitude(double altitude) {
        return Math.min(MAX_ALTITUDE, altitude);
    }
    /**
     * Produces the sound made by the eagle.
     *
     * @return A string representing the sound made by the eagle.
     */
    public String makeSound() {
        return super.makeSound() + "Clack-wack-chack";
    }
    /**
     * Returns a string representation of the Eagle instance, including its altitude of flight.
     *
     * @return A string representation of the Eagle instance.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "- " + super.toString() + ", Altitude: " + altitudeOfFlight + "\n";
    }
    /**
     * Compares this Eagle to another object for equality.
     *
     * @param eagle The object to compare this Eagle against.
     * @return true if the specified object is equal to this Eagle; false otherwise.
     */
    public boolean equals(Object eagle) {
        if (this == eagle) return true;
        if (!(eagle instanceof Eagle newEagle)) return false;
        if (!super.equals(eagle)) return false;
        return Double.compare(altitudeOfFlight, newEagle.altitudeOfFlight) == 0;
    }
}
