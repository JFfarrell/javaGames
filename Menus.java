package project;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Menus {
    public static void mainMenu() {
        String user = "";
        int userChoice = userWelcome();

        if (userChoice == 0)
            quit();
        else
            user = createUserProfile();

        gameSelect(user);
    }

    public static void quit() {
        System.out.println("User Exited.");
        System.exit(1);
    }

    public static String createUserProfile() {
        System.out.print("Please enter you name: ");
        Scanner prompt = new Scanner(System.in);
        String userInput = prompt.next();
        return userInput;
    }

    public static int userWelcome() {
        System.out.println("""
                Welcome!

                Press 1 to start, or 0 to quit.""");
        Scanner prompt = new Scanner(System.in);
        int userInput = prompt.nextInt();

        while (userInput < 0 || userInput > 3) {
            System.out.println("Sorry, we don't understand.");
            userInput = userWelcome();
        }
        return userInput;
    }

    public static void gameSelect(String user) {
        String[] gameStore = {"Minesweeper", "PacMan", "Hangman"};
        Minesweeper minesweeper = new Minesweeper(user);

        System.out.println("New Player " + user + "\n" +
                "Please select a game: \n" +
                "1. " + gameStore[0] + "\n" +
                "2. " + gameStore[1] + "\n" +
                "3. " + gameStore[2]);
        Scanner prompt = new Scanner(System.in);
        int userInput = prompt.nextInt();
        System.out.println(gameStore[userInput-1]);

        if (userInput == 1)
            minesweeper.Game(user);
        else
            System.out.println("Please check back later for more games...");
    }

    public static void createLeaderboard() {
        // code from https://www.w3schools.com/java/java_files_create.asp
        try {
            File myObj = new File("leaderboard.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println(".....");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
