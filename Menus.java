package javaGames;

import java.util.Scanner;

public class Menus {
    public static void mainMenu() {
        String username = "";
        System.out.println("Welcome!");
        int userChoice = userWelcome();

        if (userChoice == 0)
            quit();
        else
            username = getUsername();

        createUserProfile(username);
    }

    public static void createUserProfile(String username) {
        // provision for alternative player types
        if (username.equals("12345")) {
            System.out.println("Welcome Hack. Please enter your username: ");
            String hackUsername = "Hack " + getUsername();
            Hack user = new Hack();
            user.setUsername(hackUsername);
            gameSelect(user);
        }
        if (username.equals("Challenger")) {
            System.out.println("Welcome Challenger. Please enter your username: ");
            String challengerUsername = "Challenger " + getUsername();
            Challenger user = new Challenger();
            user.setUsername(challengerUsername);
            gameSelect(user);
        }
        Player user = new Player(username);
        user.setUsername(username);
        gameSelect(user);
    }

    public static void quit() {
        System.out.println("User Exited.");
        System.exit(1);
    }

    public static String getUsername() {
        System.out.print("Please enter you name: ");
        Scanner prompt = new Scanner(System.in);
        String userInput = prompt.nextLine();
        if (userInput == "")
            userInput = "Player 1";
        return userInput;
    }

    public static int userWelcome() {
        System.out.println("Press 1 to start, or 0 to quit.");
        int userInput = Main.checkIfInt();

        while (userInput != 0 && userInput != 1) {
            System.out.println("Sorry, we don't understand.");
            userInput = userWelcome();
        }
        return userInput;
    }

    public static void gameSelect(Player user) {
        String[] gameStore = {"Minesweeper", "Pokemon Battle", "Dice Roll"};

        System.out.println("\nNew Player " + user.getUsername() + "\n" +
                "Please select a game: \n" +
                "1. " + gameStore[0] + "\n" +
                "2. " + gameStore[1] + "\n" +
                "3. " + gameStore[2] + "\n" +
                "or Press 0 to return to the main menu.");

        if (user instanceof Challenger)
            System.out.println("Challenger Profile detected. Games will be harder.");
        if (user instanceof Hack)
            System.out.println("Hack Profile detected.");

        int userInput = Main.checkIfInt();

        if (userInput == 0) {
            mainMenu();
        }

        if (userInput == 1) {
            Leaderboard.createLeaderboard("minesweeper");
            Minesweeper minesweeper = new Minesweeper(user);
            minesweeper.playMinesweeper(user);
        }

        if (userInput == 2) {
            Leaderboard.createLeaderboard("pokemon");
            Pokemon battle = new Pokemon(user);
            Pokemon.playPKMN(user);
        }

        if (userInput == 3) {
            Leaderboard.createLeaderboard("dice");
            DiceRoll roll = new DiceRoll(user);
            roll.rollTheDice(user);
        }

        else {
            System.out.println("Please check back later for more games...");
            gameSelect(user);
        }
    }
}
