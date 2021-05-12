package javaGames;

public class pokemon extends Games{
    public pokemon(Player user) {
        super(user);
    }

    public static void playRPS(Player user) {
        System.out.println("Play Epic Battle Music... https://www.youtube.com/watch?v=PnIOoSVGUs0");
        System.out.println("""
             
             *** Pokemon Battle ***
            
            Choose 1 for Charmander
            Choose 2 for Squirtle
            Choose 3 for Bulbasaur: """);

        int max = 3;
        int min = 1;
        int score = 0;

        if (user instanceof Challenger)
            System.out.println("Challenger Mode activated!");

        int iChooseYou = exitOrGuess(user, "pokemon");

        // assuming errors not present...
        while (iChooseYou >= 0) {

            // make sure user choice in correct range...
            if (iChooseYou > max){
                System.out.println("Please guess between 1 and 3...");
                iChooseYou = exitOrGuess(user, "pokemon");
            }

            int random = (int) (Math.random() * (max - min + 1) + min);

            Exit(user, iChooseYou, score);

            System.out.println(random);
            score = Battle(random - 1, iChooseYou - 1, score, user);

            if (score <= 0) {
                System.out.println("You lose...");
                System.out.println("Score: " + score + "\n");
                postGame(user, score, "pokemon");
        }
            System.out.println("Choose another pokemon to go again, or press 0 to quit.");
            iChooseYou = Main.takeUserInput();
        }
    }

    public static void Exit(Player user, int iChooseYou, int score) {
        if (iChooseYou == 0) {
            System.out.println("User Exited.");
            System.out.println("Score: " + score + "\n");
            postGame(user, score, "pokemon");
        }
    }

    public static int Battle(int opponentChoice, int userChoice, int score, Player user) {
        // associate the user and random ints with a rock-paper-scissors pokemon game
        // Charmander beats Bulbasaur, Bulbasaur beats Squirtle, Squirtle beats Charmander
        System.out.println("in check winner: " + opponentChoice);
        String[] rps = {"Charmander", "Squirtle", "Bulbasaur"};
        String userPoke = rps[userChoice].toString();
        String oppPoke = rps[opponentChoice].toString();

        int max = score + 200;
        int min = score + 100;
        int random = (int) (Math.random() * (max - min + 1) + min);

        System.out.println("Opponent sent out " + oppPoke);
        try {
            // delay for dramatic effect (*ﾟﾛﾟ)
            Thread.sleep(300);

        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("You sent out " + userPoke);
        score = calculateScores(userChoice, opponentChoice, userPoke, oppPoke, score, random, user);
        return score;
    }

    public static int calculateScores(int userChoice, int opponentChoice, String userPoke,
                                      String oppPoke, int score, int random, Player user) {

        // score reduction is steeper for Challenger mode
        if ((userChoice + 1) % 3 == opponentChoice) {
            System.out.println(userPoke + " loses to " + oppPoke);
            if (user instanceof Challenger)
                score -= random;
            else
                score -= random/2;
        }

        if ((userChoice + 2) % 3 == opponentChoice) {
            System.out.println(oppPoke + " loses to " + userPoke);
            score += random;
        }

        if (userChoice  == opponentChoice) {
            System.out.println(userPoke + " draws with " + oppPoke);
            score += random / 2;
        }

        System.out.println("\nCurrent Score: " + score + "\n");

        return score;
    }
}
