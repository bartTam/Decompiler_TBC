package datatypes;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import datatypes.filetypes.FileFormat;

/**
 * This will hold the options from the command line args to pass to methods.
 * Also parses and validates the command line args.
 * 
 * @author MrBart
 *
 */
public class Options {
	
	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger( Options.class.getName() );
	
	private File inputFile;
	private File outputFile;
	private BinaryFile file; 
	private boolean append = false;
	private boolean withGui = true;
	
	/**
	 * This will create an options object, verifying all args
	 * 
	 * @param args The array of command line arguments
	 */
	public Options(String[] args){
		parseArgs(args);
		checkForNecessaryArgs();
		validateArgs();
		file = new BinaryFile(inputFile);
	}

	/**
	 * This will parse the command line args and set any options that are passed in.
	 * Note: This function does not verify any of the arguments.
	 * 
	 * @param args The command line arguments
	 */
	private void parseArgs(String[] args) {
		for(int argNumber = 0; argNumber < args.length; argNumber++){
			LOGGER.log(Level.FINEST, "Parsing argument[{0}]: {1}", new Object[] {argNumber, args[argNumber]});

			// Format the arguments to remove the dashes
			String currentArg;
			if(args[argNumber].startsWith("-")){
				if(args[argNumber].startsWith("--")){
					currentArg = args[argNumber].substring(2);
				} else {
					currentArg = args[argNumber].substring(1);
				}
				
				// Switch on  arguments
				switch (currentArg){
					case "headless":
						withGui = false;
						break;
					case "outfile":
					case "o":
						// The next arg will be the output file name
						argNumber++;
						if(argNumber >= args.length) printHelp();
						outputFile = new File(args[argNumber]);
						break;
						
					case "infile":
					case "i":
						// The next arg will be the input file name
						argNumber++;
						if(argNumber >= args.length) printHelp();
						inputFile = new File(args[argNumber]);
						break;
						
					case "append":
						// Append to the output file instead of overwrite
						append = true;
						
					case "help":
					case "h":
					default:
						// Show help
						printHelp();
						break;
				}
			} else {
				printHelp();
			}
		}
	}
	
	public boolean withGui(){
		return withGui;
	}
	
	public BinaryFile getFile(){
		return file;
	}
	
	/**
	 * Checks to make sure that all necessary args are accounted for
	 */
	private void checkForNecessaryArgs(){
		// Make sure they input an input and output file
		if(inputFile == null || outputFile == null){
			printHelp();
		}
	}
	
	/**
	 * Makes sure all input arguments work together.
	 * Also validates that necessary files exist, etc.
	 */
	private void validateArgs(){
		// Make sure the input file exists
		if(!inputFile.exists()){
			printError("Input file does not exist");
		}
		
		// Make sure output file exists if append is required
		if(append && !outputFile.exists()){
			printError("No output file to append");
		}
	}

	private void printHelp() {
		System.out.println("Neccessary arguments: -i <Input File> -o <Output File>");
		System.exit(1);
	}
	
	private void printError(String message){
		System.out.println(message);
		System.exit(1);
	}
	
}
