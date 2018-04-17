import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SampleTest {
	@Test
	public void returnsOneIfGivenOne() {
	  Sample sample = new Sample();
	  int result = sample.getResult(1);
	  assertEquals("Should return 1", 1, result);
	}

	@Test
	public void returnsTwoIfGivenTwo() {
		Sample sample = new Sample();
	  	int result = sample.getResult(2);
		assertEquals("Should return 2", 2, result);
	}
}