public class EntityTest {
	class DummySpace extends Space {
		public static final int ENTITY_UNABLE_MOVING = 0;
		public static final int ENTITY_ABLE_MOVING = 1;

		boolean moveTo(int entityId, int entityType, double newX, double newY) {
			if (entityId == ENTITY_ABLE_MOVING) {
				return true; 
			} else {
				return false;
			}
		}	

		void remove(int entityId, int entityType) {}

		boolean isExist(int entityId, int entityType) {
			if (entityId == ENTITY_UNABLE_MOVING) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Test
	public void testConstruction() {
		int x = 0;
		int y = 0;

		Entity coin = new Entity(x, y, EntityType.COIN);
		Entity guppy = new Entity(x, y, EntityType.GUPPY);
		Entity piranha = new Entity(x, y, EntityType.PIRANHA);
		Entity food = new Entity(x, y, EntityType.FOOD);
		Entity snail = new Entity(x, y, EntityType.SNAIL);

		assertEquals("Entity should be on x=" + String.valueOf(x), x, coin.getX());
		assertEquals("Entity should be on y=" + String.valueOf(y), y, coin.getY());
		assertEquals("Coin should have type COIN", EntityType.COIN, coin.getType());
		assertEquals("Guppy should have type GUPPY", EntityType.GUPPY, guppy.getType());
		assertEquals("Piranha should have type PIRANHA", EntityType.PIRANHA, piranha.getType());
		assertEquals("Food should have type FOOD", EntityType.FOOD, food.getType());
		assertEquals("Snail should have type SNAIL", EntityType.SNAIL, snail.getType());
	}

	@Test
	public void testEntitySize() {
		int x = 0;
		int y = 0;
		Entity coin = new Entity(x, y, EntityType.COIN);
		double width = 100;
		double height = 100;
		coin.setWidth(width);
		coin.setHeight(height);
		assertEquals("Wrong coin's left collision", x-width/2, coin.getLeft());
		assertEquals("Wrong coin's right collision", x+width/2, coin.getRight());
		assertEquals("Wrong coin's top collision", y-height/2, coin.getTop());
		assertEquals("Wrong coin's bottom collision", y+height/2, coin.getBottom());
	}

	@Test 
	public void testEntityMove() {
		DummySpace space = new DummySpace();

		int x = 0;
		int y = 0;

		Entity coin = new Entity(x, y, EntityType.COIN);
		coin.setId(DummySpace.ENTITY_UNABLE_MOVING);
		coin.setSpace(space);

		Entity food = new Entity(x, y, EntityType.FOOD);
		food.setId(DummySpace.ENTITY_ABLE_MOVING);
		food.setSpace(space);

		int newX = 100;
		int newY = 100;

		assertTrue(food.move(newX, newY));
		assertEquals("Food should be able to move", newX, food.getX());
		assertEquals("Food should be able to move", newY, food.getY());

		assertFalse(coin.move(newX, newY));
		assertEquals("Coin should not be able to move", x, coin.getX());
		assertEquals("Coin should not be able to move", y, coin.getY());
	}

	@Test
	public void testEntityRemove() {
		Entity coin = new Entity(0, 0, EntityType.COIN);
		coin.remove();
	}

	@Test
	public void testEntityExistence() {
		Entity coin = new Entity(0, 0, EntityType.COIN);
		coin.setId(DummySpace.ENTITY_ABLE_MOVING);
		Entity food = new Entity(0, 0, EntityType.FOOD);
		food.setId(DummySpace.ENTITY_UNABLE_MOVING);
		assertTrue("Coin should be exist", coin.isExist());
		assertFalse("Food should not be exist", food.isExist());
	} 

	@Test
	public void testEntityTick() {
		Entity coin = new Entity(0, 0, EntityType.COIN);
		int nTick = 5;
		for (int i = 0; i < nTick; i++) {
			coin.tick();
		}
		assertEquals("Entity's tick do not work properly", nTick, coin.getTime());
	}
}