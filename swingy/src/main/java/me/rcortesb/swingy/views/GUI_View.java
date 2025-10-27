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

	public void	setView() {
		GameStatus status = Controller.getController().getStatus();
		switch (status) {
			case IN_MENU:
				this.loadMenu();
				break ;
			case IN_CREATE_HERO:
				this.loadMenu();
				System.out.println("Load in create hero");
				break ;
			case IN_GAME:
				break ;
			case IN_BATTLE:
				System.out.println("Load in battle");
				break ;
		}
	}

	public void actionPerformed(ActionEvent e) {}

	public void loadMenu() {
		for (Component c : mainPanel.getComponents()) {
			if (c.getName().equals("menu")) {
				frame.setVisible(true);
				cardLayout.show(mainPanel, "menu");
				Controller.getController().setStatus(GameStatus.IN_MENU);
				return;
			}
		}
		mainPanel.add(GUIBuilder.getGUIBuilder().buildMenu(), "menu");
		mainPanel.revalidate();
		mainPanel.repaint();
		frame.setVisible(true);
	}

	public void changeViewMode() {
		this.frame.setVisible(false);
		Controller.getController().loadConsole(false);
	}

	public void deleteGUI() {
		this.frame.dispose();
		System.exit(0);
	}
}