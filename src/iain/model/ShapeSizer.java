package iain.model;

import java.awt.geom.Point2D;

import cs355.model.drawing.*;
import iain.model.Model;

public class ShapeSizer {
	
	private static ShapeSizer shapeSizer;
	
	public static ShapeSizer inst() {
		if (shapeSizer == null) {
			shapeSizer = new ShapeSizer();
		}
		return shapeSizer;
	}
	
	private ShapeSizer() { // empty constructor
	}
	
	public Shape setCircle(Circle shape, Point2D.Double start, Point2D.Double end) {
		if (start == null) {
			throw new IllegalArgumentException("the start point is null!");
		}if (end == null) {
			throw new IllegalArgumentException("the end point is null!");
		}
		double width = start.getX() - end.getX();
		if (width < 0) {
			width *= -1;
		}
		double height = start.getY() - end.getY();
		if (height < 0) {
			height *= -1;
		}
		double radius = 0;
		double x = 0, y = 0;
		// check to see which one is smaller and make the radius the same size as that one
		// then set the center a radius length away from the start point
		if (height < width) { 
			radius =  height / 2;
			y = (start.getY() + end.getY()) / 2;
			if (start.getX() < end.getX()) {
				x = start.getX() + radius;
			}else {
				x = start.getX() - radius;
			}
		}else {
			radius =  width / 2;
			x = (start.getX() + end.getX()) / 2;
			if (start.getY() < end.getY()) {
				y = start.getY() + radius;
			}else {
				y = start.getY() - radius;
			}
		}
		Point2D.Double center = new Point2D.Double(x, y);
		shape.setRadius(radius);
		shape.setCenter(center);
		return shape;
	}
	
	public int[] getCircle(Circle shape) {
		int[] values = new int[4];
		Point2D.Double center = shape.getCenter();
		double radius = shape.getRadius();
		double x = center.getX() - radius;
		double y = center.getY() - radius;
		values[0] = (int) x;
		values[1] = (int) y;
		values[2] = (int) (radius * 2);
		values[3] = values[2];
		return values;
	}
	
	public Shape setEllipse(Ellipse shape, Point2D.Double start, Point2D.Double end) {
		if (start == null) {
			throw new IllegalArgumentException("the start point is null!");
		}if (end == null) {
			throw new IllegalArgumentException("the end point is null!");
		}
		double width = 0, height = 0;
		width = start.getX() - end.getX();
		height = start.getY() - end.getY();
		if (width < 0) width *= -1;
		if (height < 0) height *= -1;
		double x = 0, y = 0;
//		the x coordinate for the center is the leftmost x coordinate plus the width / 2
		if (start.getX() < end.getX()) {
			x = start.getX() + (width/2);
		}else {
			x = end.getX() + (width/2);
		}
//		the y coordinate for the center is the higher point plus the height / 2
		if (start.getY() < end.getY()) {
			y = start.getY() + (height/2);
		}else {
			y = end.getY() + (height/2);
		}
		Point2D.Double point = new Point2D.Double(x, y);
		shape.setCenter(point);
		shape.setHeight(height);
		shape.setWidth(width);
		return shape;
	}
	
	public int[] getEllipse(Ellipse shape) {
		int[] values = new int[4];
		Point2D.Double center = shape.getCenter();
		double width = shape.getWidth(), height = shape.getHeight();
		double x = center.getX() - (width/2);
		double y = center.getY() - (height/2);
		values[0] = (int) x;
		values[1] = (int) y;
		values[2] = (int) width;
		values[3] = (int) height;
		return values;
	}
	
	public Shape setRectangle(Rectangle shape, Point2D.Double start, Point2D.Double end) {
		if (start == null) {
			throw new IllegalArgumentException("the start point is null!");
		}if (end == null) {
			throw new IllegalArgumentException("the end point is null!");
		}
		double width = 0, height = 0;
		width = start.getX() - end.getX();
		height = start.getY() - end.getY();
		if (width < 0) width *= -1;
		if (height < 0) height *= -1;
		double x = 0, y = 0;
		if (start.getX() < end.getX()) {
			x = start.getX();
		}else {
			x = end.getX();
		}
		if (start.getY() < end.getY()) {
			y = start.getY();
		}else {
			y = end.getY();
		}
		Point2D.Double point = new Point2D.Double(x, y);
		shape.setUpperLeft(point);
		shape.setHeight(height);
		shape.setWidth(width);
		return shape;
	}
	
	public int[] getRectangle(Rectangle shape) {
		int[] values = new int[4];
		Point2D.Double point = shape.getUpperLeft();
		values[0] = (int) point.getX();
		values[1] = (int) point.getY();
		values[2] = (int) shape.getWidth();
		values[3] = (int) shape.getHeight();
		return values;
	}
	
	public Shape setSquare(Square shape, Point2D.Double start, Point2D.Double end) {
		if (start == null) {
			throw new IllegalArgumentException("the start point is null!");
		}if (end == null) {
			throw new IllegalArgumentException("the end point is null!");
		}
		double width = start.getX() - end.getX();
		double height = start.getY() - end.getY();
		if (width < 0) width *= -1;
		if (height < 0) height *= -1;
		double size = 0;
		if (width < height) {
			size = width;
		}else {
			size = height;
		}
		double x = 0, y = 0;
		if (start.getX() < end.getX()) {
			x = start.getX();
		}else {
			x = start.getX() - size;
		}
		if (start.getY() < end.getY()) {
			y = start.getY();
		}else {
			y = start.getY() - size;
		}
		Point2D.Double upperLeft = new Point2D.Double(x, y);
		shape.setUpperLeft(upperLeft);
		shape.setSize(size);
		return shape;
	}
	
	public int[] getSquare(Square shape) {
		int[] values = new int[4];
		Point2D.Double point = shape.getUpperLeft();
		values[0] = (int) point.getX();
		values[1] = (int) point.getY();
		values[2] = (int) shape.getSize();
		values[3] = (int) shape.getSize();
		return values;
	}
	
	public int[] getTriangleX(Triangle shape) {
		int[] points = new int[Model.TRIANGLE_POINTS];
		points[0] = (int) shape.getA().getX();
		points[1] = (int) shape.getB().getX();
		points[2] = (int) shape.getC().getX();
		return points;
	}
	
	public int[] getTriangleY(Triangle shape) {
		int[] points = new int[Model.TRIANGLE_POINTS];
		points[0] = (int) shape.getA().getY();
		points[1] = (int) shape.getB().getY();
		points[2] = (int) shape.getC().getY();
		return points;
	}

}
