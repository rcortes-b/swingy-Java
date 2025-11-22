package me.rcortesb.swingy.views.gui;
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
				button.addActionListener(e -> Controller.getController().getGUI().loadGameMenu());
				break ;
			case "Create a Hero":
				button.addActionListener(e -> Controller.getController().getGUI().loadHeroMenu());
				break ;
			case "Change to Console Mode":
				button.addActionListener(e -> Controller.getController().getGUI().changeView());
				break ;
			case "Exit":
				button.addActionListener(e -> Controller.getController().cleanupResources(0));
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
				button.addActionListener(e -> Controller.getController().getGUI().createHero());
				break ;
			case "List Heroes":
				button.addActionListener(e -> Controller.getController().getGUI().listHeroes());
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