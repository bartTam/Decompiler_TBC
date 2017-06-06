package configuration;

import java.util.prefs.Preferences;

public class Configuration {

	private static final Preferences USER_PREFS = Preferences.userNodeForPackage(Configuration.class);
	private static final String FIRST_TIME = "First Time";
	
	public Configuration() {
		if(USER_PREFS.getBoolean(FIRST_TIME, true)){
			
		}
	}

}
