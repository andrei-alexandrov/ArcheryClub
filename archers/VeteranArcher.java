package archers;

import bows.CarbonBow;
import club.Club;
import util.Randomizer;
import java.util.Random;

public class VeteranArcher extends Archer {

    //Constructors:
    public VeteranArcher(String name, char gender, int age, CarbonBow bow, int yearsOfTraining) {
        super(name, gender, age, bow, yearsOfTraining);
    }

    //Override methods:
    @Override
    boolean validYearsOfTraining(int yearsOfTraining) {
        return yearsOfTraining >= 10;
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
        return 0;
    }

    @Override
    public int getType() {
        return Club.VETERANS;
    }
}
