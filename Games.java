package javaGames;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Games {

    private String username;
    private String rank;

    public Games(Player user) {
        username = user.getUsername();
    }

    public static void postGame(Player user, int score, String game) {
        if (user instanceof Hack && score > 0) {
            System.out.println("Hack Privileges Activated: Score Doubled! \n");
            score = score * 2;
        }
        writeToLeaderboard(user.getUsername(), score, game);

        System.out.println("""
                                
                Press 1 to return to the main menu.
                Press 2 to choose a new game.""");
         Scanner option = new Scanner(System.in);
         int userExit = option.nextInt();
         if (userExit == 1)
             Menus.mainMenu();
         else if (userExit == 2)
             Menus.gameSelect(user);
    }

    public static int takeUserInput() {
        int inputValue = 0;
        Scanner userInput = new Scanner(System.in);
        try {
            inputValue = userInput.nextInt();
            return inputValue;
        } catch (InputMismatchException ime) {
            //Display Error message
            System.out.println("Sorry, please try again, or press 0 to quit.");
            inputValue = takeUserInput();
        }
        return inputValue;
    }

    public static void writeToLeaderboard(String user, int score, String game) {
        if (score == 0)
            System.out.println("Well, that wasn't great...better luck next time!");
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
}
