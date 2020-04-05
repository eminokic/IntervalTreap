
/*
 * Interval Class
 * represents intervals
 * 
 * @author Emin Okic
 * @author ekeneokeke
 */
public class Interval {
	
	private int lowI;
	private int highI;
	
	/**
	 * Constructor with two parameters: the low
	 * and the high endpoints.
	 * 
	 * @param low
	 * @param high
	 */
	public Interval(int low, int high) {
		lowI=low;
		highI=high;
	}
	
	/**
	 *  Returns the low endpoint of the interval.
	 * @return lowI
	 */
	
	public int getLow() {
		return lowI;
	}
	
	/**
	 * 
	 * Returns the high endpoint of the interval.
	 * @return highI
	 */
	
	public int getHigh() {
		return highI;
	}

}
