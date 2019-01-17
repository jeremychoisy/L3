package training;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class Component extends JComponent {
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.drawString("WTFFF",10, 10);
	}
}
