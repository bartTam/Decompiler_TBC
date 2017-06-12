package view;

import controller.IController;
import datatypes.BinaryFile;

public interface View {

	void register(IController controller);

	boolean promptToSave(String fileName, boolean exiting);
	
}
