package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * @author Arav Tewari
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E>
{
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList()
	{
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element The element to add
	 */
	public boolean add(E element)
	{
		
		if (element == null)
		{
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> n = new LLNode<E>(null);
		n.data = element;
		
		if (size == 0)
		{
			n.prev = head;
			n.next = tail;
			
			head = n;
			tail = n;
		}
		else
		{
			n.prev = tail;
			n.next = null;
			
			tail.next = n;
			tail = n;
		}
		size++;
		return true;
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E get(int index)
	{
		// TODO: Implement this method.
		LLNode<E> n = null;
		if (index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			n = head;
			for (int i = 0; i < index; i++)
			{
				if (n == null)
				{
					throw new IndexOutOfBoundsException();
				}
				
				n = n.next;
				if (n.data == null)
				{
					throw new NullPointerException();
				}
			}
		}
		return n.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The     index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element)
	{
		// TODO: Implement this method
		
		LLNode<E> temp = new LLNode<E>(null);
		temp.data = element;
		LLNode<E> n = head;
		
		if (element == null)
		{
			throw new NullPointerException();
		}
		else
		{
			if (index == 0)
			{
				if (size == 0)
				{
					head = temp;
					tail = temp;
				}
				else
				{
					temp.next = n.next;
					temp.prev = null;
					head = temp;
				}
			}
			else if (index > 0 && index < size)
			{
				for (int i = 0; i < (index - 1); i++)
				{
					n = n.next;
				}
				
				temp.next = n.next;
				temp.prev = n;
				n.next.prev = temp;
				n.next = temp;
			}
			else if (index == size)
			{
				add(element);
			}
			else
			{
				throw new IndexOutOfBoundsException();
			}
			size++;
		}
	}

	/** Return the size of the list */
	public int size()
	{
		// TODO: Implement this method
		return size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index)
	{
		LLNode<E> n = new LLNode<E>(null);
		LLNode<E> temp;
		
		
		if (size == 0)
		{
			throw new IndexOutOfBoundsException();
		}
		else if (size == 1)
		{
			if (index == 0)
			{
				n.data = head.data;
				head = null;
				tail = null;
			}
			else
			{
				throw new IndexOutOfBoundsException();
			}
		}
		else
		{
			if (index == 0)
			{
				n.data = head.data;
				temp = head;
				head = temp.next;
				temp.next.prev = null;
			}
			else if (index > 0 && index < size)
			{
				temp = head;
				for (int i = 0; i < (index - 1); i++)
				{
					temp = temp.next;
				}
				
				n.data = temp.next.data;
				temp.next.prev = temp.prev;
				temp.prev.next = temp.next;
			}
			else if (index == size)
			{
				n.data = tail.data;
				temp = tail;
				tail = temp.prev;
				temp.prev.next = null;
			}
			else
			{
				throw new IndexOutOfBoundsException();
			}
		}
		size--;
		return n.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index   The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element)
	{
		LLNode<E> temp = head;
		
		if (element == null)
		{
			throw new NullPointerException();
		}
		else
		{
			if (index > 0 && index < size)
			{
				for (int i = 0; i < (index - 1); i++)
				{
					temp = temp.next;
				}
				temp.next.data = element;
			}
			else if (index == 0)
			{
				temp.data = element;
			}
			else
			{
				throw new IndexOutOfBoundsException();
			}
		}
		return temp.data;
	}
	
	@Override
	public String toString()
	{
		LLNode<E> n = head;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < (size - 1); i++)
		{
			sb.append(n.data + ", ");
			n = n.next;
		}
		return sb.toString();
	}
}

class LLNode<E>
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e)
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
