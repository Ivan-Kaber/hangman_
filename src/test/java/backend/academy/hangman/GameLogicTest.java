package backend.academy.hangman;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GameLogicTest {
    private PrintStream out;
    private Scanner scanner;
    private GameLogic gameLogic;
    private WordList wordList;
    private HiddenWord hiddenWord;

    @BeforeEach
    void setUp() {
        out = mock(PrintStream.class);
        scanner = mock(Scanner.class);
        gameLogic = new GameLogic(out, scanner);
        wordList = new WordList();
    }

    @Test
    void selectCategory_animal() {
        gameLogic.selectCategory("1");

        assertThat(gameLogic.listWords()).isEqualTo(wordList.animalList());
    }

    @Test
    void selectCategory_country() {
        gameLogic.selectCategory("2");

        assertThat(gameLogic.listWords()).isEqualTo(wordList.countryList());
    }

    @Test
    void selectCategory_fruit() {
        gameLogic.selectCategory("3");

        assertThat(gameLogic.listWords()).isEqualTo(wordList.fruitList());
    }

    @Test
    void selectCategory_sport() {
        gameLogic.selectCategory("4");

        assertThat(gameLogic.listWords()).isEqualTo(wordList.sportList());
    }

    @Test
    void selectCategory_random() {
        gameLogic.selectCategory("5");

        assertThat(gameLogic.listWords()).isIn(
            wordList.animalList(),
            wordList.countryList(),
            wordList.fruitList(),
            wordList.sportList()
        );
    }

    @Test
    void selectCategory_selectWrongCategoryThenAnimal() {
        when(scanner.nextLine()).thenReturn("invalid", "1");

        gameLogic.selectCategory("invalid");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(out, times(2)).print(captor.capture());

        assertThat(captor.getValue()).contains("Вы ввели неверные данные, попробуйте ещё раз, "
            + "введите одну цифру - желаемую категорию: ");
        assertThat(gameLogic.listWords()).isEqualTo(wordList.animalList());
    }

    @Test
    void selectLvl_easy() {
        gameLogic.selectCategory("1");

        gameLogic.selectLvl("1");

        assertThat(gameLogic.words()).isEqualTo(wordList.animalList().get(0));
    }

    @Test
    void selectLvl_medium() {
        gameLogic.selectCategory("2");

        gameLogic.selectLvl("2");

        assertThat(gameLogic.words()).isEqualTo(wordList.countryList().get(1));
    }

    @Test
    void selectLvl_hard() {
        gameLogic.selectCategory("3");

        gameLogic.selectLvl("3");

        assertThat(gameLogic.words()).isEqualTo(wordList.fruitList().get(2));
    }

    @Test
    void selectLvl_random() {
        gameLogic.selectCategory("1");

        gameLogic.selectLvl("4");

        assertThat(gameLogic.words()).isIn(
            wordList.animalList().get(0),
            wordList.animalList().get(1),
            wordList.animalList().get(2)
        );
    }

    @Test
    void selectLvl_selectWrongLevelThenEasy() {
        when(scanner.nextLine()).thenReturn("invalid", "1");

        gameLogic.selectCategory("1");

        gameLogic.selectLvl("invalid");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(out, times(2)).print(captor.capture());

        assertThat(captor.getValue()).contains("Вы ввели неверные данные, попробуйте ещё раз, "
            + "введите одну цифру - желаемый уровень сложности: ");

        assertThat(gameLogic.words()).isEqualTo(wordList.animalList().get(0));
    }

    @Test
    void chooseRandomWord_validWordLength() {
        gameLogic.selectCategory("1");

        gameLogic.selectLvl("3");

        gameLogic.chooseRandomWord();

        assertThat(gameLogic.word().word().length())
            .isBetween(GameLogic.MIN_WORD_LENGTH(), GameLogic.MAX_WORD_LENGTH());
    }

    @Test
    void chooseRandomWord_invalidWordLengthThanValid() {
        gameLogic.selectCategory("1");
        gameLogic.selectLvl("1");

        List<Word> wordList = List.of(
            new Word("abc", Category.ANIMALS, "too short"),
            new Word("superlongwordfgdfgfd", Category.ANIMALS, "too long"),
            new Word("validWord", Category.ANIMALS, "valid")
        );

        gameLogic.words(wordList);

        gameLogic.chooseRandomWord();

        Word chosenWord = gameLogic.word();
        assertThat(chosenWord).isNotNull();
        assertThat(chosenWord.word()).isEqualTo("validWord");
    }

    @Test
    void isGameOver_win() {
        hiddenWord = mock(HiddenWord.class);
        when(hiddenWord.isWin()).thenReturn(true);

        gameLogic.hiddenWord(hiddenWord);

        assertThat(gameLogic.isGameOver()).isTrue();
    }

    @Test
    void isGameOver_lose() {
        gameLogic.words(List.of(new Word("validWord", Category.ANIMALS, "valid")));
        gameLogic.chooseRandomWord();

        gameLogic.remainedMistakes(0);

        assertThat(gameLogic.isGameOver()).isTrue();
    }

    @Test
    void isGameOver_notOver() {
        hiddenWord = mock(HiddenWord.class);
        when(hiddenWord.isWin()).thenReturn(false);

        gameLogic.hiddenWord(hiddenWord);
        gameLogic.remainedMistakes(3);

        assertThat(gameLogic.isGameOver()).isFalse();
    }

    @Test
    void isLose_true() {
        gameLogic.words(List.of(new Word("validWord", Category.ANIMALS, "valid")));
        gameLogic.chooseRandomWord();

        gameLogic.remainedMistakes(0);

        assertThat(gameLogic.isLose()).isTrue();
    }

    @Test
    void isLose_false() {
        gameLogic.words(List.of(new Word("validWord", Category.ANIMALS, "valid")));
        gameLogic.chooseRandomWord();

        gameLogic.remainedMistakes(1);

        assertThat(gameLogic.isLose()).isFalse();
    }

    @Test
    void isCyrillic_withCyrillicLetter() {
        assertThat(gameLogic.isCyrillic("п")).isTrue();
    }

    @Test
    void isCyrillic_withUpperCaseCyrillicLetter() {
        assertThat(gameLogic.isCyrillic("Я")).isTrue();
    }

    @Test
    void isCyrillic_withLatinLetter() {
        assertThat(gameLogic.isCyrillic("a")).isFalse();
    }

    @Test
    void isCyrillic_withDigit() {
        assertThat(gameLogic.isCyrillic("5")).isFalse();
    }

    @Test
    void isCyrillic_withSpecialCharacter() {
        assertThat(gameLogic.isCyrillic("@")).isFalse();
    }

    @Test
    void isCyrillic_withEmptyString() {
        assertThat(gameLogic.isCyrillic("")).isFalse();
    }

    @Test
    void isCyrillic_withSpace() {
        assertThat(gameLogic.isCyrillic(" ")).isFalse();
    }
}
