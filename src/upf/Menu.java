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
	Font buttonBig = new Font("Eras Demi ITC", Font.PLAIN, 25);
	Font adFont = new Font("Arial", Font.PLAIN, 20);
	static JButton play1, play2, ad;
	JFrame f = new JFrame("Ultimate Pocket Foosball");

	public Menu() {// Constructor (Output)

		// Frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setBounds(200, 100, 280 + 6, 550 + 25);
		f.setLayout(null);

		// Layered pane
		JLayeredPane lp = new JLayeredPane();
		lp.setBounds(0, 0, 280, 550);

		// Background
		JLabel pBackground = new JLabel(UPF.phone);
		pBackground.setSize(280, 550);
		JLabel sBackground = new JLabel(new ImageIcon("resources/menuBKG.jpg"));
		sBackground.setBounds(14, 46, 253, 445);

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

		// Adding
		lp.add(pBackground, new Integer(1));
		lp.add(sBackground, new Integer(1));
		lp.add(title1, new Integer(2));
		lp.add(title2, new Integer(2));
		lp.add(title3, new Integer(2));
		lp.add(play1, new Integer(2));
		lp.add(play2, new Integer(2));
		lp.add(ad, new Integer(2));

		f.add(lp);
		f.setVisible(true);
	}

	// Input
	public void actionPerformed(ActionEvent e)
	{
		// Processing
		if (e.getSource() == play1)
		{
			f.dispose();

		}
		if (e.getSource() == play2)
		{
			try
			{
				Desktop.getDesktop().browse(
						new URL("http://heyyeyaaeyaaaeyaeyaa.com/").toURI());
			} catch (Exception a)
			{
			}
		}
		if (e.getSource() == ad)
		{
			try
			{
				Desktop.getDesktop().browse(
						new URL("http://www.theuselessweb.com/").toURI());
			} catch (Exception a)
			{
			}
		}

	}
}