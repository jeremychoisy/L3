package training;

import java.awt.Dimension;

import javax.swing.*;

public class Frame {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame();
		
		myFrame.setSize(new Dimension(350,350));
		myFrame.setVisible(true);
		
		myFrame.getContentPane().add(new Component());
	}

}
