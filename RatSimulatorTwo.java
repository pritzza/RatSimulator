
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
    JPanel mainTextPanel, attackButtonPanel, statsPanel, descriptionPanel, spellPanel, healPanel;
    JLabel statsLabel, descriptionLabel;
    Container con;
    JButton attackButton, spellButton, shabuButton, shabu2Button, protectButton, mindfulnessButton, eruptButton,
            healButton, lickButton, lick2Button, lick3Button, backButton, shopButton, leaveButton, continueButton,
            tryAgainButton;
    int playerATK, ratATK, ratGold, shopRNG;
    boolean healTab, ownLick, ownLick2, ownLick3, spellTab, ownShabu, ownShabu2, ownProtect, ownMindfulness,
            ownErupt = false;
    String spellLearned;

    // Battle dataA
    String playerWeapon = "Nothing";
    String playerWeaponVerbForButton = "Attack";
    String playerWeaponVerb = "attack";
    String ratAttackDescription = "bites you";
    String spellKill;
    String spellsMessage;
    String itemFound;
    int ratHP = 15;
    int playerHP = 100;
    int playerMaxHP = 100;
    int playerMP = 10;
    int playerMaxMP = 10;
    int ratsKilled = 1;
    int turn = 1;
    int playerHPHealed, ratRNG, protectTurns, mindfulnessTurns, trapTurns, ratItem;
    int playerGold = 0;

    // Inventory data
    JButton inventoryButton, usePotionButton, buyPotionButton, buyMushroomButton, useMushroomButton, buyTendyButton,
            useTendyButton, buyTrapButton, useTrapButton, buyPencilButton, usePencilButton, usePistolButton,
            buyPistolButton, useRatStickButton, buyRatStickButton, buyHatButton, useHatButton, ratKingButton;
    JPanel inventoryPanel, shopDescriptionPanel;
    JLabel shopDescriptionLabel;
    boolean inventoryOpen, ownPencil, ownPistol, ownRatStick, ownHat, ownRatKing, emporerRatKilled,
            lastRatKilled = false;

    // ammount of consumes
    int potionCount, mushroomCount, tendyCount, trapCount;

    // to be determined price of shop items
    int potionCost, mushroomCost, tendyCost, trapCost, pencilCost, pistolCost, ratStickCost, hatCost;

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

        // area of player hp, mp, gold etc
        statsPanel = new JPanel();
        statsPanel.setBounds(57, 58, 670, 32);
        statsPanel.setBackground(Color.black);
        con.add(statsPanel);

        // actual text for hp mp gold etc
        statsLabel = new JLabel();
        statsLabel.setForeground(Color.white);
        statsPanel.add(statsLabel);
        updateStats();

        // area for inventory items
        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(50, 460, 700, 80);
        inventoryPanel.setBackground(Color.black);
        con.add(inventoryPanel);
        inventoryPanel.setVisible(false);

        // area for healtab/heal spells
        healPanel = new JPanel();
        healPanel.setBounds(50, 460, 700, 80);
        healPanel.setBackground(Color.black);
        con.add(healPanel);
        healPanel.setVisible(false);

        // area for spelltab/ spells
        spellPanel = new JPanel();
        spellPanel.setBounds(50, 460, 700, 80);
        spellPanel.setBackground(Color.black);
        con.add(spellPanel);
        spellPanel.setVisible(false);

        // area for description text
        descriptionPanel = new JPanel();
        descriptionPanel.setBounds(0, 420, 800, 69);
        descriptionPanel.setBackground(Color.black);
        con.add(descriptionPanel);

        // description text for items and moves
        descriptionLabel = new JLabel();
        descriptionLabel.setBounds(0, 0, 800, 69);
        descriptionLabel.setForeground(Color.white);
        descriptionPanel.add(descriptionLabel);

        // big gray box
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(76, 100, 630, 250);
        mainTextPanel.setBackground(Color.DARK_GRAY);
        con.add(mainTextPanel);
        mainTextPanel.setOpaque(true);

        // text in big gray box
        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 600, 190);
        mainTextArea.setBackground(Color.DARK_GRAY);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setLineWrap(true);
        mainTextArea.setEditable(false);
        mainTextPanel.add(mainTextArea);
        mainTextArea.setText(
                "\nYou begin your journey to beat the rats...\n\n\n\nA rat appears!\n\n\n\n\n\n\nWhat will you do?");

        // area for attack spells heal inventory
        attackButtonPanel = new JPanel();
        attackButtonPanel.setBounds(237, 370, 310, 37);
        attackButtonPanel.setBackground(Color.black);
        con.add(attackButtonPanel);

        // attack button
        attackButton = new JButton(playerWeaponVerbForButton);
        attackButton.setBackground(Color.black);
        attackButton.setForeground(Color.white);
        attackButtonPanel.add(attackButton);
        attackButton.setFocusPainted(false);
        attackButton.addActionListener(choiceHandler);
        attackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                if (playerWeapon == "Nothing") {
                    descriptionLabel.setText("( " + 10 + " - " + 20 + "  damage )  Restore 1 MP");
                } else if (playerWeapon == "Pencil") {
                    descriptionLabel.setText("( " + 30 + " - " + 45 + "  damage )  Restore 1 MP");
                } else if (playerWeapon == "Pistol") {
                    descriptionLabel.setText("( " + 110 + " - " + 140 + "  damage )  Restore 1 MP");
                } else if (playerWeapon == "Charlie's Rat Bashing Stick") {
                    descriptionLabel.setText("( " + 180 + " - " + 230 + "  damage )  Restore 1 MP");
                }
            }

            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                if (playerWeapon == "Nothing") {
                    descriptionLabel.setText("( " + 10 + " - " + 20 + "  damage )  Restore 1 MP");
                } else if (playerWeapon == "Pencil") {
                    descriptionLabel.setText("( " + 30 + " - " + 45 + "  damage )  Restore 1 MP");
                } else if (playerWeapon == "Pistol") {
                    descriptionLabel.setText("( " + 110 + " - " + 140 + "  damage )  Restore 1 MP");
                } else if (playerWeapon == "Charlie's Rat Bashing Stick") {
                    descriptionLabel.setText("( " + 180 + " - " + 230 + "  damage )  Restore 1 MP");
                }
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        // spell button
        spellButton = new JButton("Spells");
        spellButton.setBackground(Color.black);
        spellButton.setForeground(Color.white);
        attackButtonPanel.add(spellButton);
        spellButton.setFocusPainted(false);
        spellButton.addActionListener(choiceHandler);

        // heal button
        healButton = new JButton("Heal");
        healButton.setBackground(Color.black);
        healButton.setForeground(Color.white);
        attackButtonPanel.add(healButton);
        healButton.setFocusPainted(false);
        healButton.addActionListener(choiceHandler);

        // shop button for when prompted by rat
        shopButton = new JButton("Shop");
        shopButton.setBackground(Color.black);
        shopButton.setForeground(Color.white);
        shopButton.setFocusPainted(false);
        shopButton.addActionListener(choiceHandler);

        // shop button for when prompted by rat
        leaveButton = new JButton("Leave");
        leaveButton.setBackground(Color.black);
        leaveButton.setForeground(Color.white);
        leaveButton.setFocusPainted(false);
        leaveButton.addActionListener(choiceHandler);

        // continue button
        continueButton = new JButton("Continue");
        continueButton.setBackground(Color.black);
        continueButton.setForeground(Color.white);
        continueButton.setFocusPainted(false);
        continueButton.addActionListener(choiceHandler);

        // inventory button
        inventoryButton = new JButton("Inventory");
        inventoryButton.setBackground(Color.black);
        inventoryButton.setForeground(Color.white);
        inventoryButton.setFocusPainted(false);
        inventoryButton.addActionListener(choiceHandler);

        // back button
        backButton = new JButton("Back");
        backButton.setBackground(Color.black);
        backButton.setForeground(Color.white);
        backButton.setFocusPainted(false);
        backButton.addActionListener(choiceHandler);

        tryAgainButton = new JButton("Try Again");
        tryAgainButton.setBackground(Color.black);
        tryAgainButton.setForeground(Color.white);
        tryAgainButton.setFocusPainted(false);
        tryAgainButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                restart();
            }
        });

        // area for shop item's descriptions
        shopDescriptionPanel = new JPanel();
        shopDescriptionPanel.setBounds(0, 490, 800, 50);
        shopDescriptionPanel.setBackground(Color.black);

        // shop item's descriptions
        shopDescriptionLabel = new JLabel();
        shopDescriptionLabel.setBounds(0, 0, 500, 50);
        shopDescriptionLabel.setBackground(Color.black);
        shopDescriptionLabel.setForeground(Color.white);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // SHOP AND INVENTORY ITEM BUTTONS AND HOVER MESSAGES //
        ////////////////////////////////////////////////////////////////////////////////////////////

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
                shopDescriptionLabel.setText("A weird shaped flask with an transparent yellow fluid in it.");
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
                if (potionCount > 0) {
                    descriptionLabel.setText("A yellow rat-shaped potion you bought from a rat.");
                } else {
                    descriptionLabel.setText("You don't have any potions.");
                }
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        buyMushroomButton = new JButton();
        buyMushroomButton.setBackground(Color.black);
        buyMushroomButton.setForeground(Color.white);
        buyMushroomButton.setText("Mushroom - " + mushroomCost + "G");
        buyMushroomButton.setFocusPainted(false);
        buyMushroomButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                buyMushroom();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("An unnatural looking fungi.");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("");
            }
        });

        useMushroomButton = new JButton();
        useMushroomButton.setBackground(Color.black);
        useMushroomButton.setForeground(Color.white);
        useMushroomButton.setText("Mushroom (x " + mushroomCount + ")");
        useMushroomButton.setFocusPainted(false);
        useMushroomButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                useMushroom();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                if (mushroomCount > 0) {
                    descriptionLabel.setText("A slender mushroom that looks like it might kill you if you eat it.");
                } else {
                    descriptionLabel.setText("You don't have any mushrooms.");
                }
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        buyTendyButton = new JButton();
        buyTendyButton.setBackground(Color.black);
        buyTendyButton.setForeground(Color.white);
        buyTendyButton.setText("Tendy - " + tendyCost + "G");
        buyTendyButton.setFocusPainted(false);
        buyTendyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                buyTendy();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("A tendy made from an unidentifiable meat.");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("");
            }
        });

        useTendyButton = new JButton();
        useTendyButton.setBackground(Color.black);
        useTendyButton.setForeground(Color.white);
        useTendyButton.setText("Tendy (x " + tendyCount + ")");
        useTendyButton.setFocusPainted(false);
        useTendyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                useTendy();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                if (tendyCount > 0) {
                    descriptionLabel.setText("A big juicy tendy with a nice crunch to it.");
                } else {
                    descriptionLabel.setText("You don't have any tendies.");
                }
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        buyTrapButton = new JButton();
        buyTrapButton.setBackground(Color.black);
        buyTrapButton.setForeground(Color.white);
        buyTrapButton.setText("Mouse Trap - " + trapCost + "G");
        buyTrapButton.setFocusPainted(false);
        buyTrapButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                buyTrap();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("A mouse trap.");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("");
            }
        });

        useTrapButton = new JButton();
        useTrapButton.setBackground(Color.black);
        useTrapButton.setForeground(Color.white);
        useTrapButton.setText("Trap (x " + trapCount + ")");
        useTrapButton.setFocusPainted(false);
        useTrapButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                useTrap();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                if (trapCount > 0) {
                    descriptionLabel.setText("A mouse trap. Should be good for trapping some rats, right?");
                } else {
                    descriptionLabel.setText("You don't have any tendies.");
                }
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        buyPencilButton = new JButton();
        buyPencilButton.setBackground(Color.black);
        buyPencilButton.setForeground(Color.white);
        buyPencilButton.setText("Pencil");
        buyPencilButton.setFocusPainted(false);
        buyPencilButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                buyPencil();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("A yellow pencil with bite marks on it. Looks good for stabbing rats.");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("");
            }
        });

        usePencilButton = new JButton();
        usePencilButton.setBackground(Color.black);
        usePencilButton.setForeground(Color.white);
        usePencilButton.setText("Pencil");
        usePencilButton.setFocusPainted(false);
        usePencilButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                usePencil();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                if (playerWeapon == "Pencil") {
                    descriptionLabel.setText("(EQUIPPED) A freshy sharpened No. 2 pencil.");
                } else {
                    descriptionLabel.setText("A freshly sharpened No. 2 pencil.");
                }
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
                    descriptionLabel
                            .setText("(EQUIPPED) Upon further inspection, you notice another engraving \"Mrs. W\".");
                } else {
                    descriptionLabel.setText("A pistol.");
                }
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        useRatStickButton = new JButton();
        useRatStickButton.setBackground(Color.black);
        useRatStickButton.setForeground(Color.white);
        useRatStickButton.setText("Charlie's Rat Bashing Stick");
        useRatStickButton.setFocusPainted(false);
        useRatStickButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                useRatStick();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                if (playerWeapon == "Charlie's Rat Bashing Stick") {
                    descriptionLabel.setText(
                            "(EQUIPPED) \"It's like whole generations of those things have died at my hands\"");
                } else {
                    descriptionLabel.setText("A legendary instrument used by the king of the rats, Charlie Kelly.");
                }
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        buyHatButton = new JButton();
        buyHatButton.setBackground(Color.black);
        buyHatButton.setForeground(Color.white);
        buyHatButton.setText("Hat");
        buyHatButton.setFocusPainted(false);
        buyHatButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                buyHat();
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("A black Nike hat. Looks like good protection.");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                shopDescriptionLabel.setText("");
            }
        });

        useHatButton = new JButton();
        useHatButton.setBackground(Color.black);
        useHatButton.setForeground(Color.white);
        useHatButton.setText("James' Hat");
        useHatButton.setFocusPainted(false);
        useHatButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("(You're already wearing the hat.)");
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText(
                        "You realize this is the legendary hat of Mr. Kim. May this hat protect you from any rat (boosts defence)");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        ratKingButton = new JButton();
        ratKingButton.setBackground(Color.black);
        ratKingButton.setForeground(Color.white);
        ratKingButton.setText("Rat King");
        ratKingButton.setFocusPainted(false);
        ratKingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText(
                        "An incredibly rare formation of rats. Urban legend says that those who find a rat king will soon meet their deaths (boosts luck)");
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

    ////////////////////////////////////////////////////////////////////////////////////////////
    // ATTACK (FUNCTIONS), SPELLTAB, AND HEALTAB: BUTTONS //
    ////////////////////////////////////////////////////////////////////////////////////////////

    // If attack button pressed, calculates and displays what happens

    public void attackRat() {

        // If rat dead, encounter new rat
        if (ratHP <= 0) {
            generateRat();
        }

        // If rat alive, generate playerATK and ratATK and exchange damage
        else if (ratHP > 0) {
            generateRatATK();
            if (playerWeapon == "Nothing") {
                playerATK = (rng.nextInt(11) + 10);
            } else if (playerWeapon == "Pencil") {
                playerATK = (rng.nextInt(16) + 30);
            } else if (playerWeapon == "Pistol") {
                playerATK = (rng.nextInt(31) + 110);
            } else if (playerWeapon == "Charlie's Rat Bashing Stick") {
                playerATK = (rng.nextInt(51) + 180);
            }
            playerMP++;

            if (playerMP > playerMaxMP) {
                playerMP = playerMaxMP;
            }

            // Increase turn count by 1, and calculate damage done to rat
            turn++;
            ratHP = ratHP - playerATK;

            // If rat isnt killed by attack, the rat then attacks. HP, MP, G, Turns are
            // updated. Battle message is displayed
            if (ratHP > 0) {
                buffs();

                playerHP = playerHP - ratATK;
                updateStats();

                mainTextArea.setText("\nYou " + playerWeaponVerb + " the " + ratNames() + "rat for " + playerATK
                        + " damage!\n\n\n\nThe " + ratNames() + "rat (# " + ratsKilled + ") remains with " + ratHP
                        + " HP.\n\n\n\nThe " + ratNames() + "rat " + ratAttackDescription + " for " + ratATK
                        + " damage!\n\n\n" + spellsMessage);
            }

            // Else if Rat is killed by players attack, increase player's max stats, update
            // HP, MP, G, turn values, and display rat killed message
            else {
                spellKill = "";
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

    ////////////////////////////////////////////////////////////////////////////////////////////
    // EVERYTHING SPELL TAB AND BUTTONS //
    ////////////////////////////////////////////////////////////////////////////////////////////

    public void spell() {

        if (spellTab == false) {

            openSpellTab();
        } else {

            closeSpellTab();
        }

        if (spellTab == true) {
            closeInventory();
            closeHealTab();
        }
    }

    public void openSpellTab() {
        spellTab = true;
        spellPanel.setVisible(true);

        shabuButton = new JButton("Shabu");
        shabuButton.setBackground(Color.black);
        shabuButton.setForeground(Color.white);
        shabuButton.setFocusPainted(false);
        shabuButton.setVisible(true);
        shabuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                if (playerMP >= 3) {
                    shabu();
                } else {
                    descriptionLabel.setText("You don't have enough mana!");
                }
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("Scorch the enemy rat for 30 - 70 damage  (Requires 3 MP)");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        if (ownShabu == false) {
            ownShabu = true;
            spellPanel.add(shabuButton);
            shabuButton.setVisible(true);
        }

        shabu2Button = new JButton("Shabu Shabu");
        shabu2Button.setBackground(Color.black);
        shabu2Button.setForeground(Color.white);
        shabu2Button.setFocusPainted(false);
        shabu2Button.setVisible(true);
        shabu2Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                if (playerMP >= 6) {
                    shabu2();
                } else {
                    descriptionLabel.setText("You don't have enough mana!");
                }
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText(
                        "Scorch and eat the enemy rat; deal 120 - 170 damage and restore 70 - 80 HP (Requires 6 MP)");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        while ((ratsKilled >= 16) && (ownShabu2 == false)) {
            ownShabu2 = true;
            spellPanel.add(shabu2Button);
            shabu2Button.setVisible(true);
        }

        mindfulnessButton = new JButton("Mindfulness");
        mindfulnessButton.setBackground(Color.black);
        mindfulnessButton.setForeground(Color.white);
        mindfulnessButton.setFocusPainted(false);
        mindfulnessButton.setVisible(true);
        mindfulnessButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                if (playerMP >= 0) {
                    mindfulnessMove();
                } else {
                    descriptionLabel.setText("You don't have enough mana!");
                }
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                descriptionLabel
                        .setText("Become mindful for 5 turns;  take double damage, but restore 3 MP  (Costs 0 MP)");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        while ((ratsKilled >= 26) && (ownMindfulness == false)) {
            ownMindfulness = true;
            spellPanel.add(mindfulnessButton);
            mindfulnessButton.setVisible(true);
        }

        protectButton = new JButton("Magic Shield");
        protectButton.setBackground(Color.black);
        protectButton.setForeground(Color.white);
        protectButton.setFocusPainted(false);
        protectButton.setVisible(true);
        protectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                if (playerMP >= 10) {
                    protectMove();
                } else {
                    descriptionLabel.setText("You don't have enough mana!");
                }
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                descriptionLabel
                        .setText("Create a magic shield to protect from the rats for 5 turns  (Requires 15 MP)");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        while ((ratsKilled >= 41) && (ownProtect == false)) {
            ownProtect = true;
            spellPanel.add(protectButton);
            protectButton.setVisible(true);
        }

        eruptButton = new JButton("Erupt");
        eruptButton.setBackground(Color.black);
        eruptButton.setForeground(Color.white);
        eruptButton.setFocusPainted(false);
        eruptButton.setVisible(true);
        eruptButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                if (playerMP >= 18) {
                    erupt();
                } else {
                    descriptionLabel.setText("You don't have enough mana!");
                }
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText(
                        "A fiery explosion that deals 500 - 700 damage, and can cook a rat to prefection to restores 400 - 500 HP (Requires 18 MP)");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        while ((ratsKilled >= 76) && (ownErupt == false)) {
            ownErupt = true;
            spellPanel.add(eruptButton);
            eruptButton.setVisible(true);
        }
    }

    public void closeSpellTab() {
        spellTab = false;
        spellPanel.setVisible(false);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // SPELL FUNCTIONS //
    ////////////////////////////////////////////////////////////////////////////////////////////

    public void shabu() {

        if (ratHP <= 0) {
            generateRat();
        }

        else if (ratHP > 0) {
            generateRatATK();
            playerATK = rng.nextInt(41) + 30;
            playerMP = playerMP - 3;

            turn++;
            ratHP = ratHP - playerATK;

            if (ratHP > 0) {
                buffs();

                playerHP = playerHP - ratATK;
                updateStats();

                mainTextArea.setText("\nYou cast shabu on the " + ratNames() + " rat and deal " + playerATK
                        + " damage!\n\n\n\nThe " + ratNames() + "rat (# " + ratsKilled + ") remains with " + ratHP
                        + " HP.\n\n\n\nThe " + ratNames() + "rat " + ratAttackDescription + " for " + ratATK
                        + " damage!\n\n\n" + spellsMessage);
            } else {
                spellKill = "Shabu";
                ratKilled();
                updateStats();
            }
        }

        while (playerHP <= 0) {

            die();
            break;
        }
    }

    public void shabu2() {

        if (ratHP <= 0) {
            generateRat();
        }

        else if (ratHP > 0) {
            generateRatATK();
            playerATK = rng.nextInt(51) + 120;
            playerHPHealed = rng.nextInt(10) + 70;
            playerHP = playerHP + playerHPHealed;
            playerMP = playerMP - 6;

            turn++;
            ratHP = ratHP - playerATK;

            if (ratHP > 0) {
                buffs();

                playerHP = playerHP - ratATK;
                updateStats();

                mainTextArea.setText("\nYou cast shabu shabu on the " + ratNames() + " rat and deal " + playerATK
                        + " damage, while restoring " + playerHPHealed + " HP!\n\n\n\nThe " + ratNames() + "rat (# "
                        + ratsKilled + ") remains with " + ratHP + " HP.\n\n\n\nThe " + ratNames() + "rat "
                        + ratAttackDescription + " for " + ratATK + " damage!\n\n\n" + spellsMessage);
            }

            else {
                spellKill = "Shabu Shabu";
                ratKilled();
                updateStats();
            }
        }
        while (playerHP <= 0) {

            die();
            break;
        }
    }

    public void protectMove() {

        if (ratHP <= 0) {
            generateRat();
        }

        else if (ratHP > 0) {
            generateRatATK();

            protectTurns = 6;
            playerMP = playerMP - 15;

            buffs();

            turn++;

            if (ratHP > 0) {

                playerHP = playerHP - ratATK;
                updateStats();

                mainTextArea.setText("\nYou conjuer a protective shield around yourself.\n\n\n\nThe " + ratNames()
                        + "rat " + ratAttackDescription + " for " + ratATK + " damage!\n\n\n" + spellsMessage);
            }
        }
        while (playerHP <= 0) {

            die();
            break;
        }
    }

    public void mindfulnessMove() {

        if (ratHP <= 0) {
            generateRat();
        }

        else if (ratHP > 0) {
            generateRatATK();

            mindfulnessTurns = 6;

            buffs();

            turn++;

            if (ratHP > 0) {

                playerHP = playerHP - ratATK;
                updateStats();

                mainTextArea.setText("\nYou slow down and take a moment to be mindful.\n\n\n\nThe " + ratNames()
                        + "rat " + ratAttackDescription + " for " + ratATK + " damage!\n\n\n" + spellsMessage);
            }
        }
        while (playerHP <= 0) {

            die();
            break;
        }
    }

    public void erupt() {

        if (ratHP <= 0) {
            generateRat();
        }

        else if (ratHP > 0) {
            generateRatATK();
            playerATK = rng.nextInt(101) + 500;
            playerHPHealed = rng.nextInt(101) + 400;
            playerHP = playerHP + playerHPHealed;
            playerMP = playerMP - 18;

            turn++;
            ratHP = ratHP - playerATK;

            if (ratHP > 0) {
                buffs();

                playerHP = playerHP - ratATK;
                updateStats();

                mainTextArea.setText("\nYou cast erupt on the " + ratNames() + " rat and deal " + playerATK
                        + " damage, while restoring " + playerHPHealed + " HP!\n\n\n\nThe " + ratNames() + "rat (# "
                        + ratsKilled + ") remains with " + ratHP + " HP.\n\n\n\nThe " + ratNames() + "rat "
                        + ratAttackDescription + " for " + ratATK + " damage!\n\n\n" + spellsMessage);
            }

            else {
                spellKill = "Erupt";
                ratKilled();
                updateStats();
            }
        }
        while (playerHP <= 0) {

            die();
            break;
        }
    }

    // deals with whether magic shield is up, mindfullness, or if you have the hat
    // ratATK modifier stuff

    public void buffs() {

        if ((mindfulnessTurns > 0) && (protectTurns > 0) && (trapTurns > 0)) {

            protectTurns--;
            mindfulnessTurns--;
            trapTurns--;
            ratATK = 0;
            playerMP = playerMP + 3;

            if ((mindfulnessTurns > 0) && (protectTurns > 0) && (trapTurns > 0)) {
                ratATK = 0;
                spellsMessage = "You think about how you got here: self reflecting inside a magic shield as a rat insults and laughs at you for\nattempting to trap it in a mouse's trap and restore 3 MP ("
                        + mindfulnessTurns + " mindful, " + trapTurns + " mouse trap, and " + protectTurns
                        + " magic shield turns left)";
            }
            if ((mindfulnessTurns == 0) && (protectTurns == 0)) {
                ratATK = 0;
                spellsMessage = "Your mindfulness and shield shatter together.\n(" + trapTurns
                        + " mouse trap turns left)";
            } else if ((trapTurns == 0) && (protectTurns == 0)) {
                spellsMessage = "Your shield fades right as the rat gets serious.\n(" + protectTurns
                        + " magic shield turns left)";
            } else if (trapTurns == 0) {
                spellsMessage = "The rat gets over your terrible attempt to trap it.\n(" + protectTurns
                        + " magic shield and " + mindfulnessTurns + " mindful turns left)";
            } else if (protectTurns == 0) {
                ratATK = 0;
                spellsMessage = "The shield shatters.\n(" + trapTurns + " mouse trap and " + mindfulnessTurns
                        + " mindful turns left)";
            } else if (mindfulnessTurns == 0) {
                ratATK = 0;
                spellsMessage = "You stop being mindful.\n(" + protectTurns + " magic shield and " + trapTurns
                        + " mouse trap turns left)";
            }

        } else if ((mindfulnessTurns > 0) && (protectTurns > 0)) {

            protectTurns--;
            mindfulnessTurns--;
            playerMP = playerMP + 3;

            if ((mindfulnessTurns > 0) && (protectTurns > 0)) {
                spellsMessage = "You think about how the shield lessened the impact and nullified your vulnerability and restore 3 MP.\n("
                        + mindfulnessTurns + " mindful and " + protectTurns + " magic shield turns left)";
            } else if (mindfulnessTurns == 0) {
                spellsMessage = "You stop being mindful. (" + protectTurns + " magic shield turns left)";
            } else if (protectTurns == 0) {
                spellsMessage = "Your shield shatters. (" + mindfulnessTurns + " mindful turns left)";
            } else {
                spellsMessage = "Your mindfulness and shield shatter together.";
            }

        } else if ((protectTurns > 0) && (trapTurns > 0)) {

            protectTurns--;
            trapTurns--;
            ratATK = 0;

            if ((protectTurns > 0) && (trapTurns > 0)) {
                ratATK = 0;
                spellsMessage = "The magic shield does nothing as the " + ratNames()
                        + "rat is too occupied with the mouse trap.\n(" + trapTurns + " mouse trap and " + protectTurns
                        + " magic shield turns left)";

            } else if (protectTurns == 0) {
                ratATK = 0;
                spellsMessage = "The shield fades away. ( " + trapTurns + " mouse trap turns left)";
            } else if (trapTurns == 0) {
                spellsMessage = "The rat gets over your terrible attempt to trap it. ( " + protectTurns
                        + " magic shield turns left)";
            } else if ((mindfulnessTurns == 0) && (trapTurns == 0)) {
                spellsMessage = "Your shield fades right as the rat gets serious.";
            }

        } else if ((mindfulnessTurns > 0) && (trapTurns > 0)) {

            mindfulnessTurns--;
            trapTurns--;
            ratATK = 0;
            playerMP = playerMP + 3;

            if ((mindfulnessTurns > 0) && (trapTurns > 0)) {
                ratATK = 0;
                spellsMessage = "You think about how the " + ratNames()
                        + "rat laughs and insults you for trying to trap a rat into a mouse's trap\nand regenerate 3 MP. ("
                        + mindfulnessTurns + " turns left)";

            } else if (mindfulnessTurns == 0) {
                ratATK = 0;
                spellsMessage = "You stop being mindful. ( " + trapTurns + " turns left)";
            } else if (trapTurns == 0) {
                spellsMessage = "The rat gets over your terrible attempt to trap it. ( " + mindfulnessTurns
                        + " mindful turns left)";
            } else if ((mindfulnessTurns == 0) && (trapTurns == 0)) {
                spellsMessage = "You stop being mindful and the rat gets over the mouse trap.";
            }

        } else if (mindfulnessTurns > 0) {

            mindfulnessTurns--;
            ratATK = ratATK * 2;
            playerMP = playerMP + 3;

            if (mindfulnessTurns > 0) {

                if (ratATK > 0) {
                    spellsMessage = "You think about how much that hurt and regenerate 3 MP ( " + mindfulnessTurns
                            + " turns left)";
                } else {
                    spellsMessage = "You think about how nice the rat is to not attack you and regenerate 3 MP ( "
                            + mindfulnessTurns + " turns left)";
                }

            } else {
                spellsMessage = "You stop being mindful.";
            }

        } else if (protectTurns > 0) {

            protectTurns--;
            ratATK = (ratATK / 2);

            if (protectTurns > 0) {

                spellsMessage = "The shield lessened the impact. (" + protectTurns + " turns left)";

            } else {
                spellsMessage = "The sheild shatters.";
            }

        } else if (trapTurns > 0) {

            trapTurns--;
            ratATK = 0;

            if (trapTurns > 0) {
                ratATK = 0;
                spellsMessage = "The rat " + ratNames() + "continues to laugh at your attemp to trap it. ( " + trapTurns
                        + " mouse trap turns left)";

            } else {
                spellsMessage = "The rat gets over your terrible attempt to trap it.";
            }

        } else {
            spellsMessage = "";
        }
        if (ownHat == true) {
            ratATK = (Math.round((ratATK * 80) / 100));
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // HEAL TAB BUTTONS //
    ////////////////////////////////////////////////////////////////////////////////////////////

    public void heal() {

        if (healTab == false) {

            openHealTab();
        } else {

            closeHealTab();
        }

        if (healTab == true) {
            closeInventory();
            closeSpellTab();
        }
    }

    public void openHealTab() {
        healTab = true;
        healPanel.setVisible(true);

        lickButton = new JButton("Lick Your Wounds");
        lickButton.setBackground(Color.black);
        lickButton.setForeground(Color.white);
        lickButton.setFocusPainted(false);
        lickButton.setVisible(true);
        lickButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                if (playerMP >= 1) {
                    lick();
                } else {
                    descriptionLabel.setText("You don't have enough mana!");
                }
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("Lick your wounds and heal  40 - 50 HP  (Requires 1 MP)");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        if (ownLick == false) {
            ownLick = true;
            healPanel.add(lickButton);
            lickButton.setVisible(true);
        }

        lick2Button = new JButton("Lick Your Wounds II");
        lick2Button.setBackground(Color.black);
        lick2Button.setForeground(Color.white);
        lick2Button.setFocusPainted(false);
        lick2Button.setVisible(true);
        lick2Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                if (playerMP >= 3) {
                    lick2();
                } else {
                    descriptionLabel.setText("You don't have enough mana!");
                }
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("Lick your wounds and heal  140 - 180 HP  (Requires 3 MP)");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        while ((ratsKilled >= 21) && (ownLick2 == false)) {
            ownLick2 = true;
            healPanel.add(lick2Button);
            lick2Button.setVisible(true);
        }

        lick3Button = new JButton("Lick Your Wounds III");
        lick3Button.setBackground(Color.black);
        lick3Button.setForeground(Color.white);
        lick3Button.setFocusPainted(false);
        lick3Button.setVisible(true);
        lick3Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                if (playerMP >= 10) {
                    lick3();
                } else {
                    descriptionLabel.setText("You don't have enough mana!");
                }
            }

            public void mouseEntered(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("Lick your wounds and heal  450 - 560 HP  (Requires 10 MP)");
            }

            public void mouseExited(final java.awt.event.MouseEvent evt) {
                descriptionLabel.setText("");
            }
        });

        while ((ratsKilled >= 51) && (ownLick3 == false)) {
            ownLick3 = true;
            healPanel.add(lick3Button);
            lick3Button.setVisible(true);
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // HEALING SPELL FUNCTIONS //
    ////////////////////////////////////////////////////////////////////////////////////////////

    public void lick() {

        if (ratHP <= 0) {
            generateRat();
        }

        else if (ratHP > 0) {
            generateRatATK();
            playerHPHealed = rng.nextInt(11) + 40;
            playerATK = 0;

            if (playerHP < playerMaxHP) {
                playerHP = playerHP + (Math.round(playerHPHealed));
            }
            playerMP--;

            buffs();
            turn++;
            playerHP = playerHP - ratATK;
            updateStats();
            mainTextArea.setText("\nYou lick your wounds and heal " + playerHPHealed + " HP!\n\n\nThe " + ratNames()
                    + "rat (# " + ratsKilled + ") remains with " + ratHP + " HP.\n\n\n\nThe " + ratNames() + "rat "
                    + ratAttackDescription + " for " + ratATK + " damage!\n\n\n" + spellsMessage);
        }
        while (playerHP <= 0) {

            die();
            break;
        }
    }

    public void lick2() {

        if (ratHP <= 0) {
            generateRat();
        }

        else if (ratHP > 0) {
            generateRatATK();
            playerHPHealed = rng.nextInt(41) + 140;
            playerATK = 0;

            if (playerHP < playerMaxHP) {
                playerHP = playerHP + (Math.round(playerHPHealed));
            }
            playerMP = playerMP - 3;

            buffs();
            turn++;
            playerHP = playerHP - ratATK;
            updateStats();
            mainTextArea.setText("\nYou lick your wounds and heal " + playerHPHealed + " HP!\n\n\nThe " + ratNames()
                    + "rat (# " + ratsKilled + ") remains with " + ratHP + " HP.\n\n\n\nThe " + ratNames() + "rat "
                    + ratAttackDescription + " for " + ratATK + " damage!\n\n\n" + spellsMessage);
        }
        while (playerHP <= 0) {

            die();
            break;
        }
    }

    public void lick3() {

        if (ratHP <= 0) {
            generateRat();
        }

        else if (ratHP > 0) {
            generateRatATK();
            playerHPHealed = rng.nextInt(111) + 450;
            playerATK = 0;

            if (playerHP < playerMaxHP) {
                playerHP = playerHP + (Math.round(playerHPHealed));
            }
            playerMP = playerMP - 10;

            buffs();
            turn++;
            playerHP = playerHP - ratATK;
            updateStats();
            mainTextArea.setText("\nYou lick your wounds and heal " + playerHPHealed + " HP!\n\n\nThe " + ratNames()
                    + "rat (# " + ratsKilled + ") remains with " + ratHP + " HP.\n\n\n\nThe " + ratNames() + "rat "
                    + ratAttackDescription + " for " + ratATK + " damage!\n\n\n" + spellsMessage);
        }
        while (playerHP <= 0) {

            die();
            break;
        }
    }

    public void closeHealTab() {
        healTab = false;
        healPanel.setVisible(false);
    }

    // Updates values of text in infoLabel
    public void updateStats() {

        while (playerHP > playerMaxHP) {
            playerHP = playerMaxHP;
        }
        while (playerMP > playerMaxMP) {
            playerMP = playerMaxMP;
        }

        statsLabel.setText("HP: " + playerHP + " / " + playerMaxHP + "            MP: " + playerMP + " / " + playerMaxMP
                + "            G: " + playerGold
                + "                                                                                                                    Turn: "
                + turn);
    }

    // opens inventory and manages what shows up
    public void openInventory() {
        if ((healTab == true) || (spellTab == true)) {
            closeHealTab();
            closeSpellTab();
        }
        inventoryOpen = true;
        inventoryPanel.setVisible(true);
        if ((potionCount == 0) && (mushroomCount == 0) && (tendyCount == 0) && (ownPencil == false)
                && (ownPistol == false) && (ownHat == false) && (ownRatStick == false) && (ownRatKing == false)) {
            descriptionLabel.setText("You have nothing in your inventory...");
        }

        if (potionCount != 0) {
            usePotionButton.setText("Potion (x " + potionCount + ")");
            inventoryPanel.add(usePotionButton);
            usePotionButton.setVisible(true);
        } else {
            usePotionButton.setVisible(false);
        }

        if (mushroomCount != 0) {
            useMushroomButton.setText("Mushroom (x " + mushroomCount + ")");
            useMushroomButton.setVisible(true);
        } else {
            useMushroomButton.setVisible(false);
        }

        if (tendyCount != 0) {
            useTendyButton.setText("Tendy (x " + tendyCount + ")");
            useTendyButton.setVisible(true);
        } else {
            useTendyButton.setVisible(false);
        }

        if (trapCount != 0) {
            useTrapButton.setText("Mouse Trap (x " + trapCount + ")");
            useTrapButton.setVisible(true);
        } else {
            useTrapButton.setVisible(false);
        }

        if (ownPencil == true) {
            usePencilButton.setVisible(true);
        }
        if (ownPistol == true) {
            usePistolButton.setVisible(true);
        }
        if (ownRatStick == true) {
            useRatStickButton.setVisible(true);
        }
        if (ownHat == true) {
            useHatButton.setVisible(true);
        }
        if (ownRatKing == true) {
            ratKingButton.setVisible(true);
            inventoryPanel.add(ratKingButton);
        }
    }

    public void closeInventory() {
        inventoryOpen = false;
        inventoryPanel.setVisible(false);
        descriptionLabel.setText("");
    }

    // Message when player dies
    public void die() {

        closeHealTab();
        closeSpellTab();
        closeInventory();
        attackButton.setVisible(false);
        healButton.setVisible(false);
        spellButton.setVisible(false);
        inventoryButton.setVisible(false);
        statsLabel.setText("You Have Fallen To The Rats");
        if (ratsKilled <= 101) {
            mainTextArea.setText("\nYou died.\n\n\nYou only manage to fend off a mere " + ratsKilled
                    + " rats.\n\n\n\n\n\n\n\nHumanity succumbs to the order of the rats.");
        } else {
            mainTextArea.setText("\nYou died.\n\n\nYou managed to defeat " + ratsKilled
                    + " rats.\n\n\n\nHowever, there is no \"last rat \"; there will always me more...\n\n\n\nHumanity will always succumb to the order of the rats... But you win! Thanks for playing!");
        }
        attackButtonPanel.add(tryAgainButton);
        tryAgainButton.setVisible(true);
    }

    // Increase max player stats, give gold, increase ratsKilled
    public void ratKilled() {

        ratHP = 0;
        ratATK = 0;
        playerMaxHP = (playerMaxHP + 10) + ratsKilled / 3;
        playerHP = (playerHP + 10) + ratsKilled / 3;

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

        if (ownRatKing == false) {
            ratGold = 1 + (rng.nextInt(4) * ratsKilled / 5);
            ratItem = rng.nextInt(101);
        } else {
            ratGold = 2 + (rng.nextInt(6) * ratsKilled / 5);
            ratItem = rng.nextInt(51);
        }

        if (ratsKilled == 100) {
            ratRNG = -1;
        } else if (ratsKilled > 100) {
            ratRNG = -2;
        } else {
            ratRNG = (26 * ratsKilled) / 8 + (rng.nextInt(31));
        }
        mainTextArea.setText("\nA " + rats() + "rat appears with " + ratHP + " HP!\n\n\n\n\n\n\n\n\nWhat will you do?");

    }

    // Setting up shop interface and randomly generates prices for items
    public void spawnShop() {

        closeHealTab();
        closeSpellTab();

        mainTextArea.setText(
                "\nYou find a rat vendor.\n\n\nSqueek Squeek Squeek Squeek\n(Care to examine the stock upheld within my emporium, human?)\n\n\n\n\n\nWhat will you do?");
        attackButtonPanel.add(shopButton);
        attackButtonPanel.add(leaveButton);
        shopButton.setVisible(true);
        leaveButton.setVisible(true);
        continueButton.setVisible(false);
        attackButton.setVisible(false);
        spellButton.setVisible(false);
        healButton.setVisible(false);
        inventoryButton.setVisible(false);
        inventoryPanel.setVisible(false);
        inventoryOpen = false;
        healTab = false;
        healPanel.setVisible(false);

    }

    // adds all buttons for what you can buy in the shop
    public void generateShop() {

        potionCost = 5;
        potionCost = rng.nextInt(5) + (potionCost - 3) + (ratsKilled / 40);

        mushroomCost = 19;
        mushroomCost = rng.nextInt(7) + mushroomCost - 6;

        tendyCost = 30;
        tendyCost = rng.nextInt(6) + tendyCost - 2;

        trapCost = 45;
        trapCost = rng.nextInt(20) + trapCost;

        pencilCost = 20;
        pencilCost = rng.nextInt(10) + pencilCost - 5;

        pistolCost = 80;
        pistolCost = rng.nextInt(15) + pistolCost - 5;

        hatCost = 110;
        hatCost = rng.nextInt(30) + hatCost - 20;

        mainTextArea.setText("\nWhat would you like, human?");

        con.add(shopDescriptionPanel);
        shopDescriptionPanel.add(shopDescriptionLabel);
        shopDescriptionPanel.setVisible(true);
        shopDescriptionLabel.setVisible(true);
        descriptionPanel.add(buyPotionButton);
        descriptionPanel.add(buyMushroomButton);
        descriptionPanel.add(buyTendyButton);
        descriptionPanel.add(buyTrapButton);
        descriptionPanel.add(buyPencilButton);
        descriptionPanel.add(buyPistolButton);
        descriptionPanel.add(buyHatButton);
        buyPotionButton.setText("Potion - " + potionCost + "G");
        buyPotionButton.setVisible(true);
        buyHatButton.setText("Nike Hat - " + hatCost + "G");
        buyHatButton.setVisible(true);
        buyTendyButton.setText("Tendy - " + tendyCost + "G");
        buyTrapButton.setText("Mouse Trap - " + trapCost + "G");
        buyMushroomButton.setText("Mushroom - " + mushroomCost + "G");
        buyPistolButton.setText("Pistol - " + pistolCost + "G");
        buyPencilButton.setText("Pencil - " + pencilCost + "G");

        if (ownPencil == false) {
            buyPencilButton.setVisible(true);
            buyMushroomButton.setVisible(false);
            buyTendyButton.setVisible(false);
            buyPistolButton.setVisible(false);
            buyTrapButton.setVisible(false);

        } else {
            buyPencilButton.setVisible(true);
        }

        if (ownPencil == true) {
            buyMushroomButton.setVisible(true);
            buyTendyButton.setVisible(true);
            buyTrapButton.setVisible(true);
            buyPencilButton.setVisible(false);
            buyPistolButton.setVisible(true);
            buyHatButton.setVisible(true);
            if (ownPistol == false) {
                buyPistolButton.setVisible(true);
            } else {
                buyPistolButton.setVisible(false);
            }

        } else {
            buyMushroomButton.setVisible(false);
            buyTendyButton.setVisible(false);
            buyPistolButton.setVisible(false);
            buyTrapButton.setVisible(false);
        }
        if (ownHat == true) {
            buyHatButton.setVisible(false);
        } else {
            buyHatButton.setVisible(true);
        }
        shopButton.setVisible(false);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // FUNCTION BEHIND ALL ITEM BUTTONS, BUY AND U //
    ////////////////////////////////////////////////////////////////////////////////////////////
    public void buyPencil() {

        if (playerGold >= pencilCost) {
            if (ownPencil == false) {
                inventoryPanel.add(usePencilButton);
                usePencilButton.setVisible(false);
                playerGold = playerGold - pencilCost;
                ownPencil = true;
                mainTextArea.setText(
                        "\nSqueek Squeek Squeek.\n(Fine transaction, human.\n\n\n\n\n\nRemember to equip new weapons!");
                updateStats();
            } else {
                mainTextArea.setText("\nSqueek Squeek.\n(All out of stock, human.)");
            }
        } else if (playerGold == 0) {
            mainTextArea.setText(
                    "\nSqueek Squeek Squeek Squeek.\n(How the do you not have a single piece of gold you worthless vermin.)");
        } else if (playerGold < pencilCost) {
            mainTextArea.setText("\nSqueek.\nPoor human. Pitiful.");

        } else {
            leaveShop();
        }
    }

    public void usePencil() {

        if (playerWeapon != "Pencil") {

            playerWeapon = "Pencil";
            playerWeaponVerbForButton = "Stab";
            playerWeaponVerb = "stab";
            attackButton.setText(playerWeaponVerbForButton);
            usePencilButton.setText("Equipped: Pencil");
            usePistolButton.setText("Pistol");
            useRatStickButton.setText("Charlie's Rat Bashing Stick");
            descriptionLabel.setText("(EQUIPPED) A freshy sharpened No. 2 pencil.");
        } else {
            playerWeapon = "Nothing";
            playerWeaponVerbForButton = "Attack";
            playerWeaponVerb = "attack";
            attackButton.setText(playerWeaponVerbForButton);
            usePencilButton.setText("Pencil");
            descriptionLabel.setText("A freshly sharpened No. 2 pencil.");
        }
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
                mainTextArea.setText("\nSqueek Squeek.\n(All out of stock, human.)");
            }
        } else if (playerGold == 0) {
            mainTextArea.setText(
                    "\nSqueek Squeek Squeek Squeek.\n(How the do you not have a single piece of gold you worthless vermin.)");
        } else if (playerGold < pistolCost) {
            mainTextArea.setText("\nSqueek.\nPoor human. Pitiful.");

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
            usePencilButton.setText("Pencil");
            useRatStickButton.setText("Charlie's Rat Bashing Stick");
            usePistolButton.setText("Equipped: Pistol");
            descriptionLabel.setText("(EQUIPPED) A pistol.");
        } else {
            playerWeapon = "Nothing";
            playerWeaponVerbForButton = "Attack";
            playerWeaponVerb = "attack";
            attackButton.setText(playerWeaponVerbForButton);
            usePistolButton.setText("Pistol");
            descriptionLabel.setText("A pistol.");
        }
    }

    public void useRatStick() {

        if (playerWeapon != "Charlie's Rat Bashing Stick") {

            playerWeapon = "Charlie's Rat Bashing Stick";
            playerWeaponVerbForButton = "Bash";
            playerWeaponVerb = "bash";
            attackButton.setText(playerWeaponVerbForButton);
            usePencilButton.setText("Pencil");
            usePistolButton.setText("Pistol");
            useRatStickButton.setText("Equipped: Charlie's Rat Bashing Stick");
            descriptionLabel.setText("(EQUIPPED) A legendary instrument used by the king of the rats, Charlie Kelly.");
        } else {
            playerWeapon = "Nothing";
            playerWeaponVerbForButton = "Attack";
            playerWeaponVerb = "attack";
            attackButton.setText(playerWeaponVerbForButton);
            descriptionLabel.setText("A legendary instrument used by the king of the rats, Charlie Kelly.");
            useRatStickButton.setText("Charlie's Rat Bashing Stick");
        }
    }

    public void buyHat() {

        if (playerGold >= hatCost) {
            if (ownHat == false) {
                inventoryPanel.add(useHatButton);
                useHatButton.setVisible(false);
                playerGold = playerGold - hatCost;
                ownHat = true;
                mainTextArea.setText(
                        "\nSqueek Squeek Squeek Squeek Squeek.\n(A masterfully forged cap, blessed by the gods. Enjoy the transaction, human.)");
                updateStats();
            } else {
                mainTextArea.setText("\nSqueek Squeek.\n(There is but one cap, human.)");
            }
        } else if (playerGold == 0) {
            mainTextArea.setText(
                    "\nSqueek Squeek Squeek Squeek.\n(How the do you not have a single piece of gold you worthless vermin.)");
        } else if (playerGold < pistolCost) {
            mainTextArea.setText("\nSqueek.\nPoor human. Pitiful.");

        } else {
            leaveShop();
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
                    "\nSqueek Squeek Squeek Squeek.\n(How the do you not have a single piece of gold you worthless vermin.)");
        } else if (playerGold < potionCost) {
            mainTextArea.setText("\nSqueek Squeek.\nPoor human. Pitiful.");

        } else {
            leaveShop();
        }
    }

    public void usePotion() {
        if (potionCount >= 1) {
            potionCount = potionCount - 1;
            playerHP = playerHP + 50;
            playerMP = playerMP + 3;
            if (playerHP > playerMaxHP) {
                playerHP = playerMaxHP;
            }
            if (playerMP > playerMaxMP) {
                playerMP = playerMaxMP;
            }
            usePotionButton.setText("Potion (x " + potionCount + ")");
            updateStats();
            descriptionLabel.setText(
                    "\nYou drink a rat's strange yellow concotion and feel tingly... " + "You restore 50 HP and 3 MP!");
        } else {
            descriptionLabel.setText("You don't have any potions");
        }
    }

    public void buyMushroom() {

        if (playerGold >= mushroomCost) {
            inventoryPanel.add(useMushroomButton);
            useMushroomButton.setVisible(false);
            playerGold = playerGold - mushroomCost;
            mushroomCount = mushroomCount + 1;
            mainTextArea.setText("\nSqueek Squeek Squeek.\n(Enjoy the transaction, human.)\n\n\nYou now have "
                    + mushroomCount + " mushrooms");
            updateStats();
        } else if (playerGold == 0) {
            mainTextArea.setText(
                    "\nSqueek Squeek Squeek Squeek.\n(How the do you not have a single piece of gold you worthless vermin.)");
        } else if (playerGold < mushroomCost) {
            mainTextArea.setText("\nSqueek.\nPoor human. Pitiful.");

        } else {
            leaveShop();
        }
    }

    public void useMushroom() {
        if (mushroomCount >= 1) {
            mushroomCount = mushroomCount - 1;
            playerHP = playerHP - 125;
            playerMP = playerMP + 15;
            if (playerHP <= 0) {
                die();
            }
            if (playerMP > playerMaxMP) {
                playerMP = playerMaxMP;
            }
            useMushroomButton.setText("Mushroom (x " + mushroomCount + ")");
            updateStats();
            descriptionLabel
                    .setText("\nYou chew and swallow the vile mushroom. You take 125 damage but restore 15 MP.");
        } else {
            descriptionLabel.setText("You don't have any mushrooms");
        }
    }

    public void buyTendy() {

        if (playerGold >= tendyCost) {
            inventoryPanel.add(useTendyButton);
            useTendyButton.setVisible(false);
            playerGold = playerGold - tendyCost;
            tendyCount = tendyCount + 1;
            mainTextArea.setText("\nSqueek Squeek Squeek.\n(Enjoy the transaction, human.)\n\n\nYou now have "
                    + tendyCount + " tendies");
            updateStats();
        } else if (playerGold == 0) {
            mainTextArea.setText(
                    "\nSqueek Squeek Squeek Squeek.\n(How the do you not have a single piece of gold you worthless vermin.)");
        } else if (playerGold < tendyCost) {
            mainTextArea.setText("\nSqueek Squeek.\nPoor human. Pitiful.");

        } else {
            leaveShop();
        }
    }

    public void useTendy() {
        if (tendyCount >= 1) {
            tendyCount = tendyCount - 1;
            playerHP = playerHP + 500;
            if (playerHP > playerMaxHP) {
                playerHP = playerMaxHP;
            }
            useTendyButton.setText("Tendy (x " + tendyCount + ")");
            updateStats();
            descriptionLabel.setText("\nYou eat the crispy, crunchy, juicy tendy. You restore 500 HP!");
        } else {
            descriptionLabel.setText("You don't have any tendies");
        }
    }

    public void buyTrap() {

        if (trapCount >= 10) {
            mainTextArea.setText(
                    "\nSqueek Squeek Squeek.\n(We have sold out of mouse traps, thank you for your business, human.)");
        } else {
            if (playerGold >= trapCost) {
                inventoryPanel.add(useTrapButton);
                useTrapButton.setVisible(false);
                playerGold = playerGold - trapCost;
                trapCount = trapCount + 1;
                mainTextArea.setText(
                        "\nSqueek Squeek Squeek Squeek.\n(Ah, one of my favorite toys. Enjoy the transaction, human.)\n\n\nYou now have "
                                + trapCount + " mouse traps");
                updateStats();
            } else if (playerGold == 0) {
                mainTextArea.setText(
                        "\nSqueek Squeek Squeek Squeek.\n(How the do you not have a single piece of gold you worthless vermin.)");
            } else if (playerGold < trapCost) {
                mainTextArea.setText("\nSqueek Squeek.\nPoor human. Pitiful.");

            } else {
                leaveShop();
            }
        }
    }

    public void useTrap() {

        if (trapCount >= 1) {
            trapCount = trapCount - 1;

            if (ratHP <= 0) {
                generateRat();
            }

            else if (ratHP > 0) {

                useTrapButton.setText("Mouse Trap (x " + trapCount + ")");

                trapTurns = rng.nextInt(2) + 3;

                buffs();

                turn++;

                if (ratHP > 0) {

                    playerHP = playerHP - ratATK;
                    updateStats();

                    mainTextArea.setText("\nYou set up a mouse trap for the " + ratNames() + " rat.\n\n\n\nThe "
                            + ratNames() + "rat laughs at your pityful attempt to trap it.\n\n\n" + spellsMessage);
                }
            }
            while (playerHP <= 0) {

                die();
                break;
            }

        } else {
            descriptionLabel.setText("You don't have any tendies");
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
        buyMushroomButton.setVisible(false);
        buyTendyButton.setVisible(false);
        buyTrapButton.setVisible(false);
        buyPencilButton.setVisible(false);
        buyPistolButton.setVisible(false);
        buyHatButton.setVisible(false);
        shopRNG = 1;

    }

    // When you kill a rat and continue: the rats death display message
    public void killedRatContinue() {

        if (ratsKilled == 15) {
            spellLearned = "~~~  You learn Shabu Shabu  ~~~";
        } else if (ratsKilled == 20) {
            spellLearned = "~~~  You learn Lick Your Wounds II  ~~~";
        } else if (ratsKilled == 25) {
            spellLearned = "~~~  You learn Mindfulness  ~~~";
        } else if (ratsKilled == 40) {
            spellLearned = "~~~  You learn Magic Shield  ~~~";
        } else if (ratsKilled == 50) {
            spellLearned = "~~~  You learn Lick Your Wounds III  ~~~";
        } else if (ratsKilled == 69) {
            spellLearned = "~~~  You find Charlie's Rat Bashing Stick  ~~~";
        } else if (ratsKilled == 75) {
            spellLearned = "~~~  You learn Erupt  ~~~";
        } else {
            spellLearned = "";
        }

        if (ownRatKing == false) {
            if (ratItem == 100) {
                itemFound = " You find a rat king too!";
                ownRatKing = true;
            }
        }
        if ((ratItem <= 5) && (ratItem < 0)) {
            itemFound = " You find a potion too!";
            potionCount = potionCount + 1;
        } else if (ratItem == 6) {
            itemFound = " You find a mushroom too!";
            mushroomCount = mushroomCount + 1;
        } else if (ratItem == 7) {
            itemFound = " You find tendy too!";
            tendyCount = tendyCount + 1;
        } else {
            itemFound = "";
        }

        if (spellKill == "") {
            mainTextArea.setText("\nYou " + playerWeaponVerb + " the " + ratNames() + "rat for " + playerATK
                    + " damage!\n\n\n\nThe " + ratNames() + "rat (# " + ratsKilled + ") dies and drops " + ratGold
                    + " gold.\n\n\nYour max HP increases by " + (10 + (ratsKilled / 3))
                    + "! Your max MP increases by 1!\n\n\n" + spellLearned + itemFound);
        } else if (spellKill == "Shabu") {
            mainTextArea.setText("\nYou cook the " + ratNames() + "rat for " + playerATK + " damage!\n\n\n\nThe "
                    + ratNames() + "rat (# " + ratsKilled + ") dies and drops " + ratGold
                    + " gold.\n\n\nYour max HP increases by " + (10 + (ratsKilled / 3))
                    + "! Your max MP increases by 1!\n\n\n" + spellLearned + itemFound);
        } else if (spellKill == "Shabu Shabu") {
            mainTextArea.setText("\nYou cook and eat the " + ratNames() + "rat for " + playerATK
                    + " damage, while restoring " + playerHPHealed + " HP!\n\n\n\nThe " + ratNames() + "rat (# "
                    + ratsKilled + ") dies and drops " + ratGold + " gold.\n\n\nYour max HP increases by "
                    + (10 + (ratsKilled / 3)) + "! Your max MP increases by 1!\n\n\n" + spellLearned + itemFound);
        } else if (spellKill == "Erupt") {
            mainTextArea.setText("\nYou char the " + ratNames() + "rat into a fine crisp for " + playerATK
                    + " damage and restore " + playerHPHealed + " HP!\n\n\n\nThe " + ratNames() + "rat (# " + ratsKilled
                    + ") dies and drops " + ratGold + " gold.\n\n\nYour max HP increases by " + (10 + (ratsKilled / 3))
                    + "! Your max MP increases by 1!\n\n\n" + spellLearned + itemFound);
        }

        ratsKilled++;

        attackButton.setVisible(false);
        spellButton.setVisible(false);
        healButton.setVisible(false);
        inventoryButton.setVisible(false);
        continueButton.setVisible(true);
        closeHealTab();
        closeSpellTab();
        closeInventory();

        if (ratsKilled == 101) {
            shopRNG = 0;
        } else {
            shopRNG = rng.nextInt(3);
        }

        while ((ratsKilled >= 70) && (ownRatStick == false)) {
            ownRatStick = true;
            inventoryPanel.add(useRatStickButton);
            useRatStickButton.setVisible(false);
        }
    }

    // When you leave the shop and go kill more rats
    public void continueRatsShop() {

        attackButton.setVisible(true);
        spellButton.setVisible(true);
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
            ratHP = rng.nextInt(10) + (1 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (1 * ratsKilled) / 4;
            ratAttackDescription = "nips you";
            return "baby ";
        } else if ((ratRNG >= 10) && (ratRNG < 15)) {
            // HP 2, ATK 2 == 4
            ratHP = rng.nextInt(10) + (2 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (2 * ratsKilled) / 4;
            ratAttackDescription = "lightly bites you";
            return "weak ";
        } else if ((ratRNG >= 15) && (ratRNG < 20)) {
            // HP 2, ATK 3 == 5
            ratHP = rng.nextInt(10) + (2 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (3 * ratsKilled) / 4;
            ratAttackDescription = "quickly chews on you";
            return "small ";
        } else if ((ratRNG >= 20) && (ratRNG < 25)) {
            // HP 3, ATK 3 == 6
            ratHP = rng.nextInt(10) + (3 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (3 * ratsKilled) / 4;
            ratAttackDescription = "squeeks and bites you";
            return "squeeking ";
        } else if ((ratRNG >= 25) && (ratRNG < 30)) {
            // HP 2, ATK 4 == 6
            ratHP = rng.nextInt(10) + (2 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (4 * ratsKilled) / 4;
            ratAttackDescription = "gnaws on you";
            return "hungry ";
        } else if ((ratRNG >= 30) && (ratRNG < 35)) {
            // HP 2, ATK 5 == 7
            ratHP = rng.nextInt(10) + (2 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
            ratGold = 0;
            ratAttackDescription = "spitefully chomps on you";
            return "homeless ";
        } else if ((ratRNG >= 35) && (ratRNG < 40)) {
            // HP 2, ATK 6 == 8
            ratHP = rng.nextInt(10) + (2 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (6 * ratsKilled) / 4;
            ratAttackDescription = "farts on your face";
            return "smelly ";
        } else if ((ratRNG >= 40) && (ratRNG < 45)) {
            // HP 5, ATK 3 == 8
            ratHP = rng.nextInt(10) + (5 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (3 * ratsKilled) / 4;
            ratAttackDescription = "skitters across you and gets hair all over you";
            return "hairy ";
        } else if ((ratRNG >= 45) && (ratRNG < 50)) {
            // HP 5, ATK 4 == 8
            ratHP = rng.nextInt(10) + (5 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (4 * ratsKilled) / 4;
            ratAttackDescription = "chomps on you";
            return "big ";
        } else if ((ratRNG >= 50) && (ratRNG < 55)) {
            // HP 4, ATK 5 == 9
            ratHP = rng.nextInt(10) + (4 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
            ratGold = 4 + rng.nextInt(3) * ratsKilled / 2;
            ratAttackDescription = "uses an orchestras spell on you";
            return "yellow ";
        } else if ((ratRNG >= 55) && (ratRNG < 60)) {
            // HP 3, ATK 6 == 9
            ratHP = rng.nextInt(10) + (3 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (6 * ratsKilled) / 4;
            ratAttackDescription = "flies on top of you and lands a bite on you";
            return "flying ";
        } else if ((ratRNG >= 60) && (ratRNG < 65)) {
            // HP 4, ATK 5 == 9
            ratHP = rng.nextInt(10) + (4 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
            ratAttackDescription = "socks you";
            return "boxer ";
        } else if ((ratRNG >= 65) && (ratRNG < 70)) {
            // HP 4, ATK -4 == 0
            ratHP = rng.nextInt(10) + (4 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (-4 * ratsKilled) / 4;
            ratAttackDescription = "apologizes and offers you some syrup ";
            return "canadian ";
        } else if ((ratRNG >= 70) && (ratRNG < 75)) {
            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
            ratAttackDescription = "vengfully bites you";
            return "lab ";
        } else if ((ratRNG >= 75) && (ratRNG < 80)) {
            // HP 1, ATK 9 == 10
            ratHP = rng.nextInt(10) + (1 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (9 * ratsKilled) / 4;
            ratAttackDescription = "shoots himself at you";
            return "cannon ";
        } else if ((ratRNG >= 80) && (ratRNG < 85)) {
            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
            ratAttackDescription = "mech punches you";
            return "cyborg ";
        } else if ((ratRNG >= 85) && (ratRNG < 95)) {
            // HP 4, ATK 6 == 10
            ratHP = rng.nextInt(10) + (4 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (6 * ratsKilled) / 4;
            ratAttackDescription = "uses a damage algorythm on you";
            return "computer ";
        } else if ((ratRNG >= 90) && (ratRNG < 100)) {
            // HP 5, ATK 3 == 18
            ratHP = rng.nextInt(10) + (5 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (3 * ratsKilled) / 4;
            ratAttackDescription = "pecks you";
            return "pigeon ";
        } else if ((ratRNG >= 95) && (ratRNG < 105)) {
            // HP 5, ATK 5 == 10
            ratHP = rng.nextInt(10) + (5 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
            ratAttackDescription = "scratches you with five claws instead of four";
            return "five toe'd ";
        } else if ((ratRNG >= 100) && (ratRNG < 110)) {
            // HP 4, ATK 6 == 10
            ratHP = rng.nextInt(10) + (4 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (6 * ratsKilled) / 4;
            ratAttackDescription = "throws curry at your eyes";
            return "indian ";
        } else if ((ratRNG >= 105) && (ratRNG < 110)) {
            // HP 6, ATK 5 == 11
            ratHP = rng.nextInt(10) + (6 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
            ratAttackDescription = "wraps you in seaweed";
            return "sushi ";
        } else if ((ratRNG >= 110) && (ratRNG < 115)) {
            // HP 3, ATK 8 == 11
            ratHP = rng.nextInt(10) + (3 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (8 * ratsKilled) / 4;
            ratAttackDescription = "reluctantly drowned you in syrup";
            return "canadian (mad) ";
        } else if ((ratRNG >= 115) && (ratRNG < 120)) {
            // HP 3, ATK 8 == 11
            ratHP = rng.nextInt(10) + (3 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (8 * ratsKilled) / 4;
            ratAttackDescription = "chops you";
            return "karate ";
        } else if ((ratRNG >= 120) && (ratRNG < 125)) {
            // HP 11, ATK 6 == 11 + (6/2)
            ratHP = rng.nextInt(10) + (3 * ratsKilled) * 1;
            // ratATK = rng.nextInt(2) * (6 * ratsKilled) / 4;
            ratAttackDescription = "transforms into something wooden";
            return "wood ";
        } else if ((ratRNG >= 125) && (ratRNG < 130)) {
            // HP 5, ATK 7 == 12
            ratHP = rng.nextInt(10) + (5 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (7 * ratsKilled) / 4;
            ratAttackDescription = "run you over with his Toyota Camry";
            return "road ";
        } else if ((ratRNG >= 130) && (ratRNG < 135)) {
            // HP 8, ATK 4 == 12
            ratHP = rng.nextInt(10) + (6 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (4 * ratsKilled) / 4;
            ratAttackDescription = "PUNK MOSH";
            return "mullet ";
        } else if ((ratRNG >= 135) && (ratRNG < 140)) {
            // HP 5, ATK 7 == 12
            ratHP = rng.nextInt(10) + (5 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (7 * ratsKilled) / 4;
            ratAttackDescription = "censors you";
            return "chinese ";
        } else if ((ratRNG >= 140) && (ratRNG < 145)) {
            // HP 2, ATK 10 == 12
            ratHP = rng.nextInt(10) + (2 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (10 * ratsKilled) / 4;
            ratAttackDescription = "breaks";
            return "glass ";
        } else if ((ratRNG >= 145) && (ratRNG < 150)) {
            ratHP = rng.nextInt(10) + (6 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (6 * ratsKilled) / 4;
            ratAttackDescription = "raps at you";
            return "DK ";
        } else if ((ratRNG >= 150) && (ratRNG < 155)) {
            // HP ?, ATK ? == ?
            ratHP = rng.nextInt((19) - 6) * (2 * ratsKilled) * 1;
            // ratATK = rng.nextInt((19) - 6) * (2 * ratsKilled) * 1;
            ratAttackDescription = "does something";
            return "random ";
        } else if ((ratRNG >= 155) && (ratRNG < 160)) {
            // HP 7, ATK 5 == 12
            ratHP = rng.nextInt(10) + (7 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
            ratAttackDescription = "calls upon the kraken";
            return "ocean ";
        } else if ((ratRNG >= 160) && (ratRNG < 165)) {
            // HP 4, ATK 8 == 12
            ratHP = rng.nextInt(10) + (4 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (8 * ratsKilled) / 4;
            ratAttackDescription = "jumps in your mouth and sneezes";
            return "poison ";
        } else if ((ratRNG >= 165) && (ratRNG < 170)) {
            // HP 5, ATK 7 == 12
            ratHP = rng.nextInt(10) + (5 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (7 * ratsKilled) / 4;
            ratAttackDescription = "deadlifts you";
            return "gym ";
        } else if ((ratRNG >= 170) && (ratRNG < 175)) {
            // HP 10, ATK 2 == 12
            ratHP = rng.nextInt(10) + (10 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (2 * ratsKilled) / 4;
            ratAttackDescription = "rams into you";
            return "cross country ";
        } else if ((ratRNG >= 175) && (ratRNG < 180)) {
            // HP 8, ATK 4 == 12
            ratHP = rng.nextInt(10) + (8 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (4 * ratsKilled) / 4;
            ratAttackDescription = "freezes you";
            return "ice ";
        } else if ((ratRNG >= 180) && (ratRNG < 185)) {
            // HP 4, ATK 8 == 12
            ratHP = rng.nextInt(10) + (4 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (8 * ratsKilled) / 4;
            ratAttackDescription = "burns you";
            return "fire ";
        } else if ((ratRNG >= 185) && (ratRNG < 190)) {
            // HP 2, ATK 11 == 13
            ratHP = rng.nextInt(10) + (2 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (11 * ratsKilled) / 4;
            ratAttackDescription = "shoots a lazer gun at you";
            return "space ";
        } else if ((ratRNG >= 190) && (ratRNG < 195)) {
            // HP 2, ATK 1 == 3
            ratHP = rng.nextInt(10) + (2 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (1 * ratsKilled) / 4;
            ratAttackDescription = "screams \"EXcqeeze Me?!?!?!\"";
            return "xander ";
        } else if ((ratRNG >= 195) && (ratRNG < 200)) {
            // HP 6, ATK 7 == 13
            ratHP = rng.nextInt(10) + (6 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (7 * ratsKilled) / 4;
            ratAttackDescription = "slaps you";
            return "russian ";
        } else if ((ratRNG >= 200) && (ratRNG < 205)) {
            // HP 7, ATK 6 == 13
            ratHP = rng.nextInt(10) + (7 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (6 * ratsKilled) / 4;
            ratAttackDescription = "plays out of tune";
            return "music ";
        } else if ((ratRNG >= 205) && (ratRNG < 210)) {
            // HP 8, ATK 5 == 13
            ratHP = rng.nextInt(10) + (8 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
            ratAttackDescription = "decks you in the jaw";
            return "iron ";
        } else if ((ratRNG >= 210) && (ratRNG < 215)) {
            // HP 5, ATK 8 == 13
            ratHP = rng.nextInt(10) + (5 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (8 * ratsKilled) / 4;
            ratAttackDescription = "PUNK MOSH";
            return "punk ";
        } else if ((ratRNG >= 215) && (ratRNG < 220)) {
            // HP 7, ATK 7 == 14
            ratHP = rng.nextInt(10) + (7 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (7 * ratsKilled) / 4;
            ratAttackDescription = "DIES IN FORTNITE AND DOUBLES IN VOLUME";
            return "screaming ";
        } else if ((ratRNG >= 220) && (ratRNG < 225)) {
            // HP 15, ATK -1 == 14
            ratHP = rng.nextInt(10) + (15 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (-1 * ratsKilled) / 4;
            ratAttackDescription = "lets you take a breather and sit on him";
            return "chair ";
        } else if ((ratRNG >= 225) && (ratRNG < 230)) {
            // HP 6, ATK 8 == 14
            ratHP = rng.nextInt(10) + (6 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (8 * ratsKilled) / 4;
            ratAttackDescription = "brutalizes you";
            return "officer ";
        } else if ((ratRNG >= 230) && (ratRNG < 235)) {
            // HP 4, ATK 10 == 14
            ratHP = rng.nextInt(10) + (4 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (10 * ratsKilled) / 4;
            ratAttackDescription = "squeeks and infects you";
            return "plauge ";
        } else if ((ratRNG >= 235) && (ratRNG < 240)) {
            // HP 12, ATK 2 == 14
            ratHP = rng.nextInt(10) + (12 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (2 * ratsKilled) / 4;
            ratAttackDescription = "sits on your face";
            return "fat ";
        } else if ((ratRNG >= 240) && (ratRNG < 245)) {
            // HP 7, ATK 7 == 14
            ratHP = rng.nextInt(10) + (7 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (7 * ratsKilled) / 4;
            ratAttackDescription = "exempts you";
            return "sir ";
        } else if ((ratRNG >= 245) && (ratRNG < 250)) {
            // HP 8, ATK 7 == 15
            ratHP = rng.nextInt(10) + (8 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (7 * ratsKilled) / 4;
            ratAttackDescription = "releases";
            return "bad ";
        } else if ((ratRNG >= 250) && (ratRNG < 255)) {
            // HP 6, ATK 9 == 15
            ratHP = rng.nextInt(10) + (6 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (9 * ratsKilled) / 4;
            ratGold = 5 + rng.nextInt(10) * ratsKilled / 7;
            ratAttackDescription = "fires you";
            return "business ";
        } else if ((ratRNG >= 255) && (ratRNG < 260)) {
            // HP 6, ATK 9 == 15
            ratHP = rng.nextInt(10) + (6 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (9 * ratsKilled) / 4;
            ratAttackDescription = "assults you";
            return "man ";
        } else if ((ratRNG >= 260) && (ratRNG < 265)) {
            // HP 8, ATK 8 == 16
            ratHP = rng.nextInt(10) + (8 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (8 * ratsKilled) / 4;
            ratAttackDescription = "uses hyper fang";
            return "super ";
        } else if ((ratRNG >= 265) && (ratRNG < 270)) {
            // HP 8, ATK 8 == 16
            ratHP = rng.nextInt(10) + (8 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (8 * ratsKilled) / 4;
            ratAttackDescription = "throws garbage at you";
            return "philadelphia ";
        } else if ((ratRNG >= 270) && (ratRNG < 275)) {
            // HP 12, ATK 5 == 17
            ratHP = rng.nextInt(10) + (12 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
            ratAttackDescription = "swallows";
            return "whale ";
        } else if ((ratRNG >= 275) && (ratRNG < 280)) {
            // HP 8, ATK 8 == 16
            ratHP = rng.nextInt(10) + (8 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (8 * ratsKilled) / 4;
            ratAttackDescription = "beheads";
            return "royal ";
        } else if ((ratRNG >= 280) && (ratRNG < 285)) {
            // HP 3, ATK 13 == 16
            ratHP = rng.nextInt(10) + (3 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (13 * ratsKilled) / 4;
            ratAttackDescription = "mutates you";
            return "radioactive ";
        } else if ((ratRNG >= 285) && (ratRNG < 290)) {
            // HP 16, ATK 0 == 16
            ratHP = rng.nextInt(10) + (16 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (0 * ratsKilled) / 4;
            ratAttackDescription = "remains a statue";
            return "statue ";
        } else if ((ratRNG >= 290) && (ratRNG < 295)) {
            // HP -16, ATK 0 == -16
            ratHP = rng.nextInt(10) + (-16 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (0 * ratsKilled) / 4;
            ratAttackDescription = "rests in peace";
            return "dead ";
        } else if ((ratRNG >= 295) && (ratRNG < 300)) {
            // HP 6, ATK 10 == 16
            ratHP = rng.nextInt(10) + (6 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (10 * ratsKilled) / 4;
            ratAttackDescription = "tricks you";
            return "candy ";
        } else if ((ratRNG >= 300) && (ratRNG < 305)) {
            // HP 8, ATK 9 == 17
            ratHP = rng.nextInt(10) + (8 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (9 * ratsKilled) / 4;
            ratAttackDescription = "politely strikes you";
            return "regal ";
        } else if ((ratRNG >= 305) && (ratRNG < 310)) {
            // HP 2, ATK 15 == 17
            ratHP = rng.nextInt(10) + (2 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (17 * ratsKilled) / 4;
            ratAttackDescription = "expodes";
            return "kamikaze ";
        } else if ((ratRNG >= 310) && (ratRNG < 315)) {
            // HP 9, ATK 8 == 17
            ratHP = rng.nextInt(10) + (9 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (8 * ratsKilled) / 4;
            ratAttackDescription = "eats garbage";
            return "Emeile ";
        } else if ((ratRNG >= 315) && (ratRNG < 320)) {
            // HP 9, ATK 9 == 18
            ratHP = rng.nextInt(10) + (9 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (9 * ratsKilled) / 4;
            ratAttackDescription = "solves the function";
            return "math ";
        } else if ((ratRNG >= 320) && (ratRNG < 325)) {
            // HP 8, ATK 11 == 19
            ratHP = rng.nextInt(10) + (8 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (11 * ratsKilled) / 4;
            ratAttackDescription = "rips you in half";
            return "gorilla ";
        } else if ((ratRNG >= 325) && (ratRNG < 400)) {
            // HP 10, ATK 10 == 20
            ratHP = rng.nextInt(10) + (10 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (10 * ratsKilled) / 4;
            ratAttackDescription = "cooks his famous ratatouille";
            return "Remy ";
        } else if (ratRNG == -1) {
            // HP 20, ATK 15 == 35
            ratHP = rng.nextInt(10) + (25 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (15 * ratsKilled) / 4;
            ratGold = rng.nextInt(500) + 1000;
            ratAttackDescription = "exiles";
            return "Emporer ";
        } else if (ratRNG == -2) {
            // HP 200, ATK 20 == 220
            ratHP = rng.nextInt(10) + (200 * ratsKilled) * 1;
            // ratATK = rng.nextInt(10) + (20 * ratsKilled) / 4;
            ratAttackDescription = "punishes you, an ignorant human, for attempting to defeat the rats";
            return "Last ";
        } else {// HP 3, ATK 2 == 5
            ratHP = rng.nextInt(10) + (4 * ratsKilled);
            // ratATK = rng.nextInt(10) + (3 * ratsKilled) / 4;
            ratAttackDescription = "bites you";
            return "";
        }
    }

    // Used every turn in combat to randomly generate the rat's ATK
    public void generateRatATK() {
        if ((ratRNG >= 5) && (ratRNG < 10)) {
            ratATK = rng.nextInt(10) + (1 * ratsKilled) / 4;
        } else if ((ratRNG >= 10) && (ratRNG < 15)) {
            ratATK = rng.nextInt(10) + (2 * ratsKilled) / 4;
        } else if ((ratRNG >= 15) && (ratRNG < 20)) {
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 4;
        } else if ((ratRNG >= 20) && (ratRNG < 25)) {
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 4;
        } else if ((ratRNG >= 25) && (ratRNG < 30)) {
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 4;
        } else if ((ratRNG >= 30) && (ratRNG < 35)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
        } else if ((ratRNG >= 35) && (ratRNG < 40)) {
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 4;
        } else if ((ratRNG >= 40) && (ratRNG < 45)) {
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 4;
        } else if ((ratRNG >= 45) && (ratRNG < 50)) {
            ratATK = rng.nextInt(10) + (4 * ratsKilled) / 4;
        } else if ((ratRNG >= 50) && (ratRNG < 55)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
        } else if ((ratRNG >= 55) && (ratRNG < 60)) {
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 4;
        } else if ((ratRNG >= 60) && (ratRNG < 65)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
        } else if ((ratRNG >= 65) && (ratRNG < 70)) {
            ratATK = rng.nextInt(10) + (-4 * ratsKilled) / 4;
        } else if ((ratRNG >= 70) && (ratRNG < 75)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
        } else if ((ratRNG >= 75) && (ratRNG < 80)) {
            ratATK = rng.nextInt(10) + (9 * ratsKilled) / 4;
        } else if ((ratRNG >= 80) && (ratRNG < 85)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
        } else if ((ratRNG >= 85) && (ratRNG < 95)) {
            ratATK = rng.nextInt(10) + (6 * ratsKilled) / 4;
        } else if ((ratRNG >= 90) && (ratRNG < 100)) {
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 4;
        } else if ((ratRNG >= 95) && (ratRNG < 105)) {
            ratATK = rng.nextInt(10) + (5 * ratsKilled) / 4;
        } else if ((ratRNG >= 100) && (ratRNG < 110)) {
            ratATK = rng.nextInt(15) + (6 * (ratsKilled) - 10) / 3;
        } else if ((ratRNG >= 105) && (ratRNG < 110)) {
            ratATK = rng.nextInt(15) + (5 * (ratsKilled) - 10) / 3;
        } else if ((ratRNG >= 110) && (ratRNG < 115)) {
            ratATK = rng.nextInt(15) + (8 * (ratsKilled) - 10) / 3;
        } else if ((ratRNG >= 115) && (ratRNG < 120)) {
            ratATK = rng.nextInt(15) + (8 * (ratsKilled) - 10) / 3;
        } else if ((ratRNG >= 120) && (ratRNG < 125)) {
            ratATK = rng.nextInt(2) * (5 * (ratsKilled) - 10) / 3;
        } else if ((ratRNG >= 125) && (ratRNG < 130)) {
            ratATK = rng.nextInt(15) + (7 * (ratsKilled) - 10) / 3;
        } else if ((ratRNG >= 130) && (ratRNG < 135)) {
            ratATK = rng.nextInt(15) + (4 * (ratsKilled) - 10) / 3;
        } else if ((ratRNG >= 135) && (ratRNG < 140)) {
            ratATK = rng.nextInt(15) + (7 * (ratsKilled) - 10) / 3;
        } else if ((ratRNG >= 140) && (ratRNG < 145)) {
            ratATK = rng.nextInt(20) + (10 * (ratsKilled) - 15) / 3;
        } else if ((ratRNG >= 145) && (ratRNG < 150)) {
            ratATK = rng.nextInt(20) + (6 * (ratsKilled) - 15) / 3;
        } else if ((ratRNG >= 150) && (ratRNG < 155)) {
            ratATK = rng.nextInt((10) - 6) * (2 * ratsKilled);
        } else if ((ratRNG >= 155) && (ratRNG < 160)) {
            ratATK = rng.nextInt(20) + (5 * (ratsKilled) - 15) / 3;
        } else if ((ratRNG >= 160) && (ratRNG < 165)) {
            ratATK = rng.nextInt(20) + (8 * (ratsKilled) - 15) / 3;
        } else if ((ratRNG >= 165) && (ratRNG < 170)) {
            ratATK = rng.nextInt(20) + (7 * (ratsKilled) - 15) / 3;
        } else if ((ratRNG >= 170) && (ratRNG < 175)) {
            ratATK = rng.nextInt(20) + (2 * (ratsKilled) - 15) / 3;
        } else if ((ratRNG >= 175) && (ratRNG < 180)) {
            ratATK = rng.nextInt(20) + (4 * (ratsKilled) - 15) / 3;
        } else if ((ratRNG >= 180) && (ratRNG < 185)) {
            ratATK = rng.nextInt(25) + (8 * (ratsKilled) - 20) / 3;
        } else if ((ratRNG >= 185) && (ratRNG < 190)) {
            ratATK = rng.nextInt(25) + (11 * (ratsKilled) - 20) / 3;
        } else if ((ratRNG >= 190) && (ratRNG < 195)) {
            ratATK = rng.nextInt(25) + (1 * (ratsKilled) - 20) / 3;
        } else if ((ratRNG >= 195) && (ratRNG < 200)) {
            ratATK = rng.nextInt(25) + (7 * (ratsKilled) - 20) / 3;
        } else if ((ratRNG >= 200) && (ratRNG < 205)) {
            ratATK = rng.nextInt(25) + (6 * (ratsKilled) - 20) / 3;
        } else if ((ratRNG >= 205) && (ratRNG < 210)) {
            ratATK = rng.nextInt(25) + (5 * (ratsKilled) - 20) / 3;
        } else if ((ratRNG >= 210) && (ratRNG < 215)) {
            ratATK = rng.nextInt(25) + (8 * (ratsKilled) - 15) / 3;
        } else if ((ratRNG >= 215) && (ratRNG < 220)) {
            ratATK = rng.nextInt(25) + (7 * (ratsKilled) - 15) / 3;
        } else if ((ratRNG >= 220) && (ratRNG < 225)) {
            ratATK = rng.nextInt(25) + (-1 * (ratsKilled) - 15) / 3;
        } else if ((ratRNG >= 225) && (ratRNG < 230)) {
            ratATK = rng.nextInt(25) + (8 * (ratsKilled) - 15) / 3;
        } else if ((ratRNG >= 230) && (ratRNG < 235)) {
            ratATK = rng.nextInt(30) + (10 * (ratsKilled) - 15) / 3;
        } else if ((ratRNG >= 235) && (ratRNG < 240)) {
            ratATK = rng.nextInt(30) + (2 * (ratsKilled) - 20) / 3;
        } else if ((ratRNG >= 240) && (ratRNG < 245)) {
            ratATK = rng.nextInt(30) + (7 * (ratsKilled) - 20) / 3;
        } else if ((ratRNG >= 245) && (ratRNG < 250)) {
            ratATK = rng.nextInt(30) + (7 * (ratsKilled) - 20) / 3;
        } else if ((ratRNG >= 250) && (ratRNG < 255)) {
            ratATK = rng.nextInt(30) + (9 * (ratsKilled) - 20) / 3;
        } else if ((ratRNG >= 255) && (ratRNG < 260)) {
            ratATK = rng.nextInt(30) + (9 * (ratsKilled) - 20) / 3;
        } else if ((ratRNG >= 260) && (ratRNG < 265)) {
            ratATK = rng.nextInt(30) + (8 * (ratsKilled) - 20) / 3;
        } else if ((ratRNG >= 265) && (ratRNG < 270)) {
            ratATK = rng.nextInt(30) + (8 * (ratsKilled) - 20) / 3;
        } else if ((ratRNG >= 270) && (ratRNG < 275)) {
            ratATK = rng.nextInt(30) + (5 * (ratsKilled) - 25) / 3;
        } else if ((ratRNG >= 275) && (ratRNG < 280)) {
            ratATK = rng.nextInt(35) + (8 * (ratsKilled) - 25) / 3;
        } else if ((ratRNG >= 280) && (ratRNG < 285)) {
            ratATK = rng.nextInt(35) + (13 * (ratsKilled) - 25) / 3;
        } else if ((ratRNG >= 285) && (ratRNG < 290)) {
            ratATK = rng.nextInt(1) + (0 * (ratsKilled)) / 3;
        } else if ((ratRNG >= 290) && (ratRNG < 295)) {
            ratATK = rng.nextInt(35) + (0 * (ratsKilled) - 25) / 3;
        } else if ((ratRNG >= 295) && (ratRNG < 300)) {
            ratATK = rng.nextInt(35) + (10 * (ratsKilled) - 25) / 3;
        } else if ((ratRNG >= 300) && (ratRNG < 305)) {
            ratATK = rng.nextInt(35) + (9 * (ratsKilled) - 25) / 3;
        } else if ((ratRNG >= 305) && (ratRNG < 310)) {
            ratATK = rng.nextInt(35) + (17 * (ratsKilled) - 25) / 3;
        } else if ((ratRNG >= 310) && (ratRNG < 315)) {
            ratATK = rng.nextInt(35) + (8 * (ratsKilled) - 25) / 3;
        } else if ((ratRNG >= 315) && (ratRNG < 320)) {
            ratATK = rng.nextInt(35) + (9 * (ratsKilled) - 25) / 3;
        } else if ((ratRNG >= 320) && (ratRNG < 325)) {
            ratATK = rng.nextInt(35) + (11 * (ratsKilled) - 25) / 3;
        } else if ((ratRNG >= 325) && (ratRNG < 400)) {
            ratATK = rng.nextInt(35) + (10 * (ratsKilled) - 25) / 3;
        } else if (ratRNG == -1) {
            ratATK = rng.nextInt(100) + (15 * ratsKilled) / 3;
        } else if (ratRNG == -2) {
            ratATK = rng.nextInt(169) + (8 * ratsKilled);
        } else {
            ratATK = rng.nextInt(10) + (3 * ratsKilled) / 4;
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
            return "pigeon ";
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
        } else if ((ratRNG >= 325) && (ratRNG < 400)) {
            return "Remy ";
        } else if (ratRNG == -1) {
            return "Emporer ";
        } else if (ratRNG == -2) {
            return "Last ";
        } else {
            return "";
        }
    }

    public void restart() {

        // Battle data
        playerWeapon = "Nothing";
        playerWeaponVerbForButton = "Attack";
        attackButton.setText("Attack");
        playerWeaponVerb = "attack";
        ratAttackDescription = "bites you";
        ratHP = 15;
        playerHP = 100;
        playerMaxHP = 100;
        playerMP = 10;
        playerMaxMP = 10;
        ratsKilled = 1;
        turn = 1;
        protectTurns = 0;
        mindfulnessTurns = 0;
        trapTurns = 0;
        playerGold = 0;
        ratGold = 0;
        potionCount = 0;
        mushroomCount = 0;
        tendyCount = 0;
        trapCount = 0;
        ownHat = false;
        ownPencil = false;
        ownPistol = false;
        ownRatStick = false;
        ownRatKing = false;

        healTab = false;
        inventoryOpen = false;
        spellTab = false;
        ownLick = false;
        ownLick2 = false;
        ownLick3 = false;
        spellTab = false;
        ownShabu = false;
        ownShabu2 = false;
        ownProtect = false;
        ownMindfulness = false;
        ownErupt = false;

        spellPanel.removeAll();

        healPanel.removeAll();

        mainTextArea.setText(
                "\nYou begin your journey to beat the rats again...\n\n\n\nA rat appears!\n\n\n\n\n\n\nWhat will you do?");

        ratRNG = 0;
        tryAgainButton.setVisible(false);
        attackButton.setVisible(true);
        spellButton.setVisible(true);
        healButton.setVisible(true);
        inventoryButton.setVisible(true);

        useHatButton.setVisible(false);
        usePencilButton.setVisible(false);
        usePistolButton.setVisible(false);
        useRatStickButton.setVisible(false);
        updateStats();

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
            case "Stab":
                attackRat();
                break;
            case "Bash":
                attackRat();
                break;
            case "Spells":
                spell();
                break;
            case "Heal":
                heal();
                break;
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
