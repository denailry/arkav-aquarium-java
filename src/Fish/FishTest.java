import org.junit.Test;
import static org.junit.Assert.assertFalse;

public class FishTest {
	@Test
	public void testDirRight() {
		Fish fish = new Fish(0,0,EntityType.GUPPY);
		fish.setDirRight(false);
		assertFalse("DirRight getter or setter do not work properly",fish.getDirRight());
	}
		
	@Test
	public void testHunger() {
		Fish fish = new Fish(0,0,EntityType.GUPPY);
		fish.setHunger(false);
		assertFalse("Hunger getter or setter do not work properly",fish.getHunger());
	}
}