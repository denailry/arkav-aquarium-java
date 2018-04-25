import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CoinTest {
	@Test
	public void testCoinValue(){
	  Coin coin = new Coin(3,4,100,100,1);
	  int value = coin.getValue();
	  assertEquals("Should return 1", 1, value);
	}
}