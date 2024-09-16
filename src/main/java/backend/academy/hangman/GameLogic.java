package backend.academy.hangman;

import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;

public class GameLogic {
    private final PrintStream out;
    @Getter
    private List<List<Word>> listWords;
    private final WordList wordList;
    private final Scanner scanner;
    @Getter
    private List<Word> words;
    private final SecureRandom random;

    public GameLogic(PrintStream out, Scanner scanner) {
        this.out = out;
        wordList = new WordList();
        this.scanner = scanner;
        random = new SecureRandom();

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
                out.print("Вы ввели неверные данные, "
                        + "попробуйте ещё раз, введите одну цифру - желаемую категорию: ");
                category = scanner.nextLine();
                selectCategory(category);
            }
        }
    }

    @SuppressWarnings("MagicNumber")
    public void selectLvl(String choiceLvl) {
        String lvl = choiceLvl;
        switch (lvl) {
            case "1" -> words = listWords.get(0);
            case "2" -> words = listWords.get(1);
            case "3" -> words = listWords.get(2);
            case "4" -> words = listWords.get(random.nextInt(3));
            default -> {
                out.print("Вы ввели неверные данные, попробуйте ещё раз, "
                        + "введите одну цифру - желаемый уровень сложности: ");
                lvl = scanner.nextLine();
                selectLvl(lvl);
            }
        }
    }

}
