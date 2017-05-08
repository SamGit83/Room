package com.Robot2DSquare.src.main;

import java.awt.Color;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Robot2DSquare extends JFrame implements Room, KeyListener {
	Point currLoc;
	JLabel label;
	BufferStrategy strategy;
	JFrame frame = new JFrame("Square Room Robot");
	private static final String IMG_PATH = "C:/Users/sampa/Desktop/Workspace/Robot2D/src/images/robot.jpg";
    
	public Robot2DSquare(String s) throws IOException {
		// TODO Auto-generated constructor stub
		super(s);
		
		BufferedImage img = ImageIO.read(new File(IMG_PATH));
        ImageIcon icon = new ImageIcon(img);
        
		JPanel panel = (JPanel) frame.getContentPane();
		label = new JLabel(icon);

//        panel.setBounds(5, 5, 800, 800);
		currLoc = getStartPosition();
        panel.setBackground(Color.BLACK);
        panel.add(label);

		frame.pack();
        if(frame.contains(currLoc)) System.out.println("Position exists");
        frame.setVisible(true);
//        frame.addKeyListener(new keyInput(this));
        frame.setBackground(Color.BLACK);
        frame.setBounds(5, 5, 800, 800);
        
        //createBufferStrategy(2);
        strategy = getBufferStrategy();

        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.dispose();
        strategy.show();
        
        g.drawImage(img, currLoc.x, currLoc.y, null);
        img.getGraphics().drawImage(img, 1, 2, null);
        
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if ((key == KeyEvent.VK_F) || (key == KeyEvent.VK_G)) {
			currLoc.y++;
			//frame.add(label, currLoc.y);
			System.out.println("Key Pressed " + currLoc.getLocation());
		} else if ((key == KeyEvent.VK_L) || (key == KeyEvent.VK_V)) {
			currLoc.x--;
			System.out.println("Key Pressed " + currLoc.getLocation());
		} else if ((key == KeyEvent.VK_R) || (key == KeyEvent.VK_H)) {
			currLoc.x++;
			System.out.println("Key Pressed " + currLoc.getLocation());
		} else {
			keyTyped(e);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new Robot2DSquare ("Robot 2D");
	}

	@Override
	public Point getStartPosition() {
		// TODO Auto-generated method stub
		Point p = new Point();
		p.x = 1;
		p.y = 2;
		
		return p;
	}

	@Override
	public boolean contains(Point position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == 27) {
			System.exit(0);
		}
	}

}