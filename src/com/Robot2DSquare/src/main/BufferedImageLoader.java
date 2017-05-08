package com.Robot2DSquare.src.main;

import java.awt.image.*;
import java.io.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	private BufferedImage image;
	private static final String IMG_PATH = "C:/Users/sampa/Desktop/Workspace/Room/res/images/robot.jpg";
	
	public BufferedImageLoader() {
		// TODO Auto-generated constructor stub
	}
	
	public BufferedImage loadImage() throws IOException {
		image = ImageIO.read(new File(IMG_PATH));
		return image;
	}

}
