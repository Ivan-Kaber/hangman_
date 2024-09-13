package backend.academy.hangman;

import java.io.PrintStream;
import java.util.Arrays;

public class HiddenWord {
    private String word;
    private final String[] hiddenWord;
    private final PrintStream out;

    public HiddenWord(String word, PrintStream out) {
        this.out = out;
        this.word = word;
        hiddenWord = new String[word.length()];
        Arrays.fill(hiddenWord, "_");
    }

    public void print() {
        for(String el: hiddenWord) {
            out.print(el);
        }
    }


}
