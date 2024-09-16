package backend.academy.hangman;

import lombok.Getter;

@Getter public class Alphabet {
    private final String[] alphabet = {"а", "б", "в", "г", "д", "е", "ж",
        "з", "и", "й", "к", "л", "м", "н", "о",
        "п", "р", "с", "т", "у", "ф", "х", "ц",
        "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я"};

    public void deleteLetter(String letter) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i].equalsIgnoreCase(letter.replace("й", "й"))) {
                alphabet[i] = " ";
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        @SuppressWarnings("MagicNumber")
        int[] rowLengths = {7, 7, 7, 7, 4};
        int startIndex = 0;

        for (int rowLength : rowLengths) {
            for (int i = startIndex; i < startIndex + rowLength; i++) {
                builder.append(alphabet[i]);
                if (i < startIndex + rowLength - 1) {
                    builder.append(' ');
                }
            }
            builder.append('\n');
            startIndex += rowLength;
        }

        return builder.toString();
    }
}
