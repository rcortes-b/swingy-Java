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
				button.addActionListener(e -> Controller.getController().getGUI().loadMenu());
				//button.addActionListener(e -> System.out.println("Create a hero from the menu still to develop"));
				break ;
			case "Change to Console Mode":
				button.addActionListener(e -> Controller.getController().getGUI().changeViewMode());
				break ;
			case "Exit":
				button.addActionListener(e -> Controller.getController().cleanup(0));
		}
		return button;
	}
}