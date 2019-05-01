import org.junit.Assert;
import org.junit.Test;

public class introductionTests {
    @Test
    public void testIntroduction() {
        RulesAndIntro intro = new RulesAndIntro();
        String printedIntroduction = intro.printIntroduction();
        Assert.assertEquals(printedIntroduction, "Welcome to Categorical Hangman!\n" +
                "------------------------------------------\n" +
                "In this game you will choose a difficulty.\n" +
                "Then there will be a dropdown box of categories\n " +
                "to choose from!\nClick the x in the upper corner to continue");
    }


    @Test
    public void testGameRules() {
        RulesAndIntro intro = new RulesAndIntro();
        String printedRules = intro.printRules();
        Assert.assertEquals(printedRules, "How to play: \n"+
                "-------------------------------------\n" +
                "To play, you will guess a letter.\n" +
                "If it is in the word, it will be placed in the correct\n" +
                "location. Otherwise, if the letter is not in the word,\n" +
                "the hangman will receive a body part. If your \n" +
                "character has all of its body parts, the game is over.");
    }
}
