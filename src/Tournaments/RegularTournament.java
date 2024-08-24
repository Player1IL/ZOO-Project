package Tournaments;

import Animals.Base.Animal;
import Animals.Thread.AnimalThread;
import Array.EnhancedLimitedArray;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class RegularTournament extends Tournament {
    private Boolean startFlag;
    private ArrayList<Scores> scores;
    private ArrayList<Referee> referees;
    private ArrayList<Boolean> flagGroup;
    private ArrayList<ArrayList<Animal>> animalGroup;
    private Animal.Competition competitionArea;
    private EnhancedLimitedArray.Tournament type;

    public RegularTournament(ArrayList<ArrayList<Animal>> animalGroup, Animal.Competition competitionArea, EnhancedLimitedArray.Tournament type) {
        super(animalGroup);
        this.competitionArea = competitionArea;
        this.type = type;
    }

    @Override
    void setup(ArrayList<ArrayList<Animal>> animalGroup) {
        this.animalGroup = animalGroup;

        startFlag = Boolean.FALSE;
        scores = new ArrayList<>();
        referees = new ArrayList<>();

        flagGroup = populateFlagArray(this.animalGroup.size());

        for (int i = 0; i < this.animalGroup.size(); ++i) {
            ArrayList<Animal> innerAnimalList = this.animalGroup.get(i);

            scores.add(new Scores());

            for (Animal animal : innerAnimalList) {
                int finalI = i; // For thread

                System.out.println("Adding referee for " + animal.getAnimaleName());
                referees.add(new Referee("Runner: " + (finalI + 1), scores.get(finalI), flagGroup.get(finalI)));

                System.out.println("Creating AnimalThread for " + animal.getAnimaleName());
                animal.setAnimalThread(new AnimalThread(animal, startFlag, flagGroup.get(finalI), referees.get(finalI)));

                Thread animalThread = new Thread(animal.getAnimalThread());
                animalThread.start();

                Thread refereeThread = new Thread(referees.get(finalI));
                refereeThread.start();
            }
        }
        System.out.println("Creating Tournament Thread");
        this.thread = new TournamentThread(scores, startFlag);
        Thread tournamentThread = new Thread(this.thread);
        tournamentThread.start();
    }

    public ArrayList<Boolean> populateFlagArray(int size) {
        ArrayList<Boolean> flagGroup = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            Boolean booleanObject = Boolean.FALSE;
            flagGroup.add(booleanObject);
        }
        return flagGroup;
    }

    public void start() {
        thread.run();
    }

    public String returnCompetitionArea() {
        return competitionArea.toString();
    }

    public String returnTournamentType() {
        return type.toString();
    }
}
