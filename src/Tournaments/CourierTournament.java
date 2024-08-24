package Tournaments;

import Animals.Base.Animal;
import Animals.Thread.AnimalThread;
import Array.EnhancedLimitedArray;

import java.util.ArrayList;

public class CourierTournament extends Tournament {
    private Boolean startFlag;
    private ArrayList<Scores> scores;
    private ArrayList<Referee> referees;
    private ArrayList<ArrayList<Boolean>> flagGroup;
    ArrayList<ArrayList<Animal>> animalGroup;
    private Animal.Competition competitionArea;
    private EnhancedLimitedArray.Tournament type;

    public CourierTournament(ArrayList<ArrayList<Animal>> animalGroup, Animal.Competition competitionArea, EnhancedLimitedArray.Tournament type) {
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

        flagGroup = populateFlagArray(this.animalGroup);

        for (int i = 0; i < this.animalGroup.size(); ++i) {
            ArrayList<Animal> innerAnimalList = this.animalGroup.get(i);
            ArrayList<Boolean> innerFlagList = flagGroup.get(i);

            scores.add(new Scores());

            for (int j = 0; j < innerAnimalList.size(); ++j) {
                int finalI = i;
                Animal animal = innerAnimalList.get(j);

                if (j == innerAnimalList.size() - 1) {
                    System.out.println("Adding referee for " + animal.getAnimaleName());
                    referees.add(new Referee("Team: " + (finalI + 1), scores.get(finalI), innerFlagList.get(j)));

                    System.out.println("Creating AnimalThread for " + animal.getAnimaleName());
                    animal.setAnimalThread(new AnimalThread(animal, innerFlagList.get(j - 1), innerFlagList.get(j), referees.get(finalI)));

                    Thread refereeThread = new Thread(referees.get(finalI));
                    refereeThread.start();
                } else if (j == 0) {
                    System.out.println("Creating AnimalThread for " + animal.getAnimaleName());
                    animal.setAnimalThread(new AnimalThread(animal, startFlag, innerFlagList.get(j)));
                } else {
                    System.out.println("Creating AnimalThread for " + animal.getAnimaleName());
                    animal.setAnimalThread(new AnimalThread(animal, innerFlagList.get(j - 1), innerFlagList.get(j)));
                }
                Thread animalThread = new Thread(animal.getAnimalThread());
                animalThread.start();
            }
        }
        System.out.println("Creating Tournament Thread");
        this.thread = new TournamentThread(scores, startFlag);
        Thread tournamentThread = new Thread(this.thread);
        tournamentThread.start();
    }
    public ArrayList<ArrayList<Boolean>> populateFlagArray(ArrayList<ArrayList<Animal>> animalGroup) {
        ArrayList<ArrayList<Boolean>> flagGroup = new ArrayList<>();
        for (ArrayList<Animal> animalList : animalGroup) {
            ArrayList<Boolean> flagList = new ArrayList<>();
            for (Animal animal : animalList) {
                Boolean booleanObject = new Boolean("false");
                flagList.add(booleanObject);
            }
            flagGroup.add(flagList);
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
