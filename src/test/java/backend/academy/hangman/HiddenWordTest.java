package backend.academy.hangman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class HiddenWordTest {

    ByteArrayOutputStream byteArrayOutputStream;
    HiddenWord hiddenWord;

    @BeforeEach
    void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        hiddenWord = new HiddenWord("страус", printStream);
    }

    @Test
    void testPrintHiddenWord() {
        hiddenWord.print();

        assertEquals("______", byteArrayOutputStream.toString());
    }
}
