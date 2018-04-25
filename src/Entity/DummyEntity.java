public class DummyEntity extends Entity {
	private double lifetime;
	private boolean die;

	public DummyEntity(int x, int y, double lifetime) {
		super(x, y, EntityType.OTHER);
		this.die = false;
		this.lifetime = lifetime;
	}

	@Override
	public void tick(double delay) {
		super.tick(delay);
		if (this.time > this.lifetime*(1/delay)) {
			this.die = true;
		}
	}

	public boolean isDie() {
		return this.die;
	}
}