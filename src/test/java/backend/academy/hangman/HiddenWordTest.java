package backend.academy.hangman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    void testPrintHiddenWordHiddenWord() {
        hiddenWord.printHiddenWord();

        assertThat("______").isEqualTo(byteArrayOutputStream.toString());
    }

    @Test
    void fillAlreadyUsedLetters_validLetterAddToSet() {
        hiddenWord.fillAlreadyUsedLetters("а");

        assertThat(hiddenWord.isAlreadyUsed("а")).isTrue();
        assertThat(hiddenWord.isAlreadyUsed("д")).isFalse();
    }

    @Test
    void containsLetter_validLetter() {
        assertThat(hiddenWord.containsLetter("с")).isTrue();
    }

    @Test
    void containsLetter_invalidLetter() {
        assertThat(hiddenWord.containsLetter("б")).isFalse();
    }

    @Test
    void updateHiddenWord_testUpdateTwoExistLetters() {
        hiddenWord.fillAlreadyUsedLetters("с");
        hiddenWord.fillAlreadyUsedLetters("т");

        hiddenWord.updateHiddenWord();
        hiddenWord.printHiddenWord();

        assertThat("ст___с").isEqualTo(byteArrayOutputStream.toString());
    }

    @Test
    void updateHiddenWord_testUpdateTwoFalseLetters() {
        hiddenWord.fillAlreadyUsedLetters("е");
        hiddenWord.fillAlreadyUsedLetters("о");

        hiddenWord.updateHiddenWord();
        hiddenWord.printHiddenWord();

        assertThat("______").isEqualTo(byteArrayOutputStream.toString());
    }

    @Test
    void isWin_win() {
        hiddenWord.fillAlreadyUsedLetters("с");
        hiddenWord.fillAlreadyUsedLetters("т");
        hiddenWord.fillAlreadyUsedLetters("р");
        hiddenWord.fillAlreadyUsedLetters("а");
        hiddenWord.fillAlreadyUsedLetters("у");

        hiddenWord.updateHiddenWord();

        assertThat(hiddenWord.isWin()).isTrue();
    }

    @Test
    void isWin_notWin() {
        hiddenWord.fillAlreadyUsedLetters("с");
        hiddenWord.fillAlreadyUsedLetters("т");
        hiddenWord.fillAlreadyUsedLetters("р");
        hiddenWord.fillAlreadyUsedLetters("а");
        hiddenWord.fillAlreadyUsedLetters("л");

        hiddenWord.updateHiddenWord();

        assertThat(hiddenWord.isWin()).isFalse();
    }
}
