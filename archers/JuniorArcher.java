package archers;

import bows.WoodenBow;
import club.Club;
import util.Randomizer;
import java.util.Random;

public class JuniorArcher extends Archer {

    //Constructors:
    public JuniorArcher(String name, char gender, int age, WoodenBow bow, int yearsOfTraining) {
        super(name, gender, age, bow, yearsOfTraining);
    }

    //Override methods:
    @Override
    boolean validYearsOfTraining(int yearsOfTraining) {
        return yearsOfTraining >= 1;
    }

    @Override
    public int getArrowsNumber() {
        return 30;
    }

    @Override
    protected int getArrowScore() {
        if (new Random().nextInt(100 + 1) <= getMissChance()) {
            return 0;
        } else {
            return Randomizer.getRandomNumber(1, 10);
        }
    }

    @Override
    protected int getMissChance() {
        return 10;
    }

    @Override
    public int getType() {
        return Club.JUNIORS;
    }
}
