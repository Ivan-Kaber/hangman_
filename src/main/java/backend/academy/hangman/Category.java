package backend.academy.hangman;

import java.security.SecureRandom;

public enum Category {
    ANIMALS, CITIES, FRUITS, COUNTRIES, SPORTS;

    public static Category getRandomCategory() {
        return values()[new SecureRandom().nextInt(values().length)];
    }
}
