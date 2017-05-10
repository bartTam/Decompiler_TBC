package datatypes.filetypes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import datatypes.BinaryFile;

public interface FileFormat {
	
	
	
	/**
	 * Scans the header of a binary file to check what kind it is
	 * 
	 * @param file The file to scan the header of.
	 * @return Returns a FileType object that will disassemble the file.
	 */
	public static FileFormat scanHeader(BinaryFile bin){			
		FileFormat os = null;
		try {
			

			// Read file header to find what kind of file it is
			if(bin.checkBytesAtLocation(0, WindowsEXE.MAGIC_NUMBER)){
				// Windows exe file
				System.out.println("Windows exe");
				os = WindowsEXE.getWindowsEXE(bin);
			} else if(bin.checkBytesAtLocation(0, LinuxELF.MAGIC_NUMBER)){
				// Linux elf file
			}
			
		} catch (IOException e){
			e.printStackTrace();
		}
		return os;
	}
	
	
}
