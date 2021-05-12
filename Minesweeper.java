package javaGames;

public class Minesweeper extends Games {
    private String username;

    public Minesweeper(Player user) {
        super(user);
        username = user.getUsername();
    }

        public static void playMinesweeper(Player user) {
        System.out.println("""
             
             *** Minesweeper ***
            Avoid the bomb,
            guess as many correct numbers as you can!
            Guess number between 1 and 5:""");

        int max = 5;
        int min = 1;
        int score = 0;

        if (user instanceof Challenger){
            System.out.println("Challenger Mode activated!");
            max -= 1;
        }

        // check if exit or guess
        int userGuess = exitOrGuess(user, "minesweeper");

        // assuming errors not present...
        while (userGuess >= 0) {
            // produce a random number and check if user's guess matches
            int random = (int) (Math.random() * (max - min + 1) + min);

            // various conditions and error checks
            checkInput(user, userGuess, random, score, max);

            if (userGuess > max){
                System.out.println("Please guess between 1 and 5...");
                userGuess = exitOrGuess(user, "minesweeper");
            }

            score += 1;
            System.out.println("You guessed " + userGuess);
            System.out.println("phew...good guess!\n" +
                    "The bomb was on: " + random + "\n" +
                    "Score: " + score + "\n\n" +
                    "Guess again, or press 0 to quit.");
            userGuess = Main.checkIfInt();
        }
    }

    private static void checkInput(Player user, int userGuess, int random, int score, int max) {
        // various checks on user input throughout game
        if (userGuess == random) {
            System.out.println("""
                        (((≪*☆*KA-BOOM*☆*≫)))
                        You lose...""");
            System.out.println("\nFinal Score: " + score);
            postGame(user, score, "minesweeper");
        }

        if (userGuess == 0) {
            System.out.println("User Exited.");
            System.out.println("Score: " + score);
            postGame(user, score, "minesweeper");
        }
    }
}
