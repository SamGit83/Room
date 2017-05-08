package com.Robot2DSquare.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	SqRobot2D robot;
	
	public KeyInput(SqRobot2D robot) {
		// TODO Auto-generated constructor stub
		this.robot = robot;
	}
	
	public void keyPressed(KeyEvent e){
		robot.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		robot.keyReleased(e);
	}

}