/**
 * @author Ian Turnbull
 * @version 4/6/17
 * 	Project 3 Itec 324
 * WordLetterCount.java 
 * A program that counts the number of words and the number of alphabet letters in input files.
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WordLetterCount {

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			WordLetterCount window = new WordLetterCount();
			window.mainFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Create the application.
	 */
	public WordLetterCount() {
		fileChooser = new JFileChooser();
		fileOneWords = "";
		fileTwoWords = "";
		fileThreeWords = "";
		fileCount = 0;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Word Letter Count");
		mainFrame.setBounds(100, 100, 450, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setLocationRelativeTo(null);
		
		fileOneTextField = new JTextField();
		fileOneTextField.setBackground(Color.WHITE);
		fileOneTextField.setEditable(false);
		fileOneTextField.setBounds(54, 34, 288, 20);
		mainFrame.getContentPane().add(fileOneTextField);
		fileOneTextField.setColumns(10);
		
		fileTwoTextField = new JTextField();
		fileTwoTextField.setBackground(Color.WHITE);
		fileTwoTextField.setEditable(false);
		fileTwoTextField.setBounds(54, 86, 288, 20);
		fileTwoTextField.setColumns(10);
		mainFrame.getContentPane().add(fileTwoTextField);
		
		fileThreeTextField = new JTextField();
		fileThreeTextField.setBackground(Color.WHITE);
		fileThreeTextField.setEditable(false);
		fileThreeTextField.setBounds(54, 140, 288, 20);
		fileThreeTextField.setColumns(10);
		mainFrame.getContentPane().add(fileThreeTextField);
//count button		
		JButton countWordsButton = new JButton("Count Words"); //add constriant to check for file name not textbox
		countWordsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fileCount > 0) {
					BoundedQueue<String> queue = new BoundedQueue<String>(fileCount);
					int tNum = 0;
					
					if(!fileOneTextField.getText().isEmpty() ) {
						Runnable r1 = new LettersRunnable(fileOneWords, fileOne.getName(), tNum++, queue);
						Thread t1 = new Thread(r1);
						t1.start();
					}
					if(!fileTwoTextField.getText().isEmpty()) {
						Runnable r2 = new LettersRunnable(fileTwoWords, fileTwo.getName(), tNum++, queue);
						Thread t2 = new Thread(r2);
						t2.start();
					}
					if(!fileThreeTextField.getText().isEmpty()) {
						Runnable r3 = new LettersRunnable(fileThreeWords, fileThree.getName(), tNum++, queue);
						Thread t3 = new Thread(r3);
						t3.start();
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) 
					{
					}
					Runnable r4 = new SaveRunnable(queue, fileCount);
					Thread t4 = new Thread(r4);
					t4.start();
				}
				else
					JOptionPane.showMessageDialog(mainFrame, "Select a file");
			}
		});
		countWordsButton.setBounds(126, 190, 162, 44);
		mainFrame.getContentPane().add(countWordsButton);
//file 1
		JButton fileOneButton = new JButton("...");
		fileOneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				int result = fileChooser.showOpenDialog(mainFrame);
				if(result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					fileOneTextField.setText(selectedFile.getAbsolutePath());
					fileOne = new File(selectedFile.getAbsolutePath());
					Scanner scan;
					try {
						scan = new Scanner(fileOne);
						while(scan.hasNextLine()) {
							fileOneWords += scan.nextLine();
							fileOneWords += "\n";
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					fileCount++;
				}
			}
		});
		fileOneButton.setBounds(352, 33, 36, 23);
		mainFrame.getContentPane().add(fileOneButton);
//file 2
		JButton fileTwoButton = new JButton("...");
		fileTwoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				int result = fileChooser.showOpenDialog(mainFrame);
				if(result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					fileTwoTextField.setText(selectedFile.getAbsolutePath());
					fileTwo = new File(selectedFile.getAbsolutePath());
					Scanner scan;
					try {
						scan = new Scanner(fileTwo);
						while(scan.hasNextLine()) {
							fileTwoWords += scan.nextLine();
							fileTwoWords += "\n";
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					fileCount++;
				}
			}
		});
		fileTwoButton.setBounds(352, 85, 36, 23);
		mainFrame.getContentPane().add(fileTwoButton);
//file 3
		JButton fileThreeButton = new JButton("...");
		fileThreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				int result = fileChooser.showOpenDialog(mainFrame);
				if(result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					fileThreeTextField.setText(selectedFile.getAbsolutePath());
					fileThree = new File(selectedFile.getAbsolutePath());
					Scanner scan;
					try {
						scan = new Scanner(fileThree);
						while(scan.hasNextLine()) {
							fileThreeWords += scan.nextLine();
							fileThreeWords += "\n";
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					fileCount++;
				}
			}
		});
		fileThreeButton.setBounds(352, 139, 36, 23);
		mainFrame.getContentPane().add(fileThreeButton);
	
	}
	
	private JFrame mainFrame;
	private JTextField fileOneTextField,fileTwoTextField, fileThreeTextField;
	private JFileChooser fileChooser;
	private File fileOne, fileTwo, fileThree;
	private String fileOneWords, fileTwoWords, fileThreeWords;
	private int fileCount;
}
