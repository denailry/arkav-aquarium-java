import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AquariumTest {
	
	private int left;
	private int right;
	private int top;
	private int bottom;
	private Aquarium aquarium;

	private String pointToString(int x, int y) {
		return "<" + String.valueOf(x) + "," + String.valueOf(y) + ">";
	}

	@Before
	public void initialize() {
		this.left = 0;
		this.right = 100;
		this.top = 0;
		this.bottom = 100;
		this.aquarium = new Aquarium(this.left, this.right, this.top, this.bottom);
	}

	@Test
	public void testBoundaryInitialization() {
		assertEquals("Wrong left boundary value from getLeft", this.left, this.aquarium.getLeft());
		assertEquals("Wrong right boundary value from getRight", this.right, this.aquarium.getRight());
		assertEquals("Wrong top boundary value from getTop", this.top, this.aquarium.getTop());
		assertEquals("Wrong bottom boundary value from getBottom", this.bottom, this.aquarium.getBottom());
	}

	@Test
	public void testMoney() {
		assertEquals("Starting money should be 20", 20, aquarium.getMoney());
		assertTrue("Should be okay to buy with price 10, first buy", aquarium.buy(10));
		assertTrue("Should be okay to buy with price 10, second buy", aquarium.buy(10));
		assertFalse("Should not be okay to buy with price 1, third buy", aquarium.buy(1));	
	}

	@Test
	public void testMoveInsideBoundary() {
		int newX;
		int newY;

		newX = this.top + 5; 
		newY = this.left + 5;
		assertTrue("1. (inside) Should be able to move to " + pointToString(newX, newY), this.aquarium.isAbleMovingTo(newX, newY));
		newX = this.left;
		newY = this.top;
		assertTrue("2. (inside) Should be able to move to " + pointToString(newX, newY), this.aquarium.isAbleMovingTo(newX, newY));
	}

	@Test
	public void testMoveOutsideBoundary() {
		int newX;
		int newY;

		newX = this.left - 1;
		newY = this.top;
		assertFalse("1. (outside) Should not be able to move to " + pointToString(newX, newY), this.aquarium.isAbleMovingTo(newX, newY));
		newX = this.left;
		newY = this.bottom + 1;
		assertFalse("2. (outside) Should not be able to move to " + pointToString(newX, newY), this.aquarium.isAbleMovingTo(newX, newY));
		newX = this.right + 5;
		newY = this.bottom + 5;
		assertFalse("3. (outside) Should not be able to move to " + pointToString(newX, newY), this.aquarium.isAbleMovingTo(newX, newY));
	}

	@Test
	public void testAddEntity() {
		Coin coin = new Coin(0, 0, 0, 0, 0);
		Food food = new Food(0, 0, 0, 0);
		Guppy guppy = new Guppy(0, 0, 0, 0);
		Piranha piranha = new Piranha(0, 0, 0, 0);
		Snail snail = new Snail(0, 0, 0, 0);
		Coin something = new Coin(0, 0, 0, 0, 0);

		this.aquarium.add(coin);
		this.aquarium.add(food);
		this.aquarium.add(guppy);
		this.aquarium.add(piranha);
		this.aquarium.add(snail);

		assertTrue("Coin should be exist", this.aquarium.isExist(coin.getId(), EntityType.COIN));
		assertTrue("Food should be exist", this.aquarium.isExist(food.getId(), EntityType.FOOD));
		assertTrue("Piranha should be exist", this.aquarium.isExist(piranha.getId(), EntityType.PIRANHA));
		assertTrue("Guppy should be exist", this.aquarium.isExist(guppy.getId(), EntityType.GUPPY));
		assertTrue("Snail should be exist", this.aquarium.isExist(snail.getId(), EntityType.SNAIL));
		assertFalse("Something should not be exist", this.aquarium.isExist(something.getId(), EntityType.COIN));
	}

	@Test
	public void testRemoveEntity() {
		this.aquarium.remove(0, EntityType.COIN);
		this.aquarium.remove(1, EntityType.FOOD);
		this.aquarium.remove(2, EntityType.GUPPY);
		this.aquarium.remove(3, EntityType.PIRANHA);
		this.aquarium.remove(4, EntityType.SNAIL);

		assertFalse("Coin should have been removed", this.aquarium.isExist(0, EntityType.COIN));
		assertFalse("Food should have been removed", this.aquarium.isExist(1, EntityType.FOOD));
		assertFalse("Piranha should have been removed", this.aquarium.isExist(2, EntityType.GUPPY));
		assertFalse("Guppy should have been removed", this.aquarium.isExist(3, EntityType.PIRANHA));
		assertFalse("Snail should have been removed", this.aquarium.isExist(4, EntityType.SNAIL));
	}

	@Test
	public void testTick() {
		this.aquarium.tick(0);
	}

	@Test
	public void testGameOver() {
		Aquarium aq = new Aquarium(0, 0, 0, 0);
		aq.buy(aq.getMoney() - 9);
		aq.tick(0);
		assertTrue("Should have been game over", aq.isGameOver());
	}
}