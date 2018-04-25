public class Aquarium extends Tick implements Space {
	private final int STARTING_MONEY = 20;

	// Screen attributes
	private int left;
	private int right;
	private int top;
	private int bottom;

	// Object attributes
	private LinkedList<Coin> coins = new LinkedList<Coin>();
	private LinkedList<Food> foods = new LinkedList<Food>();
	private LinkedList<Guppy> guppies = new LinkedList<Guppy>();
	private LinkedList<Piranha> piranhas = new LinkedList<Piranha>();
	private Snail snail;

	// Object Counter
	private int nObject;

	// Game attributes
	private int money;
	private boolean gameOver;

	public Aquarium(int left, int right, int top, int bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		this.money = STARTING_MONEY;
		this.nObject = 0;
		this.gameOver = false;
	}

	private void tickCoins(double delay) {
		Element<Coin> eCoin = coins.getFirst();
		while (eCoin != null) {
			eCoin.getInfo().tick(delay);
			eCoin = eCoin.getNext();
		}
	}

	private void tickFoods(double delay) {
		Element<Food> eFood = foods.getFirst();
		while (eFood != null) {
			eFood.getInfo().tick(delay);
			eFood = eFood.getNext();
		}
	}

	private void tickGuppies(double delay) {
		Element<Guppy> eGuppy = guppies.getFirst();
		LinkedList<Coin> newCoins = new LinkedList<Coin>();
		LinkedList<Food> guppyFoods = new LinkedList<Food>(foods);
		while (eGuppy != null) {
			eGuppy.getInfo().tick(guppyFoods, newCoins, delay);
			eGuppy = eGuppy.getNext();
		}
		this.add(newCoins);
	}

	private void tickPiranhas(double delay) {
		Element<Piranha> ePiranha = piranhas.getFirst();
		LinkedList<Coin> newCoins = new LinkedList<Coin>();
		LinkedList<Guppy> piranhaFoods = new LinkedList<Guppy>(guppies);
		while (ePiranha != null) {
			ePiranha.getInfo().tick(piranhaFoods, newCoins, delay);
			ePiranha = ePiranha.getNext();
		}
		this.add(newCoins);
	}

	private void tickSnail(double delay) {
		if (this.snail != null) {
			LinkedList<Coin> copyCoins = new LinkedList<Coin>(coins);
			this.snail.tick(copyCoins, delay);
		}
	}

	private void checkLoseCondition() {
		if (this.coins.size() == 0 && 
			this.foods.size() == 0 &&
			this.guppies.size() == 0 &&
			this.piranhas.size() == 0 &&
			this.money < 10) {
				System.out.println("GAME OVER");
				this.gameOver = true;
		}
	}

	private <T extends Entity> int search(LinkedList<T> objectList, int entityId) {
		Element<T> eObject = objectList.getFirst();
		int i = 0;
		boolean found = false;
		while (eObject != null && !found) {
			if (eObject.getInfo().getId() == entityId) {
				return i;
			}
			eObject = eObject.getNext();
			i++;
		}
		return -1;
	}

	private <T extends Entity> T putOut(LinkedList<T> objectList, int entityId) {
		int index = search(objectList, entityId);
		if (index != -1) {
			T obj = objectList.get(index);
			objectList.remove(index);
			return obj;
		}
		return null;
	}

	public void tick(double delay) {
		if (!this.gameOver) {
			tickCoins(delay);
			tickFoods(delay);
			tickGuppies(delay);
			tickPiranhas(delay);
			tickSnail(delay);
			checkLoseCondition();
		}
	}

	public <T extends Entity> void add(LinkedList<T> list) {
		Element<T> element = list.getFirst();
		while (element != null) {
			this.add(element.getInfo());
			element = element.getNext();
		}
	}

	public <T extends Entity> void add(T object) {
		if (object.getType() == EntityType.COIN) {
			coins.add((Coin) object);
		} else if (object.getType() == EntityType.FOOD) {
			foods.add((Food) object);
		} else if (object.getType() == EntityType.GUPPY) {
			guppies.add((Guppy) object);
		} else if (object.getType() == EntityType.PIRANHA) {
			piranhas.add((Piranha) object);
		} else if (object.getType() == EntityType.SNAIL) {
			this.snail = (Snail) object;
		} else {
			return;
		}
		object.setSpace(this);
		object.setId(nObject);
		nObject++;
	}

	@Override
	public boolean isAbleMovingTo(double newX, double newY) {
		return (newX >= this.left &&
				newX <= this.right &&
				newY >= this.top &&
				newY <= this.bottom
			);
	}

	@Override
	public void remove(int entityId, EntityType entityType) {
		if (entityType == EntityType.COIN) {
			Coin coin = putOut(this.coins, entityId);
			if (coin != null) {
				this.money += coin.getValue();
			}
		} else if (entityType == EntityType.FOOD) {
			putOut(this.foods, entityId);
		} else if (entityType == EntityType.GUPPY) {
			putOut(this.guppies, entityId);
		} else if (entityType == EntityType.PIRANHA) {
			putOut(this.piranhas, entityId);
		} else if (entityType == EntityType.SNAIL) {
			this.snail = null;
		}
	} 

	@Override
	public boolean isExist(int entityId, EntityType entityType) {
		int index = -1;
		if (entityType == EntityType.COIN) {
			index = search(this.coins, entityId);
		} else if (entityType == EntityType.FOOD) {
			index = search(this.foods, entityId);
		} else if (entityType == EntityType.GUPPY) {
			index = search(this.guppies, entityId);
		} else if (entityType == EntityType.PIRANHA) {
			index = search(this.piranhas, entityId);
		} else if (entityType == EntityType.SNAIL) {
			if (this.snail != null && this.snail.getId() == entityId) {
				index = 0;
			}
		}
		return (index != -1);
	}

	public LinkedList<Coin> getCoins() {
		return new LinkedList<Coin>(this.coins);
	}

	public LinkedList<Food> getFoods() {
		return new LinkedList<Food>(this.foods);
	}

	public LinkedList<Guppy> getGuppies() {
		return new LinkedList<Guppy>(this.guppies);
	}

	public LinkedList<Piranha> getPiranhas() {
		return new LinkedList<Piranha>(this.piranhas);
	}

	public int getLeft() {
		return this.left;
	}

	public int getRight() {
		return this.right;
	}

	public int getTop() {
		return this.top;
	}

	public int getBottom() {
		return this.bottom;
	}

	public Snail getSnail() {
		return this.snail;
	}

	public boolean buy(int price) {
		if (this.money < price) {
			return false;
		}
		money = money - price;
		return true;
	}

	public int getMoney() {
		return this.money;
	}

	public boolean isGameOver() {
		return this.gameOver;
	}
}