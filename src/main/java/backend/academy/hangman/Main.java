package backend.academy.hangman;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.PrintStream;
import java.util.Scanner;

@SuppressFBWarnings("DM_DEFAULT_ENCODING")
public class Main {
    private Main() {}

    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out);
        Scanner scanner = new Scanner(System.in);
        GameInterface gameInterface = new GameInterface(out, scanner);
        gameInterface.startGame();
    }
}
