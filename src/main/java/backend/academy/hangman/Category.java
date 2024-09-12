package backend.academy.hangman;

import java.util.Random;

public enum Category {
    ANIMALS, CITIES, FRUITS, COUNTRIES, SPORTS;

    public static Category getRandomCategory() {
        return values()[new Random().nextInt(values().length)];
    }
}
