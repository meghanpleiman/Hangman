import java.util.ArrayList;
import java.util.List;

class LetterValue {
    private List<String> listOfLettersInWord = new ArrayList<>();
    private List<String>  urlAndWord = new ArrayList<>();
    private Boolean newGame = true;
    private int errors = 0;
    private String pictureLocation;
    private int initial = 0;

    List<String> createWordLabel(String letter, String categoryWord) {
        categoryWord = categoryWord.replace("\u00A0","");
        if (initial == 0) {
            String url = checkIfInWord(letter, categoryWord);
            String resultingWord = String.join(",", listOfLettersInWord).replace(",", "");
            urlAndWord.add(url);
            urlAndWord.add(resultingWord);
            initial = 1;
        }
        else{
            String url = checkIfInWord(letter, categoryWord);
            String resultingWord = String.join(",", listOfLettersInWord).replace(",", "");
            urlAndWord.set(0,url);
            urlAndWord.set(1,resultingWord);
        }
        return urlAndWord;
    }

    String checkIfInWord(String letter, String categoryWord) {
        getListOfLetters(categoryWord);
        convertNonLetters(categoryWord);
        categoryWord = categoryWord.toUpperCase();
        boolean letterInWord = categoryWord.contains(letter);

        if (letterInWord && !letter.equals("")) {
            int index = categoryWord.indexOf(letter);
            while (index < categoryWord.length()) {
                listOfLettersInWord.set(index, letter);
                index = categoryWord.indexOf(letter, index + 1);
                if (index == -1) {
                    break;
                }
            }
        } else if (!letter.equals("")){
            errors += 1;
            if (errors == 1) {
                pictureLocation = "/Hangman2.png";
            } else if (errors == 2) {
                pictureLocation = "/Hangman3.png";
            } else if (errors == 3) {
                pictureLocation = "/Hangman4.png";
            } else if (errors == 4) {
                pictureLocation = "/Hangman5.png";
            } else if (errors == 5) {
                pictureLocation = "/Hangman6.png";
            } else if (errors >= 6) {
                pictureLocation = "/HangmanEnd.png";
            }
        }
        if (errors == 0) {
            pictureLocation = "/Hangman1.png";
        }

        return pictureLocation;
    }

    private void getListOfLetters(String categoryWord) {
        if (newGame) {
            newGame = false;
            for (int i = 0; i < categoryWord.length(); i++) {
                listOfLettersInWord.add(" _ ");
            }
        }
    }

    private void convertNonLetters(String categoryWord) {
        if (categoryWord.contains(" ")) {
            int index = categoryWord.indexOf(" ");
            while (index < categoryWord.length()) {
                listOfLettersInWord.set(index, "  ");
                index = categoryWord.indexOf(" ", index + 1);
                if (index == -1) {
                    break;
                }
            }
        }

        if (categoryWord.contains("-")) {
            int index = categoryWord.indexOf("-");
            while (index < categoryWord.length()) {
                listOfLettersInWord.set(index, "-");
                index = categoryWord.indexOf("-", index + 1);
                if (index == -1) {
                    break;
                }
            }
        }

        if (categoryWord.contains(".")) {
            int index = categoryWord.indexOf(".");
            while (index < categoryWord.length()) {
                listOfLettersInWord.set(index, ".");
                index = categoryWord.indexOf(".", index + 1);
                if (index == -1) {
                    break;
                }
            }
        }
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public String getPictureLocation() {
        return pictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        this.pictureLocation = pictureLocation;
    }
}