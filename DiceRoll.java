package javaGames;

public class DiceRoll extends Games {
    public DiceRoll(Player user) {
        super(user);
    }

    public static void rollTheDice(Player user) {
        System.out.println("""
                 
                 *** Dice Roll ***
                            
                Guess the number on the dice.
                The closer you get, the more points...
                but if you guess exactly, you lose! """);

        int max = 6;
        int min = 1;
        int score = 0;

        if (user instanceof Challenger)
            challengerMode(user, score, max, min);
        else
            game(user, score, max, min);
    }

    public static void game(Player user, int score, int max, int min) {

        int userGuess = Main.takeUserInput();
        if (userGuess == 0)
            Games.postGame(user, score, "dice");

        // assuming errors not present...
        while (userGuess >= 0) {
            Exit(user, userGuess, score, max);

            if (userGuess > max){
                System.out.println("Please guess between 1 and 6...");
                userGuess = exitOrGuess(user, "dice");
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
                System.out.println("Hard Luck, the roll was " + random +".");
                System.out.println("Your Score: " + score);
                postGame(user, score, "dice");
            }

            score += 5/(Math.ceil(Math.abs(userGuess - random)));
            System.out.println("Good Guess, the dice roll was " + random);
            System.out.println("Score: " + score);
            System.out.println("Guess again, or press 0 to quit.");
            userGuess = Main.takeUserInput();
        }
    }

    public static void challengerMode(Player user, int score, int max, int min) {
        System.out.println("""
                
                *** Challenger Mode ***
                
                There are now two numbers to avoid.
                Your score will now grow twice as fast!""");

        int userGuess = Main.takeUserInput();
        if (userGuess == 0)
            Games.postGame(user, score, "dice");

        // assuming errors not present...
        while (userGuess >= 0) {
            Exit(user, userGuess, score, max);

            if (userGuess > max){
                System.out.println("Please guess between 1 and 6...");
                userGuess = exitOrGuess(user, "dice");
            }

            System.out.println("Rolling the dice...");
            try {
                // delay for dramatic effect (*ﾟﾛﾟ)
                Thread.sleep(500);

            } catch (InterruptedException e) {
                System.err.format("IOException: %s%n", e);
            }

            int random = (int) (Math.random() * (max - min + 1) + min);
            int random2 = (int) (Math.random() * (max - min + 1) + min);

            if (userGuess == random || userGuess == random2) {
                System.out.println("Hard Luck, the roll was " + random +".");
                System.out.println("Your Score: " + score);
                postGame(user, score, "dice");
            }

            score += 2*(5/(Math.ceil(Math.abs(userGuess - random))));
            System.out.println("Good Guess, the dice roll was " + random);
            System.out.println("Score: " + score);
            System.out.println("Guess again, or press 0 to quit.");
            userGuess = Main.takeUserInput();
        }
    }

    public static void Exit(Player user, int userGuess, int score, int max) {
        if (userGuess == 0) {
            System.out.println("User Exited.");
            postGame(user, score, "dice");
        }
    }
}
