import java.lang.*;
import java.util.Random;


public class Snail extends Entity {
	private double distanceWith(double pos1X, double pos1Y, double pos2X, double pos2Y) {
		double xDiff = abs(pos1X - pos2X);
		double yDiff = abs(pos1Y - pos2Y);
		return sqrt(xDiff*xDiff + yDiff*yDiff);
	}

	private boolean isAlignX(Entity aligner, Entity aligned) {
		return (aligned.getX() > aligner.getLeft() && aligned.getX() < aligner.getRight());
	}

	public Snail::Snail(double x, double y, double width, double height) : Entity(x, y, width, height, TYPE_SNAIL) {
		this->setImage("snail-small-right.png");
		return;	
	}

	public Coin* Snail::findNearestCoin(LinkedList<Coin> &coins) {
		Element<Coin>* eCoin = coins.getFirst();
		if (eCoin == NULL) {
			return NULL;
		}
		Coin *nearestCoin = eCoin->getInfo();
		double minDistance = distanceWith(
			this->getX(),
			this->getY(),
			nearestCoin->getX(), 
			nearestCoin->getY());

		eCoin = eCoin->getNext();
		while (eCoin != NULL) {
			double distance = distanceWith(
				this->getX(),
				this->getY(),
				eCoin->getInfo()->getX(), 
				eCoin->getInfo()->getY());
			if (distance < minDistance) {
				minDistance = distance;
				nearestCoin = eCoin->getInfo();
			}

			eCoin = eCoin->getNext();
		}

		return nearestCoin;
	}

	public boolean Snail::isAbleToConsume(Coin const& coin) {
		return (this->getSpace())->isExist(coin.getId(), TYPE_COIN) && (
			(coin.getX() > this->getLeft()) && 
			(coin.getX() < this->getRight()) &&
			(coin.getY() > this->getTop()) && 
			(coin.getY() < this->getBottom())
		);
	}

	public void Snail::tick(LinkedList<Coin> &coins, double delay) {
		Coin *coin = findNearestCoin(coins);
		if (coin != NULL) {
			this->setDirection(atan2(0, coin->getX()-this->getX()));
			if (this->getDirection() < 1) {
				this->setImage("snail-small-right.png");
			} else {
				this->setImage("snail-small-left.png");
			}
			if (!isAlignX(*this, *coin)) {
				double newX = this->getX() + 200*cos(this->getDirection())*delay;
				double newY = this->getY() + 200*sin(this->getDirection())*delay;
				if (this->getSpace()->moveTo(this->getId(), TYPE_SNAIL, newX, newY)) {
					this->setX(newX);
					this->setY(newY);
				}
			}
			if (this->isAbleToConsume(*coin)) {
				this->getSpace()->remove(coin->getId(), TYPE_COIN);
			}
		}
	}
};