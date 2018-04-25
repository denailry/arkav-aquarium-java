import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FoodTest {
	@Test
	public void testFoodTick(){
		Aquarium aquarium = new Aquarium(0,100,0,100);
		Food food1 = new Food(3,4,10,10);
		boolean cek = aquarium.add(food1);
		food1.tick(0);
		assertNotEquals("Y should not be equal",4,food1.getY());		
	}

	@Test
	public void testFoodTickAtBottom(){
		Aquarium aquarium = new Aquarium(0,100,0,100);
		Food food2 = new Food(3,4,10,10);
		boolean cek = aquarium.add(food2);
		food2.tick(2);
		Food food1 = new Food(3,4,10,10);
		cek = aquarium.add(food1);
		food1.tick(0);
		assertEquals("The first element should be food1",food1,aquarium.getFoods().getFirst().getInfo());	
	}
}