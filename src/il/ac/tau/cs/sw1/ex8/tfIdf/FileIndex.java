package il.ac.tau.cs.sw1.ex8.tfIdf;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IHistogram;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {
	private HashMapHistogram<String>[] histogramArray; // Declare the array as a class member

	// Add this method to get the histogram array



	private Map<String, HashMapHistogram<String>> map = new HashMap<String, HashMapHistogram<String>>();

	public Map<String,HashMapHistogram<String>> getMap(){
		return this.map;
	}
	private boolean isInitialized = false;
	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 * @pre: isInitialized() == false;
	 */
	public void indexDirectory(String folderPath) { //Q1

		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		for (File file: listFiles){
			if (file.isFile()){
				String fileName = file.getName();
				try {
					List<String> allTokens = FileUtils.readAllTokens(file);
					HashMapHistogram<String> curHistogram = new HashMapHistogram<String>();
					curHistogram.addAll(allTokens);
					this.getMap().put(fileName, curHistogram);
				}
				catch (Exception error){
					throw new RuntimeException(error);
				}
			}
		}

		isInitialized = true;
	}




	// Q2

	/* @pre: isInitialized() */
	public int getCountInFile(String word, String fileName) throws FileIndexException{
		try{
			return this.map.get(fileName).getCountForItem(word.toLowerCase());
		}
		catch (NullPointerException e){
			throw new FileIndexException("File not found " + fileName );
		}
	}


	/* @pre: isInitialized() */
	public int getNumOfUniqueWordsInFile(String fileName) throws FileIndexException{
		try{
			return this.map.get(fileName).getItemsSet().size();
		}
		catch (NullPointerException e) {
			throw new FileIndexException("File not found " + fileName);
		}
	}

	/* @pre: isInitialized() */
	public int getNumOfFilesInIndex(){
		return this.map.size();
	}


	/* @pre: isInitialized() */
	public double getTF(String word, String fileName) throws FileIndexException { // Q3
		try {
			double repsOfWord = getCountInFile(word, fileName);
			double numOfWordsInDoc = this.map.get(fileName).getCountsSum();
			double tf = repsOfWord / numOfWordsInDoc;
			return tf;
		} catch (NullPointerException e) {
			throw new FileIndexException("File not found " + fileName);
		}
	}


	/* @pre: isInitialized()
	 * @pre: exist fileName such that getCountInFile(word) > 0*/
	public double getIDF(String word) { //Q4
		int numOfFiles = this.map.size();
		int numOfDocWithWord = 0;
		for (HashMapHistogram<String> histogram : this.map.values()) {
			if (histogram.getCountForItem(word) > 0) {
				numOfDocWithWord++;
			}
		}
		return Math.log((double) numOfFiles /  numOfDocWithWord);
	}



	/*
	 * @pre: isInitialized()
	 * @pre: 0 < k <= getNumOfUniqueWordsInFile(fileName)
	 * @post: $ret.size() = k
	 * @post for i in (0,k-2):
	 * 		$ret[i].value >= $ret[i+1].value
	 */
	public List<Map.Entry<String, Double>> getTopKMostSignificantWords(String fileName, int k) throws FileIndexException {// Q5
		try {
			List<Map.Entry<String, Double>> lst = new ArrayList<>(k);
			Set<String> words = this.map.get(fileName).getItemsSet();
			Set<String> sortedWords = sortWordsLexicographically(words);
			int numOfWords = 0;

			while (numOfWords < k) {
				double maxTFIDF = 0;
				String removedWord = null;

				for (String word : sortedWords) {
					double curTFIDF = getTFIDF(word, fileName);
					if (curTFIDF > maxTFIDF) {
						maxTFIDF = curTFIDF;
						removedWord = word;
					}
				}

				if (removedWord != null) {
					numOfWords++;
					sortedWords.remove(removedWord);
					lst.add(new AbstractMap.SimpleEntry<>(removedWord, maxTFIDF));
				} else {
					break;
				}
			}

			return lst;
		} catch (NullPointerException e) {
			throw new FileIndexException("File not found " + fileName);
		}
	}
	private Set<String> sortWordsLexicographically(Set<String> words) {
		List<String> sortedList = new ArrayList<>(words);
		Collections.sort(sortedList);
		return new LinkedHashSet<>(sortedList);
	}

	/* @pre: isInitialized() */
	public double getCosineSimilarity(String fileName1, String fileName2) throws FileIndexException { //Q6
		try {
			Set<String> words1 = this.map.get(fileName1).getItemsSet();
			Set<String> words2 = this.map.get(fileName2).getItemsSet();
			Set<String> intersection = new HashSet<>(words1);
			intersection.retainAll(words2);
			double numerator = 0;
			for (String word : intersection) {
				double multiplication = getTFIDF(word, fileName1) * getTFIDF(word, fileName2);
				numerator += multiplication;
			}
			double aSum = calculateSumOfSquares(words1, fileName1);
			double bSum = calculateSumOfSquares(words2, fileName2);
			return numerator/Math.sqrt(aSum * bSum);
		}
		catch (NullPointerException e){
			throw new FileIndexException("File not found " + fileName1 + "or file not found " + fileName2);

		}
	}
	private double calculateSumOfSquares(Set<String> words, String fileName) throws FileIndexException {
		double sum = 0;
		for (String word : words) {
			double curTFIDF = getTFIDF(word, fileName);
			sum += curTFIDF * curTFIDF;
		}
		return sum;
	}


	/*
	 * @pre: isInitialized()
	 * @pre: 0 < k <= getNumOfFilesInIndex()-1
	 * @post: $ret.size() = k
	 * @post for i in (0,k-2):
	 * 		$ret[i].value >= $ret[i+1].value
	 */
	public List<Map.Entry<String, Double>> getTopKClosestDocuments(String fileName, int k) throws FileIndexException { //Q7
		try {
			List<Map.Entry<String, Double>> lst = new ArrayList<>(k);
			Set<String> files = new HashSet<>(this.map.keySet());
			int numOfDOCS = 0;
			while (numOfDOCS < k) {
				double maxCosineSimilarity = 0;
				String removedFile = null;
				files.remove(fileName);

				for (String file : files) {
					double curCosineSimilarity = getCosineSimilarity(file, fileName);
					if (curCosineSimilarity > maxCosineSimilarity) {
						maxCosineSimilarity = curCosineSimilarity;
						removedFile = file;
					}
				}

				if (removedFile != null) {
					numOfDOCS++;
					files.remove(removedFile);
					lst.add(new AbstractMap.SimpleEntry<>(removedFile, maxCosineSimilarity));
				} else {
					break;
				}
			}

			return lst;
		}
		catch (NullPointerException e) {
			throw new FileIndexException("File not found " + fileName);
		}
	}





	//add private methods here, if needed


	/*************************************************************/
	/********************* Don't change this ********************/
	/*************************************************************/

	public boolean isInitialized(){
		return this.isInitialized;
	}

	/* @pre: exist fileName such that getCountInFile(word) > 0*/
	public double getTFIDF(String word, String fileName) throws FileIndexException{
		return this.getTF(word, fileName)*this.getIDF(word);
	}

	private static double calcTF(int repetitionsForWord, int numOfWordsInDoc){
		return (double)repetitionsForWord/numOfWordsInDoc;
	}

	private static double calcIDF(int numOfDocs, int numOfDocsContainingWord){
		return Math.log((double)numOfDocs/numOfDocsContainingWord);
	}

}
