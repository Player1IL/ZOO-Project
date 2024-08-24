package Tournaments;

import Animals.Base.Animal;

import java.util.ArrayList;

public abstract class Tournament {
    protected TournamentThread thread;

    public Tournament(ArrayList<ArrayList<Animal>> animalGroup) {
        setup(animalGroup);
    }
    abstract void setup(ArrayList<ArrayList<Animal>> animalGroup);
    public TournamentThread getThread() {return thread;}
}
