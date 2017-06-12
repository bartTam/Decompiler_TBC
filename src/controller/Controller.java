package controller;

import datatypes.BinaryFile;
import datatypes.CurrentFileManager;
import plugin.PluginManager;
import view.View;
import view.actions.IViewAction;

public class Controller extends Thread implements IController {
	
	private View view;
	private CurrentFileManager fileManager;
	private PluginManager pluginManager;
	
	public Controller(View view, CurrentFileManager fileManager, PluginManager pluginManager){
		this.view = view;
		this.fileManager = fileManager;
		this.pluginManager = pluginManager;
		view.register(this);
	}
	
	@Override
	public void run(){
		
	}

	@Override
	public void viewInput(IViewAction action) {
		
	}

	@Override
	public void exit() {
		for(BinaryFile file : fileManager.getFiles()){
			if(file.isModified()){
				view.promptToSave(file.getFile().getName(), true);
			}
		}
	}
	
}
