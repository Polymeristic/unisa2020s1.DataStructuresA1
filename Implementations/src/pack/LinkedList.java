package pack;


import java.util.Iterator;

/**
 * @author simont
 *
 */
public class LinkedList implements List, Iterable<String> {

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
    public LinkedList() { }

    /**
     * Create a new list with a given head
     * @param head_ Head of the list
     */
    public LinkedList(Node head_) {
        head = head_;
        tail = head_;
    }

    public LinkedList(String... items) {
        add(items);
    }

    /**
     * Creates a new list from another list
     * @param other Other list to copy
     */
    public LinkedList(LinkedList other) { // Copy constructor.
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
        Node node = getNode(index);
        removeNode(node);

        return node;
    }

    public int indexOf(String obj) {
        Node n = head;

        for (int i = 0; i < length; i++) {
            if (n.data.equals(obj))
                return i;

            n = n.next;
        }

        return -1;
    }

    public Node getNode(int index) {
        if (indexOutOfBounds(index))
            throw new IndexOutOfBoundsException("Index " + index + " of bounds in list. Length is " + index);

        if (isMostEfficientForwards(index)) {
            return getNodeForwards(index);
        } else {
            return getNodeBackwards(reverseIndexDirection(index));
        }
    }

    public String get(int index) {
        return getNode(index).data;
    }

    public boolean isEmpty() {
        return length == 0 || head == null || tail == null;
    }

    public int size() {
        return length;
    }

    @Override
    public String toString() {
        Node n = head;

        if (n == null)
            return "List (Empty)";

        StringBuilder s = new StringBuilder();

        for (String i : this) {
            s.append(i).append(", ");
        }

        return "List (" + s.substring(0, s.length() - 2) + ")";
    }

    /**
     * Adds multiple objects to the linked list
     * @param obj Objects to add
     * @return if the operation was successful
     */
    public boolean add(String... obj) {
        for (String s : obj) {
            add(s);
        }

        return true;
    }

    public boolean add(String obj) {
        if (obj == null)
            throw new NullPointerException("Can't add null value to list");

        Node n = new Node();
        n.data = obj;

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
    public boolean add(int index, String obj) {
        if (obj == null)
            throw new NullPointerException("Can't add null value to list");

        if (indexOutOfBounds(index))
            throw new IndexOutOfBoundsException("Can't insert at index " + index + ", out of bounds. Length of list is " + length);

        Node n = new Node();
        n.data = obj;

        insertBeforeNode(getNode(index), n);

        return true;
    }

    public boolean contains(String obj) {
        return indexOf(obj) != -1;
    }

    public boolean remove(String obj) {
        int i = indexOf(obj);

        if (i != -1) {
            remove(i);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        return false;
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
     *
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    private Node getNodeBackwards(int index) {
        if (indexOutOfBounds(index))
            throw new IndexOutOfBoundsException("Index " + index + " is out of the bounds of the array. Length is " + length);

        Node n = tail;

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
     *
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    private Node getNodeForwards(int index) {
        if (indexOutOfBounds(index))
            throw new IndexOutOfBoundsException("Index " + index + " is out of the bounds of the array. Length is " + length);

        Node n = head;

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
        if (node == null) {
            throw new NullPointerException("Node cannot be null when removing.");
        }

        length --;

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            setHead(node.next);
        }

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
     * Iterator for this linked list
     * @return An iterator instance
     */
    @Override
    public Iterator<String> iterator() {
        return new NodeIterator(head);
    }
}
