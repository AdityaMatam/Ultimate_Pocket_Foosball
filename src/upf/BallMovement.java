package upf;

import java.awt.Color;
import java.awt.geom.*;

import javax.swing.JLabel;

import upf.Menu.RunAI;

public class BallMovement {
	static int xC = 140, yC = 266, yV = 0, xV = 0;
	boolean test = true;
	Ellipse2D.Double b;
	Ellipse2D.Double[] p1 = new Ellipse2D.Double[10];
	Ellipse2D.Double[] p2 = new Ellipse2D.Double[10];

	public void updateBallPosition(JLabel ball, JLabel[] player1,
			JLabel[] player2, JLabel net1, JLabel net2) {
		xC += xV;
		yC += yV;
		b = new Ellipse2D.Double(xC, yC, 7, 7);
		Rectangle2D.Double[] n1 = createNet(net1);
		Rectangle2D.Double[] n2 = createNet(net2);
		for (int x = 0; x < 10; x++) {
			p1[x] = new Ellipse2D.Double(player1[x].getX(), player1[x].getY(),
					player1[x].getWidth(), player1[x].getHeight());
			p2[x] = new Ellipse2D.Double(player2[x].getX(), player2[x].getY(),
					player2[x].getWidth(), player2[x].getHeight());
		}
		detectCollision(n1, n2);
		ball.setLocation(xC, yC);
	}

	public Rectangle2D.Double[] createNet(JLabel net) {
		Rectangle2D.Double[] output = {
				new Rectangle2D.Double(net.getX() - 1, net.getY(), 1,
						net.getHeight()),
				new Rectangle2D.Double((net.getX() + (net.getWidth())),
						net.getY(), 1, net.getHeight()),
				new Rectangle2D.Double((net.getX()), net.getY(),
						(net.getWidth()), net.getHeight()) };
		return output;
	}

	public void detectCollision(Rectangle2D.Double[] n1, Rectangle2D.Double[] n2) {

		if (xC < 20 && xV < 0) {
			xV = -xV;
		} else if (xC > 252 && xV > 0) {
			xV = -xV;
		} else if (yC < 89 && yV < 0) {
			yV = -yV;
		} else if (yC > 440 && yV > 0) {
			yV = -yV;
		}
		if (b.intersects(n1[0])) {
			// left net rectangle
			xV = -xV;
		} else if (b.intersects(n1[1])) {
			// right net rectangle
			xV = -xV;
		} else if (b.intersects(n1[2])) {
			// goal p1
			Game.scoreCounter1 += 1;
			// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// red team
			goal(true, Game.scoreCounter1);

			// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// resetBall(false);
		}
		if (b.intersects(n2[0])) {
			// left net rectangle
			xV = -xV;
		} else if (b.intersects(n2[1])) {
			// right net rectangle
			xV = -xV;
		} else if (b.intersects(n2[2])) {
			// goal p2
			Game.scoreCounter2 += 1;
			// //////////////////////////////////////////////////////////////////////////
			// //////////////////////////////////////////////////////////////////////////////blue
			// team
			goal(false, Game.scoreCounter2);
			// ///////////////////////////////////////////////////////////////////////////
			// /////////////////////////////////////////////////////////////////////////////
			// resetBall(false);
		}
		for (int x = 0; x < 10; x++) {
			Rectangle2D.Double[] boundingRectp1 = new Rectangle2D.Double[5];
			boundingRectp1 = createBoundingRect(p1[x]);
			Rectangle2D.Double[] boundingRectp2 = new Rectangle2D.Double[5];
			boundingRectp2 = createBoundingRect(p2[x]);
			int test = 1;
			if (b.intersects(boundingRectp1[2])) {
				// Center-most rectangle for player 1
				if (yV >= 0)
					yV = -yV;
			} else if (b.intersects(boundingRectp1[0])) {
				// Outer-left rectangle for player 1
				if (xV > 1 && (yV >= 1 && yV < 8)) {
					xV -= test;
					yV += test;
				} else if (xV <= -1 && (yV > 1 && yV <= 8)) {
					xV -= test;
					yV -= test;
				} else if (yV <= 0) {
					if (xV < -1) {
						xV += test;
						yV -= test;
					}
					if (xV > 1) {
						xV -= test;
						yV -= test;
					}
				}
				if (yV >= 0) {
					yV = -yV;
				}

			} else if (b.intersects(boundingRectp1[1])) {
				// Outer-right rectangle for player 1
				if (xV < -1 && (yV >= 1 && yV < 8)) {
					xV += test;
					yV += test;
				} else if (xV >= 1 && (yV > 1 && yV <= 8)) {
					xV += test;
					yV -= test;
				} else if (yV <= 0) {
					if (xV < -1) {
						xV += test;
						yV -= test;
					}
					if (xV > 1) {
						xV -= test;
						yV -= test;
					}
				}
				if (yV >= 0) {
					yV = -yV;

				}

			} else if (b.intersects(boundingRectp2[2])) {
				// Center-most rectangle for player 2
				if (yV <= 0)
					yV = -yV;

			} else if (b.intersects(boundingRectp2[0])) {
				// Outer-left rectangle for player 2
				if (xV > 1 && (yV < -1 && yV >= -8)) {
					xV -= test;
					yV -= test;
				} else if (xV <= -1 && (yV <= -1 && yV > -8)) {
					xV -= test;
					yV += test;
				} else if (yV >= 0) {
					if (xV < -1) {
						xV += test;
						yV += test;
					}
					if (xV > 1) {
						xV -= test;
						yV += test;
					}
				}
				if (yV <= 0) {

					yV = -yV;

				}

			}

			else if (b.intersects(boundingRectp2[1])) {
				// Outer-right rectangle for player 2
				if (xV < -1 && (yV <= -1 && yV > -8)) {
					xV += test;
					yV -= test;
				} else if (xV > 1 && (yV < -1 && yV >= -8)) {
					xV += test;
					yV += test;
				} else if (yV >= 0) {
					if (xV < -1) {
						xV += test;
						yV += test;
					}
					if (xV > 1) {
						xV -= test;
						yV += test;
					}
				}
				if (yV <= 0) {
					yV = -yV;
				}
			}
		}
	}

	public Rectangle2D.Double[] createBoundingRect(Ellipse2D.Double player) {
		Rectangle2D.Double[] rect = new Rectangle2D.Double[3];
		rect[0] = new Rectangle2D.Double(player.getX(), player.getY(), 3,
				player.height);
		rect[1] = new Rectangle2D.Double((player.getX() + 4), player.getY(), 3,
				player.height);
		rect[2] = new Rectangle2D.Double((player.getX() + 3), player.getY(), 1,
				player.height);
		return rect;
	}

	/*
	 * public void resetPlayers() { PlayerMovement move = new PlayerMovement();
	 * move.x1 = move.xStart; move.x2 = move.xStart; for (int i = 0; i != 10;
	 * i++) { move.player1Position(i); move.player2Position(i); } }
	 */
	public void resetBall(boolean global) {
		// ////////////////////////////////////////////////////////////////////////////////////

		if (!global) {
			Game.countdown.setVisible(true);
			for (int i = 3; i != 0; i--) {
				Game.countdown.setText("" + i);
				Game.countdown.setFont(Game.countdownFont1);
				UPF.pause(750);
				Game.countdown.setFont(Game.countdownFont2);
				UPF.pause(250);
			}
			Game.countdown.setVisible(false);
			Game.toMenu.setEnabled(true);
			Game.ball.setVisible(true);

			// ////////////////////////////////////////////////////////////////////////////////////
		}
		xV = 0;
		yV = 0;
		while (xV == 0) {
			xV = (int) (Math.random() * 14 - 7);
			if (Math.random() < 0.5) {
				yV = 8 - Math.abs(xV);
			} else {
				yV = -(8 - Math.abs(xV));
			}
		}
		xC = 140;
		yC = 266;
	}

	// ///////////////////////////////////////////////////////////////////////////
	/*
	 * public void kick(int y) { if (y >= 125-2 & y <= 125+7) new Thread(new
	 * Runnable() { public void run() { kickAnim(1, 2, false, 1); } }).start();
	 * else if (y >= 155-2 & y <= 155+7) new Thread(new Runnable() { public void
	 * run() { kickAnim(2, 4, false, 2); } }).start(); else if (y >= 195-2 & y
	 * <= 195+7) new Thread(new Runnable() { public void run() { kickAnim(10, 7,
	 * true, 3); } }).start(); else if (y >= 235-2 & y <= 235+7) new Thread(new
	 * Runnable() { public void run() { kickAnim(4, 8, false, 4); } }).start();
	 * else if (y >= 291-2 & y <= 291+7) new Thread(new Runnable() { public void
	 * run() { kickAnim(7, 3, true, 5); } }).start(); else if (y >= 330-2 & y <=
	 * 330+7) new Thread(new Runnable() { public void run() { kickAnim(8, 11,
	 * false, 6); } }).start(); else if (y >= 370-2 & y <= 370+7) new Thread(new
	 * Runnable() { public void run() { kickAnim(3, 1, true, 7); } }).start();
	 * else if (y >= 402-2 & y <= 402+7) new Thread(new Runnable() { public void
	 * run() { kickAnim(1, 0, true, 8); } }).start(); /* switch (row) { case 1:
	 * new Thread(new Runnable() { public void run() { kickAnim(1, 2, false, 1);
	 * } }).start(); break; case 2: new Thread(new Runnable() { public void
	 * run() { kickAnim(2, 4, false, 2); } }).start(); break; case 3: new
	 * Thread(new Runnable() { public void run() { kickAnim(10, 7, true, 3); }
	 * }).start(); break; case 4: new Thread(new Runnable() { public void run()
	 * { kickAnim(4, 8, false, 4); } }).start(); break; case 5: new Thread(new
	 * Runnable() { public void run() { kickAnim(7, 3, true, 5); } }).start();
	 * break; case 6: new Thread(new Runnable() { public void run() {
	 * kickAnim(8, 11, false, 6); } }).start(); break; case 7: new Thread(new
	 * Runnable() { public void run() { kickAnim(3, 1, true, 7); } }).start();
	 * break; case 8: new Thread(new Runnable() { public void run() {
	 * kickAnim(1, 0, true, 8); } }).start(); break; }
	 * 
	 * }
	 * 
	 * public void kickAnim(int start, int end, boolean colour, int row) { //
	 * PlayerKick ImageIcon kickb = new ImageIcon("resources/kick blue.png");
	 * ImageIcon kickr = new ImageIcon("resources/kick red.png");
	 * 
	 * for (int i = start; i != end; i++) { if (!colour) Game.player2[i - 1] =
	 * new JLabel(kickr); else Game.player1[i - 1] = new JLabel(kickb);
	 * 
	 * if (colour) i -= 2; } UPF.pause(150); for (int i = start; i != end; i++)
	 * { if (!colour) Game.player2[i - 1] = new JLabel(Game.playerR); else
	 * Game.player1[i - 1] = new JLabel(Game.playerB); if (colour) i -= 2; } }
	 */
	public void goal(boolean colour, int score) // true = blue // false = red
	{
		Game.ball.setVisible(false);
		if (colour) {
			Game.scoreBlue.setText("" + score);
			Game.goal.setForeground(Color.blue);
		} else {
			Game.scoreRed.setText("" + score);
			Game.goal.setForeground(Color.red);
		}
		Game.toMenu.setEnabled(false);
		Game.goal.setVisible(true);
		UPF.pause(2000);
		Game.goal.setVisible(false);
		if (Game.scoreCounter1 == 2 | Game.scoreCounter2 == 2)
			win (colour);
		else
			resetBall(false);
	}

	public void win(boolean colour) 
	{
		RunAI.killAI();
		if (colour) {
			Game.win.setForeground(Color.blue);
			Game.win.setText("BLUE\nWINS");
		} else {
			Game.win.setForeground(Color.red);
			Game.win.setText("RED\nWINS");
		}
		Game.toMenu.setEnabled(false);
		Game.win.setVisible(true);
		UPF.pause(2000);
		Game.returnToMenu();
	}
	// ///////////////////////////////////////////////////////////////////////////
}
