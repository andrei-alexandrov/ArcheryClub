package bows;

import util.Validator;

public abstract class Bow implements IBow {

    //Characteristics:
    public static final String BOW_TYPE_CARBON = "Carbon";
    public static final String BOW_TYPE_WOODEN = "Wooden";
    public static final String BOW_TYPE_ALUMINUM = "Aluminum";

    private String manufacturer;
    private double weight;
    private int strength;
    private String type;

    //Constructors:
    public Bow(String manufacturer, double weight, int strength) {
        if (validateStrength(strength)) {
            this.strength = strength;
        } else {
            this.strength = 28;
        }
        if (Validator.textValidator(manufacturer)) {
            this.manufacturer = manufacturer;
        }
        if (weight > 0) {
            this.weight = weight;
        }
    }

    //Abstract methods:
    abstract boolean validateStrength(int strength);
}
