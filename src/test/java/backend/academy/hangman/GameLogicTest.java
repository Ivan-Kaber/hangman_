package backend.academy.hangman;

import java.io.PrintStream;
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

    @BeforeEach
    void setUp() {
        out = mock(PrintStream.class);
        scanner = mock(Scanner.class);
        gameLogic = new GameLogic(out, scanner);
        wordList = new WordList();
    }

    @Test
    void selectAnimalCategory() {
        gameLogic.selectCategory("1");

        assertThat(gameLogic.listWords()).isEqualTo(wordList.animalList());
    }

    @Test
    void selectCountryCategory() {
        gameLogic.selectCategory("2");

        assertThat(gameLogic.listWords()).isEqualTo(wordList.countryList());
    }

    @Test
    void selectFruitCategory() {
        gameLogic.selectCategory("3");

        assertThat(gameLogic.listWords()).isEqualTo(wordList.fruitList());
    }

    @Test
    void selectSportCategory() {
        gameLogic.selectCategory("4");

        assertThat(gameLogic.listWords()).isEqualTo(wordList.sportList());
    }

    @Test
    void selectRandomCategory() {
        gameLogic.selectCategory("5");

        assertThat(gameLogic.listWords()).isIn(
            wordList.animalList(),
            wordList.countryList(),
            wordList.fruitList(),
            wordList.sportList()
        );
    }

    @Test
    void selectWrongCategoryThenAnimal() {
        when(scanner.nextLine()).thenReturn("invalid", "1");

        gameLogic.selectCategory("invalid");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(out, times(2)).print(captor.capture());

        assertThat(captor.getValue()).contains("Вы ввели неверные данные, попробуйте ещё раз, "
            + "введите одну цифру - желаемую категорию: ");
        assertThat(gameLogic.listWords()).isEqualTo(wordList.animalList());
    }

    @Test
    void selectEasyLevel() {
        gameLogic.selectCategory("1");

        gameLogic.selectLvl("1");

        assertThat(gameLogic.words()).isEqualTo(wordList.animalList().get(0));
    }

    @Test
    void selectMediumLevel() {
        gameLogic.selectCategory("2");

        gameLogic.selectLvl("2");

        assertThat(gameLogic.words()).isEqualTo(wordList.countryList().get(1));
    }

    @Test
    void selectHardLevel() {
        gameLogic.selectCategory("3");

        gameLogic.selectLvl("3");

        assertThat(gameLogic.words()).isEqualTo(wordList.fruitList().get(2));
    }

    @Test
    void selectRandomLevel() {
        gameLogic.selectCategory("1");

        gameLogic.selectLvl("4");

        assertThat(gameLogic.words()).isIn(
            wordList.animalList().get(0),
            wordList.animalList().get(1),
            wordList.animalList().get(2)
        );
    }

    @Test
    void selectWrongLevelThenEasy() {
        when(scanner.nextLine()).thenReturn("invalid", "1");

        gameLogic.selectCategory("1");

        gameLogic.selectLvl("invalid");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(out, times(2)).print(captor.capture());

        assertThat(captor.getValue()).contains("Вы ввели неверные данные, попробуйте ещё раз, "
            + "введите одну цифру - желаемый уровень сложности: ");

        assertThat(gameLogic.words()).isEqualTo(wordList.animalList().get(0));
    }

}
