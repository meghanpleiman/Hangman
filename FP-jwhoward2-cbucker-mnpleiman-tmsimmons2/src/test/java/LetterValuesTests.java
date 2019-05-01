import org.junit.Assert;
import org.junit.Test;

public class LetterValuesTests {
    @Test
    public void letterNotInWord() {
        LetterValue letterValue = new LetterValue();
        String url = letterValue.checkIfInWord("S","ORaNge");
        Assert.assertEquals("/Hangman2.png", url);
    }

    @Test
    public void letterInWord() {
        LetterValue letterValue = new LetterValue();
        String url = letterValue.checkIfInWord("R","ORaNge");
        Assert.assertEquals("/Hangman1.png", url);
    }

    @Test
    public void uniformUppercaseLetterInWord() {
        LetterValue letterValue = new LetterValue();
        String url = letterValue.checkIfInWord("R","ORANGE");
        Assert.assertEquals("/Hangman1.png", url);
    }

    @Test
    public void uniformLowercaseLetterInWord() {
        LetterValue letterValue = new LetterValue();
        String url = letterValue.checkIfInWord("R","orange");
        Assert.assertEquals("/Hangman1.png", url);
    }

    @Test
    public void uniformUppercaseLetterNotInWord() {
        LetterValue letterValue = new LetterValue();
        String url = letterValue.checkIfInWord("S","ORANGE");
        Assert.assertEquals("/Hangman2.png", url);
    }

    @Test
    public void uniformLowercaseLetterNotInWord() {
        LetterValue letterValue = new LetterValue();
        String url = letterValue.checkIfInWord("S","orange");
        Assert.assertEquals("/Hangman2.png", url);
    }
}
