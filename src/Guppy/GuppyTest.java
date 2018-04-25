import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GuppyTest {
	@Test
	public void testGuppyLastFed(){
	  	Guppy guppy = new Guppy(3,4,100,100);
	  	guppy.setLastFed(10);
		int lastFed = guppy.getLastFed();
	  	assertEquals("Should return 10", 10, lastFed);
	}

	@Test
	public void testGuppyIsAbleToConsume(){
		Aquarium aquarium = new Aquarium(200,200,200,200);	
	  	Guppy guppy = new Guppy(3,4,100,100);
	  	Food food = new Food(3,4,50,50);
	  	aquarium.add(food);
	  	assertTrue("Should be able to consume", guppy.isAbleToConsume(food));
	}

	@Test
	public void testGuppyFindNearestFood(){
		LinkedList<Food> foods = new LinkedList<Food>();
		Food food1 = new Food(9,10,50,50);
		Food food2 = new Food(3,4,50,50);
		Food food3 = new Food(20,25,50,50);
		Guppy guppy = new Guppy(4,5,100,100);
		foods.add(food1);
		foods.add(food2);		
		foods.add(food3);
		Food nearestFood = guppy.findNearestFood(foods);
		assertEquals("Should be food2", food2, nearestFood);
	}
}