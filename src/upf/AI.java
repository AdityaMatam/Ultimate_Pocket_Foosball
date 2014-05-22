package upf;

public class AI {
public void move(){
	int tempY=BallMovement.yC, counter =0, tempX = BallMovement.xC,temp;
	try{Thread.sleep(500);}
	catch (InterruptedException ie){}
	PlayerMovement p2 = new PlayerMovement();
	
	// makes the "AI" move in a random direction
	int numOfMoves = (int)(Math.random()*13+1);
	if (Math.random()<0.5){
		for (int x=0;x<numOfMoves;x++){
		p2.p2Left();
		try{Thread.sleep(20);}
		catch (InterruptedException ie){}
		}
	}else {
		for (int x=0;x<numOfMoves;x++){
		p2.p2Right();
		try{Thread.sleep(10);}
		catch (InterruptedException ie){}
		}
	}
	/*
	while (true){
		tempY+=BallMovement.yV;
		counter+=1;
		if (Math.abs(tempY-330)<=3){
			temp = BallMovement.xV*counter;
			if (temp+BallMovement.xC>252){
				temp=temp+(temp-252);
			if (temp +BallMovement.xC<20)
				temp=(-(20-temp)+20);
			
		}
	}*/
}
}

