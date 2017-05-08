package com.Robot2DSquare.src.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.*;

public class robot {
	
	private double x;
	private double y;
	private int radians;
	
	private boolean rotate = false;
	
	private BufferedImage img;
	AffineTransform at;

	public robot(double x, double y, SqRobot2D sqrobot2d) {
		this.x = x;
		this.y = y;
		
		img = sqrobot2d.getImg();
	}
	
	public void tick() {
		//x++;
	}
	/*
	public void rotateImage(String prevVector, String vector) {
		at = new AffineTransform();
        // 4. translate it to the center of the component
        at.translate(img.getWidth() / 2, img.getHeight() / 2);
        // 3. do the actual rotation
        at.rotate(Math.PI/4);
        // 2. just a scale because this image is big
        //at.scale(0.5, 0.5);

        // 1. translate the object so that you rotate it around the 
        //    center (easier :))
        at.translate(-img.getWidth()/2, -img.getHeight()/2);
        rotate = true;
	}*/
	
	public void rotateImage(String prevVector, String vector, int key) {
		 at = new AffineTransform();

		 if (prevVector.equals(vector)) {
			 radians = 0;
		 } else if (key == KeyEvent.VK_L){
			 radians = -90;
		 } else {
			 radians = 90;
		 }
		 
		 at.rotate(Math.toRadians(radians), img.getWidth()/2, img.getHeight()/2);
		 AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		 img = op.filter(img, null);

		 //rotate = prevVector.equals(vector);
	}
	
	public void render(Graphics g) {
		//if(!rotate) {
			g.drawImage(img, (int)x, (int)y, null);
		/*} else {
			Graphics2D g2d = (Graphics2D) g;
	        g2d.drawImage(img, at, null);
		}*/
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}

}