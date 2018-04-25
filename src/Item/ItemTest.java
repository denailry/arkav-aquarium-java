import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ItemTest {
	@Test
	public void testItem(){
	  Item item1 = new Item(0,0,EntityType.FOOD);
		Item item2 = new Item(0,0,EntityType.FOOD);
		assertEquals("Should be equal", item1, item2);
	}
}