import java.io.Serializable;

public class Score implements Serializable {

    public int addToScore(String category, String letter, String word){
        int points = 0;
        int amountOfLetter = getAmountOfLetter(word, letter);

        switch (category) {
            case "U.S Cities":
            case "Countries":
                points = 15*amountOfLetter;
                break;
            case "Superpowers":
            case "Celebrities":
                points = 10*amountOfLetter;
                break;
            case "Colors":
            case "U.S. States":
            case "Animals":
                points = 5*amountOfLetter;
                break;
        }
        return points;
    }

    private int getAmountOfLetter(String word, String letter) {
        int total = 0;
        for (int index = 0; index < word.length(); index++) {
            if (word.toUpperCase().charAt(index) == letter.charAt(0)) {
                total += 1;
            }
        }
        return total;
    }
}