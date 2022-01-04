package archery.util;

import java.util.Random;

public class Randomizer {

    private static final String[] MALE_NAMES = {"Miha", "Mario", "John", "Marc", "Tim", "Jordan", "Pavel"};
    private static final String[] FEMALE_NAMES = {"Monica", "Maria", "Geri", "Tedi", "Lilian"};

    public static int random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static String randomMaleName() {
        return MALE_NAMES[random(0, MALE_NAMES.length - 1)];
    }

    public static String randomFemaleName() {
        return FEMALE_NAMES[random(0, FEMALE_NAMES.length - 1)];
    }
}
