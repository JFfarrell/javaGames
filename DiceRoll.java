package javaGames;

public class DiceRoll extends Games {
    public DiceRoll(Player user) {
        super(user);
    }

    public static void rollTheDice(Player user) {
        System.out.println("""
                 
                 *** Dice Roll ***
                            
                The closer you get, the more points...
                but if you guess exactly, you lose! """);

        int max = 6;
        int min = 1;
        int score = 0;

        int userGuess = takeUserInput();
        if (userGuess == 0)
            Games.postGame(user, score, "dice");

        // assuming errors not present...
        while (userGuess >= 0 && userGuess <= max) {
            if (userGuess == 0) {
                System.out.println("User Exited.");
                postGame(user, score, "dice");
            }

            System.out.println("Rolling the dice...");
            try {
                // delay for dramatic effect (*ﾟﾛﾟ)
                Thread.sleep(500);

            } catch (InterruptedException e) {
                System.err.format("IOException: %s%n", e);
            }

            int random = (int) (Math.random() * (max - min + 1) + min);

            if (userGuess == random) {
                System.out.println("Hard Luck. Score: " + score);
                postGame(user, score, "dice");
            }

            score += 5/(Math.ceil(Math.abs(userGuess - random)));
            System.out.println("Good Guess, the dice roll was " + random);
            System.out.println("Score: " + score);
            System.out.println("Guess again, or press 0 to quit.");
            userGuess = takeUserInput();
        }
    }
}
