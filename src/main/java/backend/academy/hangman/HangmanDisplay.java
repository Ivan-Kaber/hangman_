package backend.academy.hangman;

import java.io.PrintStream;

public class HangmanDisplay {
    private static final String[] HANGMAN = {

        """





 |
/|\\""", """

 |
 |
 |
 |
 |
/|\\""", """
 ______
 |
 |
 |
 |
 |
/|\\""",

        """
 ______
 |    |
 |
 |
 |
 |
/|\\""",

        """
 ______
 |    |
 |    O
 |
 |
 |
/|\\""",

        """
 ______
 |    |
 |    O
 |    |
 |    |
 |
/|\\""",

        """
 ______
 |    |
 |    O
 |   /|
 |    |
 |
/|\\""",

        """
 ______
 |    |
 |    O
 |   /|\\
 |    |
 |
/|\\""",

        """
 ______
 |    |
 |    O
 |   /|\\
 |    |
 |   /
/|\\""",

        """
 ______
 |    |
 |    O
 |   /|\\
 |    |
 |   / \\
/|\\""",
    };
    private final PrintStream out;

    public HangmanDisplay(PrintStream out) {
        this.out = out;
    }

    public void printHangman(int remainedMistakes) {
        if (remainedMistakes < 0 || remainedMistakes >= HANGMAN.length) {
            throw new IllegalArgumentException("Remained mistakes must be between 0 and " + (HANGMAN.length - 1));
        }
        out.print("\n" + HANGMAN[HANGMAN.length - remainedMistakes - 1] + "\n");
    }

}
