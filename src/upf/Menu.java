package upf;

//Imports
import java.awt.*;

import javax.swing.*;

import upf.Game.MovePlayers;
import upf.Game.RunGame;

import java.awt.event.*;

public class Menu implements ActionListener
{
	// Declarations
	Font title = new Font("Berlin Sans FB Demi", Font.PLAIN, 42);
	Font button = new Font("Eras Demi ITC", Font.PLAIN, 25);// 25
	static AI bob = new AI();
	static JLabel sBackground;
	static JLabel title1, title2, title3, title1Shade, title2Shade,
			title3Shade;
	static JButton play1, play2, easy, hard, back, bannerAd;
	static volatile boolean runAI = true;
	static boolean gameMode; // true = 2 player , false = single player
	static JInternalFrame advertisement;

	static boolean difficulty;

	public Menu() {// Constructor (Output)

		// Background
		sBackground = new JLabel(new ImageIcon("resources//menuBKG.jpg"));
		sBackground.setBounds(14, 46, 253, 445);

		// Ultimate
		title1 = new JLabel("Ultimate", SwingConstants.CENTER);
		title1.setBounds(14, 75, 253, 42);
		title1.setFont(title);
		title1.setForeground(Color.WHITE);

		title1Shade = new JLabel("Ultimate", SwingConstants.CENTER);
		title1Shade.setBounds(4, 85, 253, 42);
		title1Shade.setFont(title);
		title1Shade.setForeground(new Color(0, 0, 0, 150));

		// Pocket
		title2 = new JLabel("Pocket", SwingConstants.CENTER);
		title2.setBounds(14, 125, 253, 42);
		title2.setFont(title);
		title2.setForeground(Color.WHITE);

		title2Shade = new JLabel("Pocket", SwingConstants.CENTER);
		title2Shade.setBounds(4, 135, 253, 42);
		title2Shade.setFont(title);
		title2Shade.setForeground(new Color(0, 0, 0, 150));

		// Foosball
		title3 = new JLabel("Foosball", SwingConstants.CENTER);
		title3.setBounds(14, 175, 253, 42);
		title3.setFont(title);
		title3.setForeground(Color.WHITE);

		title3Shade = new JLabel("Foosball", SwingConstants.CENTER);
		title3Shade.setBounds(4, 185, 253, 42);
		title3Shade.setFont(title);
		title3Shade.setForeground(new Color(0, 0, 0, 150));

		// Single Player button
		play1 = new JButton("SINGLE PLAYER");
		play1.setFont(button);
		play1.setBounds(24, 250, 233, 40);
		play1.addActionListener(this);

		// 2 Player Button
		play2 = new JButton("2 PLAYER");
		play2.setFont(button);
		play2.setBounds(24, 310, 233, 40);
		play2.addActionListener(this);

		// Difficulty
		// Easy
		easy = new JButton("EASY");
		easy.setFont(button);
		easy.setBounds(24, 250, 233, 40);
		easy.addActionListener(this);
		easy.setVisible(false);

		// Normal
		hard = new JButton("HARD");
		hard.setFont(button);
		hard.setBounds(24, 310, 233, 40);
		hard.addActionListener(this);
		hard.setVisible(false);

		// Back
		back = new JButton("BACK");
		back.setFont(button);
		back.setBounds(24, 370, 233, 40);
		back.addActionListener(this);
		back.setVisible(false);

		// Ad button
		UPF.ad.addActionListener(this);

		// Adding
		UPF.lp.add(sBackground, new Integer(0));
		UPF.lp.add(title1, new Integer(2));
		UPF.lp.add(title2, new Integer(2));
		UPF.lp.add(title3, new Integer(2));
		UPF.lp.add(title1Shade, new Integer(1));
		UPF.lp.add(title2Shade, new Integer(1));
		UPF.lp.add(title3Shade, new Integer(1));
		UPF.lp.add(play1, new Integer(2));
		UPF.lp.add(play2, new Integer(2));
		UPF.lp.add(easy, new Integer(2));
		UPF.lp.add(hard, new Integer(2));
		UPF.lp.add(back, new Integer(2));

		UPF.f.repaint();
	}

	public void remove()
	{
		UPF.lp.remove(sBackground);
		UPF.lp.remove(title1);
		UPF.lp.remove(title2);
		UPF.lp.remove(title3);
		UPF.lp.remove(title1Shade);
		UPF.lp.remove(title2Shade);
		UPF.lp.remove(title3Shade);
		UPF.lp.remove(play1);
		UPF.lp.remove(play2);
		UPF.lp.remove(easy);
		UPF.lp.remove(hard);
		UPF.lp.remove(back);
		UPF.f.repaint();
		UPF.ad.removeActionListener(this);
	}

	// Input
	public void actionPerformed(ActionEvent e)
	{
		// Processing
		if (e.getSource() == play1)
		{
			gameMode = false;
			play1.setVisible(false);
			play2.setVisible(false);
			easy.setVisible(true);
			hard.setVisible(true);
			back.setVisible(true);
		} else if (e.getSource() == play2)
		{
			gameMode = true;
			play();
		}

		if (e.getSource() == easy)
		{
			difficulty = true;
			play();
		} else if (e.getSource() == hard)
		{
			difficulty = false;
			play();
		} else if (e.getSource() == back)
		{
			play1.setVisible(true);
			play2.setVisible(true);
			easy.setVisible(false);
			hard.setVisible(false);
			back.setVisible(false);
		}
		if (e.getSource() == UPF.ad)
		{
			UPF.link();
		}

	}

	public void play()
	{
		remove();
		new Game();
		RunGame.runGame = true;
		MovePlayers.movePlayers = true;
		// BallMovement.resetBall();
		if (!gameMode)
		{
			runAI = true;
			new Thread(new RunAI()).start();
		}
	}

	public static void bannerAd()
	{
		advertisement = new JInternalFrame("", false, true, false, false);
		bannerAd = new JButton("advertisement");

		advertisement.setBounds(14, 46, 253, 445);
		bannerAd.setBounds(0, 0, 243, 412);// 5,28

		advertisement.setVisible(true);

		advertisement.add(bannerAd);
		bannerAd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aActionEvent)
			{
				UPF.link();
			}});
		UPF.lp.add(advertisement, new Integer(7));
	}

	public static class RunAI implements Runnable
	{
		public void run()
		{
			while (runAI)
			{
				UPF.pause(10);
				if (difficulty == true)
					bob.moveRandom(Game.player2);
				else
					bob.move(Game.player2);
			}
		}

		public static void killAI()
		{
			runAI = false;
		}
	}
}