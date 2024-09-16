package backend.academy.hangman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class HangmanDisplayTest {
    ByteArrayOutputStream byteArrayOutputStream;
    HangmanDisplay hangmanDisplay;

    @BeforeEach
    void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        hangmanDisplay = new HangmanDisplay(printStream);
    }

    @Test
    void printHangman_remainedNineMistakes() {
        hangmanDisplay.printHangman(9);

        assertEquals("""






             |
            /|\\
            """, byteArrayOutputStream.toString());
    }

    @Test
    void printHangman_remainedEightMistakes() {
        hangmanDisplay.printHangman(8);

        assertEquals("""


             |
             |
             |
             |
             |
            /|\\
            """, byteArrayOutputStream.toString());
    }

    @Test
    void printHangman_remainedSevenMistakes() {
        hangmanDisplay.printHangman(7);

        assertEquals("""

             ______
             |
             |
             |
             |
             |
            /|\\
            """, byteArrayOutputStream.toString());
    }

    @Test
    void printHangman_remainedSixMistakes() {
        hangmanDisplay.printHangman(6);

        assertEquals("""

             ______
             |    |
             |
             |
             |
             |
            /|\\
            """, byteArrayOutputStream.toString());
    }

    @Test
    void printHangman_remainedFiveMistakes() {
        hangmanDisplay.printHangman(5);

        assertEquals("""

             ______
             |    |
             |    O
             |
             |
             |
            /|\\
            """, byteArrayOutputStream.toString());
    }

    @Test
    void printHangman_remainedFourMistakes() {
        hangmanDisplay.printHangman(4);

        assertEquals("""

             ______
             |    |
             |    O
             |    |
             |    |
             |
            /|\\
            """, byteArrayOutputStream.toString());
    }

    @Test
    void printHangman_remainedThreeMistakes() {
        hangmanDisplay.printHangman(3);

        assertEquals("""

             ______
             |    |
             |    O
             |   /|
             |    |
             |
            /|\\
            """, byteArrayOutputStream.toString());
    }

    @Test
    void printHangman_remainedTwoMistakes() {
        hangmanDisplay.printHangman(2);

        assertEquals("""

             ______
             |    |
             |    O
             |   /|\\
             |    |
             |
            /|\\
            """, byteArrayOutputStream.toString());
    }

    @Test
    void printHangman_remainedOneMistakes() {
        hangmanDisplay.printHangman(1);

        assertEquals("""

             ______
             |    |
             |    O
             |   /|\\
             |    |
             |   /
            /|\\
            """, byteArrayOutputStream.toString());
    }

    @Test
    void printHangman_remainedZeroMistakes() {
        hangmanDisplay.printHangman(0);

        assertEquals("""

             ______
             |    |
             |    O
             |   /|\\
             |    |
             |   / \\
            /|\\
            """, byteArrayOutputStream.toString());
    }

    @Test
    void printHangman_remainedMoreThanNineMistakesException() {
        assertThrows(IllegalArgumentException.class, () -> hangmanDisplay.printHangman(10));
    }

    @Test
    void printHangman_remainedLessThanZeroMistakesException() {
        assertThrows(IllegalArgumentException.class, () -> hangmanDisplay.printHangman(-1));
    }

}
