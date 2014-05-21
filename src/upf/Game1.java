package upf;

//Imports
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class Game1 implements ActionListener, KeyListener
{
	// Declarations
	Font title = new Font("Berlin Sans FB Demi", Font.PLAIN, 42);
	Font buttonBig = new Font("Eras Demi ITC", Font.PLAIN, 25);
	Font adFont = new Font("Arial", Font.PLAIN, 20);
	static JButton play1, play2, ad;
	static JLabel ball;
	static JLabel[] player1 = new JLabel[10], player2 = new JLabel[10];
	PlayerMovement move = new PlayerMovement();
    static boolean[] keys = new boolean [4];

	public Game1() {// Constructor (Output)
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
		for (int i = 0; i != 8; i++)
		{
			bar[i] = new JLabel(bari);
			bar[i].setBounds(14, move.y[i] + 2, 254, 5);
		}

		// Player1
		ImageIcon player;
		player = new ImageIcon("resources/player1.png");
		for (int i = 0; i != 10; i++)
		{
			player1[i] = new JLabel(player);
			player1[i].setSize(9, 9);
			move.player1Position(i);
		}

		// Player2
		player = new ImageIcon("resources/player2.png");
		for (int i = 0; i != 10; i++)
		{
			player2[i] = new JLabel(player);
			player2[i].setSize(9, 9);
			move.player2Position(i);
		}

		//Ball
		ImageIcon soccer = new ImageIcon("resources/ball.png");
		ball = new JLabel (soccer);
		ball.setBounds(136,265,7,7);

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
		UPF.lp.add(ball,new Integer (3));
		
		UPF.f.repaint();
		final BallMovement bMove = new BallMovement();
		new Thread (new Runnable(){
			@Override
			public void run(){
				bMove.resetBall();
				while (true){
					try {Thread.sleep(43);}
					catch (InterruptedException e){};
				bMove.updateBallPosition(ball, player1, player2, net1, net2);
				}
			}
		}).start();
		new Thread (new Runnable(){
            public void run(){
                    while (true){
                            try
                    {
                        Thread.sleep (10);
                    }
                    catch (Exception e)
                    {
                    }
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
            if (k == KeyEvent.VK_A)
            {
                    keys[2]=true;
            }
            else if (k == KeyEvent.VK_D)
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
            if (k == KeyEvent.VK_A)
            {
                    keys[2]=false;
            }
            else if (k == KeyEvent.VK_D)
            {
                    keys[3]=false;
            }
            
    }
	
	public void keyTyped(KeyEvent e)
	{
	}
}