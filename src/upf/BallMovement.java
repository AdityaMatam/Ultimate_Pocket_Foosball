package upf;

import java.awt.geom.*;

import javax.swing.JLabel;

public class BallMovement
{
	static int xC = 140, yC = 266, yV = 0, xV = 0;
	boolean test = true;
	Ellipse2D.Double b;
	Ellipse2D.Double[] p1 = new Ellipse2D.Double[10];
	Ellipse2D.Double[] p2 = new Ellipse2D.Double[10];

	public void updateBallPosition(JLabel ball, JLabel[] player1,
			JLabel[] player2, JLabel net1, JLabel net2)
	{
		xC += xV;
		yC += yV;
		b = new Ellipse2D.Double(xC, yC, 7, 7);
		Rectangle2D.Double[] n1 = {
				new Rectangle2D.Double(net1.getX() - 1, net1.getY(), 1, net1
						.getHeight()),
				new Rectangle2D.Double((net1.getX() + (net1.getWidth())), net1
						.getY(), 1, net1.getHeight()),
				new Rectangle2D.Double((net1.getX()), net1.getY(), (net1
						.getWidth()), net1.getHeight()) };
		Rectangle2D.Double[] n2 = {
				new Rectangle2D.Double(net2.getX() - 1, net2.getY(), 1, net2
						.getHeight()),
				new Rectangle2D.Double((net2.getX() + (net2.getWidth())), net2
						.getY(), 1, net2.getHeight()),
				new Rectangle2D.Double((net2.getX()), net2.getY(), (net2
						.getWidth()), net1.getHeight()) };
		for (int x = 0; x < 10; x++)
		{
			p1[x] = new Ellipse2D.Double(player1[x].getX(), player1[x].getY(),
					player1[x].getWidth(), player1[x].getHeight());
			p2[x] = new Ellipse2D.Double(player2[x].getX(), player2[x].getY(),
					player2[x].getWidth(), player2[x].getHeight());
		}
		detectCollision(n1, n2);
		ball.setLocation(xC, yC);
	}

	public void detectCollision(Rectangle2D.Double[] n1, Rectangle2D.Double[] n2)
	{
		if (xC < 20 && xV < 0)
		{
			xV = -xV;
		} else if (xC > 252 && xV > 0)
		{
			xV = -xV;
		} else if (yC < 89 && yV < 0)
		{
			yV = -yV;
		} else if (yC > 440 && yV > 0)
		{
			yV = -yV;
		}
		if (b.intersects(n1[0]))
		{
			// left net rectangle
			xV = -xV;
		} else if (b.intersects(n1[1]))
		{
			// right net rectangle
			xV = -xV;
		} else if (b.intersects(n1[2]))
		{
			// goal p1
			Game.scoreCounter1 += 1;
			System.out.println(Game.scoreCounter1);
			resetBall();
		}
		if (b.intersects(n2[0]))
		{
			// left net rectangle
			xV = -xV;
		} else if (b.intersects(n2[1]))
		{
			// right net rectangle
			xV = -xV;
		} else if (b.intersects(n2[2]))
		{
			// goal p2
			Game.scoreCounter2 += 1;
			System.out.println(Game.scoreCounter2);
			resetBall();
		}
		for (int x = 0; x < 10; x++)
		{
			Rectangle2D.Double[] boundingRectp1 = new Rectangle2D.Double[5];
			boundingRectp1 = createBoundingRect(p1[x]);
			Rectangle2D.Double[] boundingRectp2 = new Rectangle2D.Double[5];
			boundingRectp2 = createBoundingRect(p2[x]);
			int test = 1;
			if (b.intersects(boundingRectp1[2]))
			{
				// Center-most for player 1
				if (yV >= 0)
					yV = -yV;
				System.out.println("X: " + xV + " Y: " + yV);
			} else if (b.intersects(boundingRectp1[0]))
			{
				// Outer-left rectangle for player 1
				if (xV > 1 && (yV >= 1 && yV < 8))
				{
					xV -= test;
					yV += test;
				} else if (xV <= -1 && (yV > 1 && yV <= 8))
				{
					xV -= test;
					yV -= test;
				} else if (yV <= 0)
				{
					if (xV < -1)
					{
						xV += test;
						yV -= test;
					}
					if (xV > 1)
					{
						xV -= test;
						yV -= test;
					}
				}
				if (yV >= 0)
				{
					yV = -yV;
				}

				System.out.println("X: " + xV + " Y: " + yV);

			} else if (b.intersects(boundingRectp1[1]))
			{
				// Outer-right rectangle for player 1
				if (xV < -1 && (yV >= 1 && yV < 8))
				{
					xV += test;
					yV += test;
				} else if (xV >= 1 && (yV > 1 && yV <= 8))
				{
					xV += test;
					yV -= test;
				} else if (yV <= 0)
				{
					if (xV < -1)
					{
						xV += test;
						yV -= test;
					}
					if (xV > 1)
					{
						xV -= test;
						yV -= test;
					}
				}
				if (yV >= 0)
				{
					yV = -yV;

				}

				System.out.println("X: " + xV + " Y: " + yV);

			} else if (b.intersects(boundingRectp2[2]))
			{
				// Center-most rectangle for player 2
				if (yV <= 0)
					yV = -yV;
				System.out.println("X: " + xV + " Y: " + yV);

			} else if (b.intersects(boundingRectp2[0]))
			{
				// Outer-left rectangle for player 2
				if (xV > 1 && (yV < -1 && yV >= -8))
				{
					xV -= test;
					yV -= test;
				} else if (xV <= -1 && (yV <= -1 && yV > -8))
				{
					xV -= test;
					yV += test;
				} else if (yV >= 0)
				{
					if (xV < -1)
					{
						xV += test;
						yV += test;
					}
					if (xV > 1)
					{
						xV -= test;
						yV += test;
					}
				}
				if (yV <= 0)
				{

					yV = -yV;

				}

				System.out.println("X: " + xV + " Y: " + yV);

			}

			else if (b.intersects(boundingRectp2[1]))
			{
				// Outer-right rectangle for player 2
				if (xV < -1 && (yV <= -1 && yV > -8))
				{
					xV += test;
					yV -= test;
				} else if (xV > 1 && (yV < -1 && yV >= -8))
				{
					xV += test;
					yV += test;
				} else if (yV >= 0)
				{
					if (xV < -1)
					{
						xV += test;
						yV += test;
					}
					if (xV > 1)
					{
						xV -= test;
						yV += test;
					}
				}
				if (yV <= 0)
				{
					yV = -yV;
					System.out.println("X: " + xV + " Y: " + yV);
				}
			}
		}
	}

	public Rectangle2D.Double[] createBoundingRect(Ellipse2D.Double player)
	{
		Rectangle2D.Double[] rect = new Rectangle2D.Double[3];
		rect[0] = new Rectangle2D.Double(player.getX(), player.getY(), 3,
				player.height);
		rect[1] = new Rectangle2D.Double((player.getX() + 4), player.getY(), 3,
				player.height);
		rect[2] = new Rectangle2D.Double((player.getX() + 3), player.getY(), 1,
				player.height);
		return rect;
	}

	public void resetBall()
	{
		xV = 0;
		yV = 0;
		while (xV == 0)
		{
			xV = (int) (Math.random() * 16 - 8);
			if (Math.random() < 0.5)
			{
				yV = 8 - Math.abs(xV);
			} else
			{
				yV = -(8 - Math.abs(xV));
			}
		}
		xC = 140;
		yC = 266;
	}

	public void kick(int row)
	{
		switch (row) {
		case 1:
			kickAnim(1, 2, false, row);
			break;
		case 2:
			kickAnim(2, 4, false, row);
			break;
		case 3:
			kickAnim(10, 7, true, row);
			break;
		case 4:
			kickAnim(4, 8, false, row);
			break;
		case 5:
			kickAnim(7, 3, true, row);
			break;
		case 6:
			kickAnim(8, 11, false, row);
			break;
		case 7:
			kickAnim(3, 1, true, row);
			break;
		case 8:
			kickAnim(1, 0, true, row);
			break;

		}

	}

	public void kickAnim(int start, int end, boolean colour, int row)
	{
		PlayerMovement move = new PlayerMovement();
		for (int i = start; i != end; i++)
		{
			if (!colour)
			{
				Game.redKick[i - 1].setVisible(true);
				Game.redKick[i - 1].setLocation(move.y[row - 1] + 4,
						move.x2[i - 1] + 2);
			} else
			{
				Game.blueKick[i - 1].setVisible(true);
				Game.blueKick[i - 1].setLocation(move.y[row - 1] + 4,
						move.x2[i - 1] + 2);
			}
			if (colour)
				i -= 2;
		}
		try
		{
			Thread.sleep(150);
		} catch (InterruptedException e)
		{
		}
		for (int i = start; i != end; i++)
		{
			if (!colour)
				Game.redKick[i - 1].setVisible(false);
			else
				Game.blueKick[i - 1].setVisible(false);
			if (colour)
				i -= 2;
		}
	}

}
