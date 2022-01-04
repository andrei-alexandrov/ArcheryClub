package archery.bows;

public class AluminumBow extends Bow implements ISeniorBow {

    private static final int SIGHT_BONUS = 1;

    public AluminumBow(String manufacturer, double weight, int strength) {
        super(manufacturer, weight, strength);
    }

    @Override
    public String getType() {
        return BOW_TYPE_ALUMINUM;
    }

    @Override
    boolean validateStrength(int strength) {
        return strength >= 25 && strength <= 40;
    }

    @Override
    public int getBonusPoints() {
        return SIGHT_BONUS;
    }
}
