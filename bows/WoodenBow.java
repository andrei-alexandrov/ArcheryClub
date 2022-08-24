package bows;

public class WoodenBow extends Bow {

    //Constructors:
    public WoodenBow(String manufacturer, double weight, int strength) {
        super(manufacturer, weight, strength);
    }

    //Override methods:
    @Override
    public String getType() {
        return BOW_TYPE_WOODEN;
    }

    @Override
    boolean validateStrength(int strength) {
        return strength >= 20 && strength <= 30;
    }

    @Override
    public int getBonusPoints() {
        return 0;
    }
}
