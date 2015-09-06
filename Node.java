/*
 * This is a simple implementation of a LinkedList that stores integers.
 * This class includes basic constructors and simple methods that
 * manipulates the data structure in some way.
 */

public class Node {
    private Node next;
    private int value;

    /**
     * Constructor of a Node object given an int value.
     */
    public Node(int value) {
	this.value = value;
    }

    /**
     * Constructor of a Node object given an int array.
     */
    public Node(int[] nodes) {
	if (nodes.length == 0) {
	    return;
	}
	this.value = nodes[0];
	Node nextnode = this;
	for (int i = 1; i < nodes.length; i++) {
	    Node next = new Node(nodes[i]);
	    nextnode.next = next;
	    nextnode = next;
	}
    }

    @Override
    public String toString() {
	String returnString = Integer.toString(value);
	Node nextNode = this.next;
	while (nextNode != null) {
	    returnString += (" " + nextNode.value);
	    nextNode = nextNode.next;
	}
	return returnString;
    }

    /**
     * Reverses the node that is calling this method.
     * @return a Node that has the contents reversed.
     */
    public Node reverse() {
	Node reversed = new Node(value);
	Node nextNode = this.next;
	while (nextNode != null) {
	    Node temp = new Node(nextNode.value);
	    temp.next = reversed;
	    reversed = temp;
	    nextNode = nextNode.next;
	}
	return reversed;
    }

    /**
     * Removes a certain int value that is contained by the Node.
     * @param removeAll whether to remove all occurences or only the first
     * @param val the value to be removed from the Node
     * @return the Node object that has the value removed, if exists
     */
    public Node remove(boolean removeAll, int val) {
	if (removeAll) {
	    // we have to delete all instances of val
	    if (this.value == val) {
		// this Node should be removed and its child returned
		if (this.next == null) {
		    // if there is no child, return null instantly
		    return null;
		} else {
		    // return the child, but keep removing
		    return this.next.remove(removeAll, val);
		}
	    } else {
		// remove should be done to its children
		if (this.next == null) {
		    // no more child to perform remove, return this
		    return this;
		} else {
		    // the child should also perform remove
		    this.next = this.next.remove(removeAll, val);
		    return this;
		}
	    }
	} else {
	    // deleting only the first occurence should suffice.
	    if (this.value == val) {
		// this Node is removed and the next node takes its place.
		return this.next;
	    } else {
		// we should look into its children for the value.
		if (this.next == null) {
		    // no more child, return this.
		    return this;
		} else {
		    // the children should go through the remove process.
		    this.next = this.next.remove(removeAll, val);
		    return this;
		}
	    }
	}
    }

    public static void main(String[] args) {
	int[] integers = {1, 2, 4, 5, 3, 6};
	Node test = new Node(integers);
	System.out.println(test.toString());
	System.out.println(test.reverse().toString());

	int[] integers2 = {1, 2};
	Node test2 = new Node(integers2);
	System.out.println(test2.toString());
	System.out.println(test2.reverse().toString());

	Node test3 = new Node(5);
	System.out.println(test3.toString());
	System.out.println(test3.reverse().toString());

	int[] integers3 = {1, 4, 2, 5, 5, 4};
	Node test4 = new Node(integers3);
	System.out.println(test4.toString());
	System.out.println(test4.remove(true, 5));
	System.out.println(test4.remove(false, 4));
	System.out.println(test4.remove(true, 7));
	
    }

    public Node getNext() {
	return this.next;
    }

    public void setNext(Node node) {
	this.next = node;
    }

}
