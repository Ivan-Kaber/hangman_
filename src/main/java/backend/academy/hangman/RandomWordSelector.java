package backend.academy.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWordSelector {
    private List<Word> words = new ArrayList<>();

    public RandomWordSelector(List<Word> words) {
        this.words = words;
    }

    public Word getRandomWord() {
        return words.get(new Random().nextInt(words.size()));
    }
}
