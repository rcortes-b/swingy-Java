package me.rcortesb.swingy.views;
import me.rcortesb.swingy.gui_utilities.*;
import me.rcortesb.swingy.controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI_View implements ViewModel {
	private JFrame frame;
	private JPanel mainPanel;  //mainPanel.add(menuPanel, "menu");
	private CardLayout cardLayout; //cardLayout.show(mainPanel, "menu");
	private boolean	firstCard = true;
	private Controller controller = Controller.getController();

	public GUI_View() {
		this.frame = new JFrame("Achieve The Border");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		screen.width /= 2;
		screen.height /= 2;
		this.frame.setPreferredSize(screen);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.cardLayout = new CardLayout();
		this.mainPanel = new JPanel(this.cardLayout);
		this.mainPanel.setPreferredSize(screen);
		this.frame.add(this.mainPanel);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
	}
	public void launch() {
		this.loadMenu();
	}

	public void	setView() {
		GameStatus status = controller.getStatus();
		switch (status) {
			case IN_MENU:
				this.loadMenu();
				break ;
			case IN_HERO_MENU:
				this.loadHeroMenu();
				break ;
			case IN_GAME:
				break ;
			case IN_BATTLE:
				System.out.println("Load in battle");
				break ;
		}
	}
 
 	/* View Loader */

	public boolean loadViewIfExists(String viewName) {
		for (Component c : mainPanel.getComponents()) {
			if (c.getName().equals(viewName)) {
				if (viewName.equals("heroListing"))
					GUIBuilder.getGUIBuilder().updateList((JPanel)c);
				frame.setVisible(true);
				cardLayout.show(mainPanel, viewName);
				return true;
			}
		}
		return false;
	}

	public void loadMenu() {
		controller.setStatus(GameStatus.IN_MENU);
		if (loadViewIfExists("menu") == false) {
			mainPanel.add(GUIBuilder.getGUIBuilder().buildMenu(), "menu");
			mainPanel.revalidate();
			mainPanel.repaint();
			frame.setVisible(true);
			loadViewIfExists("menu");
		}
	}

	public void loadHeroMenu() {
		controller.setStatus(GameStatus.IN_HERO_MENU);
		if (loadViewIfExists("heroMenu") == false) {
			mainPanel.add(GUIBuilder.getGUIBuilder().buildHeroMenu(), "heroMenu");
			mainPanel.revalidate();
			mainPanel.repaint();
			frame.setVisible(true);
			loadViewIfExists("heroMenu");
		}
	}

	public void loadListHeroes() {
		if (loadViewIfExists("heroListing") == false) {
			mainPanel.add(GUIBuilder.getGUIBuilder().buildHeroListing(), "heroListing");
			mainPanel.revalidate();
			mainPanel.repaint();
			frame.setVisible(true);
			loadViewIfExists("heroListing");
		}
	}

	public void loadHeroCreation() {
		if (loadViewIfExists("heroCreation") == false) {
			mainPanel.add(GUIBuilder.getGUIBuilder().buildHeroCreation(this.frame), "heroCreation");
			mainPanel.revalidate();
			mainPanel.repaint();
			frame.setVisible(true);
			loadViewIfExists("heroCreation");
		}
	}

	public void changeView() {
		this.frame.setVisible(false);
		controller.changeView();
	}

	public void deleteGUI() {
		this.frame.dispose();
	}
}