import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ElementTest {
	@Test
	public void testNoParamConstruction() {
		Element<Integer> element = new Element();
		assertEquals("No param construction should have null next", null, element.getNext());
		assertEquals("No param construction should have null info", null, element.getInfo());
	}

	@Test
	public void testInfoParamConstruction() {
		Integer integer = 0;
		Element<Integer> element = new Element(integer);
		assertEquals("Info param construction should have null next", null, element.getNext());
		assertEquals("Info param construction should have non-null info", integer, element.getInfo());
	}

	@Test
	public void testSetter() {
		Element<Integer> something = new Element();
		Integer integer = 0;

		Element<Integer> element = new Element();
		element.setInfo(integer);
		element.setNext(something);

		assertEquals("Set info do not work properly", integer, element.getInfo());
		assertEquals("Set next do not work properly", something, element.getNext());
	}
}