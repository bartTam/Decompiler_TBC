package datatypes.filetypes;

import datatypes.BinaryFile;

class LinuxELF implements FileType {

	private final BinaryFile file;
	
	public LinuxELF(byte[] bytes){
		file = new BinaryFile(bytes);
	}
}
