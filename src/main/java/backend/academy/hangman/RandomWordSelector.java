package backend.academy.hangman;

import java.security.SecureRandom;
import java.util.List;

public class RandomWordSelector {

    public RandomWordSelector() {
    }

    private final List<Word> animalList = List.of(
        new Word("жираф", Category.ANIMALS, "Животное с длинной шеей, обитающее в Африке"),
        new Word("кенгуру", Category.ANIMALS, "Животное, которое прыгает и имеет сумку"),
        new Word("пингвин", Category.ANIMALS, "Нелетающая птица, которая плавает"),
        new Word("медведь", Category.ANIMALS, "Большое млекопитающее с густым мехом"),
        new Word("обезьяна", Category.ANIMALS, "Примат с длинными руками и хвостом")
    );

    private final List<Word> fruitList = List.of(
        new Word("яблоко", Category.FRUITS, "Круглый плод, обычно красного, зеленого или желтого цвета"),
        new Word("банан", Category.FRUITS, "Длинный желтый плод с мягкой сладкой мякотью"),
        new Word("апельсин", Category.FRUITS, "Цитрусовый плод с оранжевой коркой и кисло-сладким вкусом"),
        new Word("груша", Category.FRUITS, "Плод с мягкой сладкой мякотью, часто зеленого или желтого цвета"),
        new Word("ананас", Category.FRUITS, "Тропический плод с колючей коркой и сладкой мякотью")
    );

    private final List<Word> countryList = List.of(
        new Word("Россия", Category.COUNTRIES, "Страна в Восточной Европе и Северной Азии"),
        new Word("Италия", Category.COUNTRIES, "Страна в Южной Европе, известная своей историей и культурой"),
        new Word("Австралия", Category.COUNTRIES, "Страна и континент на юге Тихого океана"),
        new Word("Бразилия", Category.COUNTRIES,
            "Страна в Южной Америке, известная своей амазонской тропической областью"),
        new Word("Япония", Category.COUNTRIES,
            "Островное государство в Восточной Азии, известное своей технологией и культурой")
    );

    private final List<Word> sportList = List.of(
        new Word("футбол", Category.SPORTS, "Командный вид спорта с мячом, играемый ногами"),
        new Word("баскетбол", Category.SPORTS, "Командный вид спорта, где цель — забросить мяч в корзину"),
        new Word("волейбол", Category.SPORTS, "Командный вид спорта, в котором мяч перебрасывается через сетку"),
        new Word("теннис", Category.SPORTS, "Индивидуальный или парный вид спорта с ракеткой и мячом"),
        new Word("хоккей", Category.SPORTS, "Командный вид спорта на льду с клюшками и шайбой")
    );

    public Word getRandomWordByCategory(Category category) {
        return switch (category) {
            case ANIMALS -> animalList.get(new SecureRandom().nextInt(animalList.size()));
            case FRUITS -> fruitList.get(new SecureRandom().nextInt(fruitList.size()));
            case COUNTRIES -> countryList.get(new SecureRandom().nextInt(countryList.size()));
            case SPORTS -> sportList.get(new SecureRandom().nextInt(sportList.size()));
            default -> throw new IllegalArgumentException("There is no category equal to " + category);
        };
    }
}
