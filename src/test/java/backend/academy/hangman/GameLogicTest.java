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
    private HiddenWord hiddenWord;

    @BeforeEach
    void setUp() {
        out = mock(PrintStream.class);
        scanner = mock(Scanner.class);
        gameLogic = new GameLogic(out, scanner);
    }

    @Test
    void selectCategory_animal() {
        gameLogic.selectCategory("1");

        assertThat(gameLogic.getMapWords()).isEqualTo(WordList.getANIMALS());
    }

    @Test
    void selectCategory_country() {
        gameLogic.selectCategory("3");

        assertThat(gameLogic.getMapWords()).isEqualTo(WordList.getCOUNTRIES());
    }

    @Test
    void selectCategory_fruit() {
        gameLogic.selectCategory("2");

        assertThat(gameLogic.getMapWords()).isEqualTo(WordList.getFRUITS());
    }

    @Test
    void selectCategory_sport() {
        gameLogic.selectCategory("4");

        assertThat(gameLogic.getMapWords()).isEqualTo(WordList.getSPORTS());
    }

    @Test
    void selectCategory_random() {
        gameLogic.selectCategory("5");

        assertThat(gameLogic.getMapWords()).isIn(
            WordList.getANIMALS(),
            WordList.getCOUNTRIES(),
            WordList.getFRUITS(),
            WordList.getSPORTS()
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
        assertThat(gameLogic.getMapWords()).isEqualTo(WordList.getANIMALS());
    }

    @Test
    void selectLvl_easy() {
        gameLogic.selectCategory("1");

        gameLogic.selectLvl("1");

        assertThat(gameLogic.getWords()).isEqualTo(WordList.getANIMALS().get(Difficulty.EASY));
    }

    @Test
    void selectLvl_medium() {
        gameLogic.selectCategory("3");

        gameLogic.selectLvl("2");

        assertThat(gameLogic.getWords()).isEqualTo(WordList.getCOUNTRIES().get(Difficulty.MEDIUM));
    }

    @Test
    void selectLvl_hard() {
        gameLogic.selectCategory("2");

        gameLogic.selectLvl("3");

        assertThat(gameLogic.getWords()).isEqualTo(WordList.getFRUITS().get(Difficulty.HARD));
    }

    @Test
    void selectLvl_random() {
        gameLogic.selectCategory("1");

        gameLogic.selectLvl("4");

        assertThat(gameLogic.getWords()).isIn(
            WordList.getANIMALS().get(Difficulty.EASY),
            WordList.getANIMALS().get(Difficulty.MEDIUM),
            WordList.getANIMALS().get(Difficulty.HARD)
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

        assertThat(gameLogic.getWords()).isEqualTo(WordList.getANIMALS().get(Difficulty.EASY));
    }

    @Test
    void chooseRandomWord_validWordLength() {
        gameLogic.selectCategory("1");

        gameLogic.selectLvl("3");

        gameLogic.chooseRandomWord();

        assertThat(gameLogic.getWord().word().length())
            .isBetween(GameLogic.getMIN_WORD_LENGTH(), GameLogic.getMAX_WORD_LENGTH());
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

        gameLogic.setWords(wordList);

        gameLogic.chooseRandomWord();

        Word chosenWord = gameLogic.getWord();
        assertThat(chosenWord).isNotNull();
        assertThat(chosenWord.word()).isEqualTo("validWord");
    }

    @Test
    void isGameOver_win() {
        hiddenWord = mock(HiddenWord.class);
        when(hiddenWord.isWin()).thenReturn(true);

        gameLogic.setHiddenWord(hiddenWord);

        assertThat(gameLogic.isGameOver()).isTrue();
    }

    @Test
    void isGameOver_lose() {
        gameLogic.setWords(List.of(new Word("validWord", Category.ANIMALS, "valid")));
        gameLogic.chooseRandomWord();

        gameLogic.setRemainedMistakes(0);

        assertThat(gameLogic.isGameOver()).isTrue();
    }

    @Test
    void isGameOver_notOver() {
        hiddenWord = mock(HiddenWord.class);
        when(hiddenWord.isWin()).thenReturn(false);

        gameLogic.setHiddenWord(hiddenWord);
        gameLogic.setRemainedMistakes(3);

        assertThat(gameLogic.isGameOver()).isFalse();
    }

    @Test
    void isLose_true() {
        gameLogic.setWords(List.of(new Word("validWord", Category.ANIMALS, "valid")));
        gameLogic.chooseRandomWord();

        gameLogic.setRemainedMistakes(0);

        assertThat(gameLogic.isLose()).isTrue();
    }

    @Test
    void isLose_false() {
        gameLogic.setWords(List.of(new Word("validWord", Category.ANIMALS, "valid")));
        gameLogic.chooseRandomWord();

        gameLogic.setRemainedMistakes(1);

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
