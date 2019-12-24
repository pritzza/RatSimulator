import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class RatSimulatorTwo {

    JFrame window;
    JTextArea mainTextArea;
    JPanel mainTextPanel, attackButtonPanel, statsPanel;
    JLabel playerHPLabel, ratHPLabel, ratsKilledLabel;
    Container con;
    JButton attackButton, magicButton, healButton;
    int playerATK, ratATK;

    int ratHP = 15;
    int playerHP = 75;
    int ratsKilled = 0;
    int turn = 1;

    boolean dead;

    ChoiceHandler choiceHandler = new ChoiceHandler();
    Random rng = new Random();

    public static void main(final String[] args) {

        new RatSimulatorTwo();

    }

    public RatSimulatorTwo() {

        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);
        con = window.getContentPane();

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(76, 100, 630, 250);
        mainTextPanel.setBackground(Color.DARK_GRAY);
        con.add(mainTextPanel);
        mainTextPanel.setOpaque(true);

        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 600, 190);
        mainTextArea.setBackground(Color.DARK_GRAY);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setLineWrap(true);
        mainTextArea.setEditable(false);
        mainTextPanel.add(mainTextArea);
        mainTextArea.setText("\nA rat appears!");

        attackButtonPanel = new JPanel();
        attackButtonPanel.setBounds(250, 370, 275, 150);
        attackButtonPanel.setBackground(Color.black);
        con.add(attackButtonPanel);

        attackButton = new JButton("Attack");
        attackButton.setBackground(Color.black);
        attackButton.setForeground(Color.white);
        attackButtonPanel.add(attackButton);
        attackButton.setFocusPainted(false);
        attackButton.addActionListener(choiceHandler);
        magicButton = new JButton("Magic");
        magicButton.setBackground(Color.black);
        magicButton.setForeground(Color.white);
        attackButtonPanel.add(magicButton);
        magicButton.setFocusPainted(false);
        magicButton.addActionListener(choiceHandler);
        healButton = new JButton("Heal");
        healButton.setBackground(Color.black);
        healButton.setForeground(Color.white);
        attackButtonPanel.add(healButton);
        healButton.setFocusPainted(false);
        healButton.addActionListener(choiceHandler);

    }

    public void attackRat() {

        if (ratHP <= 0) {

            ratHP = rng.nextInt(37) + 5 + (2 * ratsKilled);
            playerHP = playerHP + 25;
            ratsKilled++;

            mainTextArea.setText("\nA new rat appears with " + ratHP + " HP!");
        }

        else if (ratHP > 0) {

            int ratATK = rng.nextInt(3) * ratsKilled + (2 * ratsKilled) + 1;
            int playerATK = rng.nextInt((3) + 2 * ratsKilled) + 7;

            turn++;

            playerHP = playerHP - ratATK;
            ratHP = ratHP - playerATK;

            mainTextArea.setText("\nYou attack the rat for " + playerATK + " damage!\n\nThe rat (#" + ratsKilled
                    + ") remains with " + ratHP + " HP.\n\n\n\nThe rat retaliates with " + ratATK
                    + " damage!\n\nYou have " + playerHP + " HP now.\n\n\n\nTurn:" + turn);

        }

        while (playerHP < 0) {

            attackButtonPanel.setVisible(false);
            mainTextArea.setText("\nYou died.\n\n\nYou only manage to fend off a mere " + ratsKilled
                    + " rats.\n\n\n\n\n\n\n\nHumanity succumbs to the order of the rats.");
            break;
        }

    }

    public void magicAttackRat() {

        if (ratHP <= 0) {

            ratHP = rng.nextInt(37) + 5 + (2 * ratsKilled);
            playerHP = playerHP + 25;
            ratsKilled++;

            mainTextArea.setText("\nA new rat appears with " + ratHP + " HP!");
        }

        else if (ratHP > 0) {

            int ratATK = rng.nextInt(3) * ratsKilled + (2 * ratsKilled) + 1;
            int playerATK = rng.nextInt(9) * ratsKilled + 3;

            turn++;

            playerHP = playerHP - ratATK;
            ratHP = ratHP - playerATK;

            mainTextArea.setText("\nYou conjure a spell against the rat for " + playerATK + " damage!\n\nThe rat (#"
                    + ratsKilled + ") remains with " + ratHP + " HP.\n\n\n\nThe rat retaliates with " + ratATK
                    + " damage!\n\nYou have " + playerHP + " HP now.\n\n\n\nTurn:" + turn);

        }

        while (playerHP < 0) {

            attackButtonPanel.setVisible(false);
            mainTextArea.setText("\nYou died.\n\n\nYou only manage to fend off a mere " + ratsKilled
                    + " rats.\n\n\n\n\n\n\n\nHumanity succumbs to the order of the rats.");
            break;
        }

    }

    public void heal() {

        if (ratHP <= 0) {

            ratHP = rng.nextInt(37) + 5 + (2 * ratsKilled);
            playerHP = playerHP + 25;
            ratsKilled++;

            mainTextArea.setText("\nA new rat appears with " + ratHP + " HP!");
        }

        else if (ratHP > 0) {

            int ratATK = rng.nextInt(3) * ratsKilled + (2 * ratsKilled) + 1;
            int playerHPHealed = rng.nextInt(4) * ratsKilled + ratsKilled + 5;

            playerHP = playerHP + playerHPHealed;

            playerATK = 0;

            turn++;

            playerHP = playerHP - ratATK;
            ratHP = ratHP - playerATK;

            mainTextArea.setText("\nYou heal yourself for " + playerHPHealed + " HP!\n\nThe rat (#" + ratsKilled
                    + ") remains with " + ratHP + " HP.\n\n\n\nThe rat retaliates with " + ratATK
                    + " damage!\n\nYou have " + playerHP + " HP now.\n\n\n\nTurn:" + turn);

        }

        while (playerHP < 0) {

            attackButtonPanel.setVisible(false);
            mainTextArea.setText("\nYou died.\n\n\nYou only manage to fend off a mere " + ratsKilled
                    + " rats.\n\n\n\n\n\n\n\nHumanity succumbs to the order of the rats.");
            break;
        }

    }

    public class ChoiceHandler implements ActionListener {

        public void actionPerformed(final ActionEvent event) {

            final String yourChoice = event.getActionCommand();

            switch (yourChoice) {
            case "Attack":
                attackRat();
                break;
            case "Magic":
                magicAttackRat();
                break;
            case "Heal":
                heal();
                break;
            }

        }
    }
}