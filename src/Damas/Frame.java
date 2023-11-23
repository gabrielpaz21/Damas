package Damas;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	public Frame() {

		setBounds(200, 100, 515, 515);
		setTitle("Checkers");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		Toolkit t = Toolkit.getDefaultToolkit();
		Image icon = t.getImage("Lighthouse.jpg"); //TODO modify top icon
		setIconImage(icon);
		Panel panel = new Panel();
		add(panel);
		setVisible(true);
	}

}

