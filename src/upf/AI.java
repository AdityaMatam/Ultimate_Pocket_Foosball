package upf;

public class AI {
public void move(){
	try{Thread.sleep(500);}
	catch (InterruptedException ie){}
	PlayerMovement p2 = new PlayerMovement();
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
}
}
