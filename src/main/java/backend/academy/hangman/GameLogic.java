package backend.academy.hangman;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;

public class GameLogic {
    private final PrintStream out;
    @Getter
    private List<List<Word>> listWords;
    private final WordList wordList;
    private final Scanner scanner;

    public GameLogic(PrintStream out, Scanner scanner) {
        this.out = out;
        this.wordList = new WordList();
        this.scanner = scanner;

    }

    public void selectCategory(String choiceCategory) {
        String category = choiceCategory;
        switch (category) {
            case "1" -> listWords = new WordList().animalList();
            case "2" -> listWords = new WordList().countryList();
            case "3" -> listWords = new WordList().fruitList();
            case "4" -> listWords = new WordList().sportList();
            case "5" -> listWords = new WordList().getRandomListWords();
            default -> {
                out.print("Вы ввели неверные данные, попробуйте ещё раз, "
                        + "введите одну цифру - желаемую категорию: ");
                category = scanner.nextLine();
                selectCategory(category);
            }
        }
    }

}
