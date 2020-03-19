package pack;

import java.util.Iterator;

/**
 * Iterator used for iterating though a linked Node chain
 */
public class NodeIterator implements Iterator<String> {
    Node current;

    /**
     * Create a new node iterator from a head node
     * @param head Head node
     */
    public NodeIterator(Node head) {
        current = head;
    }

    /**
     * If there is a next node in the chain
     * @return true if a next node exists
     */
    @Override
    public boolean hasNext() {
        return current != null;
    }

    /**
     * Gets the next node in the chain
     * @return the next node
     */
    @Override
    public String next() {
        String d = current.data;
        current = current.next;

        return d;
    }
}
