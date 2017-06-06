package view.cli;

import configuration.CLArgs;
import controller.IController;
import datatypes.CurrentFileManager;
import view.View;

public class CLIView implements View {
	
	private IController controller;

	public CLIView(CLArgs args, CurrentFileManager manager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void register(IController controller) {
		this.controller = controller;
	}
	
}
