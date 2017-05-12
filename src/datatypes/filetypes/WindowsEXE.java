package datatypes.filetypes;

import datatypes.BinaryFile;

abstract class WindowsEXE implements FileFormat {

	static final byte[] MAGIC_NUMBER = {0x4d, 0x5a};
	
	private static final int PE_HEADER_POINTER = 0x3C;
	private static final int SIZE_OF_HEADER_OFFSET = 0x14;

	public static WindowsEXE getWindowsEXE(BinaryFile file){
		int peLocation = (int)((0xFF & file[PE_HEADER_POINTER]) | ((0xFF & file[PE_HEADER_POINTER + 1]) << 8) 
				| (0xFF & (file[PE_HEADER_POINTER + 2]) << 16) | (0xFF & (file[PE_HEADER_POINTER + 3]) << 24));

		if(file[peLocation + 4] == 0x4C && file[peLocation + 5] == 0x01){
			System.out.println("Windows x86");
			return new Windowsx86(file, peLocation);
		} else if(file[peLocation + 4] == (byte)0x64 && file[peLocation + 5] == (byte)0x86){
			System.out.println("Windows x64");
			return new Windowsx64(file, peLocation);
		} else {
			return null;
		}
	}
	
	
	protected final BinaryFile file;
	protected final int peLocation;
	protected final int optHeaderSize;

	protected WindowsEXE(BinaryFile file, int peLocation){
		this.file = file;
		this.peLocation = peLocation;
		optHeaderSize = (int)((0xFF & file[peLocation + SIZE_OF_HEADER_OFFSET]) | (0xFF & file[peLocation + SIZE_OF_HEADER_OFFSET + 1]) << 8);
		System.out.println(peLocation + SIZE_OF_HEADER_OFFSET);
		System.out.println(optHeaderSize);
	}
	

}

class Windowsx86 extends WindowsEXE {
	
	// x86 opcodes http://www.c-jump.com/CIS77/reference/Instructions_by_Opcode.html
	// instruction encoding http://wiki.osdev.org/X86-64_Instruction_Encoding
	// http://www.c-jump.com/CIS77/CPU/x86/lecture.html
	
	public Windowsx86(byte[] file, int peLocation){
		super(file, peLocation);
	}
	
	public String instructionAt(int location){
		
	}
}

class Windowsx64 extends WindowsEXE {
	
	public Windowsx64(byte[] file, int peLocation){
		super(file, peLocation);
	}
	
	public String instructionAt(int location){
		
	}
}
