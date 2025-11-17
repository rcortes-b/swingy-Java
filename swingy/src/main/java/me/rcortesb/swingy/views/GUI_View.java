package me.rcortesb.swingy.views;
import me.rcortesb.swingy.gui_utilities.*;
import me.rcortesb.swingy.controller.*;
import me.rcortesb.swingy.models.*;
import me.rcortesb.swingy.models.heroes.Hero;
import me.rcortesb.swingy.models.villains.Villain;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI_View extends ViewModel {
	private JFrame frame;
	private JPanel mainPanel;  //mainPanel.add(menuPanel, "menu");
	private CardLayout cardLayout; //cardLayout.show(mainPanel, "menu");


	public void loadGame(){} //to delete
	public void showExitFromGame(Game game){} //to delete
	public void showVictory(){} //to delete
	public void showDefeat(){} //to delete
	public void displayBattleResult(Hero hero, Villain villain) {} //to delete
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
 
 	/* View Loader */

	public boolean loadViewIfExists(String viewName) {
		for (Component c : mainPanel.getComponents()) {
			if (c.getName().equals(viewName)) {
				if (viewName.equals("heroListing") || viewName.equals("heroSelect") )
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
			mainPanel.add(GUIBuilder.getGUIBuilder().buildHeroMenu("heroMenu"), "heroMenu");
			mainPanel.revalidate();
			mainPanel.repaint();
			frame.setVisible(true);
			loadViewIfExists("heroMenu");
		}
	}

	public void listHeroes() {
		String createHeroView = "heroListing";
		if (Controller.getStatus() == GameStatus.IN_GAME_MENU)
			createHeroView = "heroSelect";
		if (loadViewIfExists(createHeroView) == false) {
			mainPanel.add(GUIBuilder.getGUIBuilder().buildHeroListing(), createHeroView);
			mainPanel.revalidate();
			mainPanel.repaint();
			frame.setVisible(true);
			loadViewIfExists(createHeroView);
		}
	}

	public void createHero() {
		if (loadViewIfExists("heroCreation") == false) {
			mainPanel.add(GUIBuilder.getGUIBuilder().buildHeroCreation(this.frame), "heroCreation");
			mainPanel.revalidate();
			mainPanel.repaint();
			frame.setVisible(true);
			loadViewIfExists("heroCreation");
		}
	}

	public void loadGameMenu() {
		controller.setStatus(GameStatus.IN_GAME_MENU);
		if (loadViewIfExists("gameMenu") == false) {
			mainPanel.add(GUIBuilder.getGUIBuilder().buildHeroMenu("gameMenu"), "gameMenu");
			mainPanel.revalidate();
			mainPanel.repaint();
			frame.setVisible(true);
			loadViewIfExists("gameMenu");
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