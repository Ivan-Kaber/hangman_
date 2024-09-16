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
    void deleteLetter_letterInLowerCase() {
        String letter = "а";
        alphabet.deleteLetter(letter);
        assertThat(alphabet.getAlphabet()[0]).isEqualTo(" ");
    }

    @Test
    void deleteLetter_letterInUpperCase() {
        String letter = "А";
        alphabet.deleteLetter(letter);
        assertThat(alphabet.getAlphabet()[0]).isEqualTo(" ");
    }

    @Test
    void deleteLetter_letterNotFound() {
        Alphabet alphabet1 = new Alphabet();
        String letter = "z";
        alphabet.deleteLetter(letter);
        assertThat(alphabet.getAlphabet()).isEqualTo(alphabet1.getAlphabet());
    }

    @Test
    void toString_allAlphabet() {
        String expected = "а б в г д е ж\nз и й к л м н\nо п р с т у ф\nх ц ч ш щ ъ ы\nь э ю я\n";
        assertThat(alphabet.toString()).isEqualTo(expected);
    }

    @Test
    void toString_deleteOneLetter() {
        String letter = "а";
        String expected = "  б в г д е ж\nз и й к л м н\nо п р с т у ф\nх ц ч ш щ ъ ы\nь э ю я\n";
        alphabet.deleteLetter(letter);
        assertThat(alphabet.toString()).isEqualTo(expected);
    }
}
