package backend.academy.hangman;

import org.junit.jupiter.api.BeforeAll;
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
    void testPrintHangmanRemainedSevenMistakes() {
        hangmanDisplay.printHangman(7);

        assertEquals("""
                          _____
                         |
                         |
                         |
                         |
                         |
                        /|\\""", byteArrayOutputStream.toString());
    }

    @Test
    void testPrintHangmanRemainedSixMistakes() {
        hangmanDisplay.printHangman(6);

        assertEquals("""
                          _____
                         |    |
                         |
                         |
                         |
                         |
                        /|\\""", byteArrayOutputStream.toString());
    }

    @Test
    void testPrintHangmanRemainedFiveMistakes() {
        hangmanDisplay.printHangman(5);

        assertEquals("""
                          _____
                         |    |
                         |    O
                         |
                         |
                         |
                        /|\\""", byteArrayOutputStream.toString());
    }

    @Test
    void testPrintHangmanRemainedFourMistakes() {
        hangmanDisplay.printHangman(4);

        assertEquals("""
                          _____
                         |    |
                         |    O
                         |    |
                         |    |
                         |
                        /|\\""", byteArrayOutputStream.toString());
    }

    @Test
    void testPrintHangmanRemainedThreeMistakes() {
        hangmanDisplay.printHangman(3);

        assertEquals("""
                          _____
                         |    |
                         |    O
                         |   /|
                         |    |
                         |
                        /|\\""", byteArrayOutputStream.toString());
    }

    @Test
    void testPrintHangmanRemainedTwoMistakes() {
        hangmanDisplay.printHangman(2);

        assertEquals("""
                          _____
                         |    |
                         |    O
                         |   /|\\
                         |    |
                         |
                        /|\\""", byteArrayOutputStream.toString());
    }

    @Test
    void testPrintHangmanRemainedOneMistakes() {
        hangmanDisplay.printHangman(1);

        assertEquals("""
                          _____
                         |    |
                         |    O
                         |   /|\\
                         |    |
                         |   /
                        /|\\""", byteArrayOutputStream.toString());
    }

    @Test
    void testPrintHangmanRemainedZeroMistakes() {
        hangmanDisplay.printHangman(0);

        assertEquals("""
                          _____
                         |    |
                         |    O
                         |   /|\\
                         |    |
                         |   / \\
                        /|\\""", byteArrayOutputStream.toString());
    }

    @Test
    void testPrintHangmanRemainedMOreThenSevenMistakesException() {
        assertThrows(IllegalArgumentException.class, () -> hangmanDisplay.printHangman(8));
    }

    @Test
    void testPrintHangmanRemainedLessThenZeroMistakesException() {
        assertThrows(IllegalArgumentException.class, () -> hangmanDisplay.printHangman(-1));
    }

}
