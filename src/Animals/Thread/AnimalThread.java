package Animals.Thread;

import Animals.Base.Animal;
import Tournaments.Referee;

public class AnimalThread implements Runnable {

    private static int sleepTime = 1000;

    private Animal participant;
    private Boolean startFlag;
    private Boolean finishFlag;
    private Referee referee;
    private boolean running = true;

    public AnimalThread(Animal animal, Boolean startFlag, Boolean finishFlag, Referee referee) {
        this.participant = animal;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
        this.referee = referee;
    }

    public AnimalThread(Animal animal, Boolean startFlag, Boolean finishFlag) {
        this.participant = animal;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
        this.referee = null;
    }

    public boolean changeSleepTime(int sleepTime) {
        if (AnimalThread.sleepTime == sleepTime || sleepTime <= 0) {
            return false;
        } else {
            AnimalThread.sleepTime = sleepTime;
            return true;
        }
    }

    @Override
    public void run() {
        System.out.println(participant.getAnimaleName() + " " +  startFlag.hashCode());
        try {
            synchronized (startFlag) {
                System.out.println("Animal " + participant.getAnimaleName() + " waiting for start");
                startFlag.wait();
                System.out.println("Animal " + participant.getAnimaleName() + " Got start mark!");
            }
            while (running) {
                synchronized (participant.getLock()) {
                    System.out.println("Animal " + participant.getAnimaleName() + " waiting for food!");
                    participant.getLock().wait();
                    new Thread(() -> {
                        participant.race();
                    }).start();
                    Thread.sleep(sleepTime);
                }
                if (participant.finishedRace()) {
                    synchronized (finishFlag) {
                        if (referee != null) {
                            System.out.println("Animal: " + participant.getAnimaleName() + " reached finish line!");
                            referee.animalArrival(participant);
                            finishFlag.notifyAll();
                            //finishFlag = Boolean.TRUE;
                            running = false;
                        } else {
                            System.out.println("Animal: " + participant.getAnimaleName() + " reached teammate, passing baton!");
                            finishFlag.notifyAll(); // ADDED HERE FOR NOW
                            running = false;
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("AnimalThread finished working");
    }
}
