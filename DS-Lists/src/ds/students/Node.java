package ds.students;

/**
 * @author Oliver Mitchell (mitoj001)
 */
public class Node {

	/**
	 * Next node
	 */
	public Node next;

	/**
	 * Previous node
	 */
	public Node prev;

	/**
	 * Data at this node
	 */
	public Token data;

	/**
	 * Create a new node
	 */
	public Node() { }

	/**
	 * Creata a new node with only the data set
	 * @param data Data of the node
	 */
	public Node(Token data) {
		this.data = data;
	}

	/**
	 * Create a new node and setup the initial pointers
	 * @param next Next node
	 * @param prev Previous node
	 * @param token Token data at the node
	 */
	public Node(Node next, Node prev, Token token) {
		this.next = next;
		this.prev = prev;
		this.data = token;
	}

	/**
	 * If two nodes's data are equal
	 * @param other Other node
	 * @return true if they are equal
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof Node))
			return false;

		return data.equals(((Node)other).data);
	}

	/**
	 * Gets the hash code for a node
	 * @return Hash for this node
	 */
	@Override
	public int hashCode() {
		if ( data == null )
			return 0;
		return data.hashCode();
	}
}
