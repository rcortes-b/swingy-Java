package me.rcortesb.swingy.views;

public abstract class View {
	protected String name;

	public abstract String getView();
	public abstract void displayMenu();
	public abstract void displayGameOptions();
	public abstract void welcomeInterface();
}