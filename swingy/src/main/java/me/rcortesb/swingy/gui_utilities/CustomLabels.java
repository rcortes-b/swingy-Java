package me.rcortesb.swingy.gui_utilities;
import javax.swing.*;
import java.awt.*;

public class CustomLabels {
	public JLabel getSocialMenuLabels(String labelText) {
		JLabel label = new JLabel(labelText);
		label.setFont(new Font("Serif", Font.BOLD, 14));
		label.setForeground(Color.YELLOW);
    	label.setAlignmentX(Component.CENTER_ALIGNMENT);
		return label;
	}
}