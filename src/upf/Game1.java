package upf;

//Imports
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.net.URL;

public class Game1 implements ActionListener
{
	// Declarations
	Font title = new Font("Berlin Sans FB Demi", Font.PLAIN, 42);
	Font buttonBig = new Font("Eras Demi ITC", Font.PLAIN, 25);
	Font adFont = new Font("Arial", Font.PLAIN, 20);
	static JButton play1, play2, ad;

	public Game1() {// Constructor (Output)

		int[] x = { 80, 136, 188, 57, 110, 159, 210, 108, 163, 136 };
		int[] y = { 125, 155, 195, 235, 291, 330, 370, 402 };

		// Background
		JLabel sBackground = new JLabel(new ImageIcon("resources/gameBKG.jpg"));
		sBackground.setBounds(14, 46, 253, 445);

		// Net
		ImageIcon nett = new ImageIcon("resources/goalnett.png");
		JLabel net1 = new JLabel(nett);
		net1.setBounds(100, 78, 82, 36);
		ImageIcon netb = new ImageIcon("resources/goalnetb.png");
		JLabel net2 = new JLabel(netb);
		net2.setBounds(100, 424, 82, 36);

		// Bars
		ImageIcon bari = new ImageIcon("resources/bar.png");
		JLabel[] bar = new JLabel[8];
		for (int i = 0; i != 8; i++)
		{
			bar[i] = new JLabel(bari);
			bar[i].setBounds(14, y[i] + 2, 254, 5);
		}

		// Player1
		ImageIcon player;
		player = new ImageIcon("resources/player1.png");
		JLabel[] player1 = new JLabel[10];
		for (int i = 0; i != 10; i++)
		{
			player1[i] = new JLabel(player);
			player1[i].setSize(9, 9);
			if (i < 3)
				player1[i].setLocation(x[i], y[2]);
			else if (i < 7)
				player1[i].setLocation(x[i], y[4]);
			else if (i < 9)
				player1[i].setLocation(x[i], y[6]);
			else
				player1[i].setLocation(x[i], y[7]);
		}

		// Player2
		player = new ImageIcon("resources/player2.png");
		JLabel[] player2 = new JLabel[10];
		for (int i = 9; i != -1; i--)
		{
			player2[i] = new JLabel(player);
			player2[i].setSize(9, 9);
			if (i == 9)
				player2[i].setLocation(x[i], y[0]);
			else if (i > 6)
				player2[i].setLocation(x[i], y[1]);
			else if (i > 2)
				player2[i].setLocation(x[i], y[3]);
			else
				player2[i].setLocation(x[i], y[5]);
		}

		// Ad button
		UPF.ad.addActionListener(this);

		// Adding
		UPF.lp.add(sBackground, new Integer(1));
		UPF.lp.add(net1, new Integer(2));
		UPF.lp.add(net2, new Integer(2));
		for (int i = 0; i != 8; i++)
			UPF.lp.add(bar[i], new Integer(3));
		for (int i = 0; i != 10; i++)
		{
			UPF.lp.add(player1[i], new Integer(4));
			UPF.lp.add(player2[i], new Integer(4));
		}
		UPF.f.repaint();

	}

	// Input
	public void actionPerformed(ActionEvent e)
	{
		// Processing
		if (e.getSource() == play1)
		{
			UPF.f.dispose();

		}
		if (e.getSource() == UPF.ad)
		{
			UPF.f.dispose();
			try
			{
				// Desktop.getDesktop().browse(
				// new URL("http://omfgdogs.com/").toURI());
			} catch (Exception a)
			{
			}
		}
	}
}