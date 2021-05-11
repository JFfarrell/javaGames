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
        if (username.equals("12345")) {
            Hack user = new Hack();
            user.setUsername("HackMaster");
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
        Scanner prompt = new Scanner(System.in);
        int userInput = prompt.nextInt();

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
                "3. " + gameStore[2]);
        Scanner prompt = new Scanner(System.in);
        int userInput = prompt.nextInt();

        if (userInput == 1) {
            Leaderboard.createLeaderboard("minesweeper");
            Minesweeper minesweeper = new Minesweeper(user);
            minesweeper.playMinesweeper(user);
        }

        if (userInput == 2) {
            Leaderboard.createLeaderboard("pokemon");
            pokemon battle = new pokemon(user);
            pokemon.playRPS(user);
        }

        if (userInput == 3) {
            Leaderboard.createLeaderboard("dice");
            DiceRoll roll = new DiceRoll(user);
            roll.rollTheDice(user);
        }

        else
            System.out.println("Please check back later for more games...");
    }

}
