import java.lang.*;
import java.util.Random;

public class Guppy extends Fish{
	private static int lapar=1000;
	private static int koin=640;
	private static int mati=2000;
	private static int ngegedein=5;
	private int C; // Waktu periode ikan mengeluarkan koin
  private int G;
	private int growthCounter;
  private int lastFed;
  private int lastCoin;
	private int lastDrift;
	private int driftLength;

	private double distanceWithFood(double pos1X, double pos1Y, double pos2X, double pos2Y) {
		double xDiff = Math.abs(pos1X - pos2X);
		double yDiff = Math.abs(pos1Y - pos2Y);
		return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
	}
	
	public Food findNearestFood(LinkedList<Food> foods){
		Element<Food> eFood = foods.getFirst();
		if (eFood != null){
			Food nearestFood = eFood.getInfo();
			double minDistance = distanceWithFood(this.getX(),this.getY(),nearestFood.getX(), nearestFood.getY());

			eFood = eFood.getNext();
			while (eFood != null) {
				double distance = distanceWithFood(this.getX(),this.getY(),eFood.getInfo().getX(),eFood.getInfo().getY());
				if (distance < minDistance) {
					minDistance = distance;
					nearestFood = eFood.getInfo();
				}

				eFood = eFood.getNext();
			}
			return nearestFood;
		}else{
			return null;
		}
	}

	public boolean isAbleToConsume(Food food){
		return (food.isExist() && (
			(food.getX() > this.getLeft()) && 
			(food.getX() < this.getRight()) &&
			(food.getY() > this.getTop()) && 
			(food.getY() < this.getBottom())
		));
	}

	double pRand(double fMin, double fMax)
	{
		Random r = new Random();
		double randomValue = fMin + (fMax - fMin) * r.nextDouble();
	    return randomValue;
	}

	public Guppy(double x, double y, double width, double height){
		super(x, y, EntityType.GUPPY);
		this.setWidth(width);
		this.setHeight(height);
		setLastFed(this.time);
		C=640;
		G=1;
		growthCounter=0;
		driftLength = 200;
		lastDrift = this.time;
		
		setDirection(pRand(0,8*Math.atan(1)));
		if ((getDirection()>=2*Math.atan(1))&&(getDirection()<=6*Math.atan(1))){
			setDirRight(true);
		}else{
			setDirRight(false);
		}

		if (this.dirRight){	//menentukan gambar ikan yang dipakai
			this.setImage("small-guppy-right.png");
		}else{
			this.setImage("small-guppy-left.png");
		}
		
		this.setHunger(false);
	}

	public int getLastFed(){
		return this.lastFed;
	}

	public void setLastFed(int lastFed){
		this.lastFed = lastFed;
	}

	public void tick(LinkedList<Food> foods, LinkedList<Coin> coins, double delay){
		boolean cek = false;
		if (((this.time)-lastCoin)>=koin){
			lastCoin=(this.time);
			if ((this.G) == 1){
				Coin a = new Coin(this.getX(),this.getY(),20,20,2);
				a.setImage("koin-murah.png");
				coins.add(a);
			}else if ((this.G)==2){
				Coin a = new Coin(this.getX(),this.getY(),20,20,3);
				a.setImage("koin-sedang.png");
				coins.add(a);
			}else if ((this.G)==3){
				Coin a = new Coin(this.getX(),this.getY(),20,20,5);
				a.setImage("koin-mahal.png");
				coins.add(a);
			}
		}
		if (this.hunger){
			if ((this.time - this.lastFed)>=lapar+mati){
				this.remove();
				cek = true;
			}else{
				Food food = findNearestFood(foods);

				if (food != null){
					if (this.isAbleToConsume(food)){
						food.remove();
						setLastFed(this.time);
						setHunger(false);
						if (this.G < 3){
						growthCounter++;

							if (growthCounter == ngegedein){
								if (this.G == 1){
									G=2;
								}else if (this.G == 2){
									G=3;
								}
								growthCounter = 0;
							}
						}
				
					}else{
						if (food != null){
							setDirection(Math.atan2(food.getY()-this.getY(), food.getX()-this.getX()));
							if ((getDirection()>=2*Math.atan(1))&&(getDirection()<=6*Math.atan(1))){
								setDirRight(true);
							}
							else {
								setDirRight(false);
							}
							
							if ((this.dirRight)&&((this.G)==1)){	//menentukan gambar ikan yang dipakai
								this.setImage("small-guppy-left.png");
							}
							else if ((this.dirRight)&&((this.G)==2)){
								this.setImage("medium-guppy-left.png");
							}
							else if ((this.dirRight)&&((this.G)==3)){
								this.setImage("large-guppy-left.png");
							}
							else if ((!this.dirRight)&&((this.G)==1)){
								this.setImage("small-guppy-right.png");
							}
							else if ((!this.dirRight)&&((this.G)==2)){
								this.setImage("medium-guppy-right.png");
							}
							else if ((!this.dirRight)&&((this.G)==3)){
								this.setImage("large-guppy-right.png");
							}

							//Ngejalanin ikannya, berdasarkan dir yang udah pasti bener:
							double newX = this.getX() + 100*Math.cos(this.getDirection())*delay; //speed bisa diatur2 lah ntar
							double newY = this.getY() + 100*Math.sin(this.getDirection())*delay;
							if (this.move(newX, newY)) {
							//Update atribut:
								if ((this.time - this.lastFed)>=lapar){
									setHunger(true);
								}
							}else{
								setDirection(getDirection()+2*Math.atan(1));
							}
							cek = true;
						}
					}
				}
			}
		}

		if (!cek){
			
			if (((this.time)-(this.lastDrift))>=this.driftLength){
				setDirection(pRand(0,8*Math.atan(1)));	//randomize direction
				Random r = new Random();
				driftLength = 200 * r.nextInt();
				lastDrift = this.time;
			}

			if ((getDirection()>=2*Math.atan(1))&&(getDirection()<=6*Math.atan(1))){
				setDirRight(true);
			}
			else {
				setDirRight(false);
			}

			if ((this.dirRight)&&((this.G)==1)){	//menentukan gambar ikan yang dipakai
				this.setImage("small-guppy-left.png");
			}
			else if ((this.dirRight)&&((this.G)==2)){
				this.setImage("medium-guppy-left.png");
			}
			else if ((this.dirRight)&&((this.G)==3)){
				this.setImage("large-guppy-left.png");
			}
			else if ((!this.dirRight)&&((this.G)==1)){
				this.setImage("small-guppy-right.png");
			}
			else if ((!this.dirRight)&&((this.G)==2)){
				this.setImage("medium-guppy-right.png");
			}
			else if ((!this.dirRight)&&((this.G)==3)){
				this.setImage("large-guppy-right.png");
			}

			//Ngejalanin ikannya, berdasarkan dir yang udah pasti bener:
			double newX = this.getX() + 100*Math.cos(this.getDirection())*delay; //speed bisa diatur2 lah ntar
			double newY = this.getY() + 100*Math.sin(this.getDirection())*delay;
			if (this.move(newX, newY)) {
			//Update atribut:
				if ((this.time - this.lastFed)>=lapar){
					setHunger(true);
				}
			}else{
				setDirection(getDirection()+2*Math.atan(1));
			}
		}
		this.time = this.time + 1;
	}
}