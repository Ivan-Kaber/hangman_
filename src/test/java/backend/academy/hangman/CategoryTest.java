package backend.academy.hangman;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryTest {
    @Test
    public void testGetRandomCategoryPossibleValues() {
        int numSamples = 100;
        Category[] categories = new Category[numSamples];
        for (int i = 0; i < numSamples; i++) {
            categories[i] = Category.getRandomCategory();
        }

        for (Category category : Category.values()) {
            assertTrue(Arrays.asList(categories).contains(category));
        }
    }
}
