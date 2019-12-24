import java.util.Random;

import java.lang.Math;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.event.MouseEvent;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class RatSimulatorTwo {

    JFrame window;
    JTextArea mainTextArea;
    JPanel mainTextPanel, attackButtonPanel, statsPanel, infoPanel;
    JLabel playerHPLabel, playerMPLabel, ratsKilledLabel, turnLabel, infoLabel;
    Container con;
    JButton attackButton, magicButton, healButton;
    int playerATK, ratATK;

    int ratHP = 15;
    int playerHP = 100;
    int playerMaxHP = 100;
    int playerMP = 10;
    int playerMaxMP = 10;
    int ratsKilled = 1;
    int turn = 1;

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
        statsPanel.setBounds(76, 58, 630, 32);
        statsPanel.setBackground(Color.black);
        con.add(statsPanel);

        playerHPLabel = new JLabel();
        playerHPLabel.setForeground(Color.white);
        statsPanel.add(playerHPLabel);
        playerHPLabel.setText("HP: " + playerHP + " / " + playerMaxHP
                + "                                                                                                                              ");

        playerMPLabel = new JLabel();
        playerMPLabel.setForeground(Color.white);
        statsPanel.add(playerMPLabel);
        playerMPLabel.setText("MP: " + playerMP + " / " + playerMaxMP);

        infoPanel = new JPanel();
        infoPanel.setBounds(150, 430, 482, 69);
        infoPanel.setBackground(Color.black);
        con.add(infoPanel);

        infoLabel = new JLabel();
        infoLabel.setBounds(0, 0, 482, 69);
        infoLabel.setForeground(Color.white);
        infoPanel.add(infoLabel);

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
        attackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                attackButton.setBackground(Color.red);
                infoLabel.setText("( " + (7 + (2 * ratsKilled)) + " - " + 5 * ratsKilled + "  damage )");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                attackButton.setBackground(Color.black);
                infoLabel.setText("");
            }
        });

        magicButton = new JButton("Magic");
        magicButton.setBackground(Color.black);
        magicButton.setForeground(Color.white);
        attackButtonPanel.add(magicButton);
        magicButton.setFocusPainted(false);
        magicButton.addActionListener(choiceHandler);
        magicButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                magicButton.setBackground(Color.blue);
                if (playerMP < 3) {
                    infoLabel.setText("You don't have enough mana!");
                } else {
                    infoLabel.setText("( " + turn + " - " + (turn + (13 * ratsKilled)) + "  damage )  Requires: 4 MP");
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                magicButton.setBackground(Color.black);
                infoLabel.setText("");

            }
        });
        healButton = new JButton("Heal");
        healButton.setBackground(Color.black);
        healButton.setForeground(Color.white);
        attackButtonPanel.add(healButton);
        healButton.setFocusPainted(false);
        healButton.addActionListener(choiceHandler);
        healButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                healButton.setBackground(Color.green);
                if (playerMP < (1 + (Math.round(ratsKilled / 6)))) {
                    infoLabel.setText("You don't have enough mana!");
                } else {
                    infoLabel.setText("( " + ((8 * ratsKilled) + 25) + " - " + ((13 * ratsKilled) + 25)
                            + "  HP )\n\n  Requires : " + (2 + (Math.round(ratsKilled / 6))) + " MP");
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                healButton.setBackground(Color.black);
            }
        });

    }

    public void attackRat() {

        if (ratHP <= 0) {

            ratHP = rng.nextInt(37) + 5 + (5 * ratsKilled);
            playerMaxHP = (playerMaxHP + 25) + ratsKilled;
            playerMaxMP = playerMaxMP + 1;
            playerMP++;

            ratsKilled++;

            mainTextArea.setText("\nA new rat appears with " + ratHP + " HP!");
        }

        else if (ratHP > 0) {

            int ratATK = rng.nextInt((3) * ratsKilled) + (ratsKilled) + 2;
            int playerATK = rng.nextInt((3) + 2 * ratsKilled) + 7;

            if (playerMP < playerMaxMP) {
                playerMP++;
            }

            turn++;

            ratHP = ratHP - playerATK;

            if (ratHP > 0) {

                playerHP = playerHP - ratATK;

                playerHPLabel.setText("HP: " + playerHP + " / " + playerMaxHP
                        + "                                                                                                                              ");
                playerMPLabel.setText("MP: " + playerMP + " / " + playerMaxMP);

                mainTextArea.setText("\nYou attack the rat for " + playerATK + " damage!\n\nThe rat (#" + ratsKilled
                        + ") remains with " + ratHP + " HP.\n\n\n\nThe rat retaliates with " + ratATK
                        + " damage!\n\nYou have " + playerHP + "/" + playerMaxHP + " HP now.\n\n\n\nTurn:" + turn);
            }

            else {
                ratHP = 0;
                ratATK = 0;

                mainTextArea.setText("\nYou attack the rat for " + playerATK + " damage!\n\nThe rat (#" + ratsKilled
                        + ") dies.\n\n\n\n\n\n\n\n\n\nTurn:" + turn);
            }

        }

        while (playerHP < 0) {

            playerHPLabel.setText("You Have Fallen To The Rats");
            playerMPLabel.setText("");
            attackButtonPanel.setVisible(false);
            mainTextArea.setText("\nYou died.\n\n\nYou only manage to fend off a mere " + ratsKilled
                    + " rats.\n\n\n\n\n\n\n\nHumanity succumbs to the order of the rats.");
            break;
        }

    }

    public void magicAttackRat() {

        if (ratHP <= 0) {

            ratHP = rng.nextInt(37) + 5 + (5 * ratsKilled);
            playerMaxHP = (playerMaxHP + 25) + ratsKilled;
            playerMaxMP = playerMaxMP + 1;
            playerMP++;
            ratsKilled++;

            mainTextArea.setText("\nA new rat appears with " + ratHP + " HP!");
        }

        else if (ratHP > 0) {

            playerMP = playerMP - 4;

            int ratATK = rng.nextInt((3) * ratsKilled) + (ratsKilled) + 2;
            int playerATK = rng.nextInt(11) * ratsKilled + (3 * ratsKilled) + turn;

            turn++;
            ratHP = ratHP - playerATK;

            if (ratHP > 0) {

                playerHP = playerHP - ratATK;

                playerHPLabel.setText("HP: " + playerHP + " / " + playerMaxHP
                        + "                                                                                                                              ");
                playerMPLabel.setText("MP: " + playerMP + " / " + playerMaxMP);

                mainTextArea.setText("\nYou conjure a spell against the rat for " + playerATK + " damage!\n\nThe rat (#"
                        + ratsKilled + ") remains with " + ratHP + " HP.\n\n\n\nThe rat retaliates with " + ratATK
                        + " damage!\n\nYou have " + playerHP + "/" + playerMaxHP + " HP now.\n\n\n\nTurn:" + turn);
            }

            else {
                ratHP = 0;
                ratATK = 0;

                playerMPLabel.setText("MP: " + playerMP + " / " + playerMaxMP);

                mainTextArea.setText("\nYou conjuer a spell against the rat for " + playerATK + " damage!\n\nThe rat (#"
                        + ratsKilled + ") dies.\n\n\n\n\n\n\n\n\n\nTurn:" + turn);
            }

        }
        while (playerHP < 0) {

            playerHPLabel.setText("You Have Fallen To The Rats");
            playerMPLabel.setText("");
            attackButtonPanel.setVisible(false);
            mainTextArea.setText("\nYou died.\n\n\nYou only manage to fend off a mere " + ratsKilled
                    + " rats.\n\n\n\n\n\n\n\nHumanity succumbs to the order of the rats.");
            break;
        }

    }

    public void heal() {

        if (ratHP <= 0) {

            ratHP = rng.nextInt(37) + 5 + (5 * ratsKilled);
            playerMaxHP = (playerMaxHP + 25) + ratsKilled;
            playerMaxMP = playerMaxMP + 1;
            ratsKilled++;

            mainTextArea.setText("\nA new rat appears with " + ratHP + " HP!");
        }

        else if (ratHP > 0) {

            int ratATK = rng.nextInt((3) * ratsKilled) + (ratsKilled) + 2;
            int playerHPHealed = rng.nextInt(6) + 8 * ratsKilled + 25;

            if (playerHP < playerMaxHP) {

                playerHP = playerHP + (Math.round(playerHPHealed));

            }

            while (playerHP > playerMaxHP) {

                playerHP = playerMaxHP;

            }

            playerMP = playerMP - (2 + (Math.round(ratsKilled / 6)));

            playerATK = 0;

            turn++;

            ratHP = ratHP - playerATK;

            playerHP = playerHP - ratATK;

            playerHPLabel.setText("HP: " + playerHP + " / " + playerMaxHP
                    + "                                                                                                                              ");
            playerMPLabel.setText("MP: " + playerMP + " / " + playerMaxMP);

            mainTextArea.setText("\nYou heal yourself for " + playerHPHealed + " HP!\n\nThe rat (#" + ratsKilled
                    + ") remains with " + ratHP + " HP.\n\n\n\nThe rat retaliates with " + ratATK
                    + " damage!\n\nYou have " + playerHP + "/" + playerMaxHP + " HP now.\n\n\n\nTurn:" + turn);

        }

        while (playerHP < 0)

        {

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
                if (playerMP > 3) {
                    magicAttackRat();
                    break;
                } else if (playerMP < 4) {
                    break;
                }
            case "Heal":
                if (playerMP > (1 + (Math.round(ratsKilled / 6)))) {
                    heal();
                    break;
                }
            }

        }
    }
}