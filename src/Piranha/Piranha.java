import java.lang.*;
import java.util.Random;

public class Piranha extends Fish{
	private int lastFed;
	private int lastDrift;
	private int driftLength;

	public Guppy findNearestGuppy(LinkedList<Guppy> guppies){
		Element<Guppy> eGuppy = guppies.getFirst();
		if (eGuppy != null){
			Guppy nearestGuppy = eGuppy.getInfo();
			double minDistance = distanceWithGuppy(
				this.getX(),
				this.getY(),
				nearestGuppy.getX(), 
				nearestGuppy.getY());

			eGuppy = eGuppy.getNext();
			while (eGuppy != null) {
				double distance = distanceWithGuppy(
					this.getX(),
					this.getY(),
					eGuppy.getInfo().getX(), 
					eGuppy.getInfo().getY());
				if (distance < minDistance) {
					minDistance = distance;
					nearestGuppy = eGuppy.getInfo();
				}

				eGuppy = eGuppy.getNext();
			}
			return nearestGuppy;
		}else{
			return null;
		}
	}

	public boolean isAbleToConsume(Guppy guppy){
		return (this.isExist(guppy.getId(), EntityType.GUPPY) && (
			(guppy.getX() > this.getLeft()) && 
			(guppy.getX() < this.getRight()) &&
			(guppy.getY() > this.getTop()) && 
			(guppy.getY() < this.getBottom())
		));
	}

	double pRand(double fMin, double fMax)
	{
		Random r = new Random();
		double randomValue = fMin + (fMax - fMin) * r.nextDouble();
	    return randomValue;
	}

	public Piranha(double x, double y, double width, double height){
		super(x, y, EntityType.PIRANHA);
		this.setWidth(width);
		this.setHeight(height);
		setLastFed(this.time);
		driftLength = 200;
		lastDrift = this.time;
		
		setDirection(pRand(0,8*Math.atan(1)));
		if ((getDirection()>=2*Math.atan(1))&&(getDirection()<=6*Math.atan(1))){
			setDirRight(true);
		}else{
			setDirRight(false);
		}

		if (this.dirRight){	//menentukan gambar ikan yang dipakai
			this.setImage("piranha-right.png");
		}else{
			this.setImage("piranha-left.png");
		}
		
		this.setHunger(false);
	}

	public int getLastFed(){
		return this.lastFed;
	}

	public void setLastFed(int lastFed){
		this.lastFed = lastFed;
	}

	public void tick(LinkedList<Guppy> guppies, LinkedList<Coin> coins, double delay){	//belum diimplementasi
		boolean cek = false;
		
		if (this.hunger){
			if ((this.time - this.lastFed)>=LAPAR+MATI){
				this.remove();
				cek = true;
			}else{
				Guppy guppy = findNearestGuppy(guppies);

				if (guppy != null){
					if (this.isAbleToConsume(guppy)){
						guppy.remove();
						setLastFed(this.time);
						setHunger(false);
						Coin a = new Coin(this.getX(),this.getY(),20,20,20);//value untuk koin harus selalu 50 untuk guppy tahap pertama, nanti bisa diubah kalo perlu
						a.setImage("diamond_small.png");
						coins.add(a);
				
					}else{	//kalau tidak bisa consume makanan
						//ngejar guppy, belum implementasi (findNearestFood, setDir, setSpeed)
						if (guppy != null){
							setDirection(Math.atan2(guppy.getY()-this.getY(), guppy.getX()-this.getX()));
							if ((getDirection()>=2*Math.atan(1))&&(getDirection()<=6*Math.atan(1))){
								setDirRight(true);
							}
							else {
								setDirRight(false);
							}

							if (this.dirRight){	//menentukan gambar ikan yang dipakai
								this.setImage("piranha-left.png");
							}else{
								this.setImage("piranha-right.png");
							}

							//Ngejalanin ikannya, berdasarkan dir yang udah pasti bener:
							double newX = this.getX() + 100*Math.cos(this.getDirection())*delay; //speed bisa diatur2 lah ntar
							double newY = this.getY() + 100*Math.sin(this.getDirection())*delay;
							if (this.move(newX, newY)) {
							//Update atribut:
								if ((this.time - this.lastFed)>=LAPAR){
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

			if (this.dirRight){	//menentukan gambar ikan yang dipakai
				this.setImage("piranha-left.png");
			}else{
				this.setImage("piranha-right.png");
			}

			//Ngejalanin ikannya, berdasarkan dir yang udah pasti bener:
			double newX = this.getX() + 100*Math.cos(this.getDirection())*delay; //speed bisa diatur2 lah ntar
			double newY = this.getY() + 100*Math.sin(this.getDirection())*delay;
			if (this.move(newX, newY)) {
			//Update atribut:
				if ((this.time - this.lastFed)>=LAPAR){
					setHunger(true);
				}
			}else{
				setDirection(getDirection()+2*Math.atan(1));
			}
		}
		this.time = this.time + 1;
	}
}