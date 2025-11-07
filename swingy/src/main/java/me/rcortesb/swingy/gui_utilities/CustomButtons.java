package me.rcortesb.swingy.gui_utilities;
import me.rcortesb.swingy.views.GUI_View;
import me.rcortesb.swingy.controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomButtons {
	public JButton getMenuButton(String buttonText) {
		JButton button = new JButton(buttonText);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setFocusPainted(false);
		button.setFont(new Font("SansSerif", Font.PLAIN, 18));
		switch (buttonText) {
			case "Start Game":
				button.addActionListener(e -> System.out.println("Start game from the menu still to develop"));
				break ;
			case "Create a Hero":
				button.addActionListener(e -> Controller.getController().getGUI().loadHeroMenu());
				//button.addActionListener(e -> System.out.println("Create a hero from the menu still to develop"));
				break ;
			case "Change to Console Mode":
				button.addActionListener(e -> Controller.getController().getGUI().changeView());
				break ;
			case "Exit":
				button.addActionListener(e -> Controller.getController().cleanup(0));
		}
		return button;
	}

	public JButton getHeroMenuButton(String buttonText) {
		JButton button = new JButton(buttonText);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setFocusPainted(false);
		button.setFont(new Font("SansSerif", Font.PLAIN, 18));
		switch (buttonText) {
			case "Create Hero":
				button.addActionListener(e -> System.out.println("Start game from the menu still to develop"));
				break ;
			case "List Heroes":
				button.addActionListener(e -> /*Controller.getController().getGUI().loadHeroManager()*/ System.out.println("List hero buttons"));
				//button.addActionListener(e -> System.out.println("Create a hero from the menu still to develop"));
				break ;
			case "Change to Console Mode":
				button.addActionListener(e -> Controller.getController().getGUI().changeView());
				break ;
			case "Back To Menu":
				button.addActionListener(e -> Controller.getController().getGUI().loadMenu());
		}
		return button;
	}
}