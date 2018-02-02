import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class WordGenerator {
	public File inFile;
	public int wordCounter = 0;
	public int sentenceCounter = 0;
	public Scanner text;

	public WordGenerator(String fileName) throws IOException{
		inFile = new File(fileName);
		text = new Scanner(inFile);
	}

	public boolean hasNext() {
		return text.hasNext();
	}

	public String next() {
		String next = text.next();
		wordCounter++;
		
		char lastChar = next.charAt(next.length() - 1);
		if (lastChar == '.' || lastChar == '?' || lastChar == '!') {
			sentenceCounter++;
		}		
		return next;
	}

	public int getWordCount() {
		return wordCounter;
	}

	public int getSentenceCount() {
		return sentenceCounter;			
	}
}
