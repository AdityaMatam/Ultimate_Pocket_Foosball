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
		
		// Background
		JLabel sBackground = new JLabel(new ImageIcon("resources/gameBKG.jpg"));
		sBackground.setBounds(14, 46, 253, 445);

		//Net
		ImageIcon nett = new ImageIcon("resources/goalnett.png");
		JLabel net1 = new JLabel(nett);
		net1.setBounds(100,78,82,36);
		ImageIcon netb = new ImageIcon("resources/goalnetb.png");
		JLabel net2 = new JLabel(netb);
		net2.setBounds(100,424,82,36);

		
		/*
		// Ultimate
		JLabel title1 = new JLabel("Ultimate", SwingConstants.CENTER);
		title1.setBounds(14, 75, 253, 42);
		title1.setFont(title);
		title1.setForeground(Color.WHITE);

		// Pocket
		JLabel title2 = new JLabel("Pocket", SwingConstants.CENTER);
		title2.setBounds(14, 125, 253, 42);
		title2.setFont(title);
		title2.setForeground(Color.WHITE);

		// Foosball
		JLabel title3 = new JLabel("Foosball", SwingConstants.CENTER);
		title3.setBounds(14, 175, 253, 42);
		title3.setFont(title);
		title3.setForeground(Color.WHITE);

		// Single Player button
		play1 = new JButton("SINGLE PLAYER");
		play1.setFont(buttonBig);
		play1.setBounds(24, 250, 233, 40);
		play1.addActionListener(this);

		// 2 Player Button
		play2 = new JButton("2 PLAYER (wip)");
		play2.setFont(buttonBig);
		play2.setBounds(24, 310, 233, 40);
		play2.addActionListener(this);

		// AD Button
		ad = new JButton("advertisement");
		ad.setFont(adFont);
		ad.setBounds(14, 452, 253, 40);
		ad.addActionListener(this);
		*/
		// Adding
		UPF.lp.add(sBackground, new Integer(1));
		UPF.lp.add(net1, new Integer(2));
		UPF.lp.add(net2, new Integer(2));
		
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
			try
			{
				Desktop.getDesktop().browse(
						new URL("http://omfgdogs.com/").toURI());
			} catch (Exception a)
			{
			}
		}
	}
}