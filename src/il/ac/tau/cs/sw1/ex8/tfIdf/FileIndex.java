package il.ac.tau.cs.sw1.ex8.tfIdf;

import java.io.File;
import java.util.List;
import java.util.Map;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {
	
	private boolean isInitialized = false;
	
	//Add members here

	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 * @pre: isInitialized() == false;
	 */
  	public void indexDirectory(String folderPath) { //Q1
		//This code iterates over all the files in the folder. add your code wherever is needed

		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		for (File file : listFiles) {
			// for every file in the folder
			if (file.isFile()) {
				/*******************/
				// add your code here
				/*******************/
			}
		}
		/*******************/
		// add your code here
		/*******************/
		isInitialized = true;
	}
	
	
	
	// Q2
  	
	/* @pre: isInitialized() */
	public int getCountInFile(String word, String fileName) throws FileIndexException{ 
		// add your code here
		return 0; //replace this with the correct value
	}
	
	/* @pre: isInitialized() */
	public int getNumOfUniqueWordsInFile(String fileName) throws FileIndexException{ 
		// add your code here
		return 0; //replace this with the correct value
	}
	
	/* @pre: isInitialized() */
	public int getNumOfFilesInIndex(){
		// add your code here
		return 0; //replace this with the correct value
	}

	
	/* @pre: isInitialized() */
	public double getTF(String word, String fileName) throws FileIndexException{ // Q3
		// add your code here
		return 0.; //replace this with the correct value
	}
	
	/* @pre: isInitialized() 
	 * @pre: exist fileName such that getCountInFile(word) > 0*/
	public double getIDF(String word){ //Q4
		// add your code here
		return 0.; //replace this with the correct value
	}
	
	
	
	/*
	 * @pre: isInitialized()
	 * @pre: 0 < k <= getNumOfUniqueWordsInFile(fileName)
	 * @post: $ret.size() = k
	 * @post for i in (0,k-2):
	 * 		$ret[i].value >= $ret[i+1].value
	 */
	public List<Map.Entry<String, Double>> getTopKMostSignificantWords(String fileName, int k) 
													throws FileIndexException{ //Q5
		// add your code here
		return null; //replace this with the correct value
	}
	
	/* @pre: isInitialized() */
	public double getCosineSimilarity(String fileName1, String fileName2) throws FileIndexException{ //Q6
		// add your code here
		return 0.; //replace this with the correct value
	}
	
	/*
	 * @pre: isInitialized()
	 * @pre: 0 < k <= getNumOfFilesInIndex()-1
	 * @post: $ret.size() = k
	 * @post for i in (0,k-2):
	 * 		$ret[i].value >= $ret[i+1].value
	 */
	public List<Map.Entry<String, Double>> getTopKClosestDocuments(String fileName, int k) 
			throws FileIndexException{ //Q6
		// add your code here
		return null; //replace this with the correct value
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
