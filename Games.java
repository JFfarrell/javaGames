package javaGames;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Games {

    private String username;

    public Games(Player user) {
        username = user.getUsername();
    }

    // return here after every game ends
    public static void postGame(Player user, int score, String game) {
        if (user instanceof Hack && score > 0) {
            System.out.println("Hack Privileges Activated: Score Doubled! \n");
            score = score * 2;
        }
        writeToLeaderboard(user.getUsername(), score, game);

        System.out.println("""
                                
                Press 1 to return to the main menu.
                Press 2 to choose a new game.""");
        int userInput = Main.checkIfInt();
        if (userInput == 1)
            Menus.mainMenu();
        else if (userInput == 2)
            Menus.gameSelect(user);
        else {
            System.out.println("""
                     Sorry, we don't understand...
                     Returning to game select menu.""");
            Menus.gameSelect(user);
        }
    }

    private static void writeToLeaderboard(String user, int score, String game) {
        if (score == 0)
            System.out.println("\nWell, that wasn't great...better luck next time!");
        if (score >  0) {
            try (FileWriter fw = new FileWriter(game+"leaderboard.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(user + ":" + score);
            } catch (IOException ignored) {
            }
        }
        //sort and display leaderboard
        Leaderboard.Display(game);
    }

    public static int exitOrGuess(Player user, String game) {
        // check the user's input for errors.
        int userGuess = Main.checkIfInt();

        if (userGuess == 0)
            Games.postGame(user, 0, game);
        return userGuess;
    }
}
