package datatypes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import datatypes.architectures.Architecture;
import datatypes.filetypes.FileFormat;

public class BinaryFile {
	private FileFormat type;
	private Architecture arch;
	private byte[] binFile;
	
	public BinaryFile(File file){
		InputStream fileHeader;
		try {
			fileHeader = new BufferedInputStream(new FileInputStream(file));
			binFile = new byte[file.length() > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)file.length()];
			fileHeader.read(binFile);
			fileHeader.close();
			type = FileFormat.scanHeader(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * This will check if a sequence of bytes occurs at an offset into the binary file.
	 * 
	 * @param offset The offset to check at.
	 * @param bytes The sequence of chars to check at
	 * @return Returns true if the sequence occurs, false otherwise
	 */
	public boolean checkBytesAtLocation(int offset, byte[] bytes){
		for(int i = 0; i < bytes.length; i++){
			if(byteAt(offset + i) != bytes[i]){
				return false;
			}
		}
		return true;
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
