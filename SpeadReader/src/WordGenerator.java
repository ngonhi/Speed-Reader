import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class WordGenerator {
	public File inFile;
	public int wordCounter = 0;
	public int sentenceCounter = 0;
	public String next;
	public Scanner text;

	public WordGenerator(String fileName) throws IOException{
		this.inFile = new File(fileName);
		this.text = new Scanner(inFile);
	}

	public boolean hasNext() {
		return (this.text).hasNext();
	}

	public String next() {
		return (this.text).next(); 
	}

	public int getWordCount() {
		if (this.hasNext()) {
			this.wordCounter++;
		}
		return this.wordCounter;
	}

	public int getSentenceCount() {
		int strLength = this.next().length();
		char lastChar = this.next().charAt(strLength - 1);
		if (lastChar == '.' || lastChar == '?' || lastChar == '!') {
			this.sentenceCounter++;
		}
		
		return this.sentenceCounter;			
	}
}
