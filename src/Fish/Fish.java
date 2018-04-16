public class Fish extends Entity {
	
	protected	boolean dirRight; //bernilai true jika ke arah kanan dan false jika ke arah kiri
	protected	boolean hunger; //bernilai true apabila ikan lapar

		// Constructor
		// Need position x and y
	public Fish(double x, double y, EntityType type){
		super(x, y, type);
		setDirRight(true);
		setHunger(true);
	}

	public void setDirRight(boolean dir){
		dirRight = dir;
	}

	public void setHunger(boolean hgr){
		hunger = hgr;
	}

	public boolean getDirRight(){
		return dirRight;
	}

	public boolean getHunger(){
		return hunger;
	}

}