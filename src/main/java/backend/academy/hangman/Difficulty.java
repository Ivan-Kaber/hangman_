package backend.academy.hangman;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Difficulty {
    EASY("Легко"), MEDIUM("Средне"), HARD("Сложно"), RANDOM("Случайный уровень сложности");

    private final int id;
    private final String title;

    Difficulty(String title) {
        this.id = ordinal() + 1;
        this.title = title;
    }

    public static String getOrderView() {
        return Arrays.stream(values())
            .map(c -> String.format("%s) %s", c.id, c.title))
            .collect(Collectors.joining(System.lineSeparator()));
    }
}
