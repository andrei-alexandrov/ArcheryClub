package util;

import java.util.Random;

public abstract class Randomizer {

    public static final String[] MALE_NAMES = {"Andrei", "Ivan", "Georgi", "Mihail", "Tom"};
    public static final String[] FEMALE_NAMES = {"Tedi", "Desi", "Lili", "Bibi", "Bebi"};

    public static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static String randomMaleName() {
        return MALE_NAMES[getRandomNumber(0, MALE_NAMES.length - 1)];
    }

    public static String randomFemaleName() {
        return FEMALE_NAMES[getRandomNumber(0, FEMALE_NAMES.length - 1)];
    }

}
