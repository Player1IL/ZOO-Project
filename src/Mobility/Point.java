/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Point
 */
package Mobility;

import Graphics.IClonable; /**
 * The Point class represents a point in a 2-dimensional space with coordinates (x, y).
 * It provides methods to calculate distance between points, and overrides toString and equals methods.
 */
public class Point implements IClonable {
        private int x, y;
    /**
     * Constructs a Point object with specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Calculates the Euclidean distance between this point and another point p.
     *
     * @param p The other point to calculate the distance to.
     * @return The distance between this point and point p.
     */
    public double distanceP2P(Point p) {
        return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
    }
    public int getX() {return this.x;}
    public int getY() {return this.y;}
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    /**
     * Returns a string representation of the point in the format "(x, y)".
     *
     * @return A string representation of the point.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    /**
     * Checks if this point is equal to another object.
     *
     * @param point The object to compare with this point.
     * @return true if the given object is a Point with the same coordinates, false otherwise.
     */
    @Override
    public boolean equals(Object point) {
        if (this == point) return true;
        if (!(point instanceof Point newPoint)) return false;
        return x == newPoint.x && y == newPoint.y;
    }
}
