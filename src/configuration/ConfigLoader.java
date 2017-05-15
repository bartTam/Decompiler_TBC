package configuration;

import java.io.File;
import java.util.prefs.Preferences;

public class ConfigLoader {

	private static final Preferences compPrefs = Preferences.systemNodeForPackage(ConfigLoader.class);
	private static final Preferences userPrefs = Preferences.userNodeForPackage(ConfigLoader.class);
	
	private static final String DEFAULT_APPLICATION_FOLDER = System.getProperty("user.home");
	private static final String APP_LOCATION = "App Location";
	private static final String APP_LOCATION_NOT_DEFINED = "App Location Not Defined *";
	public static Configuration getConfig(){
		String appLocation = userPrefs.get(APP_LOCATION, APP_LOCATION_NOT_DEFINED);
		if(appLocation.equals(APP_LOCATION_NOT_DEFINED)){
			userPrefs.put(APP_LOCATION, DEFAULT_APPLICATION_FOLDER);
			appLocation = DEFAULT_APPLICATION_FOLDER;
		}
		
		return new Configuration(new File(appLocation));
		
	}
	
}
