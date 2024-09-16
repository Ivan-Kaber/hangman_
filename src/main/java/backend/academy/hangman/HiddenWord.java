package backend.academy.hangman;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HiddenWord {
    private final String word;
    private final String[] hiddenWord;
    private final PrintStream out;
    private final Set<String> hiddenWordLetters;
    private final Set<String> alreadyUsedLetters = new HashSet<>();
    private int countLetters = 0;

    public HiddenWord(String word, PrintStream out) {
        this.out = out;
        this.word = word.toLowerCase();
        hiddenWord = new String[word.length()];
        Arrays.fill(hiddenWord, "_");
        hiddenWordLetters = new HashSet<>(word.length());
        for (char c : this.word.toCharArray()) {
            hiddenWordLetters.add(String.valueOf(c));
        }
    }

    public void printHiddenWord() {
        for (String el : hiddenWord) {
            out.print(el);
        }
    }

    public void updateHiddenWord() {
        for (int i = 0; i < word.length(); i++) {
            if (alreadyUsedLetters.contains(String.valueOf(word.charAt(i)).toLowerCase())
                && "_".equals(hiddenWord[i])) {
                hiddenWord[i] = String.valueOf(word.charAt(i)).toLowerCase();
                countLetters++;
            }
        }
    }

    public void fillAlreadyUsedLetters(String letter) {
        alreadyUsedLetters.add(letter.toLowerCase());
    }

    public boolean containsLetter(String letter) {
        return hiddenWordLetters.contains(letter.toLowerCase());
    }

    public boolean isAlreadyUsed(String letter) {
        return alreadyUsedLetters.contains(letter.toLowerCase());
    }

    public boolean isWin() {
        return countLetters == word.length();
    }
}
