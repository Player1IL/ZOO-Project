package Array;

import Animals.Base.Animal;

import java.util.ArrayList;

public class EnhancedLimitedArray {
    public enum Tournament {
        Regular, Courier
    }
    private Tournament type;
    private ArrayList<ArrayList<Animal>> array;
    private Animal.Competition competition;
    private int teams;
    private int participants;
    private boolean started = false;

    public EnhancedLimitedArray(Tournament type, ArrayList<ArrayList<Animal>> array, Animal.Competition competition, int teams, int participants) {
        this.type = type;
        this.array = array; // Multi-Dimensional Array of Animals
        this.competition = competition; // Competition type
        this.teams = teams; // Max number of teams in array
        this.participants = participants; // Max number of participants in each team
    }
    public void startTournament(boolean state) {
        started = state;
    }
    public ArrayList<ArrayList<Animal>> getArray() {
        return array;
    }
    public Animal.Competition getCompetition() {
        return competition;
    }
    public int getTeamsSize() {
        return teams;
    }
    public int getParticipantsSize() {
        return participants;
    }
    public Tournament getTournamentType() {return type;}
    public String tournamentFullRoster() {
        StringBuilder msg = new StringBuilder();

        for (int i = 0; i < teams; ++i) {
            ArrayList<Animal> innerAnimalList = this.array.get(i);
             msg.append("Team ").append(i + 1).append(": ");
            for (Animal animal : innerAnimalList) {
                if (animal != null) {
                    msg.append(animal.getAnimaleName()).append(" ");
                }
            }
            msg.append("\n");
        }
        return msg.toString();
    }
}
