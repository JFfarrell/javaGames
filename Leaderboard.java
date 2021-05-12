package javaGames;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Leaderboard {
    public static void Display(String game) {
        // check file exists
        try {
            File myFile = new File(game+"Leaderboard.txt");
            Scanner file = new Scanner(myFile);

            // divide text file into lines/strings
            List<ArrayList<String>> sortedLeaderboard = splitLeaderboardFile(file);

            // display leaderboard contents
            System.out.println(game + " Leaderboard");
            System.out.println("-------------");
            printLeaderboard(sortedLeaderboard);

            file.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<ArrayList<String>> splitLeaderboardFile(Scanner file) {
        ArrayList<String> unsortedUsers = new ArrayList<String>();
        ArrayList<String> unsortedScores = new ArrayList<String>();
        ArrayList<Integer> unsortedIntScores = new ArrayList<Integer>();

        // prepare separate lists from leaderboard txt file, for later sorting
        while (file.hasNextLine()) {
            String line = file.nextLine();
            // only count lines with content
            if (line.length() > 0) {
                String split[] = line.split(":");
                String username = split[0].toString();
                String score = split[1].toString();
                unsortedUsers.add(username);
                unsortedScores.add(score);
                unsortedIntScores.add(Integer.parseInt(score));
            }
        }
        List<ArrayList<String>> leaderboard = sortLeaderboard(unsortedIntScores, unsortedScores, unsortedUsers);
        return leaderboard;
    }

    public static List<ArrayList<String>> sortLeaderboard(ArrayList<Integer> unsortedIntScores,
                                                          ArrayList<String> unsortedScores,
                                                          ArrayList<String> unsortedUsers) {

        ArrayList<String> sortedUsers = new ArrayList<String>();
        ArrayList<String> sortedScores = new ArrayList<String>();
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        List<ArrayList<String>> leaderboard = new ArrayList<>();

        // check the index of the biggest number and replace that number with -1, to get the next
        for (int x = 0; x < unsortedScores.size(); x++) {
            for (int y = 0; y < unsortedScores.size(); y++) {
                // get the index of the biggest item and save it
                if (unsortedIntScores.get(y) == Collections.max(unsortedIntScores)
                        && unsortedIntScores.get(y) > 0) {
                    indexes.add(y);
                    unsortedIntScores.set(y, Integer.valueOf(-1));
                }
            }
        }
        // use the indexed list to sort our leaderboard components
        for(int i:indexes) {
            sortedScores.add(unsortedScores.get(i));
            sortedUsers.add(unsortedUsers.get(i));
        }
        leaderboard.add(sortedUsers);
        leaderboard.add(sortedScores);

        return leaderboard;
    }

    public static void printLeaderboard(List<ArrayList<String>> leaderboard) {
        // print only first 10
        for (int x = 0; x < leaderboard.get(0).size(); x++) {
            if (x < 10)
                System.out.println(leaderboard.get(0).get(x) + " : " + leaderboard.get(1).get(x));
            try {
                // delay for dramatic effect (*ﾟﾛﾟ)
                Thread.sleep(400);

            } catch (InterruptedException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
    }

    public static void createLeaderboard(String game) {
        // create leaderboard for game, if not already one made
        // code from https://www.w3schools.com/java/java_files_create.asp
        try {
            File myObj = new File(game+"Leaderboard.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("Leaderboard Loading.....\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
