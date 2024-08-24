package Tournaments;

import Animals.Base.Animal;

import java.util.Stack;

public class Referee implements Runnable {

    private static int sleepTime = 1000;

    private String name;
    private Scores scores;
    private final Stack<Animal> stack = new Stack<Animal>();
    private boolean working;
    private Boolean finishFlag;

    public Referee(String name, Scores scores, Boolean flag) {
        this.name = name;
        this.scores = scores;
        this.finishFlag = flag;
        working = true;
    }
    public void animalArrival(Animal animal) {
        stack.push(animal);
    }
    //public void finishedJob(boolean set) {if (set) {working = false;}}

    @Override
    public void run() {
        try {
            synchronized (finishFlag) {
                while (!finishFlag) {
                    while (stack.empty()) {
                        System.out.println("Referee waiting for animal");
                        finishFlag.wait();
                        Thread.sleep(sleepTime);
                    }
                    if (!stack.empty()) {
                        System.out.println("Animal reached referee");
                        scores.add(stack.pop().getAnimaleName());
                        break;
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Referee done working");
    }
}
