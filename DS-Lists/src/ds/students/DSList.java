package ds.students;

import ds.interfaces.List;

import java.awt.image.TileObserver;
import java.util.Iterator;


/**
 * @author Oliver Mitchell (mitoj001)
 */
public class DSList implements List, Iterable<Token> {

	/** Head node **/
	public Node head
			= null;

	/** Length of the list **/
	private int length
			= 0;

	/** Tail node **/
	public Node tail
			= null;

	/**
	 * Create a new list with no defined head
	 */
	public DSList() { }

	/**
	 * Create a new list with a given head
	 * @param head_ Head of the list
	 */
	public DSList(Node head_) {
		head = head_;
		recalculateTail();
	}

	/**
	 * Adds multiple tokens to a given token
	 * @param items List of tokens to add
	 */
	public DSList(Token... items) {
		add(items);
	}

	/**
	 * Creates a new list from another list
	 * @param other Other list to copy
	 */
	public DSList(DSList other) { // Copy constructor.
		head = other.head;
		tail = other.tail;
		length = other.length;
	}

	/**
	 * Remove the object at the specified index from the list, if it exists.
	 * @param index The index to remove
	 * @return The object previously at the specified index
	 *
	 * @throws IndexOutOfBoundsException if the specified index is out of range
	 */
	public Token remove(int index) {
		// Get node and remove
		Node node = getNode(index);
		removeNode(node);

		return node.getToken();
	}

	public int indexOf(Token obj) {
		Node n = head;

		// Go through each node and return index of object if equals param
		for (int i = 0; i < length; i++) {
			if (n.getToken().equals(obj))
				return i;

			n = n.next;
		}

		return -1;
	}

	/**
	 * Gets the node that is associated with a token
	 * @param t token to get node of
	 * @return the node that contains the token
	 *
	 * @throws NullPointerException if token is null
	 */
	private Node getTokenNode(Token t) {
		if (t == null)
			throw new NullPointerException("Token cannot be null");

		// If no head return null
		if (head == null)
			return null;

		Node walker = head;

		// Go through list walking forwards, if data equals token, return that node
		while (walker != null) {
			if (walker.getToken().equals(t))
				return walker;

			walker = walker.next;
		}

		return null;
	}

	/**
	 * Gets a node at the specified index, if it exists
	 * @param index Index to retrieve
	 * @return The node at the specified index
	 */
	private Node getNode(int index) {
		if (indexOutOfBounds(index))
			return null;

		// Check direction efficiency and choose to walk back or forwards
		if (isMostEfficientForwards(index)) {
			return getNodeForwards(index);
		} else {
			return getNodeBackwards(reverseIndexDirection(index));
		}
	}

	/**
	 * Get the object at the specified index, if it exists.
	 * @param index The index to retrieve
	 * @return The object at the specified index, if it exists, and null if it does not exist
	*/
	public Token get(int index) {
		// Get index of node and return null if not found/in range
		Node n = getNode(index);
		return (n == null) ? null : n.getToken();
	}

	/**
	 * Returns true if this list contains no elements.
	 * @return True if the list is empty.
	 */
	public boolean isEmpty() {
		return length == 0 || head == null || tail == null;
	}

	/**
	 * Returns the number of elements in this list.
	 * @return The number of elements in this list.
	 */
	public int size() {
		return length;
	}

	/**
	 * Returns a string containing the toString()
	 * 	for each object in this list.
	 * @return The concatenated toString() for each element in this list
	 */
	@Override
	public String toString() {
		Node n = head;

		// If empty return blank string
		if (n == null)
			return "";

		// Build string by iterating items
		StringBuilder s = new StringBuilder();

		for (Token i : this) {
			s.append(i).append(" ");
		}

		return s.substring(0, s.length() - 1);
	}

	/**
	 * Appends multiple specified elements to the end of this list.
	 * @param obj The objects to add.
	 * @return True if the objects have been added to the list.
	 *
	 * @throws NullPointerException if the specified object is null
	 */
	public boolean add(Token... obj) {
		// Add all
		for (Token s : obj) {
			add(s);
		}

		return true;
	}

	/**
	 * Appends the specified element to the end of this list.
	 * @param obj The object to add.
	 * @return True if the object has been added to the list.
	 *
	 * @throws NullPointerException if the specified object is null
	 */
	public boolean add(Token obj) {
		if (obj == null)
			throw new NullPointerException("Can't add null value to list");

		Node n = new Node();
		n.setToken(obj);

		// Check if head and tail is set, if not, set to object
		if (head == null ||
			tail == null) {
			head = n;
			tail = n;

			length = 1;
		} else {
			insertAfterNode(tail, n);
		}

		return true;
	}

	/**
	 * Adds a value to a specific position in the list
	 * @param index Index at which to add the object
	 * @param obj The object to add
	 * @return If the object was added
	 */
	public boolean add(int index, Token obj) {
		if (obj == null)
			throw new NullPointerException("Can't add null value to list");

		Node newNode = new Node(obj);

		// Check if inserting at end, if yes, insert after instead of before node
		if (index == length) {
			insertAfterNode(tail, newNode);
		} else {
			if (indexOutOfBounds(index))
				return false;

			insertBeforeNode(getNode(index), newNode);
		}

		return true;
	}

	/**
	 * Check if the list contains a token
	 * @param obj The object whose presence is to be tested
	 * @return True if the list contains the token
	 */
	public boolean contains(Token obj) {
		return indexOf(obj) != -1;
	}

	/**
	 * Find a token in the list and remove it
	 * @param obj The object to remove
	 * @return If the token was removed
	 */
	public boolean remove(Token obj) {
		Node n = getTokenNode(obj);

		// If node not found return false
		if (n == null)
			return false;

		removeNode(n);

		return true;
	}

	/**
	 * Gets the hashCode of the list
	 * @return Hash code
	 */
	@Override
	public int hashCode() {
		// Get the string and return the string's hash
		return toString().hashCode();
	}

	/**
	 * Checks if two lists are equal
	 * @param other Other list
	 * @return True if they are equal
	 */
	@Override
	public boolean equals(Object other) {
		// Check basic equals
		if (this == other) 					return true;
		if (other == null) 					return false;
		if (getClass() != other.getClass()) return false;
		DSList l = (DSList) other;
		if (l.length != length) 			return false;

		Iterator<Token> iterA = iterator();
		Iterator<Token> iterB = l.iterator();

		// Iterate through both at same time, if any values dont match, return false
		while (iterA.hasNext()) {
			if (!iterA.next().equals(iterB.next())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Reverses the direction in which an index is referenced and returns a new index in the opposite direction
	 * @param index Index to reverse
	 * @return The reversed index
	 */
	private int reverseIndexDirection(int index) {
		return Math.abs((index + 1) - length);
	}

	/**
	 * Checks if the most efficient direction for an index to take is forwards
	 * @return true if the most efficient direction is forwards
	 */
	private boolean isMostEfficientForwards(int index) {
		return index < (length / 2);
	}

	/**
	 * Finds a Node using an index by walking backwards
	 * @param index Index to find (reversely indexed)
	 * @return Node at index
	 */
	private Node getNodeBackwards(int index) {
		if (indexOutOfBounds(index))
			return null;

		Node n = tail;

		// Walk backwards to the specified index
		for (int i = 0; i <= index; i++) {
			if (i == index) break;
			n = n.prev;
		}

		return n;
	}

	/**
	 * Finds a Node using an index by walking forwards
	 * @param index Index to find
	 * @return Node at index
	 */
	private Node getNodeForwards(int index) {
		if (indexOutOfBounds(index))
			return null;

		Node n = head;

		// Walk forward to the specified index
		for (int i = 0; i <= index; i++) {
			if (i == index) break;
			n = n.next;
		}

		return n;
	}

	/**
	 * Checks if an index is within the bounds of the list.
	 * to allow for adding to the end of the list.
	 * @param index Index to check
	 * @return true if within the bounds
	 */
	private boolean indexOutOfBounds(int index) {
		return  index < 0 ||
				index >= length;
	}

	/**
	 * Removes a node
	 * @param node Node to remove
	 *
	 * @throws NullPointerException when argument is null
	 */
	private void removeNode(Node node) {
		if (node == null)
			throw new NullPointerException("Node cannot be null when removing.");

		length --;

		// Check if was head, if so, set next to new head
		if (node.prev != null) {
			node.prev.next = node.next;
		} else {
			setHead(node.next);
		}

		// Check if was tail, if so, set prev to new tail
		if (node.next != null) {
			node.next.prev = node.prev;
		} else {
			setTail(node.prev);
		}
	}

	/**
	 * Replaces a node with a new node
	 * @param node Node to replace
	 * @param newNode New node
	 *
	 * @throws NullPointerException when arguments are null
	 */
	private void replaceNode(Node node, Node newNode) {
		if (node == null || newNode == null)
			throw new NullPointerException("Node, or new node, cannot be null when replacing");


		// Check if was head, if so, set to new node
		if (node.prev != null) {
			node.prev.next = newNode;
		} else {
			setHead(newNode);
		}

		// Check if was tail, if so, set to new node
		if (node.next != null) {
			node.next.prev = newNode;
		} else {
			setTail(newNode);
		}

		// Insert self
		newNode.next = node.next;
		newNode.prev = node.prev;
	}

	/**
	 * Inserts a node before the specified node
	 * @param node Base node
	 * @param newNode Node to add
	 *
	 * @throws NullPointerException when arguments are null
	 */
	private void insertBeforeNode(Node node, Node newNode) {
		if (node == null || newNode == null)
			throw new NullPointerException("Node, or new node, cannot be null when inserting");


		// Put self before a node, and remap
		length ++;
		newNode.prev = node.prev;
		newNode.next = node;

		if (newNode.prev != null)
			newNode.prev.next = newNode;
		else
			setHead(newNode);

		node.prev = newNode;
	}

	/**
	 * Inserts a node after the specified node
	 * @param node Base node
	 * @param newNode Node to add
	 *
	 * @throws NullPointerException when arguments are null
	 */
	private void insertAfterNode(Node node, Node newNode) {
		if (node == null || newNode == null)
			throw new NullPointerException("Node, or new node, cannot be null when inserting");

		// put self after node and remap
		length ++;
		newNode.next = node.next;

		if (newNode.next != null)
			newNode.next.prev = newNode;
		else
			setTail(newNode);

		newNode.prev = node;
		node.next = newNode;
	}

	/**
	 * Sets the tail node
	 * @param tail New tail node
	 */
	private void setTail(Node tail) {
		this.tail = tail;
	}

	/**
	 * Sets a new head node
	 * @param head New head node
	 */
	private void setHead(Node head) {
		this.head = head;
	}

	/**
	 * Recalculates the tail if it has become disembodied, will also recalculate the length
	 */
	private void recalculateTail() {
		if (head == null) {
			tail = null;
			return;
		}

		Node n = head;
		length = 1;

		// Go through by walking forwards to calculate length
		while (n.next != null) {
			length++;
			n = n.next;
		}

		// Set tail to last Node in chain
		tail = n;
	}


	/**
	 * Iterator for this linked list
	 * @return An iterator instance
	 */
	@Override
	public Iterator<Token> iterator() {
		return new NodeIterator(head);
	}
}
