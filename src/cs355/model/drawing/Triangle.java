package cs355.model.drawing;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

/**
 * Add your triangle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Triangle extends Shape {

	// The three points of the triangle.
	private Point2D.Double a;
	private Point2D.Double b;
	private Point2D.Double c;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param center the center of the new shape.
	 * @param a the first point, relative to the center.
	 * @param b the second point, relative to the center.
	 * @param c the third point, relative to the center.
	 */
	public Triangle(Color color, Point2D.Double center, Point2D.Double a,
					Point2D.Double b, Point2D.Double c)
	{

		// Initialize the superclass.
		super(color, center);
		this.type = Shape.SHAPE_TYPE.triangle;
		// Set fields.
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * Getter for the first point.
	 * @return the first point as a Java point.
	 */
	public Point2D.Double getA() {
		return a;
	}

	/**
	 * Setter for the first point.
	 * @param a the new first point.
	 */
	public void setA(Point2D.Double a) {
		this.a = a;
	}

	/**
	 * Getter for the second point.
	 * @return the second point as a Java point.
	 */
	public Point2D.Double getB() {
		return b;
	}

	/**
	 * Setter for the second point.
	 * @param b the new second point.
	 */
	public void setB(Point2D.Double b) {
		this.b = b;
	}

	/**
	 * Getter for the third point.
	 * @return the third point as a Java point.
	 */
	public Point2D.Double getC() {
		return c;
	}

	/**
	 * Setter for the third point.
	 * @param c the new third point.
	 */
	public void setC(Point2D.Double c) {
		this.c = c;
	}

	/**
	 * Add your code to do an intersection test
	 * here. You shouldn't need the tolerance.
	 * @param pt = the point to test against.
	 * @param tolerance = the allowable tolerance.
	 * @return true if pt is in the shape,
	 *		   false otherwise.
	 */
	@Override
	public boolean pointInShape(Point2D.Double point, double tolerance) {
		double x = point.x - getCenter().x;
		double y = point.y - getCenter().y;
		Point2D.Double pt = new Point2D.Double(x, y);
		double x1 = pt.x - a.x, y1 = pt.y - a.y;
		double x2 = b.x - a.x, y2 = b.y - a.y;
		double one = x1 * y2 * -1;
		double two = y1 * x2;
		double result1 = one + two;
		
		x1 = pt.x - b.x;
		y1 = pt.y - b.y;
		x2 = c.x - b.x;
		y2 = c.y - b.y;
		one = x1 * y2 * -1;
		two = y1 * x2;
		double result2 = one + two;
		
		x1 = pt.x - c.x;
		y1 = pt.y - c.y;
		x2 = a.x - c.x;
		y2 = a.y - c.y;
		one = x1 * y2 * -1;
		two = y1 * x2;
		double result3 = one + two;
		if (result1 < 0 && result2 < 0 && result3 < 0) {
			this.isSelected = true;
		}else if (result1 > 0 && result2 > 0 && result3 > 0) {
			this.isSelected = true;
		}else {
			this.isSelected = false;
		}
		return this.isSelected;
	}

	@Override
	public void resetShape(Double start, Double end) {
	}

}
