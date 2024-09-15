package backend.academy.hangman;

import java.io.PrintStream;

public class HangmanDisplay {
    private static final String[] HANGMAN = {
        """
  _____
 |
 |
 |
 |
 |
/|\\""",

        """
  _____
 |    |
 |
 |
 |
 |
/|\\""",

        """
  _____
 |    |
 |    O
 |
 |
 |
/|\\""",

        """
  _____
 |    |
 |    O
 |    |
 |    |
 |
/|\\""",

        """
  _____
 |    |
 |    O
 |   /|
 |    |
 |
/|\\""",

        """
  _____
 |    |
 |    O
 |   /|\\
 |    |
 |
/|\\""",

        """
  _____
 |    |
 |    O
 |   /|\\
 |    |
 |   /
/|\\""",

        """
  _____
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
        out.print(HANGMAN[HANGMAN.length - remainedMistakes - 1]);
    }

}
