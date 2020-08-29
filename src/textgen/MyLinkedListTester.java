/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Arav Tewari
 *
 */
public class MyLinkedListTester
{

	private static final int LONG_LIST_LENGTH = 10;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		// Feel free to use these lists, or add your own
		shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);

	}

	/**
	 * Test if the get method is working correctly.
	 */
	/*
	 * You should not need to add much to this method. We provide it as an example
	 * of a thorough test.
	 */
	@Test
	public void testGet()
	{
		// test empty list, get should throw an exception
		try
		{
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e)
		{

		}

		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try
		{
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e)
		{

		}
		try
		{
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e)
		{

		}
		// test longer list contents
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
		}

		// test off the end of the longer array
		try
		{
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e)
		{

		}
		try
		{
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e)
		{
		}

	}

	/**
	 * Test removing an element from the list. We've included the example from the
	 * concept challenge. You will want to add more tests.
	 */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
	}

	/**
	 * Test adding an element into the end of the list, specifically public boolean
	 * add(E element)
	 */
	@Test
	public void testAddEnd()
	{
		assertEquals("Last element is correct", shortList.get(1), "B");
		assertEquals("First element is correct", (Integer)65, list1.get(0));
	}

	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Empty List is correct", (Integer)0, (Integer)emptyList.size());
		assertEquals("Any other size is correct", (Integer)3, (Integer)list1.size());
	}

	/**
	 * Test adding an element into the list at a specified index, specifically:
	 * public void add(int index, E element)
	 */
	@Test
	public void testAddAtIndex()
	{
		list1.add(2,14);
		emptyList.add(0,15);
		assertEquals("Element is inserted correctly", emptyList.get(0), (Integer)15);
	}

	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		int a = list1.set(0,45);
		assertEquals("Set: check a is correct ", 45, a);
		try
		{
			String x = shortList.set(2,"C");
			fail("Check out of Bounds");
		}
		catch(IndexOutOfBoundsException e)
		{}	    
	}

}
