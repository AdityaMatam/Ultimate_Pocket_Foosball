package upf;

import java.awt.geom.*;

import javax.swing.JLabel;

public class BallMovement {
	static int xC = 140, yC = 266, yV = 0, xV = 0;
	boolean test = true;
	Ellipse2D.Double b;
	Ellipse2D.Double[] p1 = new Ellipse2D.Double[10];
	Ellipse2D.Double[] p2 = new Ellipse2D.Double[10];

	/**
	 * Adds the x and y velocities to the x and y coordinates of the ball, and calls the method
	 * to check for ball collisions.
	 * <p>
	 * Also calls the method that resets the ball using new coordinates
	 * 
	 * @param ball
	 *            JLabel that contains the image for the ball
	 * @param player1
	 *            JLabel array that contains the players for team 1
	 * @param player2
	 *            JLabel array that contains the players for team 2
	 * @param net1
	 *            JLabel that contains the image for team 1's net
	 * @param net2
	 *            JLabel that contains the image for the team 2's net
	 */
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

	/**
	 * Creates the three Rectangle2D.Double objects that are used for collision
	 * detection with the net
	 * 
	 * @param net
	 *            JLabel that influences where the net rectangles are created
	 * @return the array of Rectangle 2D objects that are used for collision
	 *         detection
	 */
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

	/**
	 * Determines whether or not the ball intersects any of the players or the
	 * net. Depending on what it hits, it readjusts the x and y velocities.
	 * 
	 * @param n1
	 *            The array of net rectangles for player 1
	 * @param n2
	 *            The array of net rectangles for player 2
	 */
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
			// red team
			Game.goal(true, Game.scoreCounter1);
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
			Game.goal(false, Game.scoreCounter2);
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
			} else if (b.intersects(boundingRectp2[1])) {
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

	/**
	 * Creates the 5 rectangles for each player so that collisions can be
	 * handled.
	 * 
	 * @param player
	 *            The Ellipse2D.Double that covers the player objects
	 * @return an array of rectangles that cover the players
	 */
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

	/**
	 * Resets the location and the velocities of the ball
	 * 
	 * @param global
	 */
	public static void resetBall(boolean global) {
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
}
