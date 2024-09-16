package backend.academy.hangman;

import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

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
    private WordList wordList;
    private Word word;
    private int remainedMistakes;

    public GameLogic(PrintStream out, Scanner scanner) {
        this.out = out;
        this.scanner = scanner;
        wordList = new WordList();
        random = new SecureRandom();
        alphabet = new Alphabet();
        hangmanDisplay = new HangmanDisplay(out);
        remainedMistakes = MAX_MISTAKES;
    }

    public void selectCategory(String choiceCategory) {
        String category = choiceCategory;
        switch (category) {
            case "1" -> listWords = new WordList().getAnimalList();
            case "2" -> listWords = new WordList().getCountryList();
            case "3" -> listWords = new WordList().getFruitList();
            case "4" -> listWords = new WordList().getSportList();
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
