package backend.academy.hangman;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class WordList {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final List<Word> EASY_ANIMALS = List.of(
        new Word("лиса", Category.ANIMALS, "Хищник с рыжей шерстью"),
        new Word("лось", Category.ANIMALS, "Крупное животное с большими рогами"),
        new Word("заяц", Category.ANIMALS, "Животное с длинными ушами"),
        new Word("рысь", Category.ANIMALS, "Кошачий хищник с коротким хвостом"),
        new Word("крот", Category.ANIMALS, "Маленькое животное, которое роет землю")
    );

    private static final List<Word> MEDIUM_ANIMALS = List.of(
        new Word("олень", Category.ANIMALS, "Животное с рогами, обитает в лесах"),
        new Word("медведь", Category.ANIMALS, "Большое млекопитающее с густым мехом"),
        new Word("гусь", Category.ANIMALS, "Птица с длинной шеей, которая часто встречается на водоемах"),
        new Word("лебедь", Category.ANIMALS, "Крупная белая птица с длинной шеей"),
        new Word("утка", Category.ANIMALS, "Птица, обитающая на водоемах")
    );

    private static final List<Word> HARD_ANIMALS = List.of(
        new Word("гиппопотам", Category.ANIMALS, "Крупное млекопитающее, живет в реках Африки"),
        new Word("хамелеон", Category.ANIMALS, "Ящерица, способная менять цвет"),
        new Word("мурена", Category.ANIMALS, "Хищная рыба, обитает в коралловых рифах"),
        new Word("аллигатор", Category.ANIMALS, "Крупный хищник с мощными челюстями"),
        new Word("бабуин", Category.ANIMALS, "Примат с длинной мордой")
    );

    private static final List<Word> EASY_COUNTRIES = List.of(
        new Word("Китай", Category.COUNTRIES, "Страна с самой большой численностью населения"),
        new Word("Франция", Category.COUNTRIES, "Известна своей кухней и Эйфелевой башней"),
        new Word("Япония", Category.COUNTRIES, "Островное государство, известное своей культурой"),
        new Word("Италия", Category.COUNTRIES, "Страна с богатой историей и известными кулинарными традициями"),
        new Word("Бразилия", Category.COUNTRIES, "Самая большая страна Южной Америки")
    );

    private static final List<Word> MEDIUM_COUNTRIES = List.of(
        new Word("Швеция", Category.COUNTRIES, "Скандинавская страна с высоким уровнем жизни"),
        new Word("Аргентина", Category.COUNTRIES, "Южноамериканская страна, известная своим танго"),
        new Word("Австралия", Category.COUNTRIES, "Островной континент с уникальной фауной"),
        new Word("Мексика", Category.COUNTRIES, "Страна, граничащая с США, известна своей кухней"),
        new Word("Индия", Category.COUNTRIES, "Вторая по численности населения страна мира")
    );

    private static final List<Word> HARD_COUNTRIES = List.of(
        new Word("Казахстан", Category.COUNTRIES, "Крупная страна в Центральной Азии"),
        new Word("Мадагаскар", Category.COUNTRIES, "Островное государство у восточного побережья Африки"),
        new Word("Никарагуа", Category.COUNTRIES, "Центральноамериканская страна с озерами и вулканами"),
        new Word("Парагвай", Category.COUNTRIES, "Страна в Южной Америке, не имеющая выхода к морю"),
        new Word("Таджикистан", Category.COUNTRIES, "Горная страна в Центральной Азии")
    );

    private static final List<Word> EASY_FRUITS = List.of(
        new Word("Яблоко", Category.FRUITS, "Красный, зеленый или желтый плод, известный своим сладким вкусом"),
        new Word("Груша", Category.FRUITS, "Фрукт с мягкой текстурой и сочной мякотью"),
        new Word("Слива", Category.FRUITS, "Маленький круглый фрукт с косточкой, фиолетового цвета"),
        new Word("Вишня", Category.FRUITS, "Маленький круглый фрукт с ярко-красной кожицей"),
        new Word("Киви", Category.FRUITS, "Маленький плод с коричневой шкуркой и зеленой мякотью")
    );

    private static final List<Word> MEDIUM_FRUITS = List.of(
        new Word("Мандарин", Category.FRUITS, "Цитрусовый фрукт, похожий на маленький апельсин"),
        new Word("Абрикос", Category.FRUITS, "Фрукт с мягкой кожицей и косточкой внутри"),
        new Word("Ананас", Category.FRUITS, "Тропический фрукт с твердой кожурой и сладкой мякотью"),
        new Word("Персик", Category.FRUITS, "Фрукт с пушистой кожурой и сочной мякотью"),
        new Word("Грейпфрут", Category.FRUITS, "Крупный цитрусовый фрукт с горьковато-сладким вкусом")
    );

    private static final List<Word> HARD_FRUITS = List.of(
        new Word("Авокадо", Category.FRUITS, "Зеленый фрукт с мягкой маслянистой мякотью и крупной косточкой"),
        new Word("Гранат", Category.FRUITS, "Красный фрукт с множеством маленьких сочных зерен внутри"),
        new Word("Папайя", Category.FRUITS, "Тропический фрукт с оранжевой мякотью и черными семенами"),
        new Word("Маракуйя", Category.FRUITS, "Тропический фрукт с кисло-сладкой мякотью и множеством семян"),
        new Word("Фейхоа", Category.FRUITS, "Зеленый фрукт с ароматной и кисло-сладкой мякотью")
    );

    private static final List<Word> EASY_SPORTS = List.of(
        new Word("Футбол", Category.SPORTS, "Командный вид спорта с мячом, играемый ногами"),
        new Word("Баскетбол", Category.SPORTS, "Командный вид спорта, где цель — забросить мяч в корзину"),
        new Word("Теннис", Category.SPORTS, "Индивидуальный или парный вид спорта с ракеткой и мячом"),
        new Word("Волейбол", Category.SPORTS, "Командный вид спорта, в котором мяч перебрасывается через сетку"),
        new Word("Плавание", Category.SPORTS, "Спорт, включающий перемещение в воде")
    );

    private static final List<Word> MEDIUM_SPORTS = List.of(
        new Word("Гандбол", Category.SPORTS, "Командный вид спорта, в котором мяч забрасывается в ворота руками"),
        new Word("Хоккей", Category.SPORTS, "Командный вид спорта на льду с клюшками и шайбой"),
        new Word("Бокс", Category.SPORTS, "Спортивный поединок, в котором спортсмены бьют друг друга кулаками"),
        new Word("Регби", Category.SPORTS, "Командный вид спорта с овальным мячом и многочисленными правилами"),
        new Word("Бадминтон", Category.SPORTS, "Индивидуальный или парный вид спорта с воланом и ракетками")
    );

    private static final List<Word> HARD_SPORTS = List.of(
        new Word("Скалолазание", Category.SPORTS, "Спорт, включающий подъем по скалам или искусственным стенам"),
        new Word("Бодибилдинг", Category.SPORTS, "От английского 'телостроительство'"),
        new Word("Кайтсерфинг", Category.SPORTS, "Водный вид спорта, сочетающий серфинг и управление кайтом"),
        new Word("Каноэ", Category.SPORTS, "Водный спорт на каноэ или каяке, включая греблю по рекам и озерам"),
        new Word("Триатлон", Category.SPORTS, "Многоборье, включающее плавание, велогонку и бег")
    );

    @Getter
    private static final Map<Difficulty, List<Word>> ANIMALS = Map.of(
        Difficulty.EASY, EASY_ANIMALS,
        Difficulty.MEDIUM, MEDIUM_ANIMALS,
        Difficulty.HARD, HARD_ANIMALS
    );

    @Getter
    private static final Map<Difficulty, List<Word>> COUNTRIES = Map.of(
        Difficulty.EASY, EASY_COUNTRIES,
        Difficulty.MEDIUM, MEDIUM_COUNTRIES,
        Difficulty.HARD, HARD_COUNTRIES
    );

    @Getter
    private static final Map<Difficulty, List<Word>> FRUITS = Map.of(
        Difficulty.EASY, EASY_FRUITS,
        Difficulty.MEDIUM, MEDIUM_FRUITS,
        Difficulty.HARD, HARD_FRUITS
    );

    @Getter
    private static final Map<Difficulty, List<Word>> SPORTS = Map.of(
        Difficulty.EASY, EASY_SPORTS,
        Difficulty.MEDIUM, MEDIUM_SPORTS,
        Difficulty.HARD, HARD_SPORTS
    );

    public static Map<Difficulty, List<Word>> getRandomListWords() {
        return switch (RANDOM.nextInt(Category.values().length - 1)) { //not considering RANDOM
            case 0 -> ANIMALS;
            case 1 -> COUNTRIES;
            case 2 -> FRUITS;
            default -> SPORTS;
        };
    }

    public static Word getRandomWord(List<Word> words) {
        return words.get(RANDOM.nextInt(words.size()));
    }

    private WordList() {
    }

}
