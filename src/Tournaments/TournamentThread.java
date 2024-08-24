package Tournaments;

import java.util.ArrayList;

public class TournamentThread implements Runnable {
    private ArrayList<Scores> scores;
    private Boolean startSignal;
    private int groups;
    private boolean working = true;
    private static int sleepTime = 1000;

    public TournamentThread(ArrayList<Scores> scores, Boolean startSignal) {
        this.scores = scores;
        this.startSignal = startSignal;
        groups = scores.size();
    }

    @Override
    public void run() {
        System.out.println(startSignal.hashCode());
        synchronized (startSignal) {
            startSignal.notifyAll();
            System.out.println("Tournament started!");
            //startSignal = Boolean.TRUE;
        }
        while (working) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // DO SOMETHING
        }
        System.out.println("Tournament finished working");
    }
    public ArrayList<Scores> returnScores() {
        return scores;
    }
}
