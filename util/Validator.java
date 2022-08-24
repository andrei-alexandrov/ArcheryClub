package util;

public abstract class Validator {

    public static boolean textValidator(String text) {
        return text != null && text.length() > 0;
    }

}
