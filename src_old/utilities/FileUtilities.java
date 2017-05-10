package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

public class FileUtilities {
	
	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger( FileUtilities.class.getName() );
	
	public static boolean writeToFile(String[] output, File outputFile){
		try {
			BufferedWriter writer = Files.newBufferedWriter(outputFile.toPath());
			for(String line : output){
				writer.write(line + '\n');
			}
			
		} catch(IOException e){
			
			return false;
		}
		return true;
	}
}
