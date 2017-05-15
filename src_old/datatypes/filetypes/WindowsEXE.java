package datatypes.filetypes;

import datatypes.BinaryFile;

abstract class WindowsEXE implements FileFormat {

	static final byte[] MAGIC_NUMBER = {0x4d, 0x5a};
	
	private static final long PE_HEADER_POINTER = 0x3C;
	private static final long ARCH_OFFSET_FROM_HEADER = 4;
	private static final long SIZE_OF_HEADER_OFFSET = 0x14;

	public static WindowsEXE getWindowsEXE(BinaryFile file){
		long peLocation = file.intAt(PE_HEADER_POINTER, true);
		
		if(Windowsx86.isx86(file, peLocation + ARCH_OFFSET_FROM_HEADER)){
			System.out.println("Windows x86");
			return new Windowsx86(file, peLocation);
		} else if(Windowsx64.isx64(file, peLocation + ARCH_OFFSET_FROM_HEADER)){
			System.out.println("Windows x64");
			return new Windowsx64(file, peLocation);
		} else {
			System.out.println("Something went wrong");
			return null;
		}
	}
	
	
	protected final BinaryFile file;
	protected final long peLocation;
	protected final long optHeaderSize;

	protected WindowsEXE(BinaryFile file, long peLocation){
		this.file = file;
		this.peLocation = peLocation;
		optHeaderSize = file.longAt(SIZE_OF_HEADER_OFFSET, true);
		System.out.println(peLocation + SIZE_OF_HEADER_OFFSET);
		System.out.println(optHeaderSize);
	}
	

}

class Windowsx86 extends WindowsEXE {
	
	private static final byte[] X86_INDICATOR = {0x4C, 0x01};
	public static boolean isx86(BinaryFile file, long offset){
		return file.checkBytesAtLocation(offset, X86_INDICATOR);
	}
	
	// x86 opcodes http://www.c-jump.com/CIS77/reference/Instructions_by_Opcode.html
	// instruction encoding http://wiki.osdev.org/X86-64_Instruction_Encoding
	// http://www.c-jump.com/CIS77/CPU/x86/lecture.html
	
	public Windowsx86(BinaryFile file, long peLocation){
		super(file, peLocation);
	}
	
	public String instructionAt(long location){
		return "";
	}
}

class Windowsx64 extends WindowsEXE {
	
	private static final byte[] X64_INDICATOR = {0x64, (byte) 0x86};
	public static boolean isx64(BinaryFile file, long offset){
		return file.checkBytesAtLocation(offset, X64_INDICATOR);
	}
	
	public Windowsx64(BinaryFile file, long peLocation){
		super(file, peLocation);
	}
	
	public String instructionAt(long location){
		return "";
	}
}
