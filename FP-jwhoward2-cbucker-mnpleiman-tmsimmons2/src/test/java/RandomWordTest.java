import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RandomWordTest {
    /*
    Both of these tests will not work 100% of the time.
    But the chances for the words to be similar are minimal.
    So it seems to be worth testing.
     */
    @Test
    public void testRandomAnimal() throws IOException {
        RandomWord word = new RandomWord();
        String firstAnimal = word.findRandomWord(2);
        String secondAnimal = word.findRandomWord(2);
        Assert.assertNotEquals(firstAnimal, secondAnimal);
    }

    @Test
    public void testRandomColor() throws IOException {
        RandomWord word = new RandomWord();
        String firstColor = word.findRandomWord(1);
        String secondColor = word.findRandomWord(1);
        Assert.assertNotEquals(firstColor, secondColor);
    }
}
