package scripting;

import javax.script.ScriptEngineManager;

public class ScriptingFactory {
	public static final String JAVA_SCRIPT = "JavaScript";
	public static final String PYTHON = "Jython";
	public ScriptingEngine getScriptingEngine(String engine){
		ScriptEngineManager manager = new ScriptEngineManager();
		switch (engine){
			case JAVA_SCRIPT:
				return new JSScripting(manager);
			case PYTHON:
				return new JythonScripting(manager);
			default:
				return null;
		}
	}
}
