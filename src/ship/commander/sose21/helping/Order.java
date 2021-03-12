package ship.commander.sose21.helping;

import ship.commander.sose21.enums.DIRECTION;

public class Order {
	
	private int y;
	private int x;	
	private DIRECTION vh;
	private DIRECTION LR;
	
	
	public String toString() {
		
		return "|y: "+this.getY()+"|  x:"+this.getX()+"|  VH: "+this.getVh().toString()+"|  LR: "+this.getLR().toString();
		
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public DIRECTION getVh() {
		return vh;
	}


	public void setVh(DIRECTION vh) {
		this.vh = vh;
	}


	public DIRECTION getLR() {
		return LR;
	}


	public void setLR(DIRECTION lR) {
		LR = lR;
	}

}
