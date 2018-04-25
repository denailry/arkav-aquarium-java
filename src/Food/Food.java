import java.lang.*;

public class Food extends Item{
	public Food(double x, double y, double width, double height){
		super(x, y, EntityType.FOOD);
		this.setWidth(width);
		this.setHeight(height);
		this.setDirection(Math.atan(1)*4.0/2.0);
		this.setImage("food.png");
	}

	public void tick(double delay){
		double newX = this.getX() + 50*Math.cos(this.getDirection())*delay;
		double newY = this.getY() + 50*Math.sin(this.getDirection())*delay;
		if (!(this.move(newX, newY))) {
			this.remove();
			System.out.println("a");	
		}
	}
}