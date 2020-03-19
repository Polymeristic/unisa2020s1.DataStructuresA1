package ds.students;

import ds.interfaces.List;
import jdk.jshell.spi.SPIResolutionException;

/**
 * @author simont
 *
 */
public class DSList implements List {

	/** Head node **/
	private Node head
			= null;

	/** Length of the list **/
	private int length
			= 0;

	/** Tail node **/
	private Node tail
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
	}

	/**
	 * Creates a new list from another list
	 * @param other Other list to copy
	 */
	public DSList(DSList other) { // Copy constructor. 
		head = other.head;
		tail = other.tail;
		length = 0;
	}

	/**
	 * Remove the object at the specified index from the list, if it exists.
	 * @param index The index to remove
	 * @return The object previously at the specified index
	 *
	 * @throws IndexOutOfBoundsException if the specified index is out of range
	 */
	public Node remove(int index) {
		Node tmp = null;

		// Check if in bounds
		if (index < 0 || index >= getLength())
			throw new IndexOutOfBoundsException("Index " + index + " is not within the bounds of this array (array length " + length + ").");

		// If first index (ie, head)
		if (index == 0) {
			// Set tmp and set head to next object
			tmp = head;
			setHead(head);
		} else
		// If last index (ie, tail)
		if (index == length - 1) {
			tmp = tail;

			if (length == 2)
				setTail(null);
			else
				setTail(tail.prev);
		} else {
			// Check if index is on left or right hand side of array
			boolean isOnL = index < (length / 2);

			// If on left side walk forwards, else walk backwards
			tmp = head;

			if (isOnL) {
				for (int i = 0; i <= index; i++) {
					if (i == index) break;
					tmp = tmp.next;
				}
			} else {
				int ib = Math.abs((index + 1) - length);
				for (int i = 0; i <= ib; i++) {
					if (i == index) break;
					tmp = tmp.prev;
				}
			}
		}

		return tmp;
	}
	
	public int indexOf(Token obj) {
		return 0;
	}

	public Node getNode(int index) {
		return null;
	}

	public Token get(int index) {
		return null;
	}

	public boolean isEmpty() {
		return true;
	}

	public int size() {
		return 0;
	}
	
	@Override
	public String toString() {
		return "";
	}


	/**
	 * Adds a value to the end of the list
	 * @param obj The object to add.
	 * @return If the object was added
	 */
	public boolean add(Token obj) {
		if (obj == null) throw new NullPointerException("Can't add null value to list");
		return add(length, obj);
	}

	/**
	 * Adds a value to a specific position in the list
	 * @param index Index at which to add the object
	 * @param obj The object to add
	 * @return If the object was added
	 */
	public boolean add(int index, Token obj) {
		if ()

		return false;
	}

	public boolean contains(Token obj) {
		return false;
	}

	public boolean remove(Token obj) {
		return true;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		return true;
	}

	/**
	 * Checks if an index is within the bounds of the list. Treats an index equal to the length of the list as valid,
	 * to allow for adding to the end of the list.
	 * @param index Index to check
	 * @return true if within the bounds
	 */
	private boolean indexInBounds(int index) {
		return 	index >= 0 &&
				index <= length;
	}

	/**
	 * Removes a node
	 * @param node Node to remove
	 */
	private void removeNode(Node node) {

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


		if (node.prev != null) {
			node.prev.next = newNode;
		} else {
			setHead(newNode);
		}

		if (node.next != null) {
			node.next.prev = newNode;
		} else {
			setTail(newNode);
		}

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

		newNode.prev = node.prev;
		newNode.next = node;

		if (newNode.prev != null)
			newNode.prev.next = newNode;

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

		newNode.next = node.next;

		if (newNode.next != null)
			newNode.next.prev = newNode;

		newNode.prev = node;
		node.next = newNode;
	}

	/**
	 * Gets the tail node
	 * @return The tail node
	 */
	private Node getTail() {
		return tail;
	}

	/**
	 * Sets the tail node
	 * @param tail New tail node
	 */
	private void setTail(Node tail) {
		this.tail = tail;
	}

	/**
	 * Gets the head node
	 * @return Head node
	 */
	private Node getHead() {
		return head;
	}

	/**
	 * Sets a new head node
	 * @param head New head node
	 */
	private void setHead(Node head) {
		this.head = head;
	}

	/**
	 * Gets the length of the list
	 */
	private int getLength() {
		return length;
	}
}
