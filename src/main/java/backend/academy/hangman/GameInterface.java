package backend.academy.hangman;

import java.io.PrintStream;
import java.util.Scanner;

public class GameInterface {
    private final PrintStream out;
    private final Scanner scanner;
    private final GameLogic gameLogic;


    public GameInterface(PrintStream out, Scanner scanner) {
        this.out = out;
        this.scanner = scanner;
        this.gameLogic = new GameLogic(out, scanner);
    }

    public void startGame() {
        String choiceCategory;
        out.print("""
                Выберите категорию слов
                1)Животные
                2)Страны
                3)Фрукты
                4)Виды спорта
                5)Случайная категория
                Введите одну цифру - желаемую категорию:\s""");
        choiceCategory = scanner.nextLine();
        gameLogic.selectCategory(choiceCategory);
    }
}
