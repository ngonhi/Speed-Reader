import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class WordGenerator {
	public File inFile;
	public int wordCounter = 0;
	public int sentenceCounter = 0;
	public Scanner text;
	
	/* Constructor for the class */
	public WordGenerator(String fileName) throws IOException{
		inFile = new File(fileName);
		text = new Scanner(inFile);
	}

	/**
	 * Returns true if the WordGenerator has another word in its input. 
	 * @return true if and only if this WordGenerator has another word.
	 */
	public boolean hasNext() {
		return text.hasNext();
	}

	/**
	 * Finds and returns the next complete word from this WordGenerator.
	 * @return the next word.
	 */
	public String next() {
		String next = text.next();
		wordCounter++;
		
		char lastChar = next.charAt(next.length() - 1);
		if (lastChar == '.' || lastChar == '?' || lastChar == '!') {
			sentenceCounter++;
		}		
		return next;
	}

	/**
	 * Returns the number of words generated with this WordGenerator.
	 * @return an int that represents the number of words generated.
	 */
	public int getWordCount() {
		return wordCounter;
	}

	/** 
	 * Returns the number of sentences generated with this WordGenerator.
	 * @return an int that represents the number of sentences generated.
	 */
	public int getSentenceCount() {
		return sentenceCounter;			
	}
}
