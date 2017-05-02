package driver;

import java.util.logging.Level;
import java.util.logging.Logger;

import datatypes.Options;

public class Main {
	
	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );

	public static void main(String[] args) {
		Options arguments = new Options(args);
		LOGGER.log(Level.FINE, "Created Args", new Object());
	}

}
