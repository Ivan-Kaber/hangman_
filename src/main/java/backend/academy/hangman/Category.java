package backend.academy.hangman;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Category {
    ANIMALS("Животные"), FRUITS("Фрукты"), COUNTRIES("Страны"),
    SPORTS("Виды спорта"), RANDOM("Случайная категория");

    private final int id;
    private final String title;

    Category(String title) {
        this.id = ordinal() + 1;
        this.title = title;
    }

    public static String getOrderView() {
        return Arrays.stream(values())
            .map(c -> String.format("%s) %s", c.id, c.title))
            .collect(Collectors.joining(System.lineSeparator()));
    }

    public static Category getById(int i) {
        return Arrays.stream(values())
            .filter(c -> c.id == i)
            .findFirst()
            .orElse(null);
    }
}
