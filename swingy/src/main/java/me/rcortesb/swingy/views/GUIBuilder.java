package me.rcortesb.swingy.views;
import me.rcortesb.swingy.gui_utilities.*;
import me.rcortesb.swingy.controller.*;
import me.rcortesb.swingy.models.GameModel;
import me.rcortesb.swingy.models.Hero;
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
		int col_size = 5;
		boolean inGame = false;
		List<JButton> selectButtons = new ArrayList<>();

		if (Controller.getStatus() == GameStatus.IN_GAME_MENU) {
			inGame = true;
			col_size++;
		}

		/* GridLayout Listing*/
		JPanel gridPanel = new JPanel(new GridLayout(0, col_size));
		gridPanel.setBackground(Color.DARK_GRAY);

		gridPanel.add(createLabel("NAME", true, 28, Color.white));
		gridPanel.add(createLabel("CLASS", true, 28, Color.white));
		gridPanel.add(createLabel("ATTACK", true, 28, Color.white));
		gridPanel.add(createLabel("DEFENSE", true, 28, Color.white));
		gridPanel.add(createLabel("HP", true, 28, Color.white));
		if (inGame == true)
			gridPanel.add(createLabel("", true, 28, Color.white));
		
		for (int row = 0; row < row_size; row++) {
			Hero hero = heroes.get(row);
			gridPanel.add(createLabel(hero.getName(), false, 16, Color.white));
			gridPanel.add(createLabel(hero.getClassType(), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getAttack()), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getDefense()), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getHP()), false, 16, Color.white));
			if (inGame == true) {
				JButton selectButton = new JButton("Select");
				selectButton.addActionListener(e -> Controller.startGame(hero));
				gridPanel.add(selectButton);
			}
		}

		/* Button of "Ok" --> redirects to HeroMenu */
		JButton button = new JButton("OK");
		if (inGame == true)
			button.setText("CANCEL");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setFocusPainted(false);
		button.setFont(new Font("SansSerif", Font.PLAIN, 18));
		/* If inGame is true ... */
		button.addActionListener(e -> Controller.getGUI().setView());

		/* Vertical ScrollBar linked to the GridLayout */
		JScrollPane scrollPane = new JScrollPane(gridPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();

		JPanel bigPanel = new JPanel(new BorderLayout());
		bigPanel.add(scrollPane, BorderLayout.CENTER);
		bigPanel.add(button, BorderLayout.SOUTH);
		if (inGame == true)
			bigPanel.setName("heroSelect");
		else
			bigPanel.setName("heroListing");
		return bigPanel;	
	}

	public void updateList(JPanel c) {
		List<Hero> heroes = Controller.getGameModel().getHeroes();
		JScrollPane scrollPane = (JScrollPane) c.getComponent(0);
		JPanel gridPanel = (JPanel) scrollPane.getViewport().getView();
		boolean isSelect = false;
		if (Controller.getStatus() == GameStatus.IN_GAME_MENU)
			isSelect = true;
		int row_size = 5 + (isSelect ? 1 : 0);
		/* Checks if the list size is the same than the number of heroes that are already in the list */
		int sizeGap = heroes.size() - ((gridPanel.getComponentCount() / row_size) - 1);
		if (sizeGap == 0)
			return;
		System.out.println("Here arrives");
		/* If the size differes, get the index of heroes who are not present in the list (recently created) */
		sizeGap = heroes.size() - sizeGap;

		while (sizeGap < heroes.size()) {
			Hero hero = heroes.get(sizeGap);
			gridPanel.add(createLabel(hero.getName(), false, 16, Color.white));
			gridPanel.add(createLabel(hero.getClassType(), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getAttack()), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getDefense()), false, 16, Color.white));
			gridPanel.add(createLabel(String.valueOf(hero.getHP()), false, 16, Color.white));
			if (isSelect == true) {
				JButton selectButton = new JButton("Select");
				selectButton.addActionListener(e -> System.out.println("Hero NAME: " + hero.getName()));
				gridPanel.add(selectButton);
			}
			sizeGap++;
		}
		gridPanel.revalidate();
		gridPanel.repaint();
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
		String[] labelGrid = {"NAME", "CLASS", "ATTACK", "DEFENSE", "HP"};
		JTextField[] fieldValues = new JTextField[5];
		JLabel titleLabel = new JLabel("CUSTOMIZE YOUR OWN HERO!");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		titleLabel.setForeground(Color.white);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


		JPanel gridPanel = new JPanel(new GridLayout(5, 2));
	
		for (int i = 0; i < 5; i++) {
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
		String[] value = new String[5];
		List<String> error_log = new ArrayList<>();
		int i = 0;
		for (; i < 5; i++)
			value[i] = txtFields[i].getText();
		i = 0;
		if (Controller.getGameModel().heroExists(value[i]) == true)
			error_log.add("Error: A hero with name " + value[i] + " already exists.");
		i = 2;
		for(; i < 5; i++) {
			if (Controller.getGameModel().validateHeroInput(value[i], i) == false)
				value[i] = "0";
		}
		Hero new_hero = new Hero(value[0], value[1], 1, 0, Integer.parseInt(value[2]),
									Integer.parseInt(value[3]), Integer.parseInt(value[4]));
		if (Controller.getGameModel().isHeroValid(new_hero, error_log));
		if (error_log.size() == 0) {
			Controller.getController().addHero(new_hero);
			if (Controller.getStatus() == GameStatus.IN_HERO_MENU)
					Controller.getGUI().loadHeroMenu();
			else {
				Controller.startGame(new_hero); //
				System.out.println("HERE I START GAME WITH THE CREATED HERO");
			}
		}
		else
			showErrorPopUp(error_log, frame);
	}

	private void showErrorPopUp(List<String> error_log, JFrame frame) {
		JDialog dialog = new JDialog(frame, "ERROR LOG", true);
		
		JPanel dialogPanel= new JPanel();
		dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
		for (String err_msg : error_log) {
			JLabel label = createLabel("- " + err_msg, false, 16, Color.black);
			label.setBorder(null);
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

		/* Size is adaptable to the amount of message errors. 200 height to one error + additional 40 for each error */
		dialog.setSize((int)(frame.getSize().width / 1.5), 200 + ((error_log.size() - 1) * 40));
   	    dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
	}

	//public JPanel buildGame() {
		//Controller.setGame(); // build map, generate villains

		
	//}
}