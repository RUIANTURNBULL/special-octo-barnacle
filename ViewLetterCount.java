/**
 * @author Ian Turnbull
 * @version 4-6-17
 * ViewLetterCount.java
 */
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ViewLetterCount {

	/**
	 * Create the application.
	 *
	 * @param _fileWords the file words
	 * @param _wordResults the word results
	 * @param _letterResults the letter results
	 */
	public ViewLetterCount(String _fileWords, int _wordResults, int _letterResults) {
		allWords = _fileWords;
		wordResults = _wordResults;
		letterResults = _letterResults;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		countFrame = new JFrame();
		countFrame.setBounds(100, 100, 550, 300);
		countFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		countFrame.getContentPane().setLayout(null);
		countFrame.setLocationByPlatform(true);
		countFrame.setVisible(true);
		
		JLabel wordsLabel = new JLabel("# of words:");
		wordsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		wordsLabel.setBounds(67, 228, 92, 22);
		countFrame.getContentPane().add(wordsLabel);
		
		JLabel wordResultsLabel = new JLabel(Integer.toString(wordResults));
		wordResultsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		wordResultsLabel.setBounds(169, 228, 92, 22);
		countFrame.getContentPane().add(wordResultsLabel);
		
		JLabel lettersLabel = new JLabel("# of letters:");
		lettersLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lettersLabel.setBounds(282, 228, 92, 22);
		countFrame.getContentPane().add(lettersLabel);
		
		JLabel lettersResultLabel = new JLabel(Integer.toString(letterResults));
		lettersResultLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lettersResultLabel.setBounds(384, 228, 92, 22);
		countFrame.getContentPane().add(lettersResultLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 514, 206);
		countFrame.getContentPane().add(scrollPane);
		
		JTextArea wordsTextArea = new JTextArea(allWords);
		scrollPane.setViewportView(wordsTextArea);
		wordsTextArea.setEditable(false);

	}
	private JFrame countFrame;
	private String allWords;
	private int wordResults, letterResults;
}
