import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SnailTest {

	@Test
	public void testSnailIsAbleToConsume(){
		Aquarium aquarium = new Aquarium(200,200,200,200);	
	  	Snail snail = new Snail(3,4,100,100);
	  	Coin coin = new Coin(3,4,50,50,50);
	  	aquarium.add(coin);
	  	assertTrue("Should be able to consume", snail.isAbleToConsume(coin));
	}

	@Test
	public void testSnailFindNearestCoin(){
		LinkedList<Coin> coins = new LinkedList<Coin>();
		Coin coin1 = new Coin(9,10,50,50,50);
		Coin coin2 = new Coin(3,4,50,50,50);
		Coin coin3 = new Coin(20,25,50,50,50);
		Snail snail = new Snail(4,5,100,100);
		coins.add(coin1);
		coins.add(coin2);		
		coins.add(coin3);
		Coin nearestCoin = snail.findNearestCoin(coins);
		assertEquals("Should be coin2", coin2, nearestCoin);
	}
}