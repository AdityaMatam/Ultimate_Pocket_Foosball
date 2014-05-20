package upf;
import java.awt.geom.*;

import javax.swing.JLabel;
public class BallMovement {
	int xC = 140, yC = 266,xV = 5, yV=3;
	Ellipse2D.Double b;
	Ellipse2D.Double []p1 = new Ellipse2D.Double[10];
	Ellipse2D.Double[]p2 = new Ellipse2D.Double[10];
	public void updateBallPosition (JLabel ball, JLabel[] player1, JLabel[] player2, JLabel net1, JLabel net2){
		xC+=xV;
		yC+=yV;
		b = new Ellipse2D.Double(xC, yC, 7,7);
		Rectangle2D.Double[] n1 = {new Rectangle2D.Double(net1.getX(),net1.getY(),1,net1.getHeight()), 
				new Rectangle2D.Double((net1.getX()+(net1.getWidth()-1)),net1.getY(),1,net1.getHeight()),
				new Rectangle2D.Double((net1.getX()+1),net1.getY(),(net1.getWidth()-6),net1.getHeight())};
		Rectangle2D.Double [] n2 = {new Rectangle2D.Double(net2.getX(),net2.getY(),1,net2.getHeight()),
				new Rectangle2D.Double((net2.getX()+(net2.getWidth()-1)),net2.getY(),1,net2.getHeight()),
				new Rectangle2D.Double((net2.getX()+1),net2.getY(),(net2.getWidth()-6),net1.getHeight())};
		for (int x =0;x<10;x++){
			p1[x]=new Ellipse2D.Double(player1[x].getX(),player1[x].getY(),player1[x].getWidth(),player1[x].getHeight());
			p2[x]=new Ellipse2D.Double(player2[x].getX(),player2[x].getY(),player2[x].getWidth(),player2[x].getHeight());
		}
		detectCollision(n1,n2);
		ball.setLocation(xC,yC);
	}
	public void detectCollision(Rectangle2D.Double[] n1, Rectangle2D.Double[] n2){
		if (xC<20 &&xV<0){
			xV=-xV;
		}
		else if (xC >252 && xV>0){
			xV=-xV;
		}
		else if (yC<89 &&yV<0){
			yV=-yV;
		}
		else if (yC >440 && yV>0){
			yV=-yV;
		}
		if (b.intersects(n1[0])){
			// left net rectangle
			xV=-xV;
		}
		else if (b.intersects(n1[1])){
			// right net rectangle
			xV=-xV;
		}
		else if (b.intersects(n1[2])){
			// goal
			System.out.println("GOAL!!!!!");
		}
		if (b.intersects(n2[0])){
			// left net rectangle
			xV=-xV;
		}
		else if (b.intersects(n2[1])){
			// right net rectangle
			xV=-xV;
		}
		else if (b.intersects(n2[2])){
			// goal
			System.out.println("GOAL!!!!!");
		}
		for (int x =0;x<10;x++){
			Rectangle2D.Double[] boundingRectp1 = new Rectangle2D.Double[5];
			boundingRectp1 = createBoundingRect(p1[x]);
			Rectangle2D.Double[] boundingRectp2 = new Rectangle2D.Double[5];
			boundingRectp2 = createBoundingRect(p2[x]);
			int test=1;
			if (b.intersects(boundingRectp1[2])){
				// Center-most for player 1
				if (yV>=0)
					yV=-yV;
				System.out.println ("X: "+xV+ " Y: "+yV);
			}
			else if (b.intersects(boundingRectp1[0])){
				// Outer-left rectangle for player 1
					if (xV>1 &&(yV>=1&&yV<8)){
					xV-=test;
					yV+=test;
					}
				else if (xV<=-1&& (yV>1&&yV<=8)){
					xV-=test;
					yV-=test;
				}
				if (yV>=0){
					yV=-yV;
				}
				

				System.out.println ("X: "+xV+ " Y: "+yV);

			}
			else if (b.intersects(boundingRectp1[1])){
				//Outer-right rectangle for player 1
					if (xV<-1&& (yV>=1&&yV<8)){
					xV+=test;
					yV+=test;
					}
				else if (xV>=1&& (yV>1&&yV<=8)){
					xV+=test;
					yV-=test;
				}
				if (yV>=0){
					yV=-yV;
					
				}
				
				System.out.println ("X: "+xV+ " Y: "+yV);

			}
			else if (b.intersects(boundingRectp2[2])){
				// Center-most rectangle for player 2
				if (yV<=0)
					yV=-yV;
				System.out.println ("X: "+xV+ " Y: "+yV);

			}
			else if (b.intersects(boundingRectp2[0])){
				// Outer-left rectangle for player 2
					if (xV>1&& (yV<-1&&yV>=-8)){
					xV-=test;
					yV-=test;
					}
				else if (xV<=-1 && (yV<=-1&&yV>-8)){
					xV-=test;
					yV+=test;
				}
				if (yV<=0){
					
					yV=-yV;

				}
				
					System.out.println ("X: "+xV+ " Y: "+yV);

				
			}
		
			else if (b.intersects(boundingRectp2[1])){
				//Outer-right rectangle for player 2
					if (xV<-1 && (yV<=-1&&yV>-8)){
					xV+=test;
					yV-=test;
					}
				else if (xV>1 && (yV<-1&&yV>=-8)){
					xV+=test;
					yV+=test;
				}
				if (yV<=0){
					yV=-yV;
					System.out.println ("X: "+xV+ " Y: "+yV);
				}
				
			}
		}
		}
		public Rectangle2D.Double[] createBoundingRect(Ellipse2D.Double player){
			Rectangle2D.Double[] rect = new Rectangle2D.Double[3];
			rect [0]=new Rectangle2D.Double(player.getX(), player.getY(),3, player.height);
			rect [1]=new Rectangle2D.Double((player.getX()+4), player.getY(),3, player.height);
			rect[2]= new Rectangle2D.Double((player.getX()+3), player.getY(),1, player.height);
			return rect;
		}
	}

