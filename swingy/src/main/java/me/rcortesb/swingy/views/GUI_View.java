package me.rcortesb.swingy.views;
import me.rcortesb.swingy.gui_utilities.CustomButtons;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI_View extends View {
	
	public void actionPerformed(ActionEvent e) {
	}
	public String getView() {
		return "gui_view";
	}
	
	public void displayMenu() {

	}
	public JFrame createScreen() {
		JFrame frame = new JFrame("Achieve The Border");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		screen.width /= 2;
		screen.height /= 2;
		frame.setPreferredSize(screen);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}
	public void launchApp() {
		JFrame frame = createScreen();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		CustomButtons buttons = new CustomButtons();
		JButton startGameButton = buttons.getMenuButton("Start Game");
		JButton createHeroButton = buttons.getMenuButton("Create a Hero");
		JButton tutorialButton = buttons.getMenuButton("Tutorial");
		JButton exitGameButton = buttons.getMenuButton("Exit");

		
		JLabel gameTitle = new JLabel("Welcome to Achieve the Border!");
		gameTitle.setFont(new Font("Serif", Font.BOLD, 28));
		gameTitle.setForeground(Color.WHITE);
    	gameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);	
	
		//asdasdasdasdsadasdasdasdasddasda
		panel.setBackground(Color.DARK_GRAY);
		panel.add(Box.createVerticalStrut(20));
		panel.add(gameTitle);
		panel.add(Box.createVerticalStrut(60));
		panel.add(startGameButton);
		panel.add(Box.createVerticalStrut(30));
		panel.add(createHeroButton);
		panel.add(Box.createVerticalStrut(30));
		panel.add(tutorialButton);
		panel.add(Box.createVerticalStrut(30));
		panel.add(exitGameButton);

		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public void displayGameOptions() {

	}
}