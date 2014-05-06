package upf;

//Imports
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Menu implements ActionListener {
	// Declarations
	Font title = new Font("Elephant", Font.PLAIN, 42);
	Font buttonBig = new Font("Arial", Font.PLAIN, 40);
	Font buttonSmall = new Font("Arial", Font.PLAIN, 30);
	static JButton play, instructions;
	JFrame f = new JFrame("Multiplication Nation");

	public Menu() {// Constructor (Output)
		// Frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setBounds(200, 100, 285 + 6, 550 + 25);
		f.setLayout(null);

		// Layered pane
		JLayeredPane lp = new JLayeredPane();
		lp.setBounds(0, 0, 406, 522);

		// Background
		// JLabel background = new JLabel(UPF.phone);
		// background.setSize(285, 550);

		// Multiplication
		JLabel title1 = new JLabel("Multiplication");
		title1.setBounds(24, 63, 360, 42);
		title1.setFont(title);
		title1.setForeground(Color.WHITE);

		// Infestation
		JLabel title2 = new JLabel("Infestation");
		title2.setBounds(61, 108, 323, 42);
		title2.setFont(title);
		title2.setForeground(Color.WHITE);

		// Play button
		play = new JButton("PLAY");
		play.setFont(buttonBig);
		play.setBounds(65, 200, 270, 80);
		play.addActionListener(this);

		// Instructions button
		instructions = new JButton("INSTRUCTIONS");
		instructions.setFont(buttonSmall);
		instructions.setBounds(65, 300, 270, 80);
		instructions.addActionListener(this);

		// Adding
		// lp.add(background, new Integer(1));
		lp.add(title1, new Integer(2));
		lp.add(title2, new Integer(2));
		lp.add(play, new Integer(2));
		lp.add(instructions, new Integer(2));

		f.add(lp);
		f.setVisible(true);
	}

	// Input
	public void actionPerformed(ActionEvent e) {
		// Processing
		if (e.getSource() == instructions) {
			f.dispose();
			// Go to instructions
			// new Instructions();
		}
		if (e.getSource() == play) {
			f.dispose();
			// Start timer
			// Main.startTimer = System.currentTimeMillis();
			// Start Game
			// new Game();

		}

	}

}