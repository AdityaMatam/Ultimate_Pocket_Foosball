package upf;
import java.awt.geom.*;

import javax.swing.JLabel;
public class BallMovement {

		}
		else if (yC >440 && yV>0){
			yV=-yV;
		}
		for (int x =0;x<10;x++){
			Rectangle2D.Double[] boundingRectp1 = new Rectangle2D.Double[5];
			boundingRectp1 = createBoundingRect(p1[x]);
			Rectangle2D.Double[] boundingRectp2 = new Rectangle2D.Double[5];
			boundingRectp2 = createBoundingRect(p2[x]);
			int vP1=new PlayerMovement().vP1;
			int vP2 = new PlayerMovement().vP2;
			if (b.intersects(boundingRectp1[4])){
				// Center-most for player 1
				if (yV>=0)
					yV=-yV;
			}
			else if (b.intersects(boundingRectp1[0])){
				// Outer-left rectangle for player 1
				if (yV>=0){
					yV-=vP1;
					yV=-yV;
					if (xV>0)
						xV=-xV;
				}
				else
					yV+=vP1;
				xV+=vP1;
				System.out.println(vP1);
			}
			else if (b.intersects(boundingRectp1[1])){
				//Outer-right rectangle for player 1
				if (yV>=0){
					yV-=vP1;
					yV=-yV;
					if (xV<0)
						xV=-xV;
				}
				else
					yV+=vP1;
				xV+=vP1;
				System.out.println(vP1);

			}
			else if (b.intersects(boundingRectp1[2])){
				// Second from left rectangle for player 1
				if (yV>=0)
					yV=-yV;
			}
			else if (b.intersects(boundingRectp1[3])){
				// Fourth from left rectangle for player 1
			}
			else if (b.intersects(boundingRectp2[4])){
				// Center-most rectangle for player 2
				if (yV<=0)
					yV=-yV;
			}
			else if (b.intersects(boundingRectp2[0])){
				// Outer-left rectangle for player 2
				if (yV<=0){
					yV-=vP2;
					yV=-yV;
					if (xV<0)
						xV=-xV;
				}
				else
					yV+=vP2;
				xV+=vP2;
				System.out.println(vP2);

			}
			else if (b.intersects(boundingRectp2[1])){
				//Outer-right rectangle for player 2
				if (yV<=0){
					yV-=vP2;
					yV=-yV;
					if (xV>0)
						xV=-xV;
				}
				else
					yV+=vP2;
				xV+=vP2;
				System.out.println(vP2);

			}
			else if (b.intersects(boundingRectp2[2])){
				// Second from left rectangle for player 2
				if (yV<=0)
					yV=-yV;
			}
			else if (b.intersects(boundingRectp2[3])){
				// Fourth from left rectangle for player 2
			}
		}
	}
	public Rectangle2D.Double[] createBoundingRect(Ellipse2D.Double player){
		Rectangle2D.Double[] rect = new Rectangle2D.Double[5];
		rect [0]=new Rectangle2D.Double(player.getX(), player.getY(),1, player.height);
		rect [1]=new Rectangle2D.Double((player.getX()+6), player.getY(),1, player.height);
		rect[2]=new Rectangle2D.Double((player.getX()+1), player.getY(),1, player.height);
		rect[3]=new Rectangle2D.Double((player.getX()+5), player.getY(),1, player.height);
		rect[4]= new Rectangle2D.Double((player.getX()+3), player.getY(),3, player.height);
		return rect;
	}
}

