/**
 * @author Ian Turnbull
 * @version 4-6-17
 * LettersRunnable.java
 */
public class LettersRunnable implements Runnable{
	
	/**
	 * Instantiates a new letters runnable.
	 *
	 * @param _allWords the all words
	 * @param _fileName the file name
	 * @param _thread the thread
	 * @param _queue the queue
	 */
	public LettersRunnable(String _allWords, String _fileName, int _thread, BoundedQueue<String> _queue) {
		allWords = _allWords;
		fileName = _fileName;
		thread = _thread;
		queue = _queue;
		wordResults = 0;
		letterResults = 0;
	}
	
	/*
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		boolean isWord = false;

		for(int i = 0; i < allWords.length(); i++) {
			//counting total letters
			if(Character.isLetter(allWords.charAt(i))) {
				letterResults++;
			}
			//counting total words
			if(Character.isLetter(allWords.charAt(i)) && i != allWords.length()-1) {
				isWord = true;
			} else if(!Character.isLetter(allWords.charAt(i)) && isWord) {
				wordResults++;
				isWord = false;
			} else if(Character.isLetter(allWords.charAt(i)) && i == allWords.length()-1) {
				wordResults++;
			}
		}
		
		new ViewLetterCount(allWords, wordResults, letterResults);
		
		String save = "Thread " + thread + ": the file " + fileName + " has " + wordResults
				+ " words and " + letterResults + " letters.\r\n";
		
		try
		{
			queue.add(save);
			Thread.sleep((int)(Math.random() * 10));
		}
		catch(InterruptedException ex)
		{
			ex.printStackTrace();
		}
		
	}
	private String allWords, fileName;
	private int wordResults, letterResults, thread;
	private BoundedQueue<String> queue;
}
