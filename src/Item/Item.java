public class Item extends Entity{
	protected boolean isAtBottom;

	public Item(double x, double y, EntityType type){
		super(x,y,type);
		this.isAtBottom = false;
	}
}