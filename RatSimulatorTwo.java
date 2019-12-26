import java.util.Random;

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
    int playerATK, ratATK, playerGold, ratGold, ratRNG;

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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                attackButton.setBackground(Color.black);
                descriptionLabel.setText(
                        "( " + (7 + (2 * ratsKilled)) + " - " + 5 * ratsKilled + "  damage )  &  Restores 1 MP");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                magicButton.setBackground(Color.black);
                if (playerMP < 3) {
                    descriptionLabel.setText("You don't have enough mana!  You need atlesat 4 MP to cast Magic");
                } else {
                    descriptionLabel
                            .setText("( " + turn + " - " + (turn + (13 * ratsKilled)) + "  damage )  Requires: 4 MP");
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                healButton.setBackground(Color.black);
                if (playerMP < (2 + (Math.round(ratsKilled / 8)))) {
                    descriptionLabel.setText("You don't have enough mana!  You need atleast "
                            + (2 + (Math.round(ratsKilled / 8))) + " MP to cast Heal.");
                } else {
                    descriptionLabel.setText("( " + ((8 * ratsKilled) + 25) + " - " + ((13 * ratsKilled) + 25)
                            + "  HP )\n\n  Requires : " + (2 + (Math.round(ratsKilled / 8))) + " MP");
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
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

    public void attackRat() {

        if (ratHP <= 0) {

            ratHP = rng.nextInt(37) + 5 + (5 * ratsKilled);
            generateRat();

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

                playerHP = playerHP;

                updateStats();

                mainTextArea.setText("\nYou attack the rat for " + playerATK + " damage!\n\n\n\nThe rat (# "
                        + ratsKilled + ") remains with " + ratHP + " HP.\n\n\n\nThe rat retaliates with " + ratATK
                        + " damage!");
            } else {
                ratKilled();
                updateStats();

                mainTextArea.setText("\nYou attack the rat for " + playerATK + " damage!\n\n\n\nThe rat (# "
                        + ratsKilled + ") dies and drops " + ratGold + " gold!\n\n\nYour max HP increases by " + (25 + ratsKilled)
                        + "!\n\nYour max MP increases by 1!");
            }
        }
        while (playerHP < 0) {

            die();
            break;
        }
    }

    public void magicAttackRat() {

        if (ratHP <= 0) {

            ratHP = rng.nextInt(37) + 5 + (5 * ratsKilled);
            generateRat();

        }

        else if (ratHP > 0) {

            int ratATK = rng.nextInt((3) * ratsKilled) + (ratsKilled) + 2;
            int playerATK = rng.nextInt(11) * ratsKilled + (3 * ratsKilled) + turn;

            playerMP = playerMP - 4;
            turn++;
            ratHP = ratHP - playerATK;

            if (ratHP > 0) {

                playerHP = playerHP - ratATK;

                updateStats();

                mainTextArea.setText("\nYou conjure a spell against the rat for " + playerATK
                        + " damage!\n\n\n\nThe rat (# " + ratsKilled + ") remains with " + ratHP
                        + " HP.\n\n\n\nThe rat retaliates with " + ratATK + " damage!");
            } else {

                ratKilled();
                updateStats();

                mainTextArea.setText("\nYou conjuer a spell against the rat for " + playerATK
                        + " damage!\n\n\n\nThe rat (# " + ratsKilled + ") dies and drops " + ratGold + " gold!\n\n\nYour max HP increases by "
                        + (25 + ratsKilled) + "!\n\nYour max MP increases by 1!");
            }
        }
        while (playerHP < 0) {

            die();
            break;
        }
    }

    public void heal() {

        if (ratHP <= 0) {

            ratHP = rng.nextInt(37) + 5 + (5 * ratsKilled);
            generateRat();
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

            playerMP = playerMP - (2 + (Math.round(ratsKilled / 8)));
            turn++;
            playerHP = playerHP - ratATK;

            updateStats();

            mainTextArea.setText("\nYou heal yourself for " + playerHPHealed + " HP!\n\n\nThe rat (# " + ratsKilled
                    + ") remains with " + ratHP + " HP.\n\n\n\nThe rat retaliates with " + ratATK + " damage!");

        }

        while (playerHP < 0) {

            die();
            break;
        }

    }

    public void updateStats() {

        statsLabel.setText("HP: " + playerHP + " / " + playerMaxHP + "            MP: " + playerMP + " / "
                + playerMaxMP + "            G: " + playerGold
                + "                                                                                                                    Turn: "
                + turn);
    }

    public void die() {

        statsLabel.setText("You Have Fallen To The Rats");
        attackButtonPanel.setVisible(false);
        mainTextArea.setText("\nYou died.\n\n\nYou only manage to fend off a mere " + ratsKilled
                + " rats.\n\n\n\n\n\n\n\nHumanity succumbs to the order of the rats.");

    }

    public void ratKilled() {

        ratHP = 0;
        ratATK = 0;
        playerMaxHP = (playerMaxHP + 25) + ratsKilled * 2;
        playerMaxMP = playerMaxMP + 1;
        playerGold = playerGold + ratGold;
    }

    public void generateRat() {

        ratsKilled++;
        mainTextArea.setText("\nA " + ratRNG() +" rat appears with " + ratHP + " HP!\n\n\n\n\n\n\n\n\n\nWhat will you do?");
        ratGold = 1 + rng.nextInt(3) * ratsKilled / 7;

    }

/*    public String ratRNG() {
        ratRNG == rng.nextInt(3);
        if (ratRNG == 0){
            return "fat";
        }
        else if (ratRNG == 1){
            return "smelly";
        }
        else if (ratRNG == 2){
            return "remmy";
        }
    }
*/
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
