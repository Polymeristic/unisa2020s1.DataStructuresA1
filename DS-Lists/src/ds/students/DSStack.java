package ds.students;

import java.util.EmptyStackException;
import ds.interfaces.Stack;

/**
 * @author Oliver Mitchell (mitoj001)
 */
public class DSStack extends Stack {

	@Override
	public int hashCode() {
		return 0;
	}

	/**
	 * Check if two stacks are equal
	 * @param other other stack
	 * @return If two stacks are equal
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other) 					return true;
		if (other == null) 					return false;
		if (getClass() != other.getClass()) return false;
		DSStack l = (DSStack) other;

		return list.equals(l.list);
	}

	/**
	 * Creates an empty stack
	 */
	public DSStack() {
		list = new DSList();
	}

	/**
	 * Creates a new stack by copying another
	 * @param other stack to copy
	 */
	public DSStack(DSStack other) {
		list = new DSList(other.list);
	}

	/**
	 * Adds the given object to the top of the Stack.
	 * @return The given object.
	 */
	public Token push(Token obj) {
		list.add(obj);
		return obj;
	}

	/**
	 * Returns, without removing, the object at the top of the Stack.
	 * @return the object at the top of the Stack.
	 *
	 * @throws EmptyStackException if the stack is empty.
	 */
	public Token peek() {
		if (list.isEmpty())
			throw new EmptyStackException();

		return list.get(list.size() - 1);
	}

	/**
	 * Returns and removes the object at the top of the Stack.
	 * @return the object at the top of the Stack.
	 *
	 * @throws EmptyStackException if the stack is empty.
	 */
	public Token pop() {
		if (list.isEmpty())
			throw new EmptyStackException();

		Token n = list.get(list.size() - 1);
		list.remove(list.size() - 1);

		return n;
	}

	/**
	 * Returns true if this collection contains no elements.
	 * @return True if the collection is empty.
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Returns the size of the stack
	 * @return Size of the stack
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Converts this object into it's string representation
	 * @return string representation
	 */
	@Override
	public String toString() {
		return list.toString();
	}
	


}
