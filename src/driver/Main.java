package driver;

import configuration.CLArgs;
import controller.Controller;
import datatypes.CurrentFileManager;
import plugin.PluginManager;
import view.View;
import view.ViewFactory;

public class Main {
	
	public static void main(String[] args) {
		CLArgs arguments = new CLArgs(args);
		PluginManager pluginManager = PluginManager.getManager();
		pluginManager.loadPlugins();
		CurrentFileManager fileManager = new CurrentFileManager(arguments, pluginManager);
		View view = new ViewFactory().getView(arguments, fileManager, pluginManager);
		Controller controller = new Controller(view, fileManager, pluginManager);
		controller.start();
	}

}
