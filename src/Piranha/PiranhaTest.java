import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PiranhaTest {
	@Test
	public void testPiranhaLastFed(){
	  Piranha piranha = new Piranha(3,4,100,100);
	  piranha.setLastFed(10);
	  int lastFed = piranha.getLastFed();
	  assertEquals("Should return 10", 10, lastFed);
	}

	@Test
	public void testPiranhaIsAbleToConsume(){
	  Piranha piranha = new Piranha(3,4,100,100);
	  Guppy guppy = new Guppy(3,4,50,50);
	  assertTrue("Should be able to consume", piranha.isAbleToConsume(guppy));
	}
}