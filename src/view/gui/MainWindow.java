package view.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import configuration.CLArgs;

class MainWindow extends JFrame {
	
	public MainWindow(CLArgs options){
		super("Decompiler");
		add(new JLabel("Hi"));
		pack();
		setVisible(true);
	}
	
	
}
