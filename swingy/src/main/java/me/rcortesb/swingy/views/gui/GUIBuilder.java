package me.rcortesb.swingy.views.gui;
import me.rcortesb.swingy.controller.*;
import me.rcortesb.swingy.models.game.Game;
import me.rcortesb.swingy.models.heroes.*;
import me.rcortesb.swingy.models.artifacts.Artifact;
import java.util.ArrayList;
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

	public static JPanel buildHeroMenu(String viewLabel) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel gameTitle = new JLabel("Welcome to Hero manager!");
		gameTitle.setFont(new Font("Serif", Font.BOLD, 28));
		gameTitle.setForeground(Color.WHITE);
    	gameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton createHeroButton = buttons.getHeroMenuButton("Create Hero");
		JButton displayHeroesButton = buttons.getHeroMenuButton("List Heroes");
		if (viewLabel.equals("gameMenu"))
			displayHeroesButton.setText("Select Hero");
		JButton changeUIButton = buttons.getHeroMenuButton("Change to Console Mode");
		JButton goMenuButton = buttons.getHeroMenuButton("Back To Menu");

		panel.setBackground(Color.DARK_GRAY);
		panel.add(Box.createVerticalStrut(20));
		panel.add(gameTitle);
		panel.add(Box.createVerticalStrut(60));
		panel.add(createHeroButton);
		panel.add(Box.createVerticalStrut(30));
		panel.add(displayHeroesButton);
		panel.add(Box.createVerticalStrut(30));
		panel.add(changeUIButton);
		panel.add(Box.createVerticalStrut(30));
		panel.add(goMenuButton);

		JPanel bigPanel = new JPanel(new BorderLayout());
		bigPanel.add(panel, BorderLayout.CENTER);
		bigPanel.add(bannerItem(), BorderLayout.SOUTH);
		bigPanel.setName(viewLabel);
		return bigPanel;	
	}

	public JPanel buildHeroListing() {
		List<Hero> heroes = Controller.getGameModel().getHeroes();
		int row_size = heroes.size();
		int col_size = 7;
		boolean inGame = false;

		if (Controller.getStatus() == GameStatus.IN_GAME_MENU) {
			inGame = true;
			col_size++;
		}

		/* GridLayout Listing*/
		JPanel gridPanel = new JPanel(new GridLayout(row_size + 1, col_size));
		gridPanel.setBackground(Color.DARK_GRAY);

		gridPanel.add(createLabel("NAME", true, 28, Color.white));
		gridPanel.add(createLabel("CLASS", true, 28, Color.white));
		gridPanel.add(createLabel("LEVEL", true, 28, Color.white));
		gridPanel.add(createLabel("EXP", true, 28, Color.white));
		gridPanel.add(createLabel("ATTACK", true, 28, Color.white));
		gridPanel.add(createLabel("DEFENSE", true, 28, Color.white));
		gridPanel.add(createLabel("HP", true, 28, Color.white));
		if (inGame == true)
			gridPanel.add(createLabel("", true, 28, Color.white));
		
		for (int row = 0; row < row_size; row++) {
			Hero hero = heroes.get(row);
			gridPanel.add(createLabel(hero.getName(), false, 16, Color.white));
			gridPanel.add(createLabel(hero.getClassType(), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getLevel()), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getExperience()), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getAttack()), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getDefense()), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getHP()), false, 16, Color.white));
			if (inGame == true) {
				JButton selectButton = new JButton("Select");
				selectButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Controller.startGame(hero);
						Controller.getGUI().deleteView("heroListing");
					}
				});
				gridPanel.add(selectButton);
			}
		}

		JButton button = new JButton("OK");
		if (inGame == true)
			button.setText("CANCEL");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setFocusPainted(false);
		button.setFont(new Font("SansSerif", Font.PLAIN, 18));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getGUI().setView();
				Controller.getGUI().deleteView("heroListing");
			}
		});
		/* Vertical && Horizontal ScrollBar linked to the GridLayout */
		JScrollPane scrollPane = new JScrollPane(gridPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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
	
	public JPanel buildHeroCreation(JFrame frame) {
		String[] labelGrid = {"NAME", "CLASS"};
		JTextField[] fieldValues = new JTextField[2];
		JLabel titleLabel = new JLabel("CUSTOMIZE YOUR OWN HERO!");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		titleLabel.setForeground(Color.white);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


		JPanel gridPanel = new JPanel(new GridLayout(2, 2));
	
		for (int i = 0; i < 2; i++) {
			gridPanel.add(createLabel(labelGrid[i], false, 24, Color.black));
			fieldValues[i] = new JTextField();
			fieldValues[i].setFont(new Font("SansSerif", Font.PLAIN, 24));
			gridPanel.add(fieldValues[i]);
		}
		
		JButton acceptButton = new JButton("ACCEPT");
		acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		acceptButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		acceptButton.addActionListener(e -> handleHeroCreation(fieldValues, frame));
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancelButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		cancelButton.addActionListener(e -> Controller.getGUI().setView());

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
	/*Step-by-Step getItems for JTextField --> validate them (valid string, valid numbers)
	--> validateHero not exits --> do real Validation --> write into DB */
	private void handleHeroCreation(JTextField txtFields[], JFrame frame) {
		String[] value = new String[2];
		List<String> error_log = new ArrayList<>();

		for (int i = 0; i < 2; i++)
			value[i] = txtFields[i].getText();
		if (Controller.getGameModel().heroExists(value[0]) == true)
			error_log.add("Error: A hero with name " + value[0] + " already exists.");
		if (Controller.getGameModel().isValidClass(value[1]) == false)
			error_log.add("Bad input: Class must be either Warrior, Wizard or Healer");

		Hero new_hero = Controller.getGameModel().generateHero(value[0], value[1]);
		if (Controller.getGameModel().validateHero(new_hero, error_log));
		if (error_log.size() == 0) {
			Controller.getController().addHero(new_hero);
			if (Controller.getStatus() == GameStatus.IN_HERO_MENU)
					Controller.getGUI().loadHeroMenu();
			else {
				Controller.startGame(new_hero);
			}
		}
		else
			buildCustomPopUp("ERROR LOG", error_log, frame);
	}

	public void buildCustomPopUp(String title, List<String> messages, JFrame frame) {
		JDialog dialog = new JDialog(frame, title, true);
		
		JPanel dialogPanel= new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
		for (String msg : messages) {
			JLabel label = createLabel("- " + msg, false, 16, Color.black);
			label.setBorder(null);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			dialogPanel.add(Box.createVerticalStrut(20));
       		dialogPanel.add(label);
		}

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button = new JButton("OK");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> dialog.setVisible(false)); 
		buttonPanel.add(button);
        
		dialogPanel.add(Box.createVerticalStrut(20));
		dialogPanel.add(buttonPanel);   
		dialog.add(dialogPanel); 

		dialog.setSize((int)(frame.getSize().width / 1.25), 200 + ((messages.size() - 1) * 40));
   	    dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
	}

	public JPanel buildMap(Game game) {
		int map_size = game.getMapSize();
		JPanel gridPanel = new JPanel(new GridLayout(map_size, map_size));
		gridPanel.setBackground(Color.DARK_GRAY);

		for (int y = 0; y < map_size; y++) {
			for (int x = 0; x < map_size; x++) {
				JLabel label = new JLabel(" ", SwingConstants.CENTER);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				label.setFont(new Font("Serif", Font.BOLD, 28));
				label.setForeground(Color.WHITE);
				if (game.getCurrentPosition().matchCoords(x, y) == true)
					label.setText("X");
				gridPanel.add(label);
			}
		}

		JPanel bigPanel = new JPanel(new BorderLayout());
		bigPanel.add(gridPanel, BorderLayout.CENTER);
		bigPanel.setName("map");
		return bigPanel;
	}

	public void updateMap(JPanel c, Game game, GameMove dir) {
		Component comp = c.getComponent(0);
		JPanel gridPanel = (JPanel) comp;
		int map_size = game.getMapSize();
		int curr_pos = (map_size * game.getCurrentPosition().getY()) + game.getCurrentPosition().getX();
		JLabel label = (JLabel) gridPanel.getComponent(curr_pos);
		label.setText(" ");
		game.getCurrentPosition().updateCoords(dir);
		curr_pos = (map_size * game.getCurrentPosition().getY()) + game.getCurrentPosition().getX();
		JLabel label2 = (JLabel) gridPanel.getComponent(curr_pos);
		label2.setText("X");
		gridPanel.revalidate();
		gridPanel.repaint();
	}

	public void getResultPopUp(JFrame frame, String msg, String title, String button_txt) {
		JDialog dialog = new JDialog(frame, title, true);
		
		JPanel dialogPanel= new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
		JLabel label = new JLabel(msg, SwingConstants.CENTER);
		label.setFont(new Font("SansSerif", Font.BOLD, 16));
		label.setForeground(Color.BLACK);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button = new JButton(button_txt);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.addActionListener( new ActionListener() {
  			public void actionPerformed(ActionEvent e) { 
				Controller.getGame().setAsFinished();
    			dialog.setVisible(false);
				Controller.removeGame();
  			}
		});
		buttonPanel.add(button);

        dialogPanel.add(Box.createVerticalStrut(20));
       	dialogPanel.add(label);
		dialogPanel.add(Box.createVerticalStrut(20));
		dialogPanel.add(buttonPanel);   

		dialog.add(dialogPanel); 
		dialog.setSize((int)(frame.getSize().width / 1.25), 200);
   	    dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
	}

	public void getExitPopUp(JFrame frame) {
		JDialog dialog = new JDialog(frame, "CAUTION", true);
		
		JPanel dialogPanel= new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
		JLabel label1 = new JLabel("Are you sure you wanna exit?", SwingConstants.CENTER);
		label1.setFont(new Font("SansSerif", Font.BOLD, 16));
		label1.setForeground(Color.BLACK);
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel label2 = new JLabel("All the progress will be lost", SwingConstants.CENTER);
		label2.setFont(new Font("SansSerif", Font.BOLD, 16));
		label2.setForeground(Color.BLACK);
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel label3 = new JLabel("or", SwingConstants.CENTER);
		label3.setFont(new Font("SansSerif", Font.BOLD, 16));
		label3.setForeground(Color.BLACK);
		label3.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton acceptButton = new JButton("GO TO MENU");
		acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		acceptButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getGame().setAsFinished();
				dialog.setVisible(false);
				Controller.removeGame();
			}
		}); 
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancelButton.addActionListener( e -> dialog.setVisible(false));
		buttonPanel.add(acceptButton);
		buttonPanel.add(cancelButton);
	
        dialogPanel.add(Box.createVerticalStrut(20));
       	dialogPanel.add(label1);
		dialogPanel.add(Box.createVerticalStrut(20));
		dialogPanel.add(label2);
		dialogPanel.add(Box.createVerticalStrut(20));
		dialogPanel.add(buttonPanel);
		dialogPanel.add(Box.createVerticalStrut(10));
		dialogPanel.add(label3);
		dialogPanel.add(Box.createVerticalStrut(10));
		JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton changeViewButton = new JButton("CHANGE TO CONSOLE");
		changeViewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		changeViewButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				Controller.getGUI().changeView();
			}
		}); 
		buttonPanel2.add(changeViewButton);
		dialogPanel.add(buttonPanel2);


		dialog.add(dialogPanel); 
		dialog.setSize((int)(frame.getSize().width / 2), 320);
   	    dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
	}

	public void getArtifactPopUp(Artifact curr_item, Artifact new_item, JFrame frame) {
		JDialog dialog = new JDialog(frame, "ARTIFACT FOUND", true);
		
		JPanel dialogPanel= new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
		dialogPanel.add(Box.createVerticalStrut(10));
		JLabel label1 = new JLabel(new_item.hasBeenDropped(), SwingConstants.CENTER);
		label1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label1.setForeground(Color.BLACK);
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		dialogPanel.add(label1);
		dialogPanel.add(Box.createVerticalStrut(20));
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton acceptButton = new JButton("YES!");
		acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		acceptButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				Controller.getGUI().showArtifactAttached(new_item);
			}
		});
		buttonPanel.add(acceptButton);
		if (curr_item != null) {
			JLabel label2 = new JLabel("Do you want to change your " + curr_item.getType() + " of rarity " + curr_item.getRarity() + "? (y/n) ",
								SwingConstants.CENTER);
			label2.setFont(new Font("SansSerif", Font.PLAIN, 16));
			label2.setForeground(Color.BLACK);
			label2.setAlignmentX(Component.CENTER_ALIGNMENT);
			dialogPanel.add(label2);
			dialogPanel.add(Box.createVerticalStrut(20));
			JButton cancelButton = new JButton("NO");
			cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			cancelButton.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dialog.setVisible(false);
					new_item.setAsNotAttachable();
				}
			});
			buttonPanel.add(cancelButton);
		}
		dialogPanel.add(buttonPanel);
		dialogPanel.add(Box.createVerticalStrut(10));
		dialog.add(dialogPanel);
		dialog.setSize((int)(frame.getSize().width / 1.2), 230);
   	    dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
	}
}