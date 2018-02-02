import java.awt.*;

public class WordDisplayer {
	public WordGenerator word;
	public int width;
	public int height;
	public int fontSize;
	public int wpm;
	
	public WordDisplayer (WordGenerator word, int width, int height, int fontSize, int wpm) {
		this.word = word;
		this.width = width;
		this.height = height;
		this.fontSize = fontSize;
		this.wpm = wpm;
	}
	
	public char centeredChar(String word) {
		char centeredChar;
		if (word.length() <= 1) {
			centeredChar = word.charAt(0);
		}	else if (word.length() <= 5 && word.length() >=2){
			centeredChar = word.charAt(1);
		}	else if (word.length() <= 9 && word.length() >=6){
			centeredChar = word.charAt(2);
		}	else if (word.length() <= 13 && word.length() >=10){
			centeredChar = word.charAt(3);
		}	else {
			centeredChar = word.charAt(4);
		}
		
		return centeredChar;
	}
	
	public void wordDisplayer() throws InterruptedException {

		DrawingPanel panel = new DrawingPanel(width, height);
		panel.setBackground(Color.pink);
		Graphics g = panel.getGraphics();
		Font f = new Font("Courier", Font.BOLD, fontSize);
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics();

		while(word.hasNext()) {
			String next = word.next();
			char centeredChar = centeredChar(next);
			
			int x = (width - fm.charWidth(centeredChar))/ 2;
			int y = (fm.getAscent() + (height - (fm.getAscent() + fm.getDescent())) / 2);

			for (int i = 0; i < next.indexOf(centeredChar); i++) {
				x -= fm.charWidth(next.charAt(i));
			}
			
			g.setColor(Color.black);
			g.drawLine(width/2, 0, width/2, height);
			g.drawString(next, x, y);
			
			g.setColor(Color.white);
			g.drawString(String.valueOf(centeredChar), (width - fm.charWidth(centeredChar))/ 2, y);
			
			Thread.sleep(wpm);
			panel.clear();
		} // while
		
		System.out.println("Number of Words Generated = " + word.getWordCount());
		System.out.println("Number of Sentences Generated = " + word.getSentenceCount());
	} // wordDisplayer
}
