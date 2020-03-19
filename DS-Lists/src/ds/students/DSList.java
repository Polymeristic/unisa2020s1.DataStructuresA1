package ds.students;

import ds.interfaces.List;

/**
 * @author simont
 *
 */
public class DSList implements List {
	
	public Node head;
	private Node tail;

	public DSList() {
		
	}
	public DSList(Node head_) {
	}
	
	public DSList(DSList other) { // Copy constructor. 
		
	}

	public Token remove(int index) {
		
		return null;
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

	public boolean add(Token obj) {
		return true;
	}

	public boolean add(int index, Token obj) {
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
	
}
