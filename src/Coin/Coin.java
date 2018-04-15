import java.lang.*;

public class Coin extends Item{
	private int value;

	public Coin(double x, double y, double width, double height, int nilai){
		super(x, y, EntityType.COIN);
		this.setWidth(width);
		this.setHeight(height);
		this.value = nilai;
		this.setDirection(Math.atan(1)*4.0/2.0);
		this.setImage("koin-mahal.png");
	}

	public int getValue(){ //mendapatkan nilai koin
		return this.value;
	}

	public void tick(double delay) {
		double newX = this.getX() + 250*Math.cos(this.getDirection())*delay;
		double newY = this.getY() + 250*Math.sin(this.getDirection())*delay;
		if (this.move(newX, newY)) {
		}
	}
}