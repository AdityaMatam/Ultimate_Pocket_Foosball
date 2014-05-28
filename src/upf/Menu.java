package upf;

//Imports
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;

public class Menu implements ActionListener
{
	// Declarations
	Font title = new Font("Berlin Sans FB Demi", Font.PLAIN, 42);
	Font buttonBig = new Font("Eras Demi ITC", Font.PLAIN, 20);// 25
	AI bob = new AI();
	static JLabel sBackground;
	static JLabel title1, title2, title3 = new JLabel("Foosball",
			SwingConstants.CENTER);
	static JButton play1, play2;
	static boolean gameMode; 

	public Menu() {// Constructor (Output)

		// Background
		sBackground = new JLabel(new ImageIcon("resources/menuBKG.jpg"));
		sBackground.setBounds(14, 46, 253, 445);

		// Ultimate
		title1 = new JLabel("Ultimate", SwingConstants.CENTER);
		title1.setBounds(14, 75, 253, 42);
		title1.setFont(title);
		title1.setForeground(Color.WHITE);

		// Pocket
		title2 = new JLabel("Pocket", SwingConstants.CENTER);
		title2.setBounds(14, 125, 253, 42);
		title2.setFont(title);
		title2.setForeground(Color.WHITE);

		// Foosball
		title3 = new JLabel("Foosball", SwingConstants.CENTER);
		title3.setBounds(14, 175, 253, 42);
		title3.setFont(title);
		title3.setForeground(Color.WHITE);

		// Single Player button
		play1 = new JButton("SINGLE PLAYER (WIP)");
		play1.setFont(buttonBig);
		play1.setBounds(24, 250, 233, 40);
		play1.addActionListener(this);

		// 2 Player Button
		play2 = new JButton("2 PLAYER");
		play2.setFont(buttonBig);
		play2.setBounds(24, 310, 233, 40);
		play2.addActionListener(this);

		// Ad button
		UPF.ad.addActionListener(this);

		// Adding
		UPF.lp.add(sBackground, new Integer(1));
		UPF.lp.add(title1, new Integer(2));
		UPF.lp.add(title2, new Integer(2));
		UPF.lp.add(title3, new Integer(2));
		UPF.lp.add(play1, new Integer(2));
		UPF.lp.add(play2, new Integer(2));
		
		UPF.f.repaint();
	}

	public void remove()
	{
		UPF.lp.remove(sBackground);
		UPF.lp.remove(title1);
		UPF.lp.remove(title2);
		UPF.lp.remove(title3);
		UPF.lp.remove(play1);
		UPF.lp.remove(play2);
		UPF.f.repaint();
		UPF.ad.removeActionListener(this);
	}

	// Input
	public void actionPerformed(ActionEvent e)
	{
		// Processing
		if (e.getSource() == play1)
		{
			remove();
			gameMode = false;
			new Game();
			new Thread (new Runnable(){
		        public void run(){
		                while (true){
		                        
		               UPF.pause(20);
		                        bob.move(Game.player2);
		                }
		        }
		}).start();
		}
		if (e.getSource() == play2)
		{
			remove();
			gameMode = true;
			new Game();
		}
		if (e.getSource() == UPF.ad)
		{
			try
			{
				Desktop.getDesktop().browse(
						new URL("http://heyyeyaaeyaaaeyaeyaa.com/").toURI());
			} catch (Exception a)
			{
			}
		}

	}
}