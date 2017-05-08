package com.Robot2DSquare.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.io.*;

import javax.swing.JFrame;

public class SqRobot2D extends Canvas implements Runnable, Room {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 290;
	public static final int HEIGHT = 283;//WIDTH/12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Robot 2D Square";

	private boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage robot = null;
	
	private robot r;
	private Point currLoc;
	private String vector;
	private String prevVector;
	
	public SqRobot2D(String s) throws IOException {
		BufferedImageLoader loader = new BufferedImageLoader();

		try {
			robot = loader.loadImage();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		
		currLoc = getStartPosition();
		r = new robot(currLoc.x, currLoc.y, this);
	}
		
	private synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		final double ticks = 60.0;
		double ns = 1000000000 / ticks;
		double delta = 0;
		
		int tick_update = 0, frames = 0;
		long timer = System.currentTimeMillis();
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				tick_update++;
				delta-- ;
			}
			render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(tick_update + " Ticks, FPS " + frames);
				
				tick_update = 0;
				frames = 0;
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if ((key == KeyEvent.VK_F) || (key == KeyEvent.VK_G)) {
			if(((r.getY() == 0) && (vector.equals("N"))) || ((r.getY() == 500) && (vector.equals("S"))) || ((r.getX() == 0) && (vector.equals("W"))) || ((r.getX() == 500) && (vector.equals("E")))) {
				System.out.println("Not in Room size Limits, Do nothing!");
				return;
			} else {
				if(vector.equals("N")) {
					r.setY(r.getY() - 100);
					currLoc.y = (int) r.getY();
					vector = "N";
					System.out.println("Key Pressed " + (currLoc.x/100) + " " + ((500 - currLoc.y)/100) + " " + vector);
				}else if (vector.equals("S")) {
					r.setY(r.getY() + 100);
					currLoc.y = (int) r.getY();
					vector = "S";
					System.out.println("Key Pressed " + (currLoc.x/100) + " " + ((500 - currLoc.y)/100) + " " + vector);
				}else if (vector.equals("E")) {
					r.setX(r.getX() + 100);
					currLoc.x = (int) r.getX();
					vector = "E";
					System.out.println("Key Pressed " + (currLoc.x/100) + " " + ((500 - currLoc.y)/100) + " " + vector);
				}else if (vector.equals("W")) {
					r.setX(r.getX() - 100);
					currLoc.x = (int) r.getX();
					vector = "W";
					System.out.println("Key Pressed " + (currLoc.x/100) + " " + ((500 - currLoc.y)/100) + " " + vector);
				}
			}
		} else if ((key == KeyEvent.VK_D) || (key == KeyEvent.VK_N)) {
			if(((r.getY() == 0) && (vector.equals("N"))) || ((r.getY() == 500) && (vector.equals("S"))) || ((r.getX() == 0) && (vector.equals("W"))) || ((r.getX() == 500) && (vector.equals("E")))) {
					System.out.println("Not in Room size Limits, Do nothing!");
					return;
				} else {
					r.setY(r.getY() + 100);
					currLoc.y = (int) r.getY();
					System.out.println("Key Pressed " + currLoc.getLocation());
				}
		} else if ((key == KeyEvent.VK_L) || (key == KeyEvent.VK_V)) {
			if(((r.getY() == 0) && (vector.equals("E"))) || ((r.getY() == 500) && (vector.equals("W"))) || ((r.getX() == 0) && (vector.equals("N"))) || ((r.getX() == 500) && (vector.equals("S")))) {
				System.out.println("Not in Room size Limits, Do nothing!");
				return;
			} else {
				if(vector.equals("N")) {
					r.setX(r.getX() - 100);
					currLoc.x = (int) r.getX();
					prevVector = "N";
					vector = "W";
					System.out.println("Key Pressed " + (currLoc.x/100) + " " + ((500 - currLoc.y)/100) + " " + vector);
				} else if (vector.equals("S")) {
					r.setX(r.getX() + 100);
					currLoc.x = (int) r.getX();
					prevVector = "S";
					vector = "E";
					System.out.println("Key Pressed " + (currLoc.x/100) + " " + ((500 - currLoc.y)/100) + " " + vector);
				} else if (vector.equals("E")) {
					r.setY(r.getY() - 100);
					currLoc.y = (int) r.getY();
					prevVector = "E";
					vector = "N";
					System.out.println("Key Pressed " + (currLoc.x/100) + " " + ((500 - currLoc.y)/100) + " " + vector);
				} else if (vector.equals("W")) {
					r.setY(r.getY() + 100);
					currLoc.y = (int) r.getY();
					prevVector = "W";
					vector = "S";
					System.out.println("Key Pressed " + (currLoc.x/100) + " " + ((500 - currLoc.y)/100) + " " + vector);
				}
			}
			r.rotateImage(prevVector, vector, key);
		} else if ((key == KeyEvent.VK_R) || (key == KeyEvent.VK_H)) {
			System.out.println("Key Pressed " + currLoc.x + " " + currLoc.y + " " + vector);
			if(((r.getY() == 500) && (vector.equals("E"))) || ((r.getY() == 0) && (vector.equals("W"))) || ((r.getX() == 0) && (vector.equals("S"))) || ((r.getX() == 500) && (vector.equals("N")))) {
				System.out.println("Not in Room size Limits, Do nothing!");
				return;
			} else {
				if (vector.equals("N")) {
					r.setX(r.getX() + 100);
					currLoc.x = (int) r.getX();
					prevVector = "N";
					vector = "E";
					System.out.println("Key Pressed " + (currLoc.x/100) + " " + ((500 - currLoc.y)/100) + " " + vector);
				} else if (vector.equals("S")) {
					r.setX(r.getX() - 100);
					currLoc.x = (int) r.getX();
					prevVector = "S";
					vector = "W";
					System.out.println("Key Pressed " + (currLoc.x/100) + " " + ((500 - currLoc.y)/100) + " " + vector);
				}  else if (vector.equals("E")) {
					r.setY(r.getY() + 100);
					currLoc.y = (int) r.getY();
					prevVector = "E";
					vector = "S";
					System.out.println("Key Pressed " + (currLoc.x/100) + " " + ((500 - currLoc.y)/100) + " " + vector);
				}  else if (vector.equals("W")) {
					r.setY(r.getY() - 100);
					currLoc.y = (int) r.getY();
					prevVector = "W";
					vector = "N";
					System.out.println("Key Pressed " + (currLoc.x/100) + " " + ((500 - currLoc.y)/100) + " " + vector);
				}
			}
			r.rotateImage(prevVector, vector, key);
		} else {
			keyTyped(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == 27) {
			System.exit(0);
		}
	}

	private void tick() {
		r.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if(bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		r.render(g);
		g.dispose();
		bs.show();
	}

	public static void main(String args[]) throws IOException{
		SqRobot2D r2Dsq = new SqRobot2D("Robot 2D");
		
		r2Dsq.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		r2Dsq.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		r2Dsq.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(r2Dsq.TITLE);
		frame.add(r2Dsq);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		r2Dsq.start();
	}	

	public BufferedImage getImg() {
		return robot;
	}

	public Point getStartPosition() {
		// TODO Auto-generated method stub
		Point p = new Point();
		p.x = 100;
		p.y = 300;
		
		vector = "N";
		prevVector = "N";
		
		return p;
	}

	@Override
	public boolean contains(Point position) {
		// TODO Auto-generated method stub
		return false;
	}
}