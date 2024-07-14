/**
 * Author: Daniel Nekludov
 * ID: 321984619
 *
 * Class Medal
 */
package Olympics;

import java.util.Objects;

public class Medal {
    /** Enum defining types of medals */
    public enum type {
        Bronze, Silver, Gold
    }
    /** The type of the medal (Bronze, Silver, or Gold) */
    private type type;
    /** The name of the tournament where the medal was awarded */
    private String tournament;
    /** The year in which the medal was awarded */
    private int year;
    /**
     * Constructs a Medal object with the specified type, tournament name, and year.
     *
     * @param type The type of the medal (Bronze, Silver, or Gold).
     * @param tournament The name of the tournament where the medal was awarded.
     * @param year The year in which the medal was awarded.
     */
    public Medal(type type, String tournament, int year){
        this.type = type;
        this.tournament = tournament;
        this.year = year;
    }
    /**
     * Returns a string representation of the medal in the format "(Medal type: type, Tournament: tournament, Year: year)".
     *
     * @return A string representation of the medal.
     */
    @Override
    public String toString() {
        return "(Medal type: " + type +
                " Tournament: " + tournament +
                " Year: " + year + ")";
    }
    /**
     * Checks if this medal is equal to another object.
     *
     * @param medal The object to compare with this medal.
     * @return true if the given object is a Medal with the same type, tournament, and year, false otherwise.
     */
    public boolean equals(Object medal) {
        if (this == medal) return true;
        if (!(medal instanceof Medal newMedal)) return false;
        return type == newMedal.type &&
                Objects.equals(tournament, newMedal.tournament) &&
                year == newMedal.year;
    }
}
