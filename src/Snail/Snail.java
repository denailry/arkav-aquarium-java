import java.lang.*;
import java.util.Random;


public class Snail extends Entity {
	private double distanceWith(double pos1X, double pos1Y, double pos2X, double pos2Y) {
		double xDiff = Math.abs(pos1X - pos2X);
		double yDiff = Math.abs(pos1Y - pos2Y);
		return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
	}

	private boolean isAlignX(Entity aligner, Entity aligned) {
		return (aligned.getX() > aligner.getLeft() && aligned.getX() < aligner.getRight());
	}

	public Snail(double x, double y, double width, double height){
		super(x, y, EntityType.SNAIL);
		this.setWidth(width);
		this.setHeight(height);
		this.setImage("snail-small-right.png");
		return;	
	}

	public Coin findNearestCoin(LinkedList<Coin> coins) {
		Element<Coin> eCoin = coins.getFirst();
		if (eCoin == null) {
			return null;
		}
		Coin nearestCoin = eCoin.getInfo();
		double minDistance = distanceWith(
			this.getX(),
			this.getY(),
			nearestCoin.getX(), 
			nearestCoin.getY());

		eCoin = eCoin.getNext();
		while (eCoin != null) {
			double distance = distanceWith(
				this.getX(),
				this.getY(),
				eCoin.getInfo().getX(), 
				eCoin.getInfo().getY());
			if (distance < minDistance) {
				minDistance = distance;
				nearestCoin = eCoin.getInfo();
			}

			eCoin = eCoin.getNext();
		}

		return nearestCoin;
	}

	public boolean isAbleToConsume(Coin coin) {
		return (coin.isExist() && (
			(coin.getX() > this.getLeft()) && 
			(coin.getX() < this.getRight()) &&
			(coin.getY() > this.getTop()) && 
			(coin.getY() < this.getBottom())
		));
	}

	public void tick(LinkedList<Coin> coins, double delay) {
		Coin coin = findNearestCoin(coins);
		if (coin != null) {
			this.setDirection(Math.atan2(0, coin.getX()-this.getX()));
			if (this.getDirection() < 1) {
				this.setImage("snail-small-right.png");
			} else {
				this.setImage("snail-small-left.png");
			}
			if (!isAlignX(this, coin)) {
				double newX = this.getX() + 200*Math.cos(this.getDirection())*delay;
				double newY = this.getY() + 200*Math.sin(this.getDirection())*delay;
				if (this.move(newX, newY)) {} //ini jadi begini abis ngeluarin boolean sih.. gimana? biarin gini aja?
			}
			if (this.isAbleToConsume(coin)) {
				coin.remove();
			}
		}
	}
};