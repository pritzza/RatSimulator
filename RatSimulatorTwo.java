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

public class RatSimulatorTwo{

    JFrame window;
    JTextArea mainTextArea;
    JPanel mainTextPanel, attackButtonPanel, statsPanel;
    JLabel playerHPLabel, ratHPLabel, ratsKilledLabel;
    Container con;
    JButton attackButton;
    int playerATK, ratHP, ratATK;

    int playerHP = 90;
    int ratsKilled = 0;
    int turn = 0;

    ChoiceHandler choiceHandler = new ChoiceHandler();
    Random rng = new Random();

    public static void main(final String[] args){

    new RatSimulatorTwo();

    }

    public RatSimulatorTwo(){

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
        mainTextArea.setText("richard");

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

    }


public void attackRat(){

    playerHP = playerHP + 25;
    int ratHP = rng.nextInt(16+5+ratsKilled);
    ratsKilled++;
    

    while(ratHP>0){

            int ratATK = rng.nextInt(3*ratsKilled+2+ratsKilled);
            int playerATK = rng.nextInt((5)*ratsKilled+5);

            turn++;

            playerHP = playerHP - ratATK;
            ratHP = ratHP - playerATK;

            mainTextArea.setText("\nYou attack the rat for "+playerATK+" damage!\n\nThe rat (#"+ratsKilled+") remains with "+ratHP+" HP.\n\n\n\nThe rat retaliates with "+ratATK+" damage!\n\nYou have "+playerHP+" HP now.\n\n\n\nTurn:"+turn);

    }

        while(playerHP < 0){

            playerATK = -1;
            mainTextArea.setText("\nYou died.");
            break;

        }
    }

public class ChoiceHandler implements ActionListener {

    public void actionPerformed(final ActionEvent event){
        
        attackRat();

        }
    }
}