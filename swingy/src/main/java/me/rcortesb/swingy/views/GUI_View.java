package me.rcortesb.swingy.views;
import me.rcortesb.swingy.gui_utilities.CustomButtons;
import me.rcortesb.swingy.gui_utilities.CustomLabels;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI_View extends View {
	private JFrame frame;

	public GUI_View() {
		this.frame = new JFrame("Achieve The Border");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		screen.width /= 2;
		screen.height /= 2;
		frame.setPreferredSize(screen);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {
	}
	public String getView() {
		return "gui_view";
	}

	public void launchApp() {
		this.loadMenu();
	}
	public void loadMenu() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		CustomButtons buttons = new CustomButtons();
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
		//frame.add(panel, BorderLayout.CENTER);
		//frame.add(menuBanner(), BorderLayout.SOUTH);
		JPanel bigPanel = new JPanel(new BorderLayout());
		bigPanel.add(panel, BorderLayout.CENTER);
		bigPanel.add(menuBanner(), BorderLayout.SOUTH);
		frame.add(bigPanel);

		this.setWindowVisible();
	}

	public void loadMenu2() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		CustomButtons buttons = new CustomButtons();
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
		frame.add(panel, BorderLayout.CENTER);
		frame.add(menuBanner(), BorderLayout.SOUTH);

		this.setWindowVisible();
	}

	public JPanel menuBanner() {
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
	public void displayGameOptions() {

	}
	public void deleteUI() {
		frame.dispose();
	}
	private void setWindowVisible() {
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}