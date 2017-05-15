package datatypes.filetypes;

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
		// Read file header to find what kind of file it is
		if(bin.checkBytesAtLocation(0, WindowsEXE.MAGIC_NUMBER)){
			// Windows exe file
			System.out.println("Windows exe");
			os = WindowsEXE.getWindowsEXE(bin);
		} else if(bin.checkBytesAtLocation(0, LinuxELF.MAGIC_NUMBER)){
			// Linux elf file
			System.out.println("Linux elf");
		} else {
			System.out.println("Something went really wrong, " + String.format("0x%02X", bin.byteAt(0)) 
					+ String.format("0x%02X", bin.byteAt(1)) + String.format("0x%02X", bin.byteAt(2)) 
					+ String.format("0x%02X", bin.byteAt(3)));
		}
		return os;
	}
	
	public String instructionAt(long offset);
	
}
