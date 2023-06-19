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

public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<Map.Entry<T, Integer>> {
	private Iterator<Map.Entry<T, Integer>> iterator;

	public HashMapHistogramIterator(Map<T, Integer> histogramMap) {
		List<Map.Entry<T, Integer>> entries = new ArrayList<>(histogramMap.entrySet());
		Collections.sort(entries, new valComparator());
		iterator = entries.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Map.Entry<T, Integer> next() {
		return iterator.next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();

	}

	private class valComparator implements Comparator<Map.Entry<T, Integer>> {
		@Override
		public int compare(Map.Entry<T, Integer> entry1, Map.Entry<T, Integer> entry2) {
			return entry1.getKey().compareTo(entry2.getKey());
		}
	}
}
