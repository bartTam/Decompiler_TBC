package datatypes.filetypes;

import datatypes.BinaryFile;

class LinuxELF implements FileFormat {
	
	static final byte[] MAGIC_NUMBER = { 0x7F, 'E', 'L', 'F' };

	private final BinaryFile file;
	
	public LinuxELF(byte[] bytes){
		file = new BinaryFile(bytes);
	}
}
