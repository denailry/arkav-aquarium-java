public enum ShopItem {
	GUPPY (10), 
	FOOD (1), 
	PIRANHA (20), 
	EGG_1 (30), 
	EGG_2 (60), 
	EGG_3 (90)
	;

	public final int price;

	private ShopItem(int price) {
		this.price = price;
	}
}