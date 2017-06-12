package view.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import configuration.CLArgs;
import controller.IController;
import datatypes.BinaryFile;
import datatypes.CurrentFileManager;
import view.View;

public class GuiView extends JFrame implements View {
	
	private static final long serialVersionUID = -2120841388923288585L;
	
	private final JMenuBar topBar;
	private final ArrayList<IController> controllers = new ArrayList<IController>();

	public GuiView(CLArgs args, CurrentFileManager manager) {
		super("Decompiler TBC-" + manager.getCurrentFile().getFile().getName());
		
		setLayout(new BorderLayout());
		topBar = new JMenuBar();
		topBar.add(createFileMenu());
		
		setJMenuBar(topBar);
		pack();
	}
	
	private JMenu createFileMenu(){
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem open = new JMenuItem("Open File");
		
		
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				for(IController controller : controllers){
					controller.exit();
				}
			}
		});
		fileMenu.add(exit);
		return fileMenu;
	}

	@Override
	public void register(IController controller) {
		this.controllers.add(controller);
	}

	@Override
	public boolean promptToSave(String fileName, boolean exiting) {
		Object[] options = {"Save", "Discard changes"};
		String prompt;
		if(exiting){
			prompt = "Would you like to save " + fileName + " before exiting?";
		} else {
			prompt = "Would you like to save " + fileName + "?";
		}
		int returnVal = JOptionPane.showOptionDialog(this, "Would you like to save " + fileName, "Save?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return returnVal == JOptionPane.YES_OPTION;
	}
	

}
