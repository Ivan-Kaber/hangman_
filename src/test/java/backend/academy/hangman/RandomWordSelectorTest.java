package backend.academy.hangman;

import com.google.common.collect.RangeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RandomWordSelectorTest {

    @Test
    void testGetRandomAnimalWord() {
        RandomWordSelector randomWordSelector = new RandomWordSelector();
        Word word = randomWordSelector.getRandomWordByCategory(Category.ANIMALS);

        assertNotNull(word.word());
        assertEquals(word.category(), Category.ANIMALS);
        assertNotNull(word.hint());
    }

    @Test
    void testGetRandomFruitWord() {
        RandomWordSelector randomWordSelector = new RandomWordSelector();
        Word word = randomWordSelector.getRandomWordByCategory(Category.FRUITS);

        assertNotNull(word.word());
        assertEquals(word.category(), Category.FRUITS);
        assertNotNull(word.hint());
    }

    @Test
    void testGetRandomCountryWord() {
        RandomWordSelector randomWordSelector = new RandomWordSelector();
        Word word = randomWordSelector.getRandomWordByCategory(Category.COUNTRIES);

        assertNotNull(word.word());
        assertEquals(word.category(), Category.COUNTRIES);
        assertNotNull(word.hint());
    }

    @Test
    void testGetRandomSportWord() {
        RandomWordSelector randomWordSelector = new RandomWordSelector();
        Word word = randomWordSelector.getRandomWordByCategory(Category.SPORTS);

        assertNotNull(word.word());
        assertEquals(word.category(), Category.SPORTS);
        assertNotNull(word.hint());
    }
}
