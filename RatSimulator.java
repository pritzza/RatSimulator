import java.util.Random;

public class RatSimulator {

    public static void main(String[] args) {

        int playerHP = 90;
        int ratsKilled = 0;

        Random rng = new Random();

        while (playerHP > 0) {

            playerHP = playerHP + 10 + ratsKilled;
            int ratHP = rng.nextInt(30 + 1 + ratsKilled);
            ratsKilled++;

            System.out.println("\nA rat appears!");

            while (ratHP > 0) {

                int ratATK = rng.nextInt(6 + 1 + ratsKilled);
                int playerATK = rng.nextInt(20 + 5);

                playerHP = playerHP - ratATK;
                ratHP = ratHP - playerATK;

                System.out.println("\n\nYou attack the rat, " + playerATK + " damage!");
                System.out.println("\nThe rat (#" + ratsKilled + ") remains with " + ratHP + " HP.");
                System.out.println("\n\nThe rat retaliates, " + ratATK + " damage!");
                System.out.println("\nYou have " + playerHP + " HP now.\n");

            }
        }
        System.out.println("\n\nYou only manage to fend off a mere " + ratsKilled
                + " rats.\n\nHumanity succumbs to the order of the rats...\n\n");
    }
}
