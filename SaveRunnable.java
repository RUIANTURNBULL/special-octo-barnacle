/**
 * @author Ian Turnbull
 * @version 4-6-17
 * SaveRunnable.java
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveRunnable implements Runnable{
	
	/**
	 * Instantiates a new save runnable.
	 *
	 * @param _queue the queue
	 * @param _fileCount the file count
	 */
	public SaveRunnable(BoundedQueue<String> _queue, int _fileCount) {
		queue = _queue;
		fileCount = _fileCount;
	}

	/*
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		try {
			outstream = new FileWriter("results.txt");
			out = new BufferedWriter(outstream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try
		{
			int i = 1;
			while( i <= fileCount) {
				String save = queue.remove();
				try {
					out.write(save);
				} catch (IOException e) {
					e.printStackTrace();
				}
				i++;
				Thread.sleep((int)(Math.random() * 10));
			}
			out.close();
		}
		catch (InterruptedException | IOException ex) {
			ex.printStackTrace();
		}
	}
	private BoundedQueue<String> queue;
	private int fileCount;
	private FileWriter outstream;
	private BufferedWriter out;
}
