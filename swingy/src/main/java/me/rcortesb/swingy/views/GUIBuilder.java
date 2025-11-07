package me.rcortesb.swingy.views;
import me.rcortesb.swingy.gui_utilities.*;
import me.rcortesb.swingy.controller.Controller;
import me.rcortesb.swingy.models.Hero;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIBuilder {
	private static GUIBuilder guiBuilder =  new GUIBuilder();
	private static CustomButtons buttons;
	private static JPanel listPanel; 

	private GUIBuilder() {
		this.listPanel = null;
		buttons = new CustomButtons();
	}

	public static GUIBuilder getGUIBuilder() {
		return guiBuilder;
	}

	public static JPanel buildMenu() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JButton startGameButton = buttons.getMenuButton("Start Game");
		JButton createHeroButton = buttons.getMenuButton("Create a Hero");
		JButton changeUIButton = buttons.getMenuButton("Change to Console Mode");
		JButton exitGameButton = buttons.getMenuButton("Exit");

		JLabel gameTitle = new JLabel("Welcome to Achieve the Border!");
		gameTitle.setFont(new Font("Serif", Font.BOLD, 28));
		gameTitle.setForeground(Color.WHITE);
    	gameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);	

		panel.setBackground(Color.DARK_GRAY);
		panel.add(Box.createVerticalStrut(20));
		panel.add(gameTitle);
		panel.add(Box.createVerticalStrut(60));
		panel.add(startGameButton);
		panel.add(Box.createVerticalStrut(30));
		panel.add(createHeroButton);
		panel.add(Box.createVerticalStrut(30));
		panel.add(changeUIButton);
		panel.add(Box.createVerticalStrut(30));
		panel.add(exitGameButton);

		JPanel bigPanel = new JPanel(new BorderLayout());
		bigPanel.add(panel, BorderLayout.CENTER);
		bigPanel.add(bannerItem(), BorderLayout.SOUTH);
		bigPanel.setName("menu");
		return bigPanel;
	}

	private static JPanel bannerItem() {
		JLabel socialMessage = new JLabel("Check out my social media and my contact info!");
		socialMessage.setFont(new Font("SansSerif", Font.BOLD, 16));
		socialMessage.setForeground(Color.YELLOW);
		socialMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel southWrapper = new JPanel();
		southWrapper.setLayout(new BoxLayout(southWrapper, BoxLayout.Y_AXIS));
		southWrapper.add(socialMessage);
		southWrapper.add(Box.createVerticalStrut(5));

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		CustomLabels socialLabels = new CustomLabels();
		JLabel linkedinURL = socialLabels.getSocialMenuLabels("https://www.linkedin.com/in/raulcortesb");
		JLabel githubURL = socialLabels.getSocialMenuLabels("https://github.com/rcortes-b");
		JLabel mailAddress = socialLabels.getSocialMenuLabels("raulcortes.dev@gmail.com");


		panel.setBackground(Color.DARK_GRAY);
		panel.add(githubURL);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(linkedinURL);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(mailAddress);
		southWrapper.add(panel);
		southWrapper.setBackground(Color.DARK_GRAY);

		return southWrapper;
	}

	public static JPanel buildHeroMenu() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel gameTitle = new JLabel("Welcome to Hero manager!");
		gameTitle.setFont(new Font("Serif", Font.BOLD, 28));
		gameTitle.setForeground(Color.WHITE);
    	gameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton startGameButton = buttons.getHeroMenuButton("Create Hero");
		JButton createHeroButton = buttons.getHeroMenuButton("List Heroes");
		JButton changeUIButton = buttons.getHeroMenuButton("Change to Console Mode");
		JButton exitGameButton = buttons.getHeroMenuButton("Back To Menu");

		panel.setBackground(Color.DARK_GRAY);
		panel.add(Box.createVerticalStrut(20));
		panel.add(gameTitle);
		panel.add(Box.createVerticalStrut(60));
		panel.add(startGameButton);
		panel.add(Box.createVerticalStrut(30));
		panel.add(createHeroButton);
		panel.add(Box.createVerticalStrut(30));
		panel.add(changeUIButton);
		panel.add(Box.createVerticalStrut(30));
		panel.add(exitGameButton);

		JPanel bigPanel = new JPanel(new BorderLayout());
		bigPanel.add(panel, BorderLayout.CENTER);
		bigPanel.add(bannerItem(), BorderLayout.SOUTH);
		bigPanel.setName("heroMenu");
		return bigPanel;	
	}

	public JPanel buildHeroListing() {
		List<Hero> heroes = Controller.getGameModel().getHeroes();
		int row_size = heroes.size();
		int col_size = 5;

		/* GridLayout Listing*/
		JPanel gridPanel = new JPanel(new GridLayout(row_size + 1, col_size));
		gridPanel.setBackground(Color.DARK_GRAY);

		gridPanel.add(createLabel("NAME", true));
		gridPanel.add(createLabel("CLASS", true));
		gridPanel.add(createLabel("ATTACK", true));
		gridPanel.add(createLabel("DEFENSE", true));
		gridPanel.add(createLabel("HP", true));

		for (int row = 0; row < row_size; row++) {
			Hero hero = heroes.get(row);
			gridPanel.add(createLabel(hero.getName(), false));
			gridPanel.add(createLabel(hero.getClassType(), false));
			gridPanel.add(createLabel(String.valueOf(hero.getAttack()), false));
			gridPanel.add(createLabel(String.valueOf(hero.getDefense()), false));
			gridPanel.add(createLabel(String.valueOf(hero.getHP()), false));
		}

		/* Button of "Ok" --> redirects to HeroMenu */
		JButton button = new JButton("OK");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setFocusPainted(false);
		button.setFont(new Font("SansSerif", Font.PLAIN, 18));
		button.addActionListener(e -> Controller.getGUI().loadHeroMenu());

		/* Vertical ScrollBar linked to the GridLayout */
		JScrollPane scrollPane = new JScrollPane(gridPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();

		JPanel bigPanel = new JPanel(new BorderLayout());
		bigPanel.add(scrollPane, BorderLayout.CENTER);
		bigPanel.add(button, BorderLayout.SOUTH);
		bigPanel.setName("heroListing");
		return bigPanel;	
	}

	private JLabel createLabel(String txt, boolean isTitle) {
		JLabel label = new JLabel(txt, SwingConstants.CENTER);
		if (isTitle == true)
			label.setFont(new Font("SansSerif", Font.BOLD, 28));
		else
			label.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label.setForeground(Color.white);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return label;
	}
	//heroList --> get panel component --> add new row --> add values
}