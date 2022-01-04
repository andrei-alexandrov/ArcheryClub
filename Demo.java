package archery;

import archery.archers.JuniorArcher;
import archery.archers.SeniorArcher;
import archery.archers.VeteranArcher;
import archery.archers.Archer;
import archery.bows.AluminumBow;
import archery.util.Randomizer;
import archery.bows.CarbonBow;
import archery.bows.ISeniorBow;
import archery.bows.WoodenBow;

import java.util.Random;

public class Demo {

    public static void main(String[] args) {
        System.out.println("====================== Creating club =========================");
        Club club = new Club("IT Archer", "Sofia", "Andrei");
        System.out.println(club);
        System.out.println("====================== Creating archers ======================");
        for (int i = 0; i < 40; i++) {
            char gender = new Random().nextBoolean() ? Archer.MALE : Archer.FEMALE;
            int age = Randomizer.random(12, 52);
            int chance = Randomizer.random(1, 3);
            int bowStrength = Randomizer.random(20, 48);
            int bowWeight = Randomizer.random(1, 3);
            String name = gender == Archer.MALE ? Randomizer.randomMaleName() : Randomizer.randomFemaleName();
            Archer archer;
            switch (chance) {
                case 1:

                    archer = new JuniorArcher(
                            name,
                            gender,
                            age,
                            new WoodenBow(
                                    "Hoyt",
                                    bowWeight,
                                    bowStrength),
                            1);
                    club.addArcher(archer);
                    break;
                case 2:
                    ISeniorBow bow = new Random().nextBoolean() ?
                            new AluminumBow("Hoyt", bowWeight, bowStrength) :
                            new CarbonBow("Hoyt", bowWeight, bowStrength);
                    archer = new SeniorArcher(
                            name,
                            gender,
                            age,
                            bow,
                            Randomizer.random(3, 8));
                    club.addArcher(archer);
                    break;
                case 3:
                    archer = new VeteranArcher(
                            name,
                            gender,
                            age,
                            new CarbonBow(
                                    "Hoyt",
                                    bowWeight,
                                    bowStrength),
                            Randomizer.random(10, 20));
                    club.addArcher(archer);
                    break;
            }
        }
        club.startTournament();
    }
}
