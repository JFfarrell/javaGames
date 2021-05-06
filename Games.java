package javaGames;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Games {
    public String user;
    public Games(String user){
        user = user;
    }

    public Games(){
        String user = "Player 1";
    }

    public static void postGame(String user, int score) {
        writeToLeaderboard(user, score);
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
            inputValue = takeUserInput();//Advance the scanner
        }
        return inputValue;
    }

    public static void writeToLeaderboard(String user, int score) {
        try(FileWriter fw = new FileWriter("leaderboard.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(user + ":" + score);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
//        try {
//            FileWriter myWriter = new FileWriter("leaderboard.txt");
//            myWriter.write(user + ":" + score);
//            myWriter.close();
//            System.out.println("Score added to leaderboard.");
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
