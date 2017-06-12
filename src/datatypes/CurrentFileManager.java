package datatypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import configuration.CLArgs;
import plugin.PluginManager;

public class CurrentFileManager {
	
	private HashMap<File, BinaryFile> openFiles;
	private File currentFile;
	private PluginManager pluginManager;
	
	public CurrentFileManager(CLArgs args, PluginManager pluginManager){
		openFiles = new HashMap<File, BinaryFile>();
		this.pluginManager = pluginManager;
		BinaryFile firstFile = args.getFile();
		if(firstFile != null){
			openFiles.put(firstFile.getFile(), firstFile);
			currentFile = firstFile.getFile();
		} else {
			currentFile = null;
		}
	}
	
	public BinaryFile getCurrentFile(){
		return openFiles.get(currentFile);
	}
	
	public void openNewFile(File file, boolean asCurrent) throws FileNotFoundException{
		if(file != null && file.exists()){
			if(asCurrent){
				currentFile = file;
			}
			openFiles.put(file, new BinaryFile(file));
		} else
			throw new FileNotFoundException();
	}
	
	public void openNewFile(File file) throws FileNotFoundException{
		openNewFile(file, true);
	}
	
	public void setCurrentFile(File file){
		currentFile = file;
	}

	public Collection<BinaryFile> getFiles() {
		return openFiles.values();
	}
	
}
