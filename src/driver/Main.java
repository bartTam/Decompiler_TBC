package driver;

import java.util.logging.Level;
import java.util.logging.Logger;

import datatypes.BinaryFile;
import datatypes.Options;
import graphics.MainWindow;

public class Main {
	
	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );

	public static void main(String[] args) {
		Options arguments = new Options(args);
		LOGGER.log(Level.FINE, "Created Args", new Object());
		if(arguments.withGui()){
			javax.swing.SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					MainWindow main = new MainWindow(arguments);
				}
			});
		}
		BinaryFile file = arguments.getFile();
	}

}
