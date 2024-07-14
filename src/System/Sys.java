/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Main file
 */
package System;

import Animals.*;
import Animals.Base.Animal;
import Olympics.Medal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Sys class serves as the main entry point for the system, which manages a list of animals.
 */
public class Sys {
    private static Scanner scanner;
    static ArrayList<Animal> animalArrayList;

    /**
     * The main method initializes the animal list and presents a menu for user interaction.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        if (initAnimalList()) {
            System.out.println("Animal array has been initialized successfully.");
            menu();
        }
        else {
            System.out.println("Failed to initialize array list or list is empty.\nShutting down program...\nGood bye!");
            scanner.close();
            System.exit(0);
        }
    }
    /**
     * Displays a menu for the user to interact with the animal list.
     */
    private static void menu() {
        int userChoice;

        if (animalArrayList.isEmpty()) {
            System.out.println("Array list is empty, no actions can be preformed!\nExiting...");
            return;
        }
        System.out.println("Welcome to the System!");
        System.out.println("Please select an option:");

        while (true) {
            System.out.println("1. Information on animals\n2. Command to speak\n3. Exit");
            userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1 -> {
                    for (Animal animal : animalArrayList) {
                        System.out.println(animal.toString());
                    }
                }
                case 2 -> {
                    for (Animal animal : animalArrayList) {
                        System.out.println(animal.makeSound());
                    }
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }

    }
    /**
     * Initializes the animal list by creating instances of animals based on user input.
     *
     * @return true if the list is successfully initialized; false otherwise.
     */
    private static boolean initAnimalList() {
        animalArrayList = new ArrayList<>();
        int amountOfAnimals;

        System.out.println("Constructing animal array...\nEnter number of animal you want: ");
        amountOfAnimals = scanner.nextInt();
        for (int i = 0; i < amountOfAnimals; ++i) {
            animalArrayList.add(createInstanceOfAnimal());
        }
        return animalArrayList.size() == amountOfAnimals;
    }
    /**
     * Creates a specific instance of an animal based on the type and animal selected by the user.
     *
     * @param type   The type of animal (1: Terrestrial, 2: Water, 3: Air).
     * @param animal The specific animal within the type.
     * @return An instance of the selected animal.
     */
    private static Animal createSpecificInstance(int type, int animal) {
        scanner.nextLine();

        String name, areaOfLiving, breed, family, foodType;
        double weight, wingspan, diveDepth, altitudeOfFlight;
        boolean castrated, poisonous;

        System.out.println("Enter Name: ");
        name = scanner.nextLine();

        System.out.println("Enter weight: ");
        weight = scanner.nextDouble();

        switch (type) {
            case 1 -> { // Terrestrial Animals
                switch (animal) {
                    case 1 -> { // Dog
                        scanner.nextLine();

                        System.out.println("Enter breed: ");
                        breed = scanner.nextLine();

                        return new Dog(name, assignGender(), weight, createMedalArray(), breed);
                    }
                    case 2 -> { // Cat
                        scanner.nextLine();

                        System.out.println("Is castrated? (Y/N): ");

                        castrated = getBooleanByChoice();
                        return new Cat(name, assignGender(), weight, createMedalArray(), castrated);
                    }
                    case 3 -> { // Snake
                        scanner.nextLine();

                        System.out.println("Is poisonous? (Y/N): ");

                        poisonous = getBooleanByChoice();
                        return new Snake(name, assignGender(), weight, createMedalArray(), assignPoisonousLevel());
                    }
                }
            }
            case 2 -> { // Water Animals
                switch (animal) {
                    case 1 -> { // Alligator
                        scanner.nextLine();

                        System.out.println("Enter area of living: ");
                        areaOfLiving = scanner.nextLine();

                        return new Alligator(name, assignGender(), weight, createMedalArray(), areaOfLiving);
                    }
                    case 2 -> { // Whale
                        scanner.nextLine();

                        System.out.println("Enter food type: ");
                        foodType = scanner.nextLine();

                        return new Whale(name, assignGender(), weight,createMedalArray(), foodType);
                    }
                    case 3 -> { // Dolphin
                        return new Dolphin(name, assignGender(), weight, createMedalArray(), assignWaterType());
                    }
                }
            }
            case 3 -> { // Air Animals
                scanner.nextLine();

                System.out.println("Enter wingspan: ");
                wingspan = scanner.nextDouble();

                switch (animal) {
                    case 1 -> { // Eagle
                        scanner.nextLine();

                        System.out.println("Enter altitude of flight: ");
                        altitudeOfFlight = scanner.nextDouble();

                        return new Eagle(name, assignGender(), weight, createMedalArray(), wingspan, altitudeOfFlight);
                    }
                    case 2 -> { // Pigeon
                        scanner.nextLine();

                        System.out.println("Enter family type: ");
                        family = scanner.nextLine();

                        return new Pigeon(name, assignGender(), weight,createMedalArray(), wingspan, family);
                    }
                }
            }
        }
        return null;
    }
    /**
     * Prompts the user to input a yes/no answer and returns the corresponding boolean value.
     *
     * @return true for 'Y' or 'y', false for 'N' or 'n'.
     */
    private static boolean getBooleanByChoice() {
        char ans;
        boolean choice;

        while (true) {
            ans = scanner.next().charAt(0);
            if (ans == 'Y' || ans == 'y') {
                choice = true;
                break;
            }
            else if (ans == 'N' || ans == 'n') {
                choice = false;
                break;
            }
            else
                System.out.println("Invalid choice, try again");
        }
        return choice;
    }
    /**
     * Creates an instance of an animal based on the user's selection.
     *
     * @return An instance of the selected animal.
     */
    private static Animal createInstanceOfAnimal() {
        int type, animal;
        boolean flag;

        while (true) {
            flag = true;
            System.out.println("Choose Animal type:\n1. Terrestrial Animals\n2. Water Animals\n3. Air Animals");
            type = scanner.nextInt();

            switch (type) {
                case 1 -> {
                    while (flag) {
                        System.out.println("Choose Animal:\n1. Dog\n2. Cat\n3. Snake\n4. Go back");
                        animal = scanner.nextInt();

                        switch (animal) {
                            case 1, 2, 3 -> {
                                return createSpecificInstance(type, animal);
                            }
                            case 4 -> {
                                System.out.println("Going back...");
                                flag = false;
                            }
                            default -> System.out.println("Wrong input, try again.");
                        }
                    }
                }
                case 2 -> {
                    while (flag) {
                        System.out.println("Choose Animal:\n1. Alligator\n2. Whale\n3. Dolphin\n4. Go back");
                        animal = scanner.nextInt();

                        switch (animal) {
                            case 1, 2, 3 -> {
                                return createSpecificInstance(type, animal);
                            }
                            case 4 -> {
                                System.out.println("Going back...");
                                flag = false;
                            }
                            default -> System.out.println("Wrong input, try again.");
                        }
                    }
                }
                case 3 -> {
                    while (flag) {
                        System.out.println("1. Eagle\n2. Pigeon\n3. Go back");
                        animal = scanner.nextInt();

                        switch (animal) {
                            case 1, 2 -> {
                                return createSpecificInstance(type, animal);
                            }
                            case 3 -> {
                                System.out.println("Going back...");
                                flag = false;
                            }
                            default -> System.out.println("Wrong input, try again.");
                        }
                    }
                }
                default -> System.out.println("Wrong input, try again.");
            }
        }
    }
    /**
     * Creates a specific instance of a Medal based on user input.
     *
     * @return An instance of Medal.
     */
    private static Medal createSpecificInstanceOfMedal() {
        int medal, year;
        String tournament;

        scanner.nextLine();
        System.out.println("Enter tournament name: ");
        tournament = scanner.nextLine();

        System.out.println("Enter year: ");
        year = scanner.nextInt();

        while (true) {
            System.out.println("Enter Medal type [1: Bronze, 2: Silver, 3: Gold]: ");
            medal = scanner.nextInt();

            switch (medal) {
                case 1 -> { // Bronze
                    return new Medal(Medal.type.Bronze, tournament, year);
                }
                case 2 -> { // Silver
                    return new Medal(Medal.type.Silver, tournament, year);
                }
                case 3 -> { // Gold
                    return new Medal(Medal.type.Gold, tournament, year);
                }
                default -> System.out.println("Wrong input, try again.");
            }
        }
    }
    private static ArrayList<Medal> createMedalArray() {
        int amount;

        scanner.nextLine();
        System.out.println("Enter amount of medals: ");
        amount = scanner.nextInt();

        ArrayList<Medal> medals = new ArrayList<>();

        for (int i = 0; i < amount; ++i) {
            medals.add(createSpecificInstanceOfMedal());
        }
        return medals;
    }
    private static Animal.gender assignGender() {
        int gender;

        while (true) {
            System.out.println("Enter gender [1: Male, 2: Female, 3: Hermaphrodite]: ");
            gender = scanner.nextInt();

            switch (gender) {
                case 1 -> { // Male
                    return Animal.gender.Male;
                }
                case 2 -> { // Female
                    return Animal.gender.Female;
                }
                case 3 -> { // Hermaphrodite
                    return Animal.gender.Hermaphrodite;
                }
                default -> System.out.println("Wrong input, try again.");
            }
        }
    }
    private static Snake.poisonous assignPoisonousLevel() {
        int poisonous;

        while (true) {
            System.out.println("Enter poisonous level [1: Low, 2: Medium, 3: High]: ");
            poisonous = scanner.nextInt();

            switch (poisonous) {
                case 1 -> { // Low
                    return Snake.poisonous.Low;
                }
                case 2 -> { // Medium
                    return Snake.poisonous.Medium;
                }
                case 3 -> { // High
                    return Snake.poisonous.High;
                }
                default -> System.out.println("Wrong input, try again.");
            }
        }
    }
    private static Dolphin.waterType assignWaterType() {
        int waterType;

        while (true) {
            System.out.println("Enter water type [1: Sea, 2: Sweet]: ");
            waterType = scanner.nextInt();

            switch (waterType) {
                case 1 -> {
                    return Dolphin.waterType.Sea;
                }
                case 2 -> {
                    return Dolphin.waterType.Sweet;
                }
                default -> System.out.println("Wrong input, try again.");
            }
        }
    }
}