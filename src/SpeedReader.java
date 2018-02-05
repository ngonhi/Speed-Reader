import java.awt.*;
import java.io.IOException;

public class SpeedReader {
	/**
	* Finds and returns the index of the focused character of a word.
	* @param word which is a String
	* @return focusedIndex which is an int 
	*/
	public static int focusedIndex(String word) {
		int index;
		if (word.length() <= 1) {
			index = 0;
		}	else if (word.length() <= 5 && word.length() >=2){
			index = 1;
		}	else if (word.length() <= 9 && word.length() >=6){
			index = 2;
		}	else if (word.length() <= 13 && word.length() >=10){
			index = 3;
		}	else {
			index = 4;
		}

		return index;
	}

	/**
	 * Displays the word that wg generates on a panel.
	 * @param wg a WordGenerator
	 * @param width an integer representing the width of the panel.
	 * @param height an integer representing the height of the panel.
	 * @param fontSize an integer representing the size of the words displayed.
	 * @param wpm an integer representing the reading speed per minute.
	 * @throws InterruptedException 
	 */
	public static void wordDisplayer(WordGenerator wg, int width, int height, int fontSize,
			int wpm) throws InterruptedException {

		DrawingPanel panel = new DrawingPanel(width, height);
		panel.setBackground(Color.pink);
		Graphics g = panel.getGraphics();
		Font f = new Font("Courier", Font.BOLD, fontSize);
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics(); //

		/* Reads and displays words generated in an animated way */
		while(wg.hasNext()) {
			String next = wg.next();
			int focusedIndex = focusedIndex(next);
			char focusedChar = next.charAt(focusedIndex);
			
			/* Gets the coordinate of the focused character */
			int x = (width - fm.charWidth(focusedChar))/ 2;
			int y = (fm.getAscent() + (height - (fm.getAscent() + fm.getDescent())) / 2);

			/* Gets the coordinate of the first character 
			 * based on the index of the focused character 
			 */
			for (int i = 0; i < focusedIndex; i++) {
				x -= fm.charWidth(next.charAt(i));
			}

			/* Displays the word generated in black */
			g.setColor(Color.black);
			//draws a line going through the focused character
			g.drawLine(width/2, 0, width/2, height); 
			g.drawString(next, x, y); //draws the word generated

			/* Draws the focused letter in white */
			g.setColor(Color.white);
			g.drawString(String.valueOf(focusedChar), (width - fm.charWidth(focusedChar))/ 2, y);

			/* Delays displaying the next word by causing the program to sleep */
			Thread.sleep(wpm);

			panel.clear(); //clear the panel
		} // while

		/* Reports the number of words and sentences WordGenerator processed. */
		System.out.println("Number of Words Generated = " + wg.getWordCount());
		System.out.println("Number of Sentences Generated = " + wg.getSentenceCount());
	} // wordDisplayer

	/**
	 * Reads in a file and displays it in the RSVP style to a DrawingPanel.
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException{
		final int SECOND_TO_MILLISECOND = 60 * 1000;

		/* Checks whether the number of the command-line arguments is enough.
		 * If not, prints the usage message and exits the program.  
		 */
		if (args.length < 5) {
			System.err.println("You did not enter enough Arguments!");
			System.exit(0); 		
		}

		/* Passes command-line arguments */
		WordGenerator wg = new WordGenerator(args[0]);
		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);
		int fontSize = Integer.parseInt(args[3]);
		int wpm = Integer.parseInt(args[4]);
		
		/* Checks whether wpm is valid */
		if (wpm <= 0) {
			System.err.println("The wpm must be a positive integer.");
			System.exit(0);
		}
		
		int interval = SECOND_TO_MILLISECOND/wpm; //indicates the time for displaying one word

		/* Checks whether the arguments are valid */
		if (width <= 0) {
			System.err.println("The width must be a positive integer.");
			System.exit(0);
		}
		if (height <= 0) {
			System.err.println("The height must be a positive integer.");
			System.exit(0);
		}
		if (fontSize <= 0) {
			System.err.println("The fontSize must be a positive integer.");
			System.exit(0);
		}
		
		/* Displays the word that wg generates on a panel */
		wordDisplayer(wg, width, height, fontSize, interval);
		
		/* exit the program */		
		System.exit(0);
	}
}
