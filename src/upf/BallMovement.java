package upf;
import java.awt.geom.*;

import javax.swing.JLabel;
public class BallMovement {
	int xC = 136, yC = 265, vX = 6, vY=7;
	Ellipse2D.Double b;
	Ellipse2D.Double []p1 = new Ellipse2D.Double[10];
	Ellipse2D.Double[]p2 = new Ellipse2D.Double[10];
	public void updateBallPosition (JLabel ball, JLabel[] player1, JLabel[] player2){
		xC+=vX;
		yC+=vY;
		b = new Ellipse2D.Double(xC, yC, 7,7);
		for (int x =0;x<10;x++){
			p1[x]=new Ellipse2D.Double(player1[x].getX(),player1[x].getY(),player1[x].getWidth(),player1[x].getHeight());
			p2[x]=new Ellipse2D.Double(player2[x].getX(),player2[x].getY(),player2[x].getWidth(),player2[x].getHeight());
		}
		detectCollision(ball);
		ball.setLocation(xC,yC);
	}
	public void detectCollision(JLabel ball){
		if (xC<20 &&vX<0){
			vX=-vX;
		}
		else if (xC >258 && vX>0){
			vX=-vX;
		}
		else if (yC<50 &&vY<0){
			vY=-vY;
		}
		else if (yC >440 && vY>0){
			vY=-vY;
		}
		for (int x =0;x<10;x++){
			Rectangle2D.Double[] boundingRectp1 = new Rectangle2D.Double[5];
			boundingRectp1 = createBoundingRect(p1[x]);
			Rectangle2D.Double[] boundingRectp2 = new Rectangle2D.Double[5];
			boundingRectp2 = createBoundingRect(p2[x]);
		if (b.intersects(boundingRectp1[0]) || b.intersects(boundingRectp1[1])){
			// Outer-most rectangle blocks for player 1
			vY=-vY;
		}
		else if (b.intersects(boundingRectp1[2]) || b.intersects(boundingRectp1[3])){
			// Second and fourth rectangle blocks for player 1
			vY=-vY;
		}
		else if (b.intersects(boundingRectp1[4])){
			// Center-most rectangle for player 1
		}
		if (b.intersects(boundingRectp2[0]) || b.intersects(boundingRectp2[1])){
			// Outer-most rectangle blocks for player 2
			vY=-vY;
		}
		else if (b.intersects(boundingRectp2[2]) || b.intersects(boundingRectp2[3])){
			// Second and fourth rectangle blocks for player 2
			vY=-vY;
		}
		else if (b.intersects(boundingRectp2[4])){
			// Center-most rectangle for player 2
		}
		}
	}
	public Rectangle2D.Double[] createBoundingRect(Ellipse2D.Double player){
		Rectangle2D.Double[] rect = new Rectangle2D.Double[5];
		rect [0]=new Rectangle2D.Double(player.getX(), player.getY(),1, (player.height-1));
		rect [1]=new Rectangle2D.Double((player.getX()+6), player.getY(),1, (player.height-1));
		rect[2]=new Rectangle2D.Double((player.getX()+1), player.getY(),1, (player.height-1));
		rect[3]=new Rectangle2D.Double((player.getX()+5), player.getY(),1, (player.height-1));
		rect[4]= new Rectangle2D.Double((player.getX()+3), player.getY(),3, (player.height-1));
		return rect;
	}
}
