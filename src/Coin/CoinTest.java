import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CoinTest {
	@Test
	public void testCoinValue(){
		Coin coin = new Coin(3,4,100,100,1);
		int value = coin.getValue();
		assertEquals("Should return 1", 1, value);
	}

	@Test
	public void testCoinTick(){
		Aquarium aquarium = new Aquarium(200,200,200,200);	
		Coin coin = new Coin(3,4,100,100,1);
		aquarium.add(coin);
		coin.tick(2);
		assertNotEquals("Y should not be equal",4,coin.getY());
	}
}