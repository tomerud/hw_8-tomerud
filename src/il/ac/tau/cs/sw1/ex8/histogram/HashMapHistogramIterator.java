package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class HashMapHistogramIterator<T extends Comparable<T>> 
							implements Iterator<Map.Entry<T, Integer>>{
	
	//add members here
	
	//add constructor here, if needed
	
	@Override
	public boolean hasNext() {
		// add your code here
		return false; //replace this with the correct value
	}

	@Override
	public Map.Entry<T, Integer> next() {
		// add your code here
		return null; //replace this with the correct value
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
		
	}
	
	//add private methods here, if needed
}
