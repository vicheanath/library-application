package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Util {
	public static boolean isInRangeAtoZ(char c) {
		return (int)'A' <= (int)c && (int)c <= (int)'Z';
	}
	public static boolean isInRangeatoz(char c) {
		return (int)'a' <= (int)c && (int)c <= (int)'z';
	}



	public static void textInputStyle(JTextField textField) {

		textField.setForeground(LColor.PRIMARY_COLOR);
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, LColor.PRIMARY_COLOR));
//		add padding
		textField.setMargin(new Insets(5, 5, 5, 0));
		textField.setOpaque(true);
		textField.setBackground(LColor.BACKGROUND_COLOR);
		textField.setCaretColor(LColor.PRIMARY_COLOR);
		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, LColor.PRIMARY_COLOR));
			}
			@Override
			public void focusLost(FocusEvent e) {
				textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, LColor.ACCENT_COLOR_DARK));
			}
		});

	}

	public  static void buttonStyle(JButton button){
		button.setForeground(LColor.PRIMARY_COLOR);
		button.setFont(new Font("Arial", Font.PLAIN, 12));
		button.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, LColor.PRIMARY_COLOR));
		button.setBackground(LColor.BACKGROUND_COLOR);
		button.setForeground(LColor.PRIMARY_COLOR);
		button.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				button.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, LColor.PRIMARY_COLOR));
			}
			@Override
			public void focusLost(FocusEvent e) {
				button.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, LColor.ACCENT_COLOR_DARK));
			}
		});
	}
}
