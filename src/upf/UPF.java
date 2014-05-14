package upf;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class UPF{

	//static ImageIcon phone = new ImageIcon("resources/phone.png");
	static JFrame f = new JFrame("Ultimate Pocket Foosball");
	static JLayeredPane lp = new JLayeredPane();
	static JButton ad = new JButton("advertisement");
	static Font adFont = new Font("Arial", Font.PLAIN, 20);
	
	public static void main(String[] args) {
		ImageIcon phone = new ImageIcon("resources/phone.png");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		UPF.f.setFocusable(true);
		f.setBounds(200, 100, 280 + 6, 550 + 25);
		f.setLayout(null);
		
		lp.setFocusable(true);
		// Phone
		JLabel pBackground = new JLabel(phone);
		pBackground.setSize(280, 550);
		
		// AD Button
		ad = new JButton("advertisement");
		ad.setFont(adFont);
		ad.setBounds(14, 452, 253, 40);
		
		// Layered pane
		lp.setBounds(0, 0, 280, 550);
		lp.add(pBackground, new Integer(4));
		UPF.lp.add(ad, new Integer(2));
		
		UPF.f.add(UPF.lp);
		UPF.f.setVisible(true);
		
		new Menu();
		
		
	}
	
	
}
