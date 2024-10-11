package backend.academy.hangman;

import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.List;
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
    private List<List<Word>> listWords;
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
        Category category;
        if(StringUtils.isNumeric(choiceCategory)) {
            category = Category.getById(Integer.parseInt(choiceCategory));
        }
        else {
            category = null;
        }

        switch (category) {
            case ANIMALS -> listWords = WordList.getAnimalList();
            case COUNTRIES -> listWords = WordList.getCountryList();
            case FRUITS -> listWords = WordList.getFruitList();
            case SPORTS -> listWords = WordList.getSportList();
            case RANDOM -> listWords = WordList.getRandomListWords();
            case null -> {
                out.print("Вы ввели неверные данные, "
                    + "попробуйте ещё раз, введите одну цифру - желаемую категорию: ");
                selectCategory(scanner.nextLine());
            }
        }
    }

    public void selectLvl(String choiceLvl) {
        Difficulty difficulty;
        if(StringUtils.isNumeric(choiceLvl)) {
            difficulty = Difficulty.getById(Integer.parseInt(choiceLvl));
        }
        else {
            difficulty = null;
        }

        switch (difficulty) {
            case EASY -> words = listWords.get(0);
            case MEDIUM -> words = listWords.get(1);
            case HARD -> words = listWords.get(2);
            case RANDOM -> words = listWords.get(random.nextInt(3));
            case null -> {
                out.print("Вы ввели неверные данные, попробуйте ещё раз, "
                    + "введите одну цифру - желаемый уровень сложности: ");
                selectLvl(scanner.nextLine());
            }
        }
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
