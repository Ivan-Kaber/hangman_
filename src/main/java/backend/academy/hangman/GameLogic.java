package backend.academy.hangman;

import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Setter @Getter
public class GameLogic {
    @Getter
    private static final int MIN_WORD_LENGTH = 3;
    @Getter
    private static final int MAX_WORD_LENGTH = 20;
    private static final int MAX_MISTAKES = 6; //рекомендуемое количество ошибок от 6 до 9
    private final HangmanDisplay hangmanDisplay;
    private final SecureRandom random;
    private final Alphabet alphabet;
    private final PrintStream out;
    private final Scanner scanner;
    private Map<Difficulty, List<Word>> mapWords;
    private List<Word> words;
    private HiddenWord hiddenWord;
    private Word word;
    private int remainedMistakes;

    public GameLogic(PrintStream out, Scanner scanner) {
        this.out = out;
        this.scanner = scanner;
        random = new SecureRandom();
        alphabet = new Alphabet();
        hangmanDisplay = new HangmanDisplay(out);
        remainedMistakes = MAX_MISTAKES;
    }

    public void selectCategory(String choiceCategory) {
        Optional<Category> category;
        if (StringUtils.isNumeric(choiceCategory)) {
            category = Optional.ofNullable(Category.getById(Integer.parseInt(choiceCategory)));
        } else {
            category = Optional.empty();
        }

        category.ifPresentOrElse(c -> {
                switch (c) {
                    case ANIMALS -> mapWords = WordList.getANIMALS();
                    case COUNTRIES -> mapWords = WordList.getCOUNTRIES();
                    case FRUITS -> mapWords = WordList.getFRUITS();
                    case SPORTS -> mapWords = WordList.getSPORTS();
                    case RANDOM -> mapWords = WordList.getRandomListWords();
                    default -> throw new RuntimeException("Unexpected category: " + c);
                }
            },
            () -> {
                out.print("Вы ввели неверные данные, "
                    + "попробуйте ещё раз, введите одну цифру - желаемую категорию: ");
                selectCategory(scanner.nextLine());
            });
    }

    public void selectLvl(String choiceLvl) {
        Optional<Difficulty> difficulty;
        if (StringUtils.isNumeric(choiceLvl)) {
            difficulty = Optional.ofNullable(Difficulty.getById(Integer.parseInt(choiceLvl)));
        } else {
            difficulty = Optional.empty();
        }

        difficulty.ifPresentOrElse(c -> {
            switch (c) {
                case EASY -> words = mapWords.get(Difficulty.EASY);
                case MEDIUM -> words = mapWords.get(Difficulty.MEDIUM);
                case HARD -> words = mapWords.get(Difficulty.HARD);
                case RANDOM -> words = mapWords.get(
                    Difficulty.values()[random.nextInt(Difficulty.values().length - 1)]); //not considering RANDOM
                default -> throw new RuntimeException("Unexpected difficulty: " + c);
            }
        }, () -> {
            out.print("Вы ввели неверные данные, попробуйте ещё раз, "
                + "введите одну цифру - желаемый уровень сложности: ");
            selectLvl(scanner.nextLine());
        });
    }

    public void chooseRandomWord() {
        do {
            word = WordList.getRandomWord(words);
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

    public String getAlphabetState() {
        return alphabet.toString();
    }

    public boolean isCyrillic(String letter) {
        return (Pattern.compile("[а-яА-Я]").matcher(letter).matches());
    }

    public boolean isAlreadyUsed(String letter) {
        return hiddenWord.isAlreadyUsed(letter);
    }

    public void printHiddenWord() {
        hiddenWord.printHiddenWord();
    }

    public void printHangman() {
        hangmanDisplay.printHangman(remainedMistakes);
    }

    public void handleGuess(String letter) {
        alphabet.deleteLetter(letter);
        hiddenWord.fillAlreadyUsedLetters(letter);

        if (!hiddenWord.containsLetter(letter)) {
            remainedMistakes--;
            out.println("\nНеверно");
        } else {
            hiddenWord.updateHiddenWord();
        }
    }
}
