package backend.academy.hangman;

import java.io.PrintStream;
import java.util.Scanner;

public class GameInterface {
    private final Scanner scanner;
    private final PrintStream out;
    private GameLogic gameLogic;
    private String hint;
    private String letter;

    public GameInterface(PrintStream out, Scanner scanner) {
        this.out = out;
        this.scanner = scanner;
        gameLogic = new GameLogic(out, scanner);
    }

    public void startGame() {
        String choiceCategory;
        /*Заменил тестовый блок на строку, т.к. чекстайл выдавал VA_FORMAT_STRING_USES_NEWLINE из-за \n,
        который автоматически устанавливается при переносе строки. Были другие варианты:
        использовать SuppressWarnings, либо писать все в одну строку в виде: """abcd%n%s%nefg"""*/
        out.printf("Выберите категорию слов%n"
            + "%s%n"
            + "Введите одну цифру - желаемую категорию: ", Category.getOrderView());
        choiceCategory = scanner.nextLine();
        gameLogic.selectCategory(choiceCategory);

        String choiceLvl;
        out.printf("%nВыберите уровень сложности слова:%n"
            + "%s %n"
            + "Введите одну цифру - желаемый уровень сложности: ", Difficulty.getOrderView());
        choiceLvl = scanner.nextLine();
        gameLogic.selectLvl(choiceLvl);

        gameLogic.chooseRandomWord();
        while (!gameLogic.isGameOver()) {
            printState();

            out.print("Введите одну букву: ");
            letter = scanner.nextLine();

            validateAndProcessGuess(letter);
            gameLogic.handleGuess(letter);

            if (gameLogic.isWin()) {
                out.println("\nПоздравляем! Вы выиграли! Загаданное слово: " + gameLogic.getWord().word());
            }
            if (gameLogic.isLose()) {
                gameLogic.printHangman();
                out.println("Вы проиграли, загаданное слово: " + gameLogic.getWord().word());
            }
        }
    }

    public void printState() {
        gameLogic.printHangman();

        out.print("Слово: ");
        gameLogic.printHiddenWord();

        out.print("\n" + gameLogic.getAlphabetState());
        out.println("Попыток осталось: " + gameLogic.getRemainedMistakes());
        if (!isHintAlreadyUsed()) {
            out.println("Если хотите увидеть подсказку, введите '1'");
        } else {
            out.println("'" + hint + "'");
        }
    }

    public void validateAndProcessGuess(String letter) {
        if ("1".equals(letter)) {
            hint = gameLogic.getWord().hint();
            out.println(hint);
        }
        while (!gameLogic.isCyrillic(this.letter)) {
            out.print("Пожалуйста, введите одну букву русского алфавита: ");
            this.letter = scanner.nextLine();
        }
        while (gameLogic.isAlreadyUsed(this.letter)) {
            out.print("Вы уже вводили эту букву, попробуйте другую: ");
            this.letter = scanner.nextLine();
        }
    }

    public boolean isHintAlreadyUsed() {
        return hint != null;
    }
}
