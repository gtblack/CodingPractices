import java.util.ArrayList;

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
    }

}
