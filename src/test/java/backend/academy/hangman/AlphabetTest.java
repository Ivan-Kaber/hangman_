package backend.academy.hangman;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AlphabetTest {


    @Test
    void testDeleteLetter() {
        Alphabet alphabet = new Alphabet();
        String letter = "а";
        alphabet.deleteLetter(letter);
        assertThat(alphabet.alphabet()[0]).isEqualTo(" ");
    }

    @Test
    void testDeleteLetterIgnoreCase() {
        Alphabet alphabet = new Alphabet();
        String letter = "А";
        alphabet.deleteLetter(letter);
        assertThat(alphabet.alphabet()[0]).isEqualTo(" ");
    }

    @Test
    void testDeleteLetterNotFound() {
        Alphabet alphabet = new Alphabet();
        Alphabet alphabet1 = new Alphabet();
        String letter = "z";
        alphabet.deleteLetter(letter);
        assertThat(alphabet.alphabet()).isEqualTo(alphabet1.alphabet());
    }

    @Test
    void testToString() {
        Alphabet alphabet = new Alphabet();
        String expected = "а б в г д е ё\nж з и й к л м\nн о п р с т у\nф х ц ч ш щ ъ\nы ь э ю я\n";
        assertThat(alphabet.toString()).isEqualTo(expected);
    }

    @Test
    void testToStringAfterDeleteLetter() {
        Alphabet alphabet = new Alphabet();
        String letter = "а";
        String expected = "  б в г д е ё\nж з и й к л м\nн о п р с т у\nф х ц ч ш щ ъ\nы ь э ю я\n";
        alphabet.deleteLetter(letter);
        assertThat(alphabet.toString()).isEqualTo(expected);
    }
}
