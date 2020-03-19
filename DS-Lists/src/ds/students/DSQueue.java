package ds.students;

import ds.interfaces.Queue;

/**
 * @author simont
 *
 */
public class DSQueue extends Queue {
	
	public DSQueue(Queue s) {
	}

	public DSQueue() {
	}

	@Override
	public boolean offer(Token t) {
		return false;
	}

	@Override
	public Token poll() {
		return null;
	}

	@Override
	public Token peek() {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

}
