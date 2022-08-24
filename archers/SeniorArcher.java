package archers;

import bows.ISeniorBow;
import club.Club;
import util.Randomizer;
import java.util.Random;

public class SeniorArcher extends Archer {

    //Constructors
    public SeniorArcher(String name, char gender, int age, ISeniorBow bow, int yearsOfTraining) {
        super(name, gender, age, bow, yearsOfTraining);
    }

    //Override Methods:
    @Override
    boolean validYearsOfTraining(int yearsOfTraining) {
        return yearsOfTraining >= 3;
    }

    @Override
    public int getArrowsNumber() {
        return 60;
    }

    @Override
    protected int getArrowScore() {
        if (new Random().nextInt(100 + 1) <= getMissChance()) {
            return 0;
        } else {
            int score = Randomizer.getRandomNumber(6, 10);
            if (score < 10) {
                score += bow.getBonusPoints();
            }
            if (score > 10) {
                score = 10;
            }
            return score;
        }
    }

    @Override
    protected int getMissChance() {
        return 5;
    }

    @Override
    public int getType() {
        return Club.SENIORS;
    }
}
