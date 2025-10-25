package me.rcortesb.swingy.views;

public class ViewFactory {
	private static ViewFactory viewFactory= new ViewFactory();

	private ViewFactory() {}

	public static ViewFactory getViewFactory() {
		return viewFactory;
	}

	public static View chooseView(String mode) {
		if (mode.equals("console")) {
			return new Console_View();
		}
		return new GUI_View();
	}
}