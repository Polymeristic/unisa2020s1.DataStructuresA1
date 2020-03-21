package ds.students;

import ds.interfaces.Queue;

/**
 * @author Oliver Mitchell (mitoj001)
 */
public class DSQueue extends Queue {

	/**
	 * Queue to copy
	 * @param s Queue to copy
	 */
	public DSQueue(Queue s) {
		this.list = new DSList(s.list);
	}

	/**
	 * Creates an empty queue
	 */
	public DSQueue() {
		list = new DSList();
	}

	/**
	 * Inserts the given object into the Queue if possible.
	 * @param t Token to insert
	 * @return True if the object was inserted.
	 *
	 * @throws NullPointerException if the given object is null.
	 */
	@Override
	public boolean offer(Token t) {
		return list.add(t);
	}

	/**
	 * Inserts a number of given objects into the Queue, in order, if possible.
	 * @param tokens Tokens to insert
	 * @return True if the object was inserted
	 */
	public boolean offer(Token... tokens) {
		return list.add(tokens);
	}

	/**
	 * Removes and returns the head of the Queue.
	 * @return The head of the Queue.
	 */
	@Override
	public Token poll() {
		Token t = list.get(0);
		if (t != null) {
			list.remove(0);
		}

		return t;
	}

	/**
	 * Retrieves, but does not remove, the head of this Queue.
	 * If the Queue is empty, returns null.
	 * @return The head of the Queue.
	 */
	@Override
	public Token peek() {
		return list.get(0);
	}

	/**
	 * Converts this queue to a list
	 * @return String representation of the queue
	 */
	@Override
	public String toString() {
		return list.toString();
	}

	/**
	 * Gets the size of this queue
	 * @return Size of the queue
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * If this queue is empty
	 * @return If the queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
