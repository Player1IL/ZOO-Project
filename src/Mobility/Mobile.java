/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Moblie
 */
package Mobility;

import java.util.Objects;

/**
 * The abstract class Moblie represents a mobile object that can move and track its location.
 * It implements the ILocatable interface to provide location-related functionalities.
 */
abstract public class Mobile implements ILocatable {
    /** The current location of the mobile object */
   private Point location;
   /** The total distance traveled by the mobile object */
   private double totalDistance;
   /**
    * Constructs a Moblie object with an initial location.
    *
    * @param location The initial location of the mobile object.
    */
   public Mobile(Point location) {
       this.location = location;
       totalDistance = 0;
   }
   /**
    * Adds the given distance to the total distance traveled.
    *
    * @param distance The distance to add to the total distance.
    */
   public void addTotalDistance(double distance) {
       if (totalDistance + distance >= totalDistance) {
           totalDistance += distance;
       }
   }
   /**
    * Calculates the distance from the current location to the specified location.
    *
    * @param location The destination location.
    * @return The distance between the current location and the destination.
    */
   public double calcDistance(Point location) {
       return this.location.distanceP2P(location);
   }
   public Point getLocation() {
       return this.location;
   }
   public Boolean setLocation(Point p) {
       if (this.location.equals(p)) {
           return false;
       }
       else {
           this.location = p;
           return true;
       }
   }
   public double getDistance() {return totalDistance;}

   @Override
   public String toString() {
        return "Location: " + this.location + " Distance: " + totalDistance;
   }
   public boolean equals(Object mobile) {
        if (this == mobile) return true;
        if (!(mobile instanceof Mobile newMobile)) return false;
        return Objects.equals(location, newMobile.location) &&
                Double.compare(newMobile.totalDistance, totalDistance) == 0;
    }

}
