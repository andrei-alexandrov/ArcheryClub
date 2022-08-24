package bows;

public class CarbonBow extends Bow implements ISeniorBow {

    private static final int SIGHT_BONUS = 2;
    private static final int STABILIZER_BONUS = 1;

    //Constructors:
    public CarbonBow(String manufacturer, double weight, int strength) {
        super(manufacturer, weight, strength);
    }

    //Override methods:
    @Override
    public String getType() {
        return BOW_TYPE_CARBON;
    }

    @Override
    boolean validateStrength(int strength) {
        return strength >= 28 && strength <= 48;
    }

    @Override
    public int getBonusPoints() {
        return SIGHT_BONUS + STABILIZER_BONUS;
    }
}
