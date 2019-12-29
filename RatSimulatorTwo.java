import java.util.Random;
import java.util.ArrayList;

import java.lang.Math;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridLayout;

public class RatSimulatorTwo {

    JFrame window;
    JTextArea mainTextArea;
    JPanel mainTextPanel, attackButtonPanel, statsPanel, descriptionPanel;
    JLabel statsLabel, descriptionLabel;
    Container con;
    JButton attackButton, magicButton, healButton, backButton, shopButton, buyPotionButton, leaveButton, continueButton;
    int playerATK, ratATK, ratGold, shopRNG;

    // Battle data
    String playerWeapon = "Nothing";
    String playerWeaponVerbForButton = "Attack";
    String playerWeaponVerb = "attack";
    boolean magicKill;
    int ratHP = 15;
    int playerHP = 100;
    int playerMaxHP = 100;
    int playerMP = 10;
    int playerMaxMP = 10;
    int ratsKilled = 1;
    int turn = 1;
    int ratRNG = 0;
    int playerGold = 100;

    // Inventory data
    JButton inventoryButton, usePotionButton, usePistolButton, buyPistolButton;
    JPanel inventoryPanel, shopDescriptionPanel;
    JLabel shopDescriptionLabel;
    boolean inventoryOpen = false;
    boolean ownPistol;
    int potionCount = 0;
    int potionCost;
    int pistolCost;

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

        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(170, 460, 442, 50);
        inventoryPanel.setBackground(Color.black);
        con.add(inventoryPanel);

        descriptionPanel = new JPanel();
        descriptionPanel.setBounds(0, 420, 800, 69);
        descriptionPanel.setBackground(Color.black);
        con.add(descriptionPanel);

        descriptionLabel = new JLabel();
        descriptionLabel.setBounds(0, 0, 800, 69);
        descriptionLabel.setForeground(Color.white);
        descriptionPanel.add(descriptionLabel);
        // descriptionPanel.setLineWrap(true);

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
        attackButtonPanel.setBounds(237, 370, 310, 37);
        attackButtonPanel.setBackground(Color.black);
        con.add(attackButtonPanel);

        attackButton = new JButton(playerWeaponVerbForButton);
        attackButton.setBackground(Color.black);
        attackButton.setForeground(Color.white);
        attackButtonPanel.add(attackButton);
        attackButton.setFocusPainted(false);
        attackButton.addActionListener(choiceHandler);
        attackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                if (playerWeapon == "Nothing") {
                    descriptionLabel.setText("( " + 10 + " - " + 20 + "  damage )");
                } else if (playerWeapon == "Pistol") {
                    descriptionLabel.setText("( " + 60 + " - " + 100 + "  damage )");
                }
            }

            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                if (playerWeapon == "Nothing") {
                    descriptionLabel.setText("( " + 10 + " - " + 20 + "  damage )");
                } else if (playerWeapon == "Pistol") {
                    descriptionLabel.setText("( " + 60 + " - " + 100 + "  damage )");
                }
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        magicButton = new JButton("Magic");
        magicButton.setBackground(Color.black);
        magicButton.setForeground(Color.white);
        attackButtonPanel.add(magicButton);
        magicButton.setFocusPainted(false);
        magicButton.addActionListener(choiceHandler);
        magicButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                if (playerMP < 3) {
                    descriptionLabel.setText("You don't have enough mana!  You need atlesat 4 MP to cast Magic");
                } else {
                    descriptionLabel.setText("( " + turn / 2 + " - " + turn / 2 + "  damage )  Requires: 4 MP");
                }
            }

            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                if (playerMP < 3) {
                    descriptionLabel.setText("You don't have enough mana!  You need atlesat 4 MP to cast Magic");
                } else {
                    descriptionLabel.setText("( " + turn / 2 + " - " + turn / 2 + "  damage )  Requires: 4 MP");
                }
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });
        healButton = new JButton("Heal");
        healButton.setBackground(Color.black);
        healButton.setForeground(Color.white);
        attackButtonPanel.add(healButton);
        healButton.setFocusPainted(false);
        healButton.addActionListener(choiceHandler);
        healButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                if (playerMP < (2 + (Math.round(ratsKilled / 8)))) {
                    descriptionLabel.setText("You don't have enough mana!  You need atleast "
                            + (2 + (Math.round(ratsKilled / 8))) + " MP to cast Heal.");
                } else {
                    descriptionLabel.setText("( " + turn + " - " + turn + "  HP )\n\n  Requires : "
                            + (2 + (Math.round(ratsKilled / 8))) + " MP");
                }
            }

            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                if (playerMP < (2 + (Math.round(ratsKilled / 8)))) {
                    descriptionLabel.setText("You don't have enough mana!  You need atleast "
                            + (2 + (Math.round(ratsKilled / 8))) + " MP to cast Heal.");
                } else {
                    descriptionLabel.setText("( " + turn + " - " + turn + "  HP )\n\n  Requires : "
                            + (2 + (Math.round(ratsKilled / 8))) + " MP");
                }
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        shopButton = new JButton();
        shopButton.setBackground(Color.black);
        shopButton.setForeground(Color.white);
        shopButton.setText("Shop");
        shopButton.setFocusPainted(false);
        shopButton.addActionListener(choiceHandler);

        leaveButton = new JButton();
        leaveButton.setBackground(Color.black);
        leaveButton.setForeground(Color.white);
        leaveButton.setText("Leave");
        leaveButton.setFocusPainted(false);
        leaveButton.addActionListener(choiceHandler);

        continueButton = new JButton();
        continueButton.setBackground(Color.black);
        continueButton.setForeground(Color.white);
        continueButton.setText("Continue");
        continueButton.setFocusPainted(false);
        continueButton.addActionListener(choiceHandler);

        inventoryButton = new JButton();
        inventoryButton.setBackground(Color.black);
        inventoryButton.setForeground(Color.white);
        inventoryButton.setText("Inventory");
        inventoryButton.setFocusPainted(false);
        inventoryButton.addActionListener(choiceHandler);

        backButton = new JButton();
        backButton.setBackground(Color.black);
        backButton.setForeground(Color.white);
        backButton.setText("Back");
        backButton.setFocusPainted(false);
        backButton.addActionListener(choiceHandler);

        shopDescriptionPanel = new JPanel();
        shopDescriptionPanel.setBounds(0, 490, 800, 50);
        shopDescriptionPanel.setBackground(Color.black);

        shopDescriptionLabel = new JLabel();
        shopDescriptionLabel.setBounds(0, 0, 500, 50);
        shopDescriptionLabel.setBackground(Color.black);
        shopDescriptionLabel.setForeground(Color.white);

        buyPotionButton = new JButton();
        buyPotionButton.setBackground(Color.black);
        buyPotionButton.setForeground(Color.white);
        buyPotionButton.setText("Potion - " + potionCost + "G");
        buyPotionButton.setFocusPainted(false);
        buyPotionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                buyPotion();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("A weird shaped flask with an opaque yellow fluid in it.");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("");
            }
        });

        usePotionButton = new JButton();
        usePotionButton.setBackground(Color.black);
        usePotionButton.setForeground(Color.white);
        usePotionButton.setText("Potion (x " + potionCount + ")");
        usePotionButton.setFocusPainted(false);
        usePotionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                usePotion();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("A yellow rat-shaped potion you bought from a rat.");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        buyPistolButton = new JButton();
        buyPistolButton.setBackground(Color.black);
        buyPistolButton.setForeground(Color.white);
        buyPistolButton.setText("Pistol");
        buyPistolButton.setFocusPainted(false);
        buyPistolButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                buyPistol();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("A pistol with the engraving \"Polar Star\".");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("");
            }
        });

        usePistolButton = new JButton();
        usePistolButton.setBackground(Color.black);
        usePistolButton.setForeground(Color.white);
        usePistolButton.setText("Pistol");
        usePistolButton.setFocusPainted(false);
        usePistolButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                usePistol();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                if (playerWeapon == "Pistol") {
                    descriptionLabel.setText(
                            "(EQUIPPED) Upon further inspection, you notice another engraving \"Mrs. Wagner\".");
                } else {
                    descriptionLabel.setText("A pistol.");
                }
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        attackButtonPanel.add(continueButton);
        continueButton.setVisible(false);
        attackButtonPanel.add(inventoryButton);
        inventoryButton.setVisible(true);
        attackButtonPanel.add(backButton);
        backButton.setVisible(false);

    }

    // If attack button pressed, calculates and displays what happens

    public void attackRat() {

        closeInventory();

        // If rat dead, encounter new rat
        if (ratHP <= 0) {
            generateRat();
        }

        // If rat alive, generate playerATK and ratATK and exchange damage
        else if (ratHP > 0) {
            generateRatATK();
            if (playerWeapon == "Nothing") {
                playerATK = 10 * (rng.nextInt(2) + 1);
                playerMP++;
            } else if (playerWeapon == "Pistol") {
                playerATK = 20 * (rng.nextInt(3) + 3);
            }

            // Increase turn count by 1, and calculate damage done to rat
            turn++;
            ratHP = ratHP - playerATK;

            // If rat isnt killed by attack, the rat then attacks. HP, MP, G, Turns are
            // updated. Battle message is displayed
            if (ratHP > 0) {

                playerHP = playerHP;
                updateStats();

                mainTextArea.setText("\nYou " + playerWeaponVerb + " the " + ratNames() + "rat for " + playerATK
                        + " damage!\n\n\n\nThe " + ratNames() + "rat (# " + ratsKilled + ") remains with " + ratHP
                        + " HP.\n\n\n\nThe rat retaliates with " + ratATK + " damage!");
            }

            // Else if Rat is killed by players attack, increase player's max stats, update
            // HP, MP, G, turn values, and display rat killed message
            else {
                magicKill = false;
                ratKilled();
                updateStats();
            }
        }

        // If rat isnt killed by player attack, and player's HP goes below 0 after
        // exchanging damage, player dies. Displays death screen message
        while (playerHP <= 0) {
            die();
            break;
        }
    }

    // If magic button pressed, calculates and displays what happens
    public void magicAttackRat() {

        closeInventory();

        // If rat dead, encounter new rat
        if (ratHP <= 0) {
            generateRat();
        }

        // After checking if player has enough mana to cast magic attack and rat is
        // alive, generate playerATK and ratATK, subtract 4 MP, increase turn, and
        // exchange damage
        else if (ratHP > 0) {

            generateRatATK();
            playerATK = rng.nextInt(1) + turn / 2;

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
                magicKill = true;
                ratKilled();
                updateStats();
            }
        }

        // If rat isnt killed by player attack, and player's HP goes below 0 after
        // exchanging damage, player dies. Displays death screen message
        while (playerHP <= 0) {

            die();
            break;
        }
    }

    // If magic button pressed, calculates and displays what happens
    public void heal() {

        closeInventory();

        // If rat dead, encounter new rat
        if (ratHP <= 0) {
            generateRat();
        }

        // If rat alive, calculate rat ATK, how much player with heal
        else if (ratHP > 0) {

            generateRatATK();
            int playerHPHealed = rng.nextInt(1) + turn + 25;

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
        while (playerHP <= 0) {
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

    public void openInventory() {
        inventoryOpen = true;
        inventoryPanel.setVisible(true);
        if ((potionCount == 0) && (ownPistol == false)) {
            descriptionLabel.setText("You have nothing in your inventory...");
        }
        if (potionCount != 0) {
            usePotionButton.setText("Potion (x " + potionCount + ")");
            usePotionButton.setVisible(true);
        } else {
            usePotionButton.setVisible(false);
        }
        if (ownPistol == true) {
            usePistolButton.setVisible(true);
        }
    }

    public void closeInventory() {
        inventoryOpen = false;
        inventoryPanel.setVisible(false);
        descriptionLabel.setText("");
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
        playerMaxHP = (playerMaxHP + 15) + ratsKilled / 2;
        playerHP = (playerHP + 10) + ratsKilled / 4;
        playerMaxMP = playerMaxMP + 1;
        playerMP = playerMP + 1;
        if (playerHP > playerMaxHP) {
            playerHP = playerMaxHP;
        }
        if (playerMP > playerMaxMP) {
            playerMP = playerMaxMP;
        }
        playerGold = playerGold + ratGold;
        killedRatContinue();

    }

    // Generates what rat will appear, display message
    public void generateRat() {

        ratRNG = (2 * ratsKilled) + (rng.nextInt(31));

        mainTextArea.setText("\nA " + rats() + "rat appears with " + ratHP + " HP!\n\n\n\n\n\n\n\n\nWhat will you do?");
        ratGold = 1 + rng.nextInt(3) * ratsKilled / 7;

    }

    // Setting up shop interface
    public void spawnShop() {

        mainTextArea.setText(
                "\nYou find a rat vendor.\n\n\nSqueek Squeek Squeek Squeek\n(Care to examine the stock upheld within my emporium, human?)\n\n\n\n\n\nWhat will you do?");
        attackButtonPanel.add(shopButton);
        attackButtonPanel.add(leaveButton);
        shopButton.setVisible(true);
        leaveButton.setVisible(true);
        continueButton.setVisible(false);
        attackButton.setVisible(false);
        magicButton.setVisible(false);
        healButton.setVisible(false);
        inventoryButton.setVisible(false);
        inventoryPanel.setVisible(false);
        inventoryOpen = false;

        potionCost = 5;
        potionCost = rng.nextInt(4) + (potionCost + (ratsKilled / 5)) - 3;

        pistolCost = 30;
        pistolCost = rng.nextInt(10) + pistolCost - (5 + (ratsKilled / 5));

    }

    public void generateShop() {

        mainTextArea.setText("\nWhat would you like, human?");

        con.add(shopDescriptionPanel);
        shopDescriptionPanel.add(shopDescriptionLabel);
        shopDescriptionPanel.setVisible(true);
        shopDescriptionLabel.setVisible(true);
        descriptionPanel.add(buyPotionButton);
        descriptionPanel.add(buyPistolButton);
        buyPotionButton.setText("Potion - " + potionCost + "G");
        buyPotionButton.setVisible(true);
        buyPistolButton.setText("Pistol - " + pistolCost + "G");
        buyPistolButton.setVisible(true);
        shopButton.setVisible(false);

    }

    public void buyPistol() {

        if (playerGold >= pistolCost) {
            if (ownPistol == false) {
                inventoryPanel.add(usePistolButton);
                usePistolButton.setVisible(false);
                playerGold = playerGold - pistolCost;
                ownPistol = true;
                mainTextArea.setText("\nSqueek Squeek Squeek.\n(Aye, a fine pistol. Enjoy the transaction, human.)");
                updateStats();
            } else {
                mainTextArea.setText("\nSqueek Squeek\n(All out of stock, human.)");
            }
        } else if (playerGold == 0) {
            mainTextArea.setText(
                    "\nSqueek Squeek.\n(How the fuck do you not have a single piece of gold, you worthless fucking shit.)");
        } else if (playerGold < pistolCost) {
            mainTextArea.setText("\nSqueek\nPoor human. Pitiful.");

        } else {
            leaveShop();
        }
    }

    public void usePistol() {

        if (playerWeapon != "Pistol") {

            playerWeapon = "Pistol";
            playerWeaponVerbForButton = "Shoot";
            playerWeaponVerb = "shoot";
            attackButton.setText(playerWeaponVerbForButton);
            usePistolButton.setText("Equipped: Pistol");
            descriptionLabel
                    .setText("(EQUIPPED) Upon further inspection, you notice another engraving \"Mrs. Wagner\".");
        } else {
            playerWeapon = "Nothing";
            playerWeaponVerbForButton = "Attack";
            playerWeaponVerb = "attack";
            attackButton.setText(playerWeaponVerbForButton);
            usePistolButton.setText("Pistol");
            descriptionLabel.setText("A pistol.");
        }
    }

    public void buyPotion() {

        if (playerGold >= potionCost) {
            inventoryPanel.add(usePotionButton);
            usePotionButton.setVisible(false);
            playerGold = playerGold - potionCost;
            potionCount = potionCount + 1;
            mainTextArea.setText("\nSqueek Squeek Squeek.\n(Enjoy the transaction, human.)\n\n\nYou now have "
                    + potionCount + " potions");
            updateStats();
        } else if (playerGold == 0) {
            mainTextArea.setText(
                    "\nSqueek Squeek.\n(How the fuck do you not have a single piece of gold, you worthless fucking shit.)");
        } else if (playerGold < potionCost) {
            mainTextArea.setText("\nPoor human. Pitiful.");

        } else {
            leaveShop();
        }
    }

    public void usePotion() {
        if (potionCount >= 1) {
            potionCount = potionCount - 1;
            playerHP = playerHP + 50;
            playerMP = playerMP + 5;
            if (playerHP > playerMaxHP) {
                playerHP = playerMaxHP;
            }
            if (playerMP > playerMaxMP) {
                playerMP = playerMaxMP;
            }
            usePotionButton.setText("Potion (x " + potionCount + ")");
            updateStats();
            descriptionLabel.setText(
                    "\nYou drink a rat's strange yellow concotion and feel tingly... " + "You restore 50 HP and 5 MP!");
        } else {
            descriptionLabel.setText("You don't have any potions");
        }
    }

    // Leaving the shop
    public void leaveShop() {

        mainTextArea.setText("\nYou leave.");

        shopDescriptionPanel.setVisible(false);
        shopDescriptionLabel.setVisible(false);

        shopButton.setVisible(false);
        leaveButton.setVisible(false);
        inventoryButton.setVisible(false);
        continueButton.setVisible(true);
        buyPotionButton.setVisible(false);
        buyPistolButton.setVisible(false);
        shopRNG = 1;

    }

    // When you kill a rat and continue: the rats death display message
    public void killedRatContinue() {

        if (magicKill == true) {
            mainTextArea.setText("\nYou conjure a spell against the " + ratNames() + "rat for " + playerATK
                    + " damage!\n\n\n\nThe rat (# " + ratsKilled + ") dies and drops " + ratGold
                    + " gold.\n\n\nYour max HP increases by " + (15 + (ratsKilled / 2))
                    + "!\n\nYour max MP increases by 1!");
        } else if (magicKill == false) {
            mainTextArea.setText("\nYou " + playerWeaponVerb + " the " + ratNames() + "rat for " + playerATK
                    + " damage!\n\n\n\nThe rat (# " + ratsKilled + ") dies and drops " + ratGold
                    + " gold.\n\n\nYour max HP increases by " + (15 + (ratsKilled / 2))
                    + "!\n\nYour max MP increases by 1!");
        } else {
            mainTextArea.setText("you killed the rat");
        }

        ratsKilled++;

        attackButton.setVisible(false);
        magicButton.setVisible(false);
        healButton.setVisible(false);
        inventoryButton.setVisible(false);
        continueButton.setVisible(true);

        shopRNG = rng.nextInt(24);

    }

    // When you leave the shop and go kill more rats
    public void continueRatsShop() {

        attackButton.setVisible(true);
        magicButton.setVisible(true);
        healButton.setVisible(true);
        inventoryButton.setVisible(true);
        continueButton.setVisible(false);

        generateRat();
    }

    // Used to identify the type of rat you encounter and calculate their HP, USE IN
    // RAT ENCOUNTER MESSAGE
    public String rats() {
        if ((ratRNG >= 5) && (ratRNG < 10)) {
            // HP 1, ATK 1 == 2
            ratHP = rng.nextInt(10) + (1 * ratsKilled);
            //ratATK = rng.nextInt(10) + (1 * ratsKilled) / 3;
            return "baby ";
        } else if ((ratRNG >= 10) && (ratRNG < 15)) {
            // HP 2, ATK 2 == 4
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            //ratATK = rng.nextInt(10) + (2 * ratsKilled) / 3;
            return "weak ";
        } else if ((ratRNG >= 15) && (ratRNG < 20)) {
            // HP 2, ATK 3 == 5
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            //ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
            return "small ";
        } else if ((ratRNG >= 20) && (ratRNG < 25)) {
            // HP 3, ATK 3 == 6
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            //ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
            return "squeeking ";
        } else if ((ratRNG >= 25) && (ratRNG < 30)) {
            // HP 2, ATK 4 == 6
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            //ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
            return "hungry ";
        } else if ((ratRNG >= 30) && (ratRNG < 35)) {
            // HP 2, ATK 5 == 7
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            //ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "homeless ";
        } else if ((ratRNG >= 35) && (ratRNG < 40)) {
            // HP 2, ATK 6 == 8
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            //ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "smelly ";
        } else if ((ratRNG >= 40) && (ratRNG < 45)) {
            // HP 5, ATK 3 == 8
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            //ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
            return "hairy ";
        } else if ((ratRNG >= 45) && (ratRNG < 50)) {
            // HP 5, ATK 4 == 8
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            //ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
            return "big ";
        } else if ((ratRNG >= 50) && (ratRNG < 55)) {
            // HP 4, ATK 5 == 9
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            //ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "yellow ";
        } else if ((ratRNG >= 55) && (ratRNG < 60)) {
            // HP 3, ATK 6 == 9
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            //ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "flying ";
        } else if ((ratRNG >= 60) && (ratRNG < 65)) {
            // HP 4, ATK 5 == 9
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            //ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "boxer ";
        } else if ((ratRNG >= 65) && (ratRNG < 70)) {
            // HP 4, ATK -4 == 0
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            //ratATK = rng.nextInt(10) + (-4 * ratsKilled) / 3;
            return "canadian ";
        } else if ((ratRNG >= 70) && (ratRNG < 75)) {
            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            //ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "lab ";
        } else if ((ratRNG >= 75) && (ratRNG < 80)) {
            // HP 1, ATK 9 == 10
            ratHP = rng.nextInt(10) + (1 * ratsKilled);
            //ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "cannon ";
        } else if ((ratRNG >= 80) && (ratRNG < 85)) {
            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            //ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "cyborg ";
        } else if ((ratRNG >= 85) && (ratRNG < 95)) {
            // HP 4, ATK 6 == 10
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            //ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "computer ";
        } else if ((ratRNG >= 90) && (ratRNG < 100)) {
            // HP 6, ATK 4 == 10
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            //ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
            return "robo ";
        } else if ((ratRNG >= 95) && (ratRNG < 105)) {
            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            //ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "five toe'd ";
        } else if ((ratRNG >= 100) && (ratRNG < 110)) {
            // HP 4, ATK 6 == 10
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            //ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "indian ";
        } else if ((ratRNG >= 105) && (ratRNG < 110)) {
            // HP 6, ATK 5 == 11
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            //ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "sushi ";
        } else if ((ratRNG >= 110) && (ratRNG < 115)) {
            // HP 3, ATK 8 == 11
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            //ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "canadian (mad) ";
        } else if ((ratRNG >= 115) && (ratRNG < 120)) {
            // HP 3, ATK 8 == 11
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            //ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "karate ";
        } else if ((ratRNG >= 120) && (ratRNG < 125)) {
            // HP 11, ATK 6 == 11 + (6/2)
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            //ratATK = rng.nextInt(2) * (6 * ratsKilled) / 3;
            return "wood ";
        } else if ((ratRNG >= 125) && (ratRNG < 130)) {
            // HP 5, ATK 7 == 12
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            //ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "road ";
        } else if ((ratRNG >= 130) && (ratRNG < 135)) {
            // HP 6, ATK 6 == 12
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            //ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "mullet ";
        } else if ((ratRNG >= 135) && (ratRNG < 140)) {
            // HP 5, ATK 7 == 12
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            //ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "chinese ";
        } else if ((ratRNG >= 140) && (ratRNG < 145)) {
            // HP 2, ATK 10 == 12
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            //ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
            return "glass ";
        } else if ((ratRNG >= 145) && (ratRNG < 150)) {
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            //ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "DK ";
        } else if ((ratRNG >= 150) && (ratRNG < 155)) {
            // HP ?, ATK ? == ?
            ratHP = rng.nextInt((19) - 6) * (2 * ratsKilled);
            //ratATK = rng.nextInt((19) - 6) * (2 * ratsKilled);
            return "random ";
        } else if ((ratRNG >= 155) && (ratRNG < 160)) {
            // HP 7, ATK 5 == 12
            ratHP = rng.nextInt(10) + (7 * ratsKilled);
            //ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "ocean ";
        } else if ((ratRNG >= 160) && (ratRNG < 165)) {
            // HP 4, ATK 8 == 12
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            //ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "poison ";
        } else if ((ratRNG >= 165) && (ratRNG < 170)) {
            // HP 5, ATK 7 == 12
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            //ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "gym ";
        } else if ((ratRNG >= 170) && (ratRNG < 175)) {
            // HP 10, ATK 2 == 12
            ratHP = rng.nextInt(10) + (10 * ratsKilled);
            //ratATK = rng.nextInt(10) + (2 * ratsKilled) / 3;
            return "cross country ";
        } else if ((ratRNG >= 175) && (ratRNG < 180)) {
            // HP 8, ATK 4 == 12
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            //ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
            return "ice ";
        } else if ((ratRNG >= 180) && (ratRNG < 185)) {
            // HP 4, ATK 8 == 12
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            //ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "fire ";
        } else if ((ratRNG >= 185) && (ratRNG < 190)) {
            // HP 2, ATK 11 == 13
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            //ratATK = rng.nextInt(10) + (11 * ratsKilled) / 3;
            return "space ";
        } else if ((ratRNG >= 190) && (ratRNG < 195)) {
            // HP 2, ATK 1 == 3
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            //ratATK = rng.nextInt(10) + (1 * ratsKilled) / 3;
            return "xander ";
        } else if ((ratRNG >= 195) && (ratRNG < 200)) {
            // HP 6, ATK 7 == 13
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            //ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "russian ";
        } else if ((ratRNG >= 200) && (ratRNG < 205)) {
            // HP 7, ATK 6 == 13
            ratHP = rng.nextInt(10) + (7 * ratsKilled);
            //ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
            return "music ";
        } else if ((ratRNG >= 205) && (ratRNG < 210)) {
            // HP 8, ATK 5 == 13
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            //ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "iron ";
        } else if ((ratRNG >= 210) && (ratRNG < 215)) {
            // HP 5, ATK 8 == 13
            ratHP = rng.nextInt(10) + (5 * ratsKilled);
            //ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "punk ";
        } else if ((ratRNG >= 215) && (ratRNG < 220)) {
            // HP 7, ATK 7 == 14
            ratHP = rng.nextInt(10) + (7 * ratsKilled);
            //ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "screaming ";
        } else if ((ratRNG >= 220) && (ratRNG < 225)) {
            // HP 15, ATK -1 == 14
            ratHP = rng.nextInt(10) + (15 * ratsKilled);
            //ratATK = rng.nextInt(10) + (-1 * ratsKilled) / 3;
            return "chair ";
        } else if ((ratRNG >= 225) && (ratRNG < 230)) {
            // HP 6, ATK 8 == 14
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            //ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "officer ";
        } else if ((ratRNG >= 230) && (ratRNG < 235)) {
            // HP 4, ATK 10 == 14
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            //ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
            return "plauge ";
        } else if ((ratRNG >= 235) && (ratRNG < 240)) {
            // HP 12, ATK 2 == 14
            ratHP = rng.nextInt(10) + (12 * ratsKilled);
            //ratATK = rng.nextInt(10) + (2 * ratsKilled) / 3;
            return "fat ";
        } else if ((ratRNG >= 240) && (ratRNG < 245)) {
            // HP 7, ATK 7 == 14
            ratHP = rng.nextInt(10) + (7 * ratsKilled);
            //ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "sir ";
        } else if ((ratRNG >= 245) && (ratRNG < 250)) {
            // HP 8, ATK 7 == 15
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            //ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
            return "bad ";
        } else if ((ratRNG >= 250) && (ratRNG < 255)) {
            // HP 6, ATK 9 == 15
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            //ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "business ";
        } else if ((ratRNG >= 255) && (ratRNG < 260)) {
            // HP 6, ATK 9 == 15
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            //ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "man ";
        } else if ((ratRNG >= 260) && (ratRNG < 265)) {
            // HP 8, ATK 8 == 16
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            //ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "super ";
        } else if ((ratRNG >= 265) && (ratRNG < 270)) {
            // HP 8, ATK 8 == 16
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            //ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "philadelphia ";
        } else if ((ratRNG >= 270) && (ratRNG < 275)) {
            // HP 12, ATK 5 == 17
            ratHP = rng.nextInt(10) + (12 * ratsKilled);
            //ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
            return "whale ";
        } else if ((ratRNG >= 275) && (ratRNG < 280)) {
            // HP 8, ATK 8 == 16
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            //ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "royal ";
        } else if ((ratRNG >= 280) && (ratRNG < 285)) {
            // HP 3, ATK 13 == 16
            ratHP = rng.nextInt(10) + (3 * ratsKilled);
            //ratATK = rng.nextInt(10) + (13 * ratsKilled) / 3;
            return "radioactive ";
        } else if ((ratRNG >= 285) && (ratRNG < 290)) {
            // HP 16, ATK 0 == 16
            ratHP = rng.nextInt(10) + (16 * ratsKilled);
            //ratATK = rng.nextInt(10) + (0 * ratsKilled) / 3;
            return "statue ";
        } else if ((ratRNG >= 290) && (ratRNG < 295)) {
            // HP -16, ATK 0 == -16
            ratHP = rng.nextInt(10) + (-16 * ratsKilled);
            //ratATK = rng.nextInt(10) + (0 * ratsKilled) / 3;
            return "dead ";
        } else if ((ratRNG >= 295) && (ratRNG < 300)) {
            // HP 6, ATK 10 == 16
            ratHP = rng.nextInt(10) + (6 * ratsKilled);
            //ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
            return "candy ";
        } else if ((ratRNG >= 300) && (ratRNG < 305)) {
            // HP 8, ATK 9 == 17
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            //ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "regal ";
        } else if ((ratRNG >= 305) && (ratRNG < 310)) {
            // HP 2, ATK 15 == 17
            ratHP = rng.nextInt(10) + (2 * ratsKilled);
            //ratATK = rng.nextInt(10) + (17 * ratsKilled) / 3;
            return "kamikaze ";
        } else if ((ratRNG >= 310) && (ratRNG < 315)) {
            // HP 9, ATK 8 == 17
            ratHP = rng.nextInt(10) + (9 * ratsKilled);
            //ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
            return "Emeile ";
        } else if ((ratRNG >= 315) && (ratRNG < 320)) {
            // HP 9, ATK 9 == 18
            ratHP = rng.nextInt(10) + (9 * ratsKilled);
            //ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
            return "math ";
        } else if ((ratRNG >= 320) && (ratRNG < 325)) {
            // HP 8, ATK 11 == 19
            ratHP = rng.nextInt(10) + (8 * ratsKilled);
            //ratATK = rng.nextInt(10) + (11 * ratsKilled) / 3;
            return "gorilla ";
        } else if ((ratRNG >= 325) && (ratRNG < 330)) {
            // HP 10, ATK 10 == 20
            ratHP = rng.nextInt(10) + (10 * ratsKilled);
            //ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
            return "Remy ";
        } else if ((ratRNG >= 330) && (ratRNG < 335)) {
            // HP 15, ATK 15 == 30
            ratHP = rng.nextInt(10) + (15 * ratsKilled);
            //ratATK = rng.nextInt(10) + (15 * ratsKilled) / 3;
            return "Emporer ";
        } else if ((ratRNG >= 335) && (ratRNG < 340)) {
            // HP 200, ATK 20 == 220
            ratHP = rng.nextInt(10) + (200 * ratsKilled);
            //ratATK = rng.nextInt(10) + (20 * ratsKilled) / 3;
            return "the last ";
        } else {// HP 3, ATK 2 == 5
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            //ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
            return "";
        }
    }

    // Used every turn in combat to randomly generate the rat's ATK
    public void generateRatATK() {
        if ((ratRNG >= 5) && (ratRNG < 10)) {
            ratATK = rng.nextInt(10) + (1 * ratsKilled) / 3;
        } else if ((ratRNG >= 10) && (ratRNG < 15)) {
            ratATK = rng.nextInt(10) + (2 * ratsKilled) / 3;
        } else if ((ratRNG >= 15) && (ratRNG < 20)) {
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
        } else if ((ratRNG >= 20) && (ratRNG < 25)) {
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
        } else if ((ratRNG >= 25) && (ratRNG < 30)) {
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
        } else if ((ratRNG >= 30) && (ratRNG < 35)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
        } else if ((ratRNG >= 35) && (ratRNG < 40)) {
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
        } else if ((ratRNG >= 40) && (ratRNG < 45)) {
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
        } else if ((ratRNG >= 45) && (ratRNG < 50)) {
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
        } else if ((ratRNG >= 50) && (ratRNG < 55)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
        } else if ((ratRNG >= 55) && (ratRNG < 60)) {
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
        } else if ((ratRNG >= 60) && (ratRNG < 65)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
        } else if ((ratRNG >= 65) && (ratRNG < 70)) {
            ratATK = rng.nextInt(10) + (-4 * ratsKilled) / 3;
        } else if ((ratRNG >= 70) && (ratRNG < 75)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
        } else if ((ratRNG >= 75) && (ratRNG < 80)) {
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
        } else if ((ratRNG >= 80) && (ratRNG < 85)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
        } else if ((ratRNG >= 85) && (ratRNG < 95)) {
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
        } else if ((ratRNG >= 90) && (ratRNG < 100)) {
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
        } else if ((ratRNG >= 95) && (ratRNG < 105)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
        } else if ((ratRNG >= 100) && (ratRNG < 110)) {
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
        } else if ((ratRNG >= 105) && (ratRNG < 110)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
        } else if ((ratRNG >= 110) && (ratRNG < 115)) {
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
        } else if ((ratRNG >= 115) && (ratRNG < 120)) {
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
        } else if ((ratRNG >= 120) && (ratRNG < 125)) {
            ratATK = rng.nextInt(2) * (6 * ratsKilled) / 3;
        } else if ((ratRNG >= 125) && (ratRNG < 130)) {
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
        } else if ((ratRNG >= 130) && (ratRNG < 135)) {
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
        } else if ((ratRNG >= 135) && (ratRNG < 140)) {
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
        } else if ((ratRNG >= 140) && (ratRNG < 145)) {
            ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
        } else if ((ratRNG >= 145) && (ratRNG < 150)) {
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
        } else if ((ratRNG >= 150) && (ratRNG < 155)) {
            ratATK = rng.nextInt((11) - 6) * (2 * ratsKilled);
        } else if ((ratRNG >= 155) && (ratRNG < 160)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
        } else if ((ratRNG >= 160) && (ratRNG < 165)) {
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
        } else if ((ratRNG >= 165) && (ratRNG < 170)) {
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
        } else if ((ratRNG >= 170) && (ratRNG < 175)) {
            ratATK = rng.nextInt(10) + (2 * ratsKilled) / 3;
        } else if ((ratRNG >= 175) && (ratRNG < 180)) {
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 3;
        } else if ((ratRNG >= 180) && (ratRNG < 185)) {
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
        } else if ((ratRNG >= 185) && (ratRNG < 190)) {
            ratATK = rng.nextInt(10) + (11 * ratsKilled) / 3;
        } else if ((ratRNG >= 190) && (ratRNG < 195)) {
            ratATK = rng.nextInt(10) + (1 * ratsKilled) / 3;
        } else if ((ratRNG >= 195) && (ratRNG < 200)) {
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
        } else if ((ratRNG >= 200) && (ratRNG < 205)) {
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 3;
        } else if ((ratRNG >= 205) && (ratRNG < 210)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
        } else if ((ratRNG >= 210) && (ratRNG < 215)) {
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
        } else if ((ratRNG >= 215) && (ratRNG < 220)) {
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
        } else if ((ratRNG >= 220) && (ratRNG < 225)) {
            ratATK = rng.nextInt(10) + (-1 * ratsKilled) / 3;
        } else if ((ratRNG >= 225) && (ratRNG < 230)) {
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
        } else if ((ratRNG >= 230) && (ratRNG < 235)) {
            ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
        } else if ((ratRNG >= 235) && (ratRNG < 240)) {
            ratATK = rng.nextInt(10) + (2 * ratsKilled) / 3;
        } else if ((ratRNG >= 240) && (ratRNG < 245)) {
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
        } else if ((ratRNG >= 245) && (ratRNG < 250)) {
            ratATK = rng.nextInt(10) + (7 * ratsKilled) / 3;
        } else if ((ratRNG >= 250) && (ratRNG < 255)) {
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
        } else if ((ratRNG >= 255) && (ratRNG < 260)) {
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
        } else if ((ratRNG >= 260) && (ratRNG < 265)) {
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
        } else if ((ratRNG >= 265) && (ratRNG < 270)) {
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
        } else if ((ratRNG >= 270) && (ratRNG < 275)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 3;
        } else if ((ratRNG >= 275) && (ratRNG < 280)) {
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
        } else if ((ratRNG >= 280) && (ratRNG < 285)) {
            ratATK = rng.nextInt(10) + (13 * ratsKilled) / 3;
        } else if ((ratRNG >= 285) && (ratRNG < 290)) {
            ratATK = rng.nextInt(10) + (0 * ratsKilled) / 3;
        } else if ((ratRNG >= 290) && (ratRNG < 295)) {
            ratATK = rng.nextInt(10) + (0 * ratsKilled) / 3;
        } else if ((ratRNG >= 295) && (ratRNG < 300)) {
            ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
        } else if ((ratRNG >= 300) && (ratRNG < 305)) {
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
        } else if ((ratRNG >= 305) && (ratRNG < 310)) {
            ratATK = rng.nextInt(10) + (17 * ratsKilled) / 3;
        } else if ((ratRNG >= 310) && (ratRNG < 315)) {
            ratATK = rng.nextInt(10) + (8 * ratsKilled) / 3;
        } else if ((ratRNG >= 315) && (ratRNG < 320)) {
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 3;
        } else if ((ratRNG >= 320) && (ratRNG < 325)) {
            ratATK = rng.nextInt(10) + (11 * ratsKilled) / 3;
        } else if ((ratRNG >= 325) && (ratRNG < 330)) {
            ratATK = rng.nextInt(10) + (10 * ratsKilled) / 3;
        } else if ((ratRNG >= 330) && (ratRNG < 335)) {
            ratATK = rng.nextInt(10) + (15 * ratsKilled) / 3;
        } else if ((ratRNG >= 335) && (ratRNG < 340)) {
            ratATK = rng.nextInt(10) + (20 * ratsKilled) / 3;
        } else {
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 3;
        }
    }

    // Used in combat to identify the type of rat. NOT INCLUDED WHEN RAT ENCOUNTER
    // MESSAGE.
    public String ratNames() {
        if ((ratRNG >= 5) && (ratRNG < 10)) {
            return "baby ";
        } else if ((ratRNG >= 10) && (ratRNG < 15)) {
            return "weak ";
        } else if ((ratRNG >= 15) && (ratRNG < 20)) {
            return "small ";
        } else if ((ratRNG >= 20) && (ratRNG < 25)) {
            return "squeeking ";
        } else if ((ratRNG >= 25) && (ratRNG < 30)) {
            return "hungry ";
        } else if ((ratRNG >= 30) && (ratRNG < 35)) {
            return "homeless ";
        } else if ((ratRNG >= 35) && (ratRNG < 40)) {
            return "smelly ";
        } else if ((ratRNG >= 40) && (ratRNG < 45)) {
            return "hairy ";
        } else if ((ratRNG >= 45) && (ratRNG < 50)) {
            return "big ";
        } else if ((ratRNG >= 50) && (ratRNG < 55)) {
            return "yellow ";
        } else if ((ratRNG >= 55) && (ratRNG < 60)) {
            return "flying ";
        } else if ((ratRNG >= 60) && (ratRNG < 65)) {
            return "boxer ";
        } else if ((ratRNG >= 65) && (ratRNG < 70)) {
            return "canadian ";
        } else if ((ratRNG >= 70) && (ratRNG < 75)) {
            return "lab ";
        } else if ((ratRNG >= 75) && (ratRNG < 80)) {
            return "cannon ";
        } else if ((ratRNG >= 80) && (ratRNG < 85)) {
            return "cyborg ";
        } else if ((ratRNG >= 85) && (ratRNG < 95)) {
            return "computer ";
        } else if ((ratRNG >= 90) && (ratRNG < 100)) {
            return "robo ";
        } else if ((ratRNG >= 95) && (ratRNG < 105)) {
            return "five toe'd ";
        } else if ((ratRNG >= 100) && (ratRNG < 110)) {
            return "indian ";
        } else if ((ratRNG >= 105) && (ratRNG < 110)) {
            return "sushi ";
        } else if ((ratRNG >= 110) && (ratRNG < 115)) {
            return "canadian (mad) ";
        } else if ((ratRNG >= 115) && (ratRNG < 120)) {
            return "karate ";
        } else if ((ratRNG >= 120) && (ratRNG < 125)) {
            return "wood ";
        } else if ((ratRNG >= 125) && (ratRNG < 130)) {
            return "road ";
        } else if ((ratRNG >= 130) && (ratRNG < 135)) {
            return "mullet ";
        } else if ((ratRNG >= 135) && (ratRNG < 140)) {
            return "chinese ";
        } else if ((ratRNG >= 140) && (ratRNG < 145)) {
            return "glass ";
        } else if ((ratRNG >= 145) && (ratRNG < 150)) {
            return "DK ";
        } else if ((ratRNG >= 150) && (ratRNG < 155)) {
            return "random ";
        } else if ((ratRNG >= 155) && (ratRNG < 160)) {
            return "ocean ";
        } else if ((ratRNG >= 160) && (ratRNG < 165)) {
            return "poison ";
        } else if ((ratRNG >= 165) && (ratRNG < 170)) {
            return "gym ";
        } else if ((ratRNG >= 170) && (ratRNG < 175)) {
            return "cross country ";
        } else if ((ratRNG >= 175) && (ratRNG < 180)) {
            return "ice ";
        } else if ((ratRNG >= 180) && (ratRNG < 185)) {
            return "fire ";
        } else if ((ratRNG >= 185) && (ratRNG < 190)) {
            return "space ";
        } else if ((ratRNG >= 190) && (ratRNG < 195)) {
            return "xander ";
        } else if ((ratRNG >= 195) && (ratRNG < 200)) {
            return "russian ";
        } else if ((ratRNG >= 200) && (ratRNG < 205)) {
            return "music ";
        } else if ((ratRNG >= 205) && (ratRNG < 210)) {
            return "iron ";
        } else if ((ratRNG >= 210) && (ratRNG < 215)) {
            return "punk ";
        } else if ((ratRNG >= 215) && (ratRNG < 220)) {
            return "screaming ";
        } else if ((ratRNG >= 220) && (ratRNG < 225)) {
            return "chair ";
        } else if ((ratRNG >= 225) && (ratRNG < 230)) {
            return "officer ";
        } else if ((ratRNG >= 230) && (ratRNG < 235)) {
            return "plauge ";
        } else if ((ratRNG >= 235) && (ratRNG < 240)) {
            return "fat ";
        } else if ((ratRNG >= 240) && (ratRNG < 245)) {
            return "sir ";
        } else if ((ratRNG >= 245) && (ratRNG < 250)) {
            return "bad ";
        } else if ((ratRNG >= 250) && (ratRNG < 255)) {
            return "business ";
        } else if ((ratRNG >= 255) && (ratRNG < 260)) {
            return "man ";
        } else if ((ratRNG >= 260) && (ratRNG < 265)) {
            return "super ";
        } else if ((ratRNG >= 265) && (ratRNG < 270)) {
            return "philadelphia ";
        } else if ((ratRNG >= 270) && (ratRNG < 275)) {
            return "whale ";
        } else if ((ratRNG >= 275) && (ratRNG < 280)) {
            return "royal ";
        } else if ((ratRNG >= 280) && (ratRNG < 285)) {
            return "radioactive ";
        } else if ((ratRNG >= 285) && (ratRNG < 290)) {
            return "statue ";
        } else if ((ratRNG >= 290) && (ratRNG < 295)) {
            return "dead ";
        } else if ((ratRNG >= 295) && (ratRNG < 300)) {
            return "candy ";
        } else if ((ratRNG >= 300) && (ratRNG < 305)) {
            return "regal ";
        } else if ((ratRNG >= 305) && (ratRNG < 310)) {
            return "kamikaze ";
        } else if ((ratRNG >= 310) && (ratRNG < 315)) {
            return "Emeile ";
        } else if ((ratRNG >= 315) && (ratRNG < 320)) {
            return "math ";
        } else if ((ratRNG >= 320) && (ratRNG < 325)) {
            return "gorilla ";
        } else if ((ratRNG >= 325) && (ratRNG < 330)) {
            return "Remy ";
        } else if ((ratRNG >= 330) && (ratRNG < 335)) {
            return "Emporer ";
        } else if ((ratRNG >= 335) && (ratRNG < 340)) {
            return "the last ";
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
            case "Shoot":
                attackRat();
                break;
            case "Magic":
                if (playerMP >= 4) {
                    magicAttackRat();
                    break;
                } else {
                    break;
                }
            case "Heal":
                if (playerMP >= (2 + (Math.round(ratsKilled / 8)))) {
                    heal();
                    break;
                } else {
                    break;
                }
            case "Inventory": {
                if (inventoryOpen == false) {
                    openInventory();
                    break;
                } else {
                    closeInventory();
                    break;
                }
            }
            case "Continue": {
                if (shopRNG == 0) {
                    spawnShop();
                    break;

                } else {
                    continueRatsShop();
                    break;
                }
            }
            case "Shop": {
                generateShop();
                break;
            }
            case "Leave":
                leaveShop();
                break;
            }
        }
    }
}
