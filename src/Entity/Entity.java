public class Entity extends Tick {
	private Space space;
	private int id;
	private double x;
	private double y;
	private double radX;
	private double radY;
	private double direction;
	private EntityType type;
	private String image;

	public Entity(double x, double y, EntityType type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.id = -1;
	}

	@Override
	public void tick(double delay) {
		this.time++;
	}

	public int getId() {
		return this.id;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getTop() {
		return this.y - this.radY;
	} 

	public double getBottom() {
		return this.y + this.radY;
	}

	public double getLeft() {
		return this.x - this.radX;
	}

	public double getRight() {
		return this.x + this.radX;
	}

	public double getDirection() {
		return this.direction;
	}

	public EntityType getType() {
		return this.type;
	}

	public boolean move(double x, double y) {
		boolean isMoved = this.space.isAbleMovingTo(x, y); 
		if (isMoved) {
			this.x = x;
			this.y = y;
		}
		return isMoved;
	}

	public boolean isExist() {
		return this.space.isExist(this.id, this.type);
	}

	public void remove() {
		this.space.remove(this.id, this.type);
	}

	public String getImage() {
		return this.image;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setWidth(double width) {
		if (width < 0) {
			this.radX = 0;
		} else {
			this.radX = width/2.0;
		}
	}

	public void setHeight(double height) {
		if (height < 0) {
			this.radY = 0;
		} else {
			this.radY = height/2.0;
		}
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public void setSpace(Space space) {
		this.space = space;
	}
}