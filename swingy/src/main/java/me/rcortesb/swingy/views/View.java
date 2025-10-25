package me.rcortesb.swingy.views;
import me.rcortesb.swingy.controller.Controller;
public abstract class View {
	public abstract String getView();
	public abstract void displayMenu();
	public abstract void displayGameOptions();
	public abstract void launchApp();
}