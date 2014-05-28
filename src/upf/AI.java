package upf;

import javax.swing.JLabel;

public class AI {
	static PlayerMovement p2 = new PlayerMovement();
	static int yCoord = BallMovement.yC, counter = 0, tempX = BallMovement.xC;
	int row, tempDiff, diff;
	int testDiff = Integer.MAX_VALUE;
	static boolean test = false;

	public void moveRandom(JLabel[] player2) {

		// makes the "AI" move in a random direction
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException ie) {
		}
		int numOfMoves = (int) (Math.random() * 13 + 1);
		if (Math.random() < 0.5) {
			for (int x = 0; x < numOfMoves; x++) {
				if (!(player2[3].getX() + 3 < 20))
					p2.p2Left();
				try {
					Thread.sleep(20);
				} catch (InterruptedException ie) {
				}
			}
		} else {
			for (int x = 0; x < numOfMoves; x++) {
				if (!(player2[6].getX() + 3 > 252))
					p2.p2Right();
				try {
					Thread.sleep(20);
				} catch (InterruptedException ie) {
				}
			}
		}

	}
	public void move(JLabel [] player2){
		yCoord = BallMovement.yC;
		if (BallMovement.yV>0){
			outerloop:
			while (true){
				yCoord += BallMovement.yV;
				for (int x=0;x<4;x++){
					if (Math.abs(yCoord-p2.y2[x])<=7){
						row = x;
						break outerloop;
					}
				}
			}
		}
		else if (BallMovement.yV<0){
			outerloop:
			while (true){
				yCoord -= BallMovement.yV;
				for (int x=0;x<4;x++){
					if (Math.abs(yCoord-p2.y2[x])<=7){
						row = x;
						break outerloop;
					}
				}
			}
		}
		if (row ==0){
			//Goalie
			determineDirection(BallMovement.xC-player2[0].getX());
		}
		else if (row ==1){
			//Defender
			tempDiff = BallMovement.xC-player2[1].getX();
			diff =  BallMovement.xC-player2[2].getX();
			if (Math.abs(tempDiff)<Math.abs(diff)){
				determineDirection (tempDiff);
			}
			else determineDirection (diff);
		}
		else if (row == 2){
			//Midfield
			diff = BallMovement.xC-player2[3].getX();
			for (int i=0;i<3;i++){
				if (Math.abs(diff)>Math.abs(BallMovement.xC-player2[i+4].getX())){
					diff = BallMovement.xC-player2[i+4].getX();
				}
			}
			determineDirection (diff);
		}
		else if (row ==3){
			//Attacker
			diff = BallMovement.xC-player2[7].getX();
			for (int i=0;i<2;i++){
				if (Math.abs(diff)>Math.abs(BallMovement.xC-player2[i+8].getX())){
					diff = BallMovement.xC-player2[i+8].getX();
				}
			}
			determineDirection (diff);
		}
	}

	public void determineDirection(int difference){
		if (difference<0&&!(Game.player2[3].getX() - 1 < 20)){
			p2.p2Left();
		}
		if (difference>0&&!(Game.player2[6].getX() + 1 > 252)){
			p2.p2Right();
		}
	}
	public static void resetAI() {
		yCoord=BallMovement.yC;
	}

}
