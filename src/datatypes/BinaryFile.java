package datatypes;

public class BinaryFile {
	private byte[] binFile;
	
	public BinaryFile(byte[] binFile){
		this.binFile = binFile;
	}
	
	public byte byteAt(int offset){
		return binFile[offset];
	}
	
	public byte byteAt(int offset, int bitOffset){
		byte prev = (byte) (binFile[offset] << bitOffset);
		byte next = (byte) (binFile[offset+1] >>> (8-bitOffset));
		return (byte) (prev | next);
	}

	public byte[] copyNumBytes(int offset, int numBytes){
		byte[] output = new byte[numBytes];
		for(int i = 0; i < numBytes; i++){
			output[i] = binFile[offset + i];
		}
		return output;
	}
}
