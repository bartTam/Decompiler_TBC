package controller;

import view.actions.IViewAction;

public interface IController extends Runnable{

	public void viewInput(IViewAction action);

	public void exit();
}