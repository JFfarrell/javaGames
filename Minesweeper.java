package javaGames;

public class Minesweeper extends Games {
    private String rank;
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
            Guess number between 1 and 5: """);

        int max = 5;
        int min = 1;
        int score = 0;

        // check the user's input for errors.
        int userGuess = takeUserInput();

        if (userGuess == 0)
            Games.postGame(user, 0, "minesweeper");

        // assuming errors not present...
        while (userGuess >= 0 && userGuess <= max) {
            if (userGuess == 0) {
                System.out.println("User Exited.");
                postGame(user, 0, "minesweeper");
            }

            int random = (int) (Math.random() * (max - min + 1) + min);

            if (userGuess == random) {
                System.out.println("""
                        Ka-Boom, You died.""");
                System.out.println("\nFinal Score: " + score);
                postGame(user, score, "minesweeper");
            }

            score += 1;
            System.out.println(String.format("phew...good guess.\n" +
                    "Score: " + score + "\n" +
                    "Guess again, or press Q to quit."));
            userGuess = takeUserInput();
        }
    }
}
