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
		Aquarium aquarium = new Aquarium(200,200,200,200);	
	  	Piranha piranha = new Piranha(3,4,100,100);
	  	Guppy guppy = new Guppy(3,4,50,50);
	  	aquarium.add(guppy);
	  	assertTrue("Should be able to consume", piranha.isAbleToConsume(guppy));
	}

	@Test
	public void testPiranhaFindNearestGuppy(){
		LinkedList<Guppy> guppies = new LinkedList<Guppy>();
		Guppy guppy1 = new Guppy(9,10,50,50);
		Guppy guppy2 = new Guppy(3,4,50,50);
		Guppy guppy3 = new Guppy(20,25,50,50);
		Piranha piranha = new Piranha(4,5,100,100);
		guppies.add(guppy1);
		guppies.add(guppy2);		
		guppies.add(guppy3);
		Guppy nearestGuppy = piranha.findNearestGuppy(guppies);
		assertEquals("Should be guppy2", guppy2, nearestGuppy);
	}
}