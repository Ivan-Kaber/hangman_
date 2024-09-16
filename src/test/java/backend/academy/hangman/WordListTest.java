package backend.academy.hangman;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.List;

class WordListTest {
    private final WordList wordList = new WordList();

    @Test
    void getRandomListWords_shouldReturnValidList() {
        for (int i = 0; i < 100; i++) {
            List<List<Word>> result = wordList.getRandomListWords();

            assertThat(result).isIn(
                wordList.animalList(),
                wordList.countryList(),
                wordList.fruitList(),
                wordList.sportList()
            );
        }
    }

    @Test
    void getRandomWord_checkCertainWord() {
        List<Word> list = List.of(
            new Word("лиса", Category.ANIMALS, "Хищник с рыжей шерстью")
        );
        WordList wordList = new WordList();
        assertThat(wordList.getRandomWord(list)).isEqualTo(
            new Word("лиса", Category.ANIMALS, "Хищник с рыжей шерстью"));
    }

    @RepeatedTest(10)
    void getRandomWord_checkRandomWord() {
        List<List<Word>> randomList = wordList.getRandomListWords();
        Word randomWord = wordList.getRandomWord(randomList.getFirst());

        assertThat(randomWord).isNotNull();
        assertThat(randomWord.category()).isIn(Category.ANIMALS, Category.COUNTRIES, Category.FRUITS, Category.SPORTS);
        assertThat(randomWord.word()).isNotBlank();
        assertThat(randomWord.hint()).isNotBlank();
    }
}
