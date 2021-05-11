package javaGames;

public class Player {
    private static String username;

    Player(String username) {
        this.username = username;
    }

    Player() {
        this("Player 1");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
