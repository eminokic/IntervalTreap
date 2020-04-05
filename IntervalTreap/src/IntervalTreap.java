import java.util.ArrayList;
import java.util.LinkedList; 
import java.util.List;
import java.lang.Math;

/**
 * Interval Treap Class 
 * represents an interval treap
 * 
 * @author Emin Okic
 * @author ekeneokeke
 *
 */
public class IntervalTreap {
	// private variables are located here
	private Node root;
	private int size;
	private int height;
	
	/**
	 * Constructor with no parameters.
	 * Runtime: Constant
	 */
	public IntervalTreap() {
		size=0;
		height=0;
		root=null;
	}
	
	
	/**
	 * Get Root Method
	 * Returns a reference to the root node.
	 * Runtime: Constant
	 * 
	 * @return root
	 */
	public Node getRoot() {
		return root;
	}
	
	
	/**
	 * Get Size Method
	 * Returns the number of nodes in the treap.
	 * Runtime: Constant
	 * 
	 * @return size
	 */
	public int getSize() {
		return size;
	}
	
	
	/**
	 * Get Height Method
	 * Returns the height of the treap.
	 * Runtime: Constant
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}
	
	/*
	 * Interval Insert Method
	 * Adds node z, whose interval interv attribute references 
	 * an Interval object, to the interval treap.
	 * This operation must maintain the required interval treap properties
	 * Runtime: O(logn) on an n-node interval treap
	 * 
	 */
	public void intervalInsert(Node z) {
		//TODO
		z.setImax(z.getInterv().getHigh());
		int key = z.getInterv().getLow();
		Node x=root;
		if(root ==null) {
			root=z;
		}
		while(x!=null) {
			
			if(key<x.getInterv().getLow()) {
				x=x.getLeft();
			}
			else if(key>x.getInterv().getLow()) {
				x=x.getRight();
			}
			
			x.setImax(Integer.max(x.getIMax(), z.getInterv().getHigh()));	
		}
		if(x==null) {
			x=z;
		}
		
		// the rotation part of the algorithm
		while(true) {
			
			if(x.getPriority()<x.getParent().getPriority()) {  // breaks if this is true
				break;
			}
			
			
		
		if(!(x.getPriority()<x.getParent().getPriority())) {
			
			if(x.getParent().getLeft()==x) {
				
				rotateRight(x);
				
				if(x.getLeft()==null && x.getRight()==null) {
					x.setImax(x.getInterv().getHigh());
				}
				else if(x.getRight()==null && x.getLeft()!=null) {
					x.setImax(Integer.max(x.getInterv().getHigh(), x.getLeft().getIMax()));
				}
				else if(x.getLeft()==null && x.getRight()!=null) {
					x.setImax(Integer.max(x.getInterv().getHigh(),x.getRight().getIMax()));
				}
				else 
				{
					x.setImax(Integer.max(x.getRight().getIMax(), Integer.max(x.getInterv().getHigh(), x.getLeft().getIMax())));
				}
			}
			else if(x.getParent().getRight()==x) {
				
				rotateLeft(x);
				
				if(x.getLeft()==null && x.getRight()==null) {
					x.setImax(x.getInterv().getHigh());
				}
				else if(x.getRight()==null && x.getLeft()!=null) {
					x.setImax(Integer.max(x.getInterv().getHigh(), x.getLeft().getIMax()));
				}
				else if(x.getLeft()==null && x.getRight()!=null) {
					x.setImax(Integer.max(x.getInterv().getHigh(),x.getRight().getIMax()));
				}
				else 
				{
					x.setImax(Integer.max(x.getRight().getIMax(), Integer.max(x.getInterv().getHigh(), x.getLeft().getIMax())));
				}
			}
			
		}
		
		}
		
		
		}
	
	/*
	 * Helper Methods to Interval Insert
	 */
	private void rotateLeft(Node u) {
		   Node w = u.getRight();
	        w.setParent(u.getParent());
	        if (w.getParent() != null) {
	            if (w.getParent().getLeft() == u) {
	            		w.getParent().setLeft(w);
	            } else {
	            		w.getParent().setRight(w);
	            }
	        }
	        
	        u.setRight(w.getLeft());
	        if(u.getRight() != null) {
	        		u.getRight().setParent(u);
	        		
	        }
	     u.setParent(w);
	        w.setLeft(u);
	        if(u==root) {
	        		root=w;
	        		root.setParent(null);
	        		
	        }
	}
	
	
	private void rotateRight(Node u) {
		Node w=u.getLeft();
		w.setParent(u.getParent());
		
		if(w.getParent() != null) 
		{
			if(w.getParent().getLeft() == u) {
				w.getParent().setLeft(w);
			}
			else {
				w.getParent().setRight(w);
			}
		}
		u.setLeft(w.getRight());
		if(u.getLeft()!=null) {
			u.getLeft().setParent(u);
		}
		u.setParent(w);
		w.setRight(u);
		if(u==root) {
			root=w;
			root.setParent(null);
		}
	}
	
	/*
	 * Interval Delete Method
	 * removes node z, from the interval treap 
	 * This operation must maintain the required interval treap properties
	 * Runtime: O(logn) on an n-node interval treap
	 * 
	 */
	public void intervalDelete(Node z) {
		//TODO
		int key=z.getInterv().getLow();
		deletingN(this.root,key);
	}
	
	private Node deletingN(Node root,int key) {
		
		int rootkey=root.getInterv().getLow();
		
		
		if(key<rootkey) {
			root.setLeft(deletingN(root.getLeft(),key));
		}
		else if(key> rootkey) {
			root.setRight(deletingN(root.getRight(), key));
		}
		else {
			if(root.getLeft() == null && root.getRight() == null) {
    
                return null;
            } else if(root.getLeft() == null) {
               
                return root.getRight();
            } else if(root.getRight() == null) {
            
                return root.getLeft();
            } else {
      
            		Node min=minValue(root.getRight());
            		root.setImax(min.getIMax());
            		root.setRight(deletingN(root.getRight(),min.getInterv().getLow()));
            		
            }

		}
	
		return root;
	}
	
	

//	/**
//	 * Private method that is used to perform an inorder transversal
//	 * @param n
//	 * @return temp2
//	 */
//	private Node inOrderSuccessor(Node n) {
//		if(n.getRight() !=null) {
//			return minValue(n.getRight());
//		}
//		
//		Node temp2=n.getParent();
//		
//		while(temp2!=null && n==temp2.getRight()) {
//			n=temp2;
//			temp2=temp2.getParent();
//		}
//		return temp2;
//	}
//
//	
	/**
	 * Private method that is used to find the inorderSuccessor
	 * @param node
	 * @return temp
	 */
	private Node minValue(Node node) {
		Node temp=node;
		
		while(temp.getLeft() != null) {
			temp=temp.getLeft();
		}
		return temp;
	}
	


	/*
	 * Interval Search Method 
	 * returns a reference to node x in the interval treap
	 * such that x.inter overlaps interval i, 
	 * or null if no such element is in the treap
	 * 
	 * This method must not modify the interval treap.
	 * Runtime: O(logn) on an n-node interval treap.
	 * 
	 * @return reference to node x in the interval treap
	 */
	public Node intervalSearch(Interval i) {	
		//TODO
		
		/* Example Algo 
		 * 
		 * INTERVAL-SEARCH(T, i)
		 * 	x = T.root
		 * 	while x != T.nil and i does not overlap x.int
		 * 		if x.left != T.nil and x.left.max > i.low
		 * 			x = x.left
		 * 		else x = x.right
		 * 	return x
		 */
		
		Node x= root;
		while(x!=null) {
			if((i.getLow()<=x.getInterv().getHigh())	|| (x.getInterv().getLow()<=i.getHigh())	) {
				
				break;
			}
			if(x.getLeft()!=null && x.getLeft().getIMax()>=i.getLow())
			{
				
				x=x.getLeft();
			}
			else {
				x=x.getRight();
			}
			
			
		}
		
		return x;
	}
	
	/*
	 * Interval Search Exactly Method
	 * Extra Credit / Optional 
	 * 
	 * Returns a reference to a Node object x in the treap
	 * such that x.inter.low = i.low and x.interv.high = i.high
	 * or null if no such node exists
	 * 
	 * This method must not modify the interval treap.
	 * Runtime: O(logn) on an n-node interval treap.
	 * 
	 * @return reference to a Node object x in the treap
	 */
	Node intervalSearchExactly(Interval i) {
		//TODO
		
		/*
		 * Example Algo
		 * 
		 * INTERVAL-SEARCH-EXACTLY(T, i)
		 * 	x = T.root
		 * 	while x != T.nil and i not exactly overlap x
		 * 		if i.high > x.max
		 *  		x = T.nil
		 *  	else if i.low < x.low
		 *  		x = x.left
		 *  	else if i.low > x.low
		 *  		x = x.right
		 *  	else x = T.nil
		 *  return x
		 */
		
		Node x = root;
		
		while(x!=null)
		{
			if((i.getLow()<=x.getInterv().getHigh())	|| (x.getInterv().getLow()<=i.getHigh())	) {
				
				break;
			}
			
			if(i.getHigh() > x.getIMax()) {
				x=null;
			}
			else if(i.getLow() < x.getInterv().getLow()) {
				x=x.getLeft();
			}
			else if(i.getLow() > x.getInterv().getLow()) {
				x=x.getRight();
			}
			else {
				x=null;
			}
		}
		
		
		
		return x;
	}
	
	/*
	 * Overlapping Intervals Method 
	 * Extra Credit / Optional 
	 * 
	 * Returns a list of all intervals in the treap that overlap i
	 * 
	 * This method must not modify the interval treap.
	 * Runtime: O( min( n, klogn ) ) where k is the number of intervals in the output list.
	 * 
	 * @return a list of all intervsals in the treap that overlap i
	 */
	List<Interval> overlappingIntervals(Interval i) {
		//TODO
		
		/*
		 * Example Algo 
		 * 
		 * INTERVALS-SEARCH(T, x, i)
		 * 	let list be an empty array
		 * 	if i overlaps x.int
		 * 		list.APPEND(x)
		 * 	if x.left != T.nil and x.left.max > i.low
		 * 		list = list.APPEND(INTERVALS-SEARCH(T, x.left, i))
		 * 	if x.right != T.nil and x.int.low ≤ i.high and x.right.max ≥ i.low
		 * 		list = list.APPEND(INTERVALS-SEARCH(T, x.right, i))
		 * 	return list
		 */
		
		List<Interval> list = new ArrayList<Interval>();
		Node x = intervalSearch(i);
		
		if((i.getLow()<=x.getInterv().getHigh())	|| (x.getInterv().getLow()<= i.getHigh())	) {
			list.add(x.getInterv());
		}
		
		if(x.getLeft()!=null && x.getLeft().getIMax()>i.getLow()) {
			// i have to complete this part of this
		}
		
		return null;
	}
	
}