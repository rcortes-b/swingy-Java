package me.rcortesb.swingy.gui_utilities;
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
				button.addActionListener(e -> System.out.println("Create a hero from the menu still to develop"));
				break ;
			case "Tutorial":
				button.addActionListener(e -> System.out.println("Tutorial from the menu still to develop"));
				break ;
			case "Exit":
				button.addActionListener(e -> System.exit(0));
		}
		return button;
	}
}