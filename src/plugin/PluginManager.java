package plugin;

import java.util.ArrayList;

public class PluginManager {

	
	private static PluginManager singleton = new PluginManager();
	public static PluginManager getManager(){
		return singleton;
	}
	
	private ArrayList<Class<Plugin>> plugins;
	
	private PluginManager(){
		plugins = new ArrayList<Class<Plugin>>();
	}
	
	private static String PATH_TO_PLUGINS = "";
	public void loadPlugins(){
		
	}
	
	private void addPlugin(Class<Plugin> plugin){
		if(!plugins.contains(plugin)){
			plugins.add(plugin);
		}
	}
	

}
