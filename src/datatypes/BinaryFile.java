package datatypes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import datatypes.architectures.Architecture;
import datatypes.filetypes.FileFormat;

public class BinaryFile {
	private FileFormat type;
	private Architecture arch;
	private byte[][] binFile;
	private long size;
	private File file;
	
	public BinaryFile(File file){
		InputStream fileHeader;
		this.file = file;
		size = file.length();
		try {
			fileHeader = new BufferedInputStream(new FileInputStream(file));
			long currentSize = size;
			int locationInMatrix = 0;
			binFile = new byte[(int) (size/Integer.MAX_VALUE + (size%Integer.MAX_VALUE > 0 ? 1 : 0))][];
			while(currentSize > 0){
				if(currentSize > Integer.MAX_VALUE){
					binFile[locationInMatrix] = new byte[Integer.MAX_VALUE];
				}else {
					binFile[locationInMatrix] = new byte[(int) currentSize];
				}
				fileHeader.read(binFile[locationInMatrix]);

				currentSize -= binFile[locationInMatrix].length;
				locationInMatrix++;
			}
			fileHeader.close();
			type = FileFormat.scanHeader(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getFile(){
		return file;
	}

	/**
	 * This will check if a sequence of bytes occurs at an offset into the binary file.
	 * 
	 * @param offset The offset to check at.
	 * @param bytes The sequence of chars to check at
	 * @return Returns true if the sequence occurs, false otherwise
	 */
	public boolean checkBytesAtLocation(long offset, byte[] bytes){
		return checkBytesAtLocation(offset, 0, bytes);
	}
	
	public boolean checkBytesAtLocation(long offset, int bitOffset, byte[] bytes){
		for(int i = 0; i < bytes.length; i++){
			if(byteAt(offset + i, bitOffset) != bytes[i]){
				System.out.println("Offset at " + i + ", " + byteAt(offset + i, bitOffset) + "!=" + bytes[i]);
				return false;
			}
		}
		return true;
	}
	

	public byte byteAt(long offset){
		return binFile[(int) (offset/Integer.MAX_VALUE)][(int) (offset%Integer.MAX_VALUE)];
	}
	
	public byte byteAt(long offset, int bitOffset){
		byte prev = (byte) (byteAt(offset) << bitOffset);
		byte next = (byte) (byteAt(offset+1) >>> (8-bitOffset));
		return (byte) (prev | next);
	}

	public byte[] copyNumBytes(long offset, int numBytes){
		return copyNumBytes(offset, 0, numBytes);
	}
	
	public byte[] copyNumBytes(long offset, int bitOffset, int numBytes){
		byte[] output = new byte[numBytes];
		for(int i = 0; i < numBytes; i++){
			output[i] = byteAt(offset + i, bitOffset);
		}
		return output;
	}
	
	public int intAt(long offset, boolean bigEndian){
		return intAt(offset, 0, bigEndian);
	}
	
	public int intAt(long offset, int bitOffset, boolean bigEndian){
		final ByteBuffer buff = ByteBuffer.wrap(copyNumBytes(offset, bitOffset, 4));
		buff.order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
		return buff.getInt();
	}
	
	public long longAt(long offset, boolean bigEndian){
		return longAt(offset, 0, bigEndian);
	}
	
	public long longAt(long offset, int bitOffset, boolean bigEndian){
		final ByteBuffer buff = ByteBuffer.wrap(copyNumBytes(offset, bitOffset, 8));
		buff.order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
		return buff.getLong();
	}
}
