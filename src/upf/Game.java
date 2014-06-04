package upf;

//Imports
import java.awt.*;

import javax.swing.*;

import upf.Menu.RunAI;

import java.awt.event.*;

public class Game implements ActionListener, KeyListener
{
	// Declarations
	Font menuFont = new Font("Dotum", Font.PLAIN, 20);///////////////////////////////
	Font score = new Font("Haettenschweiler", Font.PLAIN, 24);
	Font goalFont = new Font("Forte", Font.PLAIN, 90);
	Font winFont = new Font ("Forte", Font.PLAIN, 80);
	static Font countdownFont1 = new Font("Hobo STD", Font.PLAIN, 72);
	static Font countdownFont2 = new Font("Hobo STD", Font.PLAIN, 50);
	static JButton  toMenu;
	static JLabel sBackground, ball, goal, win, countdown, scoreRed, scoreBlue, insRightTop,
			insLeftTop, insRightBot, insLeftBot,scoreDash = new JLabel("-");;
	static JLabel[] player1 = new JLabel[10], player2 = new JLabel[10],bar = new JLabel[8];
	// blueKick = new JLabel[10], redKick = new JLabel[10];
	static int scoreCounter1 = 0, scoreCounter2 = 0;
	static PlayerMovement move = new PlayerMovement();
	AI bob = new AI();
	static boolean[] keys = new boolean[4];
	final static BallMovement bMove = new BallMovement();
	final static JLabel net1 = new JLabel(new ImageIcon("resources/goalnett.png"));
	final static JLabel net2 = new JLabel(new ImageIcon("resources/goalnetb.png"));

	
	public Game() {// Constructor (Output)
		UPF.f.addKeyListener(this);
		UPF.f.requestFocusInWindow();

		// Background
		sBackground = new JLabel(new ImageIcon("resources/gameBKG.jpg"));
		sBackground.setBounds(14, 46, 253, 445);
		
		//Return to menu//////////////////////////////////////////////////////////
		toMenu = new JButton ("Menu");
		toMenu.setFont(menuFont);
		toMenu.setBounds(183, 54, 80, 30);
		toMenu.addActionListener(this);
				
		// Instructions
		insRightTop = new JLabel(new ImageIcon("resources/insTopRight.png"));
		insLeftTop = new JLabel(new ImageIcon("resources/insTopLeft.png"));
		insRightBot = new JLabel(new ImageIcon("resources/insBotRight.png"));
		insLeftBot = new JLabel(new ImageIcon("resources/insBotLeft.png"));
		
		insRightTop.setBounds(143, 92, 120, 176);
		insLeftTop.setBounds(21, 92, 120, 176);
		insRightBot.setBounds(143, 271, 120, 176);
		insLeftBot.setBounds(21, 271, 120, 176);
		
		insRightTop.setVisible(false);
		insLeftTop.setVisible(false);
		insRightBot.setVisible(false);
		insLeftBot.setVisible(false);

		// Net
		net1.setBounds(100, 78, 82, 36);
		net2.setBounds(100, 424, 82, 36);

		// Bars
		ImageIcon barI = new ImageIcon("resources/bar.png");
		for (int i = 0; i < 4; i++) // //////////////////////////////Reiterates
									// 4 times instead of 8
		{
			bar[i * 2] = new JLabel(barI);
			bar[i * 2 + 1] = new JLabel(barI); // ///////////////////////////////
												// added in for compatibility
			bar[i * 2].setBounds(14, move.y1[i] + 2, 254, 5); // //////////////////////////
																// y1
			bar[i * 2 + 1].setBounds(14, move.y2[i] + 2, 254, 5); // //////////////////////////
																	// y2
		}

		// Goal
		goal = new JLabel("GOAL!", SwingConstants.CENTER);
		goal.setBounds(14, 230, 253, 90);
		goal.setFont(goalFont);
		goal.setVisible(false);
		
		//Win
		win = new JLabel("", SwingConstants.CENTER);
		win.setBounds(14, 150, 253, 200);                    
		win.setFont(winFont);                              
		win.setVisible(false); 
		
		
		// Start Countdown
		countdown = new JLabel("", SwingConstants.CENTER);
		countdown.setBounds(14, 230, 253, 72);
		countdown.setForeground(Color.yellow);
		countdown.setVisible(false);

		// Player1
		ImageIcon player;
		player = new ImageIcon("resources/player1.png");
		for (int i = 0; i != 10; i++)
		{
			player1[i] = new JLabel(player);
			player1[i].setSize(9, 9);// 12
		}

		// Player2
		player = new ImageIcon("resources/player2.png");
		for (int i = 0; i != 10; i++)
		{
			player2[i] = new JLabel(player);
			player2[i].setSize(9, 9);// 12
		}

		// Ball
		ball = new JLabel(new ImageIcon("resources/ball.png"));
		ball.setBounds(136, 265, 7, 7);
		ball.setVisible(false);

		// Score
		scoreDash.setForeground(Color.yellow);
		scoreDash.setFont(score);
		scoreBlue = new JLabel("0");
		scoreBlue.setForeground(Color.blue);
		scoreBlue.setFont(score);
		scoreRed = new JLabel("0");
		scoreRed.setForeground(Color.red);
		scoreRed.setFont(score);
		scoreBlue.setBounds(52, 51, 15, 24);
		scoreDash.setBounds(67, 51, 8, 24);
		scoreRed.setBounds(79, 51, 15, 24);

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
		UPF.lp.add(win, new Integer(6));
		UPF.lp.add(insRightTop, new Integer(6));
		UPF.lp.add(insLeftTop, new Integer(6));
		UPF.lp.add(insRightBot, new Integer(6));
		UPF.lp.add(insLeftBot, new Integer(6));
		UPF.lp.add(countdown, new Integer(6));
		UPF.lp.add(scoreDash, new Integer(6));
		UPF.lp.add(scoreBlue, new Integer(6));
		UPF.lp.add(scoreRed, new Integer(6));
		UPF.lp.add(toMenu, new Integer(6));///////////////////////////

		UPF.f.repaint();

		resetAll();
		new Thread(new RunGame()).start();
		new Thread(new MovePlayers()).start();
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
		if (e.getSource() == toMenu)////////////////////////////////////////////
		{
			RunAI.killAI();
			returnToMenu();
		}
	}

	public void keyPressed(KeyEvent e)
	{
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		} else if (k == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (k == KeyEvent.VK_A & Menu.gameMode)
		{
			keys[2] = true;
		} else if (k == KeyEvent.VK_D & Menu.gameMode)
		{
			keys[3] = true;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		} else if (k == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (k == KeyEvent.VK_A & Menu.gameMode)
		{
			keys[2] = false;
		} else if (k == KeyEvent.VK_D & Menu.gameMode)
		{
			keys[3] = false;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	public static void resetAll()
	{
		BallMovement.resetBall(true);
		move.resetPlayers();
	}
	public static void returnToMenu ()
	{
		remove();
		RunGame.killGame();
		MovePlayers.stopPlayers();
		scoreCounter1=0;
		scoreCounter2=0;
		Menu.bannerAd();
		new Menu();
		
	}
	
	public static void remove()
	{
		UPF.lp.remove(sBackground);
		UPF.lp.remove(net1);
		UPF.lp.remove(net2);
		for (int i = 0; i != 8; i++)
			UPF.lp.remove(bar[i]);
		for (int i = 0; i != 10; i++)
		{
			UPF.lp.remove(player1[i]);
			UPF.lp.remove(player2[i]);
		}
		UPF.lp.remove(ball);
		UPF.lp.remove(goal);
		UPF.lp.remove(win);
		UPF.lp.remove(insRightTop);
		UPF.lp.remove(insLeftTop);
		UPF.lp.remove(insRightBot);
		UPF.lp.remove(insLeftBot);
		UPF.lp.remove(countdown);
		UPF.lp.remove(scoreDash);
		UPF.lp.remove(scoreBlue);
		UPF.lp.remove(scoreRed);
		UPF.lp.remove(toMenu);
		UPF.f.repaint();
	}

	public static void instructions()
	{
		toMenu.setEnabled(false);
		if (Menu.gameMode)
		{
			insRightTop.setVisible(true);
			insLeftTop.setVisible(true);
		}
		insRightBot.setVisible(true);
		insLeftBot.setVisible(true);
		UPF.pause(1000);
		if (Menu.gameMode)
		{
			insRightTop.setVisible(false);
			insLeftTop.setVisible(false);
		}
		insRightBot.setVisible(false);
		insLeftBot.setVisible(false);
	}
	

	
	public static void goal(boolean colour, int score) // true = blue // false = red
	{
		ball.setVisible(false);
		if (colour) {
			scoreBlue.setText("" + score);
			goal.setForeground(Color.blue);
		} else {
			scoreRed.setText("" + score);
			goal.setForeground(Color.red);
		}
		toMenu.setEnabled(false);
		goal.setVisible(true);
		UPF.pause(2000);
		goal.setVisible(false);
		if (scoreCounter1 == 5 | scoreCounter2 == 5)////////////////////////////////////////////////////////////////// score here
			win (colour);
		else
			BallMovement.resetBall(false);
	}

	public static void win(boolean colour) 
	{
		RunAI.killAI();
		if (colour) {
			win.setForeground(Color.blue);
			win.setText("<html>BLUE<br>WINS</html>");
		} else {
			win.setForeground(Color.red);
			win.setText("<html>RED<br>WINS</html>");
		}
		toMenu.setEnabled(false);
		win.setVisible(true);
		UPF.pause(2000);
		returnToMenu();
	}
	
	public static class MovePlayers implements Runnable{
		static boolean movePlayers = true;
		public void run()
		{
			while (movePlayers)
			{
				UPF.pause(10);
				move.move();
			}
		}
		public static void stopPlayers(){
			movePlayers = false;
		}
	}
	public static class RunGame implements Runnable{
		static boolean runGame = true;
		public void run()
		{
			instructions();
			BallMovement.resetBall(false);
			
			while (runGame)
			{
				UPF.pause(43);
				bMove.updateBallPosition(ball, player1, player2, net1, net2);
			}
		}
		public static void killGame(){
			runGame=false;
		}
			
	}
}