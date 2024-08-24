/**
 * Author: Daniel Nekludov
 * ID: 321984619
 * <p>
 * Class Animal
 */
package Animals.Base;

import Animals.Thread.AnimalThread;
import Graphics.*;
import Mobility.Mobile;
import Olympics.Medal;
import Mobility.Point;

import java.lang.Math;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The Animal class represents a generic animal with common attributes such as name, gender, weight, speed, medals, and position.
 * This class is abstract and should be extended by specific types of animals.
 */
abstract public class Animal extends Mobile implements IMoveable, IDrawable, IClonable, IAnimal {
    /**
     * Enum representing various competition types.
     */
    public enum Competition {
        Air1, Air2, Air3, Air4, Air5,
        Pool1, Pool2, Pool3, Pool4,
        Circle
    }

    /**
     * Enum representing the gender of the animal.
     */
    public enum Gender {
        Male, Female, Hermaphrodite
    }

    /**
     * Enum representing the orientation of the animal picture
     */
    public enum Orientation {
        North, South, West, East
    }

    private static int idCount = 0;
    private final int id;

    private String name;
    private final Gender gender;
    private double weight;

    private ArrayList<Medal> medals;

    private int maxEnergy, energyPerMeter, speed;
    private int currentEnergy = 0;
    private int consumedEnergy = 0; // After every eat

    private CompetitionFrame myFrame;
    protected final int size = 65;
    private BufferedImage img1, img2, img3, img4;
    private Orientation orientation = Orientation.East;

    private final Competition competition;
    private Point[] field;

    private AnimalThread hold;

    private final Object lock = new Object();

    /**
     * Constructs a new Animal instance with the specified attributes.
     *
     * @param name         The name of the animal.
     * @param gender       The gender of the animal.
     * @param competition  The competition type for the animal.
     * @param weight       The weight of the animal.
     * @param speed        The speed of the animal.
     * @param maxEnergy    The maximum energy the animal can have.
     * @param energyPerMeter The energy consumed per meter traveled.
     * @param medals       A list of medals won by the animal.
     * @param myFrame      The competition frame associated with the animal.
     */
    public Animal(String name, Gender gender, Competition competition, double weight, int speed, int maxEnergy, int energyPerMeter,
                  ArrayList<Medal> medals, CompetitionFrame myFrame) {
        super(new Point(0, 0)); // (X,Y) Location
        this.name = name; // Name of animal
        this.gender = gender; // Enum Gender
        this.competition = competition; // Enum Competition
        this.weight = weight; // Weight
        this.speed = speed; // Speed
        this.medals = medals; // Array of Enum Medals
        this.myFrame = myFrame; // Main JFRAME
        this.maxEnergy = maxEnergy; // Energy limit
        this.energyPerMeter = energyPerMeter; // Energy consumption per meter
        setLocationBaseOnCompetition(this.competition);

        id = (++idCount); // UID
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
     * Feeds the animal with the specified amount of energy.
     * If the energy exceeds the maximum allowed, the animal's energy is capped at the maximum.
     *
     * @param energy The amount of energy to feed the animal.
     * @return true if the energy was successfully added, false otherwise.
     */
    public boolean eat(int energy) {
        synchronized (lock) {
        if (energy >= 0) {
            if (maxEnergy >= currentEnergy + energy) {
                currentEnergy = currentEnergy + energy;
                lock.notifyAll();
                return true;
            } else if (maxEnergy < currentEnergy + energy) {
                currentEnergy = maxEnergy;
                lock.notifyAll();
                return true;
            }
        }
        return false;
        }
    }
    public Object getLock() {return lock;}
    @Override
    public String getAnimaleName() {
        return this.name;
    }
    /**
     * Returns the speed of the animal.
     *
     * @return The speed of the animal.
     */
    public int getSpeed() {
        return this.speed;
    }
    /**
     * Sets the speed of the animal.
     *
     * @param speed The new speed of the animal.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    /**
     * Loads the images for the animal based on the provided name.
     * The images represent the animal in different orientations.
     *
     * @param nm The name of the animal used to load the images.
     */
    @Override
    public void loadImages(String nm) {
        try {
            img1 = ImageIO.read(new File(PICTURE_PATH + nm + "E.png"));
            img2 = ImageIO.read(new File(PICTURE_PATH + nm + "S.png"));
            img3 = ImageIO.read(new File(PICTURE_PATH + nm + "W.png"));
            img4 = ImageIO.read(new File(PICTURE_PATH + nm + "N.png"));
        } catch (IOException e) {
            System.out.println("Cannot load image\n");
        }
    }
    /**
     * Clones the Animal instance.
     *
     * @return A clone of the Animal instance.
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    /**
     * Draws the animal on the specified graphics context.
     * The animal is drawn in the direction specified by its orientation.
     *
     * @param g The graphics context on which to draw the animal.
     */
    @Override
    public void drawObject(Graphics g) {
        if (orientation == Orientation.East) // Animal move to the east side
            //g.drawImage(img1, getLocation().getX(), getLocation().getY() - size / 10, size * 2, size, myFrame);
            g.drawImage(img1, getLocation().getX(), getLocation().getY(), size, size, myFrame);
        else if (orientation == Orientation.South) // Animal move to the south side
            //g.drawImage(img2, getLocation().getX(), getLocation().getY() - size / 10, size, size, myFrame);
            g.drawImage(img2, getLocation().getX(), getLocation().getY(), size, size, myFrame);
        else if (orientation == Orientation.West) // Animal move to the west side
            //g.drawImage(img3, getLocation().getX(), getLocation().getY() - size / 10, size * 2, size, myFrame);
            g.drawImage(img3, getLocation().getX(), getLocation().getY(), size, size, myFrame);
        else if (orientation == Orientation.North) // Animal move to the north side
            //g.drawImage(img4, getLocation().getX() - size / 2, getLocation().getY() - size / 10, size, size * 2, myFrame);
            g.drawImage(img4, getLocation().getX(), getLocation().getY(), size, size, myFrame);
    }
    /**
     * Returns the current amount of energy of the animal.
     *
     * @return The current energy amount.
     */
    public int getEnergyAmount() {
        return currentEnergy;
    }
    /**
     * Returns the amount of energy consumed by the animal.
     *
     * @return The consumed energy amount.
     */
    public int getConsumedEnergy() {
        return consumedEnergy;
    }
    /**
     * Returns the unique identifier of the animal.
     *
     * @return The unique ID of the animal.
     */
    public int getUID() {
        return id;
    }

    public void setAnimalThread(AnimalThread hold) {this.hold = hold;}
    public AnimalThread getAnimalThread() {return hold;}
    /**
     * Sets the location of the animal based on the specified competition type.
     *
     * @param competition The competition type used to determine the animal's location.
     */
    public void setLocationBaseOnCompetition(Competition competition) {
        /*_____________________________________________
        |(0,0)________________________________(1619,0)|
        |     |(77,67)                 (1542,67)|     |
        |     |(77,977)_______________(1542,977)|     |
        |(0,1048)__________________________(1619,1048)|*/
        switch (competition) {
            case Competition.Circle: {
                field = new Point[]{
                        new Point(0, 0), // Outer Top Left
                        new Point(1619, 1048), // Outer Bottom Right
                        new Point(77, 67), // Inner Top Left
                        new Point(1542, 977) // Inner Bottom Right
                };
                setLocation(field[0]);
                break;
            }
            /*_____________________________________________
            |(0,0)                                (1619,0)|
            |(0,67)______________________________(1619,67)|*/
            case Competition.Air1: {
                field = new Point[]{
                        new Point(0, 0),
                        new Point(1619, 67)
                };
                setLocation(field[0]);
                break;
            }
            /*_____________________________________________
            |(0,248)                            (1619,248)|
            |(0,311)____________________________(1619,311)|*/
            case Competition.Air2: {
                field = new Point[]{
                        new Point(0, 248),
                        new Point(1619, 311)
                };
                setLocation(field[0]);
                break;
            }
            /*_____________________________________________
            |(0,488)                            (1619,488)|
            |(0,553)____________________________(1619,553)|*/
            case Competition.Air3: {
                field = new Point[]{
                        new Point(0, 488),
                        new Point(1619, 553)
                };
                setLocation(field[0]);
                break;
            }
            /*_____________________________________________
            |(0,741)                            (1619,741)|
            |(0,805)____________________________(1619,805)|*/
            case Competition.Air4: {
                field = new Point[]{
                        new Point(0, 741),
                        new Point(1619, 805)
                };
                setLocation(field[0]);
                break;
            }
            /*_____________________________________________
            |(0,978)                            (1619,978)|
            |(0,1048)__________________________(1619,1048)|*/
            case Competition.Air5: {
                field = new Point[]{
                        new Point(0, 978),
                        new Point(1619, 1048)
                };
                setLocation(field[0]);
                break;
            }
            /*_____________________________________________
            |(142,132)                          (1477,132)|
            |(142,183)__________________________(1477,183)|*/
            case Competition.Pool1: {
                field = new Point[]{
                        new Point(142, 132),
                        new Point(1477, 183)
                };
                setLocation(field[0]);
                break;
            }
            /*_____________________________________________
            |(142,376)                          (1477,376)|
            |(142,423)__________________________(1477,423)|*/
            case Competition.Pool2: {
                field = new Point[]{
                        new Point(142, 376),
                        new Point(1477, 423)
                };
                setLocation(field[0]);
                break;
            }
            /*_____________________________________________
            |(142,618)                          (1477,618)|
            |(142,675)__________________________(1477,675)|*/
            case Competition.Pool3: {
                field = new Point[]{
                        new Point(142, 618),
                        new Point(1477, 675)
                };
                setLocation(field[0]);
                break;
            }
            /*_____________________________________________
            |(142,870)                          (1477,870)|
            |(142,912)__________________________(1477,912)|*/
            case Competition.Pool4: {
                field = new Point[]{
                        new Point(142, 870),
                        new Point(1477, 912)
                };
                setLocation(field[0]);
                break;
            }
        }
    }
    /**
     * Moves the animal to the specified point if it is different from the current location.
     *
     * @param p The target point to move the animal to.
     * @return true if the animal was successfully moved; false otherwise.
     */
    @Override
    public boolean move(Point p) {
        if (!this.getLocation().equals(p)) {
            this.setLocation(p);
            return true;
        }
        return false;
    }
    /**
     * Executes the race logic for the animal based on its speed and energy.
     * Updates the animal's location and orientation as it moves through the competition field.
     *
     * @return true if the race was successfully executed; false otherwise.
     */
    public boolean race() {
        boolean raceAgain = true;
        try {
            speed = currentEnergy / energyPerMeter;
        } catch (ArithmeticException e) {
            return false;
        }
        if (speed > 0) {
            if (Orientation.East == orientation) {
                if (speed + getLocation().getX() < field[1].getX()) {
                    move(new Point(getLocation().getX() + speed, getLocation().getY()));
                    currentEnergy -= speed;
                } else if (speed + getLocation().getX() >= field[1].getX()) {
                    move(new Point(field[1].getX(), getLocation().getY()));
                    if (Competition.Circle == competition) {
                        orientation = Orientation.South;
                    }
                    else {
                        orientation = Orientation.West;
                    }
                    currentEnergy = currentEnergy - (field[1].getX() - getLocation().getX());
                }
            } else if (Orientation.South == orientation) {
                if (speed + getLocation().getY() < field[1].getY()) {
                    move(new Point(getLocation().getX(), getLocation().getY() + speed));
                    currentEnergy -= speed;
                } else if (speed + getLocation().getY() >= field[1].getY()) {
                    try {
                        move((Point) field[1].clone());
                    } catch (CloneNotSupportedException e) {
                        return false;
                    }
                    orientation = Orientation.West;
                    currentEnergy = currentEnergy - (field[1].getY() - getLocation().getY());
                }
            } else if (Orientation.West == orientation) {
                if (getLocation().getX() - speed > field[0].getX()) {
                    move(new Point(Math.abs(getLocation().getX() - speed), getLocation().getY()));
                    currentEnergy -= speed;
                } else if (getLocation().getX() - speed <= field[0].getX()) {
                    if (Competition.Circle == competition) {
                        move(new Point(field[0].getX(), field[1].getY()));
                        orientation = Orientation.North;
                    }
                    else {
                        move(new Point(field[0].getX(), field[0].getY()));
                        // orientation = Orientation.East; // Don't repeat
                        raceAgain = false;
                    }
                    currentEnergy = currentEnergy - getLocation().getX();
                }
            } else if (Orientation.North == orientation) {
                if (getLocation().getY() - speed > field[0].getY()) {
                    move(new Point(getLocation().getX(), getLocation().getY() - speed));
                    currentEnergy -= speed;
                } else if (getLocation().getY() - speed <= field[0].getY()) {
                    try {
                        move((Point) field[0].clone());
                    } catch (CloneNotSupportedException e) {
                        return false;
                    }
                    // orientation = Orientation.East; // Don't repeat
                    raceAgain = false;
                    currentEnergy = currentEnergy - getLocation().getY();
                }
            }
            addTotalDistance(speed);
            try {
                speed = currentEnergy / energyPerMeter;
            } catch (ArithmeticException e) {
                return false;
            }
            myFrame.repaint();
            if (speed > 0 && raceAgain) {
                race();
            }
            return true;
        }
        return false;
    }
    public boolean finishedRace() {
        if (Competition.Circle == competition) {
            if (Orientation.North == orientation) {
                return getLocation().getX() == field[0].getX() && getLocation().getY() == field[0].getY();
            }
        }
        else if (competition == Competition.Air1 || competition == Competition.Air2 || competition == Competition.Air3 ||
                competition == Competition.Air4 || competition == Competition.Air5) {
            if (Orientation.West == orientation) {
                return getLocation().getX() == field[0].getX();
            }
        }
        else if (competition == Competition.Pool1 || competition == Competition.Pool2 ||
                competition == Competition.Pool3 || competition == Competition.Pool4) {
            if (Orientation.West == orientation) {
                return getLocation().getX() == field[0].getX();
            }
        }
        return false;
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
                ", Position: " + getLocation();
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
                newAnimal.speed == speed &&
                Objects.equals(getLocation(), newAnimal.getLocation()) &&
                medals.equals(newAnimal.medals) &&
                newAnimal.maxEnergy == maxEnergy &&
                newAnimal.energyPerMeter == energyPerMeter &&
                newAnimal.consumedEnergy == consumedEnergy &&
                orientation == newAnimal.orientation &&
                competition == newAnimal.competition;
    }
}
