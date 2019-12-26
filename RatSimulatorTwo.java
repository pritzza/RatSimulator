import java.util.Random;
import java.util.ArrayList;

import java.lang.Math;

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
    JPanel mainTextPanel, attackButtonPanel, statsPanel, descriptionPanel;
    JLabel statsLabel, ratsKilledLabel, descriptionLabel;
    Container con;
    JButton attackButton, magicButton, healButton, continueButton;
    int playerATK, ratATK, playerGold, ratGold;

    int ratHP = 15;
    int playerHP = 100;
    int playerMaxHP = 100;
    int playerMP = 10;
    int playerMaxMP = 10;
    int ratsKilled = 1;
    int turn = 1;
    int ratRNG = 0;

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

        statsPanel = new JPanel();
        statsPanel.setBounds(57, 58, 670, 32);
        statsPanel.setBackground(Color.black);
        con.add(statsPanel);

        statsLabel = new JLabel();
        statsLabel.setForeground(Color.white);
        statsPanel.add(statsLabel);
        updateStats();

        descriptionPanel = new JPanel();
        descriptionPanel.setBounds(150, 430, 482, 69);
        descriptionPanel.setBackground(Color.black);
        con.add(descriptionPanel);

        descriptionLabel = new JLabel();
        descriptionLabel.setBounds(0, 0, 482, 69);
        descriptionLabel.setForeground(Color.white);
        descriptionPanel.add(descriptionLabel);

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
        attackButton.setVisible(true);
        attackButtonPanel.add(attackButton);
        attackButton.setFocusPainted(false);
        attackButton.addActionListener(choiceHandler);
        attackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                attackButton.setBackground(Color.black);
                descriptionLabel.setText(
                        "( " + (ratsKilled + 6) + " - " + ((3 * ratsKilled) + 7) + "  damage )  &  Restores 1 MP");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                attackButton.setBackground(Color.black);
                descriptionLabel.setText("");
            }
        });

        magicButton = new JButton("Magic");
        magicButton.setBackground(Color.black);
        magicButton.setForeground(Color.white);
        magicButton.setVisible(true);
        attackButtonPanel.add(magicButton);
        magicButton.setFocusPainted(false);
        magicButton.addActionListener(choiceHandler);
        magicButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                magicButton.setBackground(Color.black);
                if (playerMP < 3) {
                    descriptionLabel.setText("You don't have enough mana!  You need atlesat 4 MP to cast Magic");
                } else {
                    descriptionLabel
                            .setText("( " + turn + " - " + (turn + (13 * ratsKilled)) + "  damage )  Requires: 4 MP");
                }
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                magicButton.setBackground(Color.black);
                descriptionLabel.setText("");
            }
        });
        healButton = new JButton("Heal");
        healButton.setBackground(Color.black);
        healButton.setForeground(Color.white);
        healButton.setVisible(true);
        attackButtonPanel.add(healButton);
        healButton.setFocusPainted(false);
        healButton.addActionListener(choiceHandler);
        healButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                healButton.setBackground(Color.black);
                if (playerMP < (2 + (Math.round(ratsKilled / 8)))) {
                    descriptionLabel.setText("You don't have enough mana!  You need atleast "
                            + (2 + (Math.round(ratsKilled / 8))) + " MP to cast Heal.");
                } else {
                    descriptionLabel.setText("( " + ((4 * ratsKilled) + 25) + " - " + ((10 * ratsKilled) + 25)
                            + "  HP )\n\n  Requires : " + (2 + (Math.round(ratsKilled / 8))) + " MP");
                }
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                healButton.setBackground(Color.black);
                descriptionLabel.setText("");
            }
        });
        continueButton = new JButton("Continue");
        continueButton.setBackground(Color.black);
        continueButton.setForeground(Color.white);
        continueButton.addActionListener(choiceHandler);
        attackButtonPanel.add(continueButton);
        continueButton.setText("Fight It");
        continueButton.setVisible(false);
    }

    // If attack button pressed, calculates and displays what happens

    public void attackRat() {

        // If rat dead, encounter new rat
        if (ratHP <= 0) {
            generateRat();
        }

        // If rat alive, generate playerATK and ratATK and exchange damage
        else if (ratHP > 0) {
            generateRatATK();
            int playerATK = rng.nextInt(((3) + 1) + ratsKilled) + 7;

            // If player's MP is less than their max MP, restore 1 MP
            if (playerMP < playerMaxMP) {
                playerMP++;
            }

            // Increase turn count by 1, and calculate damage done to rat
            turn++;
            ratHP = ratHP - playerATK;

            // If rat isnt killed by attack, the rat then attacks. HP, MP, G, Turns are
            // updated. Battle message is displayed
            if (ratHP > 0) {

                playerHP = playerHP - ratATK;

                updateStats();

                mainTextArea.setText("\nYou attack the " + ratNames() + "rat for " + playerATK + " damage!\n\n\n\nThe "
                        + ratNames() + "rat (# " + ratsKilled + ") remains with " + ratHP
                        + " HP.\n\n\n\nThe rat retaliates with " + ratATK + " damage!");
            }

            // Else if Rat is killed by players attack, increase player's max stats, update
            // HP, MP, G, turn values, and display rat killed message
            else {
                ratKilled();
                updateStats();

                mainTextArea.setText(
                        "\nYou attack the " + ratNames() + "rat for " + playerATK + " damage!\n\n\n\nThe rat (# "
                                + ratsKilled + ") dies and drops " + ratGold + " gold!\n\n\nYour max HP increases by "
                                + (25 + ratsKilled) + "!\n\nYour max MP increases by 1!");
            }
        }

        // If rat isnt killed by player attack, and player's HP goes below 0 after
        // exchanging damage, player dies. Displays death screen message
        while (playerHP < 0) {
            die();
            break;
        }
    }

    // If magic button pressed, calculates and displays what happens
    public void magicAttackRat() {

        // If rat dead, encounter new rat
        if (ratHP <= 0) {
            generateRat();
        }

        // After checking if player has enough mana to cast magic attack and rat is
        // alive, generate playerATK and ratATK, subtract 4 MP, increase turn, and
        // exchange damage
        else if (ratHP > 0) {

            generateRatATK();
            int playerATK = rng.nextInt(11) * ratsKilled + (3 * ratsKilled) + turn;

            playerMP = playerMP - 4;
            turn++;
            ratHP = ratHP - playerATK;

            // If rat isnt killed by attack, the rat then attacks. HP, MP, G, Turns are
            // updated. Battle message is displayed
            if (ratHP > 0) {

                playerHP = playerHP - ratATK;
                updateStats();

                mainTextArea.setText("\nYou conjure a spell against the " + ratNames() + "rat for " + playerATK
                        + " damage!\n\n\n\nThe rat (# " + ratsKilled + ") remains with " + ratHP
                        + " HP.\n\n\n\nThe rat retaliates with " + ratATK + " damage!");
            }

            // Else if Rat is killed by players attack, increase player's max stats, update
            // HP, MP, G, turn values, and display rat killed message
            else {
                ratKilled();
                updateStats();
                mainTextArea.setText("\nYou conjuer a spell against the " + ratNames() + "rat for " + playerATK
                        + " damage!\n\n\n\nThe " + ratNames() + "rat (# " + ratsKilled + ") dies and drops " + ratGold
                        + " gold!\n\n\nYour max HP increases by " + (25 + ratsKilled)
                        + "!\n\nYour max MP increases by 1!");
            }
        }

        // If rat isnt killed by player attack, and player's HP goes below 0 after
        // exchanging damage, player dies. Displays death screen message
        while (playerHP < 0) {

            die();
            break;
        }
    }

    // If magic button pressed, calculates and displays what happens
    public void heal() {

        // If rat dead, encounter new rat
        if (ratHP <= 0) {
            generateRat();
        }

        // If rat alive, calculate rat ATK, how much player with heal
        else if (ratHP > 0) {

            generateRatATK();
            int playerHPHealed = rng.nextInt((6) + 5) * ratsKilled + 25;

            // If player's HP is less than their max HP, add playerHPHealed + current
            // playerHP
            if (playerHP < playerMaxHP) {
                playerHP = playerHP + (Math.round(playerHPHealed));
            }

            // Prevents heal from going past max HP
            while (playerHP > playerMaxHP) {
                playerHP = playerMaxHP;
            }

            // Subtract MP after cast, increase turn, subtract rat damage, update HP, MP, G,
            // Turn. Display battle message
            playerMP = playerMP - (2 + (Math.round(ratsKilled / 8)));
            turn++;
            playerHP = playerHP - ratATK;
            updateStats();
            mainTextArea.setText(
                    "\nYou heal yourself for " + playerHPHealed + " HP!\n\n\nThe " + ratNames() + "rat (# " + ratsKilled
                            + ") remains with " + ratHP + " HP.\n\n\n\nThe rat retaliates with " + ratATK + " damage!");
        }

        // If rat isnt killed by player attack, and player's HP goes below 0 after
        // exchanging damage, player dies. Displays death screen message
        while (playerHP < 0) {
            die();
            break;
        }
    }

    // Updates values of text in infoLabel
    public void updateStats() {

        statsLabel.setText("HP: " + playerHP + " / " + playerMaxHP + "            MP: " + playerMP + " / " + playerMaxMP
                + "            G: " + playerGold
                + "                                                                                                                    Turn: "
                + turn);
    }

    // Message when player dies
    public void die() {

        statsLabel.setText("You Have Fallen To The Rats");
        attackButtonPanel.setVisible(false);
        mainTextArea.setText("\nYou died.\n\n\nYou only manage to fend off a mere " + ratsKilled
                + " rats.\n\n\n\n\n\n\n\nHumanity succumbs to the order of the rats.");
    }

    // Increase max player stats, give gold, increase ratsKilled
    public void ratKilled() {

        ratHP = 0;
        ratATK = 0;
        playerMaxHP = (playerMaxHP + 10) + ratsKilled;
        playerMaxMP = playerMaxMP + 1;
        playerGold = playerGold + ratGold;
        ratsKilled++;
    }

    // Generates what rat will appear, display message
    public void generateRat() {

        ratRNG = rng.nextInt(16);
        mainTextArea
                .setText("\nA " + rats() + "rat appears with " + ratHP + " HP!\n\n\n\n\n\n\n\n\n\nWhat will you do?");
        ratGold = 1 + rng.nextInt(3) * ratsKilled / 7;

    }

    // Used to identify the type of rat you encounter and calculate their HP, USE IN
    // RAT ENCOUNTER MESSAGE
    public String rats() {
        if (ratRNG == 1) {
            // HP 11, ATK 1 == 12
            ratHP = rng.nextInt(10) + (11 * ratsKilled);
            return "fat ";
        } else if (ratRNG == 2) {
            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            return "big ";
        } else if (ratRNG == 3) {
            // HP 2, ATK 6 == 8
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            return "smelly ";
        } else if (ratRNG == 4) {
            // HP 4, ATK 4 == 8
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            return "russian ";
        } else if (ratRNG == 5) {
            // HP 2, ATK 3 == 5
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            return "indian ";
        } else if (ratRNG == 6) {
            // HP 5, ATK 3 == 8
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            return "hairy ";
        } else if (ratRNG == 7) {
            // HP 10, ATK 10 == 20
            ratHP = rng.nextInt(10) + (10 * ratsKilled);
            return "Remy ";
        } else if (ratRNG == 8) {
            // HP 8, ATK 8 == 16
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            return "super ";
        } else if (ratRNG == 9) {
            // HP 2, ATK 11 == 13
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            return "space ";
        } else if (ratRNG == 10) {
            // HP 1, 9 == 10
            ratHP = rng.nextInt(10) + (1 * ratsKilled);
            return "cannon ";
        } else if (ratRNG == 11) {
            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            return "robot ";
        } else if (ratRNG == 12) {
            // HP 3, ATK -3 == 0
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            return "canadian ";
            // steal syrup button: turns
        } else if (ratRNG == 13) {
            // HP 3, ATK 8 == 11
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            return "canadian (mad) ";
        } else if (ratRNG == 14) {
            // HP 4, ATK 5 == 9
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            return "boxer ";
        } else if (ratRNG == 14) {
            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            return "five toe'd ";
        } else {
            // HP 3, ATK 2 == 5
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            return "";
        }
    }

    // Used every turn in combat to randomly generate the rat's ATK
    public void generateRatATK() {
        if (ratRNG == 1) {
            ratATK = rng.nextInt(10) + (1 * ratsKilled) / 4;
        } else if (ratRNG == 2) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
        } else if (ratRNG == 3) {
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 4;
        } else if (ratRNG == 4) {
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 4;
        } else if (ratRNG == 5) {
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 4;
        } else if (ratRNG == 6) {
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 4;
        } else if (ratRNG == 7) {
            ratATK = rng.nextInt(10) + (10 * ratsKilled) / 4;
        } else if (ratRNG == 8) {
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 4;
        } else if (ratRNG == 9) {
            ratATK = rng.nextInt(10) + (11 * ratsKilled) / 4;
        } else if (ratRNG == 10) {
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 4;
        } else if (ratRNG == 11) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
        } else if (ratRNG == 12) {
            ratATK = rng.nextInt(10) + (-3 * ratsKilled) / 4;
            playerMP++;
        } else if (ratRNG == 13) {
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 4;
        } else if (ratRNG == 14) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
        } else if (ratRNG == 14) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
        } else
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 4;
    }

    // Used in combat to identify the type of rat. NOT INCLUDED WHEN RAT ENCOUNTER
    // MESSAGE.
    public String ratNames() {
        if (ratRNG == 1) {
            return "fat ";
        } else if (ratRNG == 2) {
            return "big ";
        } else if (ratRNG == 3) {
            return "smelly ";
        } else if (ratRNG == 4) {
            return "russian ";
        } else if (ratRNG == 5) {
            return "indian ";
        } else if (ratRNG == 6) {
            return "hairy ";
        } else if (ratRNG == 7) {
            return "Remy ";
        } else if (ratRNG == 8) {
            return "super ";
        } else if (ratRNG == 9) {
            return "space ";
        } else if (ratRNG == 10) {
            return "cannon ";
        } else if (ratRNG == 11) {
            return "cyborg ";
        } else if (ratRNG == 12) {
            return "canadian ";
        } else if (ratRNG == 13) {
            return "canadian (mad) ";
        } else if (ratRNG == 14) {
            return "boxer ";
        } else if (ratRNG == 14) {
            return "five toe'd ";
        } else {
            return "";
        }
    }

    // Handles what button the player clicks and the requirements to click them
    public class ChoiceHandler implements ActionListener {

        public void actionPerformed(final ActionEvent event) {

            final String yourChoice = event.getActionCommand();

            switch (yourChoice) {
            case "Attack":
                attackRat();
                break;
            case "Magic":
                if (playerMP >= 4) {
                    magicAttackRat();
                    break;
                } else if (playerMP < 4) {
                    break;
                }
            case "Heal":
                if (playerMP >= (2 + (Math.round(ratsKilled / 8)))) {
                    heal();
                    break;
                } else {
                    break;
                }
            case "Continue":
                attackRat();
                break;
            }
        }
    }
}
