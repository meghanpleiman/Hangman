class RulesAndIntro {
    String printIntroduction() {
            return "Welcome to Categorical Hangman!\n" +
                    "------------------------------------------------\n" +
                    "In this game you will choose a difficulty.\n" +
                    "Then there will be a dropdown box of \ncategories " +
                    "to choose from!\nClick the x in the upper corner to continue";
            }

    String printRules() {
            return "\t\t\t\tHow to play \n"+
                    "------------------------------------------------\n" +
                    "To play, you will guess a letter.\n" +
                    "If it is in the word, it will be placed in the\n" +
                    "correct location. Otherwise, if the letter is\n" +
                    " not in the word,the hangman will receive a body\n" +
                    "part. If your character has all of its body parts,\n" +
                    "the game is over.";
        }
    }