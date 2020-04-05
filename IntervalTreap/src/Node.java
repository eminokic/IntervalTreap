import java.util.Random;


/**
 * Node Class 
 * represents the nodes of the treap. 
 * 
 * @author Emin Okic
 * @author ekeneokeke
 *
 */
public class Node {
	
	private int priority;
	private Interval in;
	private Node left;
	private Node right;
	private Node parent;
	private int imax;
	
	/**
	 * Constructor 
	 * that takes an Interval object i as its parameter
	 * must generate a priority for the node.
	 * Therefore, after creation of a Node object,
	 * getPriority() must return the priority of this node.
	 * 
	 * @param i
	 * 
	 */
	public Node (Interval i) {
		in=i;
		imax=i.getHigh();
		Random rand = new Random();
		priority=rand.nextInt(Integer.MAX_VALUE);
		left=null;
		right=null;
		parent=null;
	}
	
	/**
	 * Get Parent Method
	 * Returns the parent of this node.
	 * 
	 * @return parent
	 */
	public Node getParent() {
		
		return parent;
	}
	
	
	/**
	 * Get Left Child Method
	 * Returns the left child of this node.
	 * 
	 * @return left
	 */
	public Node getLeft() {
		
		return left;
	}
	
	/**
	 * Get Right Child Method
	 * Returns the right child of this node.
	 * 
	 * @return right
	 */
	public Node getRight() {
		return right;
	}
	
	/**
	 * Get Interval Method
	 * Returns the interval object stored in this node.
	 * 
	 * @return in
	 */
	public Interval getInterv() {
		return in;
	}
	
	
	/**
	 * Get IMax Method
	 * Returns the value of the Imax field of this node.
	 * 
	 * @return max
	 */
	public int getIMax() {
		
		return imax;
	}
	
	
	/**
	 * Get Priority Method
	 * Returns the priority of this node.
	 * 
	 * @return priority
	 */
	public	int getPriority() {
		return priority;
	}
	
	// additional methods that we need for this problem
	
	// set I max 
	public void setImax(int imax) {
		this.imax=imax;
	}
	
	public void setLeft(Node left) {
		this.left=left;
	}
	
	public void setRight(Node right) {
		this.right=right;
	}
	
	public void setParent(Node parent) {
		this.parent=parent;
	}
	
	
	
}