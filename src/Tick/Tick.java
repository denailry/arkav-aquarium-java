import java.lang.Math;

public abstract class Tick {
	protected int time;

	public Tick() {
		this.time = 0;
	}

	abstract void tick(double delay);

	protected void tickReset() {
		this.time = 0;
	}

	protected int tickDiff(int time) {
		return  Math.abs(this.time - time);
	}
}