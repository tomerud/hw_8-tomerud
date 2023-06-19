package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.*;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{

	public Map<T, Integer> histogramMap;
	
	
	//add constructor here, if needed

	
	public HashMapHistogram(){
		histogramMap = new HashMap<>();
	}
	
	@Override
	public void addItem(T item) {
		if (histogramMap.containsKey(item)) {
			int currentCount = histogramMap.get(item);
			histogramMap.put(item, currentCount + 1);
		} else {
			histogramMap.put(item, 1);
		}
	}
	
	@Override
	public boolean removeItem(T item)  {
		if (histogramMap.containsKey(item)) {
			int count = histogramMap.get(item);
			if (count > 1) {
				histogramMap.put(item, count - 1);
			}
			else {
				histogramMap.remove(item);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public void addAll(Collection<T> items) {
		for (T item : items) {
			addItem(item);
		}
	}

	@Override
	public int getCountForItem(T item) {

		return histogramMap.getOrDefault(item, 0);
	}

	@Override
	public void clear() {
		histogramMap.clear();
	}

	@Override
	public Set<T> getItemsSet() {
		Set<T> itemsSet = new HashSet<>();
		for (Map.Entry<T, Integer> entry : histogramMap.entrySet()) {
			if (entry.getValue() > 0) {
				itemsSet.add(entry.getKey());
			}
		}
		return itemsSet;
	}
	
	@Override
	public int getCountsSum() {
		int sum = 0;
		for (int count : histogramMap.values()) {
			sum += count;
		}
		return sum;
	}
	public Map<T, Integer> getItemsMap() {
		return histogramMap;
	}

	@Override
	public Iterator<Map.Entry<T, Integer>> iterator() {
		return histogramMap.entrySet().iterator();
	}
}
