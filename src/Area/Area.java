public class Area {
	String name;
	String image;
	int left;
	int top;
	int right;
	int bottom;

	public Area(String name, int left, int right, int top, int bottom, String image) {
		this.name = name;
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		this.image = image;
	}

	public boolean isInside(int x, int y) {
		return (x >= this.left &&
				x <= this.right &&
				y >= this.top &&
				y <= this.bottom
			);
	}

	public int getTop() {
		return this.top;
	}

	public int getLeft() {
		return this.left;
	}

	public int getRight() {
		return this.right;
	}

	public int getBottom() {
		return this.bottom;
	}

	public int getWidth() {
		return this.right-this.left;
	}

	public int getHeight() {
		return this.bottom-this.top;
	}

	public String getName() {
		return this.name;
	}

	public String getImage() {
		return this.image;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImage(String image) {
		this.image = image;
	}
}