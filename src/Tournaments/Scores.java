package Tournaments;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Scores {
    private Map<String, Date> scores;

    public Scores() {
        scores = Collections.synchronizedMap(new HashMap<>());
    }
    public void add(String name) {
        Date d = new Date();
        scores.put(name, d);
        System.out.println("Name: " + name + "Date: " + d);
    }
    public Map<String, Date> getAll() {
        return scores;
    }
}
