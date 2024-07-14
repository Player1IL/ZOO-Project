/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Moblie
 */
package Mobility;
/**
 * The abstract class Moblie represents a mobile object that can move and track its location.
 * It implements the ILocatable interface to provide location-related functionalities.
 */
abstract public class Moblie implements ILocatable {
    /** The current location of the mobile object */
   private Point location;
   /** The total distance traveled by the mobile object */
   private double totalDistance;
   /**
    * Constructs a Moblie object with an initial location.
    *
    * @param location The initial location of the mobile object.
    */
   public Moblie(Point location) {
       this.location = location;
       totalDistance = 0;
   }
   /**
    * Adds the given distance to the total distance traveled.
    *
    * @param distance The distance to add to the total distance.
    */
   private void addTotalDistance(double distance) {
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
   /**
    * Moves the mobile object to the specified location and returns the distance traveled.
    *
    * @param p The destination point to move to.
    * @return The distance traveled to reach the destination.
    */
   public double move(Point p) {
       if (location.equals(p)) {
           return 0;
       }
       else {
          double dist = calcDistance(p);
          addTotalDistance(dist);
          location = p;
          return dist;
       }
   }
}
