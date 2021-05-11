package javaGames;

public class pokemon extends Games{
    public pokemon(Player user) {
        super(user);
    }

    public static void playRPS(Player user) {
        System.out.println("""
             
             *** Pokemon Battle ***
            
            Choose 1 for Charmander
            Choose 2 for Squirtle
            Choose 3 for Bulbasaur: """);

        int max = 2;
        int min = 0;
        int score = 0;

        int iChooseYou = takeUserInput();
        if (iChooseYou == 0)
            Games.postGame(user, score, "pokemon");

        // assuming errors not present...
        while (iChooseYou >= 0 && iChooseYou <= max + 1) {
            if (iChooseYou == 0) {
                System.out.println("User Exited.");
                postGame(user, score, "pokemon");
            }

            int random = (int) (Math.random() * (max - min + 1) + min);

            score = checkWinner(random, iChooseYou - 1, score);

            if (score <= 0) {
                System.out.println("You lose...");
                postGame(user, score, "pokemon");
        }
            System.out.println("Choose another pokemon to go again, or press 0 to quit.");
            iChooseYou = takeUserInput();
        }
    }

    public static int checkWinner(int opponentChoice, int userChoice, int score) {
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

        if ((userChoice + 1) % 3 == opponentChoice) {
            System.out.println(userPoke + " loses to " + oppPoke);
            score -= random;
        }

        if ((userChoice + 2) % 3 == opponentChoice) {
            System.out.println(oppPoke + " loses to " + userPoke);
            score += random;
        }

        if (userChoice  == opponentChoice) {
            System.out.println(userPoke + " draws with " + oppPoke);
            score += random / 2;
        }

        System.out.println("Current Score: " + score);

        return score;
    }
}
