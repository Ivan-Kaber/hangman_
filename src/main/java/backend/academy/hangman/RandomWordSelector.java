package backend.academy.hangman;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class RandomWordSelector {
    private List<Word> words = new ArrayList<>();

    public RandomWordSelector(List<Word> words) {
        this.words = words;
    }

    public Word getRandomWord() {
        return words.get(new SecureRandom().nextInt(words.size()));
    }
}
