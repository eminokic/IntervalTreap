import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntervalTreapTest2 {

	/**
	 * Adds two intervals to the treap and checks their priority
	 */
	@Test
	void test01CreateIntervalTreap() {
		Interval i = new Interval(0, 4);
		Node n = new Node(i);
		n.priority = 1;


		Interval j = new Interval(4, 5);
		Node o = new Node(j);
		o.priority = 2;

		IntervalTreap it = new IntervalTreap();
		it.intervalInsert(n);
		it.intervalInsert(o);

		//The root must have the lowest priority
		assert (it.root == n);
	}

	/**
	 * Same as test 02, but priorities are reversed
	 */
	@Test
	void test02CreateIntervalTreap() {
		Interval i = new Interval(0, 4);
		Node n = new Node(i);
		n.priority = 2;


		Interval j = new Interval(4, 5);
		Node o = new Node(j);
		o.priority = 1;

		IntervalTreap it = new IntervalTreap();
		it.intervalInsert(n);
		it.intervalInsert(o);

		//The root must have the lowest priority
		assert (it.root == o);
	}

	/**
	 * Same as test 2, but nodes are added in reverse order
	 */
	@Test
	void test03CreateIntervalTreap() {
		Interval i = new Interval(0, 4);
		Node n = new Node(i);
		n.priority = 1;


		Interval j = new Interval(4, 5);
		Node o = new Node(j);
		o.priority = 2;

		IntervalTreap it = new IntervalTreap();
		it.intervalInsert(o);
		it.intervalInsert(n);

		//The root must have the lowest priority
		assert (it.root == n);
	}


	/**
	 * Same as test 3, but nodes are added in reverse order
	 */
	@Test
	void test04CreateIntervalTreap() {
		Interval i = new Interval(0, 4);
		Node n = new Node(i);
		n.priority = 2;


		Interval j = new Interval(4, 5);
		Node o = new Node(j);
		o.priority = 1;

		IntervalTreap it = new IntervalTreap();
		it.intervalInsert(o);
		it.intervalInsert(n);

		//The root must have the lowest priority
		assert (it.root == o);

		checkIntevalTreap(it);
	}


	/**
	 * Same as test 4, but priorities are not enforced
	 */
	@Test
	void test05CreateIntervalTreap() {
		Interval i = new Interval(0, 4);
		Node n = new Node(i);


		Interval j = new Interval(4, 5);
		Node o = new Node(j);

		IntervalTreap it = new IntervalTreap();
		it.intervalInsert(o);
		it.intervalInsert(n);

		checkIntevalTreap(it);
	}



	/**
	 * Chuck a million nodes into the treap and check if it
	 * has treap-like properties
	 */
	@Test
	void test06CreateIntervalTreap() {
		IntervalTreap it = new IntervalTreap();

		int numNodes = 1000000;

		for(int i = 0; i < numNodes; i++) {
			insertInterval(it, i, i*2);
		}

		if(numNodes < 51) {
			printIntervalTreap(it);
		}

		assert numNodes == it.size;

		checkIntevalTreap(it);
	}

	/**
	 * Chuck a million <b>random</b> nodes into the 
	 * treap and check if it has treap-like properties
	 */
	@Test
	void test07CreateIntervalTreap() {
		IntervalTreap it = new IntervalTreap();
		Random rand = new Random();

		int numNodes = 1000000;

		for(int i = 0; i < numNodes; i++) {
			int r1 = rand.nextInt(),
					r2 = rand.nextInt();
			insertInterval(it, Math.min(r1,r2), Math.max(r1,r2));
		}

		if(numNodes < 51) {
			printIntervalTreap(it);
		}

		assert numNodes == it.size;

		checkIntevalTreap(it);
	}




	/**
	 * Test a couple cases where every node must bubble up
	 * to become the root node
	 */
	@Test
	void test08CreateIntervalTreap() {

		int numNodes = 10;

		IntervalTreap it = new IntervalTreap();

		for(int i = 0; i < numNodes; i++) {
			Node n = new Node(new Interval(i, i+8));
			n.priority = numNodes-i;
			it.intervalInsert(n);

			assert(it.root == n);
		}

		for(int i = 0; i < numNodes; i++) {
			Node n = new Node(new Interval(-i-8, -i));
			n.priority = -i;
			it.intervalInsert(n);

			assert(it.root == n);
		}

		checkIntevalTreap(it);
	}

	@Test
	void test09SquiggleTrees() {
		IntervalTreap it = new IntervalTreap();
		insertInterval(it, 0, 16, 1);
		insertInterval(it, 8, 15, 2);
		insertInterval(it, 4, 17, 3);

		checkIntevalTreap(it);

		it = new IntervalTreap();
		insertInterval(it, 0, 16, 1);
		insertInterval(it, 4, 17, 3);
		insertInterval(it, 8, 15, 2);

		checkIntevalTreap(it);

		it = new IntervalTreap();
		insertInterval(it, 4, 17, 3);
		insertInterval(it, 8, 15, 2);
		insertInterval(it, 0, 16, 1);

		checkIntevalTreap(it);

		it = new IntervalTreap();
		insertInterval(it, 4, 17, 4);
		insertInterval(it, 2, 16, 1);
		insertInterval(it, 0, 15, 2);
		insertInterval(it, 8, 14, 3);

		checkIntevalTreap(it);

	}


	/**
	 * Add entries until something breaks
	 */


//	@Test void test10CreateIntervalTreap() { 
//		IntervalTreap it = new IntervalTreap();
//		Random rand = new Random();
//
//		while(true) {
//
//			System.out.println("\n\n\n\n");
//
//			int r1 = rand.nextInt(),
//					r2 = rand.nextInt();
//			insertInterval(it, Math.min(r1,r2), Math.max(r1,r2));
//
//			System.out.println("\n"); printIntervalTreap(it);
//
//
//			checkSize(it); 
//			checkPriorities(it); 
//			checkTreeStructure(it); 
//			checkImax(it); 
//		}
//
//	}
	
	/**
	 * Tests the deletion of the root node when the treap
	 * only contains one node.
	 */
	@Test 
	public void test12DeleteOnlyNodeInTreap() {
		IntervalTreap it = new IntervalTreap();
		insertInterval(it, -5, 5);
		assert(it.size==1);
		assert(it.getHeight()==0);
		assert(it.getSize()==1);
		
		Node n = it.getRoot();
		it.intervalDelete(n);
		
		assert(it.root==null);
		assert(it.size==0);
		System.out.println(it.getHeight());
		assert(it.getHeight()<1);
		assert(it.getSize()==0);
	}
	
	/**
	 * Add a bunch of elements and delete them. <p>
	 * Only works if tests 07 and 08 work.
	 */
	@Test
	public void test13DeleteLast() {

		IntervalTreap it = new IntervalTreap();
		Random rand = new Random();

		int numNodes = 5000;
		
		//Begin addition
		for(int i = 0; i < numNodes; i++) {
			int r1 = rand.nextInt(),
					r2 = rand.nextInt();
			insertInterval(it, Math.min(r1,r2), Math.max(r1,r2));
		}
		
		checkIntevalTreap(it);
		
		//Begin deletion
		while(it.size!=0) {
			Node n = it.findLast();
			it.intervalDelete(n);
			
			checkIntevalTreap(it);
		}
	}
	
	/**
	 * Add a bunch of elements and delete them randomly. <p>
	 */
	@Test
	public void test14DeleteRandom() {
		

		int numNodes = 10000;

		IntervalTreap it = new IntervalTreap();
		Random rand = new Random();
		ArrayList<Node> nodes = new ArrayList<Node>(numNodes);

		//Begin addition
		for(int i = 0; i < numNodes; i++) {
			int r1 = rand.nextInt(),
					r2 = rand.nextInt();
			Node n = new Node(new Interval(Math.min(r1,r2), Math.max(r1,r2)));
			nodes.add(n);
			it.intervalInsert(n);
		}
		
		checkIntevalTreap(it);
		
		//Shuffle the ArrayList so objects are deleted
		//randomly.
		Collections.shuffle(nodes);
		
		for(Node n: nodes) {
			it.intervalDelete(n);
			
			//Check size
			assert(it.getSize()==numNodes-1);
			numNodes--;
			
			//Check treap structure
			checkIntevalTreap(it);
		}
	}
	
	/**
	 * Add a thousand random nodes and check that getHeight() is correct
	 */
	@Test public void test15GetHeight() {
		IntervalTreap it = new IntervalTreap();
		Random rand = new Random();

		int numNodes = 1000;

		for(int i = 0; i < numNodes; i++) {
			int r1 = rand.nextInt(),
					r2 = rand.nextInt();
			insertInterval(it, Math.min(r1,r2), Math.max(r1,r2));
			
			testHeight(it);
		}

	}
	


	//Helper methods


	/**
	 * Recursively check that the priority of parent nodes
	 * is less than that of their children
	 * @param n the node to begin searching
	 */
	void testPrioritiesRecursive(Node n) {
		if(n==null) return;

		if(n.left!=null) {
			assert (n.priority<n.left.priority);
			testPrioritiesRecursive(n.left);
		}

		if(n.right!=null) {
			assert (n.priority<n.right.priority);
			testPrioritiesRecursive(n.right);
		}
	}

	/**
	 * Test that the structure of the treap
	 * follows the treap property that the priority
	 * of each node is less than that of all its
	 * descendants.
	 * @param it
	 */
	void checkPriorities(IntervalTreap it) {
		testPrioritiesRecursive(it.root);
	}

	/**
	 * Shorthand for the following <p>
	 * <code>it.intervalInsert(new Node(new Interval(low, high)));</code>
	 * @param it an interval treap
	 * @param low the low value of the interval to insert in to the treap
	 * @param high the high value of the interval to insert in to the treap
	 */
	void insertInterval(IntervalTreap it, int low, int high) {
		it.intervalInsert(new Node(new Interval(low, high)));
	}

	/**
	 * Shorthand for the following <p>
	 *
	 * <code>
	 *
	 * Node n = new Node(new Interval(low, high));                   <p>
	 * n.priority = priority;                                        <p>
	 * it.intervalInsert(n);                                         <p>
	 *
	 * </code>
	 *
	 * @param it an interval treap
	 * @param low the low value of the interval to insert in to the treap
	 * @param high the high value of the interval to insert in to the treap
	 * @param priority the priority of the node created to encapsulate the interval
	 * that is to be added to the interval treap
	 */
	void insertInterval(IntervalTreap it, int low, int high, int priority) {
		Node n = new Node(new Interval(low, high));
		n.priority = priority;
		it.intervalInsert(n);
	}

	/**
	 * Recursively check that a node n and all its children follow the
	 * following rules:
	 * <p>
	 * Let k be the lowest value of the interval in a node
	 * <p>
	 * 1. n.left.k  < n.k
	 * <p>
	 * 2. n.right.k > n.k
	 *
	 * @param n the node to begin searching
	 */
	void checkTreeStructureRecursive(Node n) {
		if(n==null) return;
		
		if(n.left!=null) {
			assert (n.left.interv.getLow()<=n.interv.getLow());
			checkTreeStructureRecursive(n.left);
		}

		if(n.right!=null) {
			assert (n.right.interv.getLow()>=n.interv.getLow());
			checkTreeStructureRecursive(n.right);
		}

		//Check node relationships to see if they
		//are mutual

		//Ensure that n is its parent's child, if the parent exists
		if(n.parent!=null) {
			Node p = n.parent;
			if(p.left!=n && p.right!=n)
				fail("Node is not a child of its parent");
		}

		//Ensure that the children of n have their
		//parent fields set to n
		if(n.left!=null) {
			if(n.left.parent==null)
				fail("Left child's parent is null");
			if(n.left.parent!=n)
				fail("Left child's parent is incorrect");
		}

		if(n.right!=null) {
			if(n.right.parent==null)
				fail("Right child's parent is null");
			if(n.right.parent!=n)
				fail("Right child's parent is incorrect");
		}
	}

	/**
	 *
	 * Recursively check that each node n and all its children follow the
	 * following rules:
	 * <p>
	 * Let k be the lowest value of the interval in a node
	 * <p>
	 * 1. n.left.k  < n.k
	 * <p>
	 * 2. n.right.k > n.k
	 *
	 * @param it the IntervalTreap to search
	 */
	void checkTreeStructure(IntervalTreap it) {
		checkTreeStructureRecursive(it.root);
	}

	/**
	 * Recursively check that all IMax values are what they should be
	 * @param n the node to begin searching at
	 */
	void checkImaxRecursive(Node n) {
		if(n==null) return;
		
		assert(calculateImax(n)==n.imax);

		if(n.left != null) checkImaxRecursive(n.left);
		if(n.right != null) checkImaxRecursive(n.right);
	}

	/**
	 * Calculates what IMax should be for a given node
	 * @param n the node to calculate IMax for
	 * @return IMax
	 */
	int calculateImax(Node n) {
		int ret = n.interv.getHigh();

		if(n.left != null) ret = Math.max(ret, calculateImax(n.left));
		if(n.right != null) ret = Math.max(ret, calculateImax(n.right));

		return ret;
	}

	/**
	 * Recursively check that all IMax values in a treap are what
	 * they should be
	 * @param it
	 */
	void checkImax(IntervalTreap it) {

		if(it.root==null) return;
		
		checkImaxRecursive(it.root);
	}

	/**
	 * Calculates the number of nodes in a subtree
	 * @param n the node to calculate IMax for
	 * @return IMax
	 */
	int sizeOfSubtree(Node n) {
		if(n==null) return 0;

		int ret = 1;

		if(n.left != null) ret += sizeOfSubtree(n.left);
		if(n.right != null) ret += sizeOfSubtree(n.right);

		return ret;
	}

	/**
	 * Check the size of an interval treap
	 * @param it
	 */
	void checkSize(IntervalTreap it) {
		if(it.root==null) assert it.size==0;
		int diff = it.getSize()-sizeOfSubtree(it.root);
		if(diff!=0) {
			if(diff>0) fail("expected size of interval treap is " + diff + " greater than actual size");
			else fail("actual size of interval treap is " + -diff + " greater than expected size");
		}
	}

	/**
	 * Prints the contents of the subtree starting at a node
	 * @param n the node that is the subtree's root
	 * @param depth the initial depth of the node, used for recursion
	 */
	void printSubtreeRecursive(Node n, int depth) {
		for(int i = 0; i < depth-1; i++) {
			System.out.print("|   ");
		}
		if(depth!=0) System.out.print("|---");

		if(n==null) {
			System.out.println();
			return;
		}

		int expectedIMax = calculateImax(n);

		System.out.print(n.imax);
		if(n.imax!=expectedIMax) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.print(" exp. ");
			System.err.print(expectedIMax);
		}

		System.out.print(" (");
		System.out.print(n.interv.getLow());
		System.out.print(", ");
		System.out.print(n.interv.getHigh());
		System.out.println(")");

		if(n.left!=null || n.right!=null) {
			printSubtreeRecursive(n.left, depth+1);
			printSubtreeRecursive(n.right, depth+1);
		}

	}
	
	/**
	 * Asserts that the height of the treap is correct
	 * @param it
	 */
	void testHeight(IntervalTreap it) {
		if(it.root==null) assert(it.getHeight()<1);
		else {
			int i = testHeightRecursive(it.root, 0);
			int j = it.getHeight();
			assert(j==i);
		}
	}
	
	/**
	 * Helper method for testHeight()
	 * @param n the node to calculate the depth of
	 * @param currentHeight the depth of n
	 * @return the depth of the tree formed by n
	 */
	int testHeightRecursive(Node n, int currentHeight) {
		int h = currentHeight;
		if(n.left!=null) h = testHeightRecursive(n.left, currentHeight+1);
		if(n.right!=null) h = Math.max(h, testHeightRecursive(n.right, currentHeight+1));
		return h;
	}

	/**
	 * Recursively prints the contents of an interval treap.
	 * <p>
	 * The first number is the low value of the interval in the node. <br>
	 * The second number is the high value of the interval in the node. <br>
	 * The third number is the imax value of the node. <br>
	 *
	 * @param it
	 */
	void printIntervalTreap(IntervalTreap it) {
		printSubtreeRecursive(it.root, 0);
	}
	
	public void checkIntevalTreap(IntervalTreap it) {
		checkSize(it);
		checkPriorities(it);
		checkTreeStructure(it);
		checkImax(it);
	}
	

}