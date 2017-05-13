package iain.model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;

import cs355.model.drawing.Circle;
import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Line;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Square;
import cs355.model.drawing.Triangle;

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
	
	public int[] getLine(Line shape) {
		int[] values = new int[4];
		Point2D.Double start = shape.getCenter();
		Point2D.Double end = shape.getEnd();
		values[0] = (int) start.x;
		values[1] = (int) start.y;
		values[2] = (int) end.x;
		values[3] = (int) end.y;
		return values;
	}
	
	public int[] getRectangle(Rectangle shape) {
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
	
	public int[] getSquare(Square shape) {
		int[] values = new int[4];
		Point2D.Double center = shape.getCenter();
		double size = shape.getSize();
		double x = center.getX() - size/2;
		double y = center.getY() - size/2;
		values[0] = (int) x;
		values[1] = (int) y;
		values[2] = (int) size;
		values[3] = (int) size;
		return values;
	}
	
	public int[] getTriangleX(Triangle shape) {
		int[] points = new int[Model.TOTAL_TRIANGLE_POINTS];
		Point2D.Double center = shape.getCenter();
		points[0] = (int) (shape.getA().getX() + center.getX());
		points[1] = (int) (shape.getB().getX() + center.getX());
		points[2] = (int) (shape.getC().getX() + center.getX());
		return points;
	}
	
	public int[] getTriangleY(Triangle shape) {
		int[] points = new int[Model.TOTAL_TRIANGLE_POINTS];
		Point2D.Double center = shape.getCenter();
		points[0] = (int) (shape.getA().getY() + center.getY());
		points[1] = (int) (shape.getB().getY() + center.getY());
		points[2] = (int) (shape.getC().getY() + center.getY());
		return points;
	}
	
	public Triangle setTriangle(Color currentColor, List<Point2D.Double> trianglePoints) {
		double x = trianglePoints.get(0).x + trianglePoints.get(1).x + trianglePoints.get(2).x;
		x = x / Model.TOTAL_TRIANGLE_POINTS;
		double y = trianglePoints.get(0).y + trianglePoints.get(1).y + trianglePoints.get(2).y;
		y = y / Model.TOTAL_TRIANGLE_POINTS;
		Point2D.Double center = new Point2D.Double(x, y);
		
		x = trianglePoints.get(0).getX() - center.getX();
		y = trianglePoints.get(0).getY() - center.getY();
		Point2D.Double one = new Point2D.Double(x, y);
		
		x = trianglePoints.get(1).getX() - center.getX();
		y = trianglePoints.get(1).getY() - center.getY();
		Point2D.Double two = new Point2D.Double(x, y);
		
		x = trianglePoints.get(2).getX() - center.getX();
		y = trianglePoints.get(2).getY() - center.getY();
		Point2D.Double three = new Point2D.Double(x, y);
		
		return new Triangle(currentColor, center, one, two, three);
	}

}
