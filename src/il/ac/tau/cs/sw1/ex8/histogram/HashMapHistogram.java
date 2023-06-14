package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{

	// add members here
	
	
	//add constructor here, if needed

	
	public HashMapHistogram(){
	}
	
	@Override
	public void addItem(T item) {
		// add your code here
	}
	
	@Override
	public boolean removeItem(T item)  {
		// add your code here
		return false; //replace this with the correct value
	}
	
	@Override
	public void addAll(Collection<T> items) {
		// add your code here
	}

	@Override
	public int getCountForItem(T item) {
		// add your code here
		return 0; //replace this with the correct value
	}

	@Override
	public void clear() {
		// add your code here
	}

	@Override
	public Set<T> getItemsSet() {
		// add your code here
		return null; //replace this with the correct value
	}
	
	@Override
	public int getCountsSum() {
		// add your code here
		return 0; //replace this with the correct value
	}

	@Override
	public Iterator<Map.Entry<T, Integer>> iterator() {
		// add your code here
		return null; //replace this with the correct value
	}
	
	//add private methods here, if needed
}
