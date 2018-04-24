import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class LinkedListTest {
	@Test
	public void testNoParamConstruction() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		assertEquals("No param construction should have null first", null, list.getFirst());
		assertEquals("No param construction should have zero size", 0, list.size());
	}

	@Test
	public void testAddElementToLinkedList()  {
		LinkedList<Integer> list = new LinkedList<Integer>();
		Integer one = 1;
		Integer two = 2;
		Integer three = 3;
		list.add(one);
		list.add(two);
		list.add(three);
		assertEquals("Size after add 3 element should be 3", 3, list.size());
		assertEquals("Element 'One' should be exist at index 0", one, list.get(0));
		assertEquals("Element 'Two' should be exist at index 1", two, list.get(1));
		assertEquals("Element 'Three' should be exist at index 2", three, list.get(2));
	}


	@Test
	public void testAddLinkedListToLinkedList() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		Integer one = 1;
		Integer two = 2;
		Integer three = 3;
		list.add(one);
		list.add(two);
		list.add(three);

		LinkedList<Integer> addedList = new LinkedList<Integer>();
		addedList.add(list);
		assertEquals("Size after adding list should be 3", 3, addedList.size());
		assertEquals("Element 'One' should be exist at index 0", one, addedList.get(0));
		assertEquals("Element 'Two' should be exist at index 1", two, addedList.get(1));
		assertEquals("Element 'Three' should be exist at index 2", three, addedList.get(2));
	}

	@Test
	public void testCopyConstruction() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		Integer one = 1;
		Integer two = 2;
		Integer three = 3;
		list.add(one);
		list.add(two);
		list.add(three);

		LinkedList<Integer> copyList = new LinkedList<Integer>(list);
		assertEquals("Size after copying 3 element should be 3", 3, copyList.size());
		assertEquals("Element 'One' should be exist at index 0", one, copyList.get(0));
		assertEquals("Element 'Two' should be exist at index 1", two, copyList.get(1));
		assertEquals("Element 'Three' should be exist at index 2", three, copyList.get(2));
	}
}