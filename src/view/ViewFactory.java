package view;


import configuration.CLArgs;
import datatypes.CurrentFileManager;
import plugin.PluginManager;
import view.cli.CLIView;
import view.gui.GuiView;

public class ViewFactory {
	
	public View getView(CLArgs args, CurrentFileManager manager, PluginManager pluginManager){
		if(args.withGui()){
			return new GuiView(args, manager);
		} else {
			return new CLIView(args, manager);
		}
	}
}
