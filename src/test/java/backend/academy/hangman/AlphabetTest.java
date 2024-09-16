package backend.academy.hangman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AlphabetTest {
    Alphabet alphabet;

    @BeforeEach
    void setUp() {
        alphabet = new Alphabet();
    }


    @Test
    void testDeleteLetter() {
        String letter = "а";
        alphabet.deleteLetter(letter);
        assertThat(alphabet.alphabet()[0]).isEqualTo(" ");
    }

    @Test
    void testDeleteLetterIgnoreCase() {
        String letter = "А";
        alphabet.deleteLetter(letter);
        assertThat(alphabet.alphabet()[0]).isEqualTo(" ");
    }

    @Test
    void testDeleteLetterNotFound() {
        Alphabet alphabet1 = new Alphabet();
        String letter = "z";
        alphabet.deleteLetter(letter);
        assertThat(alphabet.alphabet()).isEqualTo(alphabet1.alphabet());
    }

    @Test
    void testToString() {
        String expected = "а б в г д е ж\nз и й к л м н\nо п р с т у ф\nх ц ч ш щ ъ ы\nь э ю я\n";
        assertThat(alphabet.toString()).isEqualTo(expected);
    }

    @Test
    void testToStringAfterDeleteLetter() {
        String letter = "а";
        String expected = "  б в г д е ж\nз и й к л м н\nо п р с т у ф\nх ц ч ш щ ъ ы\nь э ю я\n";
        alphabet.deleteLetter(letter);
        assertThat(alphabet.toString()).isEqualTo(expected);
    }
}
