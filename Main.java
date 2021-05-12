package javaGames;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Get Ready to Play!");
        Menus.mainMenu();
    }

    public static int checkIfInt() {
        // assess if the user inputted a string or int...recycle until correct input
        int inputValue = 0;
        Scanner userInput = new Scanner(System.in);
        if (userInput.hasNextInt()) {
            try {
                inputValue = userInput.nextInt();
                return inputValue;
            } catch (InputMismatchException ime) {
                //Display Error message
                System.out.println("Sorry, please try again, or press 0 to quit.");
                inputValue = checkIfInt();
            }
        }
        else {
            System.out.println("Sorry, please try again, or press 0 to quit..");
            inputValue = checkIfInt();
        }
        return inputValue;
    }
}