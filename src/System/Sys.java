/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Main file
 */
package System;

import Animals.Base.Animal;
import Array.EnhancedLimitedArray;
import Graphics.CompetitionFrame;

import java.util.*;

public class Sys {
    public static ArrayList<Animal> animalArrayList = new ArrayList<>(); // Main animal array
    public static ArrayList<EnhancedLimitedArray> tournamentArrayList = new ArrayList<>();
    public static void main(String[] args) {
        new CompetitionFrame();
    }
}
