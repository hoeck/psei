import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

/**
 * @author Steffen Kersten
 *
 */
public class MarchingCubesGUI extends JFrame {
	
	public static void main(String[] args) {
		MarchingCubesGUI frame = new MarchingCubesGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setJMenuBar(frame.getJMenuBar());
	}

	public MarchingCubesGUI(){
		setTitle("Marching Cubes");
	
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		
		int screenWidth = screensize.width;
		int screenHeight = screensize.height;
		setSize(screenWidth/2, screenHeight/3);
		setLocation(screenWidth/4,screenHeight/4);
//		Image img = kit.getImage("cube.gif");
//		setIconImage(img);
		
		CalculatePanel panel = new CalculatePanel();
		add(panel);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new OpenAction());
		openItem.setMnemonic(KeyEvent.VK_O);
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		JMenuItem calculateItem = new JMenuItem("Calculate");
		calculateItem.addActionListener(new CalculateAction());
		calculateItem.setMnemonic(KeyEvent.VK_C);
		calculateItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(new AboutAction());
		aboutItem.setMnemonic(KeyEvent.VK_A);
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		
		fileMenu.add(openItem);
		editMenu.add(calculateItem);
		helpMenu.add(aboutItem);
		
	}
	
	class CalculatePanel extends JPanel
	{
		private JTextField numberInputField;
		private JLabel outputLabel;
		
		public CalculatePanel()
		{
			JButton openButton = new JButton("Open");
			openButton.addActionListener(new OpenAction());
			openButton.setMnemonic(KeyEvent.VK_O);
			
			JButton calculateButton = new JButton("Calculate");
			calculateButton.addActionListener(new CalculateAction());
			calculateButton.setMnemonic(KeyEvent.VK_C);
			
			add(openButton);
			add(calculateButton);			
			
			//TODO: some sort of progress bar to see how many points are calculated already or 
			//		an indeterminate progress bar to see if the task is still running; JProgressBar
		}
	}
	
	class AboutAction extends JDialog implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			JOptionPane.showMessageDialog(this, "written by\n\nErik Söhnel\nSteffen Kersten\nSebastian Wiegand", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	class CalculateAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//TODO: read file and calculate the new points, output is given below
						
			String calculatedString = "calculated list of 3D points";
	    	File file = new File("calculated.txt");
	    	FileWriter outputStream = null;
			try {
				outputStream = new FileWriter(file);
			} catch (IOException e) {
				System.out.println("Something went wrong here" + e);
				e.printStackTrace();
			}
	    	PrintWriter output = new PrintWriter(outputStream);
	    	output.println(calculatedString);
	    	output.flush();
	    	output.close();
		}
	}
	
	class OpenAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			final JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(fileChooser);
		}
	}
}