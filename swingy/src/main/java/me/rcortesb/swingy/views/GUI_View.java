package me.rcortesb.swingy.views;
import me.rcortesb.swingy.gui_utilities.*;
import me.rcortesb.swingy.controller.*;
import me.rcortesb.swingy.models.*;
import me.rcortesb.swingy.models.heroes.Hero;
import me.rcortesb.swingy.models.villains.Villain;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.KeyStroke;
import java.awt.*;
import java.awt.event.*;

public class GUI_View extends ViewModel {
	private JFrame frame;
	private JPanel mainPanel;  //mainPanel.add(menuPanel, "menu");
	private CardLayout cardLayout; //cardLayout.show(mainPanel, "menu");

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
		final String viewName = "heroListing";
		
		mainPanel.add(GUIBuilder.getGUIBuilder().buildHeroListing(), viewName);
		mainPanel.revalidate();
		mainPanel.repaint();
		frame.setVisible(true);
		cardLayout.show(mainPanel, viewName);
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

	public void loadMap(Game game) {
		final String viewName = "map";
		boolean exists = false;
		for (Component c : mainPanel.getComponents()) {
			if (viewName.equals(c.getName()))
				exists = true;
		}
		if (exists == false) {
			final JPanel mapPanel = GUIBuilder.getGUIBuilder().buildMap(game);
			mapPanel.setFocusable(true);
			bindKeys(mapPanel, game);
			mainPanel.add(mapPanel, viewName);
			mainPanel.revalidate();
			mainPanel.repaint();
		}
        cardLayout.show(mainPanel, viewName);
        frame.setVisible(true);
	}

	public void loadGame() {
		Controller.setStatus(GameStatus.IN_GAME);
		Game game = Controller.getGame();
		this.showGameOptions();
		this.loadMap(game);
	}

	private void showGameOptions() {
		List<String> messages = new ArrayList<>();
		messages.add("You can move the hero using the keyboard arrow keys:");
		messages.add("     Up arrow: Move one position to the North");
		messages.add("     Down arrow: Move one position to the South");
		messages.add("     Right Arrow: Move one position to the East");
		messages.add("     Left Arrow: Move one position to the West");
		messages.add( "Press the key \"Esc\" from your keyboard to change to console or leave the game");
		GUIBuilder.getGUIBuilder().buildCustomPopUp("INSTRUCTIONS", messages, frame);
	}

	public void displayBattleResult(Hero hero, Villain villain) {
		List<String> messages = new ArrayList<>();
		messages.add("You've fallen in a villain trap!!");
		if (hero.getHP() > 0)
			messages.add(hero.getName() + " has beaten the " + villain.getVillainType());
		else
			messages.add(hero.getName() + " has been beaten by " + villain.getVillainType());
		GUIBuilder.getGUIBuilder().buildCustomPopUp("BATTLE ALERT!", messages, frame);
	}

	public void showDefeat() {
		String msg = "YOU'VE BEEN DEFEATED. GOOD LUCK NEXT TIME!";
		GUIBuilder.getGUIBuilder().getResultPopUp(frame, msg, "WE'RE SORRY", "GO TO MENU");
	}

	public void showVictory() {
		String msg = "YOU'VE REACHED A BORDER AND YOU'VE SUCCESSFULLY WON THE GAME";
		GUIBuilder.getGUIBuilder().getResultPopUp(frame, msg, "CONGRATULATIONS!", "LET'S GO");
	}

	public void showExitFromGame(Game game){
		GUIBuilder.getGUIBuilder().getExitPopUp(frame);
	}

	public void changeView() {
		this.frame.setVisible(false);
		controller.changeView();
	}

	public void deleteView(String viewName) {
		for (Component c : mainPanel.getComponents()) {
			if (viewName.equals(c.getName())) {
				mainPanel.remove(c);
				if (viewName.equals("map"))
					cardLayout.show(mainPanel, "menu");
				mainPanel.revalidate();
				mainPanel.repaint();
			}
		}
	}

	public void deleteGUI() {
		this.frame.dispose();
	}

	private void bindKeys(JPanel component, Game game) {
		final String MOVE_NORTH = "moveNorth";
		final String MOVE_SOUTH = "moveSouth";
		final String MOVE_EAST = "moveEast";
		final String MOVE_WEST = "moveWest";
		final String ESC_KEY = "escMenu";
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), MOVE_NORTH);
		component.getActionMap().put(MOVE_NORTH, new MoveAction(component, GameMove.NORTH, game));
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_SOUTH);
		component.getActionMap().put(MOVE_SOUTH, new MoveAction(component, GameMove.SOUTH, game));
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), MOVE_EAST);
		component.getActionMap().put(MOVE_EAST, new MoveAction(component, GameMove.EAST, game));
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), MOVE_WEST);
		component.getActionMap().put(MOVE_WEST, new MoveAction(component, GameMove.WEST, game));
		component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), ESC_KEY);
		component.getActionMap().put(ESC_KEY, new GameMenuOptions(game));
	}

	private class MoveAction extends AbstractAction {
		JPanel panel;
		GameMove dir;
		Game game;
        MoveAction(JPanel c, GameMove d, Game g) {
            this.panel = c;
            this.dir = d;
			this.game = g;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
			GUIBuilder.getGUIBuilder().updateMap(panel, game, dir);
			game.playTurn();
			if (!game.gameIsFinished())
				loadMap(game);
		}
    }

	private class GameMenuOptions extends AbstractAction {
		Game game;

        GameMenuOptions(Game g) {
			this.game = g;
		}

        @Override
        public void actionPerformed(ActionEvent e) {
			showExitFromGame(this.game);
		}
    }
}