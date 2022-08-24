import archers.Archer;
import archers.JuniorArcher;
import archers.SeniorArcher;
import archers.VeteranArcher;
import bows.*;
import club.Club;
import util.Randomizer;

import java.util.Random;

public class Demo {
    public static void main(String[] args) {

        System.out.println("===================== Creating club =====================");
        Club archerClub = new Club("It Archer", "Sofia, Bulgaria", "Andrei");
        for (int i = 0; i < 40; i++) {
            char gender = new Random().nextBoolean() ? Archer.MALE : Archer.FEMALE;
            int age = Randomizer.getRandomNumber(12, 52);
            int chance = Randomizer.getRandomNumber(1, 3);
            String name = gender == Archer.MALE ? Randomizer.randomMaleName() : Randomizer.randomFemaleName();

            int woodenBowWeight = Randomizer.getRandomNumber(1, 2);
            int aluminiumBowWeight = Randomizer.getRandomNumber(3, 4);
            int carbonBowWeight = Randomizer.getRandomNumber(4, 5);

            int woodenBowStrength = Randomizer.getRandomNumber(20, 30);
            int aluminiumBowStrength = Randomizer.getRandomNumber(25, 40);
            int carbonBowStrength = Randomizer.getRandomNumber(28, 48);

            int juniorYearsOfTraining = Randomizer.getRandomNumber(0, 2);
            int seniorYearsOfTraining = Randomizer.getRandomNumber(3, 9);
            int veteranYearsOfTraining = Randomizer.getRandomNumber(10, 90);

            Archer archer;

            switch (chance) {
                case 1 -> {
                    archer = new JuniorArcher(
                            name,
                            gender,
                            age,
                            new WoodenBow("SacredWood", woodenBowWeight, woodenBowStrength),
                            juniorYearsOfTraining);
                    archerClub.addArcher(archer);
                }
                case 2 -> {
                    IBow bow = new Random().nextBoolean() ?
                            new AluminiumBow("Aluminium Paradise", aluminiumBowWeight, aluminiumBowStrength) :
                            new CarbonBow("Carbon Star", carbonBowWeight, carbonBowStrength);
                    archer = new SeniorArcher(name, gender, age, (ISeniorBow) bow, seniorYearsOfTraining);
                    archerClub.addArcher(archer);
                }
                case 3 -> {
                    archer = new VeteranArcher(
                            name,
                            gender,
                            age,
                            new CarbonBow("Carbon Star", carbonBowWeight, carbonBowStrength),
                            veteranYearsOfTraining);
                    archerClub.addArcher(archer);
                }
            }
        }

        archerClub.startTournament();
    }
}
