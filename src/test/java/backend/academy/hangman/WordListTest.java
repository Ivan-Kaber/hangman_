package backend.academy.hangman;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

class WordListTest {

    @Test
    void getRandomListWords_shouldReturnValidList() {
        for (int i = 0; i < 100; i++) {
            Map<Difficulty, List<Word>> result = WordList.getRandomListWords();

            assertThat(result).isIn(
                WordList.getANIMALS(),
                WordList.getCOUNTRIES(),
                WordList.getFRUITS(),
                WordList.getSPORTS()
            );
        }
    }

    @Test
    void getRandomWord_checkCertainWord() {
        List<Word> list = List.of(
            new Word("лиса", Category.ANIMALS, "Хищник с рыжей шерстью")
        );
        assertThat(WordList.getRandomWord(list)).isEqualTo(
            new Word("лиса", Category.ANIMALS, "Хищник с рыжей шерстью"));
    }

    @RepeatedTest(10)
    void getRandomWord_checkRandomWord() {
        Map<Difficulty, List<Word>> randomList = WordList.getRandomListWords();
        Word randomWord = WordList.getRandomWord(randomList.get(Difficulty.EASY));

        assertThat(randomWord).isNotNull();
        assertThat(randomWord.category()).isIn(Category.ANIMALS, Category.COUNTRIES, Category.FRUITS, Category.SPORTS);
        assertThat(randomWord.word()).isNotBlank();
        assertThat(randomWord.hint()).isNotBlank();
    }
}
