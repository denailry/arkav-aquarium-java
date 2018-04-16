public class Fish extends Entity {
	
	private	boolean dirRight; //bernilai true jika ke arah kanan dan false jika ke arah kiri
	private	boolean hunger; //bernilai true apabila ikan lapar

		// Constructor
		// Need position x and y
	public Fish(double x, double y, double width, double height, char type){
		super(x, y, width, height, type);
		setDirRight(true);
		setHunger(true);
	}

	public void setDirRight(bool dir){
		dirRight = dir;
	}

	public void setHunger(bool hgr){
		hunger = hgr;
	}

	public boolean getDirRight(){
		return dirRight;
	}

	public boolean getHunger(){
		return hunger;
	}

}