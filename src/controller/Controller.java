package controller;

import datatypes.CurrentFileManager;
import plugin.PluginManager;
import view.View;

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
	
	public void run(){
		
	}
	
}
