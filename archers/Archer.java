package archers;

import bows.IBow;
import util.Validator;
import java.util.Objects;

public abstract class Archer {

    //Characteristics
    public static final char FEMALE = 'F';
    public static final char MALE = 'M';

    private String name;
    private final char gender;
    private final int age;
    protected IBow bow;
    private int yearsOfTraining;
    private int pastTournaments;
    private Stats stats;

    //Constructors:
    public Archer(String name, char gender, int age, IBow bow, int yearsOfTraining) {
        if (Validator.textValidator(name)) {
            this.name = name;
        }
        if (gender == MALE || gender == FEMALE) {
            this.gender = gender;
        } else {
            this.gender = 'M';
        }
        if (age >= 12 && age <= 52) {
            this.age = age;
        } else {
            this.age = 33;
        }
        this.bow = bow;
        if (validYearsOfTraining(yearsOfTraining)) {
            this.yearsOfTraining = yearsOfTraining;
        }
        this.pastTournaments = 0;
    }

    //Abstract methods:
    abstract boolean validYearsOfTraining(int yearsOfTraining);
    public abstract int getArrowsNumber();
    protected abstract int getArrowScore();
    protected abstract int getMissChance();
    public abstract int getType();

    //Normal methods:
    public void attend() {
        stats = new Stats(getArrowsNumber());
        pastTournaments++;
    }

    public void shootArrows() {
        int totalScore = 0;
        for (int i = 0; i < getArrowsNumber(); i++) {
            int score = getArrowScore();
            if (score == 0) {
                stats.addMiss();
            }
            if (score == 10) {
                stats.addTen();
            }
            totalScore += score;
        }
        stats.setTotalPoints(totalScore);
    }

    public Stats getStats() {
        return stats;
    }

    public IBow getBow() {
        return bow;
    }

    //Getters and Setters:
    public int getYearsOfTraining() {
        return yearsOfTraining;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    //Override methods
    @Override
    public String toString() {
        return name + " - " + stats.getTotalPoints();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Archer archer = (Archer) o;
        return gender == archer.gender && age == archer.age && Objects.equals(name, archer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, age);
    }
}
