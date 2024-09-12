package backend.academy.hangman;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RandomWordSelectorTest {

    @Test
    void testGetRandomWord() {

        List<Word> words = new ArrayList<>();
        words.add(new Word("test", Category.ANIMALS, "hint"));
        RandomWordSelector randomWordSelector = new RandomWordSelector(words);
        Word word = randomWordSelector.getRandomWord();

        assertEquals(word.word(), "test");
        assertEquals(word.category(), Category.ANIMALS);
        assertEquals(word.hint(), "hint");
    }
}
