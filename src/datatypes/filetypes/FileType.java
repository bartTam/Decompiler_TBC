package datatypes.filetypes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public interface FileType {
	
	/**
	 * Scans the header of a binary file to check what kind it is
	 * 
	 * @param file The file to scan the header of.
	 * @return Returns a FileType object that will disassemble the file.
	 */
	public static FileType scanHeader(File file){			
		FileType os = null;
		try {
			// Read the magic number from the input file
			InputStream fileHeader = new BufferedInputStream(new FileInputStream(file));
			byte[] bytes = new byte[file.length() > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)file.length()];
			fileHeader.read(bytes);

			// Read file header to find what kind of file it is
			if(bytes[0] == 0x4d && bytes[1] == 0x5a){
				// Windows exe file
				System.out.println("Windows exe");
				os = WindowsEXE.getWindowsEXE(bytes);
			} else if(bytes[0] == 0x7F && bytes[1] == 'E' && bytes[2] == 'L' && bytes[3] == 'F'){
				// Linux elf file
			}
			
			fileHeader.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		return os;
	}
	
	
}
