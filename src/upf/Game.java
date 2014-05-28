package upf;

//Imports
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class Game implements ActionListener, KeyListener
{
	// Declarations
    Font goalFont = new Font("Forte", Font.PLAIN, 90);
    Font adFont = new Font("Arial", Font.PLAIN, 20);
    Font score = new Font("Haettenschweiler", Font.PLAIN,24);
    static Font countdownFont1 = new Font("Hobo STD", Font.PLAIN, 72);
    static Font countdownFont2 = new Font("Hobo STD", Font.PLAIN, 50);
    static JButton play1, play2, ad;
    static JLabel ball, goal, countdown,scoreRed,scoreBlue;
    static JLabel[] player1 = new JLabel[10], player2 = new JLabel[10],
                    blueKick = new JLabel[10], redKick = new JLabel[10];
    static int scoreCounter1 = 0, scoreCounter2 = 0;
    static ImageIcon playerB = new ImageIcon("resources/player1.png"),
                    playerR = new ImageIcon("resources/player2.png");
    PlayerMovement move = new PlayerMovement();
	AI bob = new AI();
    static boolean[] keys = new boolean [4];

	public Game() {// Constructor (Output)
		UPF.f.addKeyListener(this);
		UPF.f.requestFocusInWindow();
		

		// Background
		JLabel sBackground = new JLabel(new ImageIcon("resources/gameBKG.jpg"));
		sBackground.setBounds(14, 46, 253, 445);

		// Net
		ImageIcon nett = new ImageIcon("resources/goalnett.png");
		final JLabel net1 = new JLabel(nett);
		net1.setBounds(100, 78, 82, 36);
		ImageIcon netb = new ImageIcon("resources/goalnetb.png");
		final JLabel net2 = new JLabel(netb);
		net2.setBounds(100, 424, 82, 36);

		// Bars
		ImageIcon bari = new ImageIcon("resources/bar.png");
		JLabel[] bar = new JLabel[8];
		for (int i = 0; i < 4; i++) ////////////////////////////////Reiterates 4 times instead of 8
		{
			bar[i*2] = new JLabel(bari);
			bar [i*2+1] = new JLabel (bari); ///////////////////////////////// added in for compatibility
			bar[i*2].setBounds(14, move.y1[i] + 2, 254, 5); //////////////////////////// y1
			bar[i*2+1].setBounds(14, move.y2[i] + 2, 254, 5); //////////////////////////// y2
		}
		
        // Goal
        goal = new JLabel("GOAL!", SwingConstants.CENTER);
        goal.setBounds(14, 230, 253, 90);
        goal.setFont(goalFont);
        goal.setVisible(false);

        // Start Countdown
        countdown = new JLabel("",SwingConstants.CENTER);
        countdown.setBounds(14, 230, 253, 72);
        countdown.setForeground(Color.yellow);
        countdown.setVisible(false);

		// Player1
		ImageIcon player;
		player = new ImageIcon("resources/player1.png");
		for (int i = 0; i != 10; i++)
		{
			player1[i] = new JLabel(player);
			player1[i].setSize(9, 12);
			move.player1Position(i);
		}

		// Player2
		player = new ImageIcon("resources/player2.png");
		for (int i = 0; i != 10; i++)
		{
			player2[i] = new JLabel(player);
			player2[i].setSize(9, 12);
			move.player2Position(i);
		}
		
        // Ball
        ImageIcon soccer = new ImageIcon("resources/ball.png");
        ball = new JLabel(soccer);
        ball.setBounds(136, 265, 7, 7);
        ball.setVisible(false);
        
        //Score
        JLabel scoreDash = new JLabel ("-");
        scoreDash.setForeground(Color.yellow);
        scoreDash.setFont(score);
        scoreBlue = new JLabel("0");
        scoreBlue.setForeground(Color.blue);
        scoreBlue.setFont(score);
        scoreRed = new JLabel("0");
        scoreRed.setForeground(Color.red);
        scoreRed.setFont(score);
        scoreBlue.setBounds(52,51,15,24);
        scoreDash.setBounds(67,51,8,24);
        scoreRed.setBounds(79,51,15,24);

		// Ad button
		UPF.ad.addActionListener(this);


        // Adding
        UPF.lp.add(sBackground, new Integer(1));
        UPF.lp.add(net1, new Integer(2));
        UPF.lp.add(net2, new Integer(2));
        for (int i = 0; i != 8; i++)
                UPF.lp.add(bar[i], new Integer(4));
        for (int i = 0; i != 10; i++)
        {
                UPF.lp.add(player1[i], new Integer(5));
                UPF.lp.add(player2[i], new Integer(5));
        }
        UPF.lp.add(ball, new Integer(3));
        UPF.lp.add(goal, new Integer(6));
        UPF.lp.add(countdown, new Integer(6));
        UPF.lp.add(scoreDash,new Integer(6));
        UPF.lp.add(scoreBlue,new Integer(6));
        UPF.lp.add(scoreRed,new Integer(6));

        UPF.f.repaint();

        final BallMovement bMove = new BallMovement();
        new Thread(new Runnable() {
                @Override
                public void run()
                {
                        bMove.resetBall();
                        while (true)
                        {
                                UPF.pause(43);
                                bMove.updateBallPosition(ball, player1, player2, net1, net2);
                        }
                }
        }).start();
        new Thread(new Runnable() {
                public void run()
                {
                        while (true)
                        {
                                UPF.pause(10);
                                move.move();
                        }
                }
        }).start();
}

	// Input
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == UPF.ad)
		{
		System.exit(0);
			try
			{
			} catch (Exception a)
			{
			}
		}
	}

    public void keyPressed(KeyEvent e)
    {
            int k = e.getKeyCode();
            if (k == KeyEvent.VK_LEFT)
            {
                    keys[0]=true;
            }
            else if (k == KeyEvent.VK_RIGHT)
            {
                    keys[1]=true;
            }
            if (k == KeyEvent.VK_A & Menu.gameMode)
            {
                    keys[2]=true;
            }
            else if (k == KeyEvent.VK_D & Menu.gameMode)
            {
                    keys[3]=true;
            }
    }

    public void keyReleased(KeyEvent e)
    {
            int k = e.getKeyCode();
            if (k == KeyEvent.VK_LEFT)
            {
                    keys[0]=false;
            }
            else if (k == KeyEvent.VK_RIGHT)
            {
                    keys[1]=false;
            }
            if (k == KeyEvent.VK_A& Menu.gameMode)
            {
                    keys[2]=false;
            }
            else if (k == KeyEvent.VK_D& Menu.gameMode)
            {
                    keys[3]=false;
            }
            
    }
	
	public void keyTyped(KeyEvent e)
	{
	}
}