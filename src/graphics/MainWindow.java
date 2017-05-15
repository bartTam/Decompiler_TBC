package graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

import datatypes.Options;

public class MainWindow extends JFrame {
	
	public MainWindow(Options options){
		super("Decompiler");
		add(new JLabel("Hi"));
		pack();
		setVisible(true);
	}
	
	
}
