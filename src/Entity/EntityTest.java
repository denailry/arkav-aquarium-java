import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class EntityTest {
	public class DummySpace implements Space {
		public static final int ENTITY_UNABLE_MOVING = 0;
		public static final int ENTITY_ABLE_MOVING = 1;

		public static final double VALID_X = 0;
		public static final double VALID_Y = 0;

		@Override
		public boolean isAbleMovingTo(double newX, double newY) {
			if (newX == VALID_X && newY == VALID_Y) {
				return true; 
			} else {
				return false;
			}
		}	

		@Override
		public void remove(int entityId, EntityType entityType) {}

		@Override
		public boolean isExist(int entityId, EntityType entityType) {
			if (entityId == ENTITY_UNABLE_MOVING) {
				return false;
			} else {
				return true;
			}
		}
	}

	private double delta;

	@Before
	public void initialization() {
		this.delta = 0.0001;
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

		assertEquals("Entity should be on x=" + String.valueOf(x), x, coin.getX(), delta);
		assertEquals("Entity should be on y=" + String.valueOf(y), y, coin.getY(), delta);
		assertEquals("Coin should have type COIN", EntityType.COIN, coin.getType());
		assertEquals("Guppy should have type GUPPY", EntityType.GUPPY, guppy.getType());
		assertEquals("Piranha should have type PIRANHA", EntityType.PIRANHA, piranha.getType());
		assertEquals("Food should have type FOOD", EntityType.FOOD, food.getType());
		assertEquals("Snail should have type SNAIL", EntityType.SNAIL, snail.getType());
		assertEquals("Entity should have id=-1 when entity just constructed", -1, coin.getId());
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
		assertEquals("Wrong coin's left collision", x-width/2, coin.getLeft(), this.delta);
		assertEquals("Wrong coin's right collision", x+width/2, coin.getRight(), this.delta);
		assertEquals("Wrong coin's top collision", y-height/2, coin.getTop(), this.delta);
		assertEquals("Wrong coin's bottom collision", y+height/2, coin.getBottom(), this.delta);
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

		assertTrue("Food should be able to move", food.move(DummySpace.VALID_X, DummySpace.VALID_Y));
		assertEquals("Food should have been moved to x=" + DummySpace.VALID_X, DummySpace.VALID_X, food.getX(), delta);
		assertEquals("Food should have been moved to y=" + DummySpace.VALID_Y, DummySpace.VALID_Y, food.getY(), delta);

		assertFalse("Coin should not be able to move", coin.move(DummySpace.VALID_X+1, DummySpace.VALID_Y+1));
		assertEquals("Coin should not be moved", x, coin.getX(), delta);
		assertEquals("Coin should not be moved", y, coin.getY(), delta);
	}

	@Test
	public void testEntityRemove() {
		Entity coin = new Entity(0, 0, EntityType.COIN);
		coin.setSpace(new DummySpace());
		coin.remove();
	}

	@Test
	public void testEntityExistence() {
		Entity coin = new Entity(0, 0, EntityType.COIN);
		coin.setSpace(new DummySpace());
		coin.setId(DummySpace.ENTITY_ABLE_MOVING);
		Entity food = new Entity(0, 0, EntityType.FOOD);
		food.setSpace(new DummySpace());
		food.setId(DummySpace.ENTITY_UNABLE_MOVING);
		assertTrue("Coin should be exist", coin.isExist());
		assertFalse("Food should not be exist", food.isExist());
	} 

	@Test
	public void testEntityTick() {
		Entity coin = new Entity(0, 0, EntityType.COIN);
		int nTick = 5;
		for (int i = 0; i < nTick; i++) {
			coin.tick(0);
		}
		assertEquals("Entity's tick do not work properly", nTick, coin.getTime());
	}
}