package backend.academy.hangman;

import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;

public class GameLogic {
    @Getter
    private static final int MIN_WORD_LENGTH = 3;
    @Getter
    private static final int MAX_WORD_LENGTH = 20;
    private static final int MAX_MISTAKES = 6;
    private final PrintStream out;
    @Getter
    private List<List<Word>> listWords;
    private WordList wordList;
    private final Scanner scanner;
    @Getter
    @Setter
    private List<Word> words;
    private final SecureRandom random;
    @Getter
    private Word word;
    @Setter
    private HiddenWord hiddenWord;
    @Setter
    private int remainedMistakes;

    public GameLogic(PrintStream out, Scanner scanner) {
        this.out = out;
        wordList = new WordList();
        this.scanner = scanner;
        random = new SecureRandom();
        remainedMistakes = MAX_MISTAKES;

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

    public void chooseRandomWord() {
        do {
            word = wordList.getRandomWord(words);
        } while (word.word().length() <= MIN_WORD_LENGTH || word.word().length() >= MAX_WORD_LENGTH);
        hiddenWord = new HiddenWord(word.word(), out);
    }

    public boolean isGameOver() {
        return isWin() || isLose();
    }

    public boolean isWin() {
        return hiddenWord.isWin();
    }

    public boolean isLose() {
        return remainedMistakes == 0;
    }

}
