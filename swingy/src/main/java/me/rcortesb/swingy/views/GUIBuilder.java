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

		gridPanel.add(createLabel("NAME", true, 28, Color.white));
		gridPanel.add(createLabel("CLASS", true, 28, Color.white));
		gridPanel.add(createLabel("ATTACK", true, 28, Color.white));
		gridPanel.add(createLabel("DEFENSE", true, 28, Color.white));
		gridPanel.add(createLabel("HP", true, 28, Color.white));

		for (int row = 0; row < row_size; row++) {
			Hero hero = heroes.get(row);
			gridPanel.add(createLabel(hero.getName(), false, 16, Color.white));
			gridPanel.add(createLabel(hero.getClassType(), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getAttack()), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getDefense()), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getHP()), false, 16, Color.white));
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

	private JLabel createLabel(String txt, boolean isTitle, int size, Color color) {
		JLabel label = new JLabel(txt, SwingConstants.CENTER);
		if (isTitle == true)
			label.setFont(new Font("SansSerif", Font.BOLD, size));
		else
			label.setFont(new Font("SansSerif", Font.PLAIN, size));
		label.setForeground(color);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return label;
	}
	
	public JPanel buildHeroCreation() {

		JLabel titleLabel = new JLabel("CUSTOMIZE YOUR OWN HERO!");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		titleLabel.setForeground(Color.white);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


		JPanel gridPanel = new JPanel(new GridLayout(5, 2));
	
		gridPanel.add(createLabel("NAME", false, 24, Color.black));
		gridPanel.add(new JTextField());

		gridPanel.add(createLabel("CLASS", false, 24, Color.black));
		gridPanel.add(new JTextField());

		gridPanel.add(createLabel("ATTACK", false, 24, Color.black));
		gridPanel.add(new JTextField());

		gridPanel.add(createLabel("DEFENSE", false, 24, Color.black));
		gridPanel.add(new JTextField());

		gridPanel.add(createLabel("HP", false, 24, Color.black));
		gridPanel.add(new JTextField());
		
		JButton acceptButton = new JButton("ACCEPT");
		acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		acceptButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancelButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		cancelButton.addActionListener(e -> Controller.getGUI().loadHeroMenu());

		JLabel infoLabel = createLabel("*Class must either be: Warrior, Wizard or Healer", false, 18, Color.white);
		infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoLabel.setBorder(null);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setBackground(Color.DARK_GRAY);
		topPanel.add(Box.createVerticalStrut(40));
		topPanel.add(titleLabel);
		topPanel.add(Box.createVerticalStrut(80));
		topPanel.add(gridPanel);
		topPanel.add(Box.createVerticalStrut(40));
		topPanel.add(acceptButton);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(cancelButton);
		topPanel.add(Box.createVerticalStrut(20));
		topPanel.add(infoLabel);

		JPanel bigPanel = new JPanel();
		bigPanel.setBackground(Color.DARK_GRAY);
		bigPanel.add(topPanel);
		bigPanel.setName("heroCreation");
		return bigPanel;	
	}
}