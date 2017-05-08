package com.Robot2DSquare.src.main;
import java.awt.Point;

public interface Room {
	Point getStartPosition();
	boolean contains(Point position);
}