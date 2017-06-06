package scripting;

import javax.script.ScriptEngineManager;

class JSScripting implements ScriptingEngine {
	
	javax.script.ScriptEngine engine;
	
	JSScripting(ScriptEngineManager manager){
		engine = manager.getEngineByName("nashorn");
	}

}
