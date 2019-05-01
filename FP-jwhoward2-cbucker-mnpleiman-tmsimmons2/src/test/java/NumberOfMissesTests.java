import org.junit.Assert;
import org.junit.Test;

public class NumberOfMissesTests {
    @Test
    public void twoWrongGuesses() {
        LetterValue letterValue = new LetterValue();
        letterValue.checkIfInWord("L","orange");
        String url = letterValue.checkIfInWord("Q","orange");
        Assert.assertEquals("/Hangman3.png", url);
    }

    @Test
    public void threeWrongGuesses() {
        LetterValue letterValue = new LetterValue();
        letterValue.checkIfInWord("L","orange");
        letterValue.checkIfInWord("Q","orange");
        String url = letterValue.checkIfInWord("Q","orange");
        Assert.assertEquals("/Hangman4.png", url);
    }

    @Test
    public void fourWrongGuesses() {
        LetterValue letterValue = new LetterValue();
        letterValue.checkIfInWord("L","orange");
        letterValue.checkIfInWord("Q","orange");
        letterValue.checkIfInWord("Z","orange");
        String url = letterValue.checkIfInWord("Q","orange");
        Assert.assertEquals("/Hangman5.png", url);
    }

    @Test
    public void fiveWrongGuesses() {
        LetterValue letterValue = new LetterValue();
        letterValue.checkIfInWord("L","orange");
        letterValue.checkIfInWord("Q","orange");
        letterValue.checkIfInWord("Z","orange");
        letterValue.checkIfInWord("F","orange");
        String url = letterValue.checkIfInWord("Q","orange");
        Assert.assertEquals("/Hangman6.png", url);
    }

    @Test
    public void sixWrongGuesses() {
        LetterValue letterValue = new LetterValue();
        letterValue.checkIfInWord("L","orange");
        letterValue.checkIfInWord("Q","orange");
        letterValue.checkIfInWord("Z","orange");
        letterValue.checkIfInWord("F","orange");
        letterValue.checkIfInWord("M","orange");
        String url = letterValue.checkIfInWord("Q","orange");
        Assert.assertEquals("/HangmanEnd.png", url);
    }
}
